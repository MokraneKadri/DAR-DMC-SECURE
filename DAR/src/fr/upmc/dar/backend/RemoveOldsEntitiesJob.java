package fr.upmc.dar.backend;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RemoveOldsEntitiesJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
		throws JobExecutionException {

		System.out.println("Exemple Scheduler / Backend");

	}
}
