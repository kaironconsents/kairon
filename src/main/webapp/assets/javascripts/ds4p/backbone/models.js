var DS4P = DS4P || {}

DS4P.Usage = Backbone.Model.extend({
  initialize: function(usage) {
    this.set("topics", new DS4P.Topics(usage.topics));
    this.set("providers", new DS4P.Providers(usage.providers));
  },
  toJSON: function() {
    return {id: this.get('id'), name: this.get('name'), topics: this.get("topics").toJSON(), providers: this.get("providers").toJSON()}
  }
});

DS4P.Usages = Backbone.Collection.extend({model: DS4P.Usage})

DS4P.Topic = Backbone.Model.extend({
  defaults: {level: 1},
  name: function() {
    var topic = _.find(DS4P.Data.Topics, function(topic) { return topic.description == this.get('name') }, this)
    return topic == null ? null : topic.name
  },

  toJSON: function() {
    return {id: this.get('id'), name: this.get('name'), level: this.get('level'), start: this.get("start"), end: this.get('end')}
  }
})

DS4P.Topics = Backbone.Collection.extend({model: DS4P.Topic})

DS4P.Provider = Backbone.Model.extend({
  defaults: {topics: [{id: "0", name: "OVERALL", level: 1}]},
  name: function() {
  provider =  _.find(DS4P.Data.Recipients, function(recipient) { 
      return recipient.id == this.id;
  }, this);
  return provider == null ? null : provider.key;
  },
  topic: function() {
    return this.get('topics')[0]
  },

  setTopic: function(topic) {
    this.set("topics", [topic]);
  },
  toJSON: function() {
    return {id: this.get('id'), topics: this.get('topics')}
  }
})

DS4P.Providers = Backbone.Collection.extend({model: DS4P.Provider})
