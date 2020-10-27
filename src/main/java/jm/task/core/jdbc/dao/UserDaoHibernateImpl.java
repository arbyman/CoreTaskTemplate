package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

	public UserDaoHibernateImpl() {
	}

	@Override
	public void createUsersTable() {
		Session session = null;
		try {
			session = Util.getSessionFactory().openSession();
			SQLQuery sqlQuery = session.createSQLQuery("create table if not exists users (id bigint not null auto_increment, name varchar(30), lastname varchar(30), age tinyint, primary key(id))");
			sqlQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println("Failed to create table");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
	}

	@Override
	public void dropUsersTable() {
		Session session = null;
		try {
			session = Util.getSessionFactory().openSession();
			SQLQuery sqlQuery = session.createSQLQuery("drop table if exists users");
			sqlQuery.executeUpdate();
		} catch (Exception e) {
			System.out.println("Failed to drop table");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
	}

	@Override
	public void saveUser(String name, String lastName, byte age) {
		User user = new User(name, lastName, age);
		saveUser(user);
	}

	public void saveUser(User user) {
		Session session = null;
		try {
			session = Util.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Failed to save user");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
	}

	@Override
	public void removeUserById(long id) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = Util.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			User user = (User) session.get(User.class, id);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
	}

	@Override
	public List<User> getAllUsers() {
		Session session = null;
		List<User> users = null;
		try {
			session = Util.getSessionFactory().openSession();
			users = session.createQuery("From User").list();
		} catch (Exception e) {
			System.out.println("Failed to get all users");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
		return users;
	}

	@Override
	public void cleanUsersTable() {
		Session session = null;
		try {
			session = Util.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery("truncate table users");
			sqlQuery.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Failed to clean table");
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
			Util.shutdown();
		}
	}
}
