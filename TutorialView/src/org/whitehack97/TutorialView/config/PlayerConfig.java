package org.whitehack97.TutorialView.config;

import org.bukkit.entity.Player;
import org.whitehack97.TutorialView.api.PlayerManager;

public class PlayerConfig
{
	public static void LoadPlayerConfig(Player player)
	{
		PlayerManager Manager = new PlayerManager(player);
		if(Manager.isBackupExist())
		{
			if(Manager.InProgressTutorial())
			{
				
			}
		}
	}
}
