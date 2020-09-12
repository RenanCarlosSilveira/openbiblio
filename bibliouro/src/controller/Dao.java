/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.persistence.EntityManager;
import util.hibernate.HibernateJPAUtil;

/**
 *
 * @author renan
 */
public class Dao {
        private EntityManager manager = new HibernateJPAUtil().getEntityManager();

    public EntityManager getManager() {
        return manager;
    }

    public void salvar(Object objeto) {
        this.getManager().getTransaction().begin();
        this.getManager().persist(objeto);
        this.getManager().getTransaction().commit();
    }

    public void remover(Object objeto) {
        this.getManager().getTransaction().begin();
        this.getManager().remove(objeto);
        this.getManager().getTransaction().commit();
    }
}
