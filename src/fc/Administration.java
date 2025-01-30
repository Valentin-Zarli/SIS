/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 *
 * @author valen
 */
public class Administration extends Utilisateur {

    private VBox root;

    public Administration(String id, String Mot_de_passe) {
        super(id, Mot_de_passe, 2);
        this.root = new VBox();
        Label welcomeLabel = new Label("Bienvenue, " + this.getNom());
        root.getChildren().add(welcomeLabel);
    }

    public boolean verifierDMRExiste(String numeroSecu) {
        if (numeroSecu == null || numeroSecu.isBlank()) {
            System.out.println("Le numéro de sécurité sociale est obligatoire.");
            return false;
        }

        try {
            String requeteVerif = "SELECT 1 FROM DMR WHERE N_SECU = ?";
            try (Connection connection = ConnexionDataBase.getConnection(); PreparedStatement stmtVerif = connection.prepareStatement(requeteVerif)) {

                stmtVerif.setString(1, numeroSecu); // Vérifie le numéro de sécurité sociale
                try (ResultSet rs = stmtVerif.executeQuery()) {
                    return rs.next(); // Retourne vrai si une ligne existe
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification du DMR : " + e.getMessage());
            return false;
        }
    }

    public boolean creerDMR(String nom, String prenom, String dateNaissance, String genre, String numeroSecu) {
        // Validation des champs obligatoires
        if (nom == null || nom.isBlank()
                || prenom == null || prenom.isBlank()
                || dateNaissance == null || dateNaissance.isBlank()
                || genre == null || genre.isBlank()
                || numeroSecu == null || numeroSecu.isBlank()) {
            System.out.println("Tous les champs obligatoires doivent être remplis.");
            return false;
        }

        // Validation du numéro de sécurité sociale
        if (numeroSecu.length() != 15 || !numeroSecu.matches("\\d{15}")) {
            System.out.println("Le numéro de sécurité sociale doit contenir exactement 15 chiffres.");
            return false;
        }

        // Validation de la date de naissance (format YYYY-MM-DD)
        if (!dateNaissance.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("La date de naissance doit être au format YYYY-MM-DD.");
            return false;
        }

        if (verifierDMRExiste(numeroSecu)) {
            System.out.println("Un DMR existe déjà pour ce numéro de sécurité sociale.");
            return false;
        }

        try (Connection connection = ConnexionDataBase.getConnection()) {
            connection.setAutoCommit(false); // Désactiver l'autocommit

            // Récupérer le dernier ID_DMR pour générer le prochain
            long prochainID = 1; // Par défaut, le premier ID sera 1
            String requeteDernierID = "SELECT MAX(ID_DMR) AS dernier_id FROM DMR";

            try (Statement stmtDernierID = connection.createStatement(); ResultSet rs = stmtDernierID.executeQuery(requeteDernierID)) {
                if (rs.next()) {
                    prochainID = rs.getLong("dernier_id") + 1; // Incrémente le dernier ID
                }
            }

            // Insérer le nouveau DMR
            String requeteInsertDMR = "INSERT INTO DMR (ID_DMR, NOM, PRENOM, DATE_NAISSANCE, GENRE, N_SECU) "
                    + "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";

            try (PreparedStatement stmtInsert = connection.prepareStatement(requeteInsertDMR)) {
                stmtInsert.setLong(1, prochainID);
                stmtInsert.setString(2, nom);
                stmtInsert.setString(3, prenom);
                stmtInsert.setString(4, dateNaissance);
                stmtInsert.setString(5, genre);
                stmtInsert.setString(6, numeroSecu);

                int lignesAffectees = stmtInsert.executeUpdate();
                if (lignesAffectees <= 0) {
                    connection.rollback(); // Annule la transaction si échec
                    System.out.println("Échec de la création du DMR.");
                    return false;
                }
            }

            connection.commit(); // Valide explicitement la transaction
            System.out.println("DMR créé avec succès.");
            return true;

        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du DMR : " + e.getMessage());
            return false;
        }
    }

    public DMRInfo recupererDMR(String numeroSecu) {
        if (numeroSecu == null || numeroSecu.isBlank()) {
            System.out.println("Le numéro de sécurité sociale est obligatoire.");
            return null;
        }

        try (Connection connection = ConnexionDataBase.getConnection()) {
            // Vérifier si le DMR existe
            if (!verifierDMRExiste(numeroSecu)) {
                System.out.println("Aucun DMR trouvé pour ce numéro de sécurité sociale.");
                return null;
            }

            // Récupérer les informations du DMR
            String requeteRecupDMR = "SELECT * FROM DMR WHERE N_SECU = ?";
            try (PreparedStatement stmtRecup = connection.prepareStatement(requeteRecupDMR)) {
                stmtRecup.setString(1, numeroSecu);
                try (ResultSet rs = stmtRecup.executeQuery()) {
                    if (rs.next()) {
                        // Récupérer les informations du DMR
                        String nom = rs.getString("NOM");
                        String prenom = rs.getString("PRENOM");
                        String dateNaissance = rs.getString("DATE_NAISSANCE");
                        String genre = rs.getString("GENRE");

                        // Retourner un objet contenant les informations du DMR
                        return new DMRInfo(nom, prenom, dateNaissance, genre, numeroSecu);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du DMR : " + e.getMessage());
        }
        return null;
    }

    // Classe interne pour stocker les informations du DMR
    public static class DMRInfo {
        private final String nom;
        private final String prenom;
        private final String dateNaissance;
        private final String genre;
        private final String numeroSecu;

        public DMRInfo(String nom, String prenom, String dateNaissance, String genre, String numeroSecu) {
            this.nom = nom;
            this.prenom = prenom;
            this.dateNaissance = dateNaissance;
            this.genre = genre;
            this.numeroSecu = numeroSecu;
        }

        public String getNom() {
            return nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public String getDateNaissance() {
            return dateNaissance;
        }

        public String getGenre() {
            return genre;
        }

        public String getNumeroSecu() {
            return numeroSecu;
        }
    }
}

