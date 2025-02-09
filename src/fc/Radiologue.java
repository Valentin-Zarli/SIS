/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 *
 * @author valen
 */
public class Radiologue extends Utilisateur {

    private VBox root;

    public Radiologue(String id, String Mot_de_passe) {
        super(id, Mot_de_passe, 1);
        this.root = new VBox();
        Label welcomeLabel = new Label("Bienvenue, " + this.getNom());
        root.getChildren().add(welcomeLabel);
    }

    
}

    
    
    
    

