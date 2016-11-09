package fr.upmc.dar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import fr.upmc.dar.entities.Business;
import fr.upmc.dar.entities.University;

public class ApiDAO {

	protected EntityManager entityManager;

	public ApiDAO() {
		entityManager = EMF.getEntityManagerFactory().createEntityManager();
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
		entityManager.refresh(b);
		entityManager.getTransaction().commit();

	}

	public University getUniversity(String id) {
		entityManager.getTransaction().begin();
		University b=entityManager.find(University.class, id);
		entityManager.getTransaction().commit();
		return b;
	}

	public void addUniversity(University b){
		entityManager.getTransaction().begin();
		entityManager.persist(b);
		entityManager.getTransaction().commit();
	}

	public void updateUniversity(University b){
		entityManager.getTransaction().begin();
		entityManager.refresh(b);
		entityManager.getTransaction().commit();
	}


}
