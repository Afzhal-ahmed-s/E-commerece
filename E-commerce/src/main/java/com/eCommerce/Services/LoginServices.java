package com.eCommerce.Services;

import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Login;

public interface LoginServices {

	public CurrentUserSession customerlogin(Login log) throws LoginException;
	
	public CurrentUserSession sellerlogin(Login log) throws LoginException;

	public String Logout(Integer id , String uuid) throws LoginException;
	
	public CurrentUserSession adminlogin(Login log) throws LoginException;
}
