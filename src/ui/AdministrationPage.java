package ui;

import fc.Administration;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class AdministrationPage extends VBox {
    private final Administration administrateur;

    public AdministrationPage(Administration administrateur) {
        this.administrateur = administrateur;

        // Configuration de la VBox
        this.setSpacing(20);
        this.setPrefSize(800, 600);

        // Label de bienvenue
        Label welcomeLabel = new Label("Bienvenue, Administrateur " + administrateur.getNom());
        this.getChildren().add(welcomeLabel);

        // Gestion des DMR
        this.getChildren().add(createDMRManagementSection());
    }

    private VBox createDMRManagementSection() {
        VBox dmrSection = new VBox(10); // Espacement entre les éléments
        Label sectionTitle = new Label("Gestion des DMR");
        dmrSection.getChildren().add(sectionTitle);

        // Champs de formulaire
        TextField nomField = new TextField();
        nomField.setPromptText("Nom");

        TextField prenomField = new TextField();
        prenomField.setPromptText("Prénom");

        TextField dateNaissanceField = new TextField();
        dateNaissanceField.setPromptText("Date de naissance (YYYY-MM-DD)");

        TextField genreField = new TextField();
        genreField.setPromptText("Genre");

        TextField numeroSecuField = new TextField();
        numeroSecuField.setPromptText("Numéro de Sécurité Sociale");

        // Zone pour afficher les messages
        Label messageLabel = new Label();

        // Bouton de vérification
        Button verifierButton = new Button("Vérifier DMR");
        verifierButton.setOnAction(event -> {
            String numeroSecu = numeroSecuField.getText();
            if (numeroSecu.isEmpty()) {
                messageLabel.setText("Veuillez entrer un numéro de sécurité sociale.");
                return;
            }

            boolean existe = administrateur.verifierDMRExiste(numeroSecu);
            if (existe) {
                messageLabel.setText("Un DMR existe déjà pour ce numéro de sécurité sociale.");
            } else {
                messageLabel.setText("Aucun DMR trouvé pour ce numéro de sécurité sociale. Vous pouvez le créer.");
            }
        });

        // Bouton de création
        Button creerButton = new Button("Créer DMR");
        creerButton.setOnAction(event -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String dateNaissance = dateNaissanceField.getText();
            String genre = genreField.getText();
            String numeroSecu = numeroSecuField.getText();

            if (nom.isEmpty() || prenom.isEmpty() || dateNaissance.isEmpty() || genre.isEmpty() || numeroSecu.isEmpty()) {
                messageLabel.setText("Tous les champs doivent être remplis pour créer un DMR.");
                return;
            }

            boolean success = administrateur.creerDMR(nom, prenom, dateNaissance, genre, numeroSecu);
            if (success) {
                messageLabel.setText("DMR créé avec succès !");
            } else {
                messageLabel.setText("Échec de la création du DMR. Vérifiez les données ou l'existence.");
            }
        });

        // Disposition des éléments
        HBox formBox = new HBox(10, nomField, prenomField, dateNaissanceField, genreField, numeroSecuField);
        HBox buttonsBox = new HBox(10, verifierButton, creerButton);

        dmrSection.getChildren().addAll(formBox, buttonsBox, messageLabel);
        return dmrSection;
    }
}

