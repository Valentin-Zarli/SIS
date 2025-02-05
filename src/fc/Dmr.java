/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.util.ArrayList;
import java.util.List;
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


public class Dmr {

    private String id_dmr;
    private String nom;
    private String prenom;
    private Date Date_de_naissance;
    private Genre genre;
    private String n_secu;
    
    public Dmr() {
        
        this.id_dmr = null;
        this.nom = null;
        this.prenom = null;
        this.Date_de_naissance = null;
        this.genre = null;
        this.n_secu = null;
    }

    public Dmr(String id_dmr, String nom, String prenom, Date Date_de_naissance, Genre genre, String n_secu) {
        this.id_dmr = id_dmr;
        this.nom = nom;
        this.prenom = prenom;
        this.Date_de_naissance = Date_de_naissance;
        this.genre = genre;
        this.n_secu = n_secu;

    }
    

    /**
     * @return the id_dmr
     */
    public String getId_dmr() {
        return id_dmr;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return the Date_de_naissance
     */
    public Date getDate_de_naissance() {
        return Date_de_naissance;
    }

    /**
     * @return the genre
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * @return the n_secu
     */
    public String getN_secu() {
        return n_secu;
    }

    public boolean verifierDMRExiste(String numeroSecu) {
        if (numeroSecu == null || numeroSecu.isBlank()) {
            System.out.println("Le numéro de sécurité sociale est obligatoire.");
            return false;
        }

        String requeteVerif = "SELECT 1 FROM DMR WHERE N_SECU = '" + numeroSecu + "'";
        String resultat = ConnexionDataBase.sqlRequete(requeteVerif);

        return resultat != null && !resultat.isEmpty();
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

        // Vérifier si un DMR existe déjà avec ce numéro de sécurité sociale
        if (verifierDMRExiste(numeroSecu)) {
            System.out.println("Un DMR existe déjà pour ce numéro de sécurité sociale.");
            return false;
        }

        // Récupérer le dernier ID_DMR pour générer le prochain
        String requeteDernierID = "SELECT MAX(ID_DMR) AS dernier_id FROM DMR";
        String dernierID = ConnexionDataBase.sqlRequete(requeteDernierID);
        long prochainID = dernierID != null && !dernierID.isEmpty() ? Long.parseLong(dernierID) + 1 : 1;

        // Insérer le nouveau DMR
        String requeteInsertDMR = "INSERT INTO DMR (ID_DMR, NOM, PRENOM, DATE_NAISSANCE, GENRE, N_SECU) "
                + "VALUES (" + prochainID + ", '" + nom + "', '" + prenom + "', TO_DATE('" + dateNaissance + "', 'YYYY-MM-DD'), '" + genre + "', '" + numeroSecu + "')";

        // Exécuter la requête d'insertion
        boolean insertionReussie = ConnexionDataBase.sqlUpdate(requeteInsertDMR);

        if (insertionReussie) {
            System.out.println("DMR créé avec succès.");
            return true;
        } else {
            System.out.println("Échec de la création du DMR.");
            return false;
        }
    }

    public List<Dmr> recupererDMR(String idDMR, String numeroSecu, String nom, String prenom, Date date) {
        List<Dmr> dmrs = new ArrayList<>();

        if ((numeroSecu == null || numeroSecu.isBlank())
                && (idDMR == null || idDMR.isBlank())
                && (date == null)
                && (nom == null || nom.isBlank())
                && (prenom == null || prenom.isBlank())) {
            System.out.println("Au moins un critère de recherche est obligatoire.");
            return dmrs;
        }

        StringBuilder requeteRecupDMR = new StringBuilder("SELECT * FROM DMR WHERE 1=1");
        if (numeroSecu != null && !numeroSecu.isBlank()) {
            requeteRecupDMR.append(" AND N_SECU = '").append(numeroSecu).append("'");
        }
        if (nom != null && !nom.isBlank()) {
            requeteRecupDMR.append(" AND UPPER(NOM) = UPPER('").append(nom).append("')");
        }
        if (prenom != null && !prenom.isBlank()) {
            requeteRecupDMR.append(" AND UPPER(PRENOM) = UPPER('").append(prenom).append("')");
        }
        if (idDMR != null && !idDMR.isBlank()) {
            requeteRecupDMR.append(" AND ID_DMR = '").append(idDMR).append("'");
        }

        if (date != null) {
            // Formater la date au format attendu par la base de données (YYYY-MM-DD)
            requeteRecupDMR.append(" AND DATE_NAISSANCE = TO_DATE('")
                    .append(new java.text.SimpleDateFormat("yyyy-MM-dd").format(date))
                    .append("', 'YYYY-MM-DD')");
        }

        try (Connection connection = ConnexionDataBase.getConnection(); PreparedStatement stmtRecup = connection.prepareStatement(requeteRecupDMR.toString()); ResultSet rs = stmtRecup.executeQuery()) {

            while (rs.next()) {
                String id_dmr = rs.getString("ID_DMR");
                String nomPatient = rs.getString("NOM");
                String prenomPatient = rs.getString("PRENOM");
                Date dateNaissance = rs.getDate("DATE_NAISSANCE");
                String numSecu = rs.getString("N_SECU");
                String g = rs.getString("GENRE").trim().toUpperCase();
                Genre genre = g.equals("H") ? Genre.H : Genre.F;

                dmrs.add(new Dmr(id_dmr, nomPatient, prenomPatient, dateNaissance, genre, numSecu));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du DMR : " + e.getMessage());
        }

        return dmrs;
    }

}
