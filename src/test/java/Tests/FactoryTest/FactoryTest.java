package Tests.FactoryTest;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

 class FactoryTest {
	    private int param = 0;
	 
	    public FactoryTest(int a) {
			// TODO Auto-generated constructor stub
		
	    param=a;
	    
	    }
	    
	    
	    @Test
	    public void testMethodOne() {
	        int opValue = param *param;
	        System.out.println("Test method one output: " + param);
	    }
	 
	    @Test
	    public void testMethodTwo() {
	        
	        System.out.println("Test method two output: " + param);
	    }
	}
	 
	