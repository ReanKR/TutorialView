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

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.whitehack97.TutorialView.TutorialView;
import org.whitehack97.TutorialView.Util.FileManager;

public class MethodInterface
{
	private String YamlerName;
	private Location location;
	private String MainMessage;
	private String SubMessage;
	private YamlConfiguration TutorialSection;
	private double x;
	private double y;
	private double z;
	private float yaw;
	private float pitch;
	private World world;
	private String MethodName;
	
	public MethodInterface(String YamlName, String Section)
	{
		this.TutorialSection = FileManager.LoadFile("Tutorials/Methods/" + YamlName + ".set");
		this.YamlerName = YamlName;
		this.MethodName = Section;

		world = TutorialView.plugin.getServer().getWorld(TutorialSection.getString(Section + ".World"));
		x = TutorialSection.getDouble(Section + ".Coordinates.X");
		y = TutorialSection.getDouble(Section + ".Coordinates.Y");
		z = TutorialSection.getDouble(Section + ".Coordinates.Z");
		yaw = Float.parseFloat(TutorialSection.getString(Section + ".Angle.Yaw"));
		pitch = Float.parseFloat(TutorialSection.getString(Section + ".Angle.Pitch"));
		location = new Location(world, x, y, z, yaw, pitch);
		MainMessage = TutorialSection.getString(Section + ".Messages.Main");
		SubMessage = TutorialSection.getString(Section + ".Messages.Sub");
	}
	
	public String getMethodName()
	{
		return this.MethodName;
	}
	
	public String getYamlerName()
	{
		return this.YamlerName;
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public String getMainMessage()
	{
		return this.MainMessage;
	}
	
	public String getSubMessage()
	{
		return this.SubMessage;
	}
	
	public YamlConfiguration getSection()
	{
		return this.TutorialSection;
	}
}
