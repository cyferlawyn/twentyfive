package de.cydev.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cydev.services.auth.exceptions.FailedToIssueTokenException;
import de.cydev.services.auth.exceptions.TokenInvalidException;
import de.cydev.services.crypto.CryptoService;
import de.cydev.services.user.UserEntity;
import de.cydev.services.user.UserService;

@Service
public class AuthService
{
	private static final String ISSUER = "de.cydev.twentyfive";

	private static final String SECRET = "DKN9046dzh81369F";

	private static final String IV = "IUSNVZE84jf73SVR";

	@Autowired
	private UserService userService;
	
	@Autowired
	private CryptoService cryptoService;

	private ObjectMapper objectMapper;

	public AuthService()
	{
		objectMapper = new ObjectMapper();
	}

	public void validateToken(String token) throws TokenInvalidException
	{
		try
		{
			String tokenJson = cryptoService.decrypt(SECRET, IV, token);
			
			UserToken userToken = objectMapper.readValue(tokenJson, UserToken.class);
			
			if (userToken.getIssuer().equals(ISSUER))
			{
				return;
			}
		}
		catch (Throwable t)
		{
			throw new TokenInvalidException(t);
		}
	}

	public String issueToken(String userName) throws FailedToIssueTokenException
	{
		try
		{
			UserEntity userEntity = userService.getUser(userName);
			UserToken userToken = new UserToken(userName, ISSUER);
			userToken.setRights(userEntity.getRights());
			
			String tokenJson = objectMapper.writeValueAsString(userToken);
			
			return cryptoService.encrypt(SECRET, IV, tokenJson);
		}
		catch (Throwable t)
		{
			throw new FailedToIssueTokenException(t);
		}
	}
}
