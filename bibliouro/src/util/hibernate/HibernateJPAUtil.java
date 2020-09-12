package util.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("bibliouro");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	} 

}
