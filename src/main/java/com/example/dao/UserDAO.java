package com.example.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import com.example.dao.entities.User;

@Stateless
public class UserDAO {

	private static final String NOT_DEACTIVATED_SQL = "deactivated = false";
//	private static final String LAST_NAME_IS = " AND lastName = ";

	private static final class SqlQueries {

		private SqlQueries() {
			// empty default constructor
		}

		private static final String FIND_USERS = "SELECT u FROM users u WHERE ";
		private static final String FIND_USERS_SQL = FIND_USERS + NOT_DEACTIVATED_SQL;

		private static final String FIND_USER_BY_ID = FIND_USERS + NOT_DEACTIVATED_SQL + " AND id = ";
//		private static final String FIND_USER_BY_FIRST_NAME = FIND_USERS + NOT_DEACTIVATED_SQL + " AND firstName = ";
//		private static final String FIND_USER_BY_LAST_NAME = FIND_USER_BY_FIRST_NAME 

	}
	
	private static final String getSqlQueryByName(String firstName, String lastName) {
		String sql = SqlQueries.FIND_USERS_SQL;
		if (StringUtils.isNotBlank(firstName)) {
			sql += " and firstName = '" + lastName + "'";
		}
		if (StringUtils.isNotBlank(lastName)) {
			sql += " and lastName = '" + lastName + "'";
		}
		return sql;
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findUsers() {
		return this.entityManager.createQuery(SqlQueries.FIND_USERS_SQL, User.class).getResultList();
	}

	public User findUserById(long id) {
		return this.entityManager.createQuery(SqlQueries.FIND_USER_BY_ID + "'" + id + "'", User.class)
				.getSingleResult();
	}
	
	public List<User> findUsersByName(String firstName, String lastName) {
		return this.entityManager.createQuery(getSqlQueryByName(firstName, lastName), User.class).getResultList();
	}

	public User saveUser(User user) {
		this.entityManager.persist(user);
		this.entityManager.flush();
		return user;
	}

	@Transactional
	public void deleteUserById(long userId) {
		Optional.ofNullable(this.entityManager.find(User.class, userId)).ifPresent(User::deactivate);
	}

}
