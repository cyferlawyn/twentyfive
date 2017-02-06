package de.cydev.services.auth.exceptions;

public class FailedToIssueTokenException extends Exception
{
	private static final long serialVersionUID = -8609024837856033511L;
	
	public FailedToIssueTokenException()
	{
		super();
	}
	
	public FailedToIssueTokenException(Throwable t)
	{
		super(t);
	}
}
