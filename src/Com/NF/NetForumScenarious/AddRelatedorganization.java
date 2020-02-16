package Com.NF.NetForumScenarious;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Com.NF.Config.Config;
import Com.NF.Config.TestBase;
import Com.NF.Generic.GenericInput;
import Com.NF.Generic.GenericXpath;

public class AddRelatedorganization extends TestBase
{
	@Test(dependsOnMethods={"Com.NF.NetForumScenarious.AddOrgMembership.addorgmembership"})
	@Parameters({"AddRelatedOrg"})
	public void AssignClubtoDistrict(String TestData) throws Exception
	{
		String FilePath=TestData.split(",")[0];
		String sheetname=TestData.split(",")[1];
		
		GenericInput xyz=new GenericInput();
		ArrayList<String> setdata=xyz.Set(FilePath, sheetname);
		String inputdata=setdata.get(0);
		System.out.println("InputData"+inputdata);
		execute(inputdata);
	}
	public void execute (String TestData)
	{
		String has_The_Relationship_of=TestData.split(",")[0];
		String to_Organization=TestData.split(",")[1];
		String reciprocal_Relationship=TestData.split(",")[2];
		String Dirpath=TestData.split(",")[3];
		String filepath=TestData.split(",")[4];
		String Dir_Path_For_AddRelationship_Page=TestData.split(",")[5];
		String File_Path_For_AddRelationship_Page=TestData.split(",")[6];
		try
		{
			Thread.sleep(3000);
			
			Config.wt= new WebDriverWait(Config.driver,1000);
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Membership WebLink")));
			
			
			if(Config.driver.findElement(GenericXpath.GetObject("Membership WebLink")) != null)
			{
				Config.driver.findElement(GenericXpath.GetObject("Membership WebLink")).click();
				addrelatedrg(has_The_Relationship_of,to_Organization,reciprocal_Relationship,Dirpath,filepath, Dir_Path_For_AddRelationship_Page,File_Path_For_AddRelationship_Page );	
				
			}
			else
			{	Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Membership WebLink")));
				addrelatedrg(has_The_Relationship_of,to_Organization,reciprocal_Relationship,Dirpath,filepath,Dir_Path_For_AddRelationship_Page,File_Path_For_AddRelationship_Page  );
				
			}				
			
		}catch(Exception ex){System.out.println("Child Form is not Present");}		
	}
	public void addrelatedrg(String has_The_Relationship_of, String to_Organization, String reciprocal_Relationship, String Dirpath, String filepath, String Dir_Path_For_AddRelationship_Page, String File_Path_For_AddRelationship_Page ) throws Exception 
	{
		try{
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("Add - related organizations WebImage")));
			Config.driver.findElement(GenericXpath.GetObject("Add - related organizations WebImage")).click();
			test.log(LogStatus.INFO, "Clicked on Add Realated org button");
			Thread.sleep(3000);
			Config.driver.switchTo().frame("iframe1");
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("has the relationship of WebList")));
			
			WebElement hasrelationship=Config.driver.findElement(GenericXpath.GetObject("has the relationship of WebList"));
			Select selectrelationship=new Select(hasrelationship);
			selectrelationship.selectByValue(has_The_Relationship_of);
			test.log(LogStatus.INFO, "Has Realtionship selected");
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("to organization WebEdit")));
			Config.driver.findElement(GenericXpath.GetObject("to organization WebEdit")).sendKeys(to_Organization);
			test.log(LogStatus.INFO, "Orgnization is selected");
			Config.driver.findElement(GenericXpath.GetObject("Look up for organization WebImage")).click();
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Clicked on Lookup");
			Config.wt.until(ExpectedConditions.presenceOfElementLocated(GenericXpath.GetObject("reciprocal relationship WebList")));
			
			WebElement reciprocalrelation=Config.driver.findElement(GenericXpath.GetObject("reciprocal relationship WebList"));
			Select selectreciporcalvalue=new Select(reciprocalrelation);
			selectreciporcalvalue.selectByValue(reciprocal_Relationship);
			test.log(LogStatus.INFO,"Reciprocal Relationship is selected");
			String scrrenshotpathAddrelationship=Config.fullscreenshotextentreport(Dir_Path_For_AddRelationship_Page, File_Path_For_AddRelationship_Page);
			test.log(LogStatus.PASS,"Organization Assign to District Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpathAddrelationship));
			test.log(LogStatus.INFO, "Organization Assign to District Success");
			Thread.sleep(3000);
			Config.driver.findElement(GenericXpath.GetObject("Save WebButton")).click();
			Assert.assertTrue(true);
			String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
			test.log(LogStatus.PASS,"Organization Assign to District Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
			test.log(LogStatus.INFO, "Organization Assign to District Success");
			
		}catch(Exception ex)
		{
			Assert.assertTrue(false);
			String scrrenshotpath=Config.fullscreenshotextentreport(Dirpath, filepath);
			test.log(LogStatus.FAIL,"Organization Assign to District Success", test.addScreenCapture(System.getProperty("user.dir")+"/"+scrrenshotpath));
			test.log(LogStatus.INFO, "Organization Assign to District Success");
		}	
		
		
	}
	
	
}
