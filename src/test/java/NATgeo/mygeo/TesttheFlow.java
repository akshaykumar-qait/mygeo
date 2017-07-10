package NATgeo.mygeo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TesttheFlow {
	
	
	@AfterClass()
	void after_class()
	{
	System.out.println("after the class");	
	
	}
	
	@AfterTest()
	void after_test()
	{
		System.out.println("haha. after test");
	}
	
	@BeforeTest()
	void before_test()
	{
		System.out.println("before the test");
	}
	
	@BeforeClass
	void before_class()
	{
		System.out.println("before the class");
	}
	
	@AfterMethod
	void after_method()
	{
		System.out.println("after methods");
	}
	
	@BeforeMethod
	void before_method()
	{
		System.out.println("before the methods");
	}
	
	@Test(dependsOnMethods="test2")
	void test1()
	{
		System.out.println("test1");
	}
	
	
	@Test(dependsOnMethods="test3")
	void test2()
	{
		System.out.println("test2");	
	}
	
	@Test(dependsOnMethods="test4")
	void test3()
	{
		System.out.println("test3");
	}
	
	@Test(dependsOnMethods="test5")
	void test4()
	{
		System.out.println("test4");
	}
	
	@Test(dependsOnMethods="test6")
	void test5()
	{
		System.out.println("test5");
	}
	
	@Test(priority=0)
	void test6()
	{
		System.out.println("test6");
	}
	
	@Test(priority=1)
	void test7()
	{
		System.out.println("test7");
	}
	
	@Test(priority=2)
	void test8()
	{
		System.out.println("test8");
	}
	
	@Test(priority=3)
	void test9()
	{
		System.out.println("test9");
	}
	
}
