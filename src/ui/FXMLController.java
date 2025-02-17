/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ui;

import fc.Dmr;
import fc.Examen;
import fc.Pacs;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author valen
 */
public class FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView liste_image = new ListView();

    @FXML
    private ListView liste_dmr = new ListView();

    @FXML
    private ImageView image = new ImageView();

    @FXML
    private TextField fieldDMR = new TextField();

    @FXML
    private TextField fieldSECU = new TextField();

    @FXML
    private TextField fieldNom = new TextField();

    @FXML
    private TextField fieldPre = new TextField();

    @FXML
    private DatePicker fieldDateNaissance = new DatePicker();

    @FXML
    private DatePicker fieldExamen = new DatePicker();

    @FXML
    private ComboBox<Integer> comboBoxH;

    @FXML
    private ComboBox<Integer> comboBoxMin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Pacs p = new Pacs();
        liste_image.setItems(p.ListImage());

        for (int i = 0; i <= 24; i++) {
            comboBoxH.getItems().add(i);
        }

        for (int i = 0; i <= 60; i++) {
            comboBoxMin.getItems().add(i);
        }

        LocalTime currentTime = LocalTime.now();

        int heure = currentTime.getHour();  // Récupère l'heure
        int minute = currentTime.getMinute();

        // Optionnel : définir une valeur par défaut
        comboBoxH.setValue(heure);
        comboBoxMin.setValue(minute);

        liste_image.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) { // Vérifie qu'un élément est sélectionné

                System.out.println("Image sélectionnée : " + newValue);
                actionSurSelection(p.pathWithNom(newValue.toString()));

            }
        });

    }

    private void actionSurSelection(String path) {
        try {
            //Image image2 = new Image("file:" + "src/jpg/abdomen/cor494-i436.jpg");
            Image image2 = new Image(new FileInputStream(path));// Assurez-vous que le chemin est correct
            this.image.setImage(image2);
            this.image.setPreserveRatio(true);
            this.image.setFitWidth(250);
            this.image.setFitHeight(250);

            System.out.println("Image chargée : ");
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'image : " + e.getMessage());
        }

    }

    @FXML
    private void actionRechercher() {
        Dmr dmr = new Dmr();
        System.out.println(fieldDMR.getText());
        List<Dmr> dmrs = dmr.recupererDMR(fieldDMR.getText(), fieldSECU.getText(), fieldNom.getText(), fieldPre.getText(), null);
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Dmr d : dmrs) {
            items.add(d.getId_dmr() + "   Nom: " + d.getNom() + " Prenom : " + d.getPrenom());

        }
        liste_dmr.setItems(items);

        System.out.println("nom : " + dmr.getNom());

    }

    @FXML
    private void actionValider() {

        String imageselect = (String) liste_image.getSelectionModel().getSelectedItems().get(0);

        String dmrselect = (String) liste_dmr.getSelectionModel().getSelectedItems().get(0);

        String input = dmrselect;
        String firstPart = input.split("\\s+")[0]; // Sépare par les espaces et prend le premier élément
        System.out.println(firstPart); // Affiche "2"

        LocalDate selectedDate = fieldExamen.getValue();

        System.out.println(selectedDate);
        int selectedIndexH = comboBoxH.getSelectionModel().getSelectedIndex();

        Integer selectedItemH = comboBoxH.getItems().get(selectedIndexH);
        int selectedIndexM = comboBoxH.getSelectionModel().getSelectedIndex();

        Integer selectedItemM = comboBoxH.getItems().get(selectedIndexM);
        String h = " " + selectedItemH + ":";
        String m = "" + selectedItemM + "";
        String date = selectedDate.toString() + h + m;

        System.out.println("date : " + date);

        if (imageselect == null || dmrselect == null) {
            System.out.println("tous les champs ne sont pas remplis");

        }
        Pacs p = new Pacs();
        String chemin = p.pathWithNom(imageselect);
        System.out.println("id : "+firstPart+" date : "+date+ " chemin : "+chemin);
        Examen ex = new Examen();
        System.out.println("création");
        ex.creerExamen(firstPart, date, chemin);
        

    }

}
