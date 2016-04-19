package br.com.waypoints.waypoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

public class NovaRotaActivity extends AppCompatActivity {

    private EditText editTextNome;
    private Spinner spinnerGrupo;
    private Spinner spinnerEntregador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_rota);

        editTextNome = (EditText) findViewById(R.id.editTextNomeNovaRota);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupoNovaRota);
        spinnerEntregador = (Spinner) findViewById(R.id.spinnerEntregadorNovaRota);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mode_close_button) {
            Intent intentPontos = new Intent(NovaRotaActivity.this, MainActivity.class);
            intentPontos.putExtra("nome", editTextNome.getText());
            intentPontos.putExtra("grupo", spinnerGrupo.getSelectedItem().toString()); // verificar se e assim que pega o nome
            intentPontos.putExtra("entregador", spinnerEntregador.getSelectedItem().toString()); // verificar se e assim que pega o nome
            intentPontos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentPontos);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_ok, menu);
        return true;
    }
}
