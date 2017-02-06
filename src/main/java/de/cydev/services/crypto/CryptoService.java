package de.cydev.services.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import de.cydev.services.crypto.exceptions.FailedToDecryptException;
import de.cydev.services.crypto.exceptions.FailedToEncryptException;

@Service
public class CryptoService
{
	private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
	private static final String AES = "AES";
	private static final String UTF_8 = "UTF-8";

	public String encrypt(String secret, String initVector, String value) throws FailedToEncryptException
	{
		try
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(secret.getBytes(UTF_8), AES);

			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());

			return Base64.encodeBase64String(encrypted);
		}
		catch (Throwable t)
		{
			throw new FailedToEncryptException(t);
		}
	}

	public String decrypt(String secret, String initVector, String value) throws FailedToDecryptException
	{
		try
		{
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(secret.getBytes(UTF_8), AES);

			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.decodeBase64(value));

			return new String(original);
		}
		catch (Throwable t)
		{
			throw new FailedToDecryptException(t);
		}
	}
}
