package Com.NF.NetForumScenarious;


import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class Addorganization extends TestBase
{	
	@Test (dependsOnMethods={"Com.NF.NetForumScenarious.LaunchandLoginintoNF15.LaunchApplication"})
	@Parameters({"Add_Organization_Inputs"})
	
	public void AddRotaryOrganization(String TestData) throws Exception
	{
		String filepath=TestData.split(",")[0];
		String sheetname=TestData.split(",")[1];
		GenericInput xyz = new GenericInput();
		ArrayList<String> setusername=xyz.Set(filepath,sheetname);
		String inputdata= setusername.get(0);
		System.out.println("inputdata:::"+inputdata);
		execute(inputdata);	
	}
	public void execute(String TestData) throws Exception
	{
		String orgname=TestData.split(",")[0];
		String orgtype=TestData.split(",")[1];
		String addresstype=TestData.split(",")[2];
		String address=TestData.split(",")[3];
		String country=TestData.split(",")[4];
		String state=TestData.split(",")[5];
		String city=TestData.split(",")[6];
		//String Variable_ORGID=TestData.split(",")[7];
		//String Variable_ORGNAME=TestData.split(",")[8];
		String Dirpath=TestData.split(",")[7];
		String filepath=TestData.split(",")[8];
		String Dir_Path_Add_Org_Page=TestData.split(",")[9];
		String File_Path_Add_Org_Page=TestData.split(",")[10];
				
		try
		{
			
			//Navigate to Add organization From
			Config.wt=new WebDriverWait(Config.driver,10000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("CRM_LINK")));
			Thread.sleep(5000);
			
			//Config.driver.findElement(By.linkText("CRM")).click();
			Config.wt=new WebDriverWait(Config.driver,10);
			WebElement ntr=Config.driver.findElement(GenericXpath.GetObject("Orgnization_Menu_Weblink"));
			if(ntr.isDisplayed())
			{
				Config.driver.findElement(GenericXpath.GetObject("Orgnization_Menu_Weblink")).click();
				Config.wt=new WebDriverWait(Config.driver,1000);
				Config.wt.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Add Organization")));
				Config.driver.findElement(By.linkText("Add Organization")).click();
				test.log(LogStatus.INFO, "Clicked On Add org");
			}
			else
			{
				Config.driver.findElement(By.linkText("Add Organization")).click();
				test.log(LogStatus.INFO, "Clicked On Add org");
			}
			Config.wt=new WebDriverWait(Config.driver,10);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Name_WebEdit")));
			Config.driver.findElement(GenericXpath.GetObject("Name_WebEdit")).sendKeys(orgname);
			test.log(LogStatus.INFO, "Organization Name is Entered");
			Config.driver.findElement(GenericXpath.GetObject("OrganizationType_WebList")).sendKeys(orgtype);
			test.log(LogStatus.INFO, "Organization Type Is selected");
			Thread.sleep(5000);
			Config.wt=new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("AddressType1_WebList")));
			WebElement addtype=Config.driver.findElement(GenericXpath.GetObject("AddressType1_WebList"));
			Select selectitem=new Select(addtype);
			selectitem.selectByVisibleText(addresstype);
			test.log(LogStatus.INFO,"Address Type Is Selected	");
			Thread.sleep(5000);
			Config.wt=new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Address1_WebEdit")));
			
			Config.driver.findElement(GenericXpath.GetObject("Address1_WebEdit")).sendKeys(address);
			test.log(LogStatus.INFO, "Address is selected");
		
			WebElement Coun=Config.driver.findElement(GenericXpath.GetObject("Country1_WebList"));
			Select selectitem1=new Select(Coun);
			selectitem1.selectByValue(country);
			test.log(LogStatus.INFO, "Country Is Selected for ORG");
			Thread.sleep(3000);
			WebElement countryname=Config.driver.findElement(GenericXpath.GetObject("Country1_WebList"));
			String country1=countryname.getAttribute("value");
			System.out.println("Country Name"+country1);
		
			Config.wt= new WebDriverWait(Config.driver,5000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("State1_WebList")));
		
			if(country1.equalsIgnoreCase("United States")|| country1.equalsIgnoreCase("Canada"))
			{
				Select dropdown5 = new Select(Config.driver.findElement(GenericXpath.GetObject("State1_WebList")));
				dropdown5.selectByValue(state);
				test.log(LogStatus.INFO, "State is selected for US or Canada Country");
				Thread.sleep(3000);
			}
			else
			{
				Config.driver.findElement(GenericXpath.GetObject("internationalprovince_WebEdit")).sendKeys("state");
				test.log(LogStatus.INFO, "State is Selected for Other countries");
			}
			Config.driver.findElement(GenericXpath.GetObject("City1_WebEdit")).sendKeys(city);
			test.log(LogStatus.INFO, "City is selected for ORG");
			String screenfilepathAddorgpage= Config.fullscreenshotextentreport(Dir_Path_Add_Org_Page, File_Path_Add_Org_Page);
			test.log(LogStatus.PASS, "Test case pass", test.addScreenCapture(System.getProperty("user.dir")+"/"+screenfilepathAddorgpage));
			test.log(LogStatus.INFO, "Screenshotcaptured");
		
			Config.driver.findElement(GenericXpath.GetObject("Save_WebButton")).click();
		
			test.log(LogStatus.INFO,"Organization Added Sucessfully");
			
			Assert.assertTrue(true);
			String screenfilepath= Config.fullscreenshotextentreport(Dirpath, filepath);
			test.log(LogStatus.PASS, "Test case pass", test.addScreenCapture(System.getProperty("user.dir")+"/"+screenfilepath));
			test.log(LogStatus.INFO, "Screenshotcaptured");
		
			}catch(Exception ex)
			{
				Assert.assertTrue(false);
				String screenfilepath= Config.fullscreenshotextentreport(Dirpath, filepath);
				test.log(LogStatus.FAIL,"Test Is Failed",test.addScreenCapture(System.getProperty("user.dir")+"/"+screenfilepath));
				test.log(LogStatus.INFO, "Organization Add is Failed");
				
			}
		
	}
	
}	