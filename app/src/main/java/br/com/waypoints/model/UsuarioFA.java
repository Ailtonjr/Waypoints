package br.com.waypoints.model;


import android.app.ProgressDialog;
import android.view.View;

import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.service.UsuarioService;
import br.com.waypoints.util.EmailUtil;
import br.com.waypoints.util.network.VolleyCallback;


public class UsuarioFA {



	private UsuarioService usuarioService;
	private final int TAM_MIN_SENHA = 8;

	public UsuarioFA() {
		usuarioService = new UsuarioService();
	}

	public void login(View v, VolleyCallback volleyCallback, JSONObject jsonUsuario) throws BusinessException, JSONException {
		if ((jsonUsuario.get("email") == null)
				|| (jsonUsuario.get("email").toString().isEmpty())
				|| (!EmailUtil.isValid(jsonUsuario.get("email").toString()))) {
			throw new BusinessException("O email informado é inválido.");
		}
		usuarioService.doLogin(v, volleyCallback, jsonUsuario);
	}

	public void cadastro(View v, VolleyCallback callback, JSONObject jsonUsuario) throws BusinessException {

		try {
			validaUsuario(jsonUsuario);
		} catch (BusinessException be) {
			throw new BusinessException(be.getMessage());
		}
		usuarioService.cadastrar(v, callback, jsonUsuario);
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
			if (usuario.get("sexo") == null) {
				throw new BusinessException("O sexo deve ser selecionado.");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
