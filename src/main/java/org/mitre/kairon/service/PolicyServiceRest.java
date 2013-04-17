package org.mitre.kairon.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mitre.kairon.data.LevelsDAO;
import org.mitre.kairon.data.UsagesDAO;
import org.mitre.kairon.model.Levels;
import org.mitre.kairon.model.Policy;
import org.mitre.kairon.model.Policy.Provider;
import org.mitre.kairon.model.Policy.Topic;
import org.mitre.kairon.model.Policy.Usage;
import org.mitre.kairon.model.Usages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 * Unified view of patient's sharing policy
 * 
 * @author mhadley
 */
@Path("sharePolicy/{patientId}")
@Component
public class PolicyServiceRest {

	private Gson gson;

	@Autowired
	private UsagesDAO usagesDao;
	
	@Autowired
	private LevelsDAO levelsDao;

	public PolicyServiceRest() {
		this.gson = new Gson();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getPolicy(@PathParam("patientId") String patientId) {
		Policy policy = new Policy();
		policy.usages.addAll(getUsages(patientId));
		return gson.toJson(policy);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setPolicy(@PathParam("patientId") String patientId, String policyStr) {
		Policy policy = gson.fromJson(policyStr, Policy.class);

		regenerateShareLevels(patientId, policy);
		regenerateTrustLevels(patientId, policy);

		return getPolicy(patientId);
	}

	protected void regenerateShareLevels(String patientId, Policy policy) {
		levelsDao.deleteAllShareLevels(patientId);
		for (Usage usage : policy.usages) {
			levelsDao.setShareLevels(usage.getShareLevels(patientId));
		}
		
	}

	protected void regenerateTrustLevels(String patientId, Policy policy) {
		levelsDao.deleteAllTrustLevels(patientId);
		for (Usage usage : policy.usages) {
			levelsDao.setTrustLevels(usage.getTrustLevels(patientId));
		}
	}

	protected Collection<Usage> getUsages(String patientId) {
		List<Levels> shareLevels = levelsDao.getShareLevels(patientId);
		Map<String,Usages> usageLookupMap = usagesDao.getUsagesMap();
		Map<String, Usage> usages = new HashMap<String, Usage>();

		for (Levels level : shareLevels) {
			String usageId = String.valueOf(level.getUsageId());
			if (!usages.containsKey(usageId)) {
				Usage usage = new Usage();
				usage.id = usageId;
				usage.name = usageLookupMap.get(usageId).getDescription();
				usages.put(usageId, usage);
			}
			Usage usage = usages.get(usageId);

			usage.topics.add(new Topic(level));
			
		}
		
		Collection<Usage> usageList = usages.values();
		
		for (Usage usage : usageList) {
			usage.providers = getProviders(patientId, usage.id);
		}
		
		return usages.values();
	}

	protected List<Provider> getProviders(String patientId, String usageId) {
		List<Levels> trustLevels = levelsDao.getAllTrustLevels(patientId);

		Map<String, List<Topic>> providerTopicMap = new HashMap<String, List<Topic>>();
		for (Levels level : trustLevels) {
			String providerUsageId = String.valueOf(level.getUsageId());
			if (providerUsageId.equals(usageId)) {
				String providerId = level.getStaffId();
				if (!providerTopicMap.containsKey(providerId)) {
					List<Topic> topics = new ArrayList<Topic>();
					providerTopicMap.put(providerId, topics);
				}
				List<Topic> topics = providerTopicMap.get(providerId);
				topics.add(new Topic(level));
			}
		}

		List<Provider> providers = new ArrayList<Provider>();
		for (String providerId : providerTopicMap.keySet()) {
			Provider provider = new Provider();
			provider.id = providerId;
			provider.topics.addAll(providerTopicMap.get(providerId));
			providers.add(provider);
		}
		return providers;
	}

}
