package br.com.waypoints.util.network;

public interface VolleyCallback {
    void onSuccess(Object object);
    void onError(String mensagem);
}
