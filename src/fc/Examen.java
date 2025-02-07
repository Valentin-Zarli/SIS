/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import static fc.ConnexionDataBase.sqlRequete;

/**
 *
 * @author valen
 */
public class Examen {

    private String id_dmr;
    private String date;
    private String image_path;
    private String compte_rendu;

    public Examen() {

    }

    public Examen(String id_dmr, String date, String image_path) {
        this.id_dmr = id_dmr;
        this.date = date;
        this.image_path = image_path;

    }

    /**
     * @return the id_dmr
     */
    public String getId_dmr() {
        return id_dmr;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @return the image_path
     */
    public String getImage_path() {
        return image_path;
    }

    /**
     * @return the compte_rendu
     */
    public String getCompte_rendu() {
        return compte_rendu;
    }

    /**
     * @param compte_rendu the compte_rendu to set
     */
    public void setCompte_rendu(String compte_rendu) {
        this.compte_rendu = compte_rendu;
    }

    public boolean creerExamen(String id_dmr, String Date, String Path) {
        // Validation des champs obligatoires
        if (id_dmr == null || id_dmr.isBlank()
                || Date == null || Date.isBlank()
                || Path == null || Path.isBlank()) {
            System.out.println("Tous les champs obligatoires doivent être remplis.");
            return false;
        }
        
        

       
        

         // Compléter avec secondes et microsecondes
        Date += ":00.000000";

        // Insérer le nouveau DMR
        String requeteInsertExamen = "INSERT INTO DMR (ID_DMR, DATE_EXAMEN, IMAGE_PATH,COMPTE_RENDU) "
                + "VALUES ('" + id_dmr +"',STR_TO_DATE('"+Date+  "',%Y-%m-%d %H:%i:%s.%f'),'" + Path+"')";

        // Exécuter la requête d'insertion
        boolean insertionReussie = ConnexionDataBase.sqlUpdate(requeteInsertExamen);

        if (insertionReussie) {
            System.out.println("DMR créé avec succès.");
            return true;
        } else {
            System.out.println("Échec de la création du DMR.");
            return false;
        }
    }

}
