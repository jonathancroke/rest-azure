package com.microsoft.journey.azure.restcore;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.microsoft.journey.azure.exception.RestAzureException;
import com.microsoft.journey.azure.sec.AzureSecurity;

public class RestAzure {

	private static final String MANAGEMENT_API_URL = "https://management.core.windows.net";
	private static String XMS_VERSION = "2014-06-01";

	private SSLSocketFactory sslSocketFactory;
	private String xmsVersionString;
	private String uidString;
	private AzureSecurity azureSecurity = null;

	public RestAzure(String keyStoreName, String keyStorePassword, String uid,
			String xmsVersion) throws RestAzureException {
		this.uidString = uid;
		this.xmsVersionString = (xmsVersion == null ? XMS_VERSION : xmsVersion);
		this.azureSecurity = new AzureSecurity(keyStoreName, keyStorePassword);
		this.initSSLFactory();
	}

	public Response getReq(String action) throws RestAzureException {
		URL url = getActionUrl(action);
		HttpsURLConnection con = null;
		int responseCode;
		InputStreamReader isr = null;
		StringBuffer sb = null;

		try {
			con = (HttpsURLConnection) url.openConnection();
			con.setSSLSocketFactory(this.sslSocketFactory);
			con.setRequestMethod("GET");
			con.addRequestProperty("x-ms-version", xmsVersionString);
			responseCode = con.getResponseCode();

			isr = new InputStreamReader((InputStream) con.getContent());
			String inputLine = null;
			sb = new StringBuffer();
			BufferedReader in = new BufferedReader(isr);

			while ((inputLine = in.readLine()) != null)
				sb.append(inputLine);

			in.close();

		} catch (IOException e) {
			throw new RestAzureException(e.getMessage());
		}

		return new Response(responseCode, sb.toString());
	}

	public Response postReq(String action, Object toMarshal, String contentType)
			throws RestAzureException {

		URL url = getActionUrl(action);
		HttpsURLConnection con = null;
		JAXBContext jaxbContext = null;
		int responseCode;
		String responseMessage;

		try {
			jaxbContext = JAXBContext.newInstance(toMarshal.getClass());

			byte[] data = unMarshallJAXB(jaxbContext, toMarshal);
			con = (HttpsURLConnection) url.openConnection();

			con.setSSLSocketFactory(this.sslSocketFactory);
			con.setDoOutput(true);
			con.setRequestMethod("POST");

			con.addRequestProperty("x-ms-version", xmsVersionString);

			con.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			con.setRequestProperty("Content-Type", contentType);

			DataOutputStream requestStream = null;
			requestStream = new DataOutputStream(con.getOutputStream());
			requestStream.write(data);
			requestStream.flush();
			requestStream.close();
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();

		} catch (Exception e) {
			e.printStackTrace();
			throw new RestAzureException(e.getMessage());
		}
		return (new Response(responseCode, responseMessage));
	}
	
	public Response delReq(String action, String id) throws RestAzureException {

		URL url = null;
		url = getActionUrl(action, id);
		SSLSocketFactory sslFactory = this.sslSocketFactory;
		HttpsURLConnection con = null;
		int responseCode;
		String responseMessage;
		
		try {
			con = (HttpsURLConnection) url.openConnection();
			con.setSSLSocketFactory(sslFactory);
			con.setRequestMethod("DELETE");
			con.addRequestProperty("x-ms-version", xmsVersionString);
			responseCode = con.getResponseCode();
			responseMessage = con.getResponseMessage();
			
		} catch (Exception e) {
			throw new RestAzureException(e.getMessage());
		}
		
		return (new Response(responseCode, responseMessage));
	}
	
	private void initSSLFactory() throws RestAzureException {
		this.sslSocketFactory = azureSecurity.getSSLSocketFactory();
	}

	private byte[] unMarshallJAXB(JAXBContext jaxbContext, Object toMarshal)
			throws RestAzureException {

		final StringWriter stringWriter = new StringWriter();
		try {
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);

			jaxbMarshaller.marshal(toMarshal, stringWriter);

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RestAzureException(e.getMessage());
		}
		return stringWriter.toString().getBytes();
	}

	private URL getActionUrl(String action) throws RestAzureException {
		String urlStr = String.format("%s/%s/%s", MANAGEMENT_API_URL,
				this.uidString, action);

		URL url = null;
		try {
			url = new URL(String.format(urlStr));
		} catch (MalformedURLException e) {
			throw new RestAzureException("Bad URL format: " + urlStr);
		}
		return url;
	}

	private URL getActionUrl(String action, String id)
			throws RestAzureException {
		String urlStr = String.format("%s/%s/%s/%s", MANAGEMENT_API_URL,
				this.uidString, action, id);

		URL url = null;
		try {
			url = new URL(String.format(urlStr));
		} catch (MalformedURLException e) {
			throw new RestAzureException("Bad URL format: " + urlStr);
		}
		return url;
	}
}
