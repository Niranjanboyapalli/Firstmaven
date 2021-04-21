package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil {

	public static String getValueForKey(String key) throws Throwable
	{
		Properties configProperties=new Properties();
		configProperties.load(new FileInputStream("E://NewProjects//ERP_MAVEN_ONE//Propertyfile//Environment.properties"));

		return configProperties.getProperty(key);

	}

}
