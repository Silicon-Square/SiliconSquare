<%@ page import="it.siliconsquare.model.administration.User" %>
<%@ page
        import="it.siliconsquare.common.redirect.Action" %>
<%@ page
        contentType="text/html;charset=UTF-8" language="java" %>
        <%@ page import="it.siliconsquare.common.Security" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
          
<!DOCTYPE html>
<html lang="it">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <%-- DataTables --%>
    <link
            rel="stylesheet"
            href="https://cdn.datatables.net/select/1.3.3/css/select.dataTables.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"
    />
    <link
            rel="stylesheet"
            href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css"
    />

<%@ include file="/WEB-INF/jsp/header.jsp" %>
    <script
            type="text/javascript"
            src="https://code.jquery.com/jquery-3.5.1.js"
    ></script>

    <script
            type="text/javascript"
            src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"
    ></script>
    <script
            type="text/javascript"
            src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"
    ></script>
    <script
            type="text/javascript"
            src="https://cdn.datatables.net/select/1.3.3/js/dataTables.select.min.js"
    ></script>

    <%--
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"
    />
    --%>

    <!-- Buttons For Datatable --->
    <link
            rel="stylesheet"
            href="https://cdn.datatables.net/buttons/2.1.0/css/buttons.dataTables.min.css"
    />
    <script
            type="text/javascript"
            src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"
    ></script>
    <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"
    ></script>
    <script
            type="text/javascript"
            src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"
    ></script>
    <script
            type="text/javascript"
            src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"
    ></script>
    <link
            rel="shortcut icon"
            href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2"
            type="image/x-icon"
    />
</head>
<body>

<script type="text/javascript"> 

    $(document).ready(function () {

        loadActionList();
        var table = $("#userTable").DataTable({
            scrollX:false,
            ajax: {
                url: "<%=Action.ALL_USERS.value%>",
                dataSrc: "",
            },
            columns: [
                {
                    data: "avatar",
                    title: "",
                    orderable: false,
                    searchable: false,
                    render: function (data, type, row) {
                        return '<img src="' + data + '">';
                    },
                    responsivePriority: 1
                },
                {data: "email", title: "Email", responsivePriority: 1},
                {data: "name", title: "Name", responsivePriority: 2},
                {data: "surname", title: "Surname", responsivePriority:2},
                {
                    data: "username",
                    title: "Username", responsivePriority:2
                },
                {
                    title: "Role",
                    data: "role", responsivePriority:2,
                    render: function (data, type, row) {
                        return (
                            '<span class="role" style="background:' +
                            data.color +
                            '">' +
                            data.roleName +
                            "</span>"
                        );
                    },
                },
                {
                    data: {name: "Name", role: "role"}, 
                    title: "Actions",
                   // data: "idUser",
                    orderable: false,
                    searchable: false, responsivePriority:1,
                    render: function (data, type, row) {
                      
                        var masterIdRole = '<%=((User)request.getSession().getAttribute("user")).getIdRole()%>';
                        var slaveIdRole = data.role.idRole;
                        
                        var masterIdUser = '<%=((User)request.getSession().getAttribute("user")).getIdUser()%>';
                        var slaveIdUser = data.idUser;  

                        if(masterIdRole < slaveIdRole)
                            return ("<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='Edit' onclick='edit("+data.idUser+");'><i class='fas fa-pen'></i></button>"
                        );
                        
                        if(masterIdUser == slaveIdUser)
                             return ("<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='Edit' onclick='editUserPopUp("+data.idUser+");'><i class='fas fa-pen'></i></button>"
                        );
                        
                        return ("<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='Edit' onclick='setSimpleNotification(false,"+ "\"You do not have permissions to edit this user! \");'><i class='fas fa-pen'></i></button>");
                        
                       
                    },
                },
            ],

            deferRender: true,
            responsive: {
                breakpoints: [
                    {name: "bigdesktop", width: Infinity},
                    {name: "meddesktop", width: 1430},
                    {name: "smalldesktop", width: 1230},
                    {name: "medium", width: 1138},
                    {name: "tabletl", width: 974},
                    {name: "btwtabllandp", width: 798},
                    {name: "tabletp", width: 718},
                    {name: "mobilel", width: 430},
                    {name: "mobilep", width: 270},
                ],
            },
            select: {
                style: "multi",
                selector: "td:not(:last-child)",
            },
            dom: "Bftipr", //l",
            buttons: [
                {
                    extend: "selected",
                    text: "Delete",
                    action: function (e, dt, node, config) {
                        Swal.fire({
                            title: "Are you sure?",
                            text: "You will not be able to revert this operation!",
                            icon: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#3085d6",
                            cancelButtonColor: "#d33",
                            confirmButtonText: "Yes, delete!",
                            cancelButtonText: "Cancel",
                        }).then((result) => {
                            if (result.isConfirmed) {
                                var data = table.rows({selected: true}).data();
                                var newarray = [];
                                for (var i = 0; i < data.length; i++)
                                    newarray.push(data[i].email);

                                $.ajax({
                                    type: "GET",
                                    url: "<%=Action.DELETE_USERS.value%>",
                                    data: {newarray},
                                    contentType: "application/json",
                                    dataType: "json",
                                    success: function (data) {
                                        if (JSON.parse(data) == false) this.error();
                                        else {
                                            table.ajax.reload();
                                            var title = "Deleted";
                                            var description = "User successfully deleted";
                                            if (newarray.length > 1) {
                                                title = "Deleted";
                                                description = "Users successfully deleted";
                                            }
                                            Swal.fire(title, description, "success");
                                        }
                                    },
                                    error: function (e) {
                                        Swal.fire(
                                            "Error",
                                            "An error has occurred",
                                            "error"
                                        );
                                    },
                                });
                            }
                        });
                    },
                },
                {
                    extend: "selected",
                    text: "Ban/Unban",
                    action: function (e, dt, node, config) {
                        var data = table.rows({selected: true}).data();
                        var answer = 1;
                        var newarray = [];
                        for (var i = 0; i < data.length; i++)
                            newarray.push(data[i].email);
                        Swal.fire({
                            title: "What action do you want to perform on the selected users?", 
                            showDenyButton: true,
                            showCancelButton: true,
                            confirmButtonText: "Ban",
                            denyButtonText: `Unban`,
                            cancelButtonText: "Cancel",
                        }).then((result) => {
                            /* Read more about isConfirmed, isDenied below */
                            if (result.isConfirmed) {
                                Swal.fire({
                                    title:
                                        "How many days do you want to ban the user for?",
                                    input: "text",
                                    inputLabel: "Days",
                                    inputValue: answer,
                                    showCancelButton: true,
                                    confirmButtonText: "Ban",
                                    denyButtonText: `Unban`,
                                    cancelButtonText: "Cancel",
                                    inputValidator: (value) => {
                                        if (!value) {
                                            return "You must insert a number of days!";
                                        }
                                    },
                                }).then((result) => {
                                    /* Read more about isConfirmed, isDenied below */
                                    if (result.isConfirmed) {
                                        $.ajax({
                                            type: "GET",
                                            url: "<%=Action.BAN_USERS.value%>",
                                            data: {
                                                newarray,
                                                days: answer,
                                            },
                                            contentType: "application/json",
                                            dataType: "json",
                                            success: function (data) {
                                                    console.log(data);
                                                    var responseType = "success";
                                                    var message = "Operation performed";
                                                    if(data!=="OK") {responseType = "warning"; message = data;}
                                                    table.ajax.reload();
                                                    Swal.fire(message, "", responseType);
                                                
                                            },
                                            error: function () {
                                                Swal.fire(
                                                    "Error!",
                                                    "Error while performing the operation!",
                                                    "warning"
                                                );
                                            },
                                        });
                                    }
                                });
                            } else if (result.isDenied) {
                                $.ajax({
                                    type: "GET",
                                    url: "<%=Action.SBAN_USERS.value%>",
                                    data: {
                                        newarray,
                                    },
                                    contentType: "application/json",
                                    dataType: "json",
                                            success: function (data) {
                                                    console.log(data);
                                                    var responseType = "success";
                                                    var message = "Operation performed";
                                                    if(data!=="OK") {responseType = "warning"; message = data;}
                                                    table.ajax.reload();
                                                    Swal.fire(message, "", responseType);
                                                
                                            },
                                    error: function () {
                                        Swal.fire(
                                            "Error!",
                                            "Error while performing the operation!",
                                            "warning"
                                        );
                                    },
                                });
                            }
                        });
                    },
                },
            ],
            order: [[1, "asc"]],

            language: {
                select: {
                    rows: {
                        1: "1 row selected",
                        _: "%d rows selected",
                    },
                },
                sEmptyTable: "No data present in the table",
                sInfo: "View from _START_ to _END_ of _TOTAL_ eleemnts",
                sInfoEmpty: "View from 0 to 0 of 0 elements",
                sInfoFiltered: "(filtered from _MAX_ total elements)",
                sInfoPostFix: "",
                sInfoThousands: ".",
                sLengthMenu: "View _MENU_ elements",
                sLoadingRecords: "Loading...",
                sProcessing: "Processing...",
                sSearch: "Search:",
                sZeroRecords: "The search did not bring any results.",
                oPaginate: {
                    sFirst: "Start",
                    sPrevious: "<i class='fas fa-angle-double-left'></i>",
                    sNext: "<i class='fas fa-angle-double-right'></i>",
                    sLast: "Fine",
                },
                oAria: {
                    sSortAscending:
                        ": activate to sort the column in ascending order",
                    sSortDescending:
                        ": activate to sort the column in descending order",
                },
            },
            lengthMenu: [
                [10, 25, 50],
                [10, 25, 50],
            ],
            editor: {
                remove: {
                    button: "Remove",
                    confirm: {
                        _: "Are you sure you want to remove %d rows?",
                        1: "Are you sure you want to remove 1 row?",
                    },
                    submit: "Remove",
                    title: "Remove",
                },
            },
            thousands: ".",
            decimal: ",",
            columnDefs: [{className: "dt-center", targets: "_all"}],
            rowCallback: function (row, data, index) {
                if(data["banned"] == true)
                    $(row).css("background-color", "rgba(255, 0, 0, 0.4)");
            }
        });
    });
</script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<div class="container">
    <br/>

    <table id="userTable"
            class="table table-striped table-bordered table-hover display" style="width:100%">
            </table>
    <%-- <c:import url='<%=Page.HOME+"/users/edit-user-form.html"%>'/> --%>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
