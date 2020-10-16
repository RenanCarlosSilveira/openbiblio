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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Acervo;
import model.Acervo;
import util.HibernateJPAUtil;

/**
 *
 * @author renan
 */
class AcervoDao {

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

    public void remover(Object objeto) {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(objeto);
            this.getManager().getTransaction().commit();
//          this.getManager().close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel remover: " + e);
        }
    }

    public List<Acervo> getAcervosNome(String nome) {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Acervo> query = builder.createQuery(Acervo.class);
        Root<Acervo> root = query.from(Acervo.class);
        try {
            Path<String> nomePath = root.<String>get("nome");
            Predicate nomeIgual = builder.like(nomePath, "%" + nome + "%");
            query.where(nomeIgual);
            TypedQuery<Acervo> typedQuery = this.getManager().createQuery(query);
            return typedQuery.getResultList();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }
}
