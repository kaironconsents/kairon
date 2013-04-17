var DS4P = DS4P || {}
DS4P.Data = {}

patient = {
  "usages": [
    {
      "id": "3", 
      "name": "ETREAT", 
      "providers": [], 
      "topics": [
        {
          "id": "0", 
          "level": "1", 
          "name": "OVERALL"
        }
      ]
    }, 
    {
      "id": "2", 
      "name": "HRESCH", 
      "providers": [], 
      "topics": [
        {
          "id": "0", 
          "level": "3", 
          "name": "OVERALL"
        }
      ]
    }, 
    {
      "id": "1", 
      "name": "TREAT", 
      "providers": [
        {
          "id": "12", 
          "topics": [
            {
              "id": "0", 
              "level": "2", 
              "name": "OVERALL", 
              "start": "2012-01-12 00:00:00.0"
            }
          ]
        }
      ], 
      "topics": [
        {
          "end": "2013-02-28 00:00:00.0", 
          "id": "14", 
          "level": "4", 
          "name": "SEX", 
          "start": "2013-02-15 00:00:00.0"
        }, 
        {
          "end": "2070-02-18 00:00:00.0", 
          "id": "12", 
          "level": "2", 
          "name": "PSY", 
          "start": "1970-02-12 00:00:00.0"
        }, 
        {
          "id": "10", 
          "level": "2", 
          "name": "GDIS"
        }, 
        {
          "end": "2013-02-14 00:00:00.0", 
          "id": "0", 
          "level": "3", 
          "name": "OVERALL", 
          "start": "2012-02-11 00:00:00.0"
        }
      ]
    }
  ]
}


DS4P.Data.Recipients = {}

DS4P.Data.Recipients = {
  "Recipients": [
    {
      "email": "sam@sgc.mil.gov", 
      "id": "17",
      "key": "Dr. Samantha Carter"
    },
    {
      "email": "carolyn@sgc.mil.gov", 
      "id": "12", 
      "key": "Dr. Carolyn Lam"
    }, 
    {
      "email": "janet@sgc.mil.gov", 
      "id": "2", 
      "key": "Dr. Janet Fraiser"
    }, 
  ]
}["Recipients"];

DS4P.Data.Topics = [{name: "General", description: "OVERALL", id: 0},
          {name: "Substance Abuse", description: "MNTL", id: 9}, 
          {name: "Sickle Cell", description: "SICKLE", id: 15}, 
          {name: "Sexuality & Reproductive Health", description: "SEX", id: 14}, 
          {name: "Sexual Assault, Abuse or Domestic Violence", description: "SDV", id: 13},
          {name: "Genetic Disease", description: "GDIS", id: 10},
          {name: "Mental Health", description: "PSY", id: 12}]