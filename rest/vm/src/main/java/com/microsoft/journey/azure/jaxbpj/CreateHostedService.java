package com.microsoft.journey.azure.jaxbpj;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateHostedService")
@XmlType(propOrder = {"serviceName", "label", "description", "location", "affinity"})
public class CreateHostedService {

	private String serviceName;
	private String label;
	private String description;
	private String location;
	private String affinity;


	@XmlElement(name = "ServiceName", required = true)
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	@XmlElement(name = "Label", required = true)
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@XmlElement(name = "Description", required = true)
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "Location")
	public void setLocation(String location) {
		if (this.affinity != null) {
			throw new ExceptionInInitializerError(
					"Cannot set Location if Affinity group is already set.");

		}
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public String getAffinity() {

		return affinity;
	}

	@XmlElement(name = "Affinity")
	public void setAffinity(String affinity) {
		if (this.location != null) {
			throw new ExceptionInInitializerError(
					"Cannot set Affinity group if Location is already set.");
		}
		this.affinity = affinity;
	}
	
	@Override
    public String toString() {
        return "CreateHostedService{" +
                "ServiceName='" + serviceName + '\'' +
                ", Label='" + label + '\'' +
                ", Description='" + description + '\'' +
                ", Location='" + location + '\'' +
                ", Affinity='" + affinity + '\'' +
                '}';
    }

}
