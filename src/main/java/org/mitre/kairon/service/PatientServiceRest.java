package org.mitre.kairon.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import org.mitre.kairon.data.PatientInfoDAO;
import org.mitre.kairon.model.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("patients")
@Component
public class PatientServiceRest {

	@Autowired
	PatientInfoDAO patientDao;

	/**
	 * Retrieves representation of an instance of
	 * org.mitre.consent.idservice.service.IDService
	 * 
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("application/json")
	public String processPatients() {
	
		StringBuffer returnJSON = new StringBuffer();

		try {
			List<PatientInfo> patients = patientDao.allPatients();
			int noOfpatients = patients.size();
			if (noOfpatients > 0)
				returnJSON.append("{\"Patients\" :[");

			int count = 0;
			for (PatientInfo pat : patients) {
				count++;
				returnJSON.append(pat.toJSONString());
				if (count == noOfpatients) {
					returnJSON.append("]}");
				} else {
					returnJSON.append(",");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("IDService patient results " + returnJSON.toString());

		return returnJSON.toString();
	}
}
