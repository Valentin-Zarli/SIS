/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

/**
 *
 * @author valen
 */
public abstract class Utilisateur {
    private String id ;
    private String Mot_de_passe;
    private int acces ;
    
    public Utilisateur(String id, String Mot_de_passe, int acces) {
        this.id = id;
        this.Mot_de_passe = Mot_de_passe;
        this.acces = acces;
    }
    
    public String getId() {
        return id;
    }

    public int getAcces() {
        return acces;
    }
    
    public String getNom(){
        String requete = "SELECT nom "
                    + "FROM utilisateur "
                    + "WHERE numero_id="+id
                    + "  AND Mot_de_passe='"+Mot_de_passe+"'";
        return ConnexionDataBase.sqlRequete(requete);
    }
    
}
