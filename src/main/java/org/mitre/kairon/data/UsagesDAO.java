package org.mitre.kairon.data;

import java.util.List;
import java.util.Map;

import org.mitre.kairon.model.Usages;

public interface UsagesDAO {

	public abstract List<Usages> getUsages();

	public abstract Map<String, Usages> getUsagesMap();

}