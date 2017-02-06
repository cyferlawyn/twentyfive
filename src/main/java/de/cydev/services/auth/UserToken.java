package de.cydev.services.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserToken
{
	private String userName;
	private String issuer;
	private Date dateOfIssuing;
	private List<String> rights;

	public UserToken()
	{
		super();
	}
	
	public UserToken(String userName, String issuer)
	{
		super();

		this.userName = userName;
		this.issuer = issuer;
		this.dateOfIssuing = new Date();
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

	public String getIssuer()
	{
		return issuer;
	}

	public void setIssuer(String issuer)
	{
		this.issuer = issuer;
	}

	public Date getDateOfIssuing()
	{
		return dateOfIssuing;
	}

	public void setDateOfIssuing(Date dateOfIssuing)
	{
		this.dateOfIssuing = dateOfIssuing;
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
