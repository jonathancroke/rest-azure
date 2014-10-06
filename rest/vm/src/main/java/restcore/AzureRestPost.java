package restcore;

import exception.RestAzureException;

public interface AzureRestPost {
	public Response create() throws RestAzureException;
	public Response delete() throws RestAzureException;
}
