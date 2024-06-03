package ues.grupo6.horariospdm.solicitud_horario;

public class Solicitud_Horario {
    private int id_solicitud_horario;
    private int id_grupo;
    private int id_salon;
    private int id_ciclo_academico;
    private int estado_solicitud_horario;

    public Solicitud_Horario() {
    }

    public Solicitud_Horario(int id_solicitud_horario, int id_grupo, int id_salon, int id_ciclo_academico, int estado_solicitud_horario) {
        this.id_solicitud_horario = id_solicitud_horario;
        this.id_grupo = id_grupo;
        this.id_salon = id_salon;
        this.id_ciclo_academico = id_ciclo_academico;
        this.estado_solicitud_horario = estado_solicitud_horario;
    }

    public int getId_solicitud_horario() {
        return id_solicitud_horario;
    }

    public void setId_solicitud_horario(int id_solicitud_horario) {
        this.id_solicitud_horario = id_solicitud_horario;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_salon() {
        return id_salon;
    }

    public void setId_salon(int id_salon) {
        this.id_salon = id_salon;
    }

    public int getId_ciclo_academico() {
        return id_ciclo_academico;
    }

    public void setId_ciclo_academico(int id_ciclo_academico) {
        this.id_ciclo_academico = id_ciclo_academico;
    }

    public int getEstado_solicitud_horario() {
        return estado_solicitud_horario;
    }

    public void setEstado_solicitud_horario(int estado_solicitud_horario) {
        this.estado_solicitud_horario = estado_solicitud_horario;
    }
}
