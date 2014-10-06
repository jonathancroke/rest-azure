package com.microsoft.journey.azure.tools;

import com.microsoft.journey.azure.exception.RestAzureException;
import com.microsoft.journey.azure.jaxbpj.CreateHostedService;
import com.microsoft.journey.azure.restcore.AzureRestPost;
import com.microsoft.journey.azure.restcore.Response;
import com.microsoft.journey.azure.restcore.RestAzure;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public final class HostedService implements AzureRestPost {

	private RestAzure restAzure;
	private String cloudServiceName;
	private String cloudServiceLabel;
	private String cloudServiceDescription;
	private String cloudServiceLocation;
	private String cloudServiceAffinityGroup;
	private final static String ACTION = "services/hostedservices";
	private final static String TYPE = "application/xml";

	public HostedService(RestAzure restAzure, String cloudServiceName,
			String cloudServiceLabel, String cloudServiceDescription,
			String cloudServiceLocation, String cloudServiceAffinityGroup)
			throws RestAzureException {

		this.restAzure = restAzure;
		this.cloudServiceName = cloudServiceName;
		this.cloudServiceLabel = cloudServiceLabel;
		this.cloudServiceDescription = cloudServiceDescription;
		this.cloudServiceLocation = cloudServiceLocation;
		this.cloudServiceAffinityGroup = cloudServiceAffinityGroup;
	}

	public Response create() throws RestAzureException {

		String cloudServiceLabelEncoded = ConvertToBase64String(cloudServiceLabel);
		CreateHostedService createHostedService = new CreateHostedService();

		createHostedService.setServiceName(cloudServiceName);
		createHostedService.setLabel(cloudServiceLabelEncoded);
		createHostedService.setDescription(cloudServiceDescription);
		
		if (cloudServiceLocation != null) {
			createHostedService.setLocation(cloudServiceLocation);
		}
		if (cloudServiceAffinityGroup != null) {
			createHostedService.setAffinity(cloudServiceAffinityGroup);
		}

		return (restAzure.postReq(ACTION, createHostedService, TYPE));
	}

	public Response delete() throws RestAzureException {
		return (restAzure.delReq(ACTION, cloudServiceName));
	}

	private String ConvertToBase64String(String value) {
		return Base64.encode(value.getBytes());
	}
}
