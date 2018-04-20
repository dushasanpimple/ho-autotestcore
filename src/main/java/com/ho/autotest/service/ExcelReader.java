package com.ho.autotest.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ho.autotest.model.Vehicle;



public class ExcelReader extends FileService{

	
	public List <Vehicle> getList(String fileName) {
		List <Vehicle> vehicleList = new ArrayList <Vehicle>();
		try {
			FileInputStream excelFile = new FileInputStream(fileName);
			try {
				Workbook workbook = new XSSFWorkbook(excelFile);
				Sheet datatypeSheet = workbook.getSheetAt(0);
				Iterator <Row> iterator = datatypeSheet.iterator();
				iterator.next();
				while (iterator.hasNext()) {
					Vehicle vehicle = new Vehicle();
					Row currentRow = iterator.next();
					vehicle.setRegNum(currentRow.getCell(0).getStringCellValue());
					vehicle.setMake(currentRow.getCell(1).getStringCellValue());
					vehicle.setColour(currentRow.getCell(2).getStringCellValue());
					//vehicle.setFueltype(currentRow.getCell(3).getStringCellValue());
					vehicleList.add(vehicle);
				}
			} finally {
				excelFile.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vehicleList;
	}

}
