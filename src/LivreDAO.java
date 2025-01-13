
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LivreDAO {
    private Connection connection;

    public LivreDAO() throws SQLException {
        this.connection = DBConnexion.getConnection();
    }

    public void ajouterLivre(Livres livre) throws SQLException {
        String sql = "INSERT INTO Livres (titre, auteur, categorie, nombreExemplaire) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setString(3, livre.getCategorie());
            statement.setInt(4, livre.getNombreExemplaire());
            statement.executeUpdate();
        }
    }

    public Livres getLivreById(int id) throws SQLException {
        String sql = "SELECT * FROM Livres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Livres(
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("categorie"),
                        resultSet.getInt("nombreExemplaire")
                );
            }
        }
        return null;
    }

    public List<Livres> getAllLivres() throws SQLException {
        List<Livres> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livres";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Livres livre = new Livres(
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("categorie"),
                        resultSet.getInt("nombreExemplaire")
                       );
                livres.add(livre);
            }
        }
        return livres;
    }

    //methode rechercher livres
   /* public List<Livres> rechercherLivresParTitreOuAuteur(String recherche) throws SQLException {
        List<Livres> livres = new ArrayList<>();
        String sql = "SELECT * FROM Livres WHERE titre ILIKE ? OR auteur ILIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + recherche + "%");
            statement.setString(2, "%" + recherche + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Livres livre = new Livres(
                        resultSet.getString("titre"),
                        resultSet.getString("auteur"),
                        resultSet.getString("categorie"),
                        resultSet.getInt("nombreExemplaire")
                );
                livres.add(livre);
            }
        }
        return livres;
    }*/

    public List<Livres> rechercherLivresParTitreOuAuteur(String recherche) throws SQLException {
        List<Livres> livres = new ArrayList<>();

        // Vérifiez si la recherche est vide
        if (recherche == null || recherche.trim().isEmpty()) {
            System.out.println("La recherche ne peut pas être vide. Veuillez entrer un titre ou un auteur.");
            return livres;
        }

        String sql = "SELECT * FROM Livres WHERE titre ILIKE ? OR auteur ILIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + recherche + "%");
            statement.setString(2, "%" + recherche + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Livres livre = new Livres(
                            resultSet.getString("titre"),
                            resultSet.getString("auteur"),
                            resultSet.getString("categorie"),
                            resultSet.getInt("nombreExemplaire")
                    );
                    livres.add(livre);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'exécution de la requête : vérifiez si vos mots correspondent à ceux du système! " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la préparation de la requête : " + e.getMessage());
        }

        return livres;
    }



    //supprimer livre

    public void supprimerLivre(int id) throws SQLException {
        String sql = "DELETE FROM Livres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    //mettre à jour livre

    public void mettreAJourLivre(Livres livre) throws SQLException {
        String sql = "UPDATE Livres SET titre = ?, auteur = ?, categorie = ?, nombreExemplaire = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, livre.getTitre());
            statement.setString(2, livre.getAuteur());
            statement.setString(3, livre.getCategorie());
            statement.setInt(4, livre.getNombreExemplaire());
          /*  statement.setInt(5, livre.getId());*/ // Supposons qu'il y ait un attribut id dans la classe Livres
            statement.executeUpdate();
        }
    }
}
