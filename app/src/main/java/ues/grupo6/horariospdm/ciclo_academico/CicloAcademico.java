package ues.grupo6.horariospdm.ciclo_academico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CicloAcademico {

    //Object attributes declaration
    private int id_ciclo_academico;
    private int id_tipo_ciclo;
    private String inicio_ciclo_academico;
    private String fin_ciclo_academico;
    private String anio_ciclo_academico;
    private int estado_ciclo_academico;

    public CicloAcademico() {
    }

    public CicloAcademico(int id_ciclo_academico, int id_tipo_ciclo, String inicio_ciclo_academico, String fin_ciclo_academico, String anio_ciclo_academico, int estado_ciclo_academico) {
        this.id_ciclo_academico = id_ciclo_academico;
        this.id_tipo_ciclo = id_tipo_ciclo;
        this.inicio_ciclo_academico = inicio_ciclo_academico;
        this.fin_ciclo_academico = fin_ciclo_academico;
        this.anio_ciclo_academico = anio_ciclo_academico;
        this.estado_ciclo_academico = estado_ciclo_academico;
    }

    public int getId_ciclo_academico() {
        return id_ciclo_academico;
    }

    public void setId_ciclo_academico(int id_ciclo_academico) {
        this.id_ciclo_academico = id_ciclo_academico;
    }

    public int getId_tipo_ciclo() {
        return id_tipo_ciclo;
    }

    public void setId_tipo_ciclo(int id_tipo_ciclo) {
        this.id_tipo_ciclo = id_tipo_ciclo;
    }

    public String getInicio_ciclo_academico() {
        return inicio_ciclo_academico;
    }

    public void setInicio_ciclo_academico(String inicio_ciclo_academico) {
        this.inicio_ciclo_academico = inicio_ciclo_academico;
    }

    public String getFin_ciclo_academico() {
        return fin_ciclo_academico;
    }

    public void setFin_ciclo_academico(String fin_ciclo_academico) {
        this.fin_ciclo_academico = fin_ciclo_academico;
    }

    public String getAnio_ciclo_academico() {
        return anio_ciclo_academico;
    }

    public void setAnio_ciclo_academico(String anio_ciclo_academico) {
        this.anio_ciclo_academico = anio_ciclo_academico;
    }

    public int getEstado_ciclo_academico() {
        return estado_ciclo_academico;
    }

    public void setEstado_ciclo_academico(int estado_ciclo_academico) {
        this.estado_ciclo_academico = estado_ciclo_academico;
    }
}
