package ui;

import fc.Administration;
import fc.Dmr;
import fc.Genre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdministrationPage extends VBox {

    private Administration admin;
    private TextArea textAreaMessages;

    public AdministrationPage(Administration admin) {
        this.admin = admin;
        this.textAreaMessages = new TextArea();
        textAreaMessages.setEditable(false);
        textAreaMessages.setPrefHeight(100);

        // Welcome message with the administrator's name
        Label welcomeLabel = new Label("Bienvenue, " + admin.getNom());
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Components for DMR verification
        Label labelVerifDMR = new Label("Vérifier l'existence d'un DMR :");
        TextField textFieldNumSecuVerif = new TextField();
        textFieldNumSecuVerif.setPromptText("Entrez le numéro de sécurité sociale");
        Button buttonVerifDMR = new Button("Vérifier");

        // Components for DMR creation
        Label labelCreerDMR = new Label("Créer un nouveau DMR :");
        TextField textFieldNom = new TextField();
        textFieldNom.setPromptText("Entrez le nom");
        TextField textFieldPrenom = new TextField();
        textFieldPrenom.setPromptText("Entrez le prénom");
        TextField textFieldDateNaissance = new TextField();
        textFieldDateNaissance.setPromptText("Entrez la date de naissance (YYYY-MM-DD)");
        TextField textFieldGenre = new TextField();
        textFieldGenre.setPromptText("Entrez le genre (H ou F)");
        TextField textFieldNumSecuCreer = new TextField();
        textFieldNumSecuCreer.setPromptText("Entrez le numéro de sécurité sociale");
        Button buttonCreerDMR = new Button("Créer");

        // Components for DMR search
        Label labelRechercherDMR = new Label("Rechercher un DMR :");
        TextField textFieldNumSecuRechercher = new TextField();
        textFieldNumSecuRechercher.setPromptText("Entrez le numéro de sécurité sociale");
        Button buttonRechercherDMR = new Button("Rechercher");

        // Layout
        this.getChildren().addAll(
                welcomeLabel,
                labelVerifDMR, textFieldNumSecuVerif, buttonVerifDMR,
                labelCreerDMR, textFieldNom, textFieldPrenom, textFieldDateNaissance,
                textFieldGenre, textFieldNumSecuCreer, buttonCreerDMR,
                labelRechercherDMR, textFieldNumSecuRechercher, buttonRechercherDMR,
                textAreaMessages
        );

        // Button actions
        buttonVerifDMR.setOnAction(event -> {
            String numSecu = textFieldNumSecuVerif.getText();
            Dmr dmr = new Dmr(null, null, null, null, null, null);
            if (dmr.verifierDMRExiste(numSecu)) {
                textAreaMessages.appendText("Un DMR existe pour ce numéro de sécurité sociale.\n");
            } else {
                textAreaMessages.appendText("Aucun DMR trouvé pour ce numéro de sécurité sociale.\n");
            }
        });

        buttonCreerDMR.setOnAction(event -> {
            String nom = textFieldNom.getText();
            String prenom = textFieldPrenom.getText();
            String dateNaissance = textFieldDateNaissance.getText();
            String genre = textFieldGenre.getText();
            String numSecu = textFieldNumSecuCreer.getText();

            Dmr dmr = new Dmr(null, null, null, null, null, null);
            if (dmr.creerDMR(nom, prenom, dateNaissance, genre, numSecu)) {
                textAreaMessages.appendText("DMR créé avec succès.\n");
            } else {
                textAreaMessages.appendText("Échec de la création du DMR.\n");
            }
        });

        buttonRechercherDMR.setOnAction(event -> {
            String numSecu = textFieldNumSecuRechercher.getText();
            Dmr dmr = new Dmr(null, null, null, null, null, null);
            Dmr dmrTrouve = dmr.recupererDMR(numSecu);

            if (dmrTrouve != null) {
                textAreaMessages.appendText("DMR trouvé :\n");
                textAreaMessages.appendText("ID DMR : " + dmrTrouve.getId_dmr() + "\n");
                textAreaMessages.appendText("Nom : " + dmrTrouve.getNom() + "\n");
                textAreaMessages.appendText("Prénom : " + dmrTrouve.getPrenom() + "\n");
                textAreaMessages.appendText("Date de naissance : " + dmrTrouve.getDate_de_naissance() + "\n");
                textAreaMessages.appendText("Genre : " + dmrTrouve.getGenre() + "\n");
                textAreaMessages.appendText("Numéro de sécurité sociale : " + dmrTrouve.getN_secu() + "\n");
            } else {
                textAreaMessages.appendText("Aucun DMR trouvé pour ce numéro de sécurité sociale.\n");
            }
        });
    }
}