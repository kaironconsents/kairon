package org.mitre.kairon;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mitre.kairon.mock.MockUsagesDAO;
import org.mitre.kairon.service.PolicyServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-context.xml")
public class PolicyServiceRestTest {

	@Autowired
	PolicyServiceRest service;

	@Test
	public void testGetPolicy() {
		String response = service.getPolicy("1");
		JsonElement json = new Gson().fromJson(response, JsonElement.class);
		JsonObject obj = json.getAsJsonObject();
		JsonArray usages = obj.get("usages").getAsJsonArray();
			System.out.println(json);
		assertEquals(1, usages.size());
		JsonObject usage = usages.iterator().next().getAsJsonObject();
		
		assertEquals(MockUsagesDAO.TREAT_FIXTURE, usage.get("name").getAsString());
		
		JsonArray providers = usage.get("providers").getAsJsonArray();
		assertFalse(providers.isJsonNull());
		assertEquals(2, providers.size());
		
		JsonArray topics = usage.get("topics").getAsJsonArray();
		assertFalse(topics.isJsonNull());
		assertEquals(1, topics.size());
		
		JsonObject topic = topics.iterator().next().getAsJsonObject();
		assertEquals("3", topic.get("level").getAsString());
		
	}

}
