/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mitre.kairon.model;

import java.io.Serializable;

/**
 * 
 * @author ghamilton
 */

public class PatientInfo implements Serializable {

	private static final long serialVersionUID = -6008491205036319084L;
	private String patientKey;
	private String patientID;

	public PatientInfo() {
	}

	public PatientInfo(String patientKeyStr, String patientIDStr,
			String systemIDStr) throws Exception {
		patientKey = patientKeyStr;
		patientID = patientIDStr;
	}

	public String getPatientKey() {
		return patientKey;
	}

	public void setPatientKey(String patientKeyStr) {
		patientKey = patientKeyStr;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientIDStr) {
		patientID = patientIDStr;
	}

	public String toJSONString() {
		StringBuffer recipJSON = new StringBuffer("{");
		recipJSON.append("\"key\" : \"" + patientKey + "\",");
		recipJSON.append("\"id\" : \"" + patientID + "\"}");

		return recipJSON.toString();
	}

}
