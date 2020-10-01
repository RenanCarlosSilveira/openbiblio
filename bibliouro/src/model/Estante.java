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
@Table(name = "Estante")
public class Estante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idEstante")
    private int idEstante;
    @Column(name = "codigoEstante")
    private String codigoEstante;

    public Estante() {
    }
    
    public Estante(int idEstante, String codigoEstante) {
        this.idEstante = idEstante;
        this.codigoEstante = codigoEstante;
    }

    public int getIdEstante() {
        return idEstante;
    }

    public void setIdEstante(int idEstante) {
        this.idEstante = idEstante;
    }

    public String getCodigoEstante() {
        return codigoEstante;
    }

    public void setCodigoEstante(String codigoEstante) {
        this.codigoEstante = codigoEstante;
    }



}
