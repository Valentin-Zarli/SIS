/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package ui;

import fc.Administration;
import fc.Connexion;
import fc.Manipulateur;
import fc.Radiologue;
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

        Object acces = c.acces();
        if (acces instanceof Administration) {
            Administration admin = (Administration) acces;
            AdministrationPage admP = new AdministrationPage(admin);
            textAreaMessages.appendText("Accès Administration\n");

            Stage adminStage = new Stage();
            Scene adminScene = new Scene(admP, 800, 600);
            adminStage.setScene(adminScene);
            adminStage.setTitle("Page Administration");
            adminStage.show();

            Stage currentStage = (Stage) buttonValider.getScene().getWindow();
            currentStage.close();
        } else if (acces instanceof Manipulateur) {
            Manipulateur manip = (Manipulateur) acces;
            ManipulateurPage manP = new ManipulateurPage(manip);
            textAreaMessages.appendText("Accès Manipulateur\n");

            Stage manipStage = new Stage();
            Scene manipScene = new Scene(manP, 800, 600);
            manipStage.setScene(manipScene);
            manipStage.setTitle("Page Manipulateur");
            manipStage.show();

            Stage currentStage = (Stage) buttonValider.getScene().getWindow();
            currentStage.close();
        } else if (acces instanceof Radiologue) {
            Radiologue rad = (Radiologue) acces;
            pageExamen radP = new pageExamen(rad);
            textAreaMessages.appendText("Accès Radiologue\n");

            Stage radStage = new Stage();
            Scene radScene = new Scene(radP, 800, 600);
            radStage.setScene(radScene);
            radStage.setTitle("Page Radiologue");
            radStage.show();

            Stage currentStage = (Stage) buttonValider.getScene().getWindow();
            currentStage.close();
        } else {
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
