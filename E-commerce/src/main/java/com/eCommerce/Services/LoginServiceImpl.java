package com.eCommerce.Services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.Admin;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Customer;
import com.eCommerce.model.Login;
import com.eCommerce.model.Seller;
import com.eCommerce.repo.AdminRepo;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.CustomerRepo;
import com.eCommerce.repo.sellerRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginServices {

	@Autowired
	private CustomerRepo crepo;
	
	@Autowired
	private sellerRepo sRepo;

	@Autowired
	private CurrentUserSessionRepo cusr;
	
	@Autowired
	private AdminRepo arepo;

	@Override
	public CurrentUserSession customerlogin(Login log) throws LoginException {

		Customer customer = crepo.findByEmail(log.getEmail());

		if (customer == null) {
			throw new LoginException("No Customer Found With this Email");
		}

		Optional<CurrentUserSession> opt = cusr.findById(customer.getCustomerId());

		if (opt.isPresent())
			throw new LoginException("User Already Logged In....");

		String uuid = RandomString.make(6);

		CurrentUserSession cus = new CurrentUserSession(customer.getCustomerId(), LocalDate.now(), uuid);
		cusr.save(cus);
		return cus;
	}

	@Override
	public CurrentUserSession sellerlogin(Login log) throws LoginException {
		Seller seller = sRepo.findByEmail(log.getEmail());

		if (seller == null) {
			throw new LoginException("No Seller Found With this Email");
		}

		Optional<CurrentUserSession> opt = cusr.findById(seller.getSellerId());

		if (opt.isPresent())
			throw new LoginException("seller Already Logged In....");

		String uuid = RandomString.make(6);

		CurrentUserSession cus = new CurrentUserSession(seller.getSellerId(), LocalDate.now(), uuid);
		cusr.save(cus);
		return cus;
	}

	@Override
	public String Logout(Integer id,String uuid) throws LoginException {
		
		CurrentUserSession cus= cusr.findByUuid(uuid);
		
		if(cus==null) throw new LoginException("Invalid Uuid....");
		
		if(id!=cus.getUserId()) throw new LoginException("Invalid Credentials");
		
		cusr.delete(cus);
		return "Logged Out";
	}

	@Override
	public CurrentUserSession adminlogin(Login log) throws LoginException {
		Admin admin = arepo.findByUserName(log.getEmail());
		
		if(admin==null) throw new LoginException("Admin Not found with email: "+log.getEmail());
		
		Optional<CurrentUserSession> opt = cusr.findById(admin.getAdminId());

		if (opt.isPresent())
			throw new LoginException("Admin Already Logged In....");
		
		String uuid = RandomString.make(6);
		
		CurrentUserSession cus = new CurrentUserSession(admin.getAdminId(), LocalDate.now(), uuid);
		cusr.save(cus);
		return cus;
	}

}
