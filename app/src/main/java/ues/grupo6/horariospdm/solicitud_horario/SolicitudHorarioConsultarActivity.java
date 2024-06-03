package ues.grupo6.horariospdm.solicitud_horario;

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
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.grupo.Grupo;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class SolicitudHorarioConsultarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editIdSolicitudHorario;

    EditText editNombreGrupo;
    EditText editNombreSalon;
    EditText editNombreCicloAcademico;
    Spinner spinnerGrupo;
    Spinner spinnerSalon;
    Spinner spinnerCicloAcademico;
    EditText editEstadoSolicitudHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_horario_actualizar);
        helper = new ControlBD(this);
        editIdSolicitudHorario = (EditText) findViewById(R.id.editIdSolicitudHorario);
        spinnerGrupo = (Spinner) findViewById(R.id.spinnerGrupo);
        spinnerSalon = (Spinner) findViewById(R.id.spinnerSalon);
        spinnerCicloAcademico = (Spinner) findViewById(R.id.spinnerCicloAcademico);
        editEstadoSolicitudHorario = (EditText) findViewById(R.id.editEstadoSolicitudHorario);
    }
    public void consultarSolicitudHorario(View v) {
        if (editIdSolicitudHorario.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID", Toast.LENGTH_SHORT).show();
            return;
        }
        helper.abrir();
        Solicitud_Horario solicitudHorario = helper.consultarSolicitudHorario(Integer.parseInt(String.valueOf(editIdSolicitudHorario.getText())));
        if(solicitudHorario == null)
            Toast.makeText(this, "Solicitud no registrada", Toast.LENGTH_LONG).show();
        else{
            Toast.makeText(this, String.valueOf(solicitudHorario.getId_solicitud_horario()), Toast.LENGTH_LONG).show();
            editIdSolicitudHorario.setText(solicitudHorario.getId_solicitud_horario());
            editNombreGrupo.setText(solicitudHorario.getId_grupo());
            editNombreSalon.setText(solicitudHorario.getId_salon());
            editNombreCicloAcademico.setText(solicitudHorario.getId_ciclo_academico());
            if(solicitudHorario.getEstado_solicitud_horario()==1){
                editEstadoSolicitudHorario.setText("Activo");
            } else {
                editEstadoSolicitudHorario.setText("Inactivo");
            }
            helper.cerrar();
        }
    }
}