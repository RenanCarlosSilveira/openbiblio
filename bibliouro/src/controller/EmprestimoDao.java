/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import model.Emprestimo;
import model.Exemplar;
import org.hibernate.Metamodel;
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

    /*public List<Emprestimo> findComment(String nome) {

        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> criteriaQuery = builder.createQuery(Emprestimo.class);
        Root<Emprestimo> root = criteriaQuery.from(Emprestimo.class);

        Join<Exemplar, Pessoa> join = root.join("idPessoa");

        criteriaQuery.select(root);
        criteriaQuery.where(builder.like(builder.upper(join.get("nome")), nome.toUpperCase().trim())); //IMPORTANTEEEEEE

        //return criteriaQuery.get;
    }*/
    public List<Emprestimo> getEmprestimosPessoa(String nome) {
        CriteriaBuilder cb = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery();
        Root<Emprestimo> ri = cq.from(Emprestimo.class); // joins 
        Join<Emprestimo, Exemplar> h = ri.join("idPessoa");
        cq.select(ri).where(cb.like(h.get("nome"), "%" + nome + "%")).distinct(true);
        Query query = this.getManager().createQuery(cq);
        List<Emprestimo> result = query.getResultList();
        System.out.println(result);
        return result;
    }

    public List<Emprestimo> getEmprestimosAtrasados() {
        CriteriaBuilder criteriaBuilder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> criteriaQuery = criteriaBuilder.createQuery(Emprestimo.class);
        Root<Emprestimo> cont = criteriaQuery.from(Emprestimo.class);
        criteriaQuery.select(cont);
        Predicate predicate = criteriaBuilder.lessThan(cont.<Date>get("datadevolucao"), Date.from(Instant.now()));
        criteriaQuery.where(predicate);
        criteriaQuery.distinct(true);
        criteriaQuery.orderBy(criteriaBuilder.desc(cont.<Date>get("datadevolucao")));
        TypedQuery<Emprestimo> queryDigitada = this.getManager().createQuery(criteriaQuery);
        return queryDigitada.getResultList();
    }

    /*public List<Emprestimo> getEmprestimosAtrasados(Date data) {
        CriteriaBuilder cb = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery();
        Root<Emprestimo> ri = cq.from(Emprestimo.class); // joins 
        Join<Emprestimo, Exemplar> h = ri.join("idPessoa");
        cq.select(ri).where(cb.lessThanOrEqualTo(ri.get("datadevolucao"), "%" + data + "%")).distinct(true);
        Query query = this.getManager().createQuery(cq);
        List<Emprestimo> result = query.getResultList();
        System.out.println(result);
        return result;
    }

    public List<Emprestimo> getEmprestimosPessoa(String nome) {
       // EntityManager em = entityManagerFactory.createEntityManager();
        //CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaBuilder cb = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> query = cb.createQuery(Emprestimo.class);
        Root<Emprestimo> employee = query.from(Emprestimo.class);
        //ListJoin<Emprestimo, Pessoa> tasks = employee.join(Employee_.tasks);
        Join<Pessoa, Emprestimo> tasks = employee.join();
        query.select(employee).where(cb.like(tasks.get("nome"), "%"+nome+"%")).distinct(true);
        
        Path<String> nomePath = employee.<String>get("nome");
            Predicate nomeIgual = cb.like(nomePath, "%" + nome + "%");
            query.where(nomeIgual);
        
        TypedQuery<Emprestimo> typedQuery = this.getManager().createQuery(query);
        System.out.println(typedQuery.getResultList());
        return typedQuery.getResultList();
    }*/

 /*public List<Emprestimo> getEmprestimosPessoa(String nome) {
       // EntityManager em = entityManagerFactory.createEntityManager();
        //CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaBuilder cb = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> query = cb.createQuery(Emprestimo.class);
        Root<Emprestimo> employee = query.from(Emprestimo.class);
        //ListJoin<Emprestimo, Pessoa> tasks = employee.join(Employee_.tasks);
        Join<Pessoa, Emprestimo> tasks = employee.join();
        query.select(employee).where(cb.like(tasks.get("nome"), "%"+nome+"%")).distinct(true);
        
        Path<String> nomePath = employee.<String>get("nome");
            Predicate nomeIgual = cb.like(nomePath, "%" + nome + "%");
            query.where(nomeIgual);
        
        TypedQuery<Emprestimo> typedQuery = this.getManager().createQuery(query);
        System.out.println(typedQuery.getResultList());
        return typedQuery.getResultList();
    }*/
 /*public List<Emprestimo> getEmprestimosPessoa(String pessoa) {
        CriteriaBuilder builder = this.getManager().getCriteriaBuilder();
        CriteriaQuery<Emprestimo> query = builder.createQuery(Emprestimo.class);
        Root<Emprestimo> m = query.from(Emprestimo.class);
        Root<Pessoa> mr = query.from(Pessoa.class);
        Predicate p1 = builder.like(mr.get("idPessoa").get("nome"), "%"+pessoa+"%");
        Predicate criteria = builder.conjunction();
        criteria = builder.and(criteria, p1);
        query.where(criteria);
        query.select(m).distinct(true);
        TypedQuery<Emprestimo> typedQuery = this.getManager().createQuery(query);
        return typedQuery.getResultList();
    }*/
}
