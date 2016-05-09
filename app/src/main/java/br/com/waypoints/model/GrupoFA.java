package br.com.waypoints.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.service.GrupoService;
import br.com.waypoints.util.network.VolleyCallback;

public class GrupoFA {
    GrupoService grupoService;
    public GrupoFA() {
        grupoService = new GrupoService();
    }
    public void cadastro(Context context, VolleyCallback callback, JSONObject jsonGrupo) throws BusinessException {
        try {
            validaGrupo(jsonGrupo);
        } catch (BusinessException be) {
            throw new BusinessException(be.getMessage());
        }
        Log.d("Grupos", jsonGrupo.toString());
        grupoService.cadastrar(context, callback, jsonGrupo);
    }

    public void getGrupos(Context context, VolleyCallback callback, JSONObject jsonUsuario) {
        grupoService.getGrupos(context, callback, jsonUsuario);
    }

    private void validaGrupo(JSONObject usuario) throws BusinessException {

        try {
            if (usuario.get("nome") == null || usuario.get("nome").toString().isEmpty()) {
                throw new BusinessException("O nome informado é inválido.");
            }
            if ((usuario.get("ramo") == null) || (usuario.get("ramo").toString().isEmpty())) {
                throw new BusinessException("O Ramo deve ser informado.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
