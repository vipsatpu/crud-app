package com.mycompany.asset.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

@Component
public class Messages {

	private static final String BUNDLE_NAME = "messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	};

	public static String getMessage(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);

		} catch (MissingResourceException e) {
			return '!' + key + '!';

		} catch (Exception e) {
			return null;
		}
	}

}
