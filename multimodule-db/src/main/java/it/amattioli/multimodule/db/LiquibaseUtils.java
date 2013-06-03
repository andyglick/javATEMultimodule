package it.amattioli.multimodule.db;

import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

public class LiquibaseUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseUtils.class);

	private LiquibaseUtils() {
		
	}
	
	public static void updateDbSchema() throws Exception {
		LOGGER.debug("Updating all DB schemas");
		for(LiquibaseDbSchemaUpdater updater: ServiceLoader.load(LiquibaseDbSchemaUpdater.class)) {
			updater.updateDbSchema();
		}
		LOGGER.debug("All DB schemas update");
	}

	public static void updateDbSchema(String changelog) throws Exception {
		LOGGER.debug("Updating DB schema using changelog {}", changelog);
		Liquibase liquibase = new Liquibase(changelog, 
				                            new ClassLoaderResourceAccessor(), 
				                            new JdbcConnection(HibernateUtils.getConnection()));
		liquibase.update("");
	}
	
	public static boolean isThereSomethingToUpdate(String changelog) throws Exception {
		Liquibase liquibase = new Liquibase(changelog, 
				                            new ClassLoaderResourceAccessor(), 
				                            new JdbcConnection(HibernateUtils.getConnection()));
		return liquibase.listUnrunChangeSets("").size() == 0;
	}
	
}
