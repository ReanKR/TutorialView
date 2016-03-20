package org.whitehack97.TutorialView.Util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TutorialView.TutorialView;

public class FileManager
{
	public static void Save(YamlConfiguration playerSection, File file)
	{
		try
		{
			playerSection.save(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static YamlConfiguration SpecLoadFile(String Path, String FileName) // jar 파일에 있는 어떤 파일을 특정한 디렉토리에 생성하고자 할 때
	{
		if (!FileName.endsWith(".yml"))
	    {
			FileName = FileName + ".yml";
	    }
		File file = getFile(FileName);
		File LastFile = new File(Path + FileName);
		if(! LastFile.exists())
		{
			if(! file.exists())
			{
				  try
			      {
			    	  TutorialView.plugin.saveResource(FileName, true);
			      }
			      catch(IllegalArgumentException e)
			      {
			    	  e.printStackTrace();
			    	  try
			    	  {
						file.createNewFile();
			    	  }
			    	  catch (IOException e1)
			    	  {
						e1.printStackTrace();
						return null;
			    	  }
			      }
			}
			File Directory = new File(Path);
			File NewDirectory = new File(Path + "/" + FileName);
			if(! Directory.exists())
			{
				Directory.mkdirs();
			}
			if(! NewDirectory.exists())
			{
				try
				{
					NewDirectory.createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			FileInputStream inputStream = null;
			FileOutputStream outputStream = null;
			try
			{
				inputStream = new FileInputStream(file);
				outputStream = new FileOutputStream(NewDirectory);
			}
			catch (FileNotFoundException e1)
			{
				e1.printStackTrace();
			}
			
			BufferedInputStream bin = new BufferedInputStream(inputStream);
			BufferedOutputStream bout = new BufferedOutputStream(outputStream);
			 
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			try
			{
				while ((bytesRead = bin.read(buffer, 0, 1024)) != -1)
				{
				    bout.write(buffer, 0, bytesRead);
				}
				bout.close();
				bin.close();
				outputStream.close();
				inputStream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		YamlConfiguration Section = YamlConfiguration.loadConfiguration(LastFile);
		return Section;
	}

	public static File getFile(String FileName)
	{
		if (!FileName.endsWith(".yml"))
	    {
			FileName = FileName + ".yml";
	    }
		File File = new File("plugins/TutorialView/" + FileName);
		return File;
	}

	public static YamlConfiguration LoadFile(String FileName)
	{
		if (!FileName.endsWith(".yml"))
	    {
			FileName = FileName + ".yml";
	    }
		File file = new File("plugins/TutorialView/" + FileName);
		if(! file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		YamlConfiguration Section = YamlConfiguration.loadConfiguration(file);
		return Section;
	}
	
	public static YamlConfiguration LoadSourceFile(String FileName)
	{
		if (!FileName.endsWith(".yml"))
	    {
			FileName = FileName + ".yml";
	    }
		File file = new File("plugins/TutorialView/" + FileName);
		if(! file.exists())
		{
			try
			{
				TutorialView.plugin.saveResource(FileName, true);
			}
			catch (IllegalArgumentException e)
			{
		    	try
		    	{
		    		file.createNewFile();
		    	}
		    	catch (IOException e1)
		    	{
					e1.printStackTrace();
					return null;
		    	}
			}
		}
		YamlConfiguration Section = YamlConfiguration.loadConfiguration(file);
		return Section;
	}

}
