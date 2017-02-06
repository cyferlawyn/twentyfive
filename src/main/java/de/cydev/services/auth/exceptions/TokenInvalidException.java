package de.cydev.services.auth.exceptions;

public class TokenInvalidException extends Exception
{
	private static final long serialVersionUID = -8215608468364364991L;
	
	public TokenInvalidException()
	{
		super();
	}
	
	public TokenInvalidException(Throwable t)
	{
		super(t);
	}
}
