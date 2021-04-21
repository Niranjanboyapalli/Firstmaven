package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class ERP_Functions {
	
	public static WebDriver driver;
	
	public static String verifyUrl(String url)
	{
		String res=null;
		System.setProperty("webdriver.chrome.driver","E://NewProjects//ERP_StockAccounting//CommonDrivers//chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		if(driver.findElement(By.name("btnsubmit")).isDisplayed())
		{
			res="Application lunch sucessfully";
		}
		else
		{
			res="Application launch failed";
		}
		
		return res;
	}
	
	public static String verifyLogin(String username,String password) throws Throwable
	{
		String res=null;
		WebElement user1=driver.findElement(By.xpath("//input[@id='username']"));
		user1.clear();
		user1.sendKeys(username);
		WebElement pass1=driver.findElement(By.xpath("//input[@id='password']"));
		pass1.clear();
		pass1.sendKeys(password);
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
		Thread.sleep(4000);
		if(driver.findElement(By.linkText("Logout")).isDisplayed())
		{
			res="Application login sucess";
		}
		else
		{
			res="Application login fail";
		}
		return res;
	
	}
	public static String verifySuppliers(String suppname,String addres,
String city,String country,String contperson,String phnum,String email,String mobilenum,String notes) throws Throwable
	{
		String res=null;
		driver.findElement(By.linkText("Suppliers")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//body/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
		Thread.sleep(4000);
		String expected=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.name("x_Supplier_Name")).sendKeys(suppname);
		driver.findElement(By.name("x_Address")).sendKeys(addres);
		driver.findElement(By.name("x_City")).sendKeys(city);
		driver.findElement(By.name("x_Country")).sendKeys(country);
		driver.findElement(By.name("x_Contact_Person")).sendKeys(contperson);
		driver.findElement(By.name("x_Phone_Number")).sendKeys(phnum);
		driver.findElement(By.name("x__Email")).sendKeys(email);
		driver.findElement(By.name("x_Mobile_Number")).sendKeys(mobilenum);
		driver.findElement(By.name("x_Notes")).sendKeys(notes);
		driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
		if(!driver.findElement(By.name("psearch")).isDisplayed())
		
			driver.findElement(By.xpath("//body/div[2]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]")).click();
			driver.findElement(By.name("psearch")).clear();
			driver.findElement(By.name("psearch")).sendKeys(expected);
			driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
	
		String actual=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr/td[6]/div/span/span")).getText();
		if(actual.equals(expected))
		{
			//Reporter.log("Supplier creation"+expected+" "+actual,true);
			res="PASS";
			
			
		}
		else
		{
			res="FAIL";
		}
		
		return res;
	}
	
	
public static String verifyCustomer(String Custname,String addres,String city,
		String country,String contperson,String phnum,String email,String mobilenum,String notes)throws Throwable
{
	String res=null;
	driver.findElement(By.xpath("//body/div[2]/div[2]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//body/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
	String expected=driver.findElement(By.name("x_Customer_Number")).getAttribute("value");
	driver.findElement(By.name("x_Customer_Name")).sendKeys(Custname);
	driver.findElement(By.name("x_Address")).sendKeys(addres);
	driver.findElement(By.name("x_City")).sendKeys(city);
	driver.findElement(By.name("x_Country")).sendKeys(country);
	driver.findElement(By.name("x_Contact_Person")).sendKeys(contperson);
	driver.findElement(By.name("x_Phone_Number")).sendKeys(phnum);
	driver.findElement(By.name("x__Email")).sendKeys(email);
	driver.findElement(By.name("x_Mobile_Number")).sendKeys(mobilenum);
	driver.findElement(By.name("x_Notes")).sendKeys(notes);
	Thread.sleep(4000);
	driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
	Thread.sleep(4000);
	driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
	Thread.sleep(4000);
	if(!driver.findElement(By.name("psearch")).isDisplayed())
		
		driver.findElement(By.xpath("//body/div[2]/div[3]/div[1]/div[1]/div[2]/div[2]/div[1]/button[1]/span[1]")).click();
		driver.findElement(By.name("psearch")).clear();
		driver.findElement(By.name("psearch")).sendKeys(expected);
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
		Thread.sleep(4000);
	String actual=driver.findElement(By.xpath("//table[@id='tbl_a_customerslist']/tbody/tr/td[5]/div/span/span")).getText();
	if(actual.equals(expected))
	{
		//Reporter.log("Supplier creation"+expected+" "+actual,true);
		res="PASS";
		
		
	}
	else
	{
		res="FAIL";
	}
	
	return res;
	
	
}
	

}
