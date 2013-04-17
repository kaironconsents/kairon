package org.mitre.kairon.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.mitre.kairon.data.RecipientInfoDAO;
import org.mitre.kairon.model.RecipientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/recipients")
public class RecipientServiceRest {

	@Autowired
	RecipientInfoDAO recipientDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String processRecipientRequest() {
		// id =
		// DAOFactory.getDAOFactory().getRecipientInfoDAO().findByID(recipientID).getRecipientEmail();
		StringBuffer returnJSON = new StringBuffer();

		try {
			List<RecipientInfo> recipients = recipientDao.allRecipients();
			int noOfrecipients = recipients.size();
			if (noOfrecipients > 0)
				returnJSON.append("{\"Recipients\" :[");

			int count = 0;
			for (RecipientInfo rep : recipients) {
				count++;
				returnJSON.append(rep.toJSONString());
				if (count == noOfrecipients) {
					returnJSON.append("]}");
				} else {
					returnJSON.append(",");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("IDService results " + returnJSON.toString());
		return returnJSON.toString();
	}
}
