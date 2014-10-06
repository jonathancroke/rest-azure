package com.microsoft.journey.azure.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.microsoft.journey.azure.exception.RestAzureException;
import com.microsoft.journey.azure.restcore.AzureRestGet;
import com.microsoft.journey.azure.restcore.Response;
import com.microsoft.journey.azure.restcore.RestAzure;

public final class Locations implements AzureRestGet {

	private RestAzure restAzure;
	public final String action = "locations";
	private StringBuffer sb = null;

	public Locations(RestAzure restAzure) throws RestAzureException {
		
		this.restAzure = restAzure;
		this.sb = new StringBuffer();
		get();
	}

	public Response get() throws RestAzureException {
		return (restAzure.getReq(action));
	}
}
