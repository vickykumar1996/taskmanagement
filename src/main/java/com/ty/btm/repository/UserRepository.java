package com.ty.btm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.btm.entity.Role;
import com.ty.btm.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByuserEmail(String userEmail);
	
	@Query("select u from User u where u.role=?1")
	List<User> findAll(Role employee);
	
	User findUserByUserEmailAndUserPassword(String userEmail,String userPassword);
}
