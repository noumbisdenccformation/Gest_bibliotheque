import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;  // for converting Date to String
import java.util.List;

public class MembresDAO {
    private Connection connection;

    public MembresDAO() throws SQLException {
        this.connection = DBConnexion.getConnection();
    }

    public void enregistrerMembre(Membres membre) throws SQLException {
        String sql = "INSERT INTO Membres (nom, prenom, email, adhesionDate) VALUES ( ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, membre.getNom());
            statement.setString(2, membre.getPrenom());
            statement.setString(3, membre.getEmail());
            statement.setDate(4, membre.getAdhesionDate());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Échec de l'insertion du membre, aucune ligne affectée.");
            }

        }
    }

  /*  public Membres rechercherMembre(String id) throws SQLException {
        String sql = "SELECT * FROM Membres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Membres(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        (java.sql.Date) convertSqlDateToJavaDate(resultSet.getDate("adhesionDate"))
                );
            }
        }
        return null;
    }


    public void supprimerMembre(String id) throws SQLException {
        String sql = "DELETE FROM Membres WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        }
    }
*/
    public List<Membres> afficherListeDesMembres() throws SQLException {
        List<Membres> membres = new ArrayList<>();
        String sql = "SELECT * FROM Membres";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Membres membre = new Membres(
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getDate("adhesionDate")
                );
                membres.add(membre);
            }
        }
        return membres;
    }

    // Helper methods for date conversion between Java and SQL
    private static java.sql.Date convertJavaDateToSqlDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        } else {
            return null;
        }
    }

    private static Date convertSqlDateToJavaDate(java.sql.Date sqlDate) {
        if (sqlDate != null) {
            return new Date(sqlDate.getTime());
        } else {
            return null;
        }
    }




    public void mettreAJourMembre(Membres membre) throws SQLException {
        String sql = "UPDATE Membres SET nom = ?, prenom = ?, email = ?, adhesionDate = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, membre.getNom());
            statement.setString(2, membre.getPrenom());
            statement.setString(3, membre.getEmail());
            statement.setDate(4, convertJavaDateToSqlDate(membre.getAdhesionDate()));
            statement.executeUpdate();
        }

    }


}
