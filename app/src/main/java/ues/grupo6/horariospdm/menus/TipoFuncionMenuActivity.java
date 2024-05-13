package ues.grupo6.horariospdm.menus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ues.grupo6.horariospdm.R;

public class TipoFuncionMenuActivity extends AppCompatActivity {
    String[] menu = {"Insertar Registro", "Eliminar Registro", "Consultar Registro","Actualizar Registro"};
    String[] activities = {"TipoFuncionInsertarActivity", "TipoFuncionEliminarActivity", "TipoFuncionConsultarActivity", "TipoFuncionActualizarActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_funcion_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        ListView listView = findViewById(R.id.list_tipo_funcion_menu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreValue = activities[position];
                try {
                    Class<?> clase = Class.forName("ues.grupo6.horariospdm.tipo_funcion." + nombreValue);
                    Intent inte = new Intent(TipoFuncionMenuActivity.this, clase);
                    TipoFuncionMenuActivity.this.startActivity(inte);
                } catch (ClassNotFoundException e) {
                    Toast.makeText(TipoFuncionMenuActivity.this, R.string.error_intent_load, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}