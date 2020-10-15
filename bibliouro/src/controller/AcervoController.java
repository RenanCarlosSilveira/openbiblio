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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class AcervoController implements Initializable {

    @FXML
    private TextField t_consulta;
    private ComboBox<?> c_termoconsulta;
    @FXML
    private Button b_back;
    @FXML
    private Button b_busca;
    @FXML
    private TextField t_codpe;
    @FXML
    private TextField t_id;
    @FXML
    private ComboBox<?> c_estante;
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_save;
    @FXML
    private ImageView b_rmv;
    @FXML
    private ImageView b_editar;
    @FXML
    private ListView<?> list_prateleira;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void on_buscar(MouseEvent event) {
        if (!t_consulta.getText().equals("")) {
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 0) {
                System.out.println("selecionou por barras");
            }
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                System.out.println("selecionou por obra");
            }
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 2) {
                System.out.println("selecionou por autor");
            }
            /*if (c_termoconsulta.getSelectionModel().getSelectedIndex()){
            JOptionPane.showMessageDialog(null, "Selecione o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }*/
        } else {
            JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
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

}
