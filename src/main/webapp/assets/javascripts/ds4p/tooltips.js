function addToolTips() {
  var treatment = "Set your preferences for sharing your health data with health care providers";
  var emergency = "Set your preferences for when use of your healthcare information is required to immediately treat a condition that is urgent and critical";
  var research = "Set your preferences for when use of your healthcare information is to conduct scientific investigations to obtain healthcare knowledge";

  $("#treat-nav").tooltip({title: treatment, delay: 750});
  $("#etreat-nav").tooltip({title: emergency, delay: 750});
  $("#hresch-nav").tooltip({title: research, delay: 750});
}