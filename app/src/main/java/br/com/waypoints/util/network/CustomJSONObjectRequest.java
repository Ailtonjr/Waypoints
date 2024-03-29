package br.com.waypoints.util.network;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomJSONObjectRequest extends JsonObjectRequest {

    private static String urlBase ="http://gmuh.dyndns.info:3000/waypoints-ws/recursos/";
    public CustomJSONObjectRequest(int method, String url, JSONObject jsonRequest,
                                    Response.Listener<JSONObject> listener,
                                    Response.ErrorListener errorListener) {
        super(method, urlBase + url, jsonRequest, listener, errorListener);
    }

    public CustomJSONObjectRequest(String url, JSONObject jsonRequest,
                                   Response.Listener<JSONObject> listener,
                                   Response.ErrorListener errorListener) {
        super(urlBase + url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }
}
