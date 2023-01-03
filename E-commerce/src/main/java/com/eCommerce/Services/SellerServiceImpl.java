package com.eCommerce.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.SellerException;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Seller;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.sellerRepo;

@Service
public class SellerServiceImpl implements SellerService{

	@Autowired
	private sellerRepo sRepo;

	@Autowired
	private CurrentUserSessionRepo cusRepo;	
	
	@Override
	public Seller insertSeller(Seller seller) throws SellerException {
		Seller existingSeller = sRepo.findByEmail(seller.getEmail());

		if (existingSeller != null) {
			throw new SellerException("Seller with this Email already Exist");
		}

		Seller newSeller = sRepo.save(seller);

		return newSeller;
	}

	@Override
	public String deleteSeller(int sid, String Key) throws SellerException, LoginException {

				Optional<Seller> opt = sRepo.findById(sid);
				if (opt == null)
					throw new SellerException("Invalid Credentials...!");

				Seller seller = opt.get();

				CurrentUserSession cus = cusRepo.findByUuid(Key);

				if (cus.getUserId() == sid) {
					throw new LoginException("Please Login First");
				}

				sRepo.delete(seller);

				return "We hope you enjoy our service "+seller.getSellerName();
	}

	@Override
	public Seller updateName(int sid, String key, String sellerName) throws SellerException, LoginException {

				Optional<Seller> opt = sRepo.findById(sid);
				if (opt == null)
					throw new SellerException("Invalid Credentials...!");

				Seller seller = opt.get();

				CurrentUserSession cus = cusRepo.findByUuid(key);

				if (cus.getUserId() != sid) {
					throw new LoginException("Please Login First");
				}
				
				seller.setSellerName(sellerName);
				sRepo.save(seller);
				
				
				return seller;
	}

	
}
