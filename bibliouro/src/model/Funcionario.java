/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package MODEL;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author Renan Silveira
 */
/*
@Entity
@Table(name = "Funcionario")
@Audited
@AuditTable(value = "Log_Func")
public class Funcionario {

    @Id
    @GeneratedValue
    private int idFuncionario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoFuncionario", nullable = false)
    private TipoFuncionario idTipoFuncionario;
    private String nomeFuncionario;
    private String numCpfFuncionario;
    private String emailFuncionario;
    @Temporal(TemporalType.DATE)
    private Date dataNascimentoFuncionario;

    public Funcionario(int idFuncionario, TipoFuncionario idTipoFuncionario, String nomeFuncionario, String numCpfFuncionario, String emailFuncionario, Date dataNascimentoFuncionario) {
        this.idFuncionario = idFuncionario;
        this.idTipoFuncionario = idTipoFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.numCpfFuncionario = numCpfFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.dataNascimentoFuncionario = dataNascimentoFuncionario;
    }

    public Funcionario(TipoFuncionario idTipoFuncionario, String nomeFuncionario, String numCpfFuncionario, String emailFuncionario, Date dataNascimentoFuncionario) {
        this.idTipoFuncionario = idTipoFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.numCpfFuncionario = numCpfFuncionario;
        this.emailFuncionario = emailFuncionario;
        this.dataNascimentoFuncionario = dataNascimentoFuncionario;
    }

    public Funcionario() {

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
*/