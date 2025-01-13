import java.util.ArrayList;
public class Livres {

    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaire;

    //présentation des getters

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getNombreExemplaire() {
        return nombreExemplaire;
    }

    // présentatinn des setters

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNombreExemplaire(int nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }


    //contrstuvteurs
    public Livres( String titre, String auteur, String categorie, int nombreExemplaire) {
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaire = nombreExemplaire;
    }

    @Override
    public String toString() {
        return "Titre : " + titre +
                "\nAuteur : " + auteur +
                "\nCatégorie : " + categorie +
                "\nNombre d'exemplaires : " + nombreExemplaire;
    }




}
