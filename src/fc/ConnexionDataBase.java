/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valen
 */
public class ConnexionDataBase {

    //String url = "jdbc:oracle:thin:@oracle.im2ag.fr:1521/XE"; // Changez l'URL selon votre configuration
    // Utilisation du port local
    private static final String user = "zarliv";
    private static final String password = "02fa1007a9";

    private static final String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    private static final String dbUrl = "jdbc:oracle:thin:@im2ag-oracle.univ-grenoble-alpes.fr:1521:im2ag";
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rset;
    private static Scanner sc = new Scanner(System.in);

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, user, password);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return conn;
    }

    public static boolean sqlUpdate(String requete) {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Exécution de la requête de modification
            int lignesAffectees = stmt.executeUpdate(requete);
            return lignesAffectees > 0; // Retourne true si au moins une ligne a été affectée
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return false;
        }
    }

    public static String sqlRequete(String requete) {
        Scanner sc = new Scanner(System.in); // Cette variable est définie mais jamais utilisée.

        try {
            conn = ConnexionDataBase.getConnection();
            stmt = conn.createStatement();

            // Exécution de la requête SQL
            rset = stmt.executeQuery(requete);
            String resultat;
            resultat = "";

            while (rset.next()) {
                // Affichage du premier champ de chaque ligne du résultat
                resultat += rset.getString(1);

            }
            System.out.println("Resultat : " + resultat);

            return resultat;
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }


    // Ajoutez un return ou fermez simplement la méthode si rien n'est retourné
    return null; // Retourne null si la méthode est censée renvoyer une chaîne de caractères.
}
public static ResultSet sqlRequete2(String requete) {
    try {
        conn = ConnexionDataBase.getConnection();
        stmt = conn.createStatement();
        return stmt.executeQuery(requete);
    } catch (SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        return null;
    }
}

}
