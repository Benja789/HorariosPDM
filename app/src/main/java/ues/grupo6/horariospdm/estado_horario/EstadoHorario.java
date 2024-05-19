package ues.grupo6.horariospdm.estado_horario;

public class EstadoHorario {
    private int id_estado_horario;
    private int id_horario;
    private String nombre_estado_horario;
    private int estado;

    public EstadoHorario() {
    }

    public EstadoHorario(int id_estado_horario, int id_horario, String nombre_estado_horario, int estado) {
        this.id_estado_horario = id_estado_horario;
        this.id_horario = id_horario;
        this.nombre_estado_horario = nombre_estado_horario;
        this.estado = estado;
    }

    public int getId_estado_horario() {
        return id_estado_horario;
    }

    public void setId_estado_horario(int id_estado_horario) {
        this.id_estado_horario = id_estado_horario;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getNombre_estado_horario() {
        return nombre_estado_horario;
    }

    public void setNombre_estado_horario(String nombre_estado_horario) {
        this.nombre_estado_horario = nombre_estado_horario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
