package org.whitehack97.TutorialView.api;

/*
 * Copyright 2016 whitehack97@gmail.com (Rean KR). All rights reserved.
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

import java.io.File;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.Util.FileManager;

public class PlayerManager
{
	private Player player;
	private YamlConfiguration PlayerSection;
	private File File;

	public PlayerManager(Player player)
	{
		this.player = player;
		this.PlayerSection = FileManager.LoadFile("Players/" + player.getUniqueId().toString());
		this.File = FileManager.getFile("Players/" + player.getUniqueId().toString());
	}
	
	public void setTid(int Tid)
	{
		PlayerSection.set("Progress.TID", Tid);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setEnableProgress(boolean Enabled)
	{
		PlayerSection.set("Progress.Enabled", Enabled);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setTutorialName(String TutorialName)
	{
		PlayerSection.set("Progress.Name", TutorialName);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setTutorialMethod(String MethodName)
	{
		PlayerSection.set("Progress.Method", MethodName);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setLastLocation(Location location)
	{
		PlayerSection.set("Backup.Location.World", location.getWorld().getName());
		PlayerSection.set("Backup.Location.Coordinates.X", location.getX());
		PlayerSection.set("Backup.Location.Coordinates.Y", location.getY());
		PlayerSection.set("Backup.Location.Coordinates.Z", location.getZ());
		PlayerSection.set("Backup.Location.Coordinates.Yaw", location.getYaw());
		PlayerSection.set("Backup.Location.Coordinates.Pitch", location.getPitch());
		FileManager.Save(PlayerSection, File);
	}
	
	public void setTutorialComplete(String TutorialName, boolean Completed)
	{
		PlayerSection.set("Complete." + TutorialName + ".Complete", Completed);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setRewardComplete(String TutorialName, boolean Completed)
	{
		PlayerSection.set("Complete." + TutorialName + ".Reward", Completed);
		FileManager.Save(PlayerSection, File);
	}
	
	public void setGameMode(GameMode gamemode)
	{
		PlayerSection.set("Backup.GameMode", gamemode.name());
		FileManager.Save(PlayerSection, File);
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	public int getTid()
	{
		return PlayerSection.getInt("Progress.TID");
	}
	
	public boolean InProgressTutorial()
	{
		return PlayerSection.getBoolean("Progress.Enabled");
	}
	
	public String getTutorialName()
	{
		return PlayerSection.getString("Progress.Name");
	}
	
	public String getTutorialMethod()
	{
		return PlayerSection.getString("Progress.Method");
	}
	
	public boolean isBackupExist()
	{
		if(PlayerSection.contains("Backup")) return true;
		else return false;
	}
	
	public Location getLastLocation()
	{
		World world = TutorialView.plugin.getServer().getWorld(PlayerSection.get("Backup.Location.World").toString());
		double x = Double.parseDouble(PlayerSection.get("Backup.Location.Coordinates.X").toString());
		double y = Double.parseDouble(PlayerSection.get("Backup.Location.Coordinates.Y").toString());
		double z = Double.parseDouble(PlayerSection.get("Backup.Location.Coordinates.Z").toString());
		float yaw = Float.parseFloat(PlayerSection.get("Backup.Location.Coordinates.Yaw").toString());
		float pitch = Float.parseFloat(PlayerSection.get("Backup.Location.Coordinates.Pitch").toString());
		Location location = new Location(world, x, y, z, yaw, pitch);
		return location;
	}
	
	public GameMode getGameMode()
	{
		return GameMode.valueOf(PlayerSection.getString("Backup.GameMode"));
	}
	
	public float getWalkSpeed()
	{
		return Float.parseFloat(PlayerSection.getString("Backup.WalkSpeed"));
	}
	
	public float getFlySpeed()
	{
		return Float.parseFloat(PlayerSection.getString("Backup.FlySpeed"));
	}
	
	public boolean isExistTutorialInformation(String TutorialName)
	{
		if(PlayerSection.contains("Complete." + TutorialName)) return true;
		else return false;
	}
	
	public boolean isCompleteTutorial(String TutorialName)
	{
		return PlayerSection.getBoolean("Complete." + TutorialName + ".Complete");
	}
	
	public boolean isReceivedRewards(String TutorialName)
	{
		return PlayerSection.getBoolean("Complete." + TutorialName + ".Reward");
	}
}