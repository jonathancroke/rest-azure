package com.microsoft.journey.azure.sec;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import com.microsoft.journey.azure.exception.RestAzureException;

public class AzureSecurity {

	private String keyStoreName;
	private String keyStorePassword;

	public AzureSecurity(String keyStoreName, String keyStorePassword) {
		this.keyStoreName = keyStoreName;
		this.keyStorePassword = keyStorePassword;
	}

	public SSLSocketFactory getSSLSocketFactory() throws RestAzureException {

		KeyStore ks;
		SSLContext context = null;

		try {
			ks = getKeyStore();

			KeyManagerFactory keyManagerFactory;
			keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
			keyManagerFactory.init(ks, keyStorePassword.toCharArray());

			context = SSLContext.getInstance("TLS");
			context.init(keyManagerFactory.getKeyManagers(), null,
					new SecureRandom());
		} catch (Exception e) {
			throw new RestAzureException(e.getMessage());
		}
		return context.getSocketFactory();
	}

	private KeyStore getKeyStore() throws IOException {
		
		KeyStore ks = null;
		FileInputStream fis = null;
		
		try {
			ks = KeyStore.getInstance("JKS");
			char[] passwordArray = keyStorePassword.toCharArray();
			fis = new java.io.FileInputStream(keyStoreName);
			ks.load(fis, passwordArray);
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		return ks;
	}
}
