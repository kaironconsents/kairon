$(document).ready(function() {
  $('#navigation a').click(function (e) {
    e.preventDefault();

    $("#navigation .nav-tab").removeClass("active");
    $(this).closest(".nav-tab").addClass("active");
    
    $(this).tab('show');
  })

  $("#usages").html(new DS4P.UsagesView({collection: new DS4P.Usages(patient['usages'])}).$el);
  $("#TREAT").addClass("active in");

  addToolTips();
});