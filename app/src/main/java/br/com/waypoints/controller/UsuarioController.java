package br.com.waypoints.controller;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.model.UsuarioFA;


public class UsuarioController {
    private UsuarioFA usuarioFA;

    public UsuarioController(){
        usuarioFA = new UsuarioFA();
    }

    public void login(View v, ProgressDialog pDialog, String email, String senha) throws BusinessException {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("email", email);
            jsonData.put("senha", senha);
            usuarioFA.login(v, pDialog, jsonData);
        } catch (JSONException e) {
            Log.e("Log", e.getMessage());
        }
    }


    public void cadastro(View v,ProgressDialog pDialog , String nome, String email, String senha, String cnh, String sexo) throws BusinessException {

        JSONObject jsonData = new JSONObject();
        try {
            jsonData.put("nome", nome);
            jsonData.put("email", email);
            jsonData.put("senha", senha);
            jsonData.put("categoriaCNH", cnh);
            jsonData.put("sexo", sexo);
            usuarioFA.cadastro(v, pDialog, jsonData);
        } catch (JSONException e) {
            Log.e("Log", e.getMessage());
        }
    }
}
