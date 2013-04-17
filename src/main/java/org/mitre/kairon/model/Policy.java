package org.mitre.kairon.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class Policy {

	public List<Usage> usages;

	public Policy() {
		usages = new ArrayList<Usage>();
	}

	public static class Topic {
		public String id;
		public String name;
		public String level;
		public String start;
		public String end;

		public Topic() {
		}

		public Topic(String id, String name, String level, String start,
				String end) {
			super();
			this.id = id;
			this.name = name;

			this.level = level;
			this.start = start;
			this.end = end;
		}
		
		public Topic(Levels level) {
			this.id = String.valueOf(level.getTopicId());
			this.name = level.getTopic();
			this.start = dbDateToUSA(level.getStartDate());
			this.end = dbDateToUSA(level.getEndDate());
			this.level = String.valueOf(level.getLevel());
		}
		
		protected static String dbDateToUSA(String dbDate) {
			try {
				SimpleDateFormat dbFormat = new SimpleDateFormat();
				dbFormat.applyPattern("yyyy-MM-dd");
				Date d = dbFormat.parse(dbDate);
				SimpleDateFormat usaFormat = new SimpleDateFormat();
				usaFormat.applyPattern("M/d/yyyy");
				return usaFormat.format(d);
			} catch (Exception e) {
				return null;
			}
		}


	}

	public static class Usage {
		public String id;
		public String name;
		public String description;
		public List<Topic> topics;
		public List<Provider> providers;

		public Usage() {
			topics = new ArrayList<Topic>();
			providers = new ArrayList<Provider>();
		}

		public List<Levels> getShareLevels(final String patientId) {
			return getShareLevels(patientId, this);
		}

		public List<Levels> getTrustLevels(final String patientId) {
			return getTrustLevels(patientId, this);
		}

		protected static String usaDateToDB(String usaDate) {
			try {
				SimpleDateFormat usaFormat = new SimpleDateFormat();
				usaFormat.applyPattern("M/d/yyyy");
				Date d = usaFormat.parse(usaDate);
				SimpleDateFormat dbFormat = new SimpleDateFormat();
				dbFormat.applyPattern("yyyy-MM-dd");
				return dbFormat.format(d);
			} catch (Exception e) {
				return null;
			}

		}


		public static List<Levels> getShareLevels(final String patientId,
				final Usage usage) {
			return Lists.transform(usage.topics, new Function<Topic, Levels>() {
				public Levels apply(Topic t) {
					return new Levels(patientId, Integer.parseInt(usage.id),
							Integer.parseInt(t.id), Integer.parseInt(t.level),
							usaDateToDB(t.start), usaDateToDB(t.end));
				}
			});
		}

		public static List<Levels> getTrustLevels(final String patientId,
				final Usage usage) {
			return Lists.transform(usage.providers,
					new Function<Provider, Levels>() {
						public Levels apply(Provider p) {
							Topic t = p.topics.iterator().next();
							return new Levels(patientId, Integer
									.parseInt(usage.id), p.id, Integer
									.parseInt(t.level), t.start, t.end);
						}
					});
		}

	}

	public static class Provider {
		public String id;
		public String name;
		public List<Topic> topics;

		public Provider() {
			topics = new ArrayList<Topic>();
		}
	}

}
