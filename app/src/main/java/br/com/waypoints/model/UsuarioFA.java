package br.com.waypoints.model;


import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.service.UsuarioService;
import br.com.waypoints.util.EmailUtil;


public class UsuarioFA {



	UsuarioService usuarioService;

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

	public Usuario cadastro(Usuario usuario) {

		return null;
	}
	
}
