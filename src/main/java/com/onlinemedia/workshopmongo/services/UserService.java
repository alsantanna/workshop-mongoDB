package com.onlinemedia.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinemedia.workshopmongo.domain.User;
import com.onlinemedia.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private  UserRepository repo;
	
	public List<User> findAll() {
	     return repo.findAll();	
	}

}
