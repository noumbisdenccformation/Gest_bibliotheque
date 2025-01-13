import java.util.Date;

public class Membres {
    private String nom;
    private String prenom;
    private String email;
    private java.sql.Date adhesionDate;

    //les getters

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public java.sql.Date getAdhesionDate() {
        return adhesionDate;
    }


    //les setters


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdhesionDate(Date adhesionDate) {
        this.adhesionDate = (java.sql.Date) adhesionDate;
    }

    //constructeur

    public Membres( String nom, String prenom, String email, java.sql.Date adhesionDate) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }

    @Override
    public String toString() {
        return  "\nNom : " + nom +
                "\nPrenom : " + prenom +
                "\nEmail : " + email +
                "\nDate d'Adhésion : " + adhesionDate;
    }
}
