/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "Emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idEmprestimo")
    private int idEmprestimo;
    @Temporal(TemporalType.DATE)
    private Date dataretirada;
    @Temporal(TemporalType.DATE)
    private Date datadevolucao;
    @Temporal(TemporalType.DATE)
    private Date datadevolvido;
   // @ManyToMany(targetEntity = Exemplar.class/*, cascade = CascadeType.ALL*/)
   // private List<Exemplar> idExemplar;
    @ManyToMany
    @JoinTable(name = "emprestimo_exemplar", joinColumns = {@JoinColumn(name = "idEmprestimo")}, inverseJoinColumns = {@JoinColumn(name = "idExemplar")})
    List<Exemplar> idExemplar;
    @ManyToOne(targetEntity = Pessoa.class/*, cascade = CascadeType.ALL*/)
    @JoinColumn(/*unique = false*/name="idPessoa", referencedColumnName = "idPessoa")
    @OrderBy("datadevolucao ASC")
    private Pessoa idPessoa;
    

    public Emprestimo() {
    }

    public Emprestimo(int idEmprestimo, Date dataretirada, Date datadevolucao, Date datadevolvido, List<Exemplar> idExemplar, Pessoa idPessoa) {
        this.idEmprestimo = idEmprestimo;
        this.dataretirada = dataretirada;
        this.datadevolucao = datadevolucao;
        this.datadevolvido = datadevolvido;
        this.idExemplar = idExemplar;
        this.idPessoa = idPessoa;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public Date getDataretirada() {
        return dataretirada;
    }

    public void setDataretirada(Date dataretirada) {
        this.dataretirada = dataretirada;
    }

    public Date getDatadevolucao() {
        return datadevolucao;
    }

    public void setDatadevolucao(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }

    public Date getDatadevolvido() {
        return datadevolvido;
    }

    public void setDatadevolvido(Date datadevolvido) {
        this.datadevolvido = datadevolvido;
    }

    public List<Exemplar> getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(List<Exemplar> idExemplar) {
        this.idExemplar = idExemplar;
    }

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }


    @Override
    public String toString() {
        return  "Pessoa: "+ idPessoa.getNome() + "\nRetirada: " + dataretirada + " - Prevista: " + datadevolucao; ////
    }
}
