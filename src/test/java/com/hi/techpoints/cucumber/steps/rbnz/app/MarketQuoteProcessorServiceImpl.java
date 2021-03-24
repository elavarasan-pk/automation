package com.hi.techpoints.cucumber.steps.rbnz.app;

class MktQuoteDataServiceImpl /* implements MktQuoteDataService */ {
	/*
	 * 
	 * private EntityManager em = null;
	 * 
	 * public MktQuoteDataServiceImpl() { em =
	 * entityManagerFactory().createEntityManager(); em.getTransaction().begin(); }
	 * 
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<MktQuote> getMktQuoteByDateAndEnvId(Date marketDate,
	 * int mktEnvId) { return em.createQuery("Select t from " +
	 * MktQuote.class.getSimpleName() + " t").getResultList();
	 * 
	 * }
	 * 
	 * @Override public void insertMarketQuoteList(List<MktQuote> list) {
	 * 
	 * for(int i=0; i < list.size(); i++) { em.persist(list.get(i)); } }
	 * 
	 * //EntityManagerFactory contains hsql in-memory database connection private
	 * EntityManagerFactory entityManagerFactory() {
	 * LocalContainerEntityManagerFactoryBean lef = new
	 * LocalContainerEntityManagerFactoryBean();
	 * 
	 * Properties dsProps = new Properties(); dsProps.put("driverClassName",
	 * "org.hsqldb.jdbcDriver"); dsProps.put("jdbcUrl", "jdbc:hsqldb:mem:testDB");
	 * dsProps.put("username", "sa"); dsProps.put("password", ""); HikariConfig
	 * hikariConfig = new HikariConfig(dsProps); HikariDataSource dataSource = new
	 * HikariDataSource(hikariConfig);
	 * 
	 * lef.setDataSource(dataSource); lef.setJpaVendorAdapter(new
	 * HibernateJpaVendorAdapter()); String[] packages =
	 * {"com.numerix.oneview.market.data.entity"}; lef.setPackagesToScan(packages);
	 * 
	 * Properties props = new Properties(); props.put("hibernate.hbm2ddl.auto",
	 * "create-drop"); props.put("hibernate.dialect",
	 * "org.hibernate.dialect.HSQLDialect"); props.put("hibernate.show_sql",
	 * "true"); props.put("hibernate.format_sql", "false");
	 * props.put("hibernate.globally_quoted_identifiers", "false");
	 * props.put("hibernate.order_inserts", "true");
	 * props.put("hibernate.order_updates", "true");
	 * props.put("hibernate.jdbc.batch_size", "20");
	 * props.put("hibernate.cache.use_second_level_cache", "true");
	 * props.put("hibernate.cache.use_query_cache", "false");
	 * props.put("hibernate.cache.use_minimal_puts", "true");
	 * props.put("hibernate.generate_statistics", "false");
	 * props.put("hibernate.cache.use_structured_entries", "ENABLE_SELECTIVE");
	 * props.put("hibernate.enable_lazy_load_no_trans", "true");
	 * props.put("hibernate.cache.hazelcast.shutdown_on_session_factory_close",
	 * "true"); props.put("hibernate.id.new_generator_mappings", "true");
	 * lef.setJpaProperties(props);
	 * 
	 * lef.afterPropertiesSet();
	 * 
	 * return lef.getObject(); }
	 * 
	 * 
	 * @Override protected void finalize() throws Throwable { super.finalize();
	 * if(em !=null) em.close(); }
	 * 
	 * @Override public MktQuote getMktQuote(int id) { // TODO Auto-generated method
	 * stub return null; }
	 * 
	 * @Override public void saveMarketQuote(MktQuote mktQuote) { // TODO
	 * Auto-generated method stub }
	 * 
	 * @Override public void deleteMktQuoteByDateAndEnvId(Date marketDate, int
	 * mktEnvId) { // TODO Auto-generated method stub }
	 * 
	 * @Override public void insertMarketQuote(MktQuote mktQuote) { // TODO
	 * Auto-generated method stub }
	 * 
	 */}
