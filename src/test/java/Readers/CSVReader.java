package Readers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvReader;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVReader {

	
public static void main(String[] args) throws IOException {
		
		String outputFile = "resource/LoginTest/CSV_Files/yourfile.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		
		     CSVWriter writer = new CSVWriter(new FileWriter(outputFile), ',');
		     // feed in your array (or convert your data to an array)
		     String[] entries = "first#second#third".split("#");
		     writer.writeNext(entries);
		     
		     writer.writeNext(entries);
		     
		     writer.close();
		}


String readSpec(String key)
{
	
	
	return key;
}
	
	
}
