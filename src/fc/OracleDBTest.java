/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class OracleDBTest {

    public static void main(String[] args) {
        //String url = "jdbc:oracle:thin:@oracle.im2ag.fr:1521/XE"; // Changez l'URL selon votre configuration
        String user = "zarliv";
        String password = "02fa1007a9";

        String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
        String dbUrl = "jdbc:oracle:thin:@im2ag-oracle.univ-grenoble-alpes.fr:1521:im2ag";  // Utilisation du port local

        Connection conn;
        Statement stmt;
        ResultSet rset;
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(dbUrl, user, password);
            stmt = conn.createStatement();

            System.out.println("Veuillez saisir un identifiant :");
            String id = sc.nextLine();
            System.out.println("Veuillez saisir un mot de passe :");
            String password2 = sc.nextLine();
   
            rset = stmt.executeQuery("SELECT Nom "
                    + "FROM Administration "
                    + "WHERE numero_id="+id
                    + "  AND Mot_de_passe='"+password2+"'");

            while (rset.next()) {
                System.out.println(rset.getString(1));
            }

            rset.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erreur SQL : \n" + e.getMessage());
        }
    }
}
