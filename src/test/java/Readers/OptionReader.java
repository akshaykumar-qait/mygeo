package Readers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OptionReader {

	public String optionFileReader(String optionKey) throws IOException
	{
		File f = new File("resource/data.properties");

		BufferedReader b = new BufferedReader(new FileReader(f));

		String readLine = "";
		boolean flag = false;

		String arrsplit[],temp[] = null;
		while ((readLine = b.readLine()) != null) {
			
			arrsplit = readLine.split("=");

			if (arrsplit[0].equals(optionKey)&&!arrsplit[0].startsWith("#")) {
				flag=true;
				temp=readLine.split("=");
			}
	
		}

		
		if(flag==true)
		{
			
			return temp[1].trim().toLowerCase();
		}
		return null;

		
	}
	
	

	public void writeit(String optionKey, Object optionValue) throws IOException {

		File f = new File("resource/data.properties");

		String readLine = "", olddata = "";
		BufferedReader b = new BufferedReader(new FileReader(f));
		while ((readLine = b.readLine()) != null) {

			if (olddata == "") {
				olddata = olddata + readLine;
			} else {
				olddata = olddata + "\n" + readLine;

			}
		}

		olddata = olddata + "\n";

		try {
			
			
			FileWriter fw = new FileWriter("resource/data.properties");
			fw.append(olddata + optionKey + "=" + optionValue.toString());
			fw.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		b.close();


		// throw new UnsupportedOperationException("Not implemented.");
	}
	
	
	public static void main(String args[]) throws IOException
	{
		
		new OptionReader().writeit("b", "chrome");
		
	}
	
	
	
	
	
}
