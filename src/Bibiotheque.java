import java.util.ArrayList;
import java.util.ListIterator;
public class Bibiotheque {
    private String nomBibio;
    public static  ArrayList listeLivres = new ArrayList();
    public static ArrayList listeMembres = new ArrayList();

    public  Bibiotheque(String nomdelaBibio){
        this.nomBibio = nomdelaBibio;
        this.listeLivres = null;
    }

    //ajouter un livre

    public void ajouterLivre(Livres livre) {
        if (!listeLivres.contains(livre)) {
            listeLivres.add(livre);
            System.out.println("Livre ajouté avec succès.");
        } else {
            System.out.println("Le livre existe déjà dans la bibliothèque.");
        }
    }

    //supprimer un livre

    public boolean supprimerLivre(Livres livre) {
        return listeLivres.remove(livre); // Renvoie true si le livre a été supprimé, false sinon
    }

    //afficher la liste de tous les livres

    public void afficherTousLesLivres() {
        for (Object livre : listeLivres) {
            String rec = livre.toString();
            System.out.println(rec);
        }
    }

    // Rechercher un livre
  /*  public Livres rechercherLivre(String titre) {
        for (Livres livre : listeLivres) {
            if (livre.getTitre().equalsIgnoreCase(titre)) {
                return livre;
            }
        }
        return null; // Le livre n'a pas été trouvé
    }
 */

    //inscrire un membre à la bibiothèque

    public void inscrireMembre(Membres membre) {
        if (!listeMembres.contains(membre)) {
            listeMembres.add(membre);
            System.out.println("le membre a été ajouté avec succès.");
        } else {
            System.out.println("Le membre existe déjà dans la bibliothèque.");
        }
    }



    //supprimer un membre

    public boolean supprimerMembre(Membres membre) {
        return listeMembres.remove(membre); // Renvoie true si le membre a été supprimé, false sinon
    }

    //rechercher un membre par nom

   /* public Membres rechercherMembre(String id) {
        for (Object membre : listeMembres) {
            membre= (cast)Membres;
            if (membre.getId().equalsIgnoreCase(id)) {
                return membre;
            }
        }
        return null; // Le livre n'a pas été trouvé
    }
*/




}
