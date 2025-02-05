package ui;

import fc.Examen;
import fc.Utilisateur;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class pageExamen extends VBox {
    private final Examen examen;

    public pageExamen(Examen examen) {
        this.examen = examen;

        // Configuration de la VBox
        this.setSpacing(20);
        this.setPrefSize(800, 600);

        // Label principal
        Label labelCompteRendu = new Label("Compte rendu");

        // Champs de texte
        TextField textField = new TextField("jTextField1");

        // Section image
        VBox imageSection = new VBox();
        ImageView imageView = new ImageView();
        afficherImage(examen.getImage_path(), imageView);
        imageSection.getChildren().add(imageView);

        // Disposition des composants
        HBox content = new HBox(20, imageSection, new VBox(labelCompteRendu, textField));
        content.setSpacing(20);

        this.getChildren().add(content);
    }

    private void afficherImage(String cheminImage, ImageView imageView) {
        try {
            
            Image image = new Image(new FileInputStream(cheminImage));
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(250);
            imageView.setFitHeight(250);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found: " + cheminImage);
        }
    }
}

