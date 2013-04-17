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

public class RecipientInfo implements Serializable {

	private static final long serialVersionUID = 6825933968789747915L;
	private String recipientKey;
	private String recipientID;
	private String recipientEmail;
	private boolean vetted = false;

	public RecipientInfo() {
	}

	public RecipientInfo(String recipientKeyStr, String recipientIDStr,
			Boolean vettedStr) {
		super();
		recipientKey = recipientKeyStr;
		recipientID = recipientIDStr;
		vetted = vettedStr;

	}

	public RecipientInfo(String recipientKeyStr, String recipientIDStr,
			String recipientEmailStr, Boolean vettedStr) throws Exception {
		super();
		recipientKey = recipientKeyStr;
		recipientID = recipientIDStr;
		recipientEmail = recipientEmailStr;
		vetted = vettedStr;
	}

	public String getRecipientKey() {
		return recipientKey;
	}

	public void setRecipientKey(String recipientKeyStr) {
		recipientKey = recipientKeyStr;
	}

	public String getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(String recipientIDStr) {
		recipientID = recipientIDStr;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmailStr) {
		recipientEmail = recipientEmailStr;
	}

	public Boolean isVetted() {
		return vetted;
	}

	public void setVetted(Boolean vettedStr) {
		vetted = vettedStr;
	}

	public String toJSONString() {
		StringBuffer recipJSON = new StringBuffer("{");
		recipJSON.append("\"key\" : \"" + recipientKey + "\",");
		recipJSON.append("\"email\" : \"" + recipientEmail + "\",");
		recipJSON.append("\"id\" : \"" + recipientID + "\"}");

		return recipJSON.toString();
	}
}
