/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import model.Acervo;
import model.Autor;
import model.Emprestimo;
import model.Exemplar;
import model.Pessoa;
import model.Prateleira;
import model.TipoAcervo;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class EmprestimoController implements Initializable {

    Emprestimo emprestimoglobal = new Emprestimo();
    EmprestimoDao dao = new EmprestimoDao();

    Pessoa pessoaglobal = new Pessoa();
    ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
    ObservableList<Pessoa> pessoaselecionada = FXCollections.observableArrayList();
    PessoaDao daopessoa = new PessoaDao();

    ObservableList<Exemplar> exemplares = FXCollections.observableArrayList();
    ObservableList<Exemplar> exemplaresselecionados = FXCollections.observableArrayList();
    ExemplarDao daoexemplar = new ExemplarDao();

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
    private Text l_exception;
    @FXML
    private ListView<Exemplar> list_exemplares_selecionar;
    @FXML
    private ListView<Exemplar> list_exemplares_selecionados;
    @FXML
    private TextField t_busca_exemplares;
    @FXML
    private ImageView b_add_exemplares;
    @FXML
    private ImageView b_rmv_exemplares;
    @FXML
    private ListView<Pessoa> list_pessoas_selecionar;
    @FXML
    private ListView<Pessoa> list_pessoa_selecionada;
    @FXML
    private ImageView b_add_pessoa;
    @FXML
    private ImageView b_rmv_pessoa;
    @FXML
    private TextField t_busca_pessoas;
    @FXML
    private DatePicker d_retirada;
    private DatePicker d_devolução;
    @FXML
    private DatePicker d_devolvido;
    @FXML
    private ListView<Emprestimo> list_emprest;
    @FXML
    private DatePicker d_devolucao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_pessoas_selecionar.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        list_exemplares_selecionar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void on_buscar(MouseEvent event) {
        ObservableList<Emprestimo> emprestimos = FXCollections.observableArrayList();
        emprestimos.clear();
        list_emprest.setItems(null);
        for (Emprestimo c : dao.getEmprestimosPessoa(t_consulta.getText())) {
            emprestimos.addAll(c);
            list_emprest.setItems(emprestimos);
        }
        /*while (!list_emprest.getItems().equals(null)) {

        }*/
    }

    @FXML
    private void closeview(MouseEvent event) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        this.dao.closedb();
        this.emprestimoglobal = null;
        stage.close();
    }

    @FXML
    private void on_salvar(MouseEvent event) throws IOException {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((d_devolucao.getValue().equals(null) || d_devolucao.getValue().equals("")) || (d_retirada.getValue().equals(null) || d_retirada.getValue().equals("")) || exemplaresselecionados.isEmpty() || pessoaselecionada.isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Datas, Pessoa, Exemplar)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                //Emprestimo emprestimo = new Emprestimo();
                //EmprestimoDao dao = new EmprestimoDao();
                this.emprestimoglobal.setDatadevolucao(Date.valueOf(d_devolucao.getValue()));
                this.emprestimoglobal.setDataretirada(Date.valueOf(d_retirada.getValue()));
                this.emprestimoglobal.setIdExemplar(this.list_exemplares_selecionados.getItems());
                this.emprestimoglobal.setIdPessoa(pessoaglobal);
                this.dao.salvar(emprestimoglobal);
                System.out.println("SAVE");
            }
        } else {
            /*if ((t_nomeacervo.getText().equals(null) || t_nomeacervo.getText().equals(""))
                    || t_chamada.getText().equals(null) || t_chamada.getText().equals("")
                    || t_exemplares.getText().equals(null) || t_exemplares.getText().equals("")
                    //|| c_status.getSelectionModel().getSelectedItem().toString().equals(null) || c_status.getSelectionModel().getSelectedItem().toString().equals("")
                    || autoresselecionados.isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (Nome, Chamada, Exemplares, Autores, Status)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                acervoglobal.setNome(t_nomeacervo.getText());
                acervoglobal.setAno(Integer.valueOf(t_ano.getText()));
                acervoglobal.setChamada(t_chamada.getText());
                acervoglobal.setEdicao(Integer.valueOf(t_edicao.getText()));
                acervoglobal.setTotalexemplares(Integer.valueOf(t_exemplares.getText()));
                acervoglobal.setStatus(c_status.getValue());
                acervoglobal.setIdTipoAcervo(c_tipo.getValue());
                acervoglobal.setIdPrateleira(c_local.getValue());
                acervoglobal.setIdAutor(this.autoresselecionados);
                dao.salvar(acervoglobal);
                System.out.println("UPDT");
            }*/
            System.out.println("FALHOOOOOOU!");
        }
        d_devolucao.setEditable(false);
        d_retirada.setEditable(false);
        d_devolvido.setEditable(false);
        t_busca_exemplares.setEditable(false);
        t_busca_pessoas.setEditable(false);
        list_exemplares_selecionados.setItems(null);
        list_exemplares_selecionar.setItems(null);
        list_pessoa_selecionada.setItems(null);
        list_pessoas_selecionar.setItems(null);
        pessoaselecionada.clear();
        pessoas.clear();
        exemplares.clear();
        exemplaresselecionados.clear();
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
    }

    @FXML
    private void on_remover(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Optional<ButtonType> result = alert.showAndWait();
        ObservableList<Exemplar> exemplaresremover = FXCollections.observableArrayList();
        exemplaresremover.addAll(list_exemplares_selecionados.getItems());
        ObservableList<Pessoa> pessoaremover = FXCollections.observableArrayList();
        pessoaremover.addAll(list_pessoa_selecionada.getItems());
        /*if (result.get() == ButtonType.OK) {
            if (exemplaresremover.isEmpty() && pessoaremover.isEmpty()) {
                dao.remover(this.acervoglobal);
                t_ano.setText("");
                t_chamada.setText("");
                t_edicao.setText("");
                t_exemplares.setText("");
                t_id.setText("");
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
                t_id.setEditable(false);
                t_nomeacervo.setEditable(false);*/
        d_devolucao.setEditable(false);
        d_retirada.setEditable(false);
        d_devolvido.setEditable(false);
        t_busca_exemplares.setEditable(false);
        t_busca_pessoas.setEditable(false);
        list_exemplares_selecionados.setItems(null);
        list_exemplares_selecionar.setItems(null);
        list_pessoa_selecionada.setItems(null);
        list_pessoas_selecionar.setItems(null);
        pessoaselecionada.clear();
        pessoas.clear();
        exemplares.clear();
        exemplaresselecionados.clear();
        b_rmv.setVisible(false);
        b_add.setVisible(true);
        b_save.setVisible(false);
        /*System.out.println("RMV");
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Existem pendências (Autores e Status), verifique!", "E");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }
        } else {
            System.out.println("NO RMV");
        }*/
    }

    @FXML
    private void on_add(MouseEvent event
    ) {
        System.out.println("ADD");
        d_devolucao.setEditable(true);
        d_retirada.setEditable(true);
        d_devolvido.setEditable(false);
        t_busca_exemplares.setEditable(true);
        t_busca_pessoas.setEditable(true);
        b_rmv.setVisible(false);
        b_add.setVisible(false);
        b_save.setVisible(true);
    }

    @FXML
    private void on_editar(MouseEvent event
    ) throws IOException {
        if (list_emprest.getSelectionModel().getSelectedItem() != null) {
            /*acervoglobal = list_acervos.getSelectionModel().getSelectedItem();
            System.out.println(acervoglobal);
            this.t_id.setText(String.valueOf(acervoglobal.getIdAcervo()));
            /*this.t_nomeacervo.setText(acervoglobal.getNome());
            this.t_chamada.setText(acervoglobal.getChamada());
            this.t_exemplares.setText(String.valueOf(acervoglobal.getTotalexemplares()));
            this.t_edicao.setText(String.valueOf(acervoglobal.getEdicao()));
            this.t_ano.setText(String.valueOf(acervoglobal.getAno()));
            this.c_status.getSelectionModel().select(acervoglobal.getStatus());
            this.c_tipo.getSelectionModel().select(acervoglobal.getIdTipoAcervo());
            this.c_local.getSelectionModel().select(acervoglobal.getIdPrateleira());
            this.autoresselecionados.clear();
            this.autoresselecionados.addAll(this.acervoglobal.getIdAutor());
            this.list_autores_selecionados.setItems(this.autoresselecionados);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_acervos.setItems(null);*/
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Selecione um registro para editar!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
        d_devolucao.setEditable(true);
        d_retirada.setEditable(true);
        d_devolvido.setEditable(false);
        t_busca_exemplares.setEditable(true);
        t_busca_pessoas.setEditable(true);
        //b_rmv.setVisible(true);
        b_save.setVisible(true);
        b_add.setVisible(false);
        //list_acervos.setItems(null);
    }

    @FXML
    private void on_lista_exemplares(KeyEvent event
    ) {
        if (t_busca_exemplares.equals(null) || t_busca_exemplares.equals("")) {
            exemplares.clear();
            list_exemplares_selecionar.setItems(null);
        } else {
            try {
                exemplares.clear();
                this.list_exemplares_selecionar.setItems(null);
                for (Exemplar c : daoexemplar.getExemplaresEtiqueta(t_busca_exemplares.getText())) {
                    exemplares.add(c);
                    this.list_exemplares_selecionar.setItems(exemplares);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @FXML
    private void on_lista_pessoas(KeyEvent event
    ) {
        if (t_busca_pessoas.equals(null) || t_busca_pessoas.equals("")) {
            pessoas.clear();
            list_pessoas_selecionar.setItems(null);
        } else {
            try {
                pessoas.clear();
                this.list_pessoas_selecionar.setItems(null);
                for (Pessoa c : daopessoa.getPessoasNome(t_busca_pessoas.getText())) {
                    pessoas.add(c);
                    System.out.println(pessoas);
                    this.list_pessoas_selecionar.setItems(pessoas);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @FXML
    private void on_seleciona_exemplares(MouseEvent event) throws IOException {
        if (this.list_exemplares_selecionar.getSelectionModel().getSelectedItem().getQuantdisponivel() != 0) {
            this.exemplaresselecionados.addAll(this.list_exemplares_selecionar.getSelectionModel().getSelectedItems());
            this.list_exemplares_selecionados.setItems(this.exemplaresselecionados);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Não há exemplares disponíveis para esse acervo!", "E");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }
    }

    @FXML
    private void on_remove_exemplares(MouseEvent eventF) {
        this.exemplaresselecionados.remove(this.list_exemplares_selecionados.getSelectionModel().getSelectedItem());
        this.list_exemplares_selecionados.setItems(this.exemplaresselecionados);
    }

    @FXML
    private void on_seleciona_pessoa(MouseEvent event) {
        this.pessoaselecionada.add(this.list_pessoas_selecionar.getSelectionModel().getSelectedItem());
        this.list_pessoa_selecionada.setItems(this.pessoaselecionada);
        this.pessoaglobal = list_pessoa_selecionada.getItems().get(0);
    }

    @FXML
    private void on_remove_pessoa(MouseEvent event) {
        this.pessoaselecionada.remove(this.list_pessoa_selecionada.getSelectionModel().getSelectedItem());
        this.list_pessoa_selecionada.setItems(this.pessoaselecionada);
        this.pessoaglobal = null;
    }

}
