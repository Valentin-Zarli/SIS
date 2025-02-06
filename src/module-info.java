/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module SIS1 {
    requires javafx.swt;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires java.sql;
    requires java.base;
    opens ui to javafx.graphics;
    exports ui;  // Si ton code UI est dans ce package
    

      // Exporter le package ui si tu veux qu'il soit accessible
    exports fc;  // Exporter le package fc si tu veux qu'il soit accessible
    opens fc to javafx.fxml;
}
