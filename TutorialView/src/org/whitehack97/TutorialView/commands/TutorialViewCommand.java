package org.whitehack97.TutorialView.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.whitehack97.TutorialView.TutorialView;

public class TutorialViewCommand implements CommandExecutor
{
	private TutorialView plugin;

	public TutorialViewCommand(TutorialView plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
