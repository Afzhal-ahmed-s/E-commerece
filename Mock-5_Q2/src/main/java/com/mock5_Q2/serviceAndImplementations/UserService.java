package com.mock5_Q2.serviceAndImplementations;

import java.util.List;

import com.mock5_Q2.exceptions.EmailException;
import com.mock5_Q2.exceptions.UserException;
import com.mock5_Q2.model.Email;
import com.mock5_Q2.model.User;

public interface UserService {

	//1
	public List<User> getAllUsers()throws UserException;
	
	//2
	public User createUser(User user)throws UserException;
	
	//3
	public User getUser(Integer userId)throws UserException;
	
	//4
	public User deleteUser(Integer userId)throws UserException;
	
	//7
	public Email getEmailForUser(Integer userId, Integer EmailId)throws UserException, EmailException;
	
	//6
	public Email createEmailforUser(Email email)throws UserException;
	
}
