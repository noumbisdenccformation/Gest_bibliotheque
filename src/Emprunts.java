import java.util.Date;

public class Emprunts {

    private String membreId;
    private String livreId;
    private Date dateEmprunt;
    private Date dateRetourPrevu;
    private Date dateRetourEffective;

    //présentation des getters
    public String getMembreId() {
        return membreId;
    }

    public String getLivreId() {
        return livreId;
    }

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public Date getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public Date getDateRetourEffective() {
        return dateRetourEffective;
    }

    // présentatinn des setters


    public void setMembreId(String membreId) {
        this.membreId = membreId;
    }

    public void setLivreId(String livreId) {
        this.livreId = livreId;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public void setDateRetourPrevu(Date dateRetourPrevu) {
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public void setDateRetourEffective(Date dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    //constructeur d'emprunt

    public Emprunts( String membreId, String livreId, Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective) {
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
        this.dateRetourEffective = dateRetourEffective;
    }

    // Définir la méthode toString() pour un affichage clair
    @Override public String toString() {
        return "Emprunt{" + "Membre ID='" + membreId + '\'' +
                        ", Livre ID='" + livreId + '\'' +
                        ", Date d'emprunt=" + dateEmprunt +
                        ", Date de retour prévu=" + dateRetourPrevu +
                        (dateRetourEffective != null ? ", Date de retour effective=" + dateRetourEffective : ", Non retourné") +
                       '}';
       }
}
