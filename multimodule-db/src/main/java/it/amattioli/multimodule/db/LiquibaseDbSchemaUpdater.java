package it.amattioli.multimodule.db;

public interface LiquibaseDbSchemaUpdater {

	public void updateDbSchema() throws Exception;
	
}
