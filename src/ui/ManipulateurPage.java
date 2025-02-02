package ui;

import fc.Examen;
import fc.Manipulateur;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ManipulateurPage extends VBox {

    private Manipulateur manipulateur;
    private TextArea textAreaMessages;
    private TextField numeroSecuField;
    private TextField dateExamenField;
    private TextArea compteRenduArea;
    private Button creerExamenButton;

    public ManipulateurPage(Manipulateur manipulateur) {
        this.manipulateur = manipulateur;
        this.textAreaMessages = new TextArea();
        textAreaMessages.setEditable(false);
        textAreaMessages.setPrefHeight(100);

        // Welcome message with the manipulator's name
        Label welcomeLabel = new Label("Bienvenue, " + manipulateur.getNom());
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Adding fields for Examen details
        numeroSecuField = new TextField();
        numeroSecuField.setPromptText("Numéro de Sécurité Sociale");
        
        dateExamenField = new TextField();
        dateExamenField.setPromptText("Date de l'examen (YYYY-MM-DD HH:MM)");

        compteRenduArea = new TextArea();
        compteRenduArea.setPromptText("Compte rendu de l'examen");

        // Button to create the Examen
        creerExamenButton = new Button("Créer l'examen");
        creerExamenButton.setOnAction(e -> creerExamen());

        // Add components to the VBox
        this.getChildren().addAll(welcomeLabel, textAreaMessages, numeroSecuField, dateExamenField, compteRenduArea, creerExamenButton);
    }

    // Method to create an exam
    private void creerExamen() {
        String numeroSecu = numeroSecuField.getText();
        String dateExamen = dateExamenField.getText();
        String compteRendu = compteRenduArea.getText();

        Examen examen = new Examen(null, null, null);
        boolean isCreated = examen.creerExamen(numeroSecu, dateExamen, compteRendu);

        // Display the result message in the text area
        if (isCreated) {
            textAreaMessages.appendText("Examen créé avec succès.\n");
        } else {
            textAreaMessages.appendText("Erreur lors de la création de l'examen.\n");
        }
    }
}
