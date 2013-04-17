package org.mitre.kairon.mock;

import java.util.List;

import org.mitre.kairon.data.LevelsDAO;
import org.mitre.kairon.model.Levels;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component
public class MockLevelsDAO implements LevelsDAO {

	List<Levels> trustLevels;
	List<Levels> shareLevels;
	
	public MockLevelsDAO() {
		trustLevels = Lists.newArrayList(new Levels("1", 1, "1", 1, null, null), new Levels("1", 1, "2", 2, null, null));
		shareLevels = Lists.newArrayList(new Levels("1", 1, 0, 3, null, null));
	}
	
	public void deleteAllShareLevels(String patientId) {
		this.shareLevels.clear();
	}

	public void setShareLevels(List<Levels> levels) {
		this.shareLevels = levels;
	}

	public void deleteAllTrustLevels(String patientId) {
		this.trustLevels.clear();
	}

	public void setTrustLevels(List<Levels> levels) {
		trustLevels = levels;
	}

	public List<Levels> getShareLevels(String patientId) {
		return shareLevels;
	}

	public List<Levels> getAllTrustLevels(String patientId) {
		return trustLevels;
	}

}
