package ues.grupo6.horariospdm;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
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

        drawerLayout = (DrawerLayout) findViewById(R.id.main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open_menu,R.string.closed_menu);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}