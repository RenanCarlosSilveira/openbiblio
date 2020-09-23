/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private CheckBox c_escola;
    @FXML
    private TextField t_base;
    @FXML
    private Button b_salvarbase;
    @FXML
    private Button b_salvar;
    @FXML
    private TextField t_nometipo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        busca_unidade();
    }
    
    public void initialize(URL url, ResourceBundle rb, String nomeUnidade) {
        String nomeunidade;
        busca_unidade();
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void on_verificaescola(MouseEvent event) {
    }

    @FXML
    private void on_salvaunidade(MouseEvent event) {
    }


    public void salva_tipo() {
        ConfiguracaoDao dao = new ConfiguracaoDao();
        TipoAcervo tipo = new TipoAcervo();
        tipo.setNome(t_nometipo.getText());
        dao.salvar(tipo);
        System.out.println("Salvo!");
    }

    @FXML
    private void on_salvartipo(ActionEvent event) {
        salva_tipo();
    }
    
    void busca_unidade(){
        Unidade unidade = new Unidade();
        ConfiguracaoDao dao = new ConfiguracaoDao();
        unidade = dao.getNomeUnidade(1);
        l_base.setText(unidade.getNomeUnidade());
        }
    }
