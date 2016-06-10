package com.laviton.dao;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.laviton.model.MeterDetails;
import com.laviton.model.MeterLogs;
import com.laviton.model.TennantDetails;

@Repository(value="TennatsDao")
public class TennantDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void addmeterLogs(TennantDetails meterlogs)
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
	public TennantDetails getlogs(int
			tennantid){
		ArrayList<TennantDetails> meter=new ArrayList<TennantDetails>();
		TennantDetails tennant =new TennantDetails();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(TennantDetails.class).add(Restrictions.eq("tennant_id",tennantid));
			
					
			meter= (ArrayList<TennantDetails>) criteria.list();
			System.out.println(meter.size());
			
		for(TennantDetails tr:meter)
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
	public int getTennatByacno(String accountno){
		ArrayList<TennantDetails> meter=new ArrayList<TennantDetails>();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		int t=0;
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(TennantDetails.class).add(Restrictions.eq("tennant_account_number",accountno));
			
					
			meter= (ArrayList<TennantDetails>) criteria.list();
			System.out.println(meter.size());
			t=meter.get(0).getTennant_id();
			
			tx.commit();
			
			
	}catch(Exception e){
		System.out.println(e);
		tx.rollback();
	}
		return t;
	}
public void deletetennant(TennantDetails tennantdetails)
{
	 sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try{
			 int i=0;
			 tx.begin();
			
			session.delete(tennantdetails);
			System.out.println("datas deleted");
			  
			 
			 
			
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
public void updatetennant(TennantDetails tennantdetails)
{
	 sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try{
			 int i=0;
			 tx.begin();
			
			session.update(tennantdetails);
			System.out.println("datas deleted");
			  
			 
			 
			
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

public void assignmeter(int tennantid,int meter)
{ArrayList<TennantDetails> meter1=new ArrayList<TennantDetails>();

ArrayList<MeterDetails> meter2=new ArrayList<MeterDetails>();
	 sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try{
			 int i=0;
			 tx.begin();
			 Criteria criteria1 = session.createCriteria(TennantDetails.class).add(Restrictions.eq("tennant_id",tennantid));
				TennantDetails tennant=new TennantDetails();
				meter1= (ArrayList<TennantDetails>) criteria1.list();
				System.out.println(meter1.size());
				tennant=meter1.get(0);
				Criteria criteria2 = session.createCriteria(MeterDetails.class).add(Restrictions.eq("meter_id",meter));
				
				
				meter2= (ArrayList<MeterDetails>) criteria2.list();
				System.out.println(meter2.size());
				MeterDetails metera=new MeterDetails();
				metera=meter2.get(0);
				metera.setMeterassign("true");
			tennant.getMeetings().add(metera);
			session.saveOrUpdate(tennant);
			System.out.println("datas deleted");
			  
			 
			 
			
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

public void removemeter(int tennantid,int meter)
{ArrayList<TennantDetails> meter1=new ArrayList<TennantDetails>();

ArrayList<MeterDetails> meter2=new ArrayList<MeterDetails>();
	 sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction tx = session.getTransaction();
		try{
			 int i=0;
			 tx.begin();
			 Criteria criteria1 = session.createCriteria(TennantDetails.class).add(Restrictions.eq("tennant_id",tennantid));
				TennantDetails tennant=new TennantDetails();
				meter1= (ArrayList<TennantDetails>) criteria1.list();
				System.out.println(meter1.size());
				tennant=meter1.get(0);
				Criteria criteria2 = session.createCriteria(MeterDetails.class).add(Restrictions.eq("meter_id",meter));
				
				
				meter2= (ArrayList<MeterDetails>) criteria2.list();
				System.out.println(meter2.size());
				MeterDetails metera=new MeterDetails();
				metera=meter2.get(0);
				metera.setMeterassign("false");
			tennant.getMeetings().remove(metera);
			session.saveOrUpdate(tennant);
			System.out.println("datas deleted");
			  
			 
			 
			
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
}
