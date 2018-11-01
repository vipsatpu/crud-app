package com.mycompany.asset.security.config;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mycompany.asset.service.BeanUtil;

@Component
public class CustomJaasLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	private Map sharedState;
	private Map options;
	@Autowired
	private ApplicationContext appContext;
	private boolean succeeded = false;
	private String name = null;
	private String password = null;
	private Principal userPrincipal = null;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {

		System.out.println("Login Module - initialize called");
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		System.out.println("testOption value: " + (String) options.get("testOption"));
		//System.out.println(" initialize Context Initialised :" + appContext.getApplicationName());
		succeeded = false;
	}

	@Override
	public boolean login() throws LoginException {
		System.out.println("Login Module - login called");
		if (callbackHandler == null) {
			throw new LoginException("Oops, callbackHandler is null");
		}

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("name:");
		callbacks[1] = new PasswordCallback("password:", false);

		try {
			callbackHandler.handle(callbacks);
		} catch (IOException e) {
			throw new LoginException("Oops, IOException calling handle on callbackHandler");
		} catch (UnsupportedCallbackException e) {
			throw new LoginException("Oops, UnsupportedCallbackException calling handle on callbackHandler");
		}

		NameCallback nameCallback = (NameCallback) callbacks[0];
		PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

		name = nameCallback.getName();
		password = new String(passwordCallback.getPassword());
	/*	String[] beansArray = appContext.getBeanDefinitionNames();
		int i = 0;
		for (String beanName : beansArray) {
			i++;
			System.out.println(" Bean Name : " + i + " - " + beanName);
		}*/

		PasswordEncoder passwordEncoder1 = BeanUtil.getBean(BCryptPasswordEncoder.class);
		UserDetailsService userDetailsService1 = BeanUtil.getBean(UserDetailsService.class);
		//PasswordEncoder passwordEncoder = appContext.getBean("passwordEncoder", BCryptPasswordEncoder.class);
		//UserDetailsService userDetailsService = appContext.getBean("UserDetailsService", UserDetailsService.class);
		UserDetails userDetails = userDetailsService1.loadUserByUsername(name);
		if (passwordEncoder1.matches(password, userDetails.getPassword())) {
			succeeded = true;
			return succeeded;
		}
		/*
		 * if ("awarhoka".equals(name) && "admin".equals(password)) {
		 * System.out.println("Success! You get to log in!"); succeeded = true; return
		 * succeeded; }
		 */ else {
			System.out.println("Failure! You don't get to log in");
			succeeded = false;
			throw new FailedLoginException("Sorry! No login for you.");
		}
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println("Login Module - commit called");
		if (succeeded == false) {
			return false;
		} else {
			userPrincipal = new JAASUserPrincipal(name);
			if (!subject.getPrincipals().contains(userPrincipal)) {
				subject.getPrincipals().add(userPrincipal);
				System.out.println("subject.getPrincipals() == " + subject.getPrincipals());
			}
			return true;
		}
	}

	@Override
	public boolean abort() throws LoginException {
		System.out.println("Login Module - abort called");
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println("Login Module - logout called");
		return false;
	}
}
