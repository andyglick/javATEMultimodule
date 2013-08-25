package it.amattioli.multimodule.db;

import it.amattioli.multimodule.db.LiquibaseDbSchemaUpdater;

public class CompositeDbSchemaUpdater implements LiquibaseDbSchemaUpdater {
	private LiquibaseDbSchemaUpdater[] updaters;
	
	public CompositeDbSchemaUpdater(LiquibaseDbSchemaUpdater... updaters) {
		this.updaters = updaters;
	}
	
	@Override
	public void updateDbSchema() throws Exception {
		for (LiquibaseDbSchemaUpdater curr: updaters) {
			curr.updateDbSchema();
		}
		
	}

}
