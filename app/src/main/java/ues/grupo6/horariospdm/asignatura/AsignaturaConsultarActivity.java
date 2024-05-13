package ues.grupo6.horariospdm.asignatura;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.escuela.Escuela;
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class AsignaturaConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId_Asignatura;
    EditText editId_Escuela;
    EditText editNombre_asignatura;
    EditText editCodigo_asignatura;
    EditText editEstado_asignatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_consultar);
        helper = new ControlBD(this);
        editId_Asignatura = (EditText) findViewById(R.id.editIdAsignatura);
        editId_Escuela = (EditText) findViewById(R.id.editIdEscuela);
        editCodigo_asignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        editEstado_asignatura = (EditText) findViewById(R.id.editEstadoAsignatura);
    }

    public void consultarAsignatura(View v) {
        if (editId_Asignatura.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de asignatura", Toast.LENGTH_SHORT).show();
            return;
        }

        helper.abrir();
        Asignatura asignatura = helper.consultarAsignatura(Integer.parseInt(String.valueOf(editId_Asignatura.getText())));
        if (asignatura == null)
            Toast.makeText(this, "Asignatura no registrada", Toast.LENGTH_LONG).show();
        else {
            Escuela escuela = helper.consultarEscuela(asignatura.getId_escuela());
            if (escuela != null) {
                editId_Escuela.setText(escuela.getId_escuela());
            } else {
                editId_Escuela.setText("Escuela no encontrado");
            }
            editNombre_asignatura.setText(String.valueOf(asignatura.getNombre_asignatura()));
            if (asignatura.getEstado_asignatura() == 1) {
                editEstado_asignatura.setText("Activo");
            } else {
                editEstado_asignatura.setText("Inactivo");
            }
            helper.cerrar();
        }
    }
}