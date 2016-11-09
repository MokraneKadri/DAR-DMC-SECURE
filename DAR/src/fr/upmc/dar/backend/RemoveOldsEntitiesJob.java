package fr.upmc.dar.backend;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.upmc.dar.dao.EMF;

public class RemoveOldsEntitiesJob implements Job {
	private String EVENTS_WEEKS_OLD = "DELETE events FROM Event events WHERE events.date<:date";
	private String BUSINESS_WEEKS_OLD = "DELETE b FROM Business b WHERE b.date<:date AND (NOT EXISTS FROM Event e WHERE e.business=b)";
	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
		/*Init Entity Manager*/
		EntityManager em = EMF.getEntityManagerFactory().createEntityManager();
		
		
		/*Removing Events which are 1 month old and ended*/
		removeOldEvent(em,4);
		
		/*Removing Business Data wich are 1 month old and not used */
		removeOldBusiness(em,4);
		
		/*End of Request*/
		em.close();
	}
	
	public void removeOldEvent(EntityManager em,int Weeks){
		long now = System.currentTimeMillis();
		long minus = now - (Weeks * 7L * 24L * 60L * 60L * 1000L);
		Timestamp date = new Timestamp(minus);
		try{
			em.createQuery(EVENTS_WEEKS_OLD).setParameter("date",date).executeUpdate();
		}catch(Exception e){System.out.println("Erreur : Retrait des vieux Events");};
	}
	
	public void removeOldBusiness(EntityManager em,int Weeks){
		long now = System.currentTimeMillis();
		long minus = now - (Weeks * 7L * 24L * 60L * 60L * 1000L);
		Timestamp date = new Timestamp(minus);
		try{
			em.createQuery(BUSINESS_WEEKS_OLD).setParameter("date",date).executeUpdate();
		}catch(Exception e){System.out.println("Erreur : Retrait des vieux Business");};
	}
}
