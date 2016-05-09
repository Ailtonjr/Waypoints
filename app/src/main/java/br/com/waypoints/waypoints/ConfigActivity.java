package br.com.waypoints.waypoints;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.waypoints.entity.Usuario;

public class ConfigActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private  Usuario usuarioGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //TESTE de setar o nome e email no menu
        Intent intent = getIntent();
        usuarioGet = (Usuario) intent.getSerializableExtra("usuario");

        View hView =  navigationView.getHeaderView(0);
        TextView nomeMenu = (TextView)hView.findViewById(R.id.textViewNomeMenu);
        TextView emailMenu = (TextView)hView.findViewById(R.id.textViewEmailMenu);
        nomeMenu.setText(usuarioGet.getNome());
        emailMenu.setText(usuarioGet.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rotas) {
            Intent intentRotas = new Intent(ConfigActivity.this, RotasActivity.class);
            intentRotas.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentRotas.putExtra("usuario", usuarioGet);
            startActivity(intentRotas);
            finish();
        } else if (id == R.id.nav_grupos) {
            Intent intentGrupos = new Intent(ConfigActivity.this, GruposActivity.class);
            intentGrupos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intentGrupos.putExtra("usuario", usuarioGet);
            startActivity(intentGrupos);
            finish();
        } else if (id == R.id.nav_configuracoes) {
            Intent intentConfiguracoes = new Intent(ConfigActivity.this, ConfigActivity.class);
            intentConfiguracoes.putExtra("usuario", usuarioGet);
            startActivity(intentConfiguracoes);
        } else if (id == R.id.nav_sobre) {
            Intent intentSobre = new Intent(ConfigActivity.this, SobreActivity.class);
            startActivity(intentSobre);
        } else if (id == R.id.nav_sair) {
            Intent intentLogin = new Intent(ConfigActivity.this, MainActivity.class);
            startActivity(intentLogin);
            // Aqui deletar HASH de sessão no app e no banco.
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
