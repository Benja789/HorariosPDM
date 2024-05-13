package ues.grupo6.horariospdm.evento;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;


public class EventoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editIdEvento;
    EditText editNombreEvento;
    EditText editIdTipoEvento;
    EditText editEstadoEvento;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_consultar);
        helper = new ControlBD(this);
        editIdEvento = (EditText) findViewById(R.id.editIdEvento);
        editNombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        editIdTipoEvento = (EditText) findViewById(R.id.editIdTipoEvento);
        editEstadoEvento = (EditText) findViewById(R.id.editEstadoEvento);
    }
    public void consultarEvento(View v) {
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
                editIdTipoEvento.setText(tipoEvento.getNombre_tipo_evento());
            } else {
                editIdTipoEvento.setText("Tipo de evento no encontrado");
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
        editIdTipoEvento.setText("");
        editEstadoEvento.setText("");
    }
}