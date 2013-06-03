package it.amattioli.multimodule.test;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Transaction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import it.amattioli.dominate.RepositoryRegistry;
import it.amattioli.dominate.hibernate.HibernateRepositoryFactory;
import it.amattioli.dominate.hibernate.HibernateSessionManager;
import it.amattioli.dominate.sessions.SessionMode;
import it.amattioli.encapsulate.dates.Day;
import it.amattioli.multimodule.db.HibernateEntityMapper;
import it.amattioli.multimodule.db.HibernateUtils;
import it.amattioli.multimodule.db.LiquibaseDbSchemaUpdater;

public class DbTestUtility {
	private IDatabaseTester databaseTester;
	private HibernateEntityMapper hibernateEntityMapper;
	private String hibernateCfgResource;
	private LiquibaseDbSchemaUpdater liquibaseDbSchemaUpdater;
	
	public String getHibernateCfgResource() {
		return hibernateCfgResource;
	}

	public void setHibernateCfgResource(String hibernateCfgResource) {
		this.hibernateCfgResource = hibernateCfgResource;
	}

	public HibernateEntityMapper getHibernateEntityMapper() {
		return hibernateEntityMapper;
	}

	public void setHibernateEntityMapper(HibernateEntityMapper hibernateEntityMapper) {
		this.hibernateEntityMapper = hibernateEntityMapper;
	}

	public void initHibernate() {
		HibernateSessionManager.setCfgResource(getHibernateCfgResource());
		RepositoryRegistry.instance().setRepositoryFactoryClass(HibernateRepositoryFactory.class);
		getHibernateEntityMapper().map();
	}
	
	public LiquibaseDbSchemaUpdater getLiquibaseDbSchemaUpdater() {
		return liquibaseDbSchemaUpdater;
	}

	public void setLiquibaseDbSchemaUpdater(LiquibaseDbSchemaUpdater liquibaseDbSchemaUpdater) {
		this.liquibaseDbSchemaUpdater = liquibaseDbSchemaUpdater;
	}

	public void updateDbSchema() throws Exception {
		getLiquibaseDbSchemaUpdater().updateDbSchema();
	}
	
	public void initDataSet(String dataSetResource) throws Exception {
		databaseTester = new JdbcDatabaseTester((String)HibernateSessionManager.getConfiguration().getProperties().get("hibernate.connection.driver_class"),
				                                (String)HibernateSessionManager.getConfiguration().getProperties().get("hibernate.connection.url"), 
				                                (String)HibernateSessionManager.getConfiguration().getProperties().get("hibernate.connection.username"), 
				                                (String)HibernateSessionManager.getConfiguration().getProperties().get("hibernate.connection.password"));
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(dataSetResource));
		databaseTester.setDataSet( dataSet );
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.onSetup();
	}
	
	public void tearDownDataSet() throws Exception {
		databaseTester.onTearDown();
	}
	
	public void shutDownDb() throws Exception {
		HibernateUtils.getConnection().prepareCall("SHUTDOWN").execute();
	}

	public Transaction startTransaction() {
		HibernateSessionManager smgr = new HibernateSessionManager(SessionMode.THREAD_LOCAL);
		Transaction tx = smgr.getSession().getTransaction();
		tx.begin();
		return tx;
	}
	
	public void assertTableContent(String expectedDataSetResource, String tableName) throws Exception {
		QueryDataSet databaseDataSet = new QueryDataSet(databaseTester.getConnection());
		databaseDataSet.addTable(tableName);
        ITable actualTable = databaseDataSet.getTable(tableName);
        
        IDataSet baseExpectedDataSet = new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream(expectedDataSetResource));
        ReplacementDataSet expectedDataSet = new ReplacementDataSet(baseExpectedDataSet);
        expectedDataSet.addReplacementObject("[TODAY]", Day.today().getInitTime());
        expectedDataSet.addReplacementObject("[NULL]", null);
        ITable expectedTable = expectedDataSet.getTable(tableName);
        
        Assertion.assertEquals(expectedTable, actualTable);
	}
	
	public static String dataSetPrefix(Class<?> c) {
		return "/"+c.getPackage().getName().replace('.', '/')+"/";
	}
	
}
