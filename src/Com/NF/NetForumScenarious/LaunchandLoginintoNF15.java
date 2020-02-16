package Com.NF.NetForumScenarious;

import java.util.ArrayList;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;




import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class LaunchandLoginintoNF15 extends TestBase
{
	@Test
	@Parameters({"Launch_Application_inputs"})
	
	public void LaunchApplication(String TestData) throws Exception
	{
		String filepath=TestData.split(",")[0];
		System.out.println("filepath"+filepath);
		String sheetname=TestData.split(",")[1];
		System.out.println("sheetname"+sheetname);
		GenericInput xyz = new GenericInput();
		ArrayList<String> setusername=xyz.Set(filepath,sheetname);
		String inputdata= setusername.get(0);
		System.out.println("inputdata:::"+inputdata);
		execute(inputdata);	
	}
	public void execute(String TestData)
	{
		//Receive the input
		String Browercode=TestData.split(",")[0];
		String URL=TestData.split(",")[2];
		//String Expectedtitle=TestData.split(",")[2];
		//String Status=null;
		//String ActualResult=null;		
		 String Platform=TestData.split(",")[1];
	     //for running in distributed env
	     String RunUsingSG=TestData.split(",")[3];
		 System.out.println(System.getProperty("user.dir"));
	    	DesiredCapabilities dc=new DesiredCapabilities();
			
		//Perform the operation
		try{
			if(Browercode.equalsIgnoreCase("IE")&& RunUsingSG.equalsIgnoreCase("N"))
			{ 
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"/IEDriverServer_Win32_2.53.1/IEDriverServer.exe");
				dc.setBrowserName("internet explorer");
				//dc.setVersion(Version);
				if(Platform.equalsIgnoreCase("WINDOWS"))
				{dc.setPlatform(org.openqa.selenium.Platform.WINDOWS);}
				Config.driver = new InternetExplorerDriver(dc);
					
			}		
			if(Browercode.contentEquals("CH") && RunUsingSG.equalsIgnoreCase("Y"))
			{
				System.out.println("*******************");
				System.out.println("launching chrome browser");
				System.setProperty("webdriver.chrome.driver","C:\\NF-15_Workspace\\NetForumUpgrade\\chromedriver_win32\\chromedriver.exe");
				Config.driver = new ChromeDriver();
				test.log(LogStatus.INFO, "Browser is Launched");
				Config.driver.manage().window().maximize();
				
				 
			}
			Thread.sleep(100);
			Config.driver.get(URL);
			test.log(LogStatus.INFO, "URL is OPEN");
			Config.wt=new WebDriverWait(Config.driver,10000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("CRM_LINK")));
			Thread.sleep(5000);
			
			
				
			
			//Config.driver.manage().window().maximize();
			/*if(Browercode.contentEquals("FF"))
			{
				Config.driver=new FirefoxDriver();
			}
			if(Browercode.contentEquals("IE"))
			{
				System.setProperty("webdriver.ie.driver","C:\\NF-15_Workspace\\NetForumUpgrade\\IEDriverServer.exe");

				Config.driver = new InternetExplorerDriver();
				 
			}
			
			
			/*String ActualTitle=Config.driver.getTitle();
			if(ActualTitle.equalsIgnoreCase(Expectedtitle))
			{
				System.out.println("Launch Application PASS");				
			}
			else
			{
				System.out.println("Launch Application FAILED");
				System.out.println("Launch Application FAILED second time");
	
			}*/
			
			System.out.println("Launch Application PASS");
			
			
			
		}
		catch(Exception ex)
		{
			Assert.assertTrue(false);
			System.out.println("Launch Application ERROR");
			
		}
	}

}
