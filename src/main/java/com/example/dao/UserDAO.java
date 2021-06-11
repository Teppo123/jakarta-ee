package com.example.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.example.dao.entities.User;

@Stateless
public class UserDAO {

	private static final String NOT_DEACTIVATED_SQL = "deactivated = false";

	private static final class SqlQueries {

		private SqlQueries() {
			// empty default constructor
		}

		private static final String FIND_USERS = "SELECT u FROM users u WHERE ";
		private static final String FIND_USERS_SQL = FIND_USERS + NOT_DEACTIVATED_SQL;

	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findUsers() {
		return this.entityManager.createQuery(SqlQueries.FIND_USERS_SQL, User.class).getResultList();
	}

	public void saveUser(User user) {
		this.entityManager.persist(user);
	}

	@Transactional
	public void deleteUserById(long userId) {
		Optional.ofNullable(this.entityManager.find(User.class, userId)).ifPresent(User::deactivate);
	}

}
