/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fc;

/**
 *
 * @author valen
 */

public class NewMain {
    public static void main(String[] args) {
        // Création d'une instance de la classe Examen
        Examen examen = new Examen();

        // Données de test
        String id_dmr = "2";  
        String date = "2025-02-27 19:13";  
        String path = "src/jpg/abdomen/cor494-i569.jpg";

        // Appel de la méthode creerExamen
        boolean resultat = examen.creerExamen(id_dmr, date, path);

        // Vérification du résultat
        if (resultat) {
            System.out.println("✅ Test réussi : L'examen a été créé avec succès.");
        } else {
            System.out.println("❌ Test échoué : L'examen n'a pas pu être créé.");
        }
    }
}
