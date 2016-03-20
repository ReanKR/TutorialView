package org.whitehack97.TutorialView.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.api.ErrorReport;

public class ErrorReporter
{
	public static void ExportErrorReport()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		FileManager.SpecLoadFile("plugins/TutorialView/Report/", "ErrorReport_" + sdf.format(date).toString());
		File File = new File("plugins/TutorialView/Report/ErrorReport_" + sdf.format(date).toString() + ".yml");
		if(TutorialView.ErrorReports.size() == 0)
		{
			return;
		}
		else
		{
			SubManager.Cmsg("&cSeveral problems have been identified.");
			SubManager.Cmsg("Please Note plugins/TutorialView/Report/ErrorReport_" + sdf.format(date).toString() + ".yml");
		}
		if(! File.exists())
		{
			try
			{
				File.createNewFile();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		int count = 1;
		for(ErrorReport Report : TutorialView.ErrorReports)
		{
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(File));
				writer.write(TutorialView.plugin.getDescription().getName() + " " + TutorialView.plugin.getDescription().getVersion() + " " + "Error Report\r\n");
				writer.write("Some of the problem caused when load plugin.\r\n");
				writer.write("We found " + TutorialView.ErrorReports.size() + " problem(s).\r\n\n");
				String Method = null;
				if(Report.getMethod().equalsIgnoreCase("ERROR")) Method = "Invalid";
				else Method = Report.getMethod();
				writer.write("[" + count + "]\r\n");
				writer.write("Error type: " + Method + "\r\n");
				writer.write("Path: " + Report.getAddress() + "\r\n");
				writer.write("Relate method: " + Report.getCausedMethod() + "\r\n");
				writer.write("message: " + Report.getMessage() + "\r\n");
				writer.flush();
				writer.close();
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			count++;
		}
	}

}
