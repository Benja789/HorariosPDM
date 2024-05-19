package ues.grupo6.horariospdm.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import ues.grupo6.horariospdm.R;

public class TipoEventoMenuActivity extends AppCompatActivity {

    String[] menu = {"Insertar Registro", "Eliminar Registro", "Consultar Registro","Actualizar Registro"};
    String[] activities = {"TipoEventoInsertarActivity", "TipoEventoEliminarActivity", "TipoEventoConsultarActivity", "TipoEventoActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        ListView listView = findViewById(R.id.list_tipo_evento_menu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreValue = activities[position];
                try {
                    Class<?> clase = Class.forName("ues.grupo6.horariospdm.tipo_evento." + nombreValue);
                    Intent inte = new Intent(TipoEventoMenuActivity.this, clase);
                    TipoEventoMenuActivity.this.startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}