package com.laviton.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.GenericTableMetaDataProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.laviton.model.MeterLogs;


@Repository(value="MeterLogsDao")
public class MeterLogsDao implements Serializable {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
	
	
	public void addmeterLogs(MeterLogs meterlogs)
	{
		 sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try{
			 int i=0;
			 tx.begin();
			
			session.save(meterlogs);
			System.out.println("datas added");
			  
			 
			 
			
			 tx.commit();
			   
		}catch (Exception exception) {
			System.out.println(exception);
			if (tx!=null) tx.rollback();
	         exception.printStackTrace(); 

		} finally {
			 session.close();
			sessionFactory.close();
			
		}
	}
	public ArrayList<MeterLogs> getlogs(String
			time){
		ArrayList<MeterLogs> meter=new ArrayList<MeterLogs>();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		System.out.println(time);
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(MeterLogs.class).add(Restrictions.like("date",time));
			
					
			meter=(ArrayList<MeterLogs>) criteria.list();
			
			System.out.println(meter.size());
			tx.commit();
			
			
	}catch(Exception e){
		System.out.println(e);
		tx.rollback();
	}
		return meter;
	}

}
