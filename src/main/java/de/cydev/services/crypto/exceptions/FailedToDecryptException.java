package de.cydev.services.crypto.exceptions;

public class FailedToDecryptException extends Exception
{
	private static final long serialVersionUID = 635364211669553108L;
	
	public FailedToDecryptException()
	{
		super();
	}
	
	public FailedToDecryptException(Throwable t)
	{
		super(t);
	}
}
