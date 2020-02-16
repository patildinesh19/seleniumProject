package Com.NF.NetForumScenarious;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;


public class AddOrgMembership extends TestBase
{
	@Test(dependsOnMethods={"Com.NF.NetForumScenarious.VerifyOrganization.verifyAddOrganization"})
	@Parameters({"AddMembership"})
	
	public void addorgmembership(String InputData) throws Exception
	{
		String Filepath=InputData.split(",")[0];
		String sheetname=InputData.split(",")[1];
		GenericInput xyz = new GenericInput();
		ArrayList<String> readdata=xyz.Set(Filepath, sheetname);
		String inputdata= readdata.get(0);
		System.out.println("IputData for Add Membership:"+inputdata);
		execute(inputdata);
		
		
	}
	public void execute(String TestData) throws IOException
	{
		String membershiptype=TestData.split(",")[0];
		String membershipstatus=TestData.split(",")[1];
		String Invoicenmae=TestData.split(",")[2];
		String Dirpath=TestData.split(",")[3];
		String filepath=TestData.split(",")[4];
		
		try
		{
			Config.wt=new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("add membership WebLink")));
			Config.driver.findElement(GenericXpath.GetObject("add membership WebLink")).click();
			//test.log(LogStatus.INFO, "Membership link clicked successfully");
			Thread.sleep(3000);
			Config.driver.switchTo().frame("iframe1");
			Config.wt=new WebDriverWait(Config.driver,10);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("member package Look-up WebLink")));
			Config.driver.findElement(GenericXpath.GetObject("member package Look-up WebLink")).click();
			//test.log(LogStatus.INFO, "Clicked on membership lookup");
			Thread.sleep(3000);
			Config.wt=new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Select_club_Membership")));
			Config.driver.findElement(GenericXpath.GetObject("Select_club_Membership")).click();
			//test.log(LogStatus.INFO, "Mebership Active status selected");
			Thread.sleep(3000);
			WebElement meberstatus=Config.driver.findElement(GenericXpath.GetObject("member status WebList"));
			Select status=new Select(meberstatus);
			status.selectByValue(membershipstatus);
			String membstats = meberstatus.getAttribute("value");
			//test.log(LogStatus.INFO, "Membership status checked Active");
			WebElement batchfield=Config.driver.findElement(GenericXpath.GetObject("Invoice and Payment Information batch WebList"));
			Select selectbatch = new Select(batchfield);
			selectbatch.selectByValue(Invoicenmae);
			Thread.sleep(3000);
			WebElement invoicename=Config.driver.findElement(GenericXpath.GetObject("Invoice and Payment Information batch WebList"));
			String batchname=invoicename.getAttribute("value");
			//test.log(LogStatus.INFO, "Batch Name selected");
			Thread.sleep(1000);
			String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
			//test.log(LogStatus.PASS,"Organization Membership Added Sucessfully", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
			//test.log(LogStatus.INFO, "Organization Membership Added Sucessfully");
						
			Config.driver.findElement(GenericXpath.GetObject("Save WebButton")).click();
			
			Assert.assertTrue(true);
			
		}catch(Exception ex)
		{
			Assert.assertTrue(false);
			String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
			//test.log(LogStatus.FAIL,"Organization Membership Not Added", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
			//test.log(LogStatus.INFO, "Organization Membership Not Added");
			
		}
		
	}
}
