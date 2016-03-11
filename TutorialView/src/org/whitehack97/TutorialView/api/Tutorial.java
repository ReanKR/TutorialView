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

import org.whitehack97.TutorialView.api.MethodInterface;

public class Tutorial
{
	private String TutorialName;
	private List<MethodInterface> Interfaces = new ArrayList<MethodInterface>();

	public Tutorial(String TutorialName)
	{
		this.TutorialName = TutorialName;
	}
	
	public void setInterface(List<MethodInterface> Interfaces)
	{
		this.Interfaces = Interfaces;
	}
	
	public boolean addInterface(MethodInterface Interface)
	{
		for(MethodInterface Met : Interfaces)
		{
			if(Met.equals(Interface))
			{
				return false;
			}
		}
		Interfaces.add(Interface);
		return true;
	}
	
	public boolean delInterface(MethodInterface Interface)
	{
		for(MethodInterface Met : Interfaces)
		{
			if(Met.equals(Interface))
			{
				Interfaces.remove(Interface);
				return true;
			}
		}
		return false;
	}
	
	public String getTutorialName()
	{
		return this.TutorialName;
	}
	
	public List<MethodInterface> getMethodInterface()
	{
		return this.Interfaces;
	}
}
