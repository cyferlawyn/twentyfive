package de.cydev.services.user.exceptions;

public class FailedToValidateUserException extends Exception
{
	private static final long serialVersionUID = 4932131958664474060L;
	
	public FailedToValidateUserException()
	{
		super();
	}
	
	public FailedToValidateUserException(Throwable t)
	{
		super(t);
	}
}
