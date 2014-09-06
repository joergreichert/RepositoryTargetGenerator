package de.abg.jreichert.repositorytarget.database;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("javadoc")
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
							.setProperty("hibernate.show_sql", "false")
							.setProperty("hibernate.format_sql", "false")
							.setProperty("hibernate.connection.username", "")
							.setProperty("hibernate.connection.password", "")
							.setProperty("hibernate.current_session_context_class", "thread")
							.setProperty("connection.pool_size", "1");

			if (Platform.isRunning()) {
				IPath path = Activator.getDefault().getStateLocation()
						.append("contents.db");
				File file = path.toFile();
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
					// http://stackoverflow.com/a/1689769
					// validate, update, create, create-drop
					cfg = cfg.setProperty("hibernate.hbm2ddl.auto", "create");
				} else {
					cfg = cfg.setProperty("hibernate.hbm2ddl.auto", "update");
				}
				cfg = cfg.setProperty("hibernate.connection.url",
						"jdbc:sqlite:" + path.toOSString());
			} else {
				File file = new File(System.getProperty("user.dir")
						+ "/contents.db");
				cfg = cfg.setProperty("hibernate.hbm2ddl.auto", "create");
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
			Activator
			.getDefault()
			.getLog()
			.log(new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(),
					e.getMessage(), e));
			throw new RuntimeException(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<>();

	public static synchronized Session currentSession() {
		Session s = session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static synchronized void closeSession() {
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}
