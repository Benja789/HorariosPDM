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
        editTipoEvento = (EditText) findViewById(R.id.editEstadoEvento);

    }

    public void eliminarEvento(View v){
        Evento evento = new Evento();
        evento.setId_evento(Integer.parseInt(String.valueOf(editIdEvento.getText())));
        helper.abrir();
        helper.eliminarEvento(evento);
        helper.cerrar();
        Toast.makeText(this, "Registro eliminado correctamente",Toast.LENGTH_SHORT).show();
    }
}