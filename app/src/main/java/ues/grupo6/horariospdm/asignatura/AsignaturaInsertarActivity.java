package ues.grupo6.horariospdm.asignatura;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.escuela.Escuela;
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class AsignaturaInsertarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId_Asignatura;
    Spinner spinnerId_Escuela;
    EditText editNombre_asignatura;
    EditText editCodigo_asignatura;
    EditText editEstado_asignatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asignatura_insertar);
        helper = new ControlBD(this);
        spinnerId_Escuela = (Spinner) findViewById(R.id.spinnerEscuela);
        editNombre_asignatura = (EditText) findViewById(R.id.editNombreAsignatura);
        helper.abrir();
        cargarEscuelasActivas();
    }
    private void cargarEscuelasActivas() {
        ArrayList<Escuela> listaEscuelas = new ArrayList<Escuela>();
        listaEscuelas = helper.consultarEscuelasActivas();

        // Crear un ArrayList de Strings con los nombres de los tipos de eventos
        ArrayList<String> nombresEscuelas = new ArrayList<>();
        for (Escuela escuela : listaEscuelas) {
            nombresEscuelas.add(escuela.getNombre_escuela());
        }

        // Crear ArrayAdapter con los nombres de los tipos de eventos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresEscuelas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerId_Escuela.setAdapter(adapter);

    }
    public void insertarAsignatura(View v) {
        String nombre = editNombre_asignatura.getText().toString();
        String codigo = editCodigo_asignatura.getText().toString();
        // Obtener el nombre seleccionado en el Spinner
        String nombreEscuela = spinnerId_Escuela.getSelectedItem().toString();

        // Buscar el objeto TipoEvento correspondiente al nombre seleccionado
        Escuela escuelaSeleccionada = buscarEscuelaNombre(nombreEscuela);

        // Verificar si se encontró el tipo de evento
        if (escuelaSeleccionada != null) {
            int id_Escuela = escuelaSeleccionada.getId_escuela();
            Asignatura asignatura = new Asignatura();
            asignatura.setNombre_asignatura(nombre);
            asignatura.setId_escuela(id_Escuela);
            asignatura.setCodigo_asignatura(codigo);
            asignatura.setEstado_asignatura(1);

            helper.abrir();
            String regInsertados = helper.insertarAsignatura(asignatura);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Escuela no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private Escuela buscarEscuelaNombre(String nombreEscuela) {
        ArrayList<Escuela> listaEscuelas = helper.consultarEscuelasActivas();

        for (Escuela escuela : listaEscuelas) {
            if (escuela.getNombre_escuela().equals(nombreEscuela)) {
                return escuela;
            }
        }
        // Retornar null si el tipo de evento no se encontró
        return null;
    }
}