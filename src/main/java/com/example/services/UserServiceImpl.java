package com.example.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.example.dao.UserDAO;
import com.example.dao.entities.transformers.UserTransformer;
import com.example.dto.UserDTO;
import com.example.dto.transformers.UserDTOTransformer;
import com.google.common.collect.Lists;

@ApplicationScoped
public class UserServiceImpl implements UserService {

	private final static UserDTO MOCK_USER_1 = UserDTO.builder().firstName("etunimi1").lastName("sukunimi1")
			.birthDate(LocalDate.now()).creationTime(LocalDateTime.now().minusDays(1)).build();

	private final static UserDTO MOCK_USER_2 = UserDTO.builder().firstName("etunimi2").lastName("sukunimi2")
			.birthDate(LocalDate.now()).creationTime(LocalDateTime.now().minusDays(2)).build();

	@Inject
	private UserDAO userDao;

	@Override
	public List<UserDTO> getUsers() {
		// TODO
		System.out.println("getUsers");
//		return Lists.newArrayList(MOCK_USER_1, MOCK_USER_2);
		return this.userDao.findUsers().stream().map(new UserDTOTransformer()::transform).collect(Collectors.toList());
	}

	@Override
	public UserDTO saveUser(UserDTO user) {
		this.userDao.saveUser(new UserTransformer().transform(user));
		return null; // TODO
	}

	@Override
	public UserDTO getUserById(long id) {
		// TODO
		System.out.println("getUserById, id = " + id);
		return MOCK_USER_1;
	}

	@Override
	public UserDTO getUserByName(String firstName, String lastName) {
		// TODO
		return MOCK_USER_1;
	}

	@Override
	public List<UserDTO> getUsersBornBefore(LocalDate date) {
		// TODO
		return Lists.newArrayList(MOCK_USER_1, MOCK_USER_2);
	}

	@Override
	public void deleteUserById(long id) {
		this.userDao.deleteUserById(id);
	}

}
