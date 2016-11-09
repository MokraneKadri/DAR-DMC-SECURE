package fr.upmc.dar.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
    private static final EMF singleton = new EMF();

    private EntityManagerFactory emf;

    private EMF() {}

    public static EMF getInstance() {
        return singleton;
    }


    public EntityManagerFactory getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory("DAR");
        }
        return emf;
    }
	
}