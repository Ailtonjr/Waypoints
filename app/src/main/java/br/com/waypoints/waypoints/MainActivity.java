package br.com.waypoints.waypoints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView cadastro = (TextView) findViewById(R.id.textViewCadastro);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activityCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(activityCadastro);
            }
        });
    }


}
