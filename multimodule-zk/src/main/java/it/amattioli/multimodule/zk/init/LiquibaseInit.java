package it.amattioli.multimodule.zk.init;

import it.amattioli.multimodule.db.LiquibaseUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

public class LiquibaseInit implements WebAppInit {
	private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseInit.class);
	
	@Override
	public void init(WebApp arg0) throws Exception {
		LOGGER.info("Starting Liquibase DB upgrade");
		LiquibaseUtils.updateDbSchema();
		LOGGER.info("Finished Liquibase DB upgrade");
	}

}
