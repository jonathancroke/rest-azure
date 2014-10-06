package restcore;

import exception.RestAzureException;

public interface AzureRestGet {
	public Response get() throws RestAzureException;
}
