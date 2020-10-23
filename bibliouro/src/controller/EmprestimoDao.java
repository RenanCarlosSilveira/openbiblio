/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Emprestimo;
import model.Exemplar;
import model.Pessoa;
import util.HibernateJPAUtil;

/**
 *
 * @author renan
 */
class EmprestimoDao {

    private EntityManager manager = new HibernateJPAUtil().getEntityManager();

    public EntityManager getManager() {
        return manager;
    }

    public void closedb() {
        try {
            this.getManager().close();
            System.out.println("Conexão encerrada");
        } catch (Exception e) {
            System.out.println("Nao foi possivel encerrar conexão: " + e);
        }
    }

    public void salvar(Object objeto) {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().persist(objeto);
            this.getManager().getTransaction().commit();
//            this.getManager().close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel inserir: " + e);
        }
    }

    public List<Emprestimo> getEmprestimosPessoa(String pessoa) {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> query = builder.createQuery(Emprestimo.class);
        Root<Emprestimo> m = query.from(Emprestimo.class);
        Root<Pessoa> mr = query.from(Pessoa.class);
        Predicate p1 = builder.like(mr.get("Pessoa").get("nome"), "%"+pessoa+"%");
        Predicate criteria = builder.conjunction();
        criteria = builder.and(criteria, p1);
        query.where(criteria);
        query.select(m).distinct(true);
        TypedQuery<Emprestimo> typedQuery = this.getManager().createQuery(query);
        return typedQuery.getResultList();
    }

}
