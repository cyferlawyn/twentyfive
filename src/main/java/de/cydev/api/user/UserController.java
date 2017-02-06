package de.cydev.api.user;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.cydev.services.auth.AuthService;
import de.cydev.services.auth.exceptions.TokenInvalidException;
import de.cydev.services.user.UserService;
import de.cydev.services.user.exceptions.FailedToValidateUserException;
import de.cydev.services.user.exceptions.UserAlreadyExistsException;

@RestController
public class UserController
{
	@Autowired
	private Logger logger;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/api/user/register", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<PostRegisterResponse> postRegister(HttpServletRequest request,
	        @RequestBody PostRegisterRequest requestBody)
	{
		try
		{
			userService.register(requestBody.getUserName(), requestBody.getPasswordHash());
			String token = authService.issueToken(requestBody.getUserName());

			PostRegisterResponse responseBody = new PostRegisterResponse();
			responseBody.setToken(token);

			return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
		}
		catch (UserAlreadyExistsException e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		catch (Throwable t)
		{
			logger.log(Level.SEVERE, "Error while user registration", t);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/user/login", 
					method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
					produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<PostLoginResponse> postLogin(HttpServletRequest request,
	        @RequestBody PostLoginRequest requestBody)
	{
		try
		{
			userService.validateUser(requestBody.getUserName(), requestBody.getPasswordHash());
			String token = authService.issueToken(requestBody.getUserName());

			PostLoginResponse responseBody = new PostLoginResponse();
			responseBody.setToken(token);

			return new ResponseEntity<>(responseBody, HttpStatus.OK);
		}
		catch (FailedToValidateUserException e)
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		catch (Throwable t)
		{
			logger.log(Level.SEVERE, "Error while user login", t);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/api/user/logout", method = RequestMethod.POST)
	public @ResponseBody void postLogout()
	{

	}

	@RequestMapping(value = "/api/users/{userName}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getUser(@PathVariable("userName") String userName,
	        @RequestHeader("Authorization") String token)
	{
		try
		{
			authService.validateToken(token);
			return new ResponseEntity<>(userName, HttpStatus.OK);
		}
		catch (TokenInvalidException e)
		{
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		catch (Throwable t)
		{
			logger.log(Level.SEVERE, "Error while getting user data", t);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
