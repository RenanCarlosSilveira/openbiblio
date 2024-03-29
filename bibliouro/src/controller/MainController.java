/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author toor
 */
public class MainController implements Initializable {

    @FXML
    private Button b_config;
    @FXML
    private Text l_base;
    @FXML
    private Button b_acervo;
    @FXML
    private Button b_autor;
    @FXML
    private Button b_exemplar;
    @FXML
    private Button b_pessoa;
    @FXML
    private Button b_local;
    @FXML
    private Button b_devolucao;
    @FXML
    private Button b_notificacao;
    @FXML
    private Button b_reserva;
    @FXML
    private Button b_exemplarporacervo;
    @FXML
    private Button b_reservaspendentes;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button b_close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void open_viewacervo(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent myNewScene = null;

        //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
        myNewScene = FXMLLoader.load(getClass().getResource("/view/Acervo.fxml"));
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Acervo");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void open_viewautor(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent myNewScene = null;

        //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
        myNewScene = FXMLLoader.load(getClass().getResource("/view/Autor.fxml"));
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Autor");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void open_viewexemplar(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent myNewScene = null;

        //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
        myNewScene = FXMLLoader.load(getClass().getResource("/view/Exemplar.fxml"));
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Exemplar");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void open_viewpessoa(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent myNewScene = null;

        //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
        myNewScene = FXMLLoader.load(getClass().getResource("/view/Pessoa.fxml"));
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Pessoa");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void open_viewlocal(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent myNewScene = null;

        //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
        myNewScene = FXMLLoader.load(getClass().getResource("/view/Local.fxml"));
        Scene scene = new Scene(myNewScene);
        stage.setScene(scene);
        stage.setTitle("Cadastro de Local");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @FXML
    private void open_viewdevolucao(ActionEvent event) {

    }

    @FXML
    private void open_viewnotificacao(ActionEvent event) {
    }

    @FXML
    private void open_viewreserva(ActionEvent event) {
    }

    @FXML
    private void open_viewexemplarporacervo(ActionEvent event) {
    }

    @FXML
    private void open_viewreservaspendentes(ActionEvent event) {
    }

    @FXML
    private void on_config(MouseEvent event) throws IOException {
        JLabel label = new JLabel("Digite a senha:");
        JPasswordField jpf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "Password:", JOptionPane.OK_CANCEL_OPTION);
        String m = new String(jpf.getPassword());
        if (m.equals("ouro#960523")) {
            Stage stage = new Stage();
            Parent myNewScene = null;
            //stage = New (Stage) objetodatela.getScene().getWindow(); PARA ABRIR EM MESMA TELA
            myNewScene = FXMLLoader.load(getClass().getResource("/view/Configuracao.fxml"));
            Scene scene = new Scene(myNewScene);
            stage.setScene(scene);
            stage.setTitle("Configurações Administrativas");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } else {
            System.out.println(m);
            JOptionPane.showMessageDialog(null, "Senha de administrador incorreta!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void on_close(MouseEvent event) {
        System.exit(0);
    }

}
