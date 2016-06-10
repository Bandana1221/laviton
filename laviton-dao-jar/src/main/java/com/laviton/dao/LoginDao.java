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

import com.laviton.model.MeterLogs;
import com.laviton.model.Property_Manager_Details;
import com.laviton.model.TennantDetails;

@Repository(value="LoginDao")
public class LoginDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void addmeterLogs(Property_Manager_Details meterlogs)
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
	public Property_Manager_Details getlogs(String username,String password,String type){
		ArrayList<Property_Manager_Details> property=new ArrayList<Property_Manager_Details>();
		Property_Manager_Details pr=new Property_Manager_Details();
		//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("in dao");
		
		 sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session=sessionFactory.openSession();
			
			Transaction tx = session.getTransaction();
			try{
			tx.begin();
			Criteria criteria = session.createCriteria(Property_Manager_Details.class).add(Restrictions.eq("login_user_name",username)).add(Restrictions.eq("login_password", password)).add(Restrictions.eqOrIsNull("login_user_type",type));
			
					
			property= (ArrayList<Property_Manager_Details>) criteria.list();
			for(Property_Manager_Details pr1:property)
			{
			 pr=property.get(0);
			}
			System.out.println(property.size());
			tx.commit();
			
			
	}catch(Exception e){
		System.out.println(e);
		tx.rollback();
	}
			return pr;
	}
			
			public ArrayList<TennantDetails> getList(){
				ArrayList<TennantDetails> property=new ArrayList<TennantDetails>();
				//LocalDate now = time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				System.out.println("in dao");
				
				 sessionFactory = new Configuration().configure().buildSessionFactory();
					Session session=sessionFactory.openSession();
					
					Transaction tx = session.getTransaction();
					try{
					tx.begin();
					Criteria criteria = session.createCriteria(TennantDetails.class);
					
							
					property=  (ArrayList<TennantDetails>) criteria.list();
					
					//System.out.println(property.size());
					tx.commit();
					
					
			}catch(Exception e){
				System.out.println(e);
				tx.rollback();
			}
			
			
		return property;
	}
public void deletetennant(Property_Manager_Details tennantdetails)
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
public void updatetennant(Property_Manager_Details tennantdetails)
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


}
