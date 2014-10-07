package com.microsoft.journey.azure.jaxbpj.network;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Subnet")
public class Subnet {

	private String name;
	private String addressPrefix;

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name = "AddressPrefix", required = true)
	public void setAddressPrefix(String addressPrefix) {
		this.addressPrefix = addressPrefix;
	}

	public String getAddressPrefix() {
		return addressPrefix;
	}

	@Override
	public String toString() {
		return "CreateHostedService{" + "Name='" + name + '\''
				+ ", AddressPrefix='" + addressPrefix + '\'' + '}';
	}

}
