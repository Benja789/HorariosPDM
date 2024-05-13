package ues.grupo6.horariospdm.salon;

public class Salon {
    private int idSalon;
    private int idTipoSalon;
    private int idDistribucionDeHora;
    private int idDocente;
    private String nombreSalon;
    private int capacidad;
    private String codigoSalon;
    private int disponible;

    public Salon(){}
    public Salon (int idSalon, int idTipoSalon, int idDistribucionDeHora, int idDocente, String nombreSalon, int capacidad, String codigoSalon, int disponible){
        this.idSalon=idSalon;
        this.idTipoSalon=idTipoSalon;
        this.idDistribucionDeHora=idDistribucionDeHora;
        this.idDocente=idDocente;
        this.nombreSalon=nombreSalon;
        this.capacidad=capacidad;
        this.codigoSalon=codigoSalon;
        this.disponible=disponible;
    }

    public void setIdSalon(int idSalon){this.idSalon=idSalon;}
    public void setIdTipoSalon(int idTipoSalon){this.idTipoSalon=idTipoSalon;}
    public void setIdDistribucionDeHora(int idDistribucionDeHora){this.idDistribucionDeHora=idDistribucionDeHora;}
    public void setIdDocente(int idDocente){this.idDocente=idDocente;};
    public void setNombreSalon(String nombreSalon){this.nombreSalon=nombreSalon;}
    public void setCapacidad(int capacidad){this.capacidad=capacidad;}
    public void setCodigoSalon(String codigoSalon){this.codigoSalon=codigoSalon;}
    public void setDisponible(int disponible){this.disponible=disponible;};

    public int getIdSalon(){ return idSalon;}
    public int getIdTipoSalon(){return idTipoSalon;}
    public int getIdDistribucionDeHora(){return idDistribucionDeHora;}
    public int getIdDocente(){return idDocente;}
    public String getNombreSalon(){return nombreSalon;}
    public int getCapacidad(){return capacidad;}
    public String getCodigoSalon(){return codigoSalon;}
    public int isDisponible(){return disponible;}
}