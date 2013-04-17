package org.mitre.kairon.data;

import java.util.List;

import org.mitre.kairon.model.RecipientInfo;

public interface RecipientInfoDAO {

	public abstract List<RecipientInfo> allRecipients();

}