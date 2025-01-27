/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.Connection;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author valen
 */
public class Connexion {
    private String id ;
    private String password;
    
    public Connexion(String id ,String  password){
        this.id=id;
        this.password=password;
        
    }
    
   public Utilisateur acces(){
       String requete;
       requete = "SELECT metier "
                    + "FROM utilisateur "
                    + "WHERE numero_id="+id
                    + "  AND Mot_de_passe='"+password+"'";
       if(ConnexionDataBase.sqlRequete(requete).equals("Administration")){
           Administration u = new Administration(id,password);
           return u ;
           
       }
       if(ConnexionDataBase.sqlRequete(requete).equals("Radiologue")){
             Radiologue u= new Radiologue(id,password);
           return u ;
           
       }
       if(ConnexionDataBase.sqlRequete(requete).equals("Manipulateur")){
             Manipulateur u= new Manipulateur(id,password);
           return u ;
           
       }
       return null ;
   }
   /* public Utilisateur connexion(){
        
    }*/
    
    
    
    
    
}
