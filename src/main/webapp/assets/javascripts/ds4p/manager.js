$(document).ready(function() {
  $('#navigation a').click(function (e) {
    e.preventDefault();

    $("#navigation .nav-tab").removeClass("active");
    $(this).closest(".nav-tab").addClass("active");
    
    $(this).tab('show');
  })

  $.getJSON(DS4P.Data.PatientUrl, function(patient) { 
    usages = new DS4P.Usages(patient['usages'])
    $("#usages").html(new DS4P.UsagesView({collection: usages}).$el);
    $("#TREAT").addClass("active in");
  });

  addToolTips();
});