package com.example;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.example.dto.UserDTO;
import com.example.services.UserService;

@Model
public class UserBean {

	@Inject
	private UserService userService;

	private String firstName = "";
	private String lastName = "";

//	public UserDTO getUser() {
//		return this.user;
//	}

//	public void setUser(UserDTO user) {
//		this.user = user;
//	}

	public void add() {
		this.userService.saveUser(new UserDTO(null, this.firstName, this.lastName, LocalDate.now(), null));
		this.firstName = "";
		this.lastName = "";
	}

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
