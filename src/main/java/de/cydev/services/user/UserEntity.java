package de.cydev.services.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserEntity
{
	private String userName;
	private String passwordHash;
	private Date creationDate;
	private Date lastLoginDate;
	private List<String> rights;

	public UserEntity(String userName, String passwordHash)
	{
		super();

		this.userName = userName;
		this.passwordHash = passwordHash;
		this.creationDate = new Date();
		this.rights = new ArrayList<>();
	}

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

	public Date getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}

	public Date getLastLoginDate()
	{
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	public List<String> getRights()
	{
		return rights;
	}

	public void setRights(List<String> rights)
	{
		this.rights = rights;
	}
}
