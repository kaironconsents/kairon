package org.mitre.kairon.mock;

import java.util.List;

import org.mitre.kairon.data.PatientInfoDAO;
import org.mitre.kairon.model.PatientInfo;
import org.springframework.stereotype.Component;

@Component
public class MockPatientInfoDAO implements PatientInfoDAO {

	public List<PatientInfo> allPatients() {
		// TODO Auto-generated method stub
		return null;
	}

}
