package com.microsoft.journey.azure.restcore;

import com.microsoft.journey.azure.exception.RestAzureException;

public interface AzureRestPost {
	public Response create() throws RestAzureException;
	public Response delete() throws RestAzureException;
}
