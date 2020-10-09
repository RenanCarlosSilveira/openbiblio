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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Estante;
import model.Prateleira;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class LocalController implements Initializable {

    @FXML
    private Button b_back;
    @FXML
    private TextField t_id;
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_save;
    @FXML
    private ImageView b_rmv;
    @FXML
    private TextField t_consulta;
    @FXML
    private Button b_busca;
    @FXML
    private ListView<Prateleira> list_prateleira;
    @FXML
    private TextField t_codpe;
    @FXML
    private ComboBox<Estante> c_estante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        populacombo();
    }

    public void populacombo() {
        ObservableList<Estante> estantes = FXCollections.observableArrayList();
        ConfiguracaoDao dao = new ConfiguracaoDao();
        for (Estante c : dao.getEstante()) {
            estantes.add(c);
            System.out.println(estantes);
            c_estante.setItems(estantes);
        }
    }

    @FXML
    private void closeview(MouseEvent event
    ) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        if (!t_consulta.getText().equals("")) {
            ObservableList<Prateleira> prateleiras = FXCollections.observableArrayList();
                LocalDao dao = new LocalDao();
                for (Prateleira c : dao.getPessoasNome(t_consulta.getText())) {
                    prateleiras.add(c);
                    System.out.println(prateleiras);
                    list_prateleira.setItems(prateleiras);
                }
        } else {
            JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void on_salvar(MouseEvent event
    ) {
        if ((t_codpe.getText().equals(null) || t_codpe.getText().equals(null)) || (t_codpe.getText().equals("") || t_codpe.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (Identificação)", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            LocalDao dao = new LocalDao();
            Prateleira prateleira = new Prateleira();
            prateleira.setCodigoPE(t_codpe.getText());
            prateleira.setIdEstante(c_estante.getValue());
            dao.salvar(prateleira);
            System.out.println("SAVE");
            t_codpe.setText("");
            t_codpe.setEditable(false);
            b_rmv.setVisible(false);
            b_add.setVisible(true);
            b_save.setVisible(false);
        }
    }

    @FXML
    private void on_remover(MouseEvent event
    ) {
        System.out.println("RMV");
    }

    @FXML
    private void on_add(MouseEvent event
    ) {
        System.out.println("ADD");
        t_codpe.setEditable(true);
        b_rmv.setVisible(false);
        b_save.setVisible(true);
        b_add.setVisible(false);
    }

}
