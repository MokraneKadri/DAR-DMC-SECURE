package fr.upmc.dar2.backend;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import fr.upmc.dar2.dao.EMF;
import fr.upmc.dar2.entities.Business;
import fr.upmc.dar2.api.YelpBusinessSearch;

public class UpdateBusinessHourJob implements Job {


	Business dao=new Business();
	@Override
	public void execute(JobExecutionContext context)

			throws JobExecutionException {
		/*Init Entity Manager*/
		EntityManager em= EMF.getInstance().getEntityManagerFactory().createEntityManager();


		/*Updating Business hours in database*/
		System.out.println("----------");
		System.out.println("Business Hour Updating");
		int offset = 0;

		List<Business> bs;
		/*We get 100 Business by 100 Business to limit memory usage if the database is big*/
		while ((bs = getBusinessIteration(em,offset, 100)).size() > 0)
		{
			List<String> ids=new ArrayList<String>();
			for (Business b : bs)
				ids.add(b.getId());

			try{
				List<Business> toUpdate=YelpBusinessSearch.getBusinessFromAPI(ids);
				em.getTransaction().begin();
				for(Business b : toUpdate){
				Business toModify=em.find(Business.class, b.getId());
					toModify.setCl0(b.getCl0());toModify.setOp0(b.getOp0());
					toModify.setCl1(b.getCl1());toModify.setOp1(b.getOp1());
					toModify.setCl2(b.getCl2());toModify.setOp2(b.getOp2());
					toModify.setCl3(b.getCl3());toModify.setOp3(b.getOp3());
					toModify.setCl4(b.getCl4());toModify.setOp4(b.getOp4());
					toModify.setCl5(b.getCl5());toModify.setOp5(b.getOp5());
					toModify.setCl6(b.getCl6());toModify.setOp6(b.getOp6());
					em.merge(toModify);
				}
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
