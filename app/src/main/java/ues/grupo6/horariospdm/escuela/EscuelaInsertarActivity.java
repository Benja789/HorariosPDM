package ues.grupo6.horariospdm.escuela;

import android.annotation.SuppressLint;
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

public class EscuelaInsertarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editNombre_escuela;
    EditText editPrioridad_escuela;
    EditText editEstado_escuela;
    EditText editId_escuela;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        helper = new ControlBD(this);
        editNombre_escuela = (EditText) findViewById(R.id.editNombre_escuela);
    }

    public void insertarTipoEvento(View v) {
        String nombre = editNombre_escuela.getText().toString();
        Integer prioridad = Integer.parseInt(String.valueOf(editPrioridad_escuela.getText()));
        String regInsertados;

        Escuela escuela = new Escuela();
        escuela.setNombre_escuela(nombre);
        escuela.setPrioridad_esccuela(prioridad);
        escuela.setEstado_escuela(1);

        helper.abrir();
        regInsertados = helper.insertar(escuela);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {

        editNombre_escuela.setText("");
        editPrioridad_escuela.setText("");
    }
}
