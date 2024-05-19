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

public class EscuelaActualizarActivity extends AppCompatActivity {


    ControlBD helper;
    EditText editNombre_escuela;
    EditText editPrioridad_escuela;
    EditText editEstado_escuela;
    EditText editId_escuela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_actualizar);
        helper = new ControlBD(this);
        editId_escuela = (EditText) findViewById(R.id.editIdEscuela);
        editNombre_escuela = (EditText) findViewById(R.id.editNombre_escuela);
        editPrioridad_escuela = (EditText) findViewById(R.id.editPrioridad_escuela);
        editEstado_escuela = (EditText) findViewById(R.id.editEstadoEscuela);
    }
    public void actualizarEscuela(View v) {
        Escuela escuela = new Escuela();
        escuela.setId_escuela(Integer.parseInt(String.valueOf(editId_escuela.getText())));
        escuela.setNombre_escuela(editNombre_escuela.toString());
        escuela.setPrioridad_esccuela(Integer.parseInt(String.valueOf(editPrioridad_escuela.getText())));
        escuela.setEstado_escuela(Integer.parseInt(String.valueOf(editEstado_escuela.getText())));
        helper.abrir();
        String estado = helper.actualizarEscuela(escuela);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editId_escuela.setText("");
        editNombre_escuela.setText("");
        editPrioridad_escuela.setText("");
        editEstado_escuela.setText("");
    }
}