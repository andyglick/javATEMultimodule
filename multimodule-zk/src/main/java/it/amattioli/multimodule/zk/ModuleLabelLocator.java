package it.amattioli.multimodule.zk;

import java.io.InputStream;
import java.util.Locale;
import java.util.ServiceLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.util.resource.LabelLocator2;
import org.zkoss.util.resource.Labels;

public class ModuleLabelLocator implements LabelLocator2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleLabelLocator.class);
	private String module;
	
	public ModuleLabelLocator(String module) {
		this.module = module;
	}
	
	@Override
	public InputStream locate(Locale locale) {
		return getClass().getResourceAsStream("/web/" + module + "/labels_" + locale + ".properties");
	}
	
	@Override
	public String getCharset() {
		return null;
	}
	
	public static void registerAll() {
		for (ModuleLabelLocator locator: ServiceLoader.load(ModuleLabelLocator.class)) {
			Labels.register(locator);
			LOGGER.debug("Registered label locator: "+locator);
		}
	}

}