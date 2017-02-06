package de.cydev.services.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.cydev.services.user.exceptions.FailedToValidateUserException;
import de.cydev.services.user.exceptions.UserAlreadyExistsException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest
{
	@Autowired
	private UserService userService;

	@Test
	public void testRegister()
	{
		try
		{
			for (int i = 0; i < 100; i++)
			{
				userService.register("testUserName" + i, "testPasswordHash");
				UserEntity userEntity = userService.getUser("testUserName" + i);

				assertThat(userEntity).isInstanceOf(UserEntity.class);
				assertThat(userEntity).isNotNull();
				assertThat(userEntity.getUserName()).isEqualTo("testUserName" + i);
				assertThat(userEntity.getPasswordHash()).isEqualTo("testPasswordHash");
				assertThat(userEntity.getCreationDate()).isNotNull();
				assertThat(userEntity.getLastLoginDate()).isNull();
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}

		try
		{
			userService.register("testUserName1", "testPasswordHash");
			fail("No double registration allowed");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(UserAlreadyExistsException.class);
		}
	}

	@Test
	public void testValidateUser()
	{
		try
		{
			userService.register("testUserName", "testPasswordHash");

			for (int i = 0; i < 100; i++)
			{
				userService.validateUser("testUserName", "testPasswordHash");
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}

		try
		{
			userService.validateUser("testUserName", "testWrongPasswordHash");
			fail("Failed validation must not go through");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToValidateUserException.class);
		}

		try
		{
			userService.validateUser("testWrongUserName", "testPasswordHash");
			fail("Failed validation must not go through");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToValidateUserException.class);
		}
	}
}
