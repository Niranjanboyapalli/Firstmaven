package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;

	public static WebDriver startBrowser() throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
		{
			System.out.println("Exeuting the chrome driver");
			System.setProperty("webdriver.chrome.driver", "E://NewProjects//ERP_MAVEN_ONE//CommonDrivers//chromedriver.exe");
			driver=new ChromeDriver();
			
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.out.println("Executing the Firefox");
			System.setProperty("webdriver.gecko.driver", "E://NewProjects//ERP_MAVEN_ONE//CommonDrivers//geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser key is not matching");
		}
		return driver;
	}
	
	public static void openApplication(WebDriver driver) throws Throwable
	{
		
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().window().maximize();
	}
	
	public static void waitForElement(WebDriver driver,String Locatortype,String Locatorvalue,String Testdata)
	{
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(Testdata));
		if(Locatortype.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locatorvalue)));
		}
		else if(Locatortype.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locatorvalue)));
		}
		else if(Locatortype.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locatorvalue)));
		}
	}

	public static void typeAction(WebDriver driver,String Locatortype,String Locatorevalue,String Testdata)
	{
		if(Locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locatorevalue)).clear();
			driver.findElement(By.id(Locatorevalue)).sendKeys(Testdata);
		}
		else if(Locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locatorevalue)).clear();
			driver.findElement(By.xpath(Locatorevalue)).sendKeys(Testdata);
			
		}
		else if(Locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locatorevalue)).clear();
			driver.findElement(By.name(Locatorevalue)).sendKeys(Testdata);
		}
		else
		{
			System.out.println("unble to locate the element");
		}
	}
	
	
	public static void clickAction(WebDriver driver,String Locatortype,String Locatorvalue)
	{
	if(Locatortype.equalsIgnoreCase("id"))
	{
		driver.findElement(By.id(Locatorvalue)).sendKeys(Keys.ENTER);
	}
	else if(Locatortype.equalsIgnoreCase("xpath"))
	{
		driver.findElement(By.xpath(Locatorvalue)).click();
		
	}
	else if(Locatortype.equalsIgnoreCase("name"))
	{
		driver.findElement(By.name(Locatorvalue)).click();
	}
	}

	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	public static String generateDate()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(d);
				
	}
	
	public static void captureData(WebDriver driver,String Locatortype,String Locatorvalue) throws Throwable
	{
		String snumber="";
		if(Locatortype.equalsIgnoreCase("id"))
		{
			snumber=driver.findElement(By.id(Locatorvalue)).getAttribute("value");
		}
		else if(Locatortype.equalsIgnoreCase("xpath"))
		{
			snumber=driver.findElement(By.xpath(Locatorvalue)).getAttribute("value");
		}
		else if(Locatortype.equalsIgnoreCase("name"))
		{
			snumber=driver.findElement(By.name(Locatorvalue)).getAttribute("value");
		}
		
		//FileInputStream fs=new FileInputStream("E://NewProjects//ERP_StockAccounting//CaptureData//capture.txt");
		FileWriter fw=new FileWriter("E://NewProjects//ERP_MAVEN_ONE//CaptureData//capture.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(snumber);
		bw.flush();
		bw.close();
	}
	
	public static void tablevalidation(WebDriver driver,String testdata) throws Throwable
	{
		FileReader fr=new FileReader("E://NewProjects//ERP_MAVEN_ONE//CaptureData//capture.txt");
		BufferedReader br=new BufferedReader(fr);
		String expected_data=br.readLine();
		int columndata=Integer.parseInt(testdata);
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).isDisplayed())
		
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanel"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).sendKeys(expected_data);
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbutton"))).click();
			Thread.sleep(4000);
			WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stable")));
			List<WebElement> rows=table.findElements(By.tagName("tr"));
			for(int i=1;i<=rows.size();i++)
			{
				String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+columndata+"]/div/span/span")).getText();
				Assert.assertEquals(expected_data, act_data,"supplier is not matching");
				System.out.println(act_data+"  "+expected_data);
				break;
			}
		
		
	}

	public static void stockCategories(WebDriver driver) throws Throwable
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(driver.findElement(By.linkText("Stock Items"))).perform();
		Thread.sleep(4000);
		ac.moveToElement(driver.findElement(By.xpath("//li[@id='mi_a_stock_categories']/a"))).click();
		ac.build().perform();
		Thread.sleep(4000);
	}
	
	public static void stockValidation(WebDriver driver,String Testdata) throws Throwable
	{
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).isDisplayed())
			
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanel1"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).sendKeys(Testdata);
			Thread.sleep(4000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbutton1"))).click();
			Thread.sleep(4000);
			WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stocktable")));
			List<WebElement> rows=table.findElements(By.tagName("tr"));
			for(int i=1;i<rows.size();i++)
			{
				String Act_dat=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
				Assert.assertEquals(Testdata, Act_dat,"stock is not added");
				System.out.println(Testdata+"  "+Act_dat);
				break;
			}
			
	}

	
	
	
}
