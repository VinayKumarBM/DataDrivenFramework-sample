package com.automationpractice.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.json.Json;

public class AllureConfigurator {
	
	private static Logger LOG = Logger.getLogger(AllureConfigurator.class.getName());
	private static File resultsDirectory = new File("allure-results");
	private static File reportHistoryDirectory = new File("allure-report"+File.separator+"history");
	private static File resultsHistoryDirectory = new File("allure-results"+File.separator+"history");
	private static final String PATH = resultsDirectory.getAbsolutePath()+File.separator+"%s";

	public static void configure() throws IOException{
		makeCopyOfHistory();		
		createEnvironmentFile();
		createExecutorJSON();
	}
	
	private static void createEnvironmentFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.put("User", System.getProperty("user.name"));
		props.put("Browser", ConfigReader.getProperty("browser"));
		props.put("URL", ConfigReader.getProperty("appUrl"));
		props.put("OS", System.getProperty("os.name"));      

		FileOutputStream outputStrem = new FileOutputStream(String.format(PATH, "environment.properties"));
		props.store(outputStrem, "This is a properties file for Allure environment details");
		LOG.info("Environment Properties file created");
	}

	private static void makeCopyOfHistory() throws IOException {
		if(!resultsDirectory.exists()) {
			LOG.info("Creating new Report Directory");
			resultsDirectory.mkdir();
		}

		if(reportHistoryDirectory.exists()) {
			resultsHistoryDirectory.mkdir();
			FileUtils.copyDirectory(new File(reportHistoryDirectory.getAbsolutePath()), 
					new File(resultsHistoryDirectory.getAbsolutePath()));
			LOG.info("Copied history to results directory!");
		}
	}

	private static void createExecutorJSON() throws IOException {
		Map<String, String> executorMap = new HashMap<String, String>();
		executorMap.put("name", System.getProperty("user.name"));
		executorMap.put("buildName", System.getProperty("os.name"));
		executorMap.put("type", "jenkins");

		FileWriter file = new FileWriter(String.format(PATH, "executor.json")); 	  
		file.write(new Json().toJson(executorMap));
		file.flush();
		LOG.info("Executor JSON file created");
	}
}