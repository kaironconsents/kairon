package org.mitre.kairon.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.mitre.kairon.data.RecipientInfoDAO;
import org.mitre.kairon.model.RecipientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RecipientInfoDAOImpl implements RecipientInfoDAO {

	@Autowired
	JdbcTemplate template;

	private static String SELECT_RECIPIENTS = "select recipientID, recipientKey, recipientEmail from recipient_info";

	RowMapper<RecipientInfo> mapper = new RowMapper<RecipientInfo>() {

		public RecipientInfo mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			RecipientInfo newRecip = new RecipientInfo();
			newRecip.setRecipientEmail(resultSet.getString("recipientEmail"));
			newRecip.setRecipientID(resultSet.getString("recipientID"));
			newRecip.setRecipientKey(resultSet.getString("recipientKey"));
			return newRecip;
		}
	};

	/* (non-Javadoc)
	 * @see org.mitre.kairon.data.impl.RecipientInfoDAO#allRecipients()
	 */
	public List<RecipientInfo> allRecipients() {
		return template.query(SELECT_RECIPIENTS, mapper);
	}
}
