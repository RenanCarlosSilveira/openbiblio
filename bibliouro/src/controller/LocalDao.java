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
import model.Pessoa;
import model.Prateleira;
import model.Unidade;

public class LocalDao {

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

    public List<Prateleira> getLocais(String nome) {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Prateleira> query = builder.createQuery(Prateleira.class);
        Root<Prateleira> root = query.from(Prateleira.class);
        try {
            Path<String> nomePath = root.<String>get("codigoPE");
            Predicate nomeIgual = builder.like(nomePath, "%" + nome + "%");
            query.where(nomeIgual);
            TypedQuery<Prateleira> typedQuery = this.getManager().createQuery(query);
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
