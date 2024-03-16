package com.ty.btm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.btm.dao.UserDao;
import com.ty.btm.entity.Role;
import com.ty.btm.entity.User;
import com.ty.btm.exception.NotAnAuthrouzedUserException;
import com.ty.btm.exception.UserNotFoundException;
import com.ty.btm.util.ResponseStructure;

//we write here business  logic

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user, int managerId) {
		Optional<User> optional = userDao.findById(managerId);
		if (optional.isPresent()) {
			User managerInfo = optional.get();
			if (managerInfo.getRole() == Role.MANAGER) {
				user = userDao.saveUser(user);
				ResponseStructure<User> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("created");
				responseStructure.setData(user);

				return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
			}
			throw new NotAnAuthrouzedUserException("Not an Authrouzed User to create Employee");

		}
		throw new UserNotFoundException("Manager with the Give Id Not Found");

	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
		Optional<User> optional = userDao.findById(userId);
		if (optional.isPresent()) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User with the Give Id Not Found");
	}
	
	public ResponseEntity<ResponseStructure<User>> findByEmail(String userEmail) {
		Optional<User> optional = userDao.findByEmail(userEmail);
		if (optional.isPresent()) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(optional.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User With the Give Email Not found");
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllEmployee() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Found");
		responseStructure.setData(userDao.findAllEmployee());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> delete(int userId) {
		Optional<User> optional = userDao.findById(userId);
		if (optional.isPresent()) {
			userDao.delete(optional.get());
			ResponseStructure<String> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Removed");
			responseStructure.setData("Deleted");
			return new ResponseEntity<>(responseStructure, HttpStatus.NO_CONTENT);
		}
		throw new UserNotFoundException("User With the Give Id Not found");
	}

	public ResponseEntity<ResponseStructure<User>> findByEmailAndPassword(String userEmail, String userPassword) {
		User user = userDao.findByEmailAndPassword(userEmail, userPassword);
		if (user != null) {
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Found");
			responseStructure.setData(user);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User With the Give Detalies Not found");
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user) {
		Optional<User> optional = userDao.findById(userId);
		if (optional.isPresent()) {
			user.setUserId(userId);
			User modifiedUser = userDao.saveUser(user);
			ResponseStructure<User> responseStructure = new ResponseStructure<>();
			responseStructure.setData(modifiedUser);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Modified");
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
		throw new UserNotFoundException("User With the Give UserId" + userId + " Not found");
	}

}
