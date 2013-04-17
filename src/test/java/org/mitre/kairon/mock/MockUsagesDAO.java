package org.mitre.kairon.mock;

import java.util.List;
import java.util.Map;

import org.mitre.kairon.data.UsagesDAO;
import org.mitre.kairon.model.Usages;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Component
public class MockUsagesDAO implements UsagesDAO {

	
	List<Usages> usages;
	public final static String TREAT_FIXTURE = "TREAT";
	
	public MockUsagesDAO() {
		usages = Lists.newArrayList(new Usages("1", TREAT_FIXTURE));
	}
	
	public List<Usages> getUsages() {
		return usages;
	}

	public Map<String, Usages> getUsagesMap() {
		Map<String, Usages> map = Maps.newHashMap();
		for (Usages usage : usages) {
			map.put(usage.getUsageID(), usage);
		}
		return map;
	}

}
