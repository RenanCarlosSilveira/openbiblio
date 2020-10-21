/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author renan
 */
public class MensagemController implements Initializable {

    @FXML
    private Text t_msg;
    @FXML
    private ImageView t_img;

    String param;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Param O = Ok, A = Aviso, E = Erro, I = Info
    void initData(String msg, String param) {
        t_msg.setText(msg);
        carregaImagem(param);
    }

    void carregaImagem(String param) {
        if (param.equals("O")) {
            t_img.setImage(new Image("util/ok.png"));
        }
        if (param.equals("A")) {
            t_img.setImage(new Image("util/alert.png"));
        }
        if (param.equals("I")) {
            t_img.setImage(new Image("util/info.png"));
        }
        if (param.equals("E")) {
            t_img.setImage(new Image("util/err.png"));
        }
    }

    @FXML
    private void on_closemessage(ActionEvent event) throws IOException {
        Stage stage = (Stage) t_msg.getScene().getWindow();
        stage.close();
    }

}
