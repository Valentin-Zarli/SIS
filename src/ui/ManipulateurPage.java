package ui;

import fc.Manipulateur;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class ManipulateurPage extends VBox {

    private Manipulateur manipulateur;
    private TextArea textAreaMessages;

    public ManipulateurPage(Manipulateur manipulateur) {
        this.manipulateur = manipulateur;
        this.textAreaMessages = new TextArea();
        textAreaMessages.setEditable(false);
        textAreaMessages.setPrefHeight(100);

        // Welcome message with the manipulator's name
        Label welcomeLabel = new Label("Bienvenue, " + manipulateur.getNom());
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Add components to the VBox
        this.getChildren().addAll(welcomeLabel, textAreaMessages);
    }
}