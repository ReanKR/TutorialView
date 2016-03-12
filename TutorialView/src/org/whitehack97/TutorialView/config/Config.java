package org.whitehack97.TutorialView.config;

import java.io.File;

import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.Util.FileManager;
import org.whitehack97.TutorialView.api.EconomyAPI;
import org.whitehack97.TutorialView.api.ErrorReport;

import net.milkbowl.vault.economy.Economy;

public class Config
{
	private static Server server = TutorialView.plugin.getServer();
	
	public static void CheckingServerPlugin()
	{
		File ConfigFile = FileManager.getFile("config");
		YamlConfiguration PluginSection = FileManager.LoadFile("config");

		if(!PluginSection.contains("Config-Version"))
		{
			ErrorReport Report = new ErrorReport();
			Report.setAddress(ConfigFile.getAbsolutePath());
			Report.setCausedMethod("Config-Version");
			Report.setMethod("ERROR");
			Report.setMessage("Missing config version");
			TutorialView.ErrorReports.add(Report);
			return;
		}
		else
		{
			TutorialView.ConfigVersion = PluginSection.getInt("Config-Version");
		}
		
		if(PluginSection.contains("Compatibles.Vault"))
		{
			if(!(PluginSection.getBoolean("Compatibles.Vault") || (PluginSection.getBoolean("Compatibles.Vault") && ! server.getPluginManager().isPluginEnabled("Vault"))))
			{
				ErrorReport Report = new ErrorReport();
				Report.setAddress(ConfigFile.getAbsolutePath());
				Report.setCausedMethod("Compatibles");
				Report.setMethod("ERROR");
				Report.setMessage("This plugin required Vault");
				TutorialView.ErrorReports.add(Report);
				PluginSection.set("Compatibles.Vault", false);
				return;
			}
		}
		Economy echo = EconomyAPI.getEconomy();
		try
		{
			if(!echo.isEnabled() && PluginSection.getBoolean("Compatibles.Economy"))
			{
				ErrorReport Report = new ErrorReport();
				Report.setAddress(ConfigFile.getAbsolutePath());
				Report.setCausedMethod("Compatibles");
				Report.setMethod("ERROR");
				Report.setMessage("Not found related to the Economy on the server");
				TutorialView.ErrorReports.add(Report);
				PluginSection.set("Compatibles.Economy", false);
			}
		}

		catch(NullPointerException e)
		{
			ErrorReport Report = new ErrorReport();
			Report.setAddress(ConfigFile.getAbsolutePath());
			Report.setCausedMethod("Compatibles");
			Report.setMethod("ERROR");
			Report.setMessage("Not found related to the Economy on the server");
			TutorialView.ErrorReports.add(Report);
			PluginSection.set("Compatibles.Economy", false);
		}
		
		TutorialView.UsingVaultPlugin = PluginSection.getBoolean("Compatibles.Vault");
		TutorialView.UsingEconomyPlugin = PluginSection.getBoolean("Compatibles.Economy");
		return;
	}
}
