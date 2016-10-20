package fr.upmc.dar.dao;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;


public class UserDao implements IUserDao{


	//static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAR");
	//static EntityManager em = emf.createEntityManager();
	private static final String SELECT_PAR_EMAIL = "SELECT u FROM Utilisateur u WHERE u.eMail=:email";
	private static final String PARAM_EMAIL           = "email";

	private static final String SELECT_PAR_USERNAME = "SELECT u FROM Utilisateur u WHERE u.userName=:username";
	private static final String PARAM_USERNAME           = "username";

	//private EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAR");
	
    @PersistenceContext( unitName = "DAR" )
	private  EntityManager  em      ;
	
	

	// Injection du manager, qui s'occupe de la connexion avec la BDD




	public  UserDao() {

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
		Query requete = em.createQuery( SELECT_PAR_EMAIL );
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
		Query requete = em.createQuery( SELECT_PAR_USERNAME );
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



}
