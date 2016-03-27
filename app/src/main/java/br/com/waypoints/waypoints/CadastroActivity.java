package br.com.waypoints.waypoints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final Spinner cnh = (Spinner) findViewById(R.id.spinnerCNHCadastro);
        final RadioButton radioMasc = (RadioButton) findViewById(R.id.radioButtonMascCadastro);
        final RadioButton radioFem = (RadioButton) findViewById(R.id.radioButtonFemCadastro);

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
}
