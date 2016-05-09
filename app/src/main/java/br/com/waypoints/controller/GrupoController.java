package br.com.waypoints.controller;

import android.content.Context;

import java.util.List;

import br.com.waypoints.entity.Grupo;
import br.com.waypoints.entity.Integrante;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.model.GrupoFA;
import br.com.waypoints.util.JSONUtil;
import br.com.waypoints.util.network.VolleyCallback;

public class GrupoController {
    private GrupoFA grupoFA;
    private Grupo grupo;
    private JSONUtil jsonUtil;

    public GrupoController(){
        grupo = new Grupo();
        grupoFA = new GrupoFA();
        jsonUtil = new JSONUtil();
    }

    public void cadastro(Context context, VolleyCallback callback, List<Integrante> integrantes, String nome, String ramo, String nomeProprietario, Long id) throws BusinessException {
        grupo.setNome(nome);
        grupo.setRamo(ramo);
        grupo.setNomeProprietario(nomeProprietario);
        grupo.setIntegrantes(integrantes);
        grupo.setProprietarioId(id);
        grupoFA.cadastro(context, callback, jsonUtil.getJSON(grupo));
    }
}
