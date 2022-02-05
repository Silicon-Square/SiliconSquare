//#1 BEGIN ADD

function loadCategoryFields(componentCategory) {
    createComponentFields(componentCategory);
    $("#addComponent").modal("show");
}

function createComponentFields(componentCategory) {
    var apiUrl = action.get("componentCategory") + "/" + componentCategory;

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function (data) {
            console.log("data: " + data);
            var length = data.length;
            var fields = document.getElementById("component-category-fields");
            fields.innerHTML = "";
            for (var i = 0; i < length; i++) {
                var field = data[i];
                var fieldName = field.name;
                var fieldValue = field.value;

                var fieldInputString = "";
                var fieldLabelString = "";
                if (fieldLabel !== null) {
                    fieldLabelString.append('<div style="height:10px;"></div>');
                    fieldLabelString.append('<div class="row"><div class="col-md-2">');
                    fieldLabelString.append(
                        '<label for="' +
                        fieldName +
                        '" class="control-label" style="position:relative; top:7px;">' +
                        fieldName +
                        ":</label></div>"
                    );
                    fieldInputString.append(
                        "<input type='text' class='form-control' id='" +
                        fieldName +
                        "' name='" +
                        fieldName +
                        "' value='" +
                        fieldValue +
                        "'>"
                    );
                    fieldInputString.append("</div></div>");
                }
            }
        },
    });
}

//#1 END

//#2 BEGIN EDIT
var idComponent = null;

function edit(componentId) {
    loadData(componentId);
    $("#edit").modal("show");
}

function loadData(componentId) {
    idComponent = componentId;
    loadStateList();
    loadComponent(componentId);
}

function loadComponent(componentId) {
    var apiUrl = action.get("editComponent") + "/" + componentId;

    $.ajax({
        type: "GET",
        url: apiUrl,
        contentType: "text/plain",
        dataType: "json",
        success: function (data) {
            populateData(data);
        },
        error: function (e) {
            setNotification(false, "Errore nel caricamento dei dati del componente");
            console.log("There was an error with your request...");
            console.log("error: " + JSON.stringify(e));
        },
    });
}

function loadStateList() {
    var apiUrl = action.get("allStates");

    $.ajax({
        type: "GET",
        url: apiUrl,
        contentType: "text/plain",
        dataType: "json",
        success: function (data) {
            populateRoleList(data);
        },
        error: function (e) {
            console.log("There was an error with your request...");
            console.log("error: " + JSON.stringify(e));
        },
    });
}

function populateRoleList(data) {
    var select = document.getElementById("roles");
    var length = data.length;

    select.innerHTML = "";

    for (var i = 0; i < length; i++) {
        var role = data[i];
        var option = document.createElement("option");
        option.text = role.roleName;
        option.value = role.idRole;
        select.add(option, 0);
    }
}

// populate the data table with JSON data
function populateData(data) {
    //	const obj = JSON.parse(data);
    console.log("populating data...");
    // clear the table before populating it with more data

    idUser = data.idUser;
    var avatar = data.avatar;
    var email = data.email;
    var name = data.name;
    var surname = data.surname;
    var username = data.username;
    var role = data.role;

    console.log("data: " + avatar + email + name + surname);

    $("#avatar-preview").attr("src", avatar);
    $("#email").attr("value", email);
    $("#name").attr("value", name);
    $("#surname").attr("value", surname);
    $("#username").attr("value", username);
    document.getElementById("roles").value = role.idRole;
    document.getElementById("idRole").value = role.idRole;
}
