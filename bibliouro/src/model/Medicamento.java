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
package MODEL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Renan Silveira
 */
@Entity
@Table(name = "Medicamento")
public class Medicamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idMedicamento;
    String nomeMedicamento;

    public Medicamento(int idMedicamento, String nomeMedicamento) {
        this.idMedicamento = idMedicamento;
        this.nomeMedicamento = nomeMedicamento;
    }

    public Medicamento() {
        
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }


}