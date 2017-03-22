package Utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import DriverScript.AppDriver;

public class genericFunctions {
	//#############################################################
	//#Function Name:
	//#Created By:
	//#Created Date:
	//#############################################################	
	public void Fn_LaunchApp()
	{
		AppDriver.driver.get(AppDriver.vProjectURL);
		AppDriver.driver.manage().window().maximize();
		AppDriver.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	//#############################################################
	//#Function Name:
	//#Created By:
	//#Created Date:
	//#############################################################		
	public void Fn_verifyTitle(String vExpTitle) throws Throwable
	{
		String vActTitle=AppDriver.driver.getTitle().trim();
		if(vActTitle.equals(vExpTitle))
		{
			System.out.println("PASSED");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_verifyTitle", "	Title should be matched successfully", "title matched", "PASSED");
		}
		else
		{
			System.out.println("FAILED");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_verifyTitle", "	Title should be matched successfully", "title not matched", "FAILED");
		}
	}
	
	//#############################################################
		//#Function Name:
		//#Created By:
		//#Created Date:
		//#############################################################	
	public void Fn_verifyObjectExist(List<WebElement> elemnts,int ObjCnt) throws Throwable
	{
		if(elemnts.size()==ObjCnt)
		{
			System.out.println("PASSED");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_verifyObjectExist", "Object should be exist on page", "Object exist", "PASSED");
		}
		else
		{
			System.out.println("FAILED");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_verifyObjectExist", "Object should be exist on page", "Object not exist", "FAILED");
		}
	}
	//#############################################################
		//#Function Name:
		//#Created By:
		//#Created Date:
		//#############################################################	
	public void Fn_SetValue(List<WebElement> elemnts,String vVal) throws Throwable
	{
		if(elemnts.size()>0)
		{
			elemnts.get(0).clear();
			elemnts.get(0).sendKeys(vVal);
			if(elemnts.get(0).getAttribute("value").equalsIgnoreCase(vVal))
			{
				System.out.println("PASSED");
				AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SetValue", vVal+"value should be entered successfully", vVal+"value entered", "PASSED");
			}
			else
			{
				System.out.println("FAILED");
				AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SetValue", vVal+"value should be entered successfully", vVal+"value not entered", "FAILED");
			}
		}
		else
		{
			System.out.println("Locator not found");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SetValue", vVal+"value should be entered successfully", vVal+"Locator not found", "FAILED");
		}
	}
	
	//#############################################################
		//#Function Name:
		//#Created By:
		//#Created Date:
		//#############################################################	
	public void Fn_SelectValue(List<WebElement> elemnts,String vVal) throws Throwable
	{
		if(elemnts.size()>0)
		{			
			elemnts.get(0).sendKeys(vVal);
			if(elemnts.get(0).getAttribute("value").equalsIgnoreCase(vVal))
			{
				System.out.println("PASSED");
				AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SelectValue", vVal+"value should be selected successfully", vVal+"value selected", "PASSED");
			}
			else
			{
				System.out.println("FAILED");
				AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SelectValue", vVal+"value should be selected successfully", vVal+"value not selected", "FAILED");
			}
		}
		else
		{
			System.out.println("Locator not found");
			AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_SelectValue", vVal+"value should be selected successfully", vVal+"Locator not found", "FAILED");
		}
	}
	
	//#############################################################
		//#Function Name:
		//#Created By:
		//#Created Date:
		//#############################################################	
 public void Fn_Click(List<WebElement> elemnts) throws Throwable
 {
	 if(elemnts.size()>0)
	 {
		 elemnts.get(0).click();
		 System.out.println("PASSED");
		 AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_Click", "Object should be click","Object Clicked", "PASSED");
	 }
	 else
	 {
		 System.out.println("Locator not Found");
		 AppDriver.hr.fgInsertResult(AppDriver.vResultRes, "Fn_Click", "Object should be click","Object not Clicked", "FAILED");
	 }
 }
}
