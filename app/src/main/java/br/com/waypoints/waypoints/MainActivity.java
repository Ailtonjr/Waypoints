package br.com.waypoints.waypoints;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import br.com.waypoints.controller.UsuarioController;
import br.com.waypoints.entity.Usuario;
import br.com.waypoints.exeption.BusinessException;
import br.com.waypoints.util.network.CustomVolleyRequestQueue;
import br.com.waypoints.util.network.VolleyCallback;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button botaoEntrar;
    private TextView cadastro;
    private TextView recupera;


    private UsuarioController usuarioController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenhaLogin);
        botaoEntrar = (Button) findViewById(R.id.buttonEntrarLogin);
        cadastro = (TextView) findViewById(R.id.textViewCadastroLogin);
        recupera = (TextView) findViewById(R.id.textViewEsqueciSenhaLogin);

        editTextEmail.setText("ailton@gmail.com");
        editTextSenha.setText("teste123");


        usuarioController = new UsuarioController();
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final ProgressDialog pDialog = new ProgressDialog(v.getContext());
                try {
                    pDialog.setMessage("Loading...");
                    pDialog.show();
                    usuarioController.login(v, new VolleyCallback() {
                        @Override
                        public void onSuccess(Object object) {
                            Usuario usuario = (Usuario) object;
                            pDialog.hide();
                            Toast.makeText(getApplicationContext(), "Bem Vindo " + usuario.getNome(), Toast.LENGTH_LONG).show();
                            Intent intentEntrar = new Intent(v.getContext(), RotasActivity.class);
                            intentEntrar.putExtra("usuario", usuario);
                            intentEntrar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            v.getContext().startActivity(intentEntrar);
                        }
                        @Override
                        public void onError(String mensagem) {
                            pDialog.hide();
                            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
                        }
                    },editTextEmail.getText().toString(), editTextSenha.getText().toString());
                } catch (BusinessException e) {
                    pDialog.hide();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
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
                ex.printStackTrace();
            }

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();   // Para impedir que apos logado volte a tela de login sem deslogar
    }

    @Override
    public void onStop(){
        super.onStop();
        RequestQueue queue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
        queue.cancelAll("Login");
    }

}