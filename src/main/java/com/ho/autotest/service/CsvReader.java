package com.ho.autotest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ho.autotest.common.Constants;
import com.ho.autotest.model.Vehicle;


public class CsvReader extends FileService {

	@Override
	public List<Vehicle> getList(String filepath) 
	{
		   //Create List for holding File Data Transfer objects
			List <Vehicle> vehicleList = new ArrayList<Vehicle>();		   
	        BufferedReader br = null;
	        try
	        {
	        	File fileName = new File(filepath);
	            //Reading the csv file
	            br = new BufferedReader(new FileReader(fileName));
	            String line = "";
	            //Read to skip the header
	            br.readLine();
	            //Reading from the second line
	            while ((line = br.readLine()) != null) 
	            {
	                String[] cardetails = line.split(Constants.COMMA_DELIMITER);
	                
	                if(cardetails.length > 0 )
	                {
	                    //Save the Car details in CarDetails object
	                	//Vehicle car = new Vehicle(cardetails[0],cardetails[1],cardetails[2],cardetails[3]);
	                	Vehicle car = new Vehicle(cardetails[0],cardetails[1],cardetails[2]);
	                	vehicleList.add(car);
	                }
	            }
	        }
	        catch(Exception ee){
	        	ee.printStackTrace();
	        }
	        finally
	        {
	            try{
	                br.close();
	            }
	            catch(IOException ie)
	            {
	                System.out.println("Error occured while closing the BufferedReader");
	                ie.printStackTrace();
	            }
	        }
		   
		   return vehicleList;
	   }
	
}
