
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package ui;
import fc.Radiologue;
import fc.Administration;
import fc.Connexion;
import fc.Examen;
import fc.Utilisateur;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ConnexionPage extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Labels
        Label labelIdentifiant = new Label("Identifiant :");
        Label labelMotDePasse = new Label("Mot de passe :");
        // TextField for user input
        TextField textFieldIdentifiant = new TextField();
        textFieldIdentifiant.setPromptText("Entrez votre identifiant");
        // PasswordField for password input
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Entrez votre mot de passe");
        // Button to validate the connection
        Button buttonValider = new Button("Valider");
        // TextArea for displaying messages
        TextArea textAreaMessages = new TextArea();
        textAreaMessages.setEditable(false);
        textAreaMessages.setPrefHeight(100);
        // Button action
        buttonValider.setOnAction(event -> {
            String id = textFieldIdentifiant.getText();
            String password = passwordField.getText();
            Connexion c = new Connexion(id, password);
            if (c == null) {
                textAreaMessages.appendText("Mot de passe incorrect\n");
            } else {
                textAreaMessages.appendText("Connexion réussie\n");
                Utilisateur utilisateur = c.acces();
                if (utilisateur == null) {
                    textAreaMessages.appendText("Aucun utilisateur trouvé avec ces identifiants.\n");
                } else {
                    switch (utilisateur.getAcces()) {
                        case 1 -> {
                            System.out.println("Accès Radiologue détecté");
                            RadiologuePage radPage = new RadiologuePage((Radiologue) utilisateur); 
                            textAreaMessages.appendText("Accès Radiologue\n");
                            radPage.show(); 
                        }
                        case 2 -> {
                            System.out.println("Accès Administration détecté");
                            AdministrationPage admPage = new AdministrationPage((Administration) utilisateur); // Cast en Administration
                            textAreaMessages.appendText("Accès Administration\n");
                            admPage.show();
                        }
                        case 3 -> {
                            System.out.println("Accès Manipulateur détecté");
//                            ManipulateurPage manipPage = new ManipulateurPage((Manipulateur) utilisateur); // Cast en Manipulateur
//                            textAreaMessages.appendText("Accès Manipulateur\n");
                        }
                        default -> {
                            System.out.println("Accès non reconnu");
                            textAreaMessages.appendText("Accès non reconnu\n");
                        }
                    }
                }
            }
        });
        // Layout with VBox
        VBox vbox = new VBox(10, textAreaMessages, labelIdentifiant, textFieldIdentifiant,
                labelMotDePasse, passwordField, buttonValider);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(400, 300);
        // Scene setup
        Scene scene = new Scene(vbox);
        primaryStage.setTitle("Page de Connexion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

