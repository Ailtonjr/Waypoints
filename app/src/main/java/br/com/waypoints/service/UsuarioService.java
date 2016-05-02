package br.com.waypoints.service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.network.CustomJSONObjectRequest;
import br.com.waypoints.network.CustomVolleyRequestQueue;
import br.com.waypoints.util.ParseJSON;
import br.com.waypoints.waypoints.MainActivity;
import br.com.waypoints.waypoints.RotasActivity;

public class UsuarioService {

    private ParseJSON parseJSON;
    private RequestQueue mQueue;
    private Usuario usuario;
    private String mensagemError = "";

    public UsuarioService(){
        parseJSON = new ParseJSON();
    }

    public Usuario doLogin(final View v, JSONObject usuarioJSON) throws BusinessException {
        String url ="http://gmuh.dyndns.info:3000/waypoints-ws/recursos/usuario/login";
        //RequestQueue queue = Volley.newRequestQueue(v.getContext());


        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                usuarioJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(v.getContext(), "Login efetuado com sucesso", Toast.LENGTH_LONG).show();
                usuario = parseJSON.loadUserFromJSON(response.toString());
                Intent intentEntrar = new Intent(v.getContext(), RotasActivity.class);
                intentEntrar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                v.getContext().startActivity(intentEntrar);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                int  statusCode = 0;
                if (error.networkResponse != null) {
                    statusCode = error.networkResponse.statusCode;
                }
                switch (statusCode){
                    case 403:
                    case 404:
                    case 406:

                        Toast.makeText(v.getContext(), "Usuario ou senha não confere", Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                        Toast.makeText(v.getContext(), "Servidor Offline", Toast.LENGTH_LONG).show();
                        break;
                }
                //Toast.makeText(v.getContext(), "Erro ao tentar se conectar ao servidor ", Toast.LENGTH_LONG).show();

                Log.i("Log", "Erro " + statusCode);
            }


        });
        jsObjRequest.setTag("Login");

        mQueue = CustomVolleyRequestQueue.getInstance(v.getContext()).getRequestQueue();
        mQueue.add(jsObjRequest);

        return usuario;
    }


    public Usuario cadastrar(final View v, final ProgressDialog pDialog, JSONObject usuarioJSON) throws BusinessException {
        String url ="http://gmuh.dyndns.info:3000/waypoints-ws/recursos/usuario/cadastro";

        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                usuarioJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(v.getContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show();
                usuario = parseJSON.loadUserFromJSON(response.toString());
                //Intent intentEntrar = new Intent(v.getContext(), RotasActivity.class);

                Intent intentLogin = new Intent(v.getContext(), MainActivity.class);
                intentLogin.putExtra("email", usuario.getEmail()); // Envia campo email por parametro para MainActivity
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(intentLogin);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                int  statusCode = 0;
                if (error.networkResponse != null) {
                    statusCode = error.networkResponse.statusCode;
                }
                switch (statusCode){
                    case 403:
                    case 404:
                    case 406:

                        Toast.makeText(v.getContext(), "E-mail já está cadastrado.", Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                    case 0:
                        Toast.makeText(v.getContext(), "Servidor Offline", Toast.LENGTH_LONG).show();
                        break;
                }
                pDialog.hide();
                Log.i("Log", "Erro " + statusCode);
            }


        });
        jsObjRequest.setTag("Login");

        mQueue = CustomVolleyRequestQueue.getInstance(v.getContext()).getRequestQueue();
        mQueue.add(jsObjRequest);

        return usuario;
    }
}
