/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Acervo;
import model.Autor;
import model.Prateleira;
import model.TipoAcervo;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class AcervoController implements Initializable {

    private Acervo acervoglobal = new Acervo();
    AcervoDao dao = new AcervoDao();
    ObservableList<Autor> autoresselecionados = FXCollections.observableArrayList();

    @FXML
    private TextField t_consulta;
    private ComboBox<?> c_termoconsulta;
    @FXML
    private Button b_back;
    @FXML
    private Button b_busca;
    @FXML
    private TextField t_id;
    @FXML
    private ImageView b_add;
    @FXML
    private ImageView b_save;
    @FXML
    private ImageView b_rmv;
    @FXML
    private ImageView b_editar;
    @FXML
    private TextField t_nomeacervo;
    @FXML
    private TextField t_chamada;
    @FXML
    private TextField t_exemplares;
    @FXML
    private TextField t_edicao;
    @FXML
    private TextField t_ano;
    @FXML
    private TextField t_status;
    @FXML
    private ComboBox<TipoAcervo> c_tipo;
    @FXML
    private ComboBox<Prateleira> c_local;
    @FXML
    private ListView<Autor> list_autoresselecionar;
    @FXML
    private ListView<Autor> list_autores_selecionados;
    @FXML
    private TextField t_busca_autores;
    @FXML
    private ImageView b_add_autores;
    @FXML
    private ImageView b_rmv_autores;
    @FXML
    private ListView<Acervo> list_acervos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populacombo();
        list_autores_selecionados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list_autoresselecionar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        //if (!t_consulta.getText().equals("")) {
            ObservableList<Acervo> acervos = FXCollections.observableArrayList();
            acervos.clear();
            list_acervos.setItems(null);
            for (Acervo c : dao.getAcervosNome(t_consulta.getText())) {
                acervos.add(c);
                System.out.println(acervos);
                list_acervos.setItems(acervos);
            }
            /*if (c_termoconsulta.getSelectionModel().getSelectedIndex()){
            JOptionPane.showMessageDialog(null, "Selecione o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }*/
       // } else {
          //  JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
       // }
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        this.dao.closedb();
        this.acervoglobal = null;
        stage.close();
    }

    @FXML
    private void on_salvar(MouseEvent event
    ) {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((t_nomeacervo.getText().equals(null) || t_nomeacervo.getText().equals(null)) || (t_nomeacervo.getText().equals("") || t_nomeacervo.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (Nome)", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                AcervoDao dao = new AcervoDao();
                Acervo acervo = new Acervo();
                acervo.setNome(t_nomeacervo.getText());
                acervo.setAno(Integer.valueOf(t_ano.getText()));
                acervo.setChamada(t_chamada.getText());
                acervo.setEdicao(Integer.valueOf(t_edicao.getText()));
                acervo.setTotalexemplares(Integer.valueOf(t_exemplares.getText()));
                //acervo.setIdTipoAcervo(c_tipo.getSelectionModel().getSelectedItem());
                //acervo.setIdPrateleira(c_local.getSelectionModel().getSelectedItem());
                ObservableList<Autor> autores = FXCollections.observableArrayList();
                autores.addAll(autoresselecionados);
                acervo.setIdAutor(autores);
                dao.salvar(acervo);
                System.out.println("SAVE");
            }
        } /*else {
            this.autorglobal.setIdAutor(Integer.valueOf(t_id.getText()));
            this.autorglobal.setNome(t_nome.getText());
            this.autorglobal.setNacionalidade(t_nacionalidade.getText());
            Acervo acv = new Acervo();
            //acv = list_autor.getSelectionModel().getSelectedItem();
            this.dao.salvar(this.autorglobal);
            System.out.println("UPDT");
        }*/
        t_ano.setText("");
        t_chamada.setText("");
        t_edicao.setText("");
        t_exemplares.setText("");
        t_id.setText("");
        t_nomeacervo.setText("");
        t_status.setText("");
        c_local.getSelectionModel().select(null);
        c_tipo.getSelectionModel().select(null);
        list_autores_selecionados.setItems(null);
        list_autoresselecionar.setItems(null);
        t_ano.setEditable(false);
        t_chamada.setEditable(false);
        t_edicao.setEditable(false);
        t_exemplares.setEditable(false);
        t_id.setEditable(false);
        t_nomeacervo.setEditable(false);
        t_status.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event
    ) {
        /*int reply = JOptionPane.showConfirmDialog(null, "Certeza que deseja remover?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            dao.remover(this.autorglobal);
        t_ano.setText("");
        t_chamada.setText("");
        t_edicao.setText("");
        t_exemplares.setText("");
        t_id.setText("");
        t_nomeacervo.setText("");
        t_status.setText("");
        c_local.getSelectionModel().select(null);
        c_tipo.getSelectionModel().select(null);
        list_autores_selecionados.setItems(null);
        list_autoresselecionar.setItems(null);
        t_ano.setEditable(false);
        t_chamada.setEditable(false);
        t_edicao.setEditable(false);
        t_exemplares.setEditable(false);
        t_id.setEditable(false);
        t_nomeacervo.setEditable(false);
        t_status.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
            System.out.println("RMV");
        } else {
            System.out.println("NO RMV");
        }*/
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_add(MouseEvent event) {
        System.out.println("ADD");
        t_ano.setText("");
        t_chamada.setText("");
        t_edicao.setText("");
        t_exemplares.setText("");
        t_id.setText("");
        t_nomeacervo.setText("");
        t_status.setText("");
        c_local.getSelectionModel().select(null);
        c_tipo.getSelectionModel().select(null);
        list_autores_selecionados.setItems(null);
        list_autoresselecionar.setItems(null);
        t_ano.setEditable(true);
        t_chamada.setEditable(true);
        t_edicao.setEditable(true);
        t_exemplares.setEditable(true);
        t_id.setEditable(false);
        t_nomeacervo.setEditable(true);
        t_status.setEditable(true);
        b_rmv.setVisible(false);
        b_add.setVisible(false);
        b_save.setVisible(true);
    }

    @FXML
    private void on_editar(MouseEvent event) {
        /*if (list.getSelectionModel().getSelectedItem() != null) {
           // autorglobal = list_autor.getSelectionModel().getSelectedItem();
            //int index = ListViewItem.Index;
            //System.out.println(autorglobal);
            //this.t_id.setText(String.valueOf(autorglobal.getIdAutor()));
            //this.t_nome.setText(autorglobal.getNome());
            //this.t_nacionalidade.setText(autorglobal.getNacionalidade());
            // c_estante.getSelectionModel().select(autorglobal.getIdEstante());
            t_nome.setEditable(true);
            t_nacionalidade.setEditable(true);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_autor.setItems(null);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um registro para editar!", "", JOptionPane.INFORMATION_MESSAGE);
        }*/
        b_rmv.setVisible(true);
        b_save.setVisible(true);
        b_add.setVisible(false);
        list_acervos.setItems(null);
    }

    public void populacombo() {
        ObservableList<Prateleira> prateleiras = FXCollections.observableArrayList();
        LocalDao dao = new LocalDao();
        for (Prateleira c : dao.getLocais("")) {
            prateleiras.add(c);
            System.out.println(prateleiras);
            c_local.setItems(prateleiras);
        }
        ObservableList<TipoAcervo> tipos = FXCollections.observableArrayList();
        ConfiguracaoDao dao2 = new ConfiguracaoDao();
        for (TipoAcervo c : dao2.getTipoAcervo()) {
            tipos.add(c);
            System.out.println(tipos);
            c_tipo.setItems(tipos);
        }
    }

    @FXML
    private void on_listaautores(KeyEvent event) {
        ObservableList<Autor> autores = FXCollections.observableArrayList();
        if (t_busca_autores.equals(null) || t_busca_autores.equals("")) {
            autores.clear();
            list_autoresselecionar.setItems(null);
        } else {
            try {
                AutorDao daoautor = new AutorDao();
                autores.clear();
                list_autoresselecionar.setItems(null);
                for (Autor c : daoautor.getAutores(t_busca_autores.getText())) {
                    autores.add(c);
                    System.out.println(autores);
                    list_autoresselecionar.setItems(autores);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @FXML
    private void on_selecionaautores(MouseEvent event) {
        autoresselecionados.addAll(list_autoresselecionar.getSelectionModel().getSelectedItems());
        list_autores_selecionados.setItems(autoresselecionados);
    }

    @FXML
    private void on_removeautores(MouseEvent event) {
        System.out.println("aqui");
        autoresselecionados.remove(list_autores_selecionados.getSelectionModel().getSelectedItem());
        list_autores_selecionados.setItems(autoresselecionados);
        System.out.println(list_autores_selecionados.getItems());
    }

}
