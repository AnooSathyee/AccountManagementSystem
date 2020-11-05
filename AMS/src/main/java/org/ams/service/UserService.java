package org.ams.service;

import org.ams.model.User;
import org.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public void save(User user) {
		repo.save(user);
	}

	//Method to get a particular user details based on his email
	public User findByEmail(String email) {
		return repo.findByEmail(email);	 

	}

}

