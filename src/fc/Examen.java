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
        

        // Insérer le nouveau DMR
        String requeteInsertExamen = "INSERT INTO EXAMEN (ID_DMR, DATE_EXAMEN, IMAGE_PATH,COMPTE_RENDU) "
                + "VALUES (" + id_dmr +",TO_TIMESTAMP('"+Date+  "','YYYY-MM-DD HH24:MI'),'" + Path+"',NULL)";
        String req = "INSERT INTO EXAMEN";   
                
        
        System.out.println("INSERT INTO EXAMEN (ID_DMR, DATE_EXAMEN, IMAGE_PATH,COMPTE_RENDU) "
                + "VALUES (" + id_dmr +",TO_TIMESTAMP('"+Date+  "','YYYY-MM-DD HH24:MI'),'" + Path+"')");
        

        // Exécuter la requête d'insertion
        boolean insertionReussie = ConnexionDataBase.sqlUpdate("INSERT INTO EXAMEN (ID_DMR, DATE_EXAMEN, IMAGE_PATH,COMPTE_RENDU) "
                + "VALUES (" + id_dmr +",TO_TIMESTAMP('"+Date+  "','YYYY-MM-DD HH24:MI'),'" + Path+"',NULL)");

        if (insertionReussie) {
            System.out.println("Examen créé avec succès.");
            return true;
        } else {
            System.out.println("Échec de la création du DMR.");
            return false;
        }
    }

}
