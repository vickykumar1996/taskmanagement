package com.ty.btm.dao;// data access operation

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.btm.entity.Role;
import com.ty.btm.entity.User;
import com.ty.btm.repository.UserRepository;

@Repository
public class UserDao {
	//save
	//findById
	//findByEmail
	//findAllEmployee
	//delete
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}

	public Optional<User> findByEmail(String userEmail) {
		return userRepository.findByuserEmail(userEmail);
	}

	public List<User> findAllEmployee() {
		return userRepository.findAll(Role.EMPLOYEE);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}

	public User findByEmailAndPassword(String userEmail, String userPassword) {
		return userRepository.findUserByUserEmailAndUserPassword(userEmail, userPassword);
	}

}
