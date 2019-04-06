package com.automationpractice.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

	public final static String configFilePath= System.getProperty("user.dir")+"/config.properties";

	public static String getProperty(String configKey) {

		Properties prop = new Properties();
		InputStream input = null;
		String configValue = null;
		try {
			input = new FileInputStream(configFilePath);
			// load a properties file
			prop.load(input);
			configValue = prop.getProperty(configKey);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return configValue;
	}
}

