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
@Table(name = "Unidade")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUnidade")
    private int idUnidade;
    @Column(name = "nomeUnidade")
    private String nomeUnidade;
    @Column(name = "escola")
    private int escola;

    public Unidade() {
    }

    public Unidade(int idUnidade, String nomeUnidade, int escola) {
        this.idUnidade = idUnidade;
        this.nomeUnidade = nomeUnidade;
        this.escola = escola;
    }

    public int getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public int getEscola() {
        return escola;
    }

    public void setEscola(int escola) {
        this.escola = escola;
    }

}
