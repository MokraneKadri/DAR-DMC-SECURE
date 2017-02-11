package fr.upmc.dar2.backend;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.upmc.dar2.dao.EMF;

public class RemoveOldsEntitiesJob implements Job {
	private String EVENTS_WEEKS_OLD = "DELETE FROM Event events WHERE events.date<:date";
	private String BUSINESS_WEEKS_OLD = "DELETE FROM Business b WHERE b.date<:date AND (NOT EXISTS FROM Event e WHERE e.business=b)";
	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
		/*Init Entity Manager*/
		System.out.println("----------");
		System.out.println("Old entities updating");
		EntityManager em = EMF.getInstance().getEntityManagerFactory().createEntityManager();
		
		
		/*Removing Events which are X days old and ended*/
		removeOldEvent(em,2);
		
		/*Removing Business Data wich are 1 month old and not used */
		removeOldBusiness(em,4);
		
		/*End of Request*/
		em.close();
	}
	
	public void removeOldEvent(EntityManager em,int days){
		long now = System.currentTimeMillis();
		long minus = now - (days * 24L * 60L * 60L * 1000L);
		Timestamp date = new Timestamp(minus);
		try{
			em.getTransaction().begin();
			em.createQuery(EVENTS_WEEKS_OLD).setParameter("date",date).executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){System.out.println("Erreur : Retrait des vieux Events");};
	}
	
	public void removeOldBusiness(EntityManager em,int Weeks){
		long now = System.currentTimeMillis();
		long minus = now - (Weeks * 7L * 24L * 60L * 60L * 1000L);
		Timestamp date = new Timestamp(minus);
		try{
			em.getTransaction().begin();
			em.createQuery(BUSINESS_WEEKS_OLD).setParameter("date",date).executeUpdate();
			em.getTransaction().commit();
		}catch(Exception e){System.out.println("Erreur : Retrait des vieux Business");};
	}
}
