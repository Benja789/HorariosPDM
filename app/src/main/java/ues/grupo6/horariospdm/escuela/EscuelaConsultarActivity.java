package ues.grupo6.horariospdm.escuela;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.tipo_grupo.Tipo_Grupo;

public class EscuelaConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editNombre_escuela;
    EditText editPrioridad_escuela;
    EditText editEstado_escuela;
    EditText editId_escuela;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_consultar);
        helper = new ControlBD(this);
        editId_escuela = (EditText) findViewById(R.id.editIdEscuela);
        editNombre_escuela = (EditText) findViewById(R.id.editNombre_escuela);
        editPrioridad_escuela = (EditText) findViewById(R.id.editPrioridad_escuela);
        editEstado_escuela = (EditText) findViewById(R.id.editEstadoEscuela);
    }
    public void consultarEscuela(View v) {
        helper.abrir();
        Escuela escuela = helper.consultarEscuela(Integer.parseInt(String.valueOf(editId_escuela.getText())));
        helper.cerrar();
        if(escuela == null)
            Toast.makeText(this, "Escuela no registrada",
                    Toast.LENGTH_LONG).show();
        else{
            editId_escuela.setText(String.valueOf(escuela.getId_escuela()));
            editNombre_escuela.setText(String.valueOf(escuela.getNombre_escuela()));
            editPrioridad_escuela.setText(Integer.parseInt(String.valueOf(escuela.getPrioridad_esccuela())));
            if(escuela.getEstado_escuela()==1){
                editEstado_escuela.setText("Activo");
            }
            else{
                editEstado_escuela.setText("Inactivo");
            }
        }
    }
    public void limpiarTexto(View v) {
        editId_escuela.setText("");
        editNombre_escuela.setText("");
        editPrioridad_escuela.setText("");
        editEstado_escuela.setText("");
    }
}