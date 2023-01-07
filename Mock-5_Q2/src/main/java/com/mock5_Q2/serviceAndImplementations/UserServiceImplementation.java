package com.mock5_Q2.serviceAndImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock5_Q2.dao.EmailDao;
import com.mock5_Q2.dao.UserDao;
import com.mock5_Q2.exceptions.EmailException;
import com.mock5_Q2.exceptions.UserException;
import com.mock5_Q2.model.Email;
import com.mock5_Q2.model.User;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmailDao emailDao;
	
	@Override
	public List<User> getAllUsers() throws UserException {
		
		List<User>list = userDao.findAll();
		
		if (list.isEmpty()) {

			throw new UserException("No users found");

		} else {

			return list;

		}
	}

	@Override
	public User createUser(User user) throws UserException {
		
		User existingUser=userDao.findByPhoneNo(user.getPhoneNo());
		if(existingUser==null) {
			return userDao.save(user);
		}else {
			throw new UserException("User already registered with mobile number");
		}
		
	}

	@Override
	public User getUser(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(Integer userId) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Email getEmailForUser(Integer userId, Integer EmailId) throws UserException, EmailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Email createEmailforUser(Email email) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
