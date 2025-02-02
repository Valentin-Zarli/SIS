/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author valen
 */
public class Examen {

    private String id_dmr;
    private Date Date_examen;
    private int n_image;
    private String Compte_rendu;

    public Examen(String id_dmr, Date Date_examen, String Compte_rendu) {
        this.id_dmr = id_dmr;
        this.Date_examen = Date_examen;
        this.n_image = 0;
        this.Compte_rendu = Compte_rendu;
    }

    public String getId_dmr() {
        return id_dmr;
    }

    public Date getDate_examen() {
        return Date_examen;
    }

    public int getN_image() {
        return n_image;
    }

    public String getCompte_rendu() {
        return Compte_rendu;
    }

    public boolean creerExamen(String numeroSecu, String Date_examen, String Compte_rendu) {
        int Nom_Image = 0;
        String id_dmr = null;

        if (numeroSecu == null || numeroSecu.isBlank()
                || Date_examen == null || Date_examen.isBlank()
                || Compte_rendu == null || Compte_rendu.isBlank()) {
            System.out.println("Tous les champs obligatoires doivent être remplis.");
            return false;
        }

        if (numeroSecu.length() != 15 || !numeroSecu.matches("\\d{15}")) {
            System.out.println("Le numéro de sécurité sociale doit contenir exactement 15 chiffres.");
            return false;
        }

        if (!Date_examen.matches("\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}")) {
            System.out.println("La date de naissance doit être au format YYYY-MM-DD-HH-MN.");
            return false;
        }

        Dmr dmr = new Dmr(null, null, null, null, null, null);
        if (!dmr.verifierDMRExiste(numeroSecu)) {
            System.out.println("Aucun DMR associé à ce numéro de sécu");
            return false;
        }

        String requeteId_DMR = "SELECT ID_DMR FROM DMR WHERE N_SECU = ?";

        try (Connection connection = ConnexionDataBase.getConnection(); PreparedStatement stmt = connection.prepareStatement(requeteId_DMR)) {
            stmt.setString(1, numeroSecu);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    id_dmr = rs.getString("ID_DMR");
                } else {
                    System.out.println("Aucun ID_DMR trouvé pour ce numéro de sécurité sociale.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'ID_DMR : " + e.getMessage());
            return false;
        }

        try (Connection connection = ConnexionDataBase.getConnection()) {
            connection.setAutoCommit(false); // Désactiver l'autocommit

            // Insérer le nouveau Examen
            String requeteInsertExamen = "INSERT INTO Examen (ID_DMR, DATE_EXAMEN, COMPTE_RENDU) "
                    + "VALUES (?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI'), ?)";


            try (PreparedStatement stmtInsert = connection.prepareStatement(requeteInsertExamen)) {
                stmtInsert.setString(1, id_dmr);
                stmtInsert.setString(2, Date_examen);
                //stmtInsert.setInt(3, Nom_Image);
                stmtInsert.setString(3, Compte_rendu);

                int lignesAffectees = stmtInsert.executeUpdate();
                if (lignesAffectees <= 0) {
                    connection.rollback(); // Annule la transaction si échec
                    System.out.println("Échec de la création de l'Examen.");
                    return false;
                }
            }
            connection.commit(); // Valide explicitement la transaction
            System.out.println("Examen créé avec succès.");
            return true;
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de l'Examen : " + e.getMessage());
            return false;
        }
    }

}
