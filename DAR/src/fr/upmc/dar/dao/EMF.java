package fr.upmc.dar.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");

	public static EntityManagerFactory getEntityManagerFactory(){
	    return entityManagerFactory;
	} 
}