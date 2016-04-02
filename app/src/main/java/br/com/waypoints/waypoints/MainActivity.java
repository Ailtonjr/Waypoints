package br.com.waypoints.waypoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button botaoEntrar;
    private TextView cadastro;
    private TextView recupera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        botaoEntrar = (Button) findViewById(R.id.buttonEntrarLogin);
        cadastro = (TextView) findViewById(R.id.textViewCadastroLogin);
        recupera = (TextView) findViewById(R.id.textViewEsqueciSenhaLogin);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentEntrar = new Intent(MainActivity.this, RotasActivity.class);
                intentEntrar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentEntrar);
            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intentCadastro);
            }
        });

        recupera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecuperar = new Intent(MainActivity.this, RecuperarActivity.class);
                startActivity(intentRecuperar);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            Bundle bundleParametros = getIntent().getExtras();
            editTextEmail.setText(bundleParametros.getString("email"));
        } catch (Exception ex) {

        }

    }
}