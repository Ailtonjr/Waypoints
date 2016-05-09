package br.com.waypoints.util;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.entity.Grupo;
import br.com.waypoints.entity.Usuario;

public class JSONUtil {

    public JSONObject getJSON(Grupo obj) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject getJSON(Usuario obj) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(obj);

        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }


    public Usuario loadUserFromJSON(String jsonString) {
        Usuario user = new Usuario();
        try {
            JSONObject userObject = new JSONObject(jsonString);
            //user.setId(userObject.getLong("id"));
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
