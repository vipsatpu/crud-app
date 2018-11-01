package com.mycompany.asset.security.config;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AppConfigurationEntry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mycompany.asset.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	private static final Logger logger = LogManager.getLogger(WebSecurityConfig.class);
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	@Qualifier("roleGranter")
	AuthorityGranter roleGranter;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		logger.info("Setting in-memory security using the user input...");
		//auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(dbAuthenticationProvider());
		// auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Setting in-memory security using the user input...");
		auth.authenticationProvider(dbAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/list", "/home")
				.access("hasRole('USER') or hasRole('ADMIN')")
				.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
				.access("hasRole('ADMIN')").and().formLogin().loginPage("/login").permitAll().and()
				.addFilter(authenticationFilter())
				.logout()
	            .logoutUrl("/logout").and().exceptionHandling().accessDeniedPage("/Access_Denied").and().csrf()	            
				.disable();
	}

	@Bean
	public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
		logger.info("RequestBodyReaderAuthenticationFilter.authenticationFilter");
		RequestBodyReaderAuthenticationFilter authenticationFilter = new RequestBodyReaderAuthenticationFilter();
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationFilter;
	}

	@Bean
	public DefaultJaasAuthenticationProvider dbAuthenticationProvider() {
		logger.info("DefaultJaasAuthenticationProvider.dbAuthenticationProvider");
		DefaultJaasAuthenticationProvider authenticationProvider = new DefaultJaasAuthenticationProvider();

		Map<String, String> options = new HashMap<String, String>();
		options.put("debug", "true");

		AppConfigurationEntry appConfigEntries[] = new AppConfigurationEntry[1];
		appConfigEntries[0] = new AppConfigurationEntry("com.mycompany.asset.security.config.CustomJaasLoginModule",
				javax.security.auth.login.AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);

		Map<String, AppConfigurationEntry[]> mappedConfigurations = new HashMap();
		mappedConfigurations.put("SPRINGSECURITY", appConfigEntries);
		authenticationProvider.setConfiguration(new InMemoryConfiguration(mappedConfigurations));

		AuthorityGranter[] authorityGranter = new AuthorityGranter[1];
		authorityGranter[0] = roleGranter;
		authenticationProvider.setAuthorityGranters(authorityGranter);

		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("PasswordEncoder.passwordEncoder");
		return new BCryptPasswordEncoder();
	}

	@Bean(name = "authManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		logger.info("AuthenticationManager.authenticationManagerBean");
		return super.authenticationManagerBean();
	}

}
