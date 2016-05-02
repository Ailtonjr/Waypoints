package br.com.waypoints.waypoints;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.waypoints.controller.UsuarioController;
import br.com.waypoints.exeption.BusinessException;

public class CadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private EditText confirmaSenha;
    private Spinner cnh;
    private RadioButton radioMasc;
    private RadioButton radioFem;
    private UsuarioController usuarioController;

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        nome = (EditText) findViewById(R.id.editTextNomeCadastro);
        email = (EditText) findViewById(R.id.editTextEmailCadastro);
        senha = (EditText) findViewById(R.id.editTextSenhaCadastro);
        confirmaSenha = (EditText) findViewById(R.id.editTextConfirmaSenhaCadastro);
        cnh = (Spinner) findViewById(R.id.spinnerCNHCadastro);
        radioMasc = (RadioButton) findViewById(R.id.radioButtonMascCadastro);
        radioFem = (RadioButton) findViewById(R.id.radioButtonFemCadastro);

        nome.setText("testes");
        email.setText("testes@gmail.com");
        senha.setText("teste");
        confirmaSenha.setText("teste123");
        cnh.setSelection(6);


        usuarioController = new UsuarioController();

        radioMasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioFem.isChecked()) radioFem.setChecked(false);
                view = v;
            }
        });

        radioFem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioMasc.isChecked()) radioMasc.setChecked(false);
                view = v;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_mode_close_button) {
            if(sexo() != null) {
                ProgressDialog pDialog = new ProgressDialog(view.getContext());
                if (senha.getText().toString().equals(confirmaSenha.getText().toString())) {
                    try {
                        pDialog.setMessage("Loading...");
                        pDialog.show();
                        usuarioController.cadastro(view,
                                pDialog,
                                nome.getText().toString(),
                                email.getText().toString(),
                                senha.getText().toString(),
                                cnh.getSelectedItem().toString(),
                                sexo()
                        );
                    } catch (BusinessException e) {
                        pDialog.hide();
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(view.getContext(), "Senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Deve selecionar o Sexo", Toast.LENGTH_LONG).show();
            }

            return true;
        }
        return false;
    }

    private String sexo() {
        if(radioMasc.isChecked()){
            return "M";
        }else if (radioFem.isChecked()){
            return "F";
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.botao_ok, menu);
        return true;
    }
}
