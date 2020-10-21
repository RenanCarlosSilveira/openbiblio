/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Acervo;
import model.Autor;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class AutorController implements Initializable {

    Autor autorglobal = new Autor();
    AutorDao dao = new AutorDao();

    @FXML
    private Button b_back;
    @FXML
    private TextField t_nome;
    @FXML
    private TextField t_id;
    @FXML
    private TextField t_nacionalidade;
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_save;
    @FXML
    private ImageView b_rmv;
    @FXML
    private ImageView b_editar;
    @FXML
    private TextField t_consulta;
    @FXML
    private Button b_busca;
    @FXML
    private ListView<Autor> list_autor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeview(MouseEvent event
    ) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        this.dao.closedb();
        this.autorglobal = null;
        stage.close();
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        //if (!t_consulta.getText().equals("")) {
        try {
            ObservableList<Autor> autores = FXCollections.observableArrayList();
            autores.clear();
            list_autor.setItems(null);
            for (Autor c : dao.getAutores(t_consulta.getText())) {
                autores.add(c);
                System.out.println(autores);
                list_autor.setItems(autores);
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
    ) throws IOException {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((t_nome.getText().equals(null) || t_nome.getText().equals(null)) || (t_nome.getText().equals("") || t_nome.getText().equals(""))) {
                //JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (Nome)", "", JOptionPane.INFORMATION_MESSAGE);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Nome)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                AutorDao dao = new AutorDao();
                Autor autor = new Autor();
                autor.setNome(t_nome.getText());
                autor.setNacionalidade(t_nacionalidade.getText());
                dao.salvar(autor);
                System.out.println("SAVE");
            }
        } else {
            this.autorglobal.setIdAutor(Integer.valueOf(t_id.getText()));
            this.autorglobal.setNome(t_nome.getText());
            this.autorglobal.setNacionalidade(t_nacionalidade.getText());
            Acervo acv = new Acervo();
            //acv = list_autor.getSelectionModel().getSelectedItem();
            this.dao.salvar(this.autorglobal);
            System.out.println("UPDT");
        }
        t_id.setText("");
        t_nome.setText("");
        t_nacionalidade.setText("");
        t_id.setEditable(false);
        t_nome.setEditable(false);
        t_nacionalidade.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event
    ) {
        int reply = JOptionPane.showConfirmDialog(null, "Certeza que deseja remover?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            dao.remover(this.autorglobal);
            t_id.setText("");
            t_nome.setText("");
            t_nacionalidade.setText("");
            t_id.setEditable(false);
            t_nome.setEditable(false);
            t_nacionalidade.setEditable(false);
            b_rmv.setVisible(false);
            b_add.setVisible(true);
            b_save.setVisible(false);
            System.out.println("RMV");
        } else {
            System.out.println("NO RMV");
        }
    }

    @FXML
    private void on_add(MouseEvent event) {
        System.out.println("ADD");
        t_id.setEditable(false);
        t_nome.setEditable(true);
        t_nacionalidade.setEditable(true);
        b_rmv.setVisible(false);
        b_save.setVisible(true);
        b_add.setVisible(false);
    }

    @FXML
    private void on_editar(MouseEvent event) throws IOException {
        if (list_autor.getSelectionModel().getSelectedItem() != null) {
            autorglobal = list_autor.getSelectionModel().getSelectedItem();
            //int index = ListViewItem.Index;
            System.out.println(autorglobal);
            this.t_id.setText(String.valueOf(autorglobal.getIdAutor()));
            this.t_nome.setText(autorglobal.getNome());
            this.t_nacionalidade.setText(autorglobal.getNacionalidade());
            // c_estante.getSelectionModel().select(autorglobal.getIdEstante());
            t_nome.setEditable(true);
            t_nacionalidade.setEditable(true);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_autor.setItems(null);
        } else {
            //JOptionPane.showMessageDialog(null, "Selecione um registro para editar!", "", JOptionPane.INFORMATION_MESSAGE);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Selecione um registro para editar!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

}
