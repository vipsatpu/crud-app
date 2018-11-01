package com.mycompany.asset.dao;

public interface IDaoConstants {

	public static final String JDBC_DRIVER_CLASSNAME = "spring.datasource.driver-class-name";
	public static final String JDBC_URL = "spring.datasource.url";
	public static final String JDBC_USERNAME = "spring.datasource.username";
	public static final String JDBC_PASSWORD = "spring.datasource.password";

	// Hibernate properties
	public static final String HIBERNATE_SHOW_SQL = "spring.jpa.show-sql";
	public static final String HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
	public static final String SPRING_JPA_HIBERNATE_DDL_AUTO="spring.jpa.hibernate.ddl-auto";
	public static final String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

	public static final String HIBERNATE_C3P0_MIN_SIZE = "hibernate.c3p0.min_size";
	public static final String HIBERNATE_C3P0_MAX_SIZE = "hibernate.c3p0.max_size";
	public static final String HIBERNATE_C3P0_ACQUIRE_INCREMENT = "hibernate.c3p0.acquire_increment";
	public static final String HIBERNATE_C3P0_TIMEOUT = "hibernate.c3p0.timeout";
	public static final String HIBERNATE_C3P0_MAX_STATEMENTS = "hibernate.c3p0.max_statements";

	public static final String HBT_SHOW_SQL = "hibernate.show_sql";

	public static final String HBT_USE_2ND_CACHE = "spring.jpa.properties.hibernate.cache.use_second_level_cache";
	public static final String HBT_CACHE_RGN_FACTORY_CLS = "spring.jpa.properties.hibernate.cache.region.factory_class";
	public static final String HBT_SHARED_CACHE ="spring.jpa.properties.javax.persistence.sharedCache.mode";
	public static final String HBT_CACHE_PROVIDER = "hibernate.javax.cache.provider";

}
