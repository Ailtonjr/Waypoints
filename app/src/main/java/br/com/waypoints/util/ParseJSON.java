package br.com.waypoints.util;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;

public class ParseJSON {

    public Usuario loadUserFromJSON(String jsonString) {
        Usuario user = new Usuario();
        try {
            JSONObject userObject = new JSONObject(jsonString);
            user.setId((int) userObject.getLong("id"));
            user.setNome(userObject.getString("nome"));
            user.setEmail(userObject.getString("email"));
            user.setCategoriaCNH(userObject.getString("categoriaCNH"));
            user.setSexo(userObject.getString("sexo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

}
