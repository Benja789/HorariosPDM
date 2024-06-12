package ues.grupo6.horariospdm.evento;

import android.annotation.SuppressLint;
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
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;
import ues.grupo6.horariospdm.tipo_grupo.Tipo_Grupo;


public class EventoActualizarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editIdEvento;
    EditText editNombreEvento;
    Spinner spinnerTipoEvento;
    EditText editEstadoEvento;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_actualizar);
        helper = new ControlBD(this);
        editIdEvento = (EditText) findViewById(R.id.editIdEvento);
        editNombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editEstadoEvento = (EditText) findViewById(R.id.editEstadoEvento);
        spinnerTipoEvento = (Spinner) findViewById(R.id.spinnerTipoEvento);

        // Cargar tipos de eventos desde la base de datos
        helper.abrir();
        cargarTiposEventosActivos();
    }
    private void cargarTiposEventosActivos() {
        ArrayList<TipoEvento> listaTipoEventos = new ArrayList<TipoEvento>();
        listaTipoEventos = helper.consultarTiposEventosActivos();



        // Crear un ArrayList de Strings con los nombres de los tipos de eventos
        ArrayList<String> nombresTipoEventos = new ArrayList<>();
        for (TipoEvento tipoEvento : listaTipoEventos) {
            nombresTipoEventos.add(tipoEvento.getNombre_tipo_evento());
        }

        // Crear ArrayAdapter con los nombres de los tipos de eventos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresTipoEventos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEvento.setAdapter(adapter);

    } 
    public void actualizarEvento(View v) {
        Evento evento = new Evento();
        // Obtener el nombre del tipo de evento seleccionado en el Spinner
        String nombreTipoEvento = spinnerTipoEvento.getSelectedItem().toString();
        // Buscar el objeto TipoEvento correspondiente al nombre seleccionado
        TipoEvento tipoEventoSeleccionado = buscarTipoEventoPorNombre(nombreTipoEvento);
        if (tipoEventoSeleccionado != null) {
            int id_TipoEvento = tipoEventoSeleccionado.getId_tipo_evento();

            evento.setId_evento(Integer.parseInt(String.valueOf(editIdEvento.getText())));
            evento.setNombre_evento(editNombreEvento.getText().toString());
            evento.setEstado_evento(Integer.parseInt(String.valueOf(editEstadoEvento.getText())));
            evento.setId_tipo_evento(Integer.parseInt(String.valueOf(id_TipoEvento)));
        } else {
            Toast.makeText(this, "Error: Tipo de evento no encontrado", Toast.LENGTH_SHORT).show();
        }

        helper.abrir();
        String estado = helper.actualizarEvento(evento);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    private TipoEvento buscarTipoEventoPorNombre(String nombreTipoEvento) {
        ArrayList<TipoEvento> listaTipoEventos = helper.consultarTiposEventosActivos();

        for (TipoEvento tipoEvento : listaTipoEventos) {
            if (tipoEvento.getNombre_tipo_evento().equals(nombreTipoEvento)) {
                return tipoEvento;
            }
        }
        // Retornar null si el tipo de evento no se encontró
        return null;
    }
    public void limpiarTexto(View v) {

        editIdEvento.setText("");
        editNombreEvento.setText("");
        editEstadoEvento.setText("");
    }

}
