package NATgeo.mygeo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class ExcelReader {
	//
	// public static void main(String...S) throws IOException
	// {
	//
	//
	// File file = new File("resources/Excel_Files/Untitled.ods");
	// BufferedReader is = null;
	// try {
	// is = new BufferedReader(new FileReader(file));
	//
	//
	// char my[]= new char[50] ;
	// System.out.println(my);
	//
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	//
	// }
	//

	public String readODS(String key,String type) {

		File file;

		String resFile = null;
		if(type.equals("ids"))
		{
			resFile="resources/Excel_Files/ids.ods";
		}
		else if(type.equals("locators"))
		{
			resFile="resources/Excel_Files/locators.ods";
		}
		else if(type.equals("password"))
		{
			resFile="resources/Excel_Files/passwords.ods";
		}
		else if(type.equals("urls"))
		{
			resFile="resources/Excel_Files/urls.ods";
		}
		
		
		
		
		file = new File(resFile);

		Sheet sheet;
		try {
			// Getting the 0th sheet for manipulation| pass sheet name as string
			sheet = SpreadSheet.createFromFile(file).getSheet(0);

			// Get row count and column count
			int nRowCount = sheet.getRowCount();

			// Iterating through each row of the selected sheet
			MutableCell cell = null;
			for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
				cell = sheet.getCellAt(0, nRowIndex);
			
				//System.out.println(cell.getValue()+"  "+key);
				
				if (key.equals(cell.getValue().toString())) {
					cell = sheet.getCellAt(1, nRowIndex);
					return cell.getValue().toString();
				}
				

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// Creating File object of .ods file
		File file = new File("resources/Excel_Files/Untitled.ods");
		ExcelReader objODSReader = new ExcelReader();
		System.out.println(objODSReader.readODS("edulogin_id","ids"));
	}

}
