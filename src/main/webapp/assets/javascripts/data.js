var DS4P = DS4P || {}
DS4P.Data = {}

DS4P.Data['PatientUrl'] = "rest/sharePolicy/"  + $.url().param("patient")

DS4P.Data.Recipients = {}

$.getJSON("rest/recipients", function(recipients) {
  DS4P.Data.Recipients = recipients["Recipients"];
});


DS4P.Data.Topics = [{name: "General", description: "OVERALL", id: 0},
          {name: "Substance Abuse", description: "ETH", id: 9}, 
          {name: "Sickle Cell", description: "SICKLE", id: 15}, 
          {name: "Sexuality & Reproductive Health", description: "SEX", id: 14}, 
          {name: "Sexual Assault, Abuse or Domestic Violence", description: "SDV", id: 13},
          {name: "Genetic Disease", description: "GDIS", id: 10},
          {name: "Mental Health", description: "PSY", id: 12}]