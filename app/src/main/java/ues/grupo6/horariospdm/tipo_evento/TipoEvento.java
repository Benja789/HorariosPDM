package ues.grupo6.horariospdm.tipo_evento;

public class TipoEvento {
    private int id_tipo_evento;
    private String nombre_tipo_evento;
    private int estado_tipo_evento;

    public TipoEvento(){
    }
    public TipoEvento(int id_tipo_evento, String nombre_tipo_evento, int estado_tipo_evento){
        this.id_tipo_evento = id_tipo_evento;
        this.nombre_tipo_evento = nombre_tipo_evento;
        this.estado_tipo_evento = estado_tipo_evento;
    }

    public int getId_tipo_evento() {
        return id_tipo_evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_tipo_evento = id_tipo_evento;
    }

    public String getNombre_tipo_evento() {
        return nombre_tipo_evento;
    }

    public void setNombre_tipo_evento(String nombre_tipo_evento) {
        this.nombre_tipo_evento = nombre_tipo_evento;
    }

    public int getEstado_tipo_evento() {
        return estado_tipo_evento;
    }

    public void setEstado_tipo_evento(int estado_tipo_evento) {
        this.estado_tipo_evento = estado_tipo_evento;
    }

}
