var DS4P = DS4P || {}

var sliderize = function(scope, level) {
  scope.find(".date-column h3").tooltip({title: "Establish the date range for when you would like your choices to be in effect", delay: 750});

  scope.find(".date").calendricalDate({
    usa: true
  });
  
  // Make the calendar icon show focus along with the actual text input
  scope.find(".start-date, .end-date").on("focus", function(e) {
    $(e.target).siblings(".add-on").addClass("active");
  });
  scope.find(".start-date, .end-date").on("blur", function(e) {
    $(e.target).siblings(".add-on").removeClass("active");
  });
  scope.find(".add-on i").on("click", function(e) {
    $(e.target).parent().siblings(".start-date, .end-date").focus();
  });

  scope.find(".slider").slider({
    value: level,
    min: 1,
    max: 4,
    step: 1,
    start: function(event, ui ) {
      $(ui.handle).tooltip("destroy")
    },
    stop: function( event, ui ) {
      switch (ui.value) {
      case 1:
        title = "Restricted";
        verbose = "Allow no access to the information. Note: your health data will not be available to providers that have a treatment relationship with you";
        provider = "Provider does not need my full medical information to treat me effectively. If the provider may prescribe medications, consider selecting another option that allows sharing of medication history and allergies";
        break;
      case 2:
        title = "Mostly Restricted";
        verbose = "Share information with low risk to privacy, such as allergies and immunizations";
        provider = "The provider's decisions are less important to your overall health and you are less comfortable with sharing; for example: an ancillary provider such as a dentist";
        break;
      case 3:
        title = "Mostly Shared";
        verbose = "Extend information shared to include medications and procedures";
        provider = "Provider needs most of your medical information and you are comfortable with sharing. For example: a provider you have been referred to by your Primary Care Provider";
        break;
      case 4:
        title = "Shared";
        verbose = "Allow full access to information";
        provider = "All information will be shared with the provider. For example: your Primary Care Provider or a specialist that you see regularly for a chronic condition";
        break;
      }
      $(ui.handle).parent().siblings("input").val(ui.value).change();
      $(ui.handle).tooltip({title: title});
      $(ui.handle).tooltip('show');

      if ($(ui.handle).closest(".row-fluid").hasClass("provider"))
        verbose = provider;

      // Update tooltips to more verbose description
      var toggleVerbosity = function() {
        var timeoutID = 0;
        timeoutID = setTimeout(function() {
          $(ui.handle).attr('title', verbose).tooltip('fixTitle').tooltip('show');
        }, 1000);
        $(ui.handle).on("mouseleave", function() {
          clearTimeout(timeoutID);
          $(ui.handle).attr('title', title).tooltip('fixTitle');
        });
      };
      
      toggleVerbosity();
      $(ui.handle).on("mouseover", function() { toggleVerbosity(); });
    }
  });
};

DS4P.UsageView = Backbone.View.extend({
  initialize: function() {
    _.bindAll(this, 'render', 'addTopic', 'appendTopic', 'addProvider', 'appendProvider');
    
    this.model.get('topics').bind("add", this.appendTopic)
    this.model.get('providers').bind("add", this.appendProvider)
    this.render();
  },
  className: "tab-pane fade",

  events: {
    "click .add-choice": "addTopic",
    "click .add-provider": "addProvider"
  },

  selectableProviders: function() {
    return this.selectable(DS4P.Data.Recipients, this.model.get("providers"))
  },

  selectableTopics: function() {
    return this.selectable(DS4P.Data.Topics, this.model.get("topics"))
  },

  selectable: function(masterList, selected) {
    var ids = selected.pluck("id");
    return _.reject(masterList, function(consent) { return _.contains(ids, consent.id.toString()) });
  },

  addProvider: function() {
    this.model.get('providers').add(new DS4P.Provider());
    return false;
  },

  appendProvider: function(provider) {
    this.$el.children(".providers").append(new DS4P.ProviderView({model: provider, selectableProviders: this.selectableProviders()}).$el)
  },

  addTopic: function() {
    var topic = this.selectableTopics()[0]
    if (topic != null)
      this.model.get('topics').add(new DS4P.Topic({id: topic.id.toString(), name: topic.description, changeable: true}));
    return false;
  },

  appendTopic: function(topic) {
    var parentElement = topic.get('id') == "0" ? ".general" : ".choice"
    var topicView = new DS4P.TopicView({model: topic, selectableTopics: this.selectableTopics()});
    this.$el.children(parentElement).append(topicView.$el)
  },

  render: function() {
    this.$el.html(JST['usage']())

    this.model.get('topics').each(function(topic) { this.appendTopic(topic) }, this);
    this.model.get('providers').each(function(provider) { this.appendProvider(provider)}, this);

    this
  }});

DS4P.UsagesView = Backbone.View.extend({
  initialize: function() {
    _.bindAll(this, "render", "save")
    this.render();
  },
  events: {"click .done": "save"},
  save: function() {
    // TEST
    // console.log(JSON.stringify({usages: this.collection.toJSON()}))
    var activeTab = $(".tab-pane.active").attr("id")
    var that = this;
    $.ajax({type: "PUT", url: DS4P.Data.PatientUrl, data: JSON.stringify({"usages": this.collection.toJSON()}), contentType: "application/json", dataType: "json"}).done(function(patient) {
      that.collection = new DS4P.Usages(patient['usages'])
      that.$el.empty();
      that.render();
      $("#" + activeTab).addClass("active in");
    });
  },
  id: "myTabContent",
  className: "row-fluid tab-content",
  render: function() {
    this.collection.each(function(usage) {
      this.$el.append(new DS4P.UsageView({id: usage.get('name'), model: usage, collections: this.providers}).$el)
    }, this);

    return this;
  }
})

DS4P.ProviderView = Backbone.View.extend({
  initialize: function(options) {
    this.selectableProviders = options.selectableProviders;
    this.render();
  },
  events: {"change input.provider-type-ahead": "updateProviderId", 
           "change input:not(.provider-type-ahead)": "updateTopic",
            "click a.remove": "delete"},
  'delete': function(e) {
    this.model.collection.remove(this.model)
    this.remove()
    return false;
  },
  updateTopic: function(e) {
    var topic = this.model.topic()
    topic[e.target.name] = $(e.target).val();
    this.model.setTopic(topic)
  },
  updateProviderId: function(e) {
    provider = _.find(DS4P.Data.Recipients, function(provider) { return provider.key == $(e.target).val() }, this)
    this.model.set("id", provider.id)
    $(e.target).siblings("input").val(provider.id);
  },
  className: "provider row-fluid",
  render: function() {
    var topic = this.model.topic();
    this.$el.html(JST['consent']({id: this.model.get("id"), name: this.model.name(), level: topic.level, startDate: topic.start, endDate: topic.end}));
    var name = this.$el.children(".name-column")
    if (this.model.isNew()) {
      name.append("<input type='text' name='name' class='provider-type-ahead' placeholder='Provider' />")
      name.children("input[type='text']").typeahead({source: _.map(this.selectableProviders, function(recipient) { return recipient.key })}, this);
    }

    name.prepend(JST['removeButton']())
    sliderize(this.$el, topic.level);
    return this;
  }
});

DS4P.TopicView = Backbone.View.extend({
  initialize: function(options) {
    this.selectableTopics = options.selectableTopics
    this.render();
  },
  events: {"change :input": "updateValue",
            "click a.remove": "delete"},
  className: "consent row-fluid",
  'delete': function(e) {
    this.model.collection.remove(this.model)
    this.remove()
    return false;
  },
  updateValue: function(e) {
    this.model.set(e.target.name, $(e.target).val())
  },
  render: function() {
    this.$el.html(JST['consent']({id: this.model.get("id"), name: this.model.name(), level: this.model.get("level"), startDate: this.model.get('start'), endDate: this.model.get("end")}));
    var name = this.$el.children(".name-column")
    if (this.model.get('changeable')) {
      var select = "<select name='id'>"
      select += "<option selected value='" + this.model.get('id') + "' label='" + this.model.name() + "' />"
      _.each(this.selectableTopics, function(topic) {
        if (topic.id != "0") {
          select += "<option value='" + topic.id + "' label='" + topic.name + "' "
          select += " />" 
        }
      }, this);
      select += "</select>";
      name.html(select)
    }

    if (this.model.get("id") != 0) {
      name.prepend(JST['removeButton']())
    } else {
      name.children("h2").tooltip({title: "Establish the default setting for sharing your health data", delay: 750});
      name.children("h3").remove();
    }

    
    sliderize(this.$el, this.model.get("level"));
    return this;
  }
});