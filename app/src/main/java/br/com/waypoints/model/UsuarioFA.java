package br.com.waypoints.model;


import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.service.UsuarioService;
import br.com.waypoints.util.EmailUtil;


public class UsuarioFA {



	private UsuarioService usuarioService;
	private final int TAM_MIN_SENHA = 8;

	public UsuarioFA() {
		usuarioService = new UsuarioService();
	}

	public Usuario login(View v, JSONObject jsonUsuario) throws BusinessException, JSONException {
		if ((jsonUsuario.get("email") == null)
				|| (jsonUsuario.get("email").toString().isEmpty())
				|| (!EmailUtil.isValid(jsonUsuario.get("email").toString()))) {
			throw new BusinessException("O email informado é inválido.");
		}
        /*if (usuarioDAO.getByEmail(usuario.getEmail()) == null) {
            throw new BusinessException("E-mail não cadastrado.");
        }*/
		return usuarioService.doLogin(v, jsonUsuario);
	}

	public Usuario cadastro(View v, JSONObject jsonUsuario) throws BusinessException {

		try {
			validaUsuario(jsonUsuario);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		return usuarioService.cadastrar(v, jsonUsuario);
	}

	private void validaUsuario(JSONObject usuario) throws BusinessException {
		try {
			if (usuario.get("nome") == null || usuario.get("nome").toString().isEmpty()) {
                throw new BusinessException("O nome informado é inválido.");
            }
			if ((usuario.get("email") == null)
					|| (usuario.get("email").toString().isEmpty())
					|| (!EmailUtil.isValid(usuario.get("email").toString()))) {
				throw new BusinessException("O email informado é inválido.");
			}
			if ((usuario.get("senha") == null) || (usuario.get("senha").toString().isEmpty())) {
				throw new BusinessException("A senha informada é inválida.");
			}
			if (usuario.get("senha").toString().length() < TAM_MIN_SENHA) {
				throw new BusinessException("A senha deve ter pelo menos \"" + TAM_MIN_SENHA + "\" caracteres.");
			}

//			if (usuarioDAO.getByEmail(usuario.getEmail()) != null) {
//				throw new BusinessException("Este e-mail já está cadastrado.");
//			}
			if (usuario.get("sexo") == null) {
				throw new BusinessException("O sexo deve ser selecionado.");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
