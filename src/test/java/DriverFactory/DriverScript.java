package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript {
	String inputpath="E://NewProjects//ERP_MAVEN_ONE//TestInput//InputSheet.xlsx";
	String outputpath="E://NewProjects//ERP_MAVEN_ONE//TestOutput//hybrideresults.xlsx";
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	public void startTest() throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowCount("MasterTestCases");i++)
		{
			if(xl.getCelldata("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=xl.getCelldata("MasterTestCases", i, 1);
				report=new ExtentReports("./extentreports/"+TCModule+FunctionLibrary.generateDate()+".html");
				for(int j=1;j<=xl.rowCount(TCModule);j++)
				{
					test=report.startTest(TCModule);
					String Description=xl.getCelldata(TCModule, j, 0);
					String Function_Name=xl.getCelldata(TCModule, j, 1);
					String Locator_type=xl.getCelldata(TCModule, j, 2);
					String Locator_Value=xl.getCelldata(TCModule, j, 3);
					String Test_Data=xl.getCelldata(TCModule, j, 4);
					try{
						if(Function_Name.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser();
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("captureData"))
						{
							FunctionLibrary.captureData(driver, Locator_type, Locator_Value);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("tablevalidation"))
						{
							FunctionLibrary.tablevalidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("stockCategories"))
						{
							FunctionLibrary.stockCategories(driver);
							test.log(LogStatus.INFO, Description);
						}
						else if(Function_Name.equalsIgnoreCase("stockValidation"))
						{
							FunctionLibrary.stockValidation(driver, Test_Data);
							test.log(LogStatus.INFO, Description);
						}
						xl.setCelldata(TCModule, j, 5, "pass",outputpath);
						test.log(LogStatus.PASS, Description);
						xl.setCelldata("MasterTestCases", i, 3, "pass", outputpath);
						
							
					}catch(Exception e)
					{
						System.out.println(e.getMessage());
						xl.setCelldata(TCModule, j, 5, "Fail", outputpath);
						test.log(LogStatus.FAIL, Description);
						File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screen,new File("./screens/"+TCModule+FunctionLibrary.generateDate()+".png"));
						xl.setCelldata("MasterTestCases", i, 3, "Fail", outputpath);
					}

					report.endTest(test);
					report.flush();

				}
				
			}
			else
			{
				xl.setCelldata("MasterTestCases", i, 3, "Not executed", outputpath);
			}
		}
		
	}

}
