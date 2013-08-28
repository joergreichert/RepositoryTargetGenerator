package de.abg.jreichert.repositorytarget.database;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionManager {

	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration()
					.addAnnotatedClass(Location.class)
					.addAnnotatedClass(Unit.class)
					.addAnnotatedClass(Version.class)
					.setProperty("hibernate.dialect",
							SQLiteDialect.class.getName())
					.setProperty("hibernate.connection.driver_class",
							org.sqlite.JDBC.class.getName())
					.setProperty("hibernate.show_sql", "true")
					.setProperty("hibernate.format_sql", "true")
					.setProperty("hibernate.connection.username", "")
					.setProperty("hibernate.connection.password", "")
					.setProperty("hibernate.current_session_context_class", "thread")
					.setProperty("connection.pool_size", "1")
					.setProperty("hibernate.hbm2ddl.auto", "create");

			if (Platform.isRunning()) {
				IPath path = Activator.getDefault().getStateLocation()
						.append("contents.db");
				File file = path.toFile();
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
				cfg = cfg.setProperty("hibernate.connection.url",
						"jdbc:sqlite:" + path.toOSString());
			} else {
				File file = new File(System.getProperty("user.dir")
						+ "/contents.db");
				if (!file.exists()) {
					file.createNewFile();
				}
				String path = file.getAbsolutePath().replace("\\", "/");
				cfg = cfg.setProperty("hibernate.connection.url",
						"jdbc:sqlite:" + path);
			}
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public static synchronized Session currentSession() {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static synchronized void closeSession() {
		Session s = (Session) session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}
