package ues.grupo6.horariospdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import ues.grupo6.horariospdm.menus.EventoMenuActivity;
import ues.grupo6.horariospdm.menus.TipoEventoMenuActivity;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initNavigationView();
    }

    private void initToolBar () {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar_element);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.layout_activity);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_menu, R.string.closed_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initNavigationView () {
        NavigationView navigationView = findViewById(R.id.navigation_bar);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        System.out.println("Accion del boton del menu");
        if (item.getItemId() == R.id.item_one) {
            System.out.println("item 1");
        } else if ( item.getItemId() == R.id.item_two) {
            System.out.println("item 2");
        } else if ( item.getItemId() == R.id.item_three ) {
            System.out.println("item 3");
        } else if ( item.getItemId() ==R.id.item_fourth ){
            System.out.println("item 4");
        } else callTypeEventActivity();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    public void navigateTo (View view) {
        Intent newView;
        if (view.getId() == R.id.btn_schedule) {
            newView = new Intent(this, ScheduleActivity.class);
        } else if ( view.getId() == R.id.btn_events ) {
            newView = new Intent(this, EventsActivity.class);
        } else if ( view.getId() == R.id.btn_request ){
            newView = new Intent(this, RequestActivity.class);
//        } else if ( view.getId() == R.id.btn_TipoEvento ){
//            newView = new Intent(this, TipoEventoMenuActivity.class);
        }else{
            newView = new Intent(this, EventoMenuActivity.class);
        }
        startActivity(newView);
    }
    private void callTypeEventActivity () {
        Intent newView = new Intent(this, TipoEventoMenuActivity.class);
        startActivity(newView);

    }

}