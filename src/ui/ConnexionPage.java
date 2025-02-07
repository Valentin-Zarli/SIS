/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package ui;

import fc.Administration;
import fc.Connexion;
import fc.Examen;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

                switch (c.acces().getAcces()) {
                    case 1 -> {
                        Examen ex = new Examen("1", "12", "src/jpg/abdomen/cor494-i569.jpg");
                        pageExamen radP = new pageExamen(ex);
                        // Affiche ou ouvre la page pour le radiologue
                        textAreaMessages.appendText("Accès Radiologue\n");
                        Stage radStage = new Stage(); // Nouveau Stage pour l'admin
                        Scene radScene = new Scene(radP, 800, 600); // Crée une scène avec le contenu de AdministrationPage (qui est un conteneur de type Parent ici)
                        radStage.setScene(radScene);
                        radStage.setTitle("Page Administration");
                        radStage.show();
                    }
                    case 2 -> {
                        // Création de l'objet Administration avec l'accès
                        AdministrationPage admP = new AdministrationPage((Administration) c.acces());

                        // Affiche ou ouvre la page pour l'administration
                        textAreaMessages.appendText("Accès Administration\n");

                        Stage adminStage = new Stage(); // Nouveau Stage pour l'admin
                        Scene adminScene = new Scene(admP, 800, 600); // Crée une scène avec le contenu de AdministrationPage (qui est un conteneur de type Parent ici)
                        adminStage.setScene(adminScene);
                        adminStage.setTitle("Page Administration");
                        adminStage.show();

                        // Optionnel : Fermer la page actuelle
                        Stage currentStage = (Stage) buttonValider.getScene().getWindow();
                        currentStage.close();
                    }
                    case 3 -> {
                        //ManipulateurPage manipP = new ManipulateurPage(c.acces());
                        // Affiche ou ouvre la page pour le manipulateur
                        textAreaMessages.appendText("Accès Manipulateur\n");
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("ManipulateurPage.fxml")); // Vérifie le nom exact du fichier
                            primaryStage.setTitle("Application avec FXML");
                            primaryStage.setScene(new Scene(root));
                            primaryStage.show();
                        } catch (IOException e) {
                            e.printStackTrace(); // Affiche l'erreur si le FXML n'est pas trouvé
                        }

                    }
                    default ->
                        textAreaMessages.appendText("Accès non reconnu\n");
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
