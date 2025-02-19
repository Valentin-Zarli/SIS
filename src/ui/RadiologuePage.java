/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ui;

import fc.Radiologue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RadiologuePage extends Stage {

    private Radiologue radiologue;

    public RadiologuePage(Radiologue radiologue) {
        this.radiologue = radiologue;
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/RadiologuePage.fxml"));
            RadiologuePageController controller = new RadiologuePageController();

            Parent root = loader.load();

            controller.setRadiologue(radiologue); // Initialisation correcte

            Scene scene = new Scene(root, 800, 600);
            this.setScene(scene);
            this.setTitle("Page Radiologue");
            this.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}