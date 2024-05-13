package ues.grupo6.horariospdm.distribucion_hora;

public class DistribucionHora {
    private int idDistribucionHora;
    private String horaInicio;
    private String horaFin;
    private String dia;
    private int estadoDistribucionHora;

    public DistribucionHora(){}
    public DistribucionHora(int idDistribucionHora, String horaInicio, String horaFin, String dia, int estadoDistribucionHora){
        this.idDistribucionHora = idDistribucionHora;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dia = dia;
        this.estadoDistribucionHora = estadoDistribucionHora;
    }

    public void setIdDistribucionHora(int idDistribucionHora){this.idDistribucionHora=idDistribucionHora;}
    public void setHoraInicio(String horaInicio){this.horaInicio=horaInicio;}
    public void setHoraFin(String horaFin){this.horaFin=horaFin;}
    public void setDia(String dia){this.dia=dia;}
    public void getEstadoDistribucionHora(int estadoDistribucionHora){this.estadoDistribucionHora=estadoDistribucionHora;}

    public int getIdDistribucionHora(){return idDistribucionHora;}
    public String getHoraInicio(){return horaInicio;}
    public String getHoraFin(){return horaFin;}
    public String getDia(){return dia;}
    public int getEstadoDistribucionHora(){return estadoDistribucionHora;}
}
