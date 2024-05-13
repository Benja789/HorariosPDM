package ues.grupo6.horariospdm.tipo_funcion;

public class TipoFuncion {
    int id;
    String name;
    Boolean state;

    public TipoFuncion(String name, Boolean state) {
        this.name = name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
