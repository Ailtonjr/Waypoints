package br.com.waypoints.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.util.network.CustomJSONObjectRequest;
import br.com.waypoints.util.network.CustomVolleyRequestQueue;
import br.com.waypoints.util.network.VolleyCallback;

public class GrupoService {
    private RequestQueue mQueue;

    public void cadastrar(final Context context, final VolleyCallback callback, JSONObject usuarioJSON) throws BusinessException {
        String url ="grupo/cadastro";

        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(Request.Method
                .POST, url,
                usuarioJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response.toString());            }
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
                        callback.onError("Nome do grupo já foi usado.");
                        break;
                    case 500:
                    case 0:
                        callback.onError("Problemas no Servidor, Info no SAC");
                        break;
                }
                //pDialog.hide();
                Log.i("Log", "Erro " + statusCode);
            }
        });
        jsObjRequest.setTag("Grupo");

        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
        mQueue.add(jsObjRequest);
    }


    public void getGrupos(final Context context,VolleyCallback callback, JSONObject jsonUsuario) {
        /*String url ="grupo/proprietario/";

        final CustomJSONObjectRequest jsObjRequest = new CustomJSONObjectRequest(url, jsonUsuario , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Resposta", response.toString());
      }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        });
        jsObjRequest.setTag("Grupo");

        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
        mQueue.add(jsObjRequest);*/
    }
}
