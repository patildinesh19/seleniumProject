package Com.NF.Config;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Config
{
	public static WebDriver driver= null;
	public static WebDriverWait wt=null;
	
	public static Hashtable<String,String> ht=new Hashtable<String,String>();
	
	public static void SetValueInHashTable(String ParameterName, String ParameterValue)
	{
		ht.put(ParameterName, ParameterValue);		
	}
	public static String GetValueFromHashTable(String ParameterName)
	{
		return ht.get(ParameterName);
	}
	
	public static void fullscreenshot(String Dirpath,String filepath) throws IOException
	{
		File dir = new File(Dirpath);
		dir.mkdirs();
		File fullscreenshot= ((TakesScreenshot)Config.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fullscreenshot, new File(filepath));
	}
	
	public static String fullscreenshotextentreport(String Dirpath, String screenfilepath) throws IOException
	{
		File dir= new File(Dirpath);
		dir.mkdirs();
		File fullscreenshot=((TakesScreenshot)Config.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fullscreenshot, new File(screenfilepath));
		return screenfilepath;
	}
	
}