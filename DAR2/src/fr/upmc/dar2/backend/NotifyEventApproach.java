package fr.upmc.dar2.backend;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import fr.upmc.dar2.dao.DAOFactory;
import fr.upmc.dar2.dao.EMF;
import fr.upmc.dar2.entities.Event;
import fr.upmc.dar2.entities.User;
import fr.upmc.dar2.tools.MailHelper;

public class NotifyEventApproach implements Job {

	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.NitifyarrivingTommorrow(eventsToComeSoon());
		
		
	}
	
	
	
	List<Event> eventsToComeSoon(){
		//current date
		    Date date = new Date(); // your date
		    Calendar currentCal = Calendar.getInstance();
		    currentCal.setTime(date);
		    int currentYear = currentCal.get(Calendar.YEAR);
		    int currentMonth = currentCal.get(Calendar.MONTH);
		    int currentDday = currentCal.get(Calendar.DAY_OF_MONTH);
		    
		    
		    
		    
		    //tomorrows date
		    Date tomorrow = new Date(); // your date
		    currentCal.setTime(date);
		    int Year = currentCal.get(Calendar.YEAR);
		    int Month = currentCal.get(Calendar.MONTH);
		    int day = currentCal.get(Calendar.DAY_OF_MONTH)+1;
		    
		    Calendar cal =Calendar.getInstance();
		    cal.set(Calendar.YEAR, Year);
		    cal.set(Calendar.MONTH, Month);
		    cal.set(Calendar.DAY_OF_MONTH, day);
		    
		    
		    tomorrow = cal.getTime();
		    
		    
		    List<Event>  res = DAOFactory.createEventDao().getEventsByDate(tomorrow.toString());
		    
		return res;
		
	}
	
	
	
	public void  NitifyarrivingTommorrow(List<Event> events){
		
		String subject="NEW EVENT ARRIVING SOON";
		MailHelper mailer ;
		for(Event e:events){
			for(User u :e.getCandidates()){
				String content="<p>ceci est un mail automatique <p>+"
						+ "<p> Pour rappel Lévénement "+e.getName()+" auquel vous etes inscrit débutera  "+
						e.getDateToString()+"à"+e.getHour()+"<p>";
				mailer= new MailHelper(u.geteMail(), subject, content);
				mailer.sendEmail();
				
			}
		
			
			
			
		}
		
		
		
	}

}
