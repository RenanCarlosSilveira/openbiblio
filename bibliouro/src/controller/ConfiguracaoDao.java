/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import util.HibernateJPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import model.Estante;
import model.Unidade;

/**
 *
 * @author Eduardo Comin <eduardo.comin@unoesc.edu.br>
 * @since 2018/01
 */
public class ConfiguracaoDao {

    private EntityManager manager = new HibernateJPAUtil().getEntityManager();

    public EntityManager getManager() {
        return manager;
    }

    public void salvar(Object objeto) {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().persist(objeto);
            this.getManager().getTransaction().commit();
            this.getManager().close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel inserir: " + e);
        }
    }

    public void remover(Object objeto) {
        try {
            this.getManager().getTransaction().begin();
            this.getManager().remove(objeto);
            this.getManager().getTransaction().commit();
            this.getManager().close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel remover: " + e);
        }
    }

    public Unidade getNomeUnidade() {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Unidade> query = builder.createQuery(Unidade.class);
        Root<Unidade> root = query.from(Unidade.class);
        try {
            Path<String> nomePath = root.<String>get("idUnidade");
            Predicate nomeIgual = builder.isNotNull(nomePath);
            query.where(nomeIgual);
            TypedQuery<Unidade> typedQuery = this.getManager().createQuery(query);
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public List<Estante> getEstante() {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Estante> query = builder.createQuery(Estante.class);
        Root<Estante> root = query.from(Estante.class);
        try {
            Path<String> nomePath = root.<String>get("idEstante");
            Predicate nomeIgual = builder.isNotNull(nomePath);
            query.where(nomeIgual);
            TypedQuery<Estante> typedQuery = this.getManager().createQuery(query);
            return typedQuery.getResultList();
        } catch (NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

/////////////////////////////////////// DOENCA ///////////////////////////////////////////////////////////////////////    
/*public void incluirTipoAcervo(TipoAcervo doen) {
        try {
            EntityManager em = new HibernateJPAUtil().getEntityManager();
            em.getTransaction().begin();
            em.persist(doen);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Inserido!");
        } catch (Exception e) {
            System.out.println("Nao foi possivel inserir: " + e);
        }
    }*/
}
