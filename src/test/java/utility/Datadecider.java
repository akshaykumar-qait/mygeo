package utility;

import java.io.IOException;

import Readers.ExcelReader;
import Readers.JsonReader;
import Readers.OptionReader;
import Readers.SpecReader;
import Readers.XlsxReader;
import Readers.YamlReader;

public class Datadecider {

	public String readit(String Key, String type) throws IOException {
		String data_value = new OptionReader().optionFileReader("readfrom");

		if (data_value.equals("json")) {
			return new JsonReader().readit(Key, type);

		} else if (data_value.equals("ods")) {

			return new ExcelReader().readit(Key, type);

		}

		else if (data_value.equals("xlsx")) {

			return new XlsxReader().readit(Key, type);

		} else if (data_value.equals("yaml")) {

			return new YamlReader().readit(Key, type);

		} else if (data_value.equals("spec")) {

			return new SpecReader().readit(Key, type);

		}

		return null;

	}

}
