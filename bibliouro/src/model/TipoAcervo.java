/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "TipoAcervo")
public class TipoAcervo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idTipoAcervo")
    private int idTipoAcervo;
    @Column(name = "nomeAcervo")
    private String nome;

    public TipoAcervo(int idTipoAcervo, String nome) {
        this.idTipoAcervo = idTipoAcervo;
        this.nome = nome;
    }

    public TipoAcervo() {
    }

    public int getIdTipoAcervo() {
        return idTipoAcervo;
    }

    public void setIdTipoAcervo(int idTipoAcervo) {
        this.idTipoAcervo = idTipoAcervo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

}
