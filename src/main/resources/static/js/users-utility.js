var idUser = null;

function editUserPopUp(user) {
  $.when(loadDataWithoutRoles(user)).done(function () {
    document.getElementById("roleRow").style.display = "none";
    $("#edit").modal("show");
  });

  // edit(user);
}

function edit(user) {
  $.when(loadData(user)).done(function () {
    document.getElementById("roleRow").style.display = "flex";
    $("#edit").modal("show");
  });
}

function loadDataWithoutRoles(user) {
  idUser = user;
  loadUser(user);
}

function loadData(user) {
  idUser = user;
  return $.when(
    loadRoleList().done(function () {
      loadUser(user);
    })
  );
}

function loadUser(user) {
  var apiUrl = action.get("edit") + "/" + user;

  $.ajax({
    type: "GET",
    url: apiUrl,
    contentType: "text/plain",
    dataType: "json",
    success: function (data) {
      populateData(data);
    },
    error: function (e) {
      setNotification(false, "Error loading user data");
      console.log("There was an error with your request...");
      console.log("error: " + JSON.stringify(e));
    },
  });
}

function loadRoleList() {
  var apiUrl = action.get("allRoles");

  return $.ajax({
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

  document.getElementById("name").value =name;
  document.getElementById("surname").value = surname;
  document.getElementById("username").value = username;
  document.getElementById("email").value = email;
  document.getElementById("avatar-preview").src = avatar;
  document.getElementById("avatar").value = null;

  //reset avatar
  //document.getElementById("avatar").value = "";

//onclick="document.urfrom.reset()"
  // $("#avatar-preview").attr("src", avatar);
  // $("#email").attr("value", email);
  // $("#name").attr("value", name);
  // $("#surname").attr("value", surname);
  // $("#username").attr("value", username);
  document.getElementById("roles").value = role.idRole;
  document.getElementById("idRole").value = role.idRole;
}

function saveRole(roleContainer) {
  const newRole = roleContainer.options[roleContainer.selectedIndex].value;
  document.getElementById("idRole").value = newRole;
}

function generatePassword() {
  var path = action.get("sendNewPassword") + "/" + idUser;
  $.ajax({
    type: "POST",
    url: path,
    contentType: "application/json",
    dataType: "json",
    success: function (data) {
      if (JSON.parse(data) == false) this.error();
      else
        Swal.fire(
          "Password Changed!",
          "A new password has been sent to the user's email address.",
          "success"
        );
    },
    error: function (e) {
      Swal.fire({
        title: "Error!",
        text: "The password could not be changed!",
        icon: "warning",
        showCancelButton: false,
        confirmButtonColor: "#3085d6",
        confirmButtonText: "OK!",
      }).then((result) => {
        if (result.isConfirmed) {
          Swal.fire(title, description, "warning");
        }
      });
    },
  });
}

function editUser(button) {
  var urlPage = action.get("update") + "/" + idUser;

  var form = $("#data-form-user")[0];
  var datas = new FormData(form);
  button.disabled = true;
  console.log("url: " + urlPage);
  $.ajax({
    type: "POST",
    enctype: "multipart/form-data",
    url: urlPage,
    data: datas,
    processData: false,
    contentType: false,
    cache: false,
    timeout: 600000,
    success: function (data) {
      button.disabled = false;
      document.getElementById("cancel-user-btn").click();
      if (JSON.parse(data) == false) {
        this.error();
      } else {
        $("#avatar").val("");
        setSimpleNotification(true, "User edited successfully!");
        var table = $("#userTable").DataTable();
        table.ajax.reload();
      }
    },
    error: function (e) {
      setSimpleNotification(
        false,
        "Error editing user. Please try again later!"
      );
      console.log("ERROR : ", e.responseText);
      button.disabled = false;
    },
  });
}

function insertImage(avatarPath, userName, userSurname) {
  return (
    "<img src='" +
    avatarPath +
    "' alt='" +
    userName +
    " " +
    userSurname +
    "avatar'>"
  );
}

function insertRole(roleName, roleColor) {
  return (
    '<span class="role" style="background:' +
    roleColor +
    '">' +
    roleName +
    "</span>"
  );
}

function insertActions(usr) {
  return (
    '<button class="item" data-bs-toggle="tooltip" data-bs-placement="top" title="Edit" onclick="edit(' +
    usr.idUser +
    ')"><i class="fas fa-pen"></i></button>'
  );
}

const tooltipTriggerList = [].slice.call(
  document.querySelectorAll('[data-bs-toggle="tooltip"]')
);
const tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl);
});
