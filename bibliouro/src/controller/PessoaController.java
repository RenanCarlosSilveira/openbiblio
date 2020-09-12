/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class PessoaController implements Initializable {

    @FXML
    private Text l_base;
    @FXML
    private Button b_back;
    @FXML
    private TextField t_consulta;
    @FXML
    private Button b_busca;
    @FXML
    private ComboBox<?> c_termoconsulta;
    @FXML
    private ListView<?> list_acervos;

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

}
