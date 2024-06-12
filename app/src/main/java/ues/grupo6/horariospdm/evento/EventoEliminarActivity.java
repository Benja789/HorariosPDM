package ues.grupo6.horariospdm.evento;

import android.annotation.SuppressLint;
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
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;
import ues.grupo6.horariospdm.tipo_grupo.Tipo_Grupo;

public class EventoEliminarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdEvento;
    EditText editNombreEvento;
    EditText editEstadoEvento;

    EditText editTipoEvento;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_eliminar);
        helper = new ControlBD(this);
        editIdEvento = (EditText) findViewById(R.id.editIdEvento);
        editNombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editEstadoEvento = (EditText) findViewById(R.id.editEstadoEvento);
        editTipoEvento = (EditText) findViewById(R.id.editTipoEvento);

    }

    public void eliminarEvento(View v){
        Evento evento = new Evento();
        evento.setId_evento(Integer.parseInt(String.valueOf(editIdEvento.getText())));
        helper.abrir();
        helper.eliminarEvento(evento);
        helper.cerrar();
        Toast.makeText(this, "Registro eliminado correctamente",Toast.LENGTH_SHORT).show();
    }

    public void consultarEvento(View v){
        if (editIdEvento.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de evento", Toast.LENGTH_SHORT).show();
            return;
        }

        helper.abrir();
        Evento evento = helper.consultarEvento(Integer.parseInt(String.valueOf(editIdEvento.getText())));
        if(evento == null)
            Toast.makeText(this, "Evento no registrado", Toast.LENGTH_LONG).show();
        else{
            Toast.makeText(this, String.valueOf(evento.getId_tipo_evento()), Toast.LENGTH_LONG).show();
            TipoEvento tipoEvento = helper.consultarTipoEvento(evento.getId_tipo_evento());
            if (tipoEvento != null) {
                editTipoEvento.setText(tipoEvento.getNombre_tipo_evento());
            } else {
                editTipoEvento.setText("Tipo de evento no encontrado");
            }
            editNombreEvento.setText(String.valueOf(evento.getNombre_evento()));
            if(evento.getEstado_evento()==1){
                editEstadoEvento.setText("Activo");
            } else {
                editEstadoEvento.setText("Inactivo");
            }
            helper.cerrar();
        }
    }

    public void limpiarTexto(View v) {
        editIdEvento.setText("");
        editNombreEvento.setText("");
        editTipoEvento.setText("");
        editEstadoEvento.setText("");
    }
}