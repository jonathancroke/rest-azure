package com.microsoft.journey.azure.tools;

import com.microsoft.journey.azure.exception.RestAzureException;
import com.microsoft.journey.azure.restcore.Response;
import com.microsoft.journey.azure.restcore.RestAzure;

public class Main {

	public static void main(String[] args) throws RestAzureException {
		final String KEYSTORE_NAME = "/home/jcroke/proj/azure/keys/WindowsAzureKeyStore.jks";
		final String KEYSTORE_PASSWORD = "Asdf1234";
		final String SUBID = "1cb4d880-fa50-4c35-bce0-83252492bbe7";

		RestAzure restAzure = null;
		try {
			restAzure = new RestAzure(KEYSTORE_NAME, KEYSTORE_PASSWORD, SUBID, null);
		} catch (RestAzureException e) {
			e.printStackTrace();
		}

		
		Locations locations = new Locations(restAzure);
		Response response = locations.get();
		System.out.println("locations response code: " + response.getResponseCode());
		System.out.println("locations response content: " + response.getResponse());


//		HostedService cloudService = new HostedService(restAzure, "joncloud123", "joncloudlabel123",
//				"My Cloud Description", "East US", null);
//		response = cloudService.create();
//		System.out.println("\ncloudService response code: " + response.getResponseCode());
//		System.out.println("cloudService response content: " + response.getResponse());
//		
//		response = cloudService.delete();
//		System.out.println("\ncloudService delete response code: " + response.getResponseCode());
//		System.out.println("cloudService delete response content: " + response.getResponse());
		
		
//		AffinityGroup affinityGroup = new AffinityGroup(restAzure, "jonAffGrp1", "jonAffGrp1label123",
//				"My Affinity Deiption", "East US");
//		affinityGroup.create();
//		System.out.println("\naffinityGroup response code: " + response.getResponseCode());
//		System.out.println("affinityGroup response content: " + response.getResponse());
//		
//		response = affinityGroup.delete();
//		System.out.println("\naffinityGroup delete response code: " + response.getResponseCode());
//		System.out.println("affinityGroup delete response content: " + response.getResponse());
	}

}
