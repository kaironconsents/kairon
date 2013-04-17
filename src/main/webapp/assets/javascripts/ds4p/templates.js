JST = {}

JST['usage'] = _.template('\
  <div class="general row-fluid">\
    <div class="span5 offset3 column share-column">\
      <h3>Ability to share</h3>\
    </div>\
    <div class="span4 column date-column">\
      <h3>Date range</h3>\
    </div>\
  </div>\
  <div class="choice row-fluid">\
    <div class="span3">\
      <h3>Choices</h3>\
    </div>\
    <div class="span5 column share-column">\
      <h3>Ability to share</h3>\
    </div>\
    <div class="span4 column date-column">\
      <h3>Date range</h3>\
    </div>\
  </div>\
  <div class="add-row row-fluid">\
    <a class="add-choice add-consent" href="#">\
      <div href="#" class="btn btn-info">+</div>\
      Add\
    </a>\
  </div>\
  <div class="providers">\
    <h3>Providers</h3>\
  </div>\
  <div class="add-row row-fluid">\
    <a class="add-provider add-consent" href="#">\
      <div href="#" class="btn btn-info">+</div>\
      Add\
    </a>\
  </div>\
  <button class="btn btn-info btn-large done pull-right">Done</button>');

JST['removeButton'] = _.template("<a href='#' class='pull-right btn-small remove'></a>")

JST['consent'] = _.template('\
  <div class="span3 column name-column">\
    <input name="id" value="<%= id %>" type="hidden">\
    <% if (id != null)  { %>\
      <h2><%= name %></h2>\
    <% } %>\
  </div>\
  <div class="span5 column share-column bordered">\
    <div class="share-widget">\
      <div class="protect"></div>\
      <div class="slider-container">\
        <div class="slider"></div>\
        <input name="level" value="<%= level %>" type="hidden">\
      </div>\
      <div class="share"></div>\
    </div>\
  </div>\
  <div class="span4 column date-column">\
    <div class="date-range" class="row-fluid">\
        <div class="span5 input-append">\
          <input name="start" placeholder="Start Date" value="<%= startDate %>" class="span9 start-date date" type="text">\
          <span class="add-on"><i class="icon-calendar"></i></span>\
        </div>\
        <div class="span5 input-append end-date">\
          <input name="end" placeholder="End Date" value="<%= endDate %>" class="span9 end-date date" type="text">\
          <span class="add-on"><i class="icon-calendar"></i></span>\
        </div>\
    </div>');