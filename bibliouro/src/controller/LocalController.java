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

    Prateleira pratglobal = new Prateleira();
    LocalDao dao = new LocalDao();

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
    @FXML
    private ImageView b_editar;

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
        for (Estante c : dao.getEstantes()) {
            estantes.add(c);
            System.out.println(estantes);
            c_estante.setItems(estantes);
        }
    }

    @FXML
    private void closeview(MouseEvent event
    ) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        this.dao.closedb();
        this.pratglobal = null;
        stage.close();
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        //if (!t_consulta.getText().equals("")) {
        try {
            ObservableList<Prateleira> prateleiras = FXCollections.observableArrayList();
            prateleiras.clear();
            list_prateleira.setItems(null);
            for (Prateleira c : dao.getLocais(t_consulta.getText())) {
                prateleiras.add(c);
                System.out.println(prateleiras);
                list_prateleira.setItems(prateleiras);
            }
        } catch (Exception e) {
            throw e;
        }
        //} else {
        //  JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        //}
    }

    @FXML
    private void on_salvar(MouseEvent event
    ) {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((t_codpe.getText().equals(null) || t_codpe.getText().equals(null)) || (t_codpe.getText().equals("") || t_codpe.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (Identificação)", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                LocalDao dao = new LocalDao();
                Prateleira prateleira = new Prateleira();
                prateleira.setCodigoPE(t_codpe.getText());
                prateleira.setIdEstante(c_estante.getValue());
                dao.salvar(prateleira);
                System.out.println("SAVE");
            }
        } else {
            this.pratglobal.setCodigoPE(t_codpe.getText());
            Estante est = new Estante();
            est = c_estante.getSelectionModel().getSelectedItem();
            this.pratglobal.setIdEstante(est);
            this.dao.salvar(this.pratglobal);
            System.out.println("UPDT");
        }
        t_id.setText("");
        t_codpe.setText("");
        t_codpe.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event
    ) {
        int reply = JOptionPane.showConfirmDialog(null, "Certeza que deseja remover?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            dao.remover(this.pratglobal);
            t_codpe.setText("");
            t_codpe.setEditable(false);
            b_rmv.setVisible(false);
            b_add.setVisible(true);
            b_save.setVisible(false);
            System.out.println("RMV");
        } else {
            System.out.println("NO RMV");
        }
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

    @FXML
    private void on_editar(MouseEvent event
    ) {
        if (list_prateleira.getSelectionModel().getSelectedItem() != null) {
            pratglobal = list_prateleira.getSelectionModel().getSelectedItem();
            //int index = ListViewItem.Index;
            System.out.println(pratglobal);
            this.t_id.setText(String.valueOf(pratglobal.getIdPrateleira()));
            this.t_codpe.setText(pratglobal.getCodigoPE());
            c_estante.getSelectionModel().select(pratglobal.getIdEstante());
            t_codpe.setEditable(true);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_prateleira.setItems(null);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para editar!", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
