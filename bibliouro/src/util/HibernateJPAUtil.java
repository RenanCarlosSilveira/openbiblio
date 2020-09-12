package UTIL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateJPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("hospital");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	} 

}
