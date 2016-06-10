package com.laviton.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum PersistanceManage {
	  INSTANCE;

	  private EntityManagerFactory emFactory;

	  private PersistanceManage() {

	    // "jpa-example" was the value of the name attribute of the

	    // persistence-unit element.

	    emFactory = Persistence.createEntityManagerFactory("laviton-webapi-electric-energy");

	  }

	  public EntityManager getEntityManager() {

	    return emFactory.createEntityManager();

	  }

	  public void close() {

	    emFactory.close();

	  }
}
