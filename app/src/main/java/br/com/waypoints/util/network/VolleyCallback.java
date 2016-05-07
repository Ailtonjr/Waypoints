package br.com.waypoints.util.network;

import br.com.waypoints.entity.Usuario;

public interface VolleyCallback {
    void onSuccess(Usuario usuario);
    void onError(String mensagem);
}
