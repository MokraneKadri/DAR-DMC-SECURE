package fr.upmc.dar.backend;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.upmc.dar.api.YelpBusinessSearch;
import fr.upmc.dar.dao.EMF;
import fr.upmc.dar.entities.Business;

public class UpdateBusinessHourJob implements Job {


	Business dao=new Business();
	@Override
	public void execute(JobExecutionContext context)

			throws JobExecutionException {
		/*Init Entity Manager*/
		EntityManager em=EMF.getEntityManagerFactory().createEntityManager();


		/*Updating Business hours in database*/

		int offset = 0;

		List<Business> bs;
		/*We get 100 Business by 100 Business to limit memory usage if the database is big*/
		while ((bs = getBusinessIteration(em,offset, 100)).size() > 0)
		{
			List<String> ids=new ArrayList<String>();
			for (Business b : bs)
				ids.add(b.getId());

			try{
				List<Business> toUpdate=YelpBusinessSearch.idsToBusiness(ids);
				em.getTransaction().begin();
				for(Business b : toUpdate)

					em.merge(b);
				em.getTransaction().commit();
				offset += bs.size();
			}catch(Exception e){em.close();};
		}
		/*End of Request*/
		em.close();
	}

	private List<Business> getBusinessIteration(EntityManager entityManager,int offset, int max)
	{
		return entityManager.createQuery("FROM Business m", Business.class).setFirstResult(offset).setMaxResults(max).getResultList();
	}
}
