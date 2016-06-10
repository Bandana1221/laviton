package com.laviton.ws.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class EnerConsumptionApplicatiom extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	 public EnerConsumptionApplicatiom(){
		 System.out.println("in ws");
		 singletons.add(new EnergyConsumptionWebService());
	 }
	 @Override
		public Set<Object> getSingletons() {
			return singletons;
		}
}
