package ues.grupo6.horariospdm.tipo_grupo;

public class Tipo_Grupo {
    private int id_tipo_grupo;
    private  String nombre_tipo_grupo;
    private int estado_tipo_grupo;

    public Tipo_Grupo() {
    }

    public Tipo_Grupo(int id_tipo_grupo, String nombre_tipo_grupo, int estado_tipo_grupo) {
        this.id_tipo_grupo = id_tipo_grupo;
        this.nombre_tipo_grupo = nombre_tipo_grupo;
        this.estado_tipo_grupo = estado_tipo_grupo;
    }

    public int getId_tipo_grupo() {
        return id_tipo_grupo;
    }

    public void setId_tipo_grupo(int id_tipo_grupo) {
        this.id_tipo_grupo = id_tipo_grupo;
    }

    public String getNombre_tipo_grupo() {
        return nombre_tipo_grupo;
    }

    public void setNombre_tipo_grupo(String nombre_tipo_grupo) {
        this.nombre_tipo_grupo = nombre_tipo_grupo;
    }

    public int getEstado_tipo_grupo() {
        return estado_tipo_grupo;
    }

    public void setEstado_tipo_grupo(int estado_tipo_grupo) {
        this.estado_tipo_grupo = estado_tipo_grupo;
    }
}
