package org.mitre.kairon.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usages {

	private String usageID = "";
	private String description = "";

	public Usages() {}
	
	public Usages(String usageID, String description) {
		super();
		this.usageID = usageID;
		this.description = description;
	}

	public String getUsageID() {
		return usageID;
	}

	public void setUsageID(String usageID) {
		this.usageID = usageID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJSON() {
		StringBuffer rtnJSON = new StringBuffer();

		rtnJSON.append("{\"usageId\" : \"" + usageID + "\",");
		rtnJSON.append("\"description\" : \"" + description + "\"}");
		return rtnJSON.toString();
	}

	public static String toJSON(ArrayList<Usages> usages) {
		StringBuffer rtnJSON = new StringBuffer();
		rtnJSON.append("{\"purposes\":[");
		String comma = "";
		for (Usages usage : usages) {
			rtnJSON.append(comma);
			comma = ",";
			rtnJSON.append(usage.toJSON());
		}
		rtnJSON.append("]}");
		return rtnJSON.toString();
	}
}
