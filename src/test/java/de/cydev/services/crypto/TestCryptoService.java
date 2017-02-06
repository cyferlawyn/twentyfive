package de.cydev.services.crypto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.cydev.services.crypto.exceptions.FailedToDecryptException;
import de.cydev.services.crypto.exceptions.FailedToEncryptException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCryptoService
{
	@Autowired
	private CryptoService cryptoService;

	@Test
	public void testEncrypt()
	{
		try
		{
			for (int i = 0; i < 100; i++)
			{
				String encryptedValue = cryptoService.encrypt("128BitSecret!!!!", "128BitIV!!!!!!!!", "testValue");

				assertThat(encryptedValue).isInstanceOf(String.class);
				assertThat(encryptedValue).isEqualTo("zF7lKEDx61fhLenCUIQkvQ==");
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}

		try
		{
			cryptoService.encrypt("Not128Bit", "128BitIV!!!!!!!!", "testValue");
			fail("Secrets without a length of 128 bit must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToEncryptException.class);
		}

		try
		{
			cryptoService.encrypt("128BitSecret!!!!", "Not128Bit", "testValue");

			fail("Init vectors without a length of 128 bit must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToEncryptException.class);
		}

		try
		{
			cryptoService.encrypt("128BitSecret!!!!", "128BitIV!!!!!!!!", null);
			fail("Encrypting a null value must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToEncryptException.class);
		}
	}

	@Test
	public void testDecrypt()
	{
		try
		{
			for (int i = 0; i < 100; i++)
			{
				String decryptedValue = cryptoService.decrypt("128BitSecret!!!!", "128BitIV!!!!!!!!",
				        "zF7lKEDx61fhLenCUIQkvQ==");

				assertThat(decryptedValue).isInstanceOf(String.class);
				assertThat(decryptedValue).isEqualTo("testValue");
			}
		}
		catch (Throwable t)
		{
			fail("No error expected");
		}

		try
		{
			cryptoService.decrypt("128BitSecret!!!!", "128BitIV!!!!!!!!", "notMultipleOf128BitLength");

			fail("Only values with a multiple of 128 bit length must be decrypted");
		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToDecryptException.class);
		}

		try
		{
			cryptoService.decrypt("Not128Bit", "128BitIV!!!!!!!!", "zF7lKEDx61fhLenCUIQkvQ==");
			fail("Secrets without a length of 128 bit must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToDecryptException.class);
		}

		try
		{
			cryptoService.decrypt("128BitSecret!!!!", "Not128Bit", "zF7lKEDx61fhLenCUIQkvQ==");

			fail("Init vectors without a length of 128 bit must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToDecryptException.class);
		}

		try
		{
			cryptoService.decrypt("128BitSecret!!!!", "128BitIV!!!!!!!!", null);
			fail("Decrypting a null value must fail");

		}
		catch (Throwable t)
		{
			assertThat(t).isInstanceOf(FailedToDecryptException.class);
		}
	}
}
