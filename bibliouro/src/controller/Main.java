/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author toor
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //JOptionPane.showMessageDialog(null, "Olá, bem-vindo!", "Bibliouro", JOptionPane.INFORMATION_MESSAGE);
        FXMLLoader MsgLoader = new FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent root = (Parent) MsgLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        showMessage();
    }
    
    public Stage showMessage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load()));
        MensagemController controller = loader.getController();
        controller.initData("Olá, seja Bem-vindo!", "O");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        return stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

}
