package org.whitehack97.TutorialView;

/*
 * Copyright 2016 whitehack97(Rean KR). All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.whitehack97.TutorialView.Listener.CreatingListener;
import org.whitehack97.TutorialView.Listener.PlayerListener;
import org.whitehack97.TutorialView.Listener.TutorialListener;
import org.whitehack97.TutorialView.Util.ErrorReporter;
import org.whitehack97.TutorialView.api.ErrorReport;
import org.whitehack97.TutorialView.api.PlayerManager;
import org.whitehack97.TutorialView.api.TutorialManager;
import org.whitehack97.TutorialView.commands.TutorialViewCommand;
import org.whitehack97.TutorialView.config.Config;
import org.whitehack97.TutorialView.config.TutorialConfig;

public class TutorialView extends JavaPlugin implements Listener
{
	public static TutorialView plugin;
	public static Map<String, TutorialManager> AllTutorial = new HashMap<String, TutorialManager>();
	public static Map<Player, PlayerManager> PlayerInformation = new HashMap<Player, PlayerManager>();
	public static String Prefix = "」e[」2T」futorial」bV」fiew」e]」f ";
	public static List<ErrorReport> ErrorReports = new ArrayList<ErrorReport>();
	public static int ConfigVersion = 1;
	public static boolean UsingVaultPlugin = true;
	public static boolean UsingEconomyPlugin = false;
	
	private TutorialViewCommand Command;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		Config.CheckingServerPlugin();
		TutorialConfig.LoadTutorialConfig();
		ErrorReporter.ExportErrorReport();
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new TutorialListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CreatingListener(), this);
		Command = new TutorialViewCommand(this);
		getCommand("TutorialView.Main").setExecutor(Command);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
