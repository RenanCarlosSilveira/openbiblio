/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "Prateleira")
public class Prateleira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idPrateleira")
    private int idPrateleira;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstante", nullable = false)
    private Estante idEstante;
    @Column(name = "codigoPE")
    private String codigoPE;

    public Prateleira() {
    }

    public Prateleira(int idPrateleira, Estante idEstante, String codigoPE) {
        this.idPrateleira = idPrateleira;
        this.idEstante = idEstante;
        this.codigoPE = codigoPE;
    }

    public int getIdPrateleira() {
        return idPrateleira;
    }

    public void setIdPrateleira(int idPrateleira) {
        this.idPrateleira = idPrateleira;
    }

    public Estante getIdEstante() {
        return idEstante;
    }

    public void setIdEstante(Estante idEstante) {
        this.idEstante = idEstante;
    }

    public String getCodigoPE() {
        return codigoPE;
    }

    public void setCodigoPE(String codigoPE) {
        this.codigoPE = codigoPE;
    }

    @Override
    public String toString() {
        return "Identificação: " + codigoPE + " - Estante: "+ idEstante;
    }
}
