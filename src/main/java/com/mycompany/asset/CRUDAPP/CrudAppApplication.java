package com.mycompany.asset.CRUDAPP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.mycompany.asset.utils.Messages;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = "com.mycompany.asset")
public class CrudAppApplication {

	private static final Logger logger = LogManager.getLogger(CrudAppApplication.class);

	public static void main(String[] args) {
		logger.info(Messages.getMessage("APP.main"));
		SpringApplication.run(CrudAppApplication.class, args);
	}
}
