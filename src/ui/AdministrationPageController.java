/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author lucas
 */


import fc.Administration;
import fc.Dmr;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;

public class AdministrationPageController {
    @FXML private Label welcomeLabel; 

    // Méthode publique pour initialiser le champ
//    public void initializeWelcomeLabel(String text) {
//        welcomeLabel.setText(text);
//    }

    
    @FXML private TextField textFieldNumSecuVerif;
    @FXML private Button buttonVerifDMR;
    @FXML private TextField textFieldNom;
    @FXML private TextField textFieldPrenom;
    @FXML private TextField textFieldDateNaissance;
    @FXML private TextField textFieldGenre;
    @FXML private TextField textFieldNumSecuCreer;
    @FXML private Button buttonCreerDMR;
    @FXML private TextField textFieldIdDMR;
    @FXML private TextField textFieldNomRechercher;
    @FXML private TextField textFieldPrenomRechercher;
    @FXML private TextField textFieldDateNaissanceRechercher;
    @FXML private TextField textFieldNumSecuRechercher;
    @FXML private Button buttonRechercherDMR;
    @FXML private TextArea textAreaMessages;

    private Administration admin;

    public void setAdmin(Administration admin) {
        this.admin = admin;
        welcomeLabel.setText("Bienvenue, " + admin.getNom());
    }

    @FXML
    private void initialize() {
        buttonVerifDMR.setOnAction(event -> verifierDMR());
        buttonCreerDMR.setOnAction(event -> creerDMR());
        buttonRechercherDMR.setOnAction(event -> rechercherDMR());
    }

    private void verifierDMR() {
        String numSecu = textFieldNumSecuVerif.getText();
        Dmr dmr = new Dmr(null, null, null, null, null, null);
        if (dmr.verifierDMRExiste(numSecu, null)) {
            textAreaMessages.appendText("Un DMR existe pour ce numéro de sécurité sociale.\n");
        } else {
            textAreaMessages.appendText("Aucun DMR trouvé pour ce numéro de sécurité sociale.\n");
        }
    }

    private void creerDMR() {
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
    }

    private void rechercherDMR() {
        String idDMR = textFieldIdDMR.getText();
        String nom = textFieldNomRechercher.getText();
        String prenom = textFieldPrenomRechercher.getText();
        String dateNaissance = textFieldDateNaissanceRechercher.getText();
        String numSecu = textFieldNumSecuRechercher.getText();

        Date date = null;
        if (dateNaissance != null && !dateNaissance.isBlank()) {
            date = Date.valueOf(dateNaissance);
        }

        Dmr dmr = new Dmr(null, null, null, null, null, null);
        List<Dmr> dmrs = dmr.recupererDMR(idDMR, numSecu, nom, prenom, date);

        if (!dmrs.isEmpty()) {
            textAreaMessages.appendText("DMR(s) trouvé(s) :\n");
            for (Dmr d : dmrs) {
                textAreaMessages.appendText("ID DMR : " + d.getId_dmr() + "\n");
                textAreaMessages.appendText("Nom : " + d.getNom() + "\n");
                textAreaMessages.appendText("Prénom : " + d.getPrenom() + "\n");
                textAreaMessages.appendText("Date de naissance : " + d.getDate_de_naissance() + "\n");
                textAreaMessages.appendText("Genre : " + d.getGenre() + "\n");
                textAreaMessages.appendText("Numéro de sécurité sociale : " + d.getN_secu() + "\n");
                textAreaMessages.appendText("-----------------------------\n");
            }
        } else {
            textAreaMessages.appendText("Aucun DMR trouvé pour les critères spécifiés.\n");
        }
    }
}
