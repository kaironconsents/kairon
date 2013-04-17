package org.mitre.kairon.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.kairon.data.UsagesDAO;
import org.mitre.kairon.model.Usages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UsagesDAOImpl implements UsagesDAO {

	@Autowired
	private JdbcTemplate template;

	private static String SELECT_USAGES = "SELECT usageID, description FROM usages";
	private static final RowMapper<Usages> mapper = new RowMapper<Usages>() {

		public Usages mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Usages newUsage = new Usages();
			String usageID = resultSet.getString("usageID");
			String description = resultSet.getString("description");
			newUsage.setUsageID(usageID);
			newUsage.setDescription(description);
			return newUsage;
		}
	};

	/* (non-Javadoc)
	 * @see org.mitre.kairon.data.impl.UsageDAO#getUsages()
	 */
	public List<Usages> getUsages() {
		return this.template.query(SELECT_USAGES, mapper);
	}

	/* (non-Javadoc)
	 * @see org.mitre.kairon.data.impl.UsageDAO#getUsagesMap()
	 */
	public Map<String, Usages> getUsagesMap() {
		List<Usages> usages = getUsages();
		Map<String, Usages> map = new HashMap<String, Usages>();
		for (Usages usage : usages)
			map.put(usage.getUsageID(), usage);
		return map;
	}
}
