package org.whitehack97.TutorialView.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.Util.FileManager;
import org.whitehack97.TutorialView.Util.SubManager;
import org.whitehack97.TutorialView.api.MethodInterface;
import org.whitehack97.TutorialView.api.Tutorial;
import org.whitehack97.TutorialView.api.TutorialManager;

public class TutorialConfig
{
	@SuppressWarnings("deprecation")
	public static void LoadTutorialConfig()
	{
		File directory = new File("plugins/TutorialView/Tutorials");
		File[] files = directory.listFiles();
		for(File file : files)
		{
			if(file.isFile())
			{
				if(file.getName().endsWith(".yml"))
				{
					String[] Cutter = file.getName().split(".");
					YamlConfiguration InterfaceSection = FileManager.LoadFile("Tutorials/Methods/" + Cutter[0] + ".set");
					YamlConfiguration TutorialSection = FileManager.LoadFile("Tutorials/Methods/" + Cutter[0]);

					Set<String> TutorialMethodName = InterfaceSection.getKeys(false);
					if(TutorialMethodName.size() == 0)
					{
						Bukkit.getConsoleSender().sendMessage(TutorialView.Prefix + "¡×e" + file.getName() + " is not registered.");
						continue;
					}

					List<MethodInterface> TutorialMethods = new ArrayList<MethodInterface>();
					for(String Name : TutorialMethodName)
					{
						MethodInterface Interface = new MethodInterface(Cutter[0], Name);
						TutorialMethods.add(Interface);
					}
					Tutorial tutorial = new Tutorial(Cutter[0]);
					tutorial.setInterface(TutorialMethods);
					
					TutorialManager Manager = new TutorialManager(tutorial);
					Manager.setName(TutorialSection.getString("Name"));
					Manager.setBlockMovement(TutorialSection.getBoolean("Block-Movement"));
					Manager.setBlockAllCommands(TutorialSection.getBoolean("Block-All-Commands"));
					Manager.setBroadcastCompleteTutorial(TutorialSection.getBoolean("Broadcast-Complete-Tutorial"));
					Manager.setDelaySeconds(TutorialSection.getInt("Default-Delay-Seconds"));
					Manager.setCooldownSeconds(TutorialSection.getInt("Default-Cooldown-Seconds"));
					Manager.setSoundDisabled(TutorialSection.getBoolean("Sound-Disabled"));
					Manager.setUsingTitleAPI(TutorialSection.getBoolean("Using-TitleAPI"));
					Manager.setEnableRuncommands(TutorialSection.getBoolean("Result.Run-Commands"));
					Manager.setEnableResultItems(TutorialSection.getBoolean("Result.Result-Items"));
					Manager.setCommands(TutorialSection.getStringList("Result.Commands"));
	
					Set<String> ResultMethodName = TutorialSection.getConfigurationSection("Result.Items").getKeys(false);
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
								String[] EnchantCutter = Enchant.split(", ");
								Metadata.addEnchant(Enchantment.getByName(EnchantCutter[0]), Integer.parseInt(Cutter[1]), true);
							}
						}
						itemstack.setItemMeta(Metadata);
						Manager.addResultItem(itemstack);
					}
					TutorialView.AllTutorial.put(Cutter[0], Manager);
				}
			}
		}
	}
}
