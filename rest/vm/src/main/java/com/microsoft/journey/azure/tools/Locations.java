package com.microsoft.journey.azure.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import restcore.AzureRestGet;
import restcore.Response;
import restcore.RestAzure;
import exception.RestAzureException;

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
