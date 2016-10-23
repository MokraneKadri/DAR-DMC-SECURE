package fr.upmc.dar.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.LoginType;
import fr.upmc.dar.tools.PasswordEncryptor;


public class UserDao implements IUserDao{


	//static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAR");
	//static EntityManager em = emf.createEntityManager();
	
	private static final String SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.eMail=:eMail";
	private static final String PARAM_EMAIL           = "eMail";

	private static final String SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.userName=:userName ";
	private static final String PARAM_USERNAME           = "userName";
	
	private static final String SELECT_BY_USERNAME_AND_PASSWORD = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
	private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT u FROM User u WHERE u.eMail=:eMail AND u.password=:password";
	private static final String PARAM_PASS           = "password";
	

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAR");
	
  
	private  EntityManager  em ;
	
	

	// Injection du manager, qui s'occupe de la connexion avec la BDD




	public  UserDao() {
		
		if(em==null)
		em= emf.createEntityManager()  ;
	}







	// Enregistrement d'un nouvel utilisateur
	public void createUser( User utilisateur ) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist( utilisateur );
			em.getTransaction().commit();
			
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
			throw new Exception( e );
		}
	}



	// Recherche d'un utilisateur à partir de son adresse email
	public User findUserByEmail( String email ) throws Exception {
		User utilisateur = null;
		Query requete = em.createQuery( SELECT_BY_EMAIL );
		requete.setParameter( PARAM_EMAIL, email );
		try {
			utilisateur = (User) requete.getSingleResult();
		} catch ( NoResultException e ) {
			return null;
		} catch ( Exception e ) {
			throw new Exception( e );
		}
		return utilisateur;
	}

	// Recherche d'un utilisateur à partir de son adresse email
	public User findUserByUserName( String username ) throws Exception {
		User utilisateur = null;
		Query requete = em.createQuery( SELECT_BY_USERNAME );
		requete.setParameter( PARAM_USERNAME, username );
		try {
			utilisateur = (User) requete.getSingleResult();
		} catch ( NoResultException e ) {
			return null;
		} catch ( Exception e ) {
			throw new Exception( e );
		}
		return utilisateur;
	}

	@Override
	public User findUserByCredantials( String login,String passwd,LoginType mailOrUserName) throws Exception {
		User utilisateur = null;
		
		Query requete;
		if(mailOrUserName==LoginType.EMAIL){
		 requete = em.createQuery( SELECT_BY_EMAIL );
		requete.setParameter( PARAM_EMAIL, login );//.setParameter(PARAM_PASS,passwd);
		}
		else {
		requete = em.createQuery( SELECT_BY_USERNAME);
		requete.setParameter( PARAM_USERNAME, login );//.setParameter(PARAM_PASS,passwd);
		
		}
	
		try {
			utilisateur = (User) requete.getSingleResult();
			if(PasswordEncryptor.checkPassword(passwd,utilisateur.getPassword())){
				System.out.println("password matched in dao check");
				return utilisateur;
			}else return null;
		} catch ( NoResultException e ) {
			return null;
		} catch ( Exception e ) {
			throw new Exception( e );
		}
		
	}

	

}
