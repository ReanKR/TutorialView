package org.whitehack97.TutorialView.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.Util.FileManager;
import org.whitehack97.TutorialView.Util.SubManager;
import org.whitehack97.TutorialView.api.ErrorReport;
import org.whitehack97.TutorialView.api.MethodInterface;
import org.whitehack97.TutorialView.api.Tutorial;
import org.whitehack97.TutorialView.api.TutorialManager;

public class TutorialConfig
{
	@SuppressWarnings("deprecation")
	public static void LoadTutorialConfig()
	{
		if(! new File("plugins/TutorialView/Tutorials/Example.yml").exists())
		{
			FileManager.SpecLoadFile("plugins/TutorialView/Tutorials", "Example");
		}
		if(! new File("plugins/TutorialView/Tutorials/Methods/Example_set.yml").exists())
		{
			new File("plugins/TutorialView/Tutorials/Methods").mkdir();
			FileManager.SpecLoadFile("plugis/TutorialView/Tutorials/Methods", "Example_set");
		}
		File directory = new File("plugins/TutorialView/Tutorials");
		File[] files = directory.listFiles();
		try
		{
			if(files[0] == null)
			{
				ErrorReport Report = new ErrorReport();
				Report.setMethod("Warning");
				Report.setMessage("Tutorial file not exist");
				TutorialView.ErrorReports.add(Report);
				return;
			}
		}
		catch(NullPointerException e)
		{
			ErrorReport Report = new ErrorReport();
			Report.setMethod("Warning");
			Report.setCausedMethod(directory.getAbsolutePath());
			Report.setAddress(directory.getAbsolutePath());
			Report.setMessage("Tutorial file not exist");
			TutorialView.ErrorReports.add(Report);
			return;
		}
		for(File file : files)
		{
			if(file.isFile())
			{
				if(file.getName().endsWith(".yml"))
				{
					String Filename = file.getName().replace(".yml", "");
					YamlConfiguration InterfaceSection = YamlConfiguration.loadConfiguration(new File("plugins/TutorialView/Tutorials/Methods/" + file.getName() + "_set.yml"));
					YamlConfiguration TutorialSection = YamlConfiguration.loadConfiguration(new File("plugins/TutorialView/Tutorials/" + file.getName() + ".yml"));

					Set<String> TutorialMethodName = InterfaceSection.getKeys(false);
					if(TutorialMethodName.size() == 0)
					{
						ErrorReport Report = new ErrorReport();
						Report.setAddress("Tutorials/Methods/" + Filename + ".yml");
						Report.setMethod("ERROR");
						Report.setMessage("There is no tutorial config information");
						TutorialView.ErrorReports.add(Report);
						continue;
					}

					List<MethodInterface> TutorialMethods = new ArrayList<MethodInterface>();
					for(String Name : TutorialMethodName)
					{
						MethodInterface Interface = new MethodInterface(Filename, Name);
						TutorialMethods.add(Interface);
					}
					Tutorial tutorial = new Tutorial(Filename);
					tutorial.setInterface(TutorialMethods);
					TutorialManager Manager = new TutorialManager(tutorial);
					if(TutorialSection.contains("Command")) Manager.setRunCommand(TutorialSection.getString("Command"));
					if(TutorialSection.contains("Name")) Manager.setName(TutorialSection.getString("Name"));
					if(TutorialSection.contains("Block-Movement")) Manager.setBlockMovement(TutorialSection.getBoolean("Block-Movement"));
					if(TutorialSection.contains("Block-All-Commands")) Manager.setBlockAllCommands(TutorialSection.getBoolean("Block-All-Commands"));
					if(TutorialSection.contains("Broadcast-Complete-Tutorial")) Manager.setBroadcastCompleteTutorial(TutorialSection.getBoolean("Broadcast-Complete-Tutorial"));
					if(TutorialSection.contains("Default-Delay-Seconds")) Manager.setDelaySeconds(TutorialSection.getInt("Default-Delay-Seconds"));
					if(TutorialSection.contains("Default-Cooldown-Seconds")) Manager.setCooldownSeconds(TutorialSection.getInt("Default-Cooldown-Seconds"));
					if(TutorialSection.contains("Sound-Disabled")) Manager.setSoundDisabled(TutorialSection.getBoolean("Sound-Disabled"));
					if(TutorialSection.contains("Using-TitleAPI")) Manager.setUsingTitleAPI(TutorialSection.getBoolean("Using-TitleAPI"));
					if(TutorialSection.contains("Result.Run-Commands")) Manager.setEnableRuncommands(TutorialSection.getBoolean("Result.Run-Commands"));
					if(TutorialSection.contains("Result.Result-Items")) Manager.setEnableResultItems(TutorialSection.getBoolean("Result.Result-Items"));
					if(TutorialSection.contains("Result.Commands")) Manager.setCommands(TutorialSection.getStringList("Result.Commands"));
	
					Set<String> ResultMethodName = TutorialSection.getConfigurationSection("Result.Items").getKeys(false);
					if(ResultMethodName.size() == 0)
					{
						ErrorReport Report = new ErrorReport();
						Report.setAddress("Tutorials/Methods/" + Filename + "set.yml");
						Report.setMethod("ERROR");
						Report.setMessage("There is no tutorial interface information");
						TutorialView.ErrorReports.add(Report);
						continue;
					}
					for(String Name : ResultMethodName)
					{
						int ID = 1;
						byte Data = 0;
						int Amounts = 1;
						ConfigurationSection Section = TutorialSection.getConfigurationSection("Result.Items." + Name);
						if(Section.contains("ID")) ID = Section.getInt("ID");
						if(Section.contains("DATA-VALUE")) Data = Byte.parseByte(Section.getString("DATA-VALUE"));
						if(Section.contains("Amounts")) Amounts = Section.getInt("Amounts");
						ItemStack itemstack = new ItemStack(ID, Amounts, Data);
						ItemMeta Metadata = itemstack.getItemMeta();
						if(Section.contains("NAME")) Metadata.setDisplayName(SubManager.RepColor(Section.getString("NAME")));
						
						if(Section.contains("Lore"))
						{
							Metadata.setLore(Section.getStringList("Lore"));
						}
						else if(Section.contains("DESCRIPTION"))
						{
							Metadata.setLore(Section.getStringList("DESCRIPTION"));
						}
						if(Section.contains("ENCHANTMENT"))
						{
							List<String> Enchantments = Section.getStringList("ENCHANTMENT");
							for(String Enchant : Enchantments)
							{
								try
								{
									String[] EnchantCutter = Enchant.split(", ");
									Metadata.addEnchant(Enchantment.getByName(EnchantCutter[0]), Integer.parseInt(EnchantCutter[1]), true);
								}
								catch(NullPointerException e)
								{
									ErrorReport Report = new ErrorReport();
									Report.setAddress("Tutorials/Methods/" + Filename + ".yml");
									Report.setCausedMethod(Enchant);
									Report.setMethod("ERROR");
									Report.setMessage("The method is not enchanted");
									TutorialView.ErrorReports.add(Report);
								}
							}
						}
						itemstack.setItemMeta(Metadata);
						Manager.addResultItem(itemstack);
					}
					TutorialView.AllTutorial.put(Filename, Manager);
				}
			}
		}
	}
}
