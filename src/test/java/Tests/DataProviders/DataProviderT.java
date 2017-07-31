package Tests.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataProviderT {

	@DataProvider
	public Object[][] dataMethod() {
		return new Object[][] { { "one" }, { "two" } };
	}

	@DataProvider
	public Object[][] dataMethod2() {
		return new Object[][] { { "one", "oneiss" }, { "two", "twoois" } };
	}

	@Test(dataProvider = "dataMethod2")
	public void testMethoa(String param, String param2) {
		System.out.println("2The parameter value is: " + param);
	}

	
    @Parameters({"name"})
	@Test
	public void testMethod(String name) {
		System.out.println("1The parameter value is: " +name);
	}

}
