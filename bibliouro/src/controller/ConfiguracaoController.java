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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Estante;
import model.TipoAcervo;
import model.Unidade;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class ConfiguracaoController implements Initializable {

    @FXML
    private Text l_base;
    @FXML
    private Button b_back;
    @FXML
    private TextField t_base;
    @FXML
    private Button b_salvar;
    @FXML
    private TextField t_nometipo;
    @FXML
    private CheckBox c_escola;
    @FXML
    private Button b_salvarunidade;

    /**
     * Initializes the controller class.
     */
    Unidade unidade = new Unidade();
    ConfiguracaoDao dao = new ConfiguracaoDao();
    @FXML
    private TextField t_estante;
    @FXML
    private Button b_salvarestante;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unidade = dao.getNomeUnidade();
        busca_unidade();
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void on_salvarestante(ActionEvent event) throws IOException {
        if (!t_estante.getText().equals("")) {
            ConfiguracaoDao dao = new ConfiguracaoDao();
            Estante estante = new Estante();
            estante.setCodigoEstante(t_estante.getText());
            dao.salvar(estante);
            System.out.println("Salvo!");
            t_estante.setText("");
        } else {
            //JOptionPane.showMessageDialog(null, "Favor preencher as informações!", "Bibliouro", JOptionPane.INFORMATION_MESSAGE);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Favor preencher as informações!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

    @FXML
    private void on_salvarunidade(ActionEvent event) throws IOException {
        if (!t_base.getText().equals("")) {
            //ConfiguracaoDao dao = new ConfiguracaoDao();
            Unidade unidade2 = new Unidade();
            unidade2.setNomeUnidade(t_base.getText());
            dao.salvar(unidade2);
            System.out.println("Salvo!");
        } else {
            //JOptionPane.showMessageDialog(null, "Favor preencher as informações!", "Bibliouro", JOptionPane.INFORMATION_MESSAGE);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Favor preencher as informações!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

    void busca_unidade() {
        try {
            l_base.setText(unidade.getNomeUnidade());
            t_base.setText(unidade.getNomeUnidade());
            t_base.setDisable(true);
            if (unidade.getEscola() == 1) {
                System.out.println(unidade.getEscola());
                System.out.println("É escola");
                c_escola.setSelected(true);
            }
            if (unidade.getEscola() != 1) {
                System.out.println("NÃO é escola");
                c_escola.setSelected(false);
            }
        } catch (Exception e) {
            t_base.setText("");
            t_base.setDisable(false);
            l_base.setText("NÃO DEFINIDO!");
            System.out.println(e);
        }
    }

    @FXML
    private void on_verificaescola(MouseEvent event) {
        if (c_escola.isSelected()) {
            unidade.setEscola(1);
            c_escola.setDisable(true);
        }
        if (!c_escola.isSelected()) {
            unidade.setEscola(0);
            c_escola.setDisable(true);
        }
        dao.salvar(unidade);
    }

    @FXML
    private void on_salvartipo(ActionEvent event) throws IOException {
        if (!t_nometipo.equals(null) || t_nometipo.equals("")) {
            ConfiguracaoDao dao = new ConfiguracaoDao();
            TipoAcervo tipo = new TipoAcervo();
            tipo.setNome(t_nometipo.getText());
            dao.salvar(tipo);
            System.out.println("Salvo!");
        } else {
            //JOptionPane.showMessageDialog(null, "Favor preencher as informações!", "Bibliouro", JOptionPane.INFORMATION_MESSAGE);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Favor preencher as informações!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }
}
