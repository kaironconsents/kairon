package org.mitre.kairon.data;


import java.util.List;

import org.mitre.kairon.model.Levels;

public interface LevelsDAO {

	void deleteAllShareLevels(String patientId);

	void setShareLevels(List<Levels> levels);

	void deleteAllTrustLevels(String patientId);

	void setTrustLevels(List<Levels> levels);

	List<Levels> getShareLevels(String patientId);

	List<Levels> getAllTrustLevels(String patientId);

}