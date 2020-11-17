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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author renan
 */
@Entity
@Table(name = "Exemplar")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idExemplar")
    private int idExemplar;
    @ManyToOne(targetEntity = Acervo.class, cascade = CascadeType.MERGE)
    private Acervo idAcervo;
    @Column(name = "etiqueta")
    private String etiqueta;
    @Column(name = "quantdisponivel")
    private int quantdisponivel;
    @Column(name = "quantlocado")
    private int quantlocado;
    @ManyToMany(mappedBy = "idExemplar")
    List<Emprestimo> idEmprestimo;

    public Exemplar() {
    }

    public Exemplar(int idExemplar, Acervo idAcervo, String etiqueta, int quantdisponivel, int quantlocado) {
        this.idExemplar = idExemplar;
        this.idAcervo = idAcervo;
        this.etiqueta = etiqueta;
        this.quantdisponivel = quantdisponivel;
        this.quantlocado = quantlocado;
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(int idExemplar) {
        this.idExemplar = idExemplar;
    }

    public Acervo getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(Acervo idAcervo) {
        this.idAcervo = idAcervo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getQuantdisponivel() {
        return quantdisponivel;
    }

    public void setQuantdisponivel(int quantdisponivel) {
        this.quantdisponivel = quantdisponivel;
    }

    public int getQuantlocado() {
        return quantlocado;
    }

    public void setQuantlocado(int quantlocado) {
        this.quantlocado = quantlocado;
    }

    @Override
    public String toString() {
        return "▒ " + etiqueta + " ▒\n" + idAcervo.getNome() + "\n" + "Disponível: " + quantdisponivel + " - Locado: " + quantlocado;
    }

}
