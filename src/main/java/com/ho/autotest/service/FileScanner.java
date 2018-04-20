package com.ho.autotest.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;


import com.ho.autotest.common.Constants;
import com.ho.autotest.model.Vehicle;
import com.ho.autotest.model.FileInfo;


public class FileScanner extends FileService 
{
	
	/*
	@Override
	public List<Vehicle> getList(String filepath) 
	{
		filePath = prop.getProperty("inputdir");
        File inputdir = new File(filePath);
        
        List<FileInfo> list = null;
        
        if(!inputdir.isDirectory()){
        	return null;
        }
        
        list = new ArrayList<FileInfo>();
        File[] files = inputdir.listFiles();

        if(files.length<10){
        	return null;
        }
        
        for (File file : files) 
        {
        	list.add(createFileDTO(file));    	
        }
		return list;
	}
	*/
	
	
	public List<FileInfo>  getList(String filepath) {
		List<FileInfo> fileData = new ArrayList <>();
		
		//File dir = new File("src/main/resources/testData");
		File dir = new File(filepath);
		File[] fileList = dir.listFiles();
		for (File file : fileList) {
			String absolutePath = file.getAbsolutePath();
			if (file.isFile()) {
					fileData.add(getFilesDetails(absolutePath));
			}
		}
		//return getMapOfFilesOnExtension(fileData);
		
		return fileData;
	}
	
	private FileInfo getFilesDetails(String absolutePath) {
		FileInfo fileDetails = new FileInfo();
		String mimeType = getMimeType(absolutePath);
		fileDetails.setMimeType(mimeType);
		fileDetails.setExtn(FilenameUtils.getExtension(absolutePath));
		fileDetails.setSize(new File(absolutePath).length());
		fileDetails.setName(absolutePath);
		return fileDetails;
	}

	private String getMimeType(final String fileName) {
		Tika mimeTika = new Tika();
		String fileType;
		try {
			final File file = new File(fileName);
			fileType = mimeTika.detect(file);
		} catch (IOException exp) {
			fileType = "Unknown";
		}
		return fileType;
	}
	
}
