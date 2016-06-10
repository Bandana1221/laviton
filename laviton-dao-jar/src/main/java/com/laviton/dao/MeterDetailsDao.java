package com.laviton.dao;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.laviton.model.MeterDetails;
import com.laviton.model.MeterLogs;
import com.laviton.model.TennantDetails;
@Repository(value="MeterDetailsDao")

public class MeterDetailsDao implements Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void addmeterLogs(MeterDetails meterlogs)
	{
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			Transaction tx = session.getTransaction();
			try{
				 
				 tx.begin();
	    session.save(meterlogs);

	    tx.commit();
	    tx = null;

			}catch (Exception exception) {
				System.out.println(exception);
				tx.rollback();

			} finally {
				session.close();
				
			}
		}
	public ArrayList<MeterDetails> getlogs(){
		ArrayList<MeterDetails> meter=new ArrayList<MeterDetails>();
		TennantDetails tennant =new TennantDetails();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(MeterDetails.class);
			
					
			meter= (ArrayList<MeterDetails>) criteria.list();
			System.out.println(meter.size());
			
		
			tx.commit();
			
			
	}catch(Exception e){
		System.out.println(e);
		tx.rollback();
	}
		return meter;
	}
	public MeterDetails getlogs(int
			tennantid){
		ArrayList<MeterDetails> meter=new ArrayList<MeterDetails>();
		MeterDetails tennant =new MeterDetails();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(MeterDetails.class).add(Restrictions.eq("meter_id",tennantid));
			
					
			meter= (ArrayList<MeterDetails>) criteria.list();
			System.out.println(meter.size());
			
		for(MeterDetails tr:meter)
		{
			tennant=meter.get(0);
		}
			tx.commit();
			
			
	}catch(Exception e){
		System.out.println(e);
		tx.rollback();
	}
		return tennant;
	}
}

