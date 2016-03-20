package org.whitehack97.TutorialView.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.whitehack97.TutorialView.TutorialView;

public class SubManager
{
	public static String RepColor(String Str)
	{
		return ChatColor.translateAlternateColorCodes('&', Str);
	}
	
	public static void msg(Player player, String message)
	{
		player.sendMessage(TutorialView.Prefix + ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public static void Cmsg(String message)
	{
		Bukkit.getConsoleSender().sendMessage(TutorialView.Prefix + RepColor(message));
	}

}
