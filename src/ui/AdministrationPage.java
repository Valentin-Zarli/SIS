package ui;

import fc.Administration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministrationPage extends Stage {

    private Administration admin;

    public AdministrationPage(Administration admin) {
        this.admin = admin;
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/AdministrationPage.fxml"));
            Parent root = loader.load();
            AdministrationPageController controller = loader.getController();
            controller.setAdmin(admin);

            Scene scene = new Scene(root, 800, 600);
            this.setScene(scene);
            this.setTitle("Page Administration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}