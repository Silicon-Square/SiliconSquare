var action = null;

function loadActionList() {
  var apiUrl = "/allActions";

  $.ajax({
    type: "GET",
    url: apiUrl,
    contentType: "text/plain",
    dataType: "json",
    success: function (data) {
      action = new Map();
      for (var i = 0; i < data.length; i++) {
        var actionName = data[i].name;
        var actionUrl = data[i].path;
        action.set(actionName, actionUrl);
      }
      return action;
    },
    error: function (e) {
      console.log("There was an error with your request...");
      console.log("error: " + JSON.stringify(e));
    },
  });
}

function setSimpleNotification(success, text) {
  if (success) Swal.fire("Success!", text, "success");
  else Swal.fire("Error!", text, "warning");
}

// vv pop up vv
function showPopUp(text, type) {
  Swal.fire({
    position: "top-end",
    icon: type,
    title: text,
    showConfirmButton: false,
    timer: 2000,
  });
}
// ^^ pop up ^^
