package com.example;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.example.dto.UserDTO;
import com.example.services.UserService;

// http://localhost:8080/jakartaee8-starter/index.xhtml
@Model
public class UserBean {

	@Inject
	private UserService userService;

	private String firstName = "";
	private String lastName = "";

	public void add() {
		this.userService.saveUser(new UserDTO(null, this.firstName, this.lastName, LocalDate.now(), null));
		this.firstName = "";
		this.lastName = "";
	}

	// implicit getter/setter declarations since lombok annotations don't work that
	// well for xhmtl calls

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<UserDTO> getUsers() {
		return this.userService.getUsers();
	}

	public void deleteUser(Long id) {
		this.userService.deleteUserById(id);
	}

}
