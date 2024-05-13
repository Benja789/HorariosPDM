package ues.grupo6.horariospdm.horario;

public class Horario {
    private int id_horario;
    private int id_solicitud_evento;
    private int id_solicitud_horario;

    public Horario() {
    }

    public Horario(int id_horario, int id_solicitud_evento, int id_solicitud_horario) {
        this.id_horario = id_horario;
        this.id_solicitud_evento = id_solicitud_evento;
        this.id_solicitud_horario = id_solicitud_horario;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_solicitud_evento() {
        return id_solicitud_evento;
    }

    public void setId_solicitud_evento(int id_solicitud_evento) {
        this.id_solicitud_evento = id_solicitud_evento;
    }

    public int getId_solicitud_horario() {
        return id_solicitud_horario;
    }

    public void setId_solicitud_horario(int id_solicitud_horario) {
        this.id_solicitud_horario = id_solicitud_horario;
    }
}
