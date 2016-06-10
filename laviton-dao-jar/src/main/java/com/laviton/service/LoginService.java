package com.laviton.service;

import com.laviton.dao.LoginDao;
import com.laviton.model.Property_Manager_Details;

public class LoginService {
	
	public boolean check(String username,String password,String type)
	{
		LoginDao dao=new LoginDao();
		Property_Manager_Details pd=dao.getlogs(username, password,type);
		if(pd.getLogin_user_isVerify().equals(true))
		{
			return true;
		}
		else{
			return false;
		}
	}

}
