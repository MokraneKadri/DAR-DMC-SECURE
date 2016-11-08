package fr.upmc.dar.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import fr.upmc.dar.entities.Business;

public class BusinessDAO {

	protected EntityManager entityManager;

	public BusinessDAO() {
		entityManager = Persistence.createEntityManagerFactory("DAR").createEntityManager();
	}

	public Business getBusiness(String id) {
		entityManager.getTransaction().begin();
		Business b=entityManager.find(Business.class, id);
		entityManager.getTransaction().commit();
		return b;
	}
	
	public void addBusiness(Business b){
		entityManager.getTransaction().begin();
		entityManager.persist(b);
		entityManager.getTransaction().commit();
	}
	
	public void updateBusiness(Business b){
		entityManager.getTransaction().begin();
		entityManager.merge(b);
		entityManager.getTransaction().commit();
	}
	
	
}
