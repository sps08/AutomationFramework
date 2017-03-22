package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import DriverScript.AppDriver;
import DriverScript.Xls_Reader;
//#############################################################
//#Function Name:
//#Created By:
//#Created Date:
//#############################################################	
public class keywordDriver {
	public String vExp,vObjName,vObjStr,vVal;
	public List<WebElement> elemts=null;
	genericFunctions gf=new genericFunctions();
	public void keyworddriver(String vKeywords,Xls_Reader xrs,int m,Xls_Reader xrtd,int k) throws Throwable
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\Locators\\"+AppDriver.vLocatorFile+".properties");
		prop.load(fis);
		switch (vKeywords)
		{
		case "Fn_LaunchApp":
			gf.Fn_LaunchApp();
			break;
		case "Fn_verifyTitle":
			vExp=PlaceHolder(xrs.getCellData(AppDriver.vModuleName, "Param1", m),xrtd,k);
			gf.Fn_verifyTitle(vExp);
			break;
		case "Fn_verifyObjectExist":
			vObjName=xrs.getCellData(AppDriver.vModuleName, "Locator1", m);
			vObjStr=prop.getProperty(vObjName);
			elemts=ExtractLocators(vObjStr);
			vExp=PlaceHolder(xrs.getCellData(AppDriver.vModuleName, "Param1", m),xrtd,k);
			int ObjCnt=Integer.parseInt(vExp);
			gf.Fn_verifyObjectExist(elemts,ObjCnt);
			break;
		case "Fn_SetValue":
			vObjName=xrs.getCellData(AppDriver.vModuleName, "Locator1", m);
			vObjStr=prop.getProperty(vObjName);
			elemts=ExtractLocators(vObjStr);
			vVal=PlaceHolder(xrs.getCellData(AppDriver.vModuleName, "Param1", m),xrtd,k);			
			gf.Fn_SetValue(elemts,vVal);
			break;
		case "Fn_SelectValue":
			vObjName=xrs.getCellData(AppDriver.vModuleName, "Locator1", m);
			vObjStr=prop.getProperty(vObjName);
			elemts=ExtractLocators(vObjStr);
			vVal=PlaceHolder(xrs.getCellData(AppDriver.vModuleName, "Param1", m),xrtd,k);			
			gf.Fn_SelectValue(elemts,vVal);
			break;
		case "Fn_Click":
			vObjName=xrs.getCellData(AppDriver.vModuleName, "Locator1", m);
			vObjStr=prop.getProperty(vObjName);
			elemts=ExtractLocators(vObjStr);					
			gf.Fn_Click(elemts);
			break;
		}
	}
//#############################################################
//#Function Name:
//#Created By:
//#Created Date:
//#############################################################	
	
	public List<WebElement> ExtractLocators(String vObjStr)
	{
		List<WebElement> elems=null;
		if(vObjStr.contains("@@@"))
		{
			String[] arr=vObjStr.split("@@@");
			switch (arr[0])
			{
			case "xpath":
				elems=AppDriver.driver.findElements(By.xpath(arr[1]));
				break;
			case "id":
				elems=AppDriver.driver.findElements(By.id(arr[1]));
				break;
			case "name":
				elems=AppDriver.driver.findElements(By.name(arr[1]));
				break;
			case "linkText":
				elems=AppDriver.driver.findElements(By.linkText(arr[1]));
				break;
			case "partialLinkText":
				elems=AppDriver.driver.findElements(By.partialLinkText(arr[1]));
				break;
			case "cssSelector":
				elems=AppDriver.driver.findElements(By.cssSelector(arr[1]));
				break;
			case "tagName":
				elems=AppDriver.driver.findElements(By.tagName(arr[1]));
				break;
			case "className":
				elems=AppDriver.driver.findElements(By.className(arr[1]));
				break;
			}
		}
		else
		{
			System.out.println("Invalid Locator format in properties file");
		}
		return elems;
	}
	
	//#############################################################
	//#Function Name:
	//#Created By:
	//#Created Date:
	//#############################################################	
	
	public String PlaceHolder(String vValue,Xls_Reader xrtd,int k)
	{
		String vActValue="";
		if(vValue.contains("dtParam"))
		{
			vActValue=xrtd.getCellData(AppDriver.vModuleName, vValue, k);
		}
		else
		{
			vActValue=vValue;
		}
		return vActValue;
	}
  
}
