package ues.grupo6.horariospdm.solicitud_horario;

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
import ues.grupo6.horariospdm.ciclo_academico.CicloAcademico;
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.grupo.Grupo;
import ues.grupo6.horariospdm.salon.Salon;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class SolicitudHorarioActualizarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editIdSolicitudHorario;
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

        // Cargar tipos de eventos desde la base de datos
        helper.abrir();
        cargarGrupoActivos();
        cargarSalonActivos();
        cargarCicloAcademicoActivos();
    }
    private void cargarGrupoActivos() {
        ArrayList<Grupo> listaGrupos = new ArrayList<Grupo>();
        listaGrupos = helper.consultarGruposActivos();

        // Crear un ArrayList de Strings con los nombres de los tipos de eventos
        ArrayList<Integer> nombresGrupos = new ArrayList<>();
        for (Grupo grupo : listaGrupos) {
            nombresGrupos.add(grupo.getId_grupo());
        }
        // Crear ArrayAdapter con los nombres de los tipos de eventos
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresGrupos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrupo.setAdapter(adapter);

    }
    private void cargarSalonActivos() {
        ArrayList<Salon> listaSalon = new ArrayList<Salon>();
        listaSalon = helper.consultarSalonActivos();

        // Crear un ArrayList de Strings con los nombres de los tipos de eventos
        ArrayList<String> nombresSalon = new ArrayList<>();
        for (Salon salon : listaSalon) {
            nombresSalon.add(salon.getNombreSalon());
        }
        // Crear ArrayAdapter con los nombres de los tipos de eventos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresSalon);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalon.setAdapter(adapter);

    }
    private void cargarCicloAcademicoActivos() {
        ArrayList<CicloAcademico> listaCiclo = new ArrayList<CicloAcademico>();
        listaCiclo = helper.consultarCicloActivos();

        // Crear un ArrayList de Strings con los nombres de los tipos de eventos
        ArrayList<Integer> nombresCiclo = new ArrayList<>();
        for (CicloAcademico cicloAcademico : listaCiclo) {
            nombresCiclo.add(cicloAcademico.getId_ciclo_academico());
        }
        // Crear ArrayAdapter con los nombres de los tipos de eventos
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresCiclo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCicloAcademico.setAdapter(adapter);
    }

    private Grupo buscarGrupoPorNombre(int nombreGrupo) {
        ArrayList<Grupo> listaGrupo = helper.consultarGruposActivos();

        for (Grupo grupo : listaGrupo) {
            if (grupo.getId_grupo() == nombreGrupo) {
                return grupo;
            }
        }
        // Retornar null si el tipo de evento no se encontró
        return null;
    }
    private Salon buscarSalonPorNombre(String nombreSalon) {
        ArrayList<Salon> listaSalon = helper.consultarSalonActivos();

        for (Salon salon : listaSalon) {
            if (salon.getNombreSalon().equals(nombreSalon)) {
                return salon;
            }
        }
        // Retornar null si el tipo de evento no se encontró
        return null;
    }
    private CicloAcademico buscarCicloAcademicoPorNombre(int nombreCiclo) {
        ArrayList<CicloAcademico> listaCicloAcademico = helper.consultarCicloActivos();
        for (CicloAcademico cicloAcademico : listaCicloAcademico) {
            if (cicloAcademico.getId_ciclo_academico() == nombreCiclo) {
                return cicloAcademico;
            }
        }
        // Retornar null si el tipo de evento no se encontró
        return null;
    }

    public void actualizarSolicitudHorario(View v) {
        Solicitud_Horario solicitudHorario = new Solicitud_Horario();

        int nombreGrupo = Integer.parseInt(spinnerGrupo.getSelectedItem().toString());
        int nombreCiclo = Integer.parseInt(spinnerCicloAcademico.getSelectedItem().toString());
        String nombreSalon = spinnerSalon.getSelectedItem().toString();
        // Buscar el objeto TipoEvento correspondiente al nombre seleccionado

        Grupo grupoSeleccionado = buscarGrupoPorNombre(nombreGrupo);
        Salon salonSeleccionado = buscarSalonPorNombre(nombreSalon);
        CicloAcademico cicloSeleccionado = buscarCicloAcademicoPorNombre(nombreCiclo);

        if (grupoSeleccionado != null && salonSeleccionado != null && cicloSeleccionado !=null) {
            int id_grupo = grupoSeleccionado.getId_grupo();
            int id_salon = salonSeleccionado.getIdSalon();
            int id_ciclo = cicloSeleccionado.getId_ciclo_academico();

            solicitudHorario.setId_solicitud_horario(Integer.parseInt(String.valueOf(editIdSolicitudHorario.getText())));
            solicitudHorario.setId_grupo(Integer.parseInt(String.valueOf(id_grupo)));
            solicitudHorario.setId_salon(Integer.parseInt(String.valueOf(id_salon)));
            solicitudHorario.setId_ciclo_academico(Integer.parseInt(String.valueOf(id_ciclo)));
            solicitudHorario.setEstado_solicitud_horario(Integer.parseInt(editEstadoSolicitudHorario.getText().toString()));

        } else {
            Toast.makeText(this, "Error: Tipo de evento no encontrado", Toast.LENGTH_SHORT).show();
        }

        helper.abrir();
        String estado = helper.actualizarSolicitudHorario(solicitudHorario);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
}