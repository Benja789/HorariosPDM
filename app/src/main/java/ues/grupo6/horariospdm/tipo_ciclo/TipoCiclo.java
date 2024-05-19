package ues.grupo6.horariospdm.tipo_ciclo;

public class TipoCiclo {
    private int id_tipo_ciclo;
    private String nombre_tipo_ciclo;
    private int estado_tipo_ciclo;

    public TipoCiclo() {
    }

    public TipoCiclo(int id_tipo_ciclo, String nombre_tipo_ciclo, int estado_tipo_ciclo) {
        this.id_tipo_ciclo = id_tipo_ciclo;
        this.nombre_tipo_ciclo = nombre_tipo_ciclo;
        this.estado_tipo_ciclo = estado_tipo_ciclo;
    }

    public int getId_tipo_ciclo() {
        return id_tipo_ciclo;
    }

    public void setId_tipo_ciclo(int id_tipo_ciclo) {
        this.id_tipo_ciclo = id_tipo_ciclo;
    }

    public String getNombre_tipo_ciclo() {
        return nombre_tipo_ciclo;
    }

    public void setNombre_tipo_ciclo(String nombre_tipo_ciclo) {
        this.nombre_tipo_ciclo = nombre_tipo_ciclo;
    }

    public int getEstado_tipo_ciclo() {
        return estado_tipo_ciclo;
    }

    public void setEstado_tipo_ciclo(int estado_tipo_ciclo) {
        this.estado_tipo_ciclo = estado_tipo_ciclo;
    }
}
