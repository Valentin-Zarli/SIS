/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import static fc.ConnexionDataBase.sqlRequete;
import static fc.ConnexionDataBase.sqlRequete2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author valen
 */
public class Pacs {

    private String nom;
    private String path;

    public Pacs() {

    }

    public Pacs(String nom, String path) {
        this.nom = nom;
        this.path = path;

    }

    public String pathWithNom(String nom) {

        String path = sqlRequete("Select IMAGE_PATH from pacs where NOM_IMAGE ='" + nom + "'");
        return path;

    }

    public ObservableList<String> ListImage() {

        ResultSet res = sqlRequete2("Select NOM_IMAGE from pacs");
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            while (res.next()) {

                
                items.add(res.getString("NOM_IMAGE"));
            }
            return items;

        } catch (SQLException ex) {
            Logger.getLogger(ConnexionTest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    //res.toString()

}
