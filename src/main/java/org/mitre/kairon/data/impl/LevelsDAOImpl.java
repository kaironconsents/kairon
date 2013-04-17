package org.mitre.kairon.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.mitre.kairon.data.LevelsDAO;
import org.mitre.kairon.model.Levels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LevelsDAOImpl implements LevelsDAO {

	@Autowired
	private JdbcTemplate template;

	private static final RowMapper<Levels> shareMapper = new RowMapper<Levels>() {

		public Levels mapRow(ResultSet rs, int rowNum) throws SQLException {

			Levels level = new Levels(rs.getString("patientId"),
					rs.getInt("usageId"), rs.getInt("topicId"),
					rs.getInt("level"), rs.getString("start_date"),
					rs.getString("end_date"));
			level.setTopic(rs.getString("topic"));
			return level;
		}
	};

	private static final RowMapper<Levels> trustMapper = new RowMapper<Levels>() {

		public Levels mapRow(ResultSet rs, int rowNum) throws SQLException {

			Levels level = new Levels(rs.getString("patientId"),
					rs.getInt("usageId"), rs.getInt("topicId"),
					rs.getInt("level"), rs.getString("start_date"),
					rs.getString("end_date"));

			level.setStaffId(rs.getString("staffId"));

			return level;
		}
	};

	private static String SELECT_ALL_TRUST_LEVELS = "SELECT level, ptl.topicID as topicId, description as topic, start_date, end_date, staffId, usageId, ptl.patientID FROM patient_trust_level ptl left join topics on ptl.topicID = topics.topicID where ptl.patientID = ? ";
	private static String SELECT_TRUST_LEVELS = "SELECT level, ptl.topicID as topicId, description as topic, start_date, end_date, patientId FROM patient_trust_level ptl left join topics on ptl.topicID = topics.topicID where patientID = ?  and usageId=?  and staffId=? ";
	private static String INSERT_TRUST_LEVELS = "INSERT INTO patient_trust_level (patientId, staffId, usageId, topicId, level, start_date, end_date) VALUES (?,?,?,?,?,?,?)";
	private static String SELECT_SHARE_LEVELS = "SELECT level, psl.topicID as topicId, usageId, description as topic, start_date, end_date FROM patient_share_level psl, topics where psl.topicID = topics.topicID and  patientID = ? and usageId=? ";
	private static String SELECT_ALL_SHARE_LEVELS = "SELECT level, psl.topicID as topicId, usageId, description as topic, start_date, end_date, notes, psl.patientID FROM patient_share_level psl, topics where psl.topicID = topics.topicID and  psl.patientID = ? ";
	private static String INSERT_SHARE_LEVELS = "INSERT INTO patient_share_level (patientId, usageId, topicId, level, start_date, end_date) VALUES (?,?,?,?,?,?)";
	private static String DELETE_ALL_SHARE_LEVELS = "DELETE FROM patient_share_level WHERE patientId=? ";
	private static String DELETE_ALL_TRUST_LEVELS = "DELETE FROM patient_trust_level  WHERE patientId=? ";

	private static final String emptyDate = " 00:00:00.0";

	public List<Levels> getAllTrustLevels(String patientId) {
		return this.template.query(SELECT_ALL_TRUST_LEVELS, trustMapper,
				patientId);
	}

	public List<Levels> getTrustLevels(String patientId, String usageId,
			String providerId) throws Exception {
		return this.template.query(SELECT_TRUST_LEVELS, trustMapper, patientId,
				usageId, providerId);
	}

	public void setTrustLevels(final List<Levels> levels) {
		template.batchUpdate(INSERT_TRUST_LEVELS,
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Levels level = levels.get(i);
						ps.setString(1, level.getPatientId());
						ps.setInt(2, Integer.parseInt(level.getStaffId()));
						ps.setInt(3, level.getUsageId());
						ps.setInt(4, level.getTopicId());
						ps.setInt(5, level.getLevel());
						ps.setString(6, level.getStartDate());
						ps.setString(7, level.getEndDate());
						System.out.println("Levels : insert " + ps.toString());

					}

					public int getBatchSize() {
						return levels.size();
					}
				});
	}

	//
	public void setShareLevels(final List<Levels> levels) {
		template.batchUpdate(INSERT_SHARE_LEVELS,
				new BatchPreparedStatementSetter() {

					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Levels level = levels.get(i);
						ps.setString(1, level.getPatientId());
						ps.setInt(2, level.getUsageId());
						ps.setInt(3, level.getTopicId());
						ps.setInt(4, level.getLevel());

						if (level.getStartDate() == null
								|| (level.getStartDate().equals(emptyDate))) {
							ps.setNull(5, java.sql.Types.TIMESTAMP);
							Calendar calendar = Calendar.getInstance();
							calendar.set(Calendar.YEAR, 1970);
						} else {
							ps.setString(5, level.getStartDate());
						}

						if (level.getEndDate() == null
								|| (level.getEndDate().equals(emptyDate))) {
							Calendar calendar = Calendar.getInstance();
							calendar.set(Calendar.YEAR, 2070);
							ps.setNull(6, java.sql.Types.TIMESTAMP);
						} else {
							ps.setString(6, level.getEndDate());
						}

					}

					public int getBatchSize() {
						return levels.size();
					}
				});

	}

	//
	public void deleteAllShareLevels(String patientId) {
		template.update(DELETE_ALL_SHARE_LEVELS, patientId);
	}

	public void deleteAllTrustLevels(String patientId) {
		template.update(DELETE_ALL_TRUST_LEVELS, patientId);
	}

	//
	public List<Levels> getShareLevels(String patientId, String usageId) {
		return template.query(SELECT_SHARE_LEVELS, shareMapper, patientId,
				usageId);
	}

	public List<Levels> getShareLevels(String patientId) {
		return template.query(SELECT_ALL_SHARE_LEVELS, shareMapper, patientId);
	}

}
