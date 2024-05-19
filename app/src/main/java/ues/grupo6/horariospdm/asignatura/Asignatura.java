package ues.grupo6.horariospdm.asignatura;

public class Asignatura {
    private int id_asignatura;
    private int id_escuela;
    private String nombre_asignatura;
    private String codigo_asignatura;
    private int estado_asignatura;

    public Asignatura() {
    }

    public Asignatura(int id_asignatura, int id_escuela, String nombre_asignatura, String codigo_asignatura, int estado_asignatura) {
        this.id_asignatura = id_asignatura;
        this.id_escuela = id_escuela;
        this.nombre_asignatura = nombre_asignatura;
        this.codigo_asignatura = codigo_asignatura;
        this.estado_asignatura = estado_asignatura;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public String getCodigo_asignatura() {
        return codigo_asignatura;
    }

    public void setCodigo_asignatura(String codigo_asignatura) {
        this.codigo_asignatura = codigo_asignatura;
    }

    public int getEstado_asignatura() {
        return estado_asignatura;
    }

    public void setEstado_asignatura(int estado_asignatura) {
        this.estado_asignatura = estado_asignatura;
    }
}
