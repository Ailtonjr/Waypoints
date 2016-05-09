package br.com.waypoints.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.util.JSONUtil;
import br.com.waypoints.util.network.CustomJSONObjectRequest;
import br.com.waypoints.util.network.CustomVolleyRequestQueue;
import br.com.waypoints.util.network.VolleyCallback;

public class GrupoService {
    private RequestQueue mQueue;
    private JSONUtil JSONUtil;

    public GrupoService(){
        JSONUtil = new JSONUtil();
    }

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
                callback.onError("erro!");
            }
        });
        jsObjRequest.setTag("Grupo");

        mQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();
        mQueue.add(jsObjRequest);
    }
}
