package com.eCommerce.Services;

import com.eCommerce.exceptions.AdminException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.Admin;

public interface AdminServices {

	public Admin addadmin(Admin admin) throws AdminException;
	
	public Admin updateAdmin(Admin admin,String key) throws LoginException,AdminException;
	
	public String deleteAdmin(Integer adminId,String key)
			throws LoginException,AdminException;
	
	public Admin getAdminById(Integer adminId,String key)
			throws LoginException,AdminException;
}
