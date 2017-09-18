package com.cloudleaf.webautomation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadExcel {

	@DataProvider
	public Object[][] readData() throws IOException, InvalidFormatException
	{
		File inputxl = new File("src//test//resources//TestData//tenantinfo.xlsx");
		FileInputStream fis = new FileInputStream(inputxl);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet ws =  workbook.getSheetAt(0);
		
		XSSFRow rowsno = ws.getRow(0);
	       int colNum = rowsno.getLastCellNum();
	       System.out.println("Total Number of Columns in the excel is : "+colNum);
	       int rowNum = ws.getLastRowNum()+1;
	       System.out.println("Total Number of Rows in the excel is : "+rowNum);
	       
	   	Object[][] data = new Object[rowNum][colNum];
	       
	   	for(int row=0; row<rowNum;row++)
		{
	   		
	   		for(int column=0;column<ws.getRow(row).getLastCellNum();column++)
			{
	   			data[row][column] = ws.getRow(row).getCell(column).getStringCellValue();		
			}	
		}

		/*int rows = ws.getLastRowNum();
		int columns = ws.getRow(rows).getLastCellNum();

		System.out.println("No of Rows in XL : "+rows);
		System.out.println("No of Columns in XL : "+columns);

		Object[][] data = new Object[rows][columns];

		for(int row=1; row<rows;row++)
		{
			for(int column=0;column<ws.getRow(row).getLastCellNum();column++)
			{
				data[row][column] = ws.getRow(row).getCell(column).getStringCellValue();		
			}	
		}*/
		return data;
	}

	@Test(dataProvider="readData")
	public void tenantFlow(String tenantName,String siteName,String areaName,String ccName,String ccID,String tagName,String tagID,String assetName) throws Exception
	{

		System.out.println(tenantName +"----"+siteName+"--------"+areaName+"----"+ccName+"----"+ccID+"--------"+tagName+"----------"+tagID+"-----"+assetName);
		
	}
}
