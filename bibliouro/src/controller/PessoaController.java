/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Unidade;

/**
 * FXML Controller class
 *
 * @author renan
 */
public class PessoaController implements Initializable {

    Pessoa pesglobal = new Pessoa();
    PessoaDao dao = new PessoaDao();

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
    @FXML
    private Text l_matricula;
    @FXML
    private ListView<Pessoa> list_pessoas;
    @FXML
    private ImageView b_editar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        c_termoconsulta.getSelectionModel().select(0);
    }

    void busca_unidade() {
        Unidade unidade = new Unidade();
        ConfiguracaoDao dao = new ConfiguracaoDao();
        unidade = dao.getNomeUnidade();
        try {
            if (unidade.getEscola() == 1) {
                System.out.println(unidade.getEscola());
                System.out.println("É escola");
                t_matricula.setVisible(true);
                t_matricula.setEditable(true);
                l_matricula.setVisible(true);
            }
            if (unidade.getEscola() != 1) {
                System.out.println("NÃO é escola");
                t_matricula.setVisible(false);
                t_matricula.setEditable(false);
                l_matricula.setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void closeview(MouseEvent event
    ) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        this.dao.closedb();
        this.pesglobal = null;
        stage.close();
    }

    @FXML
    private void on_buscarpessoa(MouseEvent event) throws IOException {
        if (!t_consulta.getText().equals("") || !t_consulta.getText().equals(null)) {
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 0) {
                //PessoaDao dao = new PessoaDao();
                System.out.println("selecionou por nome");
                ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
                pessoas.clear();
                list_pessoas.setItems(null);
                for (Pessoa c : dao.getPessoasNome(t_consulta.getText())) {
                    pessoas.add(c);
                    System.out.println(pessoas);
                    list_pessoas.setItems(pessoas);
                }
            }
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                //PessoaDao dao = new PessoaDao();
                System.out.println("selecionou por cpf");
                ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
                pessoas.clear();
                list_pessoas.setItems(null);
                for (Pessoa c : dao.getPessoasCpf(Integer.valueOf(t_consulta.getText()))) {
                    pessoas.add(c);
                    System.out.println(pessoas);
                    list_pessoas.setItems(pessoas);
                }

            } else if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 0
                    && c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                //JOptionPane.showMessageDialog(null, "Selecione o filtro!", "", JOptionPane.INFORMATION_MESSAGE);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Selecione o filtro!", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }
            System.out.println(c_termoconsulta.getSelectionModel().getSelectedIndex());

        } else {
            //JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(loader.load()));
            MensagemController controller = loader.getController();
            controller.initData("Digite o termo de Pesquisa!", "A");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        }

        t_consulta.setText("");
    }

    @FXML
    private void on_salvar(MouseEvent event) throws IOException {
        if (t_id.getText().equals(null) || t_id.getText().equals("")) {
            if ((t_nome.getText().equals(null) || t_cpf.getText().equals(null)) || (t_nome.getText().equals("") || t_cpf.getText().equals(""))) {
                //JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (nome e cpf)", "", JOptionPane.INFORMATION_MESSAGE);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Mensagem.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setScene(new Scene(loader.load()));
                MensagemController controller = loader.getController();
                controller.initData("Preencha as informações obrigatorias (nome e cpf)", "A");
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } else {
                //PessoaDao dao = new PessoaDao();
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(t_nome.getText());
                pessoa.setCpf(Integer.valueOf(t_cpf.getText()));
                pessoa.setNome(t_nome.getText());
                pessoa.setEmail(t_email.getText());
                pessoa.setTelefone(Integer.valueOf(t_telefone.getText()));
                pessoa.setNascimento(Date.valueOf(t_nascimento.getValue()));
                pessoa.setRua(t_rua.getText());
                pessoa.setBairro(t_bairro.getText());
                pessoa.setNumero(t_numero.getText());
                if (!t_matricula.getText().equals("")) {
                    pessoa.setMatricula(Integer.valueOf(t_matricula.getText()));
                }
                dao.salvar(pessoa);
                System.out.println("SAVE");
            }
        } else {
            this.pesglobal.setNome(t_nome.getText());
            this.pesglobal.setCpf(Integer.valueOf(t_cpf.getText()));
            this.pesglobal.setNome(t_nome.getText());
            this.pesglobal.setEmail(t_email.getText());
            this.pesglobal.setTelefone(Integer.valueOf(t_telefone.getText()));
            this.pesglobal.setNascimento(Date.valueOf(t_nascimento.getValue()));
            this.pesglobal.setRua(t_rua.getText());
            this.pesglobal.setBairro(t_bairro.getText());
            this.pesglobal.setNumero(t_numero.getText());
            if (!t_matricula.getText().equals("")) {
                this.pesglobal.setMatricula(Integer.valueOf(t_matricula.getText()));
            }
            dao.salvar(this.pesglobal);
            System.out.println("UPDT");
        }
        t_matricula.setText("");
        t_nome.setText("");
        //t_nascimento.("");
        t_cpf.setText("");
        t_email.setText("");
        t_telefone.setText("");
        t_bairro.setText("");
        t_rua.setText("");
        t_numero.setText("");
        t_nascimento.setValue(null);
        t_matricula.setEditable(false);
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
    private void on_remover(MouseEvent event
    ) {
        int reply = JOptionPane.showConfirmDialog(null, "Certeza que deseja remover?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            dao.remover(this.pesglobal);
            t_matricula.setText("");
            t_nome.setText("");
            //t_nascimento.("");
            t_cpf.setText("");
            t_email.setText("");
            t_telefone.setText("");
            t_bairro.setText("");
            t_rua.setText("");
            t_numero.setText("");
            t_nascimento.setValue(null);
            t_matricula.setEditable(false);
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
            System.out.println("RMV");
        } else {
            System.out.println("NO RMV");
        }
    }

    @FXML
    private void on_add(MouseEvent event
    ) {
        System.out.println("ADD");
        busca_unidade();
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

    @FXML
    private void on_editar(MouseEvent event) throws IOException {
        if (list_pessoas.getSelectionModel().getSelectedItem() != null) {
            pesglobal = list_pessoas.getSelectionModel().getSelectedItem();
            //int index = ListViewItem.Index;
            System.out.println(pesglobal);
            t_nome.setText(pesglobal.getNome());
            t_cpf.setText(String.valueOf(pesglobal.getCpf()));
            t_email.setText(pesglobal.getEmail());
            t_id.setText(String.valueOf(pesglobal.getIdPessoa()));
            t_matricula.setText(String.valueOf(pesglobal.getMatricula()));
            //LocalDate localDate = Date.valueOf(pesglobal.getNascimento()).toLocalDate();
            LocalDate localDate = Date.valueOf(String.valueOf(pesglobal.getNascimento())).toLocalDate().plusDays(1); //+1 é gambiarra, ver futuramente como tratar, pois esta trazendo certo do banco, mas mostrando incorreto no componente com -1 dia
            t_nascimento.setValue(localDate);
            t_numero.setText(pesglobal.getNumero());
            t_rua.setText(pesglobal.getRua());
            t_telefone.setText(String.valueOf(pesglobal.getTelefone()));
            t_bairro.setText(pesglobal.getBairro());
            busca_unidade();
            t_nome.setEditable(true);
            t_nascimento.setEditable(true);
            t_cpf.setEditable(true);
            t_email.setEditable(true);
            t_telefone.setEditable(true);
            t_bairro.setEditable(true);
            t_rua.setEditable(true);
            t_numero.setEditable(true);
            b_rmv.setVisible(true);
            b_save.setVisible(true);
            b_add.setVisible(false);
            list_pessoas.setItems(null);
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
