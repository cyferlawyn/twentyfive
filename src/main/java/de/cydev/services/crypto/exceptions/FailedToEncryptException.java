package de.cydev.services.crypto.exceptions;

public class FailedToEncryptException extends Exception
{
	private static final long serialVersionUID = 6133951385409115161L;
	
	public FailedToEncryptException()
	{
		super();
	}
	
	public FailedToEncryptException(Throwable t)
	{
		super(t);
	}
}
