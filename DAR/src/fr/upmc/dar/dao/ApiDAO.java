package fr.upmc.dar.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import fr.upmc.dar.entities.Business;
import fr.upmc.dar.entities.University;

public class ApiDAO {

	protected EntityManager em;

	public ApiDAO() {
		em = EMF.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public EntityManager getEm(){
		if(em==null || !em.isOpen())
			return EMF.getInstance().getEntityManagerFactory().createEntityManager();
		return em;
	}

	public Business getBusiness(String id) {
		EntityManager en =getEm();
		Business b=null;
		try{
			en.getTransaction().begin();
			b=en.find(Business.class, id);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
		return b;
	}

	public void addBusiness(Business b){
		EntityManager en =getEm();
		try{
			en.getTransaction().begin();
			en.persist(b);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
	}

	public void updateBusiness(Business b){
		EntityManager en =getEm();
		try{
			en.getTransaction().begin();
			en.merge(b);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}

	}

	public University getUniversity(String id) {
		EntityManager en =getEm();
		University b=null;
		try{
			en.getTransaction().begin();
			 b=en.find(University.class, id);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
		return b;
	}

	public void addUniversity(University b){
		EntityManager en =getEm();
		try{

			en.getTransaction().begin();
			en.persist(b);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
	}

	public void updateUniversity(University b){
		EntityManager en =getEm();
		try{
			en.getTransaction().begin();
			en.merge(b);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
	}


}
