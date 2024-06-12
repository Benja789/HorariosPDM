package ues.grupo6.horariospdm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import ues.grupo6.horariospdm.menus.AsignaturaMenuActivity;
import ues.grupo6.horariospdm.menus.CicloAcademicoMenuActivity;
import ues.grupo6.horariospdm.menus.DocenteMenu;
import ues.grupo6.horariospdm.menus.EscuelaMenuActivity;
import ues.grupo6.horariospdm.menus.EventoMenuActivity;
import ues.grupo6.horariospdm.menus.GrupoMenuActivity;
import ues.grupo6.horariospdm.menus.TipoEventoMenuActivity;
import ues.grupo6.horariospdm.menus.TipoFuncionMenuActivity;
import ues.grupo6.horariospdm.menus.TipoGrupoMenuActivity;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {
    DrawerLayout drawerLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    CollectionReference collectionRef = db.collection("evento");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolBar();
        initNavigationView();

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onStart () {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if ( user != null ){
            listenToChangesInFirebase();
        }
    }

    private void listenToChangesInFirebase() {
        collectionRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                List<DocumentSnapshot> documentList = new ArrayList<>();
                for (DocumentChange dc : snapshots.getDocumentChanges()) {
                    documentList.add(dc.getDocument());
                }
                mAdapter = new MyAdapter(documentList);
                recyclerView.setAdapter(mAdapter);
            }
        });
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
        if (item.getItemId() == R.id.item_one) System.out.println("item 1");
        else if ( item.getItemId() == R.id.item_two) callNewActivity(AboutActivity.class);
        else if ( item.getItemId() == R.id.item_three ) {
            FirebaseAuth.getInstance().signOut();
            callNewActivity(LoginActivity.class);
        }
        else if ( item.getItemId() ==R.id.item_fourth ) callNewActivity(AsignaturaMenuActivity.class);
        else if (item.getItemId() == R.id.item_six)  callNewActivity(DocenteMenu.class);
        else if (item.getItemId() == R.id.item_five) callNewActivity(TipoEventoMenuActivity.class);
        else if ( item.getItemId() == R.id.item_seven )callNewActivity(TipoFuncionMenuActivity.class);
        else if ( item.getItemId() ==  R.id.item_eight ) callNewActivity(EscuelaMenuActivity.class);
        else if ( item.getItemId() == R.id.item_nine ) callNewActivity(TipoGrupoMenuActivity.class);
        else if ( item.getItemId() ==  R.id.item_ten ) callNewActivity(GrupoMenuActivity.class);
        else if ( item.getItemId() == R.id.item_eleven ) callNewActivity(CicloAcademicoMenuActivity.class);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void navigateTo (View view) {
        if (view.getId() == R.id.btn_schedule) callNewActivity(ScheduleActivity.class);
        else if ( view.getId() == R.id.btn_events ) callNewActivity( EventoMenuActivity.class);
        else if ( view.getId() == R.id.btn_request ) callNewActivity(RequestActivity.class);
        else callNewActivity(EventoMenuActivity.class);
    }

    private void callNewActivity (Class<?> activityClass) {
        Intent newView = new Intent(this, activityClass);
        startActivity(newView);
    }
}