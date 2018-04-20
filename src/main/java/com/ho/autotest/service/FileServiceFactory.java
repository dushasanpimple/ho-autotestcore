package com.ho.autotest.service;

import com.ho.autotest.common.Constants;

public class FileServiceFactory 
{

	public FileService getFileService(String type)
	{
		FileService service = null;
		
		if(null == type){
			return null;
		}
		
		switch(type)
		{
			case Constants.PARAM_XLSX:
				service = new ExcelReader();
				break;
			case Constants.PARAM_CSV:
				service = new CsvReader();
				break;
			case Constants.PARAM_INFO:
				service = new FileScanner();
				break;
		}
		return service;
	}
	
}
