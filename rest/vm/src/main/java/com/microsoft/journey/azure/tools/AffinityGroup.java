package com.microsoft.journey.azure.tools;

import jaxbpj.CreateAffinityGroup;
import restcore.AzureRestPost;
import restcore.Response;
import restcore.RestAzure;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import exception.RestAzureException;

public final class AffinityGroup implements AzureRestPost {

	private RestAzure restAzure;
	private String affinityGroupName;
	private String affinityGroupLabel;
	private String affinityGroupDescription;
	private String affinityGroupLocation;
	private final static String ACTION = "affinitygroups";
	private final static String TYPE = "application/xml";

	public AffinityGroup(RestAzure restAzure, String affinityGroupName,
			String affinityGroupLabel, String affinityGroupDescription,
			String affinityGroupLocation) throws RestAzureException {

		this.restAzure = restAzure;
		this.affinityGroupName = affinityGroupName;
		this.affinityGroupLabel = affinityGroupLabel;
		this.affinityGroupDescription = affinityGroupDescription;
		this.affinityGroupLocation = affinityGroupLocation;
	}

	public Response create() throws RestAzureException {

		CreateAffinityGroup createAffinityGroup = new CreateAffinityGroup();

		createAffinityGroup.setName(affinityGroupName);
		createAffinityGroup.setLabel(ConvertToBase64String(affinityGroupLabel));
		createAffinityGroup.setDescription(affinityGroupDescription);
		createAffinityGroup.setLocation(affinityGroupLocation);

		return (restAzure.postReq(ACTION, createAffinityGroup, TYPE));
	}

	public Response delete() throws RestAzureException {
		return (restAzure.delReq(ACTION, affinityGroupName));
	}

	private String ConvertToBase64String(String value) {
		return Base64.encode(value.getBytes());
	}
}
