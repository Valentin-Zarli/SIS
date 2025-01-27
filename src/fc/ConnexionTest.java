/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fc;

import java.sql.Connection;
import java.util.Scanner;


/**
 *
 * @author valen
 */
public class ConnexionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Connection conn =ConnexionDataBase.getConnection();
        if(conn!=null){
            System.out.println("Connexion correcte à la base de données");
            Scanner sc = new Scanner(System.in);
            
            System.out.println("Veuillez saisir un identifiant :");
            String id = sc.nextLine();
            System.out.println("Veuillez saisir un mot de passe :");
            String password = sc.nextLine();
            
            
            
            Connexion c = new Connexion(id,password);
            System.out.println(c.acces());
            
            
            /*String  requete= "SELECT * "
                    + "FROM Administration "
                    + "WHERE numero_id=7"
                    + "  AND Mot_de_passe='admin127'";
            String s = ConnexionDataBase.sqlRequete(requete);
            System.out.println(s);*/
        
    }
    }
    
}
