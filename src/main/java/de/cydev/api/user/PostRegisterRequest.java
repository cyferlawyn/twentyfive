package de.cydev.api.user;

public class PostRegisterRequest
{
	private String userName;
	private String passwordHash;
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPasswordHash()
	{
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}
}
