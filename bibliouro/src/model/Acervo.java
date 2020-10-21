/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "Acervo")
public class Acervo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAcervo")
    private int idAcervo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "totalexemplares")
    private int totalexemplares;
    @Column(name = "chamada")
    private String chamada;
    @Column(name = "baixado")
    private String baixado;
    @Column(name = "edicao")
    private int edicao;
    @Column(name = "ano")
    private int ano;
    @Column(name = "status")
    private String status;
    //@JoinColumn(name = "idTipoAcervo", nullable = false)
    @OneToOne(targetEntity = TipoAcervo.class, cascade = CascadeType.MERGE)
    private TipoAcervo idTipoAcervo;
    @OneToOne(targetEntity = Prateleira.class, cascade = CascadeType.MERGE)
    private Prateleira idPrateleira;
    //@OneToOne(targetEntity = ItemDevolucaoCompra.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@ManyToOne(targetEntity = Autor.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToMany//(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "acervo_autor",
            joinColumns = {
                @JoinColumn(name = "idAcervo")},
            inverseJoinColumns = {
                @JoinColumn(name = "idAutor")}
    )
    List<Autor> idAutor;

    public Acervo() {
    }

    public Acervo(int idAcervo, String nome, int totalexemplares, String chamada, String baixado, int edicao, int ano, String status, TipoAcervo idTipoAcervo, Prateleira idPrateleira, List<Autor> idAutor) {
        this.idAcervo = idAcervo;
        this.nome = nome;
        this.totalexemplares = totalexemplares;
        this.chamada = chamada;
        this.baixado = baixado;
        this.edicao = edicao;
        this.ano = ano;
        this.status = status;
        this.idTipoAcervo = idTipoAcervo;
        this.idPrateleira = idPrateleira;
        this.idAutor = idAutor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(int idAcervo) {
        this.idAcervo = idAcervo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotalexemplares() {
        return totalexemplares;
    }

    public void setTotalexemplares(int totalexemplares) {
        this.totalexemplares = totalexemplares;
    }

    public String getChamada() {
        return chamada;
    }

    public void setChamada(String chamada) {
        this.chamada = chamada;
    }

    public String getBaixado() {
        return baixado;
    }

    public void setBaixado(String baixado) {
        this.baixado = baixado;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public TipoAcervo getIdTipoAcervo() {
        return idTipoAcervo;
    }

    public void setIdTipoAcervo(TipoAcervo idTipoAcervo) {
        this.idTipoAcervo = idTipoAcervo;
    }

    public Prateleira getIdPrateleira() {
        return idPrateleira;
    }

    public void setIdPrateleira(Prateleira idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public List<Autor> getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(List<Autor> idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "▒ " + nome + " ▒\nAno: " + ano + " - Edicao: " + edicao + " - Tipo: " + idTipoAcervo + "\nLocal: " + idPrateleira + "\nAutores: " + idAutor;
    }

}
