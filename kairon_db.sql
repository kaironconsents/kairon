# ************************************************************
# Sequel Pro SQL dump
# Version 4004
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.6.10)
# Database: kaironconsents
# Generation Time: 2013-04-08 14:38:27 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table patient_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient_info`;

CREATE TABLE `patient_info` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `patientID` varchar(255) DEFAULT NULL,
  `patientKey` varchar(255) DEFAULT NULL,
  `systemID` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `patient_info` WRITE;
/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;

INSERT INTO `patient_info` (`UID`, `patientID`, `patientKey`, `systemID`, `version`)
VALUES
	(1,'1','John Doe',NULL,1);

/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table patient_share_level
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient_share_level`;

CREATE TABLE `patient_share_level` (
  `patientShareLevelID` int(11) NOT NULL AUTO_INCREMENT,
  `patientID` int(11) NOT NULL,
  `usageID` int(11) NOT NULL,
  `topicID` int(11) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`patientShareLevelID`),
  KEY `patientID` (`patientID`),
  KEY `usageID` (`usageID`),
  KEY `FK_patient_share_level_topic` (`topicID`),
  CONSTRAINT `FK_patient_share_level_topic` FOREIGN KEY (`topicID`) REFERENCES `topics` (`topicID`),
  CONSTRAINT `FK_patient_share_level_usage` FOREIGN KEY (`usageID`) REFERENCES `usages` (`usageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `patient_share_level` WRITE;
/*!40000 ALTER TABLE `patient_share_level` DISABLE KEYS */;

INSERT INTO `patient_share_level` (`patientShareLevelID`, `patientID`, `usageID`, `topicID`, `level`, `start_date`, `end_date`)
VALUES
	(355,1,3,0,1,NULL,NULL),
	(356,1,2,0,3,NULL,NULL),
	(357,1,1,14,4,'2013-02-15 00:00:00','2013-02-28 00:00:00'),
	(358,1,1,0,3,'2012-02-11 00:00:00','2013-02-14 00:00:00'),
	(359,1,1,9,1,NULL,NULL),
	(360,1,1,15,4,'1985-02-21 00:00:00',NULL),
	(361,1,1,10,1,NULL,NULL);

/*!40000 ALTER TABLE `patient_share_level` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table patient_specified_topic
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient_specified_topic`;

CREATE TABLE `patient_specified_topic` (
  `patientID` int(11) NOT NULL,
  `topicID` int(11) NOT NULL,
  `usageID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`patientID`,`topicID`,`usageID`),
  KEY `patientID` (`patientID`),
  KEY `topicID` (`topicID`),
  KEY `FK_patient_specified_topic_usage` (`usageID`),
  CONSTRAINT `FK_patient_specified_topic_topic` FOREIGN KEY (`topicID`) REFERENCES `topics` (`topicID`),
  CONSTRAINT `FK_patient_specified_topic_usage` FOREIGN KEY (`usageID`) REFERENCES `usages` (`usageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `patient_specified_topic` WRITE;
/*!40000 ALTER TABLE `patient_specified_topic` DISABLE KEYS */;

INSERT INTO `patient_specified_topic` (`patientID`, `topicID`, `usageID`)
VALUES
	(1,9,1),
	(1,10,1),
	(1,14,1),
	(1,15,1);

/*!40000 ALTER TABLE `patient_specified_topic` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table patient_trust_level
# ------------------------------------------------------------

DROP TABLE IF EXISTS `patient_trust_level`;

CREATE TABLE `patient_trust_level` (
  `patientTrustLevelID` int(11) NOT NULL AUTO_INCREMENT,
  `patientID` int(11) NOT NULL,
  `staffID` int(11) NOT NULL,
  `usageID` int(11) NOT NULL,
  `topicID` int(11) DEFAULT NULL,
  `level` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`patientTrustLevelID`),
  KEY `patientID` (`patientID`),
  KEY `usageID` (`usageID`),
  KEY `topicID` (`topicID`),
  KEY `FK_patient_trust_level_staff_xref` (`staffID`),
  CONSTRAINT `FK_patient_trust_level_staff_xref` FOREIGN KEY (`staffID`) REFERENCES `staff_xref` (`staffID`),
  CONSTRAINT `FK_patient_trust_level_topic` FOREIGN KEY (`topicID`) REFERENCES `topics` (`topicID`),
  CONSTRAINT `FK_patient_trust_level_usage` FOREIGN KEY (`usageID`) REFERENCES `usages` (`usageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `patient_trust_level` WRITE;
/*!40000 ALTER TABLE `patient_trust_level` DISABLE KEYS */;

INSERT INTO `patient_trust_level` (`patientTrustLevelID`, `patientID`, `staffID`, `usageID`, `topicID`, `level`, `start_date`, `end_date`)
VALUES
	(15,1,17,1,0,1,NULL,NULL),
	(16,1,12,1,0,1,NULL,NULL);

/*!40000 ALTER TABLE `patient_trust_level` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table recipient_info
# ------------------------------------------------------------

DROP TABLE IF EXISTS `recipient_info`;

CREATE TABLE `recipient_info` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `recipientEmail` varchar(255) DEFAULT NULL,
  `recipientID` varchar(255) DEFAULT NULL,
  `recipientKey` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `vetted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `recipient_info` WRITE;
/*!40000 ALTER TABLE `recipient_info` DISABLE KEYS */;

INSERT INTO `recipient_info` (`UID`, `recipientEmail`, `recipientID`, `recipientKey`, `version`, `vetted`)
VALUES
	(1,'jane@kaiser.com','17','Dr. Jane Kaiser',1,NULL),
	(2,'doc@jhu.edu','2','Dr. Jim Johnson',1,NULL),
	(3,'jeans@gmail.com','3','Dr. Jean Smith',1,NULL),
	(4,'ablass@jhu.edu','12','Dr. Adam Blass',1,NULL),
	(5,'dr.taylor@aero.org','23','Dr. John Taylor',1,NULL);

/*!40000 ALTER TABLE `recipient_info` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table staff_xref
# ------------------------------------------------------------

DROP TABLE IF EXISTS `staff_xref`;

CREATE TABLE `staff_xref` (
  `staffID` int(11) NOT NULL,
  PRIMARY KEY (`staffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `staff_xref` WRITE;
/*!40000 ALTER TABLE `staff_xref` DISABLE KEYS */;

INSERT INTO `staff_xref` (`staffID`)
VALUES
	(2),
	(3),
	(7),
	(12),
	(17),
	(23),
	(25),
	(26),
	(27),
	(99);

/*!40000 ALTER TABLE `staff_xref` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table topics
# ------------------------------------------------------------

DROP TABLE IF EXISTS `topics`;

CREATE TABLE `topics` (
  `topicID` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `parent` int(11) DEFAULT NULL,
  `notes` text,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`topicID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `topics` WRITE;
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;

INSERT INTO `topics` (`topicID`, `description`, `parent`, `notes`, `name`)
VALUES
	(0,'OVERALL',NULL,'Policy for the overall record','Overall'),
	(1,'ADOL',NULL,'Policy for handling information related to an adolescent, which will be afforded heightened confidentiality per applicable organizational or jurisdictional policy.  Description:  An enterprise may have a policy that requires that adolescent patient information be provided heightened confidentiality.  Information deemed sensitive typically includes health information and patient role information including patient status, demographics, next of kin, and location. Usage Notes:  For use within an enterprise in which an adolescent is the information subject.  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','adolescent information sensitivity'),
	(2,'CEL',NULL,'Policy for handling information related to a celebrity (people of public interest (VIP), which will be afforded heightened confidentiality.  Description:  Celebrities are people of public interest (VIP) about whose information an enterprise may have a policy that requires heightened confidentiality.  Information deemed sensitive may include health information and patient role information including patient status, demographics, next of kin, and location. Usage Notes:  For use within an enterprise in which the information subject is deemed a celebrity or very important person.  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','celebrity information sensitivity'),
	(3,'DIA',NULL,'Policy for handling information related to a diagnosis, health condition or health problem, which will be afforded heightened confidentiality.  Description:  Diagnostic, health condition or health problem related information may be deemed sensitive by organizational policy, and require heightened confidentiality. Usage Notes:  For use within an enterprise that provides heightened confidentiality to diagnostic, health condition or health problem related information deemed sensitive.   If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the  law rather than or in addition to this more generic code.','diagnosis information sensitivity'),
	(4,'DRGIS',NULL,'Policy for handling information related to a drug, which will be afforded heightened confidentiality. Description:  Drug information may be deemed sensitive by organizational policy, and require heightened confidentiality. Usage Notes:  For use within an enterprise that provides heightened confidentiality to drug information deemed sensitive.   If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','drug information sensitivity'),
	(5,'EMP',NULL,'Policy for handling information related to an employee, which will be afforded heightened confidentiality.  Description:  When a patient is an employee, an enterprise may have a policy that requires heightened confidentiality.  Information deemed sensitive typically includes health information and patient role information including patient status, demographics, next of kin, and location. Usage Notes:  For use within an enterprise that employs the information subject.  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','employee information sensitivity'),
	(6,'PRD',NULL,'Policy for handling information reported by the patient about another person, e.g., a family member, which will be afforded heightened confidentiality.  Description:  Sensitive information reported by the patient about another person, e.g., family members may be deemed sensitive by default.  The flag may be set or cleared on patient\'s request.  \nUsage Notes:  For sensitive information relayed by or about a patient, which is deemed sensitive within the enterprise (i.e., by default regardless of whether the patient requested that the information be deemed sensitive.)   If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','patient default sensitivity'),
	(7,'PRS',NULL,'Information deemed sensitive by the patient for which the patient requests and will be afforded heightened confidentiality.   Description:  Patient may deem patient role and health information sensitive for which the patient may request and receive heightened confidentiality.   Information deemed sensitive may include health information and patient role information including patient status, demographics, next of kin, and location.  For example, a patient may request that sensitive information is not to be shared with family members.  Typically, information reported by the patient about family members is sensitive by default.  Flag can be set or cleared on patient\'s request. Usage Notes:  For use within an enterprise that provides heightened confidentiality to certain types of information designated by a patient as sensitive.   If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code. For VA/SAMHSA pilot, this sensitivity code could be combined with HIPAASelfPay to indicate that the provider must obtain consent in order to disclose to the patient’s payer.','patient requested sensitivity'),
	(8,'MNTL',NULL,'Policy for handling alcohol or drug-abuse information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to alcohol or drug-abuse information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code  system, and specify the law rather than or in addition to this more generic code. For VA/SAMHSA pilot, this sensitivity code could be combined with 42CFRPart2 or Title38Section7332 to indicate that the originating provider must obtain consent in order to disclose.  Any recipient would need consent to redisclose.','substance abuse information sensitivity'),
	(9,'ETH',NULL,'Policy for handling alcohol or drug-abuse information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to alcohol or drug-abuse information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code  system, and specify the law rather than or in addition to this more generic code. For VA/SAMHSA pilot, this sensitivity code could be combined with 42CFRPart2 or Title38Section7332 to indicate that the originating provider must obtain consent in order to disclose.  Any recipient would need consent to redisclose.','substance abuse information sensitivity'),
	(10,'GDIS',NULL,'Policy for handling genetic disease information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to genetic disease information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','genetic disease information sensitivity'),
	(11,'HIV',NULL,'Policy for handling HIV or AIDS information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to HIV or AIDS information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code..','HIV/AIDS information sensitivity'),
	(12,'PSY',NULL,'Policy for handling psychiatry information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to psychiatry information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','psychiatry information sensitivity'),
	(13,'SDV',NULL,'Policy for handling sexual assault, abuse, or domestic violence information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to sexual assault, abuse, or domestic violence information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','sexual assault, abuse, or domestic violence information sensitivity'),
	(14,'SEX',NULL,'Policy for handling sexuality and reproductive health information, which will be afforded heightened confidentiality.  Description:  Information handling protocols based on organizational policies related to sexuality and reproductive health information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','sexuality and reproductive health information sensitivity'),
	(15,'SICKLE',NULL,'Policy for handling sickle cell disease information, which will be afforded heightened confidentiality.  Information handling protocols based on organizational policies related to sickle cell disease information that is deemed sensitive. Usage Notes: If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','sickle cell information sensitivity'),
	(16,'STD',NULL,'Definition:  Policy for handling sexually transmitted disease information, which will be afforded heightened confidentiality. Description:  Information handling protocols based on organizational policies related to sexually transmitted disease information that is deemed sensitive. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','sexually transmitted disease information sensitivity'),
	(17,'TBOO',NULL,'Definition:  Policy for handling information not to be initially disclosed or discussed with patient except by a physician assigned to patient in this case.  Description:  Information handling protocols based on organizational policies related to sensitive patient information that must be initially discussed with the patient by an attending physician before being disclosed to the patient.\nUsage Notes:  This is usually a temporary policy constraint only, and the sensitivity classification will likely change once the provider has discussed the information with the patient or other information subject.  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','taboo'),
	(18,'DEMO',NULL,'Policy for handling all demographic information about an information subject, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to all demographic about an information subject, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','all demographic information sensitivity'),
	(19,'DOB',NULL,'Policy for handling information related to an information subject’s date of birth, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s date of birth, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','date of birth information sensitivity'),
	(20,'GENDER',NULL,'Policy for handling information related to an information subject’s gender and sexual orientation, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s gender and sexual orientation, the disclosure of which could impact the privacy, well-being, or safety of that subject. Usage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','gender and sexual orientation information sensitivity'),
	(21,'LIVARG',NULL,'Policy for handling information related to an information subject’s living arrangement, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s living arrangement, the disclosure of which  could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','living arrangement information '),
	(22,'MARST',NULL,'Policy for handling information related to an information subject’s marital status, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s marital status, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:   If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition this more generic code.','marital status information sensitivity'),
	(23,'RACE',NULL,'Policy for handling information related to an information subject’s race, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s race, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code.','race information sensitivity '),
	(24,'REL',NULL,'Policy for handling information related to an information subject’s religious affiliation, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s religion, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','religion information sensitivity '),
	(25,'REL',NULL,'Policy for handling information related to an information subject’s religious affiliation, which will be afforded heightened confidentiality.  Description:  Policies may govern sensitivity of information related to an information subject’s religion, the disclosure of which could impact the privacy, well-being, or safety of that subject.\nUsage Notes:  If there is a jurisdictional mandate, then use the applicable ActPrivacyLaw code system, and specify the law rather than or in addition to this more generic code','religion information sensitivity ');

/*!40000 ALTER TABLE `topics` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usages`;

CREATE TABLE `usages` (
  `usageID` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`usageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `usages` WRITE;
/*!40000 ALTER TABLE `usages` DISABLE KEYS */;

INSERT INTO `usages` (`usageID`, `description`, `parent`)
VALUES
	(1,'TREAT',NULL),
	(2,'HRESCH',NULL),
	(3,'ETREAT',1),
	(4,'COVERAGE',NULL),
	(5,'PUBHLTH',NULL),
	(6,'HPAYMT',NULL),
	(7,'HOPERAT',NULL),
	(8,'PATRQT',NULL),
	(9,'HMARKT',NULL);

/*!40000 ALTER TABLE `usages` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
