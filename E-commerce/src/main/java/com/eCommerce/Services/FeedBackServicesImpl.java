package com.eCommerce.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.FeedBackException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Customer;
import com.eCommerce.model.Feedback;
import com.eCommerce.model.Order;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.CustomerRepo;
import com.eCommerce.repo.FeedBackRepo;
import com.eCommerce.repo.OrderRepo;

@Service
public class FeedBackServicesImpl implements FeedBackService {

	@Autowired
	private CustomerRepo crepo;

	@Autowired
	private CurrentUserSessionRepo cusrepo;

	@Autowired
	private FeedBackRepo frepo;

	@Autowired
	private OrderRepo orepo;

	@Override
	public Feedback AddFeedBack(Feedback feedback, Integer orderId, Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, FeedBackException {
		Customer customer = checkLogin(key, customerId);

		Optional<Order> opt = orepo.findById(orderId);

		if (opt.isEmpty())
			throw new OrderException("Invalid Order Id");
		Order order = opt.get();

		feedback.setCustomer(customer);
		feedback.setOrder(order);

		Feedback savedfeedBack = frepo.save(feedback);

		return savedfeedBack;
	}

	@Override
	public Feedback updateFeedback(Feedback feedback, Integer customerId, String key)
			throws LoginException, CustomerException, FeedBackException {
		checkLogin(key, customerId);

		Optional<Feedback> opt = frepo.findById(feedback.getFeedBackId());
		if (opt.isEmpty())
			throw new FeedBackException("Invalid FeedBack Id");

		Feedback existingfeed =opt.get();
		
		feedback.setCustomer(existingfeed.getCustomer());
		feedback.setOrder(existingfeed.getOrder());
		Feedback feed = frepo.save(feedback);

		return feed;
	}

	@Override
	public String deletefeedback(Integer feedbackId, Integer customerId, String key)
			throws LoginException, CustomerException, FeedBackException {
		checkLogin(key, customerId);

		Optional<Feedback> opt = frepo.findById(feedbackId);
		if (opt.isEmpty())
			throw new FeedBackException("Invalid FeedBack Id");
		
		Feedback feed = opt.get();
		
		frepo.delete(feed);
		return "FeedBack Deleted Sucessfully....";
	}

	@Override
	public List<Feedback> viewAllFeedback(Integer customerId, String key)
			throws LoginException, CustomerException, FeedBackException {
		Customer customer = checkLogin(key, customerId);
		
		List<Feedback> fblist = frepo.findByCustomer(customer);
		
		if(fblist.size()==0) throw new FeedBackException("No FeedBack Added yet");
		
		return fblist;
	}

	@Override
	public Feedback viewFeedBackById(Integer feedbackId, Integer customerId, String key)
			throws LoginException, CustomerException, FeedBackException {
		Customer customer = checkLogin(key, customerId);
		
		Optional<Feedback> opt = frepo.findById(feedbackId);
		
		if(opt.isEmpty()) throw new FeedBackException("Invalid FeedBack ID");
		
		Feedback feed = opt.get();
		
		if(feed.getCustomer().getCustomerId()!=customer.getCustomerId()) {
			throw new FeedBackException("Invalid feedBack Id for customer : "+customer.getFirstName());
		}
		
		return feed;
	}
	
	
	public Customer checkLogin(String key, Integer customerId) throws LoginException, CustomerException {
		Optional<Customer> opt = crepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("No customer Found with id:- " + customerId);

		Customer customer = opt.get();
		CurrentUserSession cus = cusrepo.findByUuid(key);

		if (cus == null)
			throw new LoginException("Invalid Current Key");
		if (cus.getUserId() != customer.getCustomerId())
			throw new LoginException("Please Login first.....");

		return customer;

	}

	
}
