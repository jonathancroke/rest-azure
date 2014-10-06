package com.microsoft.journey.azure.restcore;

import com.microsoft.journey.azure.exception.RestAzureException;

public interface AzureRestGet {
	public Response get() throws RestAzureException;
}
