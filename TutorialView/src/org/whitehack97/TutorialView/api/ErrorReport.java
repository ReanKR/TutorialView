package org.whitehack97.TutorialView.api;

public class ErrorReport
{
	private String Message;
	private String Method;
	private Object CausedMethod;
	private String Address;
	public void setAddress(String Address)
	{
		this.Address = Address;
	}

	public void setMessage(String Message)
	{
		this.Message = Message;
	}
	
	public void setMethod(String Method)
	{
		this.Method = Method;
	}
	
	public void setCausedMethod(Object Method)
	{
		this.CausedMethod = Method;
	}
	
	public String getAddress()
	{
		return this.Address;
	}
	
	public String getMessage()
	{
		return this.Message;
	}
	
	public String getMethod()
	{
		return this.Method;
	}
	
	public String getCausedMethod()
	{
		return this.CausedMethod.toString();
	}
}
