package ui;

import fc.Examen;
import fc.Radiologue;
import fc.Dmr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Map;

public class RadiologuePageController {
    @FXML private Label welcomeLabel;
    @FXML private TextField textFieldDMR;
    @FXML private Button buttonRechercher;
    @FXML private TableView<Examen> tableViewExamens;
    @FXML private TableColumn<Examen, String> columnDMR;
    @FXML private TableColumn<Examen, String> columnDate;
    @FXML private TableColumn<Examen, String> columnCompteRendu;

    private Radiologue radiologue;
    private Dmr dmr;
    private ObservableList<Examen> examensList = FXCollections.observableArrayList();

    public void setRadiologue(Radiologue radiologue) {
        this.radiologue = radiologue;
        welcomeLabel.setText("Bienvenue, " + radiologue.getNom());
        
    }

    @FXML
    private void initialize() {
        if (tableViewExamens == null) {
            System.out.println("Erreur : tableViewExamens est null.");
            return;
        }
        if (columnDMR == null || columnDate == null || columnCompteRendu == null) {
            System.out.println("Erreur : Une ou plusieurs colonnes sont null.");
            return;
        }

        buttonRechercher.setOnAction(event -> rechercherExamens());

        // Initialiser les colonnes du tableau
        columnDMR.setCellValueFactory(new PropertyValueFactory<>("id_dmr"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCompteRendu.setCellValueFactory(new PropertyValueFactory<>("compte_rendu"));

        // Personnaliser l'affichage des cellules en fonction de la présence d'un compte rendu
        columnCompteRendu.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item.isEmpty() ? "Pas de CR" : "Compte rendu disponible");
                    setTextFill(item.isEmpty() ? Color.RED : Color.GREEN);
                }
            }
        });

        tableViewExamens.setItems(examensList);

        // Ajouter un listener pour ouvrir la page Examen en double-cliquant
        tableViewExamens.setRowFactory(tv -> {
            TableRow<Examen> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    ouvrirPageExamen(row.getItem());
                }
            });
            return row;
        });
    }

    private void rechercherExamens() {
        String idDMR = textFieldDMR.getText();
        if (idDMR == null || idDMR.isBlank()) {
            showAlert("Erreur", "Veuillez entrer un numéro de DMR.");
            return;
        }

        dmr = new Dmr();
        Map<String, List<Examen>> resultats = dmr.AfficherCompteRendu(idDMR);
        examensList.clear();
        examensList.addAll(resultats.get("SansCompteRendu"));
        examensList.addAll(resultats.get("AvecCompteRendu"));
    }

    private void ouvrirPageExamen(Examen examen) {
        new pageExamen(examen).show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

