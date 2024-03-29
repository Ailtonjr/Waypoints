package br.com.waypoints.waypoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecuperarActivity extends AppCompatActivity {

    private Button botaoEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        botaoEnviar = (Button) findViewById(R.id.buttonEnviarRecuperar);

        botaoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO enviar email com nova senha gerada para conta de email
                Intent intentLogin = new Intent(RecuperarActivity.this, MainActivity.class);
                intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentLogin);
            }
        });
    }
}
