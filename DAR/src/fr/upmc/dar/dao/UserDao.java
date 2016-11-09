package fr.upmc.dar.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.LoginType;
import fr.upmc.dar.tools.PasswordEncryptor;


public class UserDao implements IUserDao{

	private static final String SELECT_BY_EMAIL = "SELECT u FROM User u WHERE u.eMail=:eMail";
	private static final String PARAM_EMAIL           = "eMail";

	private static final String SELECT_BY_ID = "SELECT u FROM User u WHERE u.id=:id";
	private static final String PARAM_ID          = "id";

	private static final String SELECT_BY_USERNAME = "SELECT u FROM User u WHERE u.userName=:userName ";
	private static final String PARAM_USERNAME           = "userName";
	
	private static final String SELECT_BY_NAME=  "SELECT u FROM User u WHERE u.name=:name ";
	private static final String PARAM_NAME= "name";
	
	private static final String SELECT_BY_FIRSTNAME=  "SELECT u FROM User u WHERE u.firstName=:firstName ";
	private static final String PARAM_FIRSTNAME= "firstName";

	@SuppressWarnings("unused")
	private static final String SELECT_BY_USERNAME_AND_PASSWORD = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
	@SuppressWarnings("unused")
	private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT u FROM User u WHERE u.eMail=:eMail AND u.password=:password";
	@SuppressWarnings("unused")
	private static final String PARAM_PASS           = "password";


	private  EntityManager  em ;



	// Injection du manager, qui s'occupe de la connexion avec la BDD

	public  UserDao() {
		em = EMF.getInstance().getEntityManagerFactory().createEntityManager();
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

	public User findUserById( int id ) throws Exception {
		User utilisateur = null;
		Query requete = em.createQuery( SELECT_BY_ID );
		requete.setParameter( PARAM_ID, id);
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

	public JSONObject getUsersJSONProfileWhere(String field, String value) {
		if (value == null) {
			return null;
		}

		if (value.equals("")) {
			return null;
		}

		JSONObject usersJSON = new JSONObject();
		JSONArray usersArray = new JSONArray();

		try {


			List<User> users = new ArrayList<User>();

			switch(field){
			case("id"):
				users.add(this.findUserById(Integer.parseInt(value)));
			break;
			case("email"):
				users.add(this.findUserByEmail(value));
			break;
			case("name"):
				users.addAll(this.findUserByName(value));
			break;
			case("firstname"):
				users.addAll(this.findUserByFirstname(value));
			break;
			case("username"):
				users.add(this.findUserByUserName(value));
			break;

			}

			for (User user : users) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("id", user.getId());
				userJSON.put("email", user.geteMail());
				userJSON.put("name", user.getLastName());
				userJSON.put("firstname", user.getFirstName());
				userJSON.put("username", user.getUserName());

				usersArray.put(userJSON);
			}
			usersJSON.put("users", usersArray);

		} catch (Exception e) {
			System.out.println("Error JSONProfil : "+e.toString());
			return usersJSON;
		}

		return usersJSON;
	}

	private Collection<? extends User> findUserByFirstname(String value) throws Exception {
		List<User> utilisateur = null;
		Query requete = em.createQuery( SELECT_BY_FIRSTNAME );
		requete.setParameter( PARAM_FIRSTNAME, value );
		try {
			utilisateur = (List<User>) requete.getResultList();
		} catch ( NoResultException e ) {
			return null;
		} catch ( Exception e ) {
			throw new Exception( e );
		}
		return utilisateur;
	}


	private Collection<? extends User> findUserByName(String value) throws Exception {
		List<User> utilisateur = null;
		Query requete = em.createQuery( SELECT_BY_NAME );
		requete.setParameter( PARAM_NAME, value );
		try {
			utilisateur = (List<User>) requete.getResultList();
		} catch ( NoResultException e ) {
			return null;
		} catch ( Exception e ) {
			throw new Exception( e );
		}
		return utilisateur;
	}







	public JSONObject getUsersJSONProfileFromIds(ArrayList<Integer> ids){

		JSONObject usersJSON = new JSONObject();
		JSONArray usersArray = new JSONArray();

		List<User> users = new ArrayList<User>();
		try {
			for(Integer i : ids)
				users.add(this.findUserById(i));

			for (User user : users) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("id", user.getId());
				userJSON.put("email", user.geteMail());
				userJSON.put("name", user.getLastName());
				userJSON.put("firstname", user.getFirstName());
				userJSON.put("username", user.getUserName());

				usersArray.put(userJSON);
			}
			usersJSON.put("users", usersArray);

		} catch (Exception e) {
			System.out.println("Error JSONProfil : "+e.toString());
			return usersJSON;
		}
		return usersJSON;
	}



}
