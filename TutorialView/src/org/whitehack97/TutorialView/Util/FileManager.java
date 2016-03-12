package org.whitehack97.TutorialView.Util;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TutorialView.TutorialView;

public class FileManager
{

	public static YamlConfiguration LoadFile(String FileName)
	{
		if (!FileName.endsWith(".yml"))
	    {
			FileName = FileName + ".yml";
	    }

	    File file = getFile(FileName);
	    if(!file.exists())
	    {
		      try
		      {
		    	  TutorialView.plugin.saveResource(FileName, true);
		          Bukkit.getConsoleSender().sendMessage(TutorialView.Prefix + "Create New File " + file.getAbsolutePath());
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
		    	  }
		    	  
		    	  Bukkit.getConsoleSender().sendMessage(TutorialView.Prefix + "Create New File " + file.getAbsolutePath());
		      }
	    }
	    YamlConfiguration Section = YamlConfiguration.loadConfiguration(file);
	    return Section;
	}

	public static File getFile(String string)
	{
		File file = new File("plugins/TutorialView/" + string);
		return file;
	}

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

}
