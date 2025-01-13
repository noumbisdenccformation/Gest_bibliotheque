import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {


        char v;
        Scanner sc = new Scanner(System.in);
        String nomB = "nomdeB";
        Bibiotheque B = new Bibiotheque(nomB);
        do {
            System.out.println("Faire le choix de l'opération à effectuer?");

            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Enregistrer un emprunt");
            System.out.println("5. Afficher les emprunts en retard");
            System.out.println("6. Quitter");
            v = sc.nextLine().charAt(0);
            LivreDAO l1 = new LivreDAO();//objet de lecture de livre dans la base de données pour gestion dans mon programme

            //branchement sur l'opération effective choisie
            if (v == '1') {
                System.out.println("Ici, nous appelons la fonction ajouter-livre\n");


                System.out.println("Entrez le tritre du livre");
                String titre = sc.nextLine();
                System.out.println("Entrez le nom de l'auteur du livre");
                String auteur = sc.nextLine();
                System.out.println("Entrez la catégorie dans laquelle est classé le livre");
                String categorie = sc.nextLine();
                System.out.println("Entrez le nombre d'exemplaire de livre");
                int nombreExemplaire = sc.nextInt();
                sc.nextLine();
                Livres l = new Livres(titre, auteur, categorie, nombreExemplaire);
                l1.ajouterLivre(l);

                // Récupérer tous les livres
                List<Livres> tousLesLivres = l1.getAllLivres();
                System.out.println( "\n\n VOICI LA LISTE DE TOUS LES LIVRES DE LA BIBLIOTHEQUE: \n\n2");
                for (Livres livre1 : tousLesLivres) {
                    System.out.println(livre1 + "\n");
                }
                ;

            } else if (v == '2') {
                System.out.println("Ici, nous appelons la fonction rechercher un livre \n");

                System.out.println("Veuillez entrer un mot contenu dans le titre du livre ou l'auteur");
                String param = sc.nextLine();

                // Rechercher des livres contenant le mot "Java" dans le titre ou l'auteur
                List<Livres> resultats = l1.rechercherLivresParTitreOuAuteur(param);
                for (Livres l2 : resultats) {
                    System.out.println(l2 + "\n");
                }
                ;


            } else if (v == '3') {
                System.out.println("Ici, nous appelons la fonction inscrire un membre");

                try {
                    MembresDAO membresDAO = new MembresDAO();

                    // Ajouter un nouveau membre
                    System.out.println("Entrez le nom du membre");
                    String nom_membre = sc.nextLine();
                    System.out.println("Entrez le prenom du membre");
                    String prenom_membre = sc.nextLine();
                    System.out.println("Entrez le mail du membre");
                    String mail_membre = sc.nextLine();
                    System.out.println("Entrez la date d'inscription du membre (dans ce format): AAAAMMJJ");
                    String dateinscription = sc.nextLine();

                    // Définir le format de la date
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    try { // Convertir la chaîne en objet Date
                        Date dateInscription = dateFormat.parse(dateinscription);
                        // Convertir l'objet java.util.Date en java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(dateInscription.getTime());
                        // Créer un nouvel objet Membres avec les informations fournies
                        Membres nouveauMembre = new Membres(nom_membre, prenom_membre, mail_membre, sqlDate);
                        // Enregistrer le membre dans la base de données
                        membresDAO.enregistrerMembre(nouveauMembre);
                        // Afficher un message de succès
                        System.out.println("Membre inscrit avec succès! \n");
                    } catch (ParseException e) {
                        System.out.println("Format de date invalide. Veuillez entrer une date au format AAAAMMJJ.");
                    }

                    // Afficher la liste de tous les membres
                    List<Membres> tousLesMembres = membresDAO.afficherListeDesMembres();
                    System.out.println("Liste des membres :");
                    for (Membres membre : tousLesMembres) {
                        System.out.println(membre);
                    }
                } catch (SQLException e) {
                    System.err.println("Une erreur s'est produite : " + e.getMessage());
                }


            } else if (v == '4') {
                System.out.println("Ici, nous appelons la fonction Enregistrer un emprunt");
                EmpruntsDAO empruntsDAO = new EmpruntsDAO();
                // Ajouter un nouvel emprunt
                System.out.println("Entrez le nom du membre regulièrement inscrit qui veut emprunter?");
                String nomMembre = sc.nextLine();
                System.out.println("Entrez le titre du livre à emprunter?");
                String titreLivre = sc.nextLine();
                System.out.println("Entrez la date d'emprunt (dans ce format): AAAAMMJJ");
                String dateEmpruntStr = sc.nextLine();
                System.out.println("Entrez la date de retour prévu (dans ce format): AAAAMMJJ");
                String dateRetourPrevuStr = sc.nextLine();

                // Définir le format de la date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                try { // Convertir les chaînes en objets Date
                    Date dateEmprunt = dateFormat.parse(dateEmpruntStr);
                    Date dateRetourPrevu = dateFormat.parse(dateRetourPrevuStr);
                    try {
                        // Enregistrer l'emprunt
                        empruntsDAO.enregistrerEmprunt(nomMembre, titreLivre, dateEmprunt, dateRetourPrevu, null);


                    }catch (SQLException sqlException){  System.out.println("Attention: Veuillez entrer le nom de l'emprunteur valide ou le titre du livre existant!"); }
                } catch (ParseException e) {
                    System.out.println("Format de date invalide. Veuillez entrer une date au format AAAAMMJJ.");
                }

                // Afficher la liste de tous les emprunts
                List<Emprunts> tousLesEmprunts = empruntsDAO.afficherListeDesEmprunts();
                System.out.println("Liste des emprunts :");
                for (Emprunts emprunt : tousLesEmprunts) {
                    System.out.println(emprunt);
                }

            } else if (v == '5') {
                EmpruntsDAO empruntsDAO1 = new EmpruntsDAO();
                System.out.println("Ici, nous appelons la fonction afficher les emprunts en retard");
                // Afficher la liste des emprunts avec pénalité
                List<String> empruntsAvecPenalite = empruntsDAO1.afficherEmpruntsAvecPenalite();
                System.out.println("Liste des emprunts avec pénalité :");
                for (String empruntAvecPenalite : empruntsAvecPenalite)
                    {
                        System.out.println(empruntAvecPenalite);
                    }


            } else {
                System.out.println("Merci d'avoir utilisé le programme. Au revoir!");
                System.exit(0);
            }


            System.out.println("\n\n Voulez-vous reprendre? (O/N)");
            v = sc.nextLine().charAt(0);
        } while (v == 'O' || v == 'o');


    }
}
