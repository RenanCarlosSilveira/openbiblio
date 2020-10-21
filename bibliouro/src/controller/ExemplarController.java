/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Acervo;
import model.Exemplar;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class ExemplarController implements Initializable {

    Exemplar exemplarglobal = new Exemplar();
    ExemplarDao dao = new ExemplarDao();
    ObservableList<Acervo> acervo = FXCollections.observableArrayList();
    ObservableList<Acervo> acervosselecionado = FXCollections.observableArrayList();
    AcervoDao daoacervo = new AcervoDao();

    @FXML
    private Button b_back;
    @FXML
    private TextField t_consulta;
    @FXML
    private Button b_busca;
    @FXML
    private TextField t_etiqueta;
    @FXML
    private TextField t_id;
    @FXML
    private TextField t_disponivel;
    @FXML
    private TextField t_emprestado;
    @FXML
    private ListView<Acervo> list_acervosselecionar;
    @FXML
    private ListView<Acervo> list_acervoselecionado;
    @FXML
    private TextField t_buscaacervo;
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_save;
    @FXML
    private ImageView b_rmv;
    @FXML
    private ImageView b_editar;
    @FXML
    private ListView<Exemplar> list_exemplares;
    @FXML
    private ImageView b_addacervo;
    @FXML
    private ImageView b_rmvacervo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        if (!t_consulta.getText().equals("")) {
            /*if (c_termoconsulta.getSelectionModel().getSelectedIndex()){
            JOptionPane.showMessageDialog(null, "Selecione o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }*/
        } else {
            JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void on_add(MouseEvent event) {
    }

    @FXML
    private void on_salvar(MouseEvent event) {
    }

    @FXML
    private void on_remover(MouseEvent event) {
    }

    @FXML
    private void on_editar(MouseEvent event) {
    }

    @FXML
    private void on_selecionaacervo(MouseEvent event) {
        this.acervosselecionado.addAll(this.list_acervosselecionar.getSelectionModel().getSelectedItems());
        this.list_acervoselecionado.setItems(this.acervosselecionado);
    }

    @FXML
    private void on_removeacervo(MouseEvent event) {
        System.out.println("aqui");
        this.acervosselecionado.remove(this.list_acervosselecionar.getSelectionModel().getSelectedItem());
        this.list_acervoselecionado.setItems(this.acervosselecionado);
        //System.out.println(this.list_autores_selecionados.getItems());
    }

    @FXML
    private void on_listaacervos(KeyEvent event) {
        if (t_buscaacervo.equals(null) || t_buscaacervo.equals(" ") || t_buscaacervo.equals("")) {
            acervo.clear();
            list_acervosselecionar.setItems(null);
        } else {
            try {
                acervo.clear();
                this.list_acervosselecionar.setItems(null);
                for (Acervo c : daoacervo.getAcervosNome(t_buscaacervo.getText())) {
                    acervo.add(c);
                    System.out.println(acervo);
                    this.list_acervosselecionar.setItems(acervo);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
