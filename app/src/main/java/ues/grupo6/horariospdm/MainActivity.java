package ues.grupo6.horariospdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import ues.grupo6.horariospdm.menus.EventoMenuActivity;
import ues.grupo6.horariospdm.menus.TipoEventoMenuActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initToolBar();
    }

    private void initToolBar () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_element);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layout_activity);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_menu, R.string.closed_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void navigateTo (View view) {
        Intent newView;
        System.out.println("INTENTO------------------------------------------------");
        if (view.getId() == R.id.btn_schedule) {
            newView = new Intent(this, ScheduleActivity.class);
        } else if ( view.getId() == R.id.btn_events ) {
            newView = new Intent(this, EventsActivity.class);
        } else if ( view.getId() == R.id.btn_request ){
            newView = new Intent(this, RequestActivity.class);
        } else if ( view.getId() == R.id.btn_TipoEvento ){
            newView = new Intent(this, TipoEventoMenuActivity.class);
        }else{
            newView = new Intent(this, EventoMenuActivity.class);
        }
        startActivity(newView);
    }
}