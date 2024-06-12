package ues.grupo6.horariospdm.evento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class EventoInsertarActivity extends Activity {

    ControlBD helper;
    EditText edNombreEvento;
    Spinner spTipoEventos;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    //@SuppressLint("MissingInflatedId")
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_insertar);
        helper = new ControlBD(this);
        edNombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        spTipoEventos = (Spinner) findViewById(R.id.spinnerTipoEvento);

        // Cargar tipos de eventos desde la base de datos
        helper.abrir();
        cargarTiposEventosActivos();
        getData();

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
        spTipoEventos.setAdapter(adapter);

    }

    public void insertarEvento(View v) {
        String nombre = edNombreEvento.getText().toString();

        // Obtener el nombre del tipo de evento seleccionado en el Spinner
        String nombreTipoEvento = spTipoEventos.getSelectedItem().toString();

        // Buscar el objeto TipoEvento correspondiente al nombre seleccionado
        TipoEvento tipoEventoSeleccionado = buscarTipoEventoPorNombre(nombreTipoEvento);

        // Verificar si se encontró el tipo de evento
        if (tipoEventoSeleccionado != null) {
            int id_TipoEvento = tipoEventoSeleccionado.getId_tipo_evento();
            Evento evento = new Evento();
            evento.setNombre_evento(nombre);
            evento.setId_tipo_evento(id_TipoEvento);
            evento.setEstado_evento(1);

            helper.abrir();
            String regInsertados = helper.insertarEvento(evento);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: Tipo de evento no encontrado", Toast.LENGTH_SHORT).show();
        }
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
        edNombreEvento.setText("");
    }


    public void getData() {
        CollectionReference myRef = db.collection("tipo_evento");
        myRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("nombre_tipo_evento");
                        list.add(name);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EventoInsertarActivity.this, android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner spinner = findViewById(R.id.spinnerTipoEvento);
                    spinner.setAdapter(adapter);
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}