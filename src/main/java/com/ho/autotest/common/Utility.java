package com.ho.autotest.common;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;


import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;

import com.ho.autotest.model.Vehicle;
import com.ho.autotest.service.FileService;
import com.ho.autotest.service.FileServiceFactory;

public class Utility {
	
	   private Properties prop= null;
	   InputStream input = null;
	   FileService fileService = null;
	   XSSFWorkbook ExcelWBook = null;
	   XSSFSheet ExcelWSheet = null;
	   FileServiceFactory serviceFactory = new FileServiceFactory();
	   	   
	   
	   public FileServiceFactory getServiceFactory()
	   {
		   return serviceFactory;
	   }
	   
	   public Properties getPropertyObject()
	   {
		   if(null == prop){
			  return loadProperties(Constants.CONFIG_FILE);
		   }		   
		   return prop;
	   }
	   
	   
	   public Properties loadProperties(String filename)
	   {
		   prop = new Properties();
		   String propValue = null;
		   try{
			   input = getClass().getClassLoader().getResourceAsStream(filename);			   
			   if(input != null){
				   prop.load(input);
			   }
			   else{
				   throw new FileNotFoundException("property file'"+filename+"'not found in the classpath");
			   }
		   }catch(IOException er){
			   er.printStackTrace();
		   }
		   finally{
			   if(input!=null){
				   try{
					   input.close();
				   }
				   catch(IOException e){
					   e.printStackTrace();
				   }
			   }
		   }  
		return prop;
	   }
	   
		public void sleep(int seconds) {
	        try {
	            Thread.sleep(seconds * 1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	
		
		
		
    public boolean isFileSupported(String file)
    {
    	String filetype = FilenameUtils.getExtension(file);
    	return getPropertyObject().getProperty("filesupportedextns").contains(filetype);
    }
	   

	   
		/**
		 * 
		 * @param pathName
		 * @return destination
		 */     
	   public String createTestFolder(String pathName){		   
		   	String destination = null;
		   	File files = new File(pathName);
		   		if(!files.exists()){
		   			files.mkdirs();
		   		}	
		   		destination = files.getPath();
		   	return destination;
	   }
	   
	   /**
	    * @param 
	    * @return dateText
	    */
	   
	   public String getTodayDate(){		   
		   String dateText = null;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			dateText = formater.format(calendar.getTime());			
			return dateText;
	   }

}

