package org.whitehack97.TutorialView.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.whitehack97.TutorialView.TutorialView;

public class TutorialCommand implements CommandExecutor
{
	private TutorialView plugin;

	public TutorialCommand(TutorialView main)
	{
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
