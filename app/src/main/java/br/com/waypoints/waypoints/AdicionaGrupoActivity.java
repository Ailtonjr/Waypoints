package br.com.waypoints.waypoints;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.waypoints.controller.GrupoController;
import br.com.waypoints.entity.Integrante;
import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.util.network.VolleyCallback;

public class AdicionaGrupoActivity extends AppCompatActivity {

    private EditText nome;
    private Spinner ramo;
    private RadioButton radioButtonPlanejador;
    private RadioButton radioButtonEntregador;
    private Context context;
    private EditText editTextEmail;
    private ImageButton imageButtonAdd;
    private GrupoController grupoController;
    private ListView listViewEmails;
    private List<String> listEmails;
    private ArrayAdapter<String> adapter;
    private  Usuario usuarioGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_grupo);

        nome = (EditText) findViewById(R.id.editTextNomeAdicionaGrupo);
        ramo = (Spinner) findViewById(R.id.spinnerRamoNovoGrupo);
        radioButtonPlanejador = (RadioButton) findViewById(R.id.radioButtonPlanejadorAdicionaGrupo);
        radioButtonEntregador = (RadioButton) findViewById(R.id.radioButtonEntregadorAdicionaGrupo);
        editTextEmail = (EditText) findViewById(R.id.editTextEmailAdicionaGrupo);
        imageButtonAdd = (ImageButton) findViewById(R.id.imageButtonAddAdicionaGrupo);
        listViewEmails = (ListView) findViewById(R.id.listViewEmailAdicionaGrupo);
        adapter = new ArrayAdapter(this, R.layout.itemlist_email, R.id.textViewEmailItemList);
        listViewEmails.setAdapter(adapter);

        listEmails = new ArrayList<>();
        grupoController = new GrupoController();

        context = imageButtonAdd.getContext();
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
            }
        });

        radioButtonEntregador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonPlanejador.isChecked()) radioButtonPlanejador.setChecked(false);
            }
        });

        //TESTE de setar o nome e email no menu
        Intent intent = getIntent();
        usuarioGet = (Usuario) intent.getSerializableExtra("usuario");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_ok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mode_close_button) {
            final ProgressDialog pDialog = new ProgressDialog(context);
            try {
                pDialog.show();
                VolleyCallback volleyCallback = new VolleyCallback() {

                    @Override
                    public void onSuccess(Object object) {
                        pDialog.hide();
                        Toast.makeText(context, "Grupo criado com sucesso", Toast.LENGTH_LONG).show();
                        Intent intentGrupo = new Intent(context, GruposActivity.class);
                        intentGrupo.putExtra("usuario", usuarioGet);
                        intentGrupo.putExtra("grupo", nome.getText().toString());
                        context.startActivity(intentGrupo);
                    }

                    @Override
                    public void onError(String mensagem) {
                        pDialog.hide();
                        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
                    }
                };
                Usuario u1 = new Usuario();
                u1.setId(new Long(8));
                u1.setNome("Rômulo Göelzer Portolann");
                u1.setEmail("romulogoelzer@gmail.com");
                u1.setCategoriaCNH("AB");
                u1.setSexo("M");

                Usuario u2 = new Usuario();
                u2.setId(new Long(8));
                u2.setNome("Rômulo Göelzer Portolann");
                u2.setEmail("romulogoelzer@gmail.com");
                u2.setCategoriaCNH("AB");
                u2.setSexo("M");

                Integrante i1 = new Integrante();
                i1.setUsuario(u1);
                i1.setPapel("ADMIN");

                Integrante i2 = new Integrante();
                i2.setUsuario(u2);
                i2.setPapel("PLANEJADOR");

                List<Integrante> integrantes = new ArrayList<>();
                integrantes.add(i1);
                integrantes.add(i2);

                grupoController.cadastro(context,
                        volleyCallback,
                        integrantes,
                        nome.getText().toString(),
                        /*ramo.getSelectedItem().toString()*/ "RamoTeste",
                        "ProprietarioTeste",
                        (long) 8
                );
            } catch (BusinessException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            return true;
        }
        return false;
    }

    public void addListItem(String email) {
        listEmails.add(email);
        adapter.add(email);
        adapter.notifyDataSetChanged();
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
