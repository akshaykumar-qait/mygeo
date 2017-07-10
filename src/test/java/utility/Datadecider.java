package utility;

import java.io.IOException;

import Readers.ExcelReader;
import Readers.JsonDataReader;
import Readers.OptionReader;
import Readers.XlsxReader;

public class Datadecider {

	public String readit(String Key, String type) throws IOException {
		String data_value = new OptionReader().optionFileReader("readfrom");

		if (data_value.equals("json")) {
			String output = new JsonDataReader().readit(Key, type);
			return output;
		} else if (data_value.equals("ods")) {

			String output = new ExcelReader().readit(Key, type);
			return output;

		}

		else if (data_value.equals("xlsx")) {

			String output = new XlsxReader().readit(Key, type);
			return output;

		}

		return null;

	}

}
