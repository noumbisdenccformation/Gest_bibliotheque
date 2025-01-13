import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class EmpruntsDAO {

    private Connection connection;
    public EmpruntsDAO() throws SQLException {
        this.connection = DBConnexion.getConnection();
    }

    // Méthode pour récupérer l'ID du membre en fonction du nom du membre
    public int getMembreIdByName(String nomMembre) throws SQLException {
        String sql = "SELECT idmembre FROM Membres WHERE nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomMembre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { return resultSet.getInt("idmembre"); }
                else { throw new SQLException("Membre non trouvé avec le nom: " + nomMembre); }
            } } }

    // Méthode pour récupérer l'ID du livre en fonction du titre du livre
    public int getLivreIdByTitle(String titreLivre) throws SQLException {
        String sql = "SELECT id FROM Livres WHERE titre = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, titreLivre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { return resultSet.getInt("id"); }
                else { throw new SQLException("Livre non trouvé avec le titre: " + titreLivre); }
            } } }

    // Méthode pour enregistrer un emprunt
   /* public  void enregistrerEmprunt(String nomMembre, String titreLivre, Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective) throws SQLException {
        int membreId = getMembreIdByName(nomMembre);
        int livreId = getLivreIdByTitle(titreLivre);

        String sql = "INSERT INTO Emprunts (membreId, livreId, dateEmprunt, dateRetourPrevu, dateRetourEffective) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, membreId);
            statement.setInt(2, livreId);
            statement.setDate(3, new java.sql.Date(dateEmprunt.getTime()));
            statement.setDate(4, new java.sql.Date(dateRetourPrevu.getTime()));
            statement.setDate(5, dateRetourEffective != null ? new java.sql.Date(dateRetourEffective.getTime()) : null);
            statement.executeUpdate();
        }
    }*/
    public void enregistrerEmprunt(String nomMembre, String titreLivre, Date dateEmprunt, Date dateRetourPrevu, Date dateRetourEffective) throws SQLException {
        int membreId;
        int livreId;

        try {
            membreId = getMembreIdByName(nomMembre);
        } catch (SQLException e) {
            System.out.println("Attention : Le membre ayant le nom '" + nomMembre + "' n'existe pas dans la base de la Bibliothèque!");
            return;
        }

        try {
            livreId = getLivreIdByTitle(titreLivre);
        } catch (SQLException e) {
            System.out.println("Attention : Le livre ayant le titre '" + titreLivre + "' n'existe pas dans la base de la Bibliothèque!");
            return;
        }

        String sql = "INSERT INTO Emprunts (membreId, livreId, dateEmprunt, dateRetourPrevu, dateRetourEffective) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, membreId);
            statement.setInt(2, livreId);

            try {
                statement.setDate(3, new java.sql.Date(dateEmprunt.getTime()));
            } catch (NullPointerException e) {
                System.out.println("Attention: La date d'emprunt ne peut pas être null!");
                return;
            }

            try {
                statement.setDate(4, new java.sql.Date(dateRetourPrevu.getTime()));
            } catch (NullPointerException e) {
                System.out.println("Attention: La date de retour prévue ne peut pas être null.");
                return;
            }

            if (dateRetourEffective != null) {
                statement.setDate(5, new java.sql.Date(dateRetourEffective.getTime()));
            } else {
                statement.setNull(5, java.sql.Types.DATE);
            }

            statement.executeUpdate();
            // Afficher un message de succès
            System.out.println("Emprunt enregistré avec succès.\n\n");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'enregistrement de l'emprunt! " + e.getMessage());
        }
    }


    // Méthode pour afficher la liste de tous les emprunts
    public  List<Emprunts> afficherListeDesEmprunts() throws SQLException {
        List<Emprunts> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM Emprunts";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Emprunts emprunt = new Emprunts( resultSet.getString("membreId"),
                                                 resultSet.getString("livreId"),
                                                 resultSet.getDate("dateEmprunt"),
                                                 resultSet.getDate("dateRetourPrevu"),
                                                 resultSet.getDate("dateRetourEffective") );
                emprunts.add(emprunt);
            } }
        return emprunts;
    }


    // Méthode pour afficher les emprunts en retard
    public List<Emprunts> afficherEmpruntsEnRetard() throws SQLException {
        List<Emprunts> empruntsEnRetard = new ArrayList<>();
        String sql = "SELECT * FROM Emprunts WHERE dateRetourPrevu < CURRENT_DATE AND dateRetourEffective IS NULL";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) { Emprunts emprunt = new Emprunts( resultSet.getString("membreId"),
                                                                                resultSet.getString("livreId"),
                                                                                resultSet.getDate("dateEmprunt"),
                                                                                resultSet.getDate("dateRetourPrevu"),
                                                                                resultSet.getDate("dateRetourEffective")
                                                                                );
                        empruntsEnRetard.add(emprunt);
                    } }
        return empruntsEnRetard;
    }

    // Méthode pour afficher les emprunts en retard et calculer les pénalités
    public List<String> afficherEmpruntsAvecPenalite() throws SQLException {
        List<String> empruntsAvecPenalite = new ArrayList<>();
        String sql = "SELECT * FROM Emprunts WHERE dateRetourPrevu < CURRENT_DATE AND dateRetourEffective IS NULL";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) { String membreId = resultSet.getString("membreid");
                                       String livreId = resultSet.getString("livreid");
                                       Date dateRetourPrevu = resultSet.getDate("dateRetourPrevu");
                                       long joursDeRetard = (System.currentTimeMillis() - dateRetourPrevu.getTime()) / (1000 * 60 * 60 * 24);
                                       long penalite = joursDeRetard * 100; // 100 F CFA par jour de retard
                                        // Ajouter les détails à la liste des emprunts avec pénalité
                                       empruntsAvecPenalite.add("Membre ID: " + membreId + ", Livre ID: " + livreId + ", Jours de retard: " + joursDeRetard + ", Pénalité: " + penalite + " F CFA"); }
        }
        return empruntsAvecPenalite;
    }
}
