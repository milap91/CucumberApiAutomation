package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_Data_From_Excel {

	public ArrayList<String> getdata(String TestCasename,String Sheetname) throws FileNotFoundException, IOException
	
	{
		ArrayList<String> arr= new ArrayList<String>();
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream("E:\\milap_workspace\\CucumberApiProject\\src\\test\\java\\resources\\TestData.xlsx"));
		int sheetcount=book.getNumberOfSheets();
		for(int i=0;i<sheetcount;i++)
		{
			if(book.getSheetName(i).equals(Sheetname))
			{
				XSSFSheet sheet=book.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				Row firstrow= rows.next();
				Iterator<Cell> cells=firstrow.cellIterator();
				int k=0;
				int colomn =0;
				while(cells.hasNext())
				{
					Cell value=cells.next();
					String v=value.getStringCellValue();
					
					if(v.equalsIgnoreCase("testcase"))
					{
						
						colomn=k;
						
					}
					k++;
				}
				System.out.println("test case colomn is present at--"+colomn);
				
				while(rows.hasNext())
				{
					Row r= rows.next();
					if(r.getCell(colomn).getStringCellValue().equalsIgnoreCase(TestCasename))
					{
					Iterator<Cell> c=r.iterator();
					 while(c.hasNext())
					 {
						 Cell actual=c.next();
						 	if (actual.getCellType()==CellType.STRING)
						 	{
						 		arr.add(actual.getStringCellValue());
						 		
						 	}
						 	else
						 	{
						 		arr.add(NumberToTextConverter.toText(actual.getNumericCellValue()));
						 	}
					 }
				}
			}
		}
		}
		System.out.println(arr);
		return arr;
	
	}
	
	

}
