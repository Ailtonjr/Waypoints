package br.com.waypoints.waypoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CadastroActivity extends AppCompatActivity {

    private EditText email;
    private Spinner cnh;
    private RadioButton radioMasc;
    private RadioButton radioFem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = (EditText) findViewById(R.id.editTextEmailCadastro);
        cnh = (Spinner) findViewById(R.id.spinnerCNHCadastro);
        radioMasc = (RadioButton) findViewById(R.id.radioButtonMascCadastro);
        radioFem = (RadioButton) findViewById(R.id.radioButtonFemCadastro);

        radioMasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioFem.isChecked()) radioFem.setChecked(false);
            }
        });

        radioFem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioMasc.isChecked()) radioMasc.setChecked(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mode_close_button) {
            Intent intentLogin = new Intent(CadastroActivity.this, MainActivity.class);
            intentLogin.putExtra("email", email.getText().toString()); // Envia campo email por parametro para MainActivity
            intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentLogin);
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
