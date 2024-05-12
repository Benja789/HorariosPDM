package ues.grupo6.horariospdm.docente;

import android.text.Editable;

public class Docente {
    int idDocente;
    String firstName;
    String secondName;
    String firstLastName;
    String secondLastName;
    String profession;
    Boolean isActive;

    public Docente () {}
   public Docente (String firstName, String secondName, String firstLastName, String secondLastName, String profession, Boolean isActive) {
       this.secondLastName = secondLastName;
       this.firstName = firstName;
       this.secondName = secondName;
       this.firstLastName = firstLastName;
       this.profession = profession;
       this.isActive = isActive;
   }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    public void setActive(int active) { isActive = active == 1; }

    public Boolean getActive() {
        return isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public String getProfession() {
        return profession;
    }

    public String getSecondName() {
        return secondName;
    }
}
