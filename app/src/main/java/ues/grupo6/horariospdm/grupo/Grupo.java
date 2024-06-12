package ues.grupo6.horariospdm.grupo;

public class Grupo {
    private int id_grupo;
    private int id_tipo_grupo;
    private int id_asignatura;
    private int id_docente;

    public int getNum_grupo() {
        return num_grupo;
    }

    public void setNum_grupo(int num_grupo) {
        this.num_grupo = num_grupo;
    }

    private int num_grupo;
    private int estado_grupo;

    public Grupo() {
    }

    public Grupo(int id_grupo, int id_tipo_grupo, int id_asignatura, int id_docente, int num_grupo, int estado_grupo) {
        this.id_grupo = id_grupo;
        this.id_tipo_grupo = id_tipo_grupo;
        this.id_asignatura = id_asignatura;
        this.id_docente = id_docente;
        this.estado_grupo = estado_grupo;
        this.num_grupo = num_grupo;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_tipo_grupo() {
        return id_tipo_grupo;
    }

    public void setId_tipo_grupo(int id_tipo_grupo) {
        this.id_tipo_grupo = id_tipo_grupo;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getEstado_grupo() {
        return estado_grupo;
    }

    public void setEstado_grupo(int estado_grupo) {
        this.estado_grupo = estado_grupo;
    }
}
