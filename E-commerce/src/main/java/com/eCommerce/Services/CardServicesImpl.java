package com.eCommerce.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.CardException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.CardDetails;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Customer;
import com.eCommerce.repo.CardRepo;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.CustomerRepo;

@Service
public class CardServicesImpl implements CardServices {

	@Autowired
	private CardRepo cardRepo;

	@Autowired
	private CurrentUserSessionRepo cusRepo;

	@Autowired
	private CustomerRepo cRepo;

	@Override
	public CardDetails addcard(CardDetails card, String key, Integer customerId)
			throws CardException, LoginException, CustomerException {
		Customer customer = checkLogin(key, customerId);

		CardDetails savedCard = cardRepo.save(card);

		customer.getCard().add(savedCard);

		cRepo.save(customer);

		return savedCard;
	}

	@Override
	public String deleteCard(Integer cardId, String key, Integer customerId)
			throws CardException, LoginException, CustomerException {

		Customer customer = checkLogin(key, customerId);

		Optional<CardDetails> opt= cardRepo.findById(cardId);
		if(opt.isEmpty()) throw new CardException("No card Found with Id:- "+cardId);
		
		CardDetails card=opt.get();
		
		boolean flag= customer.getCard().removeIf((c)->card.getCardId()==c.getCardId());
		if(flag) {
		cRepo.save(customer);
		
		cardRepo.delete(card);
		}else {
			throw new CardException("No Card found for Customer with Id:- "+cardId);
		}
		return "Card Removed Sucessfully";
	}

	@Override
	public CardDetails getCardByCardId(Integer cardId, String key, Integer customerId)
			throws CardException, LoginException, CustomerException {
		
		Customer customer = checkLogin(key, customerId);
		
		List<CardDetails> list= customer.getCard();
		
		
		for(CardDetails card:list) {
			if(card.getCardId()==cardId) {
				return card;
			}
		}
		throw new CardException("No Card found with this Id");
	}

	@Override
	public List<CardDetails> getAllCardByCustomerId(String key, Integer customerId)
			throws CardException, LoginException, CustomerException {
		Customer customer = checkLogin(key, customerId);
		
		List<CardDetails> cardlist=customer.getCard();
		if(cardlist.size()==0||cardlist==null) {
			throw new CardException("No card is Added By "+customer.getFirstName());
		}
		
		return cardlist;
	}

	public Customer checkLogin(String key, Integer customerId) throws LoginException, CustomerException {
		Optional<Customer> opt = cRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("No customer Found with id:- " + customerId);

		Customer customer = opt.get();
		CurrentUserSession cus = cusRepo.findByUuid(key);

		if (cus == null)
			throw new LoginException("Invalid Current Key");
		if (cus.getUserId() != customer.getCustomerId())
			throw new LoginException("Please Login first.....");

		return customer;

	}

}
