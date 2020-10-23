/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
            if ((t_etiqueta.getText().equals(null) || t_etiqueta.getText().equals("")) || acervosselecionado.isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Etiqueta e Acervo Vinculado)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                Exemplar exemplar = new Exemplar();
                ExemplarDao dao2 = new ExemplarDao();
                exemplar.setEtiqueta(t_etiqueta.getText());
                exemplar.setIdAcervo(list_acervoselecionado.getItems().get(0));
                exemplar.setQuantdisponivel(acervosselecionado.get(0).getTotalexemplares());
                exemplar.setQuantlocado(0);
                dao2.salvar(exemplar);
                System.out.println("SAVE");
            }
        } else {
            if ((t_etiqueta.getText().equals(null) || t_etiqueta.getText().equals("")) || acervosselecionado.isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Etiqueta e Acervo Vinculado)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                this.exemplarglobal.setEtiqueta(t_etiqueta.getText());
                this.exemplarglobal.setIdAcervo(acervosselecionado.get(0));
                this.exemplarglobal.setQuantdisponivel(acervosselecionado.get(0).getTotalexemplares());
                this.exemplarglobal.setQuantlocado(Integer.valueOf(t_emprestado.getText()));
                dao.salvar(exemplarglobal);
                System.out.println("UPDT");
            }
        }
        t_id.setText("");
        t_id.setEditable(false);
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event
    ) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        ObservableList<Acervo> acervosremover = FXCollections.observableArrayList();
        acervosremover.addAll(list_acervoselecionado.getItems());
        if (result.get() == ButtonType.OK) {
            if (acervosremover.isEmpty() && Integer.valueOf(t_disponivel.getText()) == 0 && Integer.valueOf(t_emprestado.getText()) == 0) {
                dao.remover(this.exemplarglobal);
                t_id.setText("");
                t_id.setEditable(false);
                b_rmv.setVisible(false);
                b_add.setVisible(true);
                b_save.setVisible(false);
                System.out.println("RMV");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Existem pendências (Quant. disponível, emprestada e Acervo vinculado), verifique!", "E");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }
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
            //System.out.println(exemplarglobal);
            //          this.t_id.setText(String.valueOf(exemplarglobal.getClass()));
            this.t_id.setText(String.valueOf(exemplarglobal.getIdExemplar()));
            this.t_etiqueta.setText(exemplarglobal.getEtiqueta());
            this.t_disponivel.setText(String.valueOf(exemplarglobal.getQuantdisponivel()));
            this.t_emprestado.setText(String.valueOf(exemplarglobal.getQuantlocado()));
            this.t_etiqueta.setText(exemplarglobal.getEtiqueta());
            this.acervo.clear();
            this.acervosselecionado.addAll(this.exemplarglobal.getIdAcervo());
            this.list_acervoselecionado.setItems(this.acervosselecionado);
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
        acervosselecionado.clear();
        this.acervosselecionado.addAll(this.list_acervosselecionar.getSelectionModel().getSelectedItem());
        this.list_acervoselecionado.setItems(this.acervosselecionado);
    }

    @FXML
    private void on_removeacervo(MouseEvent event) {
        this.acervosselecionado.remove(0);
        this.list_acervoselecionado.setItems(this.acervosselecionado);
        System.out.println("RMV ACERV");
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
                    // System.out.println(acervo);
                    this.list_acervosselecionar.setItems(acervo);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
