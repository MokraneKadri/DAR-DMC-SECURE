package fr.upmc.dar.dao.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.query.Query;


/**
 * 
 * @author Daniel RADEAU
 *
 * @param <T> Any Entity
 */

public interface IDao<T> {

	/* Beaucoup moins classe ... */
	String persistenceUnitName = "DAR";
	
	/**
	 * Returns the list from the associated entity table in database 
	 * of each entity where the field value is prefixed by a specific prefix.
	 * 
	 * @param entity
	 * @param field
	 * @param prefix
	 * @return
	 */
	
	default List<T> selectTuplesWhereFieldIsPrefixedLike(Class<T> entity, String field, String prefix) {
		String hql = "SELECT entities FROM " + entity.getCanonicalName() + " entities WHERE entities." + field + " LIKE :prefix";
		System.out.println(hql);
		EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		@SuppressWarnings("unchecked")
		Query<T> query = (Query<T>) entityManager.createQuery(hql);
		query.setParameter("prefix", prefix + '%');

		List<T> list = (List<T>) query.getResultList();
		
		return list;
	}
	
	/**
	 * Returns the list from the associated entity table in database 
	 * of each entity where the field value is suffixed by a specific suffix .
	 * 
	 * @param entity
	 * @param field
	 * @param prefix
	 * @return
	 */
	
	default List<T> selectTuplesWhereFieldIsSuffixedLike(Class<T> entity, String field, String suffix) {
		String hql = "SELECT entities FROM " + entity.getCanonicalName() + " entities WHERE entities." + field + " LIKE :suffix";
		System.out.println(hql);
		EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		@SuppressWarnings("unchecked")
		Query<T> query = (Query<T>) entityManager.createQuery(hql);
		query.setParameter("suffix", '%' + suffix);
	
		List<T> list = (List<T>) query.getResultList();
		
		return list;
	}
	
	/**
	 * Returns the list from the associated entity table in database 
	 * of each entity where the field value contains a specific sequence.
	 * 
	 * @param entity
	 * @param field
	 * @param prefix
	 * @return
	 */
	
	default List<T> selectTuplesWhereFieldContainsLike(Class<T> entity, String field, String sequence) {
		String hql = "SELECT entities FROM " + entity.getCanonicalName() + " entities WHERE entities." + field + " LIKE :sequence";
		System.out.println(hql);
		EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		@SuppressWarnings("unchecked")
		Query<T> query = (Query<T>) entityManager.createQuery(hql);
		query.setParameter("sequence", '%' + sequence + '%');
		
		List<T> list = (List<T>) query.getResultList();
		
		return list;
	}
	
	default List<T> selectTuplesWhereFieldIs(Class<T> entity, String field, Object sequence) {
		String hql = "SELECT entities FROM " + entity.getCanonicalName() + " entities WHERE entities." + field + "=:sequence";
		System.out.println(hql);
		EntityManager entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
		@SuppressWarnings("unchecked")
		Query<T> query = (Query<T>) entityManager.createQuery(hql);
		query.setParameter("sequence", sequence);
		
		
		List<T> list = (List<T>) query.getResultList();
		
		return list;
	}
	
}
