/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;



/**
 *
 * @author toor
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        JOptionPane.showMessageDialog(null, "Olá, bem-vindo!", "Bibliouro", JOptionPane.INFORMATION_MESSAGE);
        FXMLLoader spinnerSceneLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent root = (Parent) spinnerSceneLoader.load();
        
        MainController ctrlrPointer = (MainController) spinnerSceneLoader.getController();
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}