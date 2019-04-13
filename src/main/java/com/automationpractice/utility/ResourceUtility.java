package com.automationpractice.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class ResourceUtility {
	
	private static Logger log = Logger.getLogger(ResourceUtility.class.getName());
	
	public static String getScreenShotFolderPath() {
		String screenShotFolderPath = GlobalVariable.basePath +ConfigReader.getProperty("screenShotFolderPath");
		createDirectory(screenShotFolderPath);
		return screenShotFolderPath;
	}
	
	public static String getReportFolderPath() {
		String reportFolderPath = GlobalVariable.basePath +ConfigReader.getProperty("reportPath");
		createDirectory(reportFolderPath);
		return reportFolderPath;
	}
	
	public static String getDataFolderPath() {
		return GlobalVariable.basePath+ConfigReader.getProperty("testDataPath");
	}
	
	private static boolean createDirectory(String folderPath) {
		Path path = Paths.get(folderPath);
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                log.info("Directory was created.\n"+folderPath);
                return true;
            } catch (IOException exp) {
                log.error(exp.getMessage(), exp);
                return false;
            }
        }
        return true;
	}
}
