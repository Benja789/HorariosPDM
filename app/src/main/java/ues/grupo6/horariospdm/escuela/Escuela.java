package ues.grupo6.horariospdm.escuela;

public class Escuela {
    private int id_escuela;
    private String nombre_escuela;
    private int prioridad_esccuela;
    private int estado_escuela;

    public Escuela() {
    }

    public Escuela(int id_escuela, String nombre_escuela, int prioridad_esccuela, int estado_escuela) {
        this.id_escuela = id_escuela;
        this.nombre_escuela = nombre_escuela;
        this.prioridad_esccuela = prioridad_esccuela;
        this.estado_escuela = estado_escuela;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }

    public String getNombre_escuela() {
        return nombre_escuela;
    }

    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    public int getPrioridad_esccuela() {
        return prioridad_esccuela;
    }

    public void setPrioridad_esccuela(int prioridad_esccuela) {
        this.prioridad_esccuela = prioridad_esccuela;
    }

    public int getEstado_escuela() {
        return estado_escuela;
    }

    public void setEstado_escuela(int estado_escuela) {
        this.estado_escuela = estado_escuela;
    }
}
