/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAutor")
    int idAutor;
    @Column(name = "nome")
    private String nome;
    @Column(name = "nacionalidade")
    private String nacionalidade;
    @ManyToMany(mappedBy = "idAutor")
    List<Acervo> idAcervo;

    public Autor() {
    }

    public Autor(int idAutor, String nome, String nacionalidade, List<Acervo> idAcervo) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idAcervo = idAcervo;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Acervo> getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(List<Acervo> idAcervo) {
        this.idAcervo = idAcervo;
    }

    @Override
    public String toString() {
        return nome + "\nNacionalidade: " + nacionalidade;
    }

}
