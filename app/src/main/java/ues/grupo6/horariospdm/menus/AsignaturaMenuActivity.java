package ues.grupo6.horariospdm.menus;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import ues.grupo6.horariospdm.R;
import androidx.appcompat.app.AppCompatActivity;

/** @noinspection ALL*/
public class AsignaturaMenuActivity extends AppCompatActivity {
    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro",
            "Actualizar Registro"};
    String[] activities={"AsignaturaInsertarActivity","AsignaturaEliminarActivity","AsignaturaConsultarActivity",
            "AsignaturaActualizarActivity"};

    String entidad = "asignatura";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        ListView listView = findViewById(R.id.list_asignatura_menu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreValue = activities[position];
                try {
                    Class<?> clase = Class.forName("ues.grupo6.horariospdm."+entidad+"." + nombreValue);
                    Intent inte = new Intent(AsignaturaMenuActivity.this, clase);
                    AsignaturaMenuActivity.this.startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

