package de.cydev.services.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.cydev.services.auth.exceptions.FailedToIssueTokenException;
import de.cydev.services.auth.exceptions.TokenInvalidException;
import de.cydev.services.user.UserService;
import de.cydev.services.user.exceptions.UserAlreadyExistsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAuthService
{
	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@Test
	public void testIssueToken() throws UserAlreadyExistsException
	{
		try
		{
			authService.issueToken("testUserNameAuth");
			fail("Issuing a token for a non-existing user must not work");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToIssueTokenException.class);
		}

		userService.register("testUserNameAuth", "testPasswordHash");

		try
		{
			for (int i = 0; i < 100; i++)
			{
				String token = authService.issueToken("testUserNameAuth");

				assertThat(token).isInstanceOf(String.class);
				assertThat(token).startsWith("FjLtUMEEJjUD");
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}
	}

	@Test
	public void testValidateToken() throws UserAlreadyExistsException, FailedToIssueTokenException
	{
		String token = authService.issueToken("testUserNameAuth");
		
		try
		{
			for (int i = 0; i < 100; i++)
			{
				authService.validateToken(token);
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}
		
		try
		{
			authService.validateToken("No valid token");
			fail("Invalid tokens must not pass validation");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(TokenInvalidException.class);
		}
	}
}
