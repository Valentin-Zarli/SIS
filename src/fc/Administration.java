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

    public boolean verifierDMRExiste(String identifiantUnique) {
        if (identifiantUnique == null) {
            System.out.println("L'identifiant unique est obligatoire.");
            return false;
        }

        try {
            String requeteVerif = "SELECT ID_DMR FROM DMR WHERE ID_DMR = ?";
            try (Connection connection = ConnexionDataBase.getConnection(); PreparedStatement stmtVerif = connection.prepareStatement(requeteVerif)) {

                stmtVerif.setLong(1, Long.parseLong(identifiantUnique)); // Convertir en entier car ID_DMR est un NUMBER
                try (ResultSet rs = stmtVerif.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Un DMR existe deja pour cet identifiant : " + rs.getLong("ID_DMR"));
                        return true; // Le DMR existe
                    } else {
                        System.out.println("Aucun DMR trouve pour cet identifiant.");
                        return false; // Le DMR n'existe pas
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification du DMR : " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("L'identifiant unique doit etre un nombre valide : " + e.getMessage());
            return false;
        }
    }

    public boolean creerDMR(String nom, String prenom, String dateNaissance, String genre, String identifiantUnique) {
        // Vérification des champs obligatoires
        if (nom == null || prenom == null || dateNaissance == null || genre == null || identifiantUnique == null) {
            System.out.println("Tous les champs obligatoires doivent etre remplis.");
            return false;
        }
        if (!verifierDMRExiste(identifiantUnique)) { {

            try {
                // Créer un nouveau DMR
                String requeteInsertDMR = "INSERT INTO ZARLIV.DMR (ID_DMR, NOM, PRENOM, DATE_NAISSANCE, GENRE, N_SECU) "
                        + "VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, '123456789012345')";

                try (Connection connection = ConnexionDataBase.getConnection(); PreparedStatement stmtInsert = connection.prepareStatement(requeteInsertDMR)) {

                    stmtInsert.setLong(1, Long.parseLong(identifiantUnique));
                    stmtInsert.setString(2, nom);
                    stmtInsert.setString(3, prenom);
                    stmtInsert.setString(4, dateNaissance);
                    stmtInsert.setString(5, genre);

                    int lignesAffectees = stmtInsert.executeUpdate();
                    if (lignesAffectees <= 0) {
                        System.out.println("Echec de la création du DMR.");
                        return false;
                    }
                }

                System.out.println("DMR cree avec succes.");
                return true;

            } catch (SQLException e) {
                System.out.println("Erreur lors de la création du DMR : " + e.getMessage());
                return false;
            } catch (NumberFormatException e) {
                System.out.println("L'identifiant unique doit etre un nombre valide : " + e.getMessage());
                return false;
            }
        }}return false;}

}
