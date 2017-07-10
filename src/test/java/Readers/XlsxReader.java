package Readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxReader {

	private XSSFWorkbook myWorkBook;

	
	public String readit(String Key, String type) throws IOException {

		
		
		File myfile;

		String resFile = null;
		if (type.equals("ids")) {
			resFile = "resources/Excel_Files/ids.xlsx";
		} else if (type.equals("locators")) {
			resFile = "resources/Excel_Files/locators.xlsx";
		} else if (type.equals("password")) {
			resFile = "resources/Excel_Files/passwords.xlsx";
		} else if (type.equals("urls")) {
			resFile = "resources/Excel_Files/urls.xlsx";
		}

		
		 myfile = new File(resFile);
		FileInputStream fis = new FileInputStream(myfile);
		myWorkBook = new XSSFWorkbook(fis);

		// Return first sheet from the XLSX workbook
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = mySheet.iterator();

		// Traversing over each row of XLSX file
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				if(cell.getStringCellValue().equals(Key))
				{
					
					return cellIterator.next().toString();
				}
				
				}
			}
		return null;
		}

	}


