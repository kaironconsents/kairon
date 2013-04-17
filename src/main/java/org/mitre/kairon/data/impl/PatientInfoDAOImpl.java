package org.mitre.kairon.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mitre.kairon.data.PatientInfoDAO;
import org.mitre.kairon.model.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PatientInfoDAOImpl implements PatientInfoDAO {

	@Autowired
	private JdbcTemplate template;
	private static String SELECT_PATIENTS = "select patientID, patientKey from patient_info  ";
	private static RowMapper<PatientInfo> mapper = new RowMapper<PatientInfo>() {

		public PatientInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PatientInfo newPatient = new PatientInfo();
			newPatient.setPatientID(rs.getString("patientID"));
			newPatient.setPatientKey(rs.getString("patientKey"));
			return newPatient;
		}
	};

	/* (non-Javadoc)
	 * @see org.mitre.kairon.data.impl.PatientInfoDAO#allPatients()
	 */
	public List<PatientInfo> allPatients() {
		return template.query(SELECT_PATIENTS, mapper);
	}
}
