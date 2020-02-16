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

import com.relevantcodes.extentreports.LogStatus;

import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class fFindOrganizationbyexcelvalue extends TestBase
{
	@Test
	@Parameters({"FindOrganization_Inputs_excelsheet"})
	public void findorgbyexcelvalues(String TestData) throws Exception
	{
		String Filepath=TestData.split(",")[0];
		String sheetname=TestData.split(",")[1];
		GenericInput xyz= new GenericInput();
		ArrayList<String> findorfinput=xyz.Set(Filepath, sheetname);
		String inputdata=findorfinput.get(0);
		System.out.println("InputData:" +inputdata);
		execute(inputdata);
		
	}
	public void execute(String TestData) throws Exception
	{
		String Organization_Name=TestData.split(",")[0];
		String DirPath= TestData.split(",")[1];
		String FilePath=TestData.split(",")[2];
		
		try{
			Thread.sleep(2000);
			Config.wt= new WebDriverWait(Config.driver, 1000);
			//Config.wt.until(ExpectedConditions.presenceOfElementLocated(By.linkText("CRM")));
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("CRM_LINK")));
			Config.driver.findElement(By.linkText("CRM")).click();
			Thread.sleep(1000);
			Config.wt= new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Orgnization_Menu_Weblink")));
			WebElement ntr=Config.driver.findElement(GenericXpath.GetObject("Orgnization_Menu_Weblink"));
			if(ntr.isDisplayed())
			{
				Config.driver.findElement(GenericXpath.GetObject("Orgnization_Menu_Weblink")).click();
				Thread.sleep(1000);
				Config.wt=new WebDriverWait(Config.driver,1000);
				Config.driver.findElement(By.linkText("Find Organization")).click();
				test.log(LogStatus.INFO, "Find Organization page open");
				Thread.sleep(1000);
			}
			else
			{
				Config.driver.findElement(By.linkText("Find Organization")).click();
				Thread.sleep(1000);
				test.log(LogStatus.INFO, "Find Org Page open sucessfully");
			}
			Config.wt=new WebDriverWait(Config.driver, 1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Search_Org_By_ID")));
			
			Config.driver.findElement(GenericXpath.GetObject("Search_Org_By_Name")).sendKeys(Organization_Name);
			//Config.fullscreenshot(DirPath, FilePath);
			String screenshotpath=Config.fullscreenshotextentreport(DirPath, FilePath);
			test.log(LogStatus.PASS, "FindORG Successfully", test.addScreenCapture(System.getProperty("user.dir")+"/"+screenshotpath));
			Config.driver.findElement(GenericXpath.GetObject("Search_Org_go")).click();
			System.out.println("Find Organization Successfully");
			Assert.assertTrue(true);
			
		}catch(Exception ex)
		{
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Org Find Fail");
			System.out.println("Find Organization Fail");
			//Config.fullscreenshot(DirPath, FilePath);
			String Failscreenshotpath=Config.fullscreenshotextentreport(DirPath, FilePath);
			test.log(LogStatus.FAIL, "Find Org Fail", test.addScreenCapture(System.getProperty("user.dir")+"/"+Failscreenshotpath));
		}
		
		
	}
}
