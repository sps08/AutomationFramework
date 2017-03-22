package DriverScript;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Utility.html_result;
import Utility.keywordDriver;

public class AppDriver {
    public static int StepsExcelRowNum=0;
	public static WebDriver driver;
	public static html_result hr;
	public static String vProject,vProjectURL,vScenarioFile,vTestDataFile,vLocatorFile,vDBName,vDBUserid,vDBPassword,vBrowser,vModuleName,vTCName,vKeywords,vResultRes,startexe,stopexe,vDesc;
	
	public static int passval=0;
	public static int totfailval=0;
	public static int failval=0;
	public static void main(String[] args) throws Throwable {
		
		//System.out.println(System.getProperty("user.dir"));
		Xls_Reader xr=new Xls_Reader(System.getProperty("user.dir")+"\\src\\DriverFiles\\ApplicationDriver.xlsx");
		int rcnt=xr.getRowCount("Applications");
		for(int i=2;i<=rcnt;i++)
		{
			String vRun=xr.getCellData("Applications", "Run", i).trim();
			if(vRun.equalsIgnoreCase("ON"))
			{
				int noofTC=0;
				int passtc=0;
				int failtc=0;
				 passval=0;
				 failval=0;
				 
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date=new Date();
				startexe=dateFormat.format(date);
				hr=new html_result();
				vProject=xr.getCellData("Applications", "Project", i).trim();
				vProjectURL=xr.getCellData("Applications", "ProjectURL", i).trim();
				vScenarioFile=xr.getCellData("Applications", "ScenarioFile", i).trim();
				vTestDataFile=xr.getCellData("Applications", "TestDataFile", i).trim();
				vLocatorFile=xr.getCellData("Applications", "LocatorFile", i).trim();
				vDBName=xr.getCellData("Applications", "DBName", i).trim();
				vDBUserid=xr.getCellData("Applications", "DBUserid", i).trim();
				vDBPassword=xr.getCellData("Applications", "DBPassword", i).trim();
				vBrowser=xr.getCellData("Applications", "Browser", i).trim();
				
				vResultRes=hr.CreateResultFileAndPath(vProject);
				hr.fg_OpenResultsFile(vResultRes, vProject);
				Xls_Reader xrtd=new Xls_Reader(System.getProperty("user.dir")+"\\src\\TestData\\"+vTestDataFile);
				Xls_Reader xrs=new Xls_Reader(System.getProperty("user.dir")+"\\src\\Scenario\\"+vScenarioFile);
				Xls_Reader xres=new Xls_Reader(System.getProperty("user.dir")+"\\src\\Results\\ExcelResult.xlsx");
				System.out.println("================"+vProject+"=======================");
				if(vBrowser.equals("Chrome"))
				{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\Browsers\\chromedriver.exe");
					driver=new ChromeDriver();					
				}
				else if(vBrowser.equals("IE"))
				{
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\Browsers\\IEDriverServer.exe");
					driver=new InternetExplorerDriver();		
				}
				else
				{
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\Browsers\\geckodriver.exe");
					driver=new FirefoxDriver();					
				}
				
				int rmodCnt=xr.getRowCount(vProject);
				for(int j=2;j<=rmodCnt;j++)
				{
					String vModRun=xr.getCellData(vProject, "Run", j).trim();
					if(vModRun.equalsIgnoreCase("ON"))
					{
						vModuleName=xr.getCellData(vProject, "ModuleName", j).trim();
						System.out.println(vModuleName);
						
						int rTCcnt=xrtd.getRowCount(vModuleName);
						for(int k=2;k<=rTCcnt;k++)
						{
							String vRCRun=xrtd.getCellData(vModuleName, "Run", k).trim();
							if(vRCRun.equalsIgnoreCase("ON"))
							{
								vTCName=xrtd.getCellData(vModuleName, "TCName", k).trim();
								System.out.println(vTCName);
								noofTC=noofTC+1;
								totfailval=0;
								int flag=0;
								int rownum=0;
								int vKeyCnt=xrs.getRowCount(vModuleName);
								for(int m=2;m<=vKeyCnt;m++)
								{
									String vKeyData=xrs.getCellData(vModuleName, "Keywords", m);
									if(vKeyData.equals(vTCName))
									{
										vDesc=xrs.getCellData(vModuleName, "Keywords", m-1).replace("//", "");
										flag=1;
										rownum=m;
									}
									if((flag==1) && (m>rownum))
									{
										if(vKeyData.contains("//"))
										{
											break;											
										}
										else
										{
											
											vKeywords=vKeyData;
											System.out.println(vKeywords);
											StepsExcelRowNum=m;
											keywordDriver kd=new keywordDriver();
											kd.keyworddriver(vKeywords, xrs, m,xrtd,k);
										}
									}
								}
								
								if(totfailval>0)
								{
									failtc=failtc+1;
									hr.ExcelResult(xres, "FAILED");
								}
								else
								{
									hr.ExcelResult(xres, "PASSED");
								}
							}
						}
						
					}
				}
				hr.fgCreateSummary(vResultRes);
				Date date1=new Date();
				stopexe=dateFormat.format(date1);
				long timediff=hr.timeDiffernce(startexe, stopexe, dateFormat);
				hr.fgInsertSummary(vResultRes, noofTC, noofTC-failtc, failtc, passval, failval,timediff );
				//hr.fgInsertSummary(vResultRes, 20, 12, 8, 200, 100, (long) 2.03);
				
				
			}
			
		}
		

	}

}
