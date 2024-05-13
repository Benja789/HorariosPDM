package ues.grupo6.horariospdm.tipo_salon;


public class TipoSalon {
    private int idTipoSalon;
    private String nombreTipoSalon;
    private int disponibleTipoSalon;
    private String codigoTipoSalon;
    private int estadoTipoSalon;

    public TipoSalon(){}
    public TipoSalon(int idTipoSalon, String nombreTipoSalon, int disponibleTipoSalon, String codigoTipoSalon, int estadoTipoSalon){
        this.idTipoSalon=idTipoSalon;
        this.nombreTipoSalon=nombreTipoSalon;
        this.disponibleTipoSalon=disponibleTipoSalon;
        this.codigoTipoSalon=codigoTipoSalon;
        this.estadoTipoSalon=estadoTipoSalon;
    }

    public void setIdTipoSalon(int idTipoSalon){this.idTipoSalon=idTipoSalon;}
    public void setNombreTipoSalon(String nombreTipoSalon){this.nombreTipoSalon=nombreTipoSalon;}
    public void setDisponibleTipoSalon(int disponibleTipoSalon){this.disponibleTipoSalon=disponibleTipoSalon;}
    public void setCodigoTipoSalon(String codigoTipoSalon){this.codigoTipoSalon=codigoTipoSalon;}
    public void setEstadoTipoSalon(int estadoTipoSalon){this.estadoTipoSalon=estadoTipoSalon;}

    public int getIdTipoSalon(){return idTipoSalon;}
    public String getNombreTipoSalon(){return nombreTipoSalon;}
    public int isDisponibleTipoSalon(){return disponibleTipoSalon;}
    public String getCodigoTipoSalon(){return codigoTipoSalon;}
    public int getEstadoTipoSalon(){return estadoTipoSalon;}
}
