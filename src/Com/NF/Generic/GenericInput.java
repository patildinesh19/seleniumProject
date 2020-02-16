package Com.NF.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GenericInput 
{
	public ArrayList<String> Set(String filename,String noofsparedshet) throws Exception
	{
		
		ArrayList<String> op=new ArrayList<String>();
		//String str=null;
		if(filename.toLowerCase().contains("xlsx"))
		{
			try
			{
				StringBuilder temp=new StringBuilder();
				
				FileInputStream file = new FileInputStream(new File(filename));
				
				//Create Workbook instance holding reference to .xlsx file
				XSSFWorkbook workbook = new XSSFWorkbook(file);

				//Get first/desired sheet from the workbook
				XSSFSheet sheet = workbook.getSheet(noofsparedshet);
				
				//Iterate through each rows one by one
				Iterator<Row> rowIterator = sheet.iterator();
				
				rowIterator.next();
				while (rowIterator.hasNext()) 
				{
					Row row = rowIterator.next();
					
					
					//For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					
					while (cellIterator.hasNext()) 
					{
						Cell cell = cellIterator.next();
						//op.add(cell.getStringCellValue());
						String inputdata=cell.getStringCellValue();
						System.out.println("inputdata :"+inputdata);
						temp.append(inputdata);
						temp.append(",");
					}
					
				}
				String actualstring=temp.toString();
				System.out.println("actualstring :"+actualstring);
				file.close();
				op.add(actualstring);
			}
			
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			}
		return op;
				
		}
}

