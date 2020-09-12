/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Renan Silveira
 */
@Entity
@Table(name = "log_func")
public class FuncionarioAudita {
    @Id
    private int rev;
    private int revtype;
    private int idFuncionario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoFuncionario", nullable = false)
    private TipoFuncionario idTipoFuncionario;
    private String nomeFuncionario;
    private String numCpfFuncionario;
    private String emailFuncionario;
    private Date dataNascimentoFuncionario;

    public FuncionarioAudita() {
    }

    public FuncionarioAudita(int rev, int revtype, int idFuncionario, TipoFuncionario idTipoFuncionario, String nomeFuncionario, String numCpfFuncionario, String emailFuncionario, Date dataNascimentoFuncionario) {
        this.rev = rev;
        this.revtype = revtype;
        this.idFuncionario = idFuncionario;
        this.idTipoFuncionario = idTipoFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.numCpfFuncionario = numCpfFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.dataNascimentoFuncionario = dataNascimentoFuncionario;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public int getRevtype() {
        return revtype;
    }

    public void setRevtype(int revtype) {
        this.revtype = revtype;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public TipoFuncionario getIdTipoFuncionario() {
        return idTipoFuncionario;
    }

    public void setIdTipoFuncionario(TipoFuncionario idTipoFuncionario) {
        this.idTipoFuncionario = idTipoFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNumCpfFuncionario() {
        return numCpfFuncionario;
    }

    public void setNumCpfFuncionario(String numCpfFuncionario) {
        this.numCpfFuncionario = numCpfFuncionario;
    }

    public String getEmailFuncionario() {
        return emailFuncionario;
    }

    public void setEmailFuncionario(String emailFuncionario) {
        this.emailFuncionario = emailFuncionario;
    }

    public Date getDataNascimentoFuncionario() {
        return dataNascimentoFuncionario;
    }

    public void setDataNascimentoFuncionario(Date dataNascimentoFuncionario) {
        this.dataNascimentoFuncionario = dataNascimentoFuncionario;
    }
}
