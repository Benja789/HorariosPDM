package ues.grupo6.horariospdm.menus;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import ues.grupo6.horariospdm.R;

/** @noinspection ALL*/
public class CicloAcademicoMenuActivity extends AppCompatActivity {
    String[] menu={"Insertar Registro","Eliminar Registro","Consultar Registro",
            "Actualizar Registro"};
    String[] activities={"CicloAcademicoInsertarActivity","CicloAcademicoEliminarActivity","CicloAcademicoConsultarActivity",
            "CicloAcademicoActualizarActivity"};

    String entidad = "ciclo_academico";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_academico_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        ListView listView = findViewById(R.id.list_ciclo_academico_menu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreValue = activities[position];
                try {
                    Class<?> clase = Class.forName("ues.grupo6.horariospdm."+entidad+"." + nombreValue);
                    Intent inte = new Intent(CicloAcademicoMenuActivity.this, clase);
                    CicloAcademicoMenuActivity.this.startActivity(inte);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

