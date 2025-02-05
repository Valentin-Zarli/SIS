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

    private  String id_dmr;
    private  String date;
    private  String image_path;
    private String compte_rendu;
    
   
    
    public Examen(String id_dmr, String date, String image_path){
        this.id_dmr=id_dmr;
        this.date=date;
        this.image_path=image_path;
        
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
    
    
    
    
}
