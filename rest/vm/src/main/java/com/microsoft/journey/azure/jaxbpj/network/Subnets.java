package com.microsoft.journey.azure.jaxbpj.network;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Subnets {
	@XmlElementWrapper(name = "Subnets")
	@XmlElement(name = "Subnet")
	private ArrayList<Subnet> subnets;

	public void setBookList(ArrayList<Subnet> subnets) {
		this.subnets = subnets;
	}

	public ArrayList<Subnet> getSubnets() {
		return subnets;
	}

}
