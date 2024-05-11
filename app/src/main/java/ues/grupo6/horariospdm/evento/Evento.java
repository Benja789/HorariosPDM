package ues.grupo6.horariospdm.evento;

public class Evento {
    private int id_evento;
    private int id_tipo_evento;
    private String nombre_evento;
    private int estado_evento;

    public Evento(){
    }

    public Evento(int id_evento, int id_tipo_evento, String nombre_evento, int estado_evento) {
        this.id_evento = id_evento;
        this.id_tipo_evento = id_tipo_evento;
        this.nombre_evento = nombre_evento;
        this.estado_evento = estado_evento;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public String getNombre_evento() {
        return nombre_evento;
    }

    public void setNombre_evento(String nombre_evento) {
        this.nombre_evento = nombre_evento;
    }

    public int getEstado_evento() {
        return estado_evento;
    }

    public void setEstado_evento(int estado_evento) {
        this.estado_evento = estado_evento;
    }
}
