//#1 BEGIN Load All Components in Table
function loadComponents(apiUrl, componentUrl, isAuthorized) {
  $.ajax({
    type: "GET",
    url: apiUrl,
    contentType: "text/plain",
    dataType: "json",
    success: function (data) {
      populateCategoryData(data, componentUrl, isAuthorized);
    },
    error: function (e) {
      console.log("There was an error with your request...");
    },
  });
}

function view(url, idComponent) {
  window.location.href = url + "/" + idComponent;
}

function populateCategoryData(data, componentUrl, isAuthorized) {
  var length = data.length;
  var delay = 0.1;
  for (var i = 0; i < length; ++i) {
    var category = data[i];
    var totalCategories = "";
    if (JSON.parse(isAuthorized) && category.total != category.totalPublished)
      totalCategories =
        '<p class="designation">Total components: <b>' +
        category.total +
        "</b></p>";

    $("#all-component-category-section").append(
      '<div class="col-12 col-sm-6 col-lg-3">' +
        "<div onclick=\"location.href='" +
        componentUrl +
        category.path +
        '\';" class="single_component_category_details wow fadeInUp" data-wow-delay= "' +
        delay +
        's"' +
        'style="cursor: pointer; visibility: visible; animation-delay: ' +
        delay +
        's; animation-name: fadeInUp;">' +
        "<!-- Component Category Thumb-->" +
        '<div class="component_category_thumb"><img ' +
        'src="' +
        category.image +
        '" ' +
        'alt="' +
        category.displayName +
        '">' +
        "<!-- Component Category Info-->" +
        "</div>" +
        "<!-- Component Category Details-->" +
        '<div class="single_component_category_details_info">' +
        '<div class="animation_test"></div>' +
        "<h6>" +
        category.displayName +
        "</h6>" +
        '<p class="designation">Components found: <b>' +
        category.totalPublished +
        "</b></p>" +
        totalCategories +
        "</div>" +
        "</div>" +
        "</div>"
    );
    delay = (delay * 10 + 0.1 * 10) / 10;
  }
}

//#1 END

//#2 BEGIN ADD and EDIT
function loadStateList() {
  var apiUrl = action.get("allStates");

  $.ajax({
    type: "GET",
    url: apiUrl,
    contentType: "text/plain",
    dataType: "json",
    success: function (data) {
      populateStateList(data);
    },
    error: function (e) {
      console.log("There was an error with your request...");
      console.log("error: " + JSON.stringify(e));
    },
  });
}

function populateStateList(data) {
  var select = document.getElementById("states");
  var length = data.length;
  select.innerHTML = "";

  for (var i = 0; i < length; i++) {
    var state = data[i];
    var option = document.createElement("option");
    option.text = state.displayName;
    option.value = state.id;
    select.add(option, 0);
  }

  saveState(select);
}

function loadCategoryFields(componentCategory) {
  loadStateList();
  createComponentFields(componentCategory, "0");
  var actionButton = document.getElementById("submit-button");
  actionButton.innerHTML = "<i class='fa fa-save'></i> Add";
  actionButton.setAttribute(
    "onclick",
    "addComponent(this, '" + componentCategory + "')"
  );
  document.getElementById("titleComponent").innerHTML =
    "Add New " + categoryDisplayName;
}

function loadComponentFields(componentCategory, id) {
  loadStateList();
  // performs the function createComponentFields only when all component fields are loaded
  $.when(
    componentFields(componentCategory).done(function () {
      createComponentFields(componentCategory, id);
      var actionButton = document.getElementById("submit-button");
      actionButton.innerHTML = "<i class='fa fa-save'></i> Edit";
      actionButton.setAttribute(
        "onclick",
        "editComponent(this, '" + componentCategory + "', '" + id + "')"
      );
    })
  );
}

var dataCompKeys = [];

/**
 * Get all the component fields of the component category
 */
function componentFields(componentCategory) {
  var apiUrl = action.get("componentCategory") + "/" + componentCategory;
  return $.ajax({
    url: apiUrl,
    type: "GET",
    dataType: "json",
    success: function (data) {
      dataCompKeys = Object.keys(data);
      console.log("componentFields success");
    },
    error: function (e) {
      console.log("componentFields error");
      console.log("error: " + JSON.stringify(e));
    },
  });
}

/**
 * Loads all values into the fields
 */
function createComponentFields(componentCategory, id) {
  document.getElementById("component-image").value = "";
  var idComponent = "";
  if (id !== "0") {
    idComponent = "/" + id;
    console.log(dataCompKeys.length);
  }

  var apiUrl =
    action.get("componentCategory") + "/" + componentCategory + idComponent;

  $.ajax({
    url: apiUrl,
    type: "GET",
    dataType: "json",
    success: function (data) {
      populateComponentData(data, id, idComponent);
    },
  });
}

function populateComponentData(data, id) {
  var columns = Object.keys(data);
  var values = Object.values(data);
  document.getElementById("component-category-fields").innerHTML = "";

  //variables of component
  var lengthC = columns.length;
  var columnsC = columns;
  if (id !== "0") {
    columnsC = dataCompKeys;
    lengthC = columnsC.length;
  }
  var counter = 0; //the fields which must be required

  $("#single-component-image-preview").attr(
    "src",
    values[columns.indexOf("image")]
  );

  for (var i = 2; i < lengthC - 3; i++) {
    var fieldName = columns[i];
    var fieldValue = values[i];
    if (id === "0") fieldValue = "";
    else {
      fieldName = columnsC[i];
      fieldValue = values[columns.indexOf(fieldName)];
      if (fieldValue === undefined || fieldValue === null) fieldValue = "";
      console.log(fieldName + " : " + fieldValue);
    }

    if (fieldName === null) continue;
    var fieldInputString = "";
    var fieldLabelString = "";
    var fieldDivString = "";
    var r = ""; //required field
    var s = ""; //required field graphical

    if (counter < 3) {
      r = "required";
      s = "*";
    }
    // Creating the input field
    fieldInputString +=
      "<input type='text' class='form-control' id='" +
      fieldName +
      "' name='" +
      fieldName +
      "' value='" +
      fieldValue +
      "' placeholder=' ' " +
      r +
      "/>";

    // Creating the label
    fieldLabelString +=
      '<label for="' +
      fieldName +
      '" class="control-label">' +
      fieldName +
      s +
      ":</label>";
    // Creating the div container for the input field and label
    fieldDivString =
      "</div>" +
      '<div class="row"><div class="col-md-12">' +
      '<div class = "form-floating">' +
      fieldInputString +
      fieldLabelString +
      "</div></div></div><div style='height:10px;'>";

    $("#component-category-fields").append(fieldDivString);
    counter++;
  }
  $("#single-dialog-component").modal("show");
}

function addComponent(button, componentCategory) {
  if (!checkRequiredFields()) return;
  var urlPage = action.get("addNewComponent") + "/" + componentCategory;

  $.when(
    actionComponent(urlPage, button).done(function (check) {
      if (JSON.parse(check) != true) {
        setSimpleNotification(
          false,
          "Error while adding component. Please try again later!"
        );
      } else {
        setSimpleNotification(true, "Component added successfully!");
        var table = $("#componentsTable").DataTable();
        table.ajax.reload();
      }
      button.disabled = false;
    })
  );
}

function editComponent(button, componentCategory, id) {
  if (!checkRequiredFields()) return;

  var urlPage =
    action.get("editComponent") + "/" + componentCategory + "/" + id;

  $.when(
    actionComponent(urlPage, button).done(function (check) {
      if (JSON.parse(check) != true) {
        setSimpleNotification(
          false,
          "Component modification error. Try again later!"
        );
      } else {
        setSimpleNotification(true, "Component updated successfully!");
        var table = $("#componentsTable").DataTable();
        table.ajax.reload();
      }
      button.disabled = false;
    })
  );
}

function actionComponent(urlPage, button) {
  var form = $("#data-form-component")[0];
  var datas = new FormData(form);
  button.disabled = true;
  var image = document
    .getElementById("single-component-image-preview")
    .getAttribute("src"); //ora ho corretto
  datas.append("image", image);
  return $.ajax({
    type: "POST",
    enctype: "multipart/form-data",
    url: urlPage,
    data: datas,
    processData: false,
    contentType: false,
    cache: false,
    timeout: 600000,
    success: function (data) {
      document.getElementById("cancel-component-btn").click();
    },
    error: function (e) {},
  });
}

function checkRequiredFields() {
  //check if all the required fields are filled into the form
  var form = document.getElementById("component-category-fields");
  var inputs = form.getElementsByTagName("input");
  var length = inputs.length;
  var required = 0;
  for (var i = 0; i < length; i++) {
    var input = inputs[i];
    if (input.required && input.value === "") required++;
  }
  console.log(required);
  if (required > 0) {
    setSimpleNotification(false, "Fill in all the mandatory fields!");
    return false;
  }
  return true;
}

function saveState(stateContainer) {
  const newState = stateContainer.options[stateContainer.selectedIndex].value;
  document.getElementById("idState").value = newState;
}

function editComponentPopUp(componentCategory, id) {
  document.getElementById("titleComponent").innerHTML =
    "Edit " + categoryDisplayName;
  loadComponentFields(componentCategory, id);
}

//#2 END ADD

function setGeniusRole() {
  var role = document.getElementById("states");
  role.style.display = "none";
  document.getElementById("idState").value = "3";
}
