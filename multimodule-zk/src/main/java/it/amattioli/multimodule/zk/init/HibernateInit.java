package it.amattioli.multimodule.zk.init;

import it.amattioli.multimodule.db.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

public class HibernateInit implements WebAppInit {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateInit.class);
	
	@Override
	public void init(WebApp app) throws Exception {
		LOGGER.info("Starting Hibernate initialization");
		HibernateUtils.map();
		LOGGER.info("Finished Hibernate initialization");
	}

}
