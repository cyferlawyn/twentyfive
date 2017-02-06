package de.cydev.services.user.exceptions;

public class UserAlreadyExistsException extends Exception
{
	private static final long serialVersionUID = -2112018121398275779L;
	
	public UserAlreadyExistsException()
	{
		super();
	}
	
	public UserAlreadyExistsException(Throwable t)
	{
		super(t);
	}
}
