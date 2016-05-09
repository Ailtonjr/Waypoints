package br.com.waypoints.service;

import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.util.JSONUtil;
import br.com.waypoints.util.network.CustomJSONObjectRequest;
import br.com.waypoints.util.network.CustomVolleyRequestQueue;
import br.com.waypoints.util.network.VolleyCallback;

public class UsuarioService{

    private JSONUtil JSONUtil;
    private RequestQueue mQueue;

    public UsuarioService(){
        JSONUtil = new JSONUtil();
    }

    public void doLogin(final View v, final VolleyCallback volleyCallback, final JSONObject usuarioJSON) throws BusinessException {
        String url ="usuario/login";

        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                usuarioJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Usuario usuario = JSONUtil.loadUserFromJSON(response.toString());

                volleyCallback.onSuccess(usuario);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error){

                int  statusCode = 0;
                if (error.networkResponse != null) {
                    statusCode = error.networkResponse.statusCode;
                }
                switch (statusCode) {
                    case 401:
                    case 403:
                        //Toast.makeText(v.getContext(), "Usuario ou senha não confere", Toast.LENGTH_LONG).show();
                        volleyCallback.onError("Usuario ou senha não confere");
                        break;
                    case 500:
                    case 0:
                        //Toast.makeText(v.getContext(), "Servidor Offline", Toast.LENGTH_LONG).show();
                        volleyCallback.onError("Servidor Offline");
                        break;
                }
                //pDialog.hide();
                Log.i("Log", "Erro " + statusCode);
            }


        });
        jsObjRequest.setTag("Login");

        mQueue = CustomVolleyRequestQueue.getInstance(v.getContext()).getRequestQueue();
        mQueue.add(jsObjRequest);
    }


    public void cadastrar(final View v, final VolleyCallback callback, JSONObject usuarioJSON) throws BusinessException {
        String url ="usuario/cadastro";

        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                usuarioJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(JSONUtil.loadUserFromJSON(response.toString()));

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
                        callback.onError("E-mail já usado!");
                        break;
                    case 500:
                    case 0:
                        callback.onError("Servidor Offline");
                        break;
                }
                Log.i("Log", "Erro " + statusCode);
            }


        });
        jsObjRequest.setTag("Login");

        mQueue = CustomVolleyRequestQueue.getInstance(v.getContext()).getRequestQueue();
        mQueue.add(jsObjRequest);
    }
}
