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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Unidade;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void on_buscarpessoa(MouseEvent event) {
        if (!t_consulta.getText().equals("") || !t_consulta.getText().equals(null)) {
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 0) {
                System.out.println("selecionou por nome");
                ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
                PessoaDao dao = new PessoaDao();
                for (Pessoa c : dao.getPessoasNome(t_consulta.getText())) {
                    pessoas.add(c);
                    System.out.println(pessoas);
                    list_pessoas.setItems(pessoas);
                }
            }
            if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                System.out.println("selecionou por cpf");
                ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();
                PessoaDao dao = new PessoaDao();
                for (Pessoa c : dao.getPessoasCpf(Integer.valueOf(t_consulta.getText()))) {
                    pessoas.add(c);
                    System.out.println(pessoas);
                    list_pessoas.setItems(pessoas);
                }

            } else if (c_termoconsulta.getSelectionModel().getSelectedIndex() == 0
                    && c_termoconsulta.getSelectionModel().getSelectedIndex() == 1) {
                JOptionPane.showMessageDialog(null, "Selecione o filtro!", "", JOptionPane.INFORMATION_MESSAGE);
            }
            System.out.println(c_termoconsulta.getSelectionModel().getSelectedIndex());

        } else {
            JOptionPane.showMessageDialog(null, "Digite o termo de Pesquisa!", "", JOptionPane.INFORMATION_MESSAGE);
        }

        t_consulta.setText("");
    }

    /*ObservableList<Word> wordsList = FXCollections.observableArrayList();
wordsList.add(new Word("First Word", "Definition of First Word");
wordsList.add(new Word("Second Word", "Definition of Second Word");
wordsList.add(new Word("Third Word", "Definition of Third Word");
ListView<Word> listViewOfWords = new ListView<>(wordsList);*/
    @FXML
    private void closeview(MouseEvent event
    ) {
        Stage stage = (Stage) b_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void on_salvar(MouseEvent event
    ) {
        if ((t_nome.getText().equals(null) || t_cpf.getText().equals(null)) || (t_nome.getText().equals("") || t_cpf.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Preencha as informações obrigatorias (nome e cpf)", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            PessoaDao dao = new PessoaDao();
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(t_nome.getText());
            pessoa.setCpf(Integer.valueOf(t_cpf.getText()));
            pessoa.setNome(t_nome.getText());
            pessoa.setEmail(t_email.getText());
            pessoa.setTelefone(Integer.valueOf(t_telefone.getText()));
            //pessoa.setNascimento(t_nascimento.());
            pessoa.setRua(t_rua.getText());
            pessoa.setBairro(t_bairro.getText());
            pessoa.setNumero(t_numero.getText());
            if (!t_matricula.getText().equals("")) {
                pessoa.setMatricula(Integer.valueOf(t_matricula.getText()));
            }
            dao.salvar(pessoa);
            System.out.println("SAVE");

            t_matricula.setText("");
            t_nome.setText("");
            //t_nascimento.("");
            t_cpf.setText("");
            t_email.setText("");
            t_telefone.setText("");
            t_bairro.setText("");
            t_rua.setText("");
            t_numero.setText("");

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

}
