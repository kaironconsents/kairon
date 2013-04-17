package org.mitre.kairon.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Trust {

	private String staffID = "";
	private String description = "";
	private String staffKey = "";

	public String getStaffKey() {
		return staffKey;
	}

	public void setStaffKey(String staffKey) {
		this.staffKey = staffKey;
	}

	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toJSON() {
		StringBuffer rtnJSON = new StringBuffer();

		rtnJSON.append("{\"staffId\" : \"" + staffID + "\",");

		rtnJSON.append("\"key\" : \"" + staffKey + "\"}");
		return rtnJSON.toString();
	}

	public static String toJSON(ArrayList<Trust> relationships) {
		StringBuffer rtnJSON = new StringBuffer();
		rtnJSON.append("{\"relationships\":[");
		String comma = "";
		for (Trust relationship : relationships) {
			rtnJSON.append(comma);
			comma = ",";
			rtnJSON.append(relationship.toJSON());
		}
		rtnJSON.append("]}");
		return rtnJSON.toString();
	}

}
