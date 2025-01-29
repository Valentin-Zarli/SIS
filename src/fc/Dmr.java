/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.Date;

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

    public Dmr(String id_dmr,String nom,String prenom,Date Date_de_naissance,Genre genre,String n_secu) {
        this.id_dmr=id_dmr;
        this.nom=nom;
        this.prenom=prenom;
        this.Date_de_naissance=Date_de_naissance;
        this.genre = genre;
        this.n_secu=n_secu;

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

}
