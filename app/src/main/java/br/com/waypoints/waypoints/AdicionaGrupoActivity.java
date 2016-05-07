package br.com.waypoints.waypoints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class AdicionaGrupoActivity extends AppCompatActivity {

    private RadioButton radioButtonPlanejador;
    private RadioButton radioButtonEntregador;
    private View view;
    private ImageButton imageButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_grupo);

        radioButtonPlanejador = (RadioButton) findViewById(R.id.radioButtonPlanejadorAdicionaGrupo);
        radioButtonEntregador = (RadioButton) findViewById(R.id.radioButtonEntregadorAdicionaGrupo);
        imageButtonAdd = (ImageButton) findViewById(R.id.imageButtonAddAdicionaGrupo);

        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Botao adiciona integrante. Adicionar a uma lista antes de integrar o grupo por completo.
            }
        });

        radioButtonPlanejador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonEntregador.isChecked()) radioButtonEntregador.setChecked(false);
                view = v;
            }
        });

        radioButtonEntregador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonPlanejador.isChecked()) radioButtonPlanejador.setChecked(false);
                view = v;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_ok, menu);
        return true;
    }

    private String tipoIntegrante() {
        if(radioButtonEntregador.isChecked()){
            return "Entregador";
        }else if (radioButtonPlanejador.isChecked()){
            return "Planejador";
        }
        return null;
    }
}
