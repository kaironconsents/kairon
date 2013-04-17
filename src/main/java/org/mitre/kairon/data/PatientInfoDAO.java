package org.mitre.kairon.data;

import java.util.List;

import org.mitre.kairon.model.PatientInfo;

public interface PatientInfoDAO {

	public abstract List<PatientInfo> allPatients();

}