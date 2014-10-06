package jaxbpj;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CreateAffinityGroup")
@XmlType(propOrder = {"name", "label", "description", "location"})
public class CreateAffinityGroup {

	private String name;
	private String label;
	private String description;
	private String location;


	@XmlElement(name = "Name", required = true)
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	@Override
    public String toString() {
        return "CreateHostedService{" +
                "Name='" + name + '\'' +
                ", Label='" + label + '\'' +
                ", Description='" + description + '\'' +
                ", Location='" + location + '\'' +
                '}';
    }

}
