package Com.NF.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
public class GenericXpath
{
	public static By GetObject(String ObjectLogicalName)
	{
		By obj=null;
		try
	{
		FileInputStream file = new FileInputStream(new File("C:\\NetforumUpgradeInMaven\\NetforumUpgrade\\Xpaths.xlsx"));

		//Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(file);

		//Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		//Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		label1:
		while (rowIterator.hasNext()) 
		{
			Row row = rowIterator.next();
			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while (cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();
				String xpathlable=cell.getStringCellValue();
				if(xpathlable.contentEquals(ObjectLogicalName))
				{
					Cell secondcell = cellIterator.next();
					String XpathValue=secondcell.getStringCellValue();
					obj=By.xpath(XpathValue);
					break label1;
				}
			}
		}	file.close();		
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	return obj;
	}
}