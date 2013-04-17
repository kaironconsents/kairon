package org.mitre.kairon.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Levels {

	private String patientId;
	private int usageId;
	private String staffId;
	private int level;
	private int topicId;
	private String topic;
	private String startDate = null;
	private String endDate = null;

	public Levels() {
	}

	public Levels(String patientId, int usageId) {
		this.patientId = patientId;
		this.usageId = usageId;

	}

	public Levels(String patientId, int usageId, int topicId, int providerId) {
		this.patientId = patientId;
		this.usageId = usageId;
		this.topicId = topicId;
	}

	public Levels(String patientId, int usageId, int topicId, int level,
			String startDate, String endDate) {
		this.patientId = patientId;
		this.usageId = usageId;
		this.topicId = topicId;
		this.level = level;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Levels(String patientId, int usageId, String staffId, int level,
			String startDate, String endDate) {
		super();
		this.patientId = patientId;
		this.usageId = usageId;
		this.staffId = staffId;
		this.level = level;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// need to specify how these will mix together
	public int calculateTotalLevelOfSharing(int trustLevel, int shareLevel) {
		return trustLevel + shareLevel;

	}

	public String getPatientId() {
		return patientId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public int getUsageId() {
		return usageId;
	}

	public void setUsageId(int usageId) {
		this.usageId = usageId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
