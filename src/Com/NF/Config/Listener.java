package Com.NF.Config;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class Listener extends TestListenerAdapter
{
	public void onTestStart(ITestResult tr)
	{
		System.out.println("Test is Started:"+tr.getName());
	}
	public void ontestFailuer (ITestResult tr)
	{
		System.out.println("Test Is Failed:"+tr.getName());
	}
	public void onTestSuccess(ITestResult tr)
	{
		System.out.println("Test Is Success:"+tr.getName());
	}
	public void onTestSkipped(ITestResult tr)
	{
		System.out.println("Test Is Skipped:"+tr.getName());
	}
	
}
