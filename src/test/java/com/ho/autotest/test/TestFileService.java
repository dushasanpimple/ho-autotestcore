package com.ho.autotest.test;

import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.commons.io.FilenameUtils;
import com.ho.autotest.model.FileInfo;
import com.ho.autotest.model.Vehicle;
import com.ho.autotest.common.Constants;
import com.ho.autotest.common.Utility;
import com.ho.autotest.service.FileService;
import com.ho.autotest.service.FileServiceFactory;


public class TestFileService 
{
	static Utility util = null;
	static Properties prop = null;
	static FileServiceFactory serviceFactory =null;
	
	@BeforeClass
	public static void setUp(){
		util = new Utility();
		prop = util.getPropertyObject();
		serviceFactory = new FileServiceFactory();
	}
	
	@Test
	public void unSupportedFileTest()
	{
		String dirPath = System.getProperty("user.dir");
		String invalidFile = prop.getProperty("invalidfilename");		
		String ext = FilenameUtils.getExtension(invalidFile);
		Assert.assertFalse("File not supported", util.isFileSupported(invalidFile));		
	}
		
	@Test
	public void csvFileReaderServiceTest()
	{
		String inputfilepath = System.getProperty("user.dir")+"/testdata/"+prop.getProperty("csvfilename");
		FileService service  = serviceFactory.getFileService(Constants.PARAM_CSV);
		List<Vehicle> actualList = service.getList(inputfilepath);
		
		String expCsvFilePath = System.getProperty("user.dir")+"/testdata/"+prop.getProperty("expcsvfilename");
		List<Vehicle> expList = service.getList(expCsvFilePath);
		
		Assert.assertTrue(expList.size()==actualList.size());
		for(int i=0;i<expList.size();i++)
		{
			System.out.println("==== Expe rec   :"+expList.get(i).toString());
			System.out.println("==== Actual Rec :"+actualList.get(i).toString());
			Assert.assertTrue(expList.get(i).toString().equals(actualList.get(i).toString()));
		}	
	}
	

	@Test
	public void excelFileReaderServiceTest()
	{
		String inputfilepath = System.getProperty("user.dir")+"/testdata/"+prop.getProperty("inputexcelfile");
		System.out.println("****  inputfilepath :"+inputfilepath);
		FileService service  = serviceFactory.getFileService(Constants.PARAM_XLSX);
		List<Vehicle> actualList = service.getList(inputfilepath);
		
		String expCsvFilePath = System.getProperty("user.dir")+"/testdata/"+prop.getProperty("expexcelfile");;
		List<Vehicle> expList = service.getList(expCsvFilePath);
		
		Assert.assertTrue(expList.size()==actualList.size());
		for(int i=0;i<expList.size();i++)
		{
			System.out.println("==== Expe rec   :"+expList.get(i).toString());
			System.out.println("==== Actual Rec :"+actualList.get(i).toString());
			Assert.assertTrue(expList.get(i).toString().equals(actualList.get(i).toString()));
		}
	}


	@Test
	public void fileScannerServiceTest()
	{
		
		String inputdir = System.getProperty("user.dir")+"/"+prop.getProperty("fileinputdir"); 
		FileService service  = serviceFactory.getFileService(Constants.PARAM_INFO);
		List<FileInfo> list = service.getList(inputdir);
		
		for(FileInfo f : list){
			System.out.println(" Name :"+f.getName());
			System.out.println(" Mime :"+f.getMimeType());
			System.out.println(" Extn :"+f.getExtn());
			System.out.println(" size :"+f.getSize());
		}
		int limit = Integer.parseInt(prop.getProperty("dirscanfilelimit"));
		
		Assert.assertTrue("InputDir should contain atleast "+limit+" Files", list.size()>=limit);
	}
	
}
