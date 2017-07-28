package Tests.DataProviders;

import org.testng.annotations.Test;

public class DataProvider {

   
 
    @Test(dataProvider = "dataMethod")
    public void testMethod(String param) {
        System.out.println("The parameter value is: " + param);
    }
 
    @DataProvider
    public Object[][] dataMethod() {
        return new Object[][] { { "one" }, { "two" } };
    }
	
	
}
