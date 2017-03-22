package Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import DriverScript.AppDriver;
import DriverScript.Xls_Reader;






public class html_result {
	
	public String ResFilePath;
	//1 st
	AppDriver dsc=new AppDriver();
	public String CreateResultFileAndPath(String vProjectName) throws Throwable 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		String TempFileName=dateFormat.format(date);
		String NewFileName1=TempFileName.replace("/","_");
		String NewFileName2=NewFileName1.replace(" ","_");
		String NewFileName=NewFileName2.replace(":","_");
		System.out.println(NewFileName);
		ResFilePath =(System.getProperty("user.dir")+"\\src\\Results\\"+vProjectName+"HtmlResult_"+NewFileName+".html");
		File f = new File(ResFilePath);
		f.createNewFile();
		return ResFilePath;
		
	}
	// 2nd step
	
	public void fg_OpenResultsFile(String ResFilePath,String Projectname) throws Throwable 
	{
		FileWriter w = new FileWriter(ResFilePath);
		BufferedWriter ts = new BufferedWriter(w);
		ts.write ("<HTML>");
		ts.write ("<head><link href='Result_JS_CSS/tablecloth.css' rel='stylesheet' type='text/css' media='screen' />");
		ts.write ("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
		ts.write ("<script type='text/javascript' src='Result_JS_CSS/tablecloth.js'></script>");
		ts.write ("</head>");
		ts.write ("<BODY>");		
        ts.write ("<TABLE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th colspan=8>ATH Automation Result</th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<TD colspan=8>");
		ts.write ("<TABLE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<TD>");
		ts.write ("<TABLE BGCOLOR=WHITE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th>Project Name</th>");
		ts.write ("<th>"+AppDriver.vProject+"</th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Execution Start</th>");
		ts.write ("<th><div id='exestart'></div></th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Execution End</th>");	
		ts.write ("<th><div id='exeend'></div></th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Total Execution Time(Sec)</th>");
		ts.write ("<th><div id='totexec'></div></th>");
		ts.write ("</TR>");
		ts.write ("</TBODY>");
		ts.write ("</TABLE>");
		ts.write ("</TD>");
		ts.write ("<TD style='width: 300px; height: 200px;'>");
		ts.write ("<TABLE BGCOLOR=WHITE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th colspan=2><div id=piechart style='width: 300px; height: 200px;'></th>");
		ts.write ("</TR>");
		ts.write ("</TBODY>");
		ts.write ("</TABLE>");
		ts.write ("</TD>");
		ts.write ("<TD>");
		ts.write ("<TABLE BGCOLOR=WHITE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th>Regression Pace</th>");
		ts.write ("<th>FEB PACE 16</th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Total Test Cases</th>");
		ts.write ("<th><div id=ttc></div></th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Total Passed Test Cases</th>");
		ts.write ("<th>");
		ts.write ("<div id=tpassed></div></th>");
		ts.write ("</TR>");
		ts.write ("<TR>");
		ts.write ("<th>Total Failed Test Cases</th>");
		ts.write ("<th><div id=tfailed></div></th>");
		ts.write ("</TR>");
		ts.write ("</TBODY>");
		ts.write ("</TABLE>");
		ts.write ("</TD>");
		ts.write ("</TR>");
		ts.write ("</TD>");
		ts.write ("</TBODY>");
		ts.write ("</TABLE>");
		ts.write ("<TABLE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th>ProjectName</th>");
		ts.write ("<th>ModuleName</th>");
		ts.write ("<th>TestCaseName</th>");
		ts.write ("<th>ValidationSteps</th>");
		ts.write ("<th>Expected</th>");
		ts.write ("<th>Actual</th>");
		ts.write("<th>Status</th>");
		ts.write("<th>ExecutionTime</th>");
		ts.write("</TR>");  
		ts.flush();
	}
	
	
	@SuppressWarnings("resource")
	public void fgInsertResult(String ResFilePath,String vkeyword,String Expected,String Actual,String Result) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter ts = new BufferedWriter(w);
		ts.write ("<TR>");
		ts.write ("<TD>" +AppDriver.vProject+"</TD>");
		ts.write ("<TD>" +AppDriver.vModuleName+"</TD>");
		ts.write ("<TD>"+AppDriver.vTCName+ " RowNum: # "+AppDriver.StepsExcelRowNum+"</TD>");
		ts.write ("<TD>"+ vkeyword +"</TD>");
		ts.write ("<TD>"+ Expected+"</TD>");
		ts.write ("<TD>"+Actual+"</TD>");
		if (Result.equals("PASSED"))
		{
			
			 
			ts.write ("<TD><FONT FACE='VERDANA' COLOR='GREEN' SIZE=1><B>"+Result+"</B></Font></TD>");	
			
			AppDriver.passval = AppDriver.passval+1;
			 
		 }	 
		 else if (Result.equals("FAILED"))
		 {
			
			
			 ts.write ("<TD><FONT FACE='VERDANA' COLOR='RED' SIZE=1><B>"+Result+"</B></Font></TD>");	
			 AppDriver.totfailval = AppDriver.totfailval+1;
			 AppDriver.failval =AppDriver.failval+1; 
			
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		String CurrentTime=dateFormat.format(date);	
		ts.write ("<TD>"+CurrentTime+"</TD>");
		ts.write ("</TR>");
		ts.flush();
	}
	
	
	public void fgCreateSummary(String ResFilePath) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter ts = new BufferedWriter(w);
		ts.write ("</TBODY>");
		ts.write ("</TABLE>");
		ts.write ("<TABLE>");
		ts.write ("<TBODY>");
		ts.write ("<TR>");
		ts.write ("<th>Total # of TC's</th>");
		ts.write ("<th>Validations Passed</th>");
		ts.write ("<th>Validations Failed</th>");
		ts.write ("<th>Total # of TC's Passed</th>");
		ts.write ("<th>Total # of TC's Failed</th>");
		ts.write ("<th>Execution Start Time</th>");
		ts.write ("<th>Execution End Time</th>");
		ts.write ("<th>TotalTimeTaken(Sec)</th>");
		ts.write ("</TR>");
		ts.flush();
		   
	}
	
	public void fgInsertSummary(String strFilePath,int noofTC,int passtc,int failtc,int passval,int failval,long timeofexe) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter ts = new BufferedWriter(w);
		    ts.write ("<TR>");
		    ts.write ("<TD>"+noofTC+"</TD>");
			ts.write ("<TD>"+passval+"</TD>");
			ts.write ("<TD>"+failval+"</TD>");
			ts.write ("<TD><div id='total_passed' data-value="+passtc+">"+passtc+"</div></TD>");
		    ts.write ("<TD><div id='total_failed' data-value="+failtc+">"+failtc+"</div></TD>");					
		    ts.write ("<TD><div id='exe_start' data-value='"+AppDriver.startexe+"'>"+AppDriver.startexe+"</div></TD>");	
			ts.write ("<TD><div id='exe_end' data-value='"+AppDriver.stopexe+"'>"+AppDriver.stopexe+"</div></TD>");	
		    ts.write ("<TD><div id='tot_exec' data-value='"+timeofexe+"'>"+timeofexe+"</div></TD>");
		    ts.write ("</TR>");
		    ts.write ("</TBODY>");
		    ts.write ("</TABLE>");
		    ts.flush();
	}
	
	public void WriteTCTime(String ResFilePath,long tctime) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("<TD WIDTH='10%'><FONT FACE='VERDANA' COLOR='GREEN' SIZE=2><B>"+tctime+"</B></FONT></TD>");
		out.newLine();
		out.flush();
	}
	
	@SuppressWarnings("resource")
	public void fgCloseFile(String ResFilePath) throws Throwable
	{
		FileWriter w = new FileWriter(ResFilePath,true);
		BufferedWriter out = new BufferedWriter(w);
		out.write ("</DIV>");
		out.write ("</BODY></HTML>");
		out.flush();
		
	}
	
	public void ExcelResult(Xls_Reader xres,String status)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();		
		String DateTime=dateFormat.format(date);
		int rownum=xres.getRowCount(AppDriver.vProject);
		xres.setCellData(AppDriver.vProject, "ModuleName", rownum+1, AppDriver.vModuleName);
		xres.setCellData(AppDriver.vProject, "TCName", rownum+1, AppDriver.vTCName);
		xres.setCellData(AppDriver.vProject, "Description", rownum+1, AppDriver.vDesc);
		xres.setCellData(AppDriver.vProject, "Status", rownum+1, status);
		xres.setCellData(AppDriver.vProject, "DateTime", rownum+1, DateTime);
	}
	
	public long timeDiffernce(String dateStart,String dateStop,DateFormat dateFormat) throws Throwable
	{
		
		 
		//HH converts hour in 24 hours format (0-23), day calculation
		Date d1=null;
		Date d2=null;
		d1 = dateFormat.parse(dateStart);
		d2 = dateFormat.parse(dateStop);
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
		//	long diffMinutes = diff / (60 * 1000) % 60;
		//	long diffHours = diff / (60 * 60 * 1000) % 24;
		//	long diffDays = diff / (24 * 60 * 60 * 1000);
 
//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
 
		
		return diffSeconds;
		
	}
	
	
}	



