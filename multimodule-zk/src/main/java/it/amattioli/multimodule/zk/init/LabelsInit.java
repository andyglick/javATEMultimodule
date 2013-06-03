package it.amattioli.multimodule.zk.init;

import it.amattioli.multimodule.zk.ModuleLabelLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppInit;

public class LabelsInit implements WebAppInit {
	private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseInit.class);
	
	@Override
	public void init(WebApp arg0) throws Exception {
		LOGGER.info("Starting labels initialization");
		ModuleLabelLocator.registerAll();
		LOGGER.info("Finished labels initialization");
	}

}
