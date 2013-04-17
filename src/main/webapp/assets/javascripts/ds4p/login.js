$(document).ready(function() {
  $.getJSON("rest/patients", function(data) {
	  var options = _.reduce(data["Patients"], function(options, patient) {
		  return options + "<option value='" + patient.id+ "'>" + patient.key + "</option>";
	  }, "");
	  $("select").html(options);
	  $("select").trigger("change");
  });
	
  $("select").on("change", function() {
	 $("#user-select-button").attr("href", "manager.html?patient=" + $(this).val());
  });
});