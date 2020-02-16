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

import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class FindOrganization extends TestBase
{
	@Test
	@Parameters({"FindOrganization_Inputs"})
	public void FindOrganization(String TestData) throws Exception
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
		String DirPath= TestData.split(",")[0];
		String FilePath=TestData.split(",")[1];
		
		String org_ID=Config.GetValueFromHashTable("Organization_ID");
		System.out.println("Organization_ID_Store"+org_ID);
		String Org_Name=Config.GetValueFromHashTable("Organization_Name");
		System.out.println("Organization_Name_Store"+Org_Name);
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
				Thread.sleep(1000);
			}
			else
			{
				Config.driver.findElement(By.linkText("Find Organization")).click();
				Thread.sleep(1000);
			}
			Config.wt=new WebDriverWait(Config.driver, 1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Search_Org_By_ID")));
			
			Config.driver.findElement(GenericXpath.GetObject("Search_Org_By_ID")).sendKeys(org_ID);
			Config.driver.findElement(GenericXpath.GetObject("Search_Org_By_Name")).sendKeys(Org_Name);
			Config.fullscreenshot(DirPath, FilePath);
			Config.driver.findElement(GenericXpath.GetObject("Search_Org_go")).click();
			System.out.println("Find Organization Successfully");
			Assert.assertTrue(true);
			
		}catch(Exception ex)
		{
			Assert.assertTrue(false);
			System.out.println("Find Organization Fail");
			Config.fullscreenshot(DirPath, FilePath);
		}
		
		
	}
}
