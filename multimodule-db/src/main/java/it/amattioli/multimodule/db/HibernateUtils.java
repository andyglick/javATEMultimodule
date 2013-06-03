package it.amattioli.multimodule.db;

import it.amattioli.dominate.hibernate.HibernateSessionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ServiceLoader;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtils.class);
	
	public static Connection getConnection() throws SQLException {
		SessionFactory sessionFactory = HibernateSessionManager.getSessionFactory() ;
		return ((SessionFactoryImpl)sessionFactory).getSettings().getConnectionProvider().getConnection();		
	}
	
	public static void map() {
		LOGGER.debug("Mapping all Hibernate entities");
		for (HibernateEntityMapper mapper: ServiceLoader.load(HibernateEntityMapper.class)) {
			LOGGER.debug("Mapping using {}", mapper);
			mapper.map();
		}
		LOGGER.debug("All Hibernate entities mapped");
	}
	
	public static void map(Class<?> entityClass) {
		Configuration configuration = HibernateSessionManager.getConfiguration();
		if (configuration.getClassMapping(entityClass.getName()) == null) {
			LOGGER.debug("Mapping class {}", entityClass);
			configuration.addClass(entityClass);
		} else {
			LOGGER.warn("Class {} already mapped", entityClass);
		}
	}

}
