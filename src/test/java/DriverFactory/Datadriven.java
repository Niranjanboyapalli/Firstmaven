package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class Datadriven {
	WebDriver driver;
	String inputpath="E://NewProjects//ERP_StockAccounting//TestInput//TestData.xlsx";
	String outputpath="E://NewProjects//ERP_StockAccounting//TestOutput//testresults.xlsx";
@BeforeTest
public void setup() throws Throwable
{
	String launch=ERP_Functions.verifyUrl("http://projects.qedgetech.com/webapp/");
	Reporter.log(launch,true);
	String login=ERP_Functions.verifyLogin("admin", "master");
	Reporter.log(login,true);
	
}
//@Test
//public void supplierr() throws Throwable
//{
	
	//ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//int rc=xl.rowCount("Supplier");
	//Reporter.log("Number of rows are::"+rc,true);
	//for(int i=1;i<=rc;i++)
	//{
		//String suppname=xl.getCelldata("Supplier", i, 0);
		//String addres=xl.getCelldata("Supplier", i, 1);
		//String city=xl.getCelldata("Supplier", i, 2);
		//String country=xl.getCelldata("Supplier", i, 3);
		//String contperson=xl.getCelldata("Supplier", i, 4);
		//String phnum=xl.getCelldata("Supplier", i, 5);
		//String email=xl.getCelldata("Supplier", i, 6);
		//String mobilenum=xl.getCelldata("Supplier", i, 7);
	//	String notes=xl.getCelldata("Supplier", i, 8);
		//String Results=ERP_Functions.verifySuppliers(suppname, addres, city, country, contperson, phnum, email, mobilenum, notes);
		 //xl.setCelldata("Supplier", i, 9, Results, outputpath);
	//}
//}
@Test
public void customer() throws Throwable
{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("Customer");
	Reporter.log("number of rows are::"+rc,true);
	for(int i=1;i<rc;i++)
	{
		String Custname=xl.getCelldata("Customer", i, 0);
		String addres=xl.getCelldata("Customer", i, 1);
		String city=xl.getCelldata("Customer", i, 2);
		String country=xl.getCelldata("Customer", i, 3);
		String contperson=xl.getCelldata("Customer", i, 4);
		String phnum=xl.getCelldata("Customer", i, 5);
		String email=xl.getCelldata("Customer", i, 6);
		String mobilenum=xl.getCelldata("Customer", i, 7);
		String notes=xl.getCelldata("Customer", i, 8);
		String Results=ERP_Functions.verifyCustomer(Custname, addres, city, country, contperson, phnum, email, mobilenum, notes);
		xl.setCelldata("Customer", i, 9, Results, outputpath);
	}
	
}

@AfterTest
public void teardown()
{
	driver.close();
}
}
