package br.com.waypoints.waypoints;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView cadastro = (TextView) findViewById(R.id.textViewCadastroLogin);
        final TextView recupera = (TextView) findViewById(R.id.textViewEsqueciSenhaLogin);

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
}