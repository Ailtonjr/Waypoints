package br.com.waypoints.waypoints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdicionaGrupoActivity extends AppCompatActivity {

    private RadioButton radioButtonPlanejador;
    private RadioButton radioButtonEntregador;
    private View view;
    private EditText editTextEmail;
    private ImageButton imageButtonAdd;
    private ListView listViewEmails;
    private List<String> listEmails = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_grupo);

        radioButtonPlanejador = (RadioButton) findViewById(R.id.radioButtonPlanejadorAdicionaGrupo);
        radioButtonEntregador = (RadioButton) findViewById(R.id.radioButtonEntregadorAdicionaGrupo);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailAdicionaGrupo);
        imageButtonAdd = (ImageButton) findViewById(R.id.imageButtonAddAdicionaGrupo);
        listViewEmails = (ListView) findViewById(R.id.listViewEmailAdicionaGrupo);
        adapter = new ArrayAdapter(this, R.layout.itemlist_email, R.id.textViewEmailItemList);
        listViewEmails.setAdapter(adapter);

        imageButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Botao adiciona integrante. Adicionar a uma lista antes de integrar o grupo por completo.
                addListItem(editTextEmail.getText().toString());
                editTextEmail.setText("");
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

    public void addListItem(String email) {
        listEmails.add(email);
        adapter.add(email);
        adapter.notifyDataSetChanged();
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
