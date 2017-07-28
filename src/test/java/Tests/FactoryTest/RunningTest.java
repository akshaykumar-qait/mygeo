package Tests.FactoryTest;

import org.testng.annotations.Factory;

public class RunningTest {

	@Factory
	public Object[] factoryMethod() {

		Object myarray[] = new Object[5];

		for (int a = 0; a < 5; a++) {
			// providing different data
			myarray[a] = new FactoryTest(a);

		}
		return myarray;

	}
	
	

}
