package org.whitehack97.TutorialView.api;

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
import java.util.List;

import org.bukkit.inventory.ItemStack;

import org.whitehack97.TutorialView.api.Tutorial;

public class TutorialManager
{
	private String Name = "TutorialView";
	private boolean BlockMovement = true;
	private boolean BlockAllCommands = true;
	private boolean BroadcastCompleteTutorial = true;
	private int DelaySeconds = 6;
	private int CooldownSeconds = 5;
	private boolean SoundDisabled = false;
	private boolean UsingTitleAPI = true;
	private boolean EnabledRunCommands = true;
	private boolean EnabledResultItems = true;
	private List<String> Commands = new ArrayList<String>();
	private List<ItemStack> ResultItems = new ArrayList<ItemStack>();
	private Tutorial tutorial;

	public TutorialManager(Tutorial tutorial)
	{
		this.tutorial = tutorial;
	}
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public void setBlockMovement(boolean Enabled)
	{
		this.BlockMovement = Enabled;
	}
	
	public void setBlockAllCommands(boolean Enabled)
	{
		this.BlockAllCommands = Enabled;
	}
	
	public void setBroadcastCompleteTutorial(boolean Enabled)
	{
		this.BroadcastCompleteTutorial = Enabled;
	}
	
	public void setDelaySeconds(int Seconds)
	{
		this.DelaySeconds = Seconds;
	}
	
	public void setCooldownSeconds(int Seconds)
	{
		this.CooldownSeconds = Seconds;
	}
	
	public void setSoundDisabled(boolean Disabled)
	{
		this.SoundDisabled = Disabled;
	}
	
	public void setUsingTitleAPI(boolean Enabled)
	{
		this.UsingTitleAPI = Enabled;
	}
	
	public void setEnableRuncommands(boolean Enabled)
	{
		this.EnabledRunCommands = Enabled;
	}
	
	public void setEnableResultItems(boolean Enabled)
	{
		this.EnabledResultItems = Enabled;
	}
	
	public void setCommands(List<String> Commands)
	{
		this.Commands = Commands;
	}
	
	public void setResultItems(List<ItemStack> Items)
	{
		this.ResultItems = Items;
	}
	
	public boolean addCommand(String Command)
	{
		for(String str : Commands)
		{
			if(str.equalsIgnoreCase(Command))
			{
				return false;
			}
		}
		Commands.add(Command);
		return true;
	}
	
	public boolean addResultItem(ItemStack Item)
	{
		for(ItemStack Itemstack : ResultItems)
		{
			if(Itemstack == Item)
			{
				return false;
			}
		}
		ResultItems.add(Item);
		return true;
	}

	public Tutorial getTutorial()
	{
		return this.tutorial;
	}
	
	public String getName()
	{
		return this.Name;
	}
	
	public boolean isBlockMovement()
	{
		return this.BlockMovement;
	}

	public boolean isBlockAllCommands()
	{
		return this.BlockAllCommands;
	}
	
	public boolean isBroadcastCompleteTutorial()
	{
		return this.BroadcastCompleteTutorial;
	}
	
	public int getDelaySeconds()
	{
		return this.DelaySeconds;
	}
	
	public int getCooldownSeconds()
	{
		return this.CooldownSeconds;
	}
	
	public boolean isSoundDisabled()
	{
		return this.SoundDisabled;
	}
	
	public boolean EnabledTitleAPI()
	{
		return this.UsingTitleAPI;
	}
	
	public boolean EnabledResultCommands()
	{
		return this.EnabledRunCommands;
	}
	
	public boolean EnabledResultItems()
	{
		return this.EnabledResultItems;
	}
	
	public List<String> getCommands()
	{
		return this.Commands;
	}
	
	public List<ItemStack> getResultItems()
	{
		return this.ResultItems;
	}
}
