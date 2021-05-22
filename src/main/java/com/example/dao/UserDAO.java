package com.example.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.dao.entities.User;

@Stateless
public class UserDAO {

	private static final class FieldNames {
		private FieldNames() {
			// empty default constructor
		}

		private static final String FIRST_NAME = "first_name";
		private static final String LAST_NAME = "last_name";
		private static final String BIRTH_DATE = "birth_date";
		private static final String CREATED_AT = "created_at";
		private static final String ID = "id";
	}

	private static final String NOT_DEACTIVATED_SQL = "deactivated = false";

	private static final class SqlQueries {
		private SqlQueries() {
			// empty default constructor
		}

		private static final String FIND_USERS = "SELECT u FROM users u WHERE ";
		private static final String FIND_USERS_SQL = FIND_USERS + NOT_DEACTIVATED_SQL;
		private static final String FIND_USER_BY_ID_SQL = FIND_USERS + FieldNames.ID + " = ?";
		private static final String FIND_USERS_BY_NAME_SQL = FIND_USERS + FieldNames.FIRST_NAME + " = ? AND "
				+ FieldNames.LAST_NAME + " = ? AND " + NOT_DEACTIVATED_SQL;
		private static final String FIND_USERS_BORN_BEFORE_SQL = FIND_USERS + FieldNames.BIRTH_DATE
				+ " > TO_DATE(?, 'DD.MM.YYYY') AND " + NOT_DEACTIVATED_SQL;
		private static final String SAVE_USER = "INSERT INTO users (" + FieldNames.FIRST_NAME + ", "
				+ FieldNames.LAST_NAME + ", " + FieldNames.BIRTH_DATE + ", " + FieldNames.CREATED_AT
				+ ") VALUES (?, ?, TO_DATE(?, 'DD.MM.YYYY'), current_timestamp)";
	}

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findUsers() {
		return this.entityManager.createQuery(SqlQueries.FIND_USERS_SQL, User.class).getResultList();
	}

	public void saveUser(User user) {
		this.entityManager.persist(user);
	}

	public void deleteUserById(long userId) {
//		this.entityManager.createQuery("DELETE FROM users WHERE ID = ?", User.class).executeUpdate();
		// Find managed Entity reference
		User user = this.entityManager.find(User.class, userId);

		// Call remove method to remove the entity
		if (user != null) {
			this.entityManager.remove(user);
		}
	}

}
