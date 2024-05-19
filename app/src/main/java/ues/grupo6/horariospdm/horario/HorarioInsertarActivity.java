package ues.grupo6.horariospdm.horario;

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
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class HorarioInsertarActivity extends AppCompatActivity {
    ControlBD helper;
    Spinner spSolicitudEventos;
    Spinner spSolicitudHorarios;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        helper = new ControlBD(this);
        spSolicitudEventos = (Spinner) findViewById(R.id.spinnerSolicitudEvento);
        spSolicitudHorarios= (Spinner) findViewById(R.id.spinnerSolicitudHorario);

        // Cargar solicitud horario y solicitud evento desde la base de datos
        helper.abrir();


    }


}