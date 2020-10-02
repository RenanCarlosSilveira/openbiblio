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
import javafx.scene.control.DatePicker;
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
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_rmv;
    @FXML
    private ImageView b_save;
    @FXML
    private TextField t_nome;
    @FXML
    private DatePicker t_nascimento;
    @FXML
    private TextField t_telefone;
    @FXML
    private TextField t_email;
    @FXML
    private TextField t_cpf;
    @FXML
    private TextField t_matricula;
    @FXML
    private TextField t_rua;
    @FXML
    private TextField t_bairro;
    @FXML
    private TextField t_numero;
    @FXML
    private TextField t_id;

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
                System.out.println("selecionou por nome");
            }
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                System.out.println("selecionou por cpf");
            }
            else{
                JOptionPane.showMessageDialog(null, "Selecione o filtro!", "", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void on_salvar(MouseEvent event) {
        System.out.println("SAVE");
        System.out.println("ADD");
        t_nome.setEditable(false);
        t_nascimento.setEditable(false);       
        t_cpf.setEditable(false);
        t_email.setEditable(false);
        t_telefone.setEditable(false);
        t_bairro.setEditable(false);
        t_rua.setEditable(false);
        t_numero.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
        
    }

    @FXML
    private void on_remover(MouseEvent event) {
        System.out.println("RMV");
    }
    
    @FXML
    private void on_add(MouseEvent event) {
        System.out.println("ADD");
        t_nome.setEditable(true);
        t_nascimento.setEditable(true);       
        t_cpf.setEditable(true);
        t_email.setEditable(true);
        t_telefone.setEditable(true);
        t_bairro.setEditable(true);
        t_rua.setEditable(true);
        t_numero.setEditable(true);
        b_rmv.setVisible(false);
        b_save.setVisible(true);
        b_add.setVisible(false);
    }

}
