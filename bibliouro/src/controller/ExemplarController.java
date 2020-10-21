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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        ObservableList<Exemplar> exemplares = FXCollections.observableArrayList();
        exemplares.clear();
        list_exemplares.setItems(null);
        for (Exemplar c : dao.getExemplaresEtiqueta(t_consulta.getText())) {
            exemplares.add(c);
            System.out.println(exemplares);
            list_exemplares.setItems(exemplares);
        }
    }

    @FXML
    private void on_salvar(MouseEvent event
    ) throws IOException {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((t_etiqueta.getText().equals(null) || t_etiqueta.getText().equals(""))) {
                //JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (Nome)", "", JOptionPane.INFORMATION_MESSAGE);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Nome, Chamada, Exemplares, Autores, Status)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                Exemplar exemplar = new Exemplar();
                ExemplarDao dao2 = new ExemplarDao();
                exemplar.setEtiqueta(t_etiqueta.getText());
                exemplar.setIdAcervo(acervosselecionado.get(0));
                exemplar.setQuantdisponivel(acervosselecionado.get(0).getTotalexemplares());
                exemplar.setQuantlocado(0);
                dao2.salvar(exemplar);
                /*acervo.setNome(t_nomeacervo.getText());
                acervo.setAno(Integer.valueOf(t_ano.getText()));
                acervo.setChamada(t_chamada.getText());
                acervo.setEdicao(Integer.valueOf(t_edicao.getText()));
                acervo.setTotalexemplares(Integer.valueOf(t_exemplares.getText()));
                acervo.setStatus(c_status.getValue());
                //acervo.setIdTipoAcervo(c_tipo.getSelectionModel().getSelectedItem());
                acervo.setIdTipoAcervo(c_tipo.getValue());
                //acervo.setIdPrateleira(c_local.getSelectionModel().getSelectedItem());
                acervo.setIdPrateleira(c_local.getValue());
                //ObservableList<Autor> autores = FXCollections.observableArrayList();
                //autores.addAll(this.autoresselecionados);
                /*for (Autor role : autoresselecionados) {
                    Autor attached = this.dao.getManager().getReference(Autor.class, role.getIdAutor());
                    autores.add(attached);
                }
                acervo.setIdAutor(this.autoresselecionados);
                dao2.salvar(acervo);*/
                System.out.println("SAVE");
            }
        } else {
            /*acervoglobal.setNome(t_nomeacervo.getText());
            acervoglobal.setAno(Integer.valueOf(t_ano.getText()));
            acervoglobal.setChamada(t_chamada.getText());
            acervoglobal.setEdicao(Integer.valueOf(t_edicao.getText()));
            acervoglobal.setTotalexemplares(Integer.valueOf(t_exemplares.getText()));
            acervoglobal.setStatus(c_status.getValue());
            acervoglobal.setIdTipoAcervo(c_tipo.getValue());
            acervoglobal.setIdPrateleira(c_local.getValue());
            acervoglobal.setIdAutor(this.autoresselecionados);
            dao.salvar(acervoglobal);*/
            System.out.println("UPDT");
        }
        t_id.setText("");/*
        t_ano.setText("");
        t_chamada.setText("");
        t_edicao.setText("");
        t_exemplares.setText("");
        t_nomeacervo.setText("");
        c_local.getSelectionModel().select(null);
        c_tipo.getSelectionModel().select(null);
        c_status.getSelectionModel().select(null);
        list_autores_selecionados.setItems(null);
        list_autoresselecionar.setItems(null);
        t_ano.setEditable(false);
        t_chamada.setEditable(false);
        t_edicao.setEditable(false);
        t_exemplares.setEditable(false);*/
        t_id.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event
    ) {
        int reply = JOptionPane.showConfirmDialog(null, "Certeza que deseja remover?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            //dao.remover(this.acervoglobal);
            t_id.setText("");
            /*            t_ano.setText("");
            t_chamada.setText("");
            t_edicao.setText("");
            t_exemplares.setText("");

            t_nomeacervo.setText("");
            c_status.getSelectionModel().select(null);
            c_local.getSelectionModel().select(null);
            c_tipo.getSelectionModel().select(null);
            list_autores_selecionados.setItems(null);
            list_autoresselecionar.setItems(null);
            t_ano.setEditable(false);
            t_chamada.setEditable(false);
            t_edicao.setEditable(false);
            t_exemplares.setEditable(false);
            t_nomeacervo.setEditable(false);*/
            t_id.setEditable(false);
            b_rmv.setVisible(false);
            b_add.setVisible(true);
            b_save.setVisible(false);
            System.out.println("RMV");
        } else {
            System.out.println("NO RMV");
            b_rmv.setVisible(false);
            b_add.setVisible(true);
            b_save.setVisible(false);
        }
    }

    @FXML
    private void on_add(MouseEvent event
    ) {
        System.out.println("ADD");
        t_id.setText("");
        t_etiqueta.setEditable(true);
        /*t_ano.setText("");
        t_chamada.setText("");
        t_edicao.setText("");
        t_exemplares.setText("");
        t_nomeacervo.setText("");
        c_status.getSelectionModel().select(null);
        c_local.getSelectionModel().select(null);
        c_tipo.getSelectionModel().select(null);
        list_autores_selecionados.setItems(null);
        list_autoresselecionar.setItems(null);
        t_ano.setEditable(true);
        t_chamada.setEditable(true);
        t_edicao.setEditable(true);
        t_exemplares.setEditable(true);
        t_nomeacervo.setEditable(true);*/
        t_id.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(false);
        b_save.setVisible(true);
    }

    @FXML
    private void on_editar(MouseEvent event
    ) throws IOException {
        if (list_exemplares.getSelectionModel().getSelectedItem() != null) {
            exemplarglobal = list_exemplares.getSelectionModel().getSelectedItem();
            //int index = ListViewItem.Index;
            System.out.println(exemplarglobal);
            //          this.t_id.setText(String.valueOf(exemplarglobal.getClass()));
            /*this.t_nomeacervo.setText(acervoglobal.getNome());
            this.t_chamada.setText(acervoglobal.getChamada());
            this.t_exemplares.setText(String.valueOf(acervoglobal.getTotalexemplares()));
            this.t_edicao.setText(String.valueOf(acervoglobal.getEdicao()));
            this.t_ano.setText(String.valueOf(acervoglobal.getAno()));
            //this.c_status.getSelectionModel().select(acervoglobal.get());
            this.c_status.getSelectionModel().select(acervoglobal.getStatus());
            this.c_tipo.getSelectionModel().select(acervoglobal.getIdTipoAcervo());
            this.c_local.getSelectionModel().select(acervoglobal.getIdPrateleira());
            this.autoresselecionados.clear();
            this.autoresselecionados.addAll(this.acervoglobal.getIdAutor());
            this.list_autores_selecionados.setItems(this.autoresselecionados);*/
            
            t_etiqueta.setEditable(true);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_exemplares.setItems(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Selecione um registro para editar!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        b_rmv.setVisible(true);
        b_save.setVisible(true);
        b_add.setVisible(false);
        list_exemplares.setItems(null);
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
