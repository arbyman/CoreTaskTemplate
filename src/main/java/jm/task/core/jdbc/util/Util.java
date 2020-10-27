package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	private static final String URL = "jdbc:mysql://localhost:3306/myDB?serverTimezone=Europe/Moscow&useSSL=false";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "admin";
	private static final String PASSWORD = "vsegda17";
	private static ServiceRegistry serviceRegistry;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static SessionFactory getSessionFactory() throws HibernateException {
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration()
					.setProperty("hibernate.connection.driver_class", DRIVER)
					.setProperty("hibernate.connection.url", URL)
					.setProperty("hibernate.connection.username", USER)
					.setProperty("hibernate.connection.password", PASSWORD)
					.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
					.addAnnotatedClass(User.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			System.out.println("SessionFactory creation failed");
		}
		return sessionFactory;
	}
	public static void shutdown() {
		if (serviceRegistry != null) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}
	}
}
