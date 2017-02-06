package de.cydev.services.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import de.cydev.services.user.exceptions.FailedToValidateUserException;
import de.cydev.services.user.exceptions.UserAlreadyExistsException;

@Service
public class UserService
{
	private Map<String, UserEntity> users;
	
	public UserService()
	{
		this.users = new HashMap<>();
	}
	
	public void register(String userName, String passwordHash) throws UserAlreadyExistsException
	{
		if (users.containsKey(userName))
		{
			throw new UserAlreadyExistsException();
		}
		
		UserEntity user = new UserEntity(userName, passwordHash);
		users.put(userName, user);
	}
	
	public void validateUser(String userName, String passwordHash) throws FailedToValidateUserException
	{
		if (users.containsKey(userName))
		{
			UserEntity user = users.get(userName);
			if (user.getPasswordHash().equals(passwordHash))
			{
				return;
			}
		}
		
		throw new FailedToValidateUserException();
	}
	
	public UserEntity getUser(String userName)
	{
		return users.get(userName);
	}
}
