package Com.NF.NetForumScenarious;

import java.io.IOException;
import java.util.ArrayList;







import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.*;

import com.relevantcodes.extentreports.LogStatus;




import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class VerifyOrganization extends TestBase
{	
	@Test(dependsOnMethods={"Com.NF.NetForumScenarious.Addorganization.AddRotaryOrganization"})
	@Parameters({"Verifyorg_Inputs"})
	
	public void verifyAddOrganization(String TestData) throws Exception
	{
		String Filepath=TestData.split(",")[0];
		String sheetname=TestData.split(",")[1];
		GenericInput xyz = new GenericInput();
		ArrayList<String> setusername=xyz.Set(Filepath,sheetname);
		String inputdata= setusername.get(0);
		System.out.println("inputdata:::"+inputdata);
		execute(inputdata);	
	}
	
	public void execute(String TestData) throws Exception
	{
		String Variable_ORGID=TestData.split(",")[0];
		String Variable_ORGNAME=TestData.split(",")[1];
		String expectedorgname=TestData.split(",")[2];
		String Dirpath=TestData.split(",")[3];
		String filepath=TestData.split(",")[4];
		
		try
		{
			Config.wt=new WebDriverWait(Config.driver, 10);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(By.linkText("More")));
			Config.wt=new WebDriverWait(Config.driver,10);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Org_Demographics")));
			WebElement orgname=Config.driver.findElement(GenericXpath.GetObject("Org_Name"));
			String ORGNAME=orgname.getText();
			System.out.println("Organization name="+ORGNAME);
			
			Config.SetValueInHashTable(Variable_ORGNAME, ORGNAME);
			WebElement orgid=Config.driver.findElement(GenericXpath.GetObject("Org_ID"));
			String ORGID=orgid.getText();
			System.out.println("Organization ID="+ORGID);
			Config.SetValueInHashTable(Variable_ORGID, ORGID);
			
	//		Assert.assertEquals("PASS:: Oganization Verification Done Successfully", expectedorgname, ORGNAME);
			
			if(ORGNAME.equalsIgnoreCase(expectedorgname))
			
			{
				Assert.assertTrue(true);
				String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
				test.log(LogStatus.PASS,"Organization verification is Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
				test.log(LogStatus.INFO, "Organization Verification is success");
			}
			else
			{
				Assert.assertTrue(false);
				String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
				test.log(LogStatus.FAIL,"Organization verification is Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
				test.log(LogStatus.INFO, "Organization Verification is success");
			}
		}catch(Exception ex)
		{
			Assert.assertTrue(false);
			String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
			test.log(LogStatus.ERROR,"Organization verification is Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
			test.log(LogStatus.INFO, "Organization Verification is success");
		}
		
	}
}
