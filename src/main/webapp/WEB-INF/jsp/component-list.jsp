<%@ page import="it.siliconsquare.model.component.Component" %>
<%@ page import="java.util.List" %>
<%@ page import="it.siliconsquare.common.redirect.*" %>
<%@ page import="it.siliconsquare.common.HtmlVisualizer" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!-- Responsive navbar-->

<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<%-- BEGIN Import for component list --%>
<link href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/4.9.95/css/materialdesignicons.css"
      rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"
        integrity="sha256-Zr3vByTlMGQhvMfgkQ5BtWRSKBGa2QlspKYJnkjZTmo=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css"
      integrity="sha256-mLBIhmBvigTFWPSCtvdu6a76T+3Xyt+K571hupeFLg4=" crossorigin="anonymous"/>
<%----%>

<%--    DataTables --%>
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.3/css/select.dataTables.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css"/>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css"/>

<link rel="stylesheet" href="<%=Page.HOME%>css/component-style.css"/>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/select/1.3.3/js/dataTables.select.min.js"></script>

<!-- Buttons For Datatable --->
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.1.0/css/buttons.dataTables.min.css"/>

<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
<script type="text/javascript"
        src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>

<%----%>
<script src="<%=Page.HOME%>js/utility.js" type="text/javascript"></script>
<script src="<%=Page.HOME%>js/component-utility.js" type="text/javascript"></script>
<%-- END Import for Component List--%>

<html lang="it">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <%
        String category = (String) request.getAttribute("componentCategory");
        String categoryDisplayName = (String) request.getAttribute("categoryDisplayName");
    %>
    <title><%=Page.TITLE%><%=HtmlVisualizer.fromSlug(category, true)%>
    </title>
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">

    <style>
        .left-sidebar{
            border-radius: 25px!important;
        }
    </style>
</head>

<body>

<%String action ="add";%>
<script>

            var categoryDisplayName = "<%=categoryDisplayName%>";
            </script>

<div class="container">
    <div class="row align-items-center">
        <div class="col-lg-12">
            <div class="show-results mt-5 ">
                <div class="float-left">
                    <%--                    <h5 class="text-dark mb-0 pt-2">Risultati: 1-6 di <%=totalResult%>                    </h5>--%>
                </div>

            </div>
        </div>
    </div>

    <div class="row">
        <!-- LEFT SIDEBAR -->
        <div class="col-lg-3" style="margin-top: 20px;">
            <div class="left-sidebar" style="position: sticky;top: 86px; margin-bottom: 80px;">
                <div class="accordion" id="accordionExample">
                    <!-- All Component Categories Widget -->
                    <div class="card mt-4">
                        <div class="card-header">Categories</div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <ul class="list-unstyled mb-0">
                                        <%
                                            for (String cat : it.siliconsquare.common.redirect.ComponentCategory.ALL) {
                                                if (cat.equalsIgnoreCase(category)) continue;
                                                String url = Page.COMPONENT + "/" + cat;
                                                out.print("<li class='category-list-links'><a href=\"" + url + "\">" + HtmlVisualizer.fromSlug(cat) + "</a></li>");
                                            } %>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END All Component Categories                 et -->
                </div>
            </div>
        </div>
        <div class="col-lg-9">
            <div class="candidates-listing-item">

                <!-- Begin Item -->
                <table id="componentsTable"  class="table table-bordered table-hover display" style="width:100%"></table>
                
                <c:import url='<%=Page.HOME+"components/single-dialog-component.html"%>'/>
                <!-- END ITEM -->

            </div>
        </div>
        <script>
            var url =  location.href.replace("#", "");
            path = url + '.asp';
            
            $(document).ready(function () {
                loadActionList();
                var table = $('#componentsTable').DataTable({
                    ajax: {
                        url: path,
                        dataSrc: ''
                    },
            scrollX:false,
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
                    columns: [
                        {
                            data: "image",
                            title: "Image",
                            searchable: false,
                            orderable: false, responsivePriority: 1,
                            render: function (data, type, row) {
                                return '<img src="' + data + '">';
                            }
                        },
                        {

                            data: {name: "Name", id: "id"}, 
                            title: "Name", responsivePriority: 1, 
                            render: function (data, type, row) {
                                return '<a href="<%=Page.COMPONENT%>/<%=category%>/' + data.id + '">' + data.Name + '</a>';
                            }
                        },

                        {data: "Manufacturer", title: "Manufacturer", responsivePriority: 2},

                        {
                            data: "id",
                            title: "Actions",
                            searchable: false,
                            orderable: false, responsivePriority: 1,
                            render: function (data, type, row) {
                                var editButton = "";
                                <%action="add";%>
                                <% if(Security.isAuthorized(Action.EDIT_COMPONENT, request.getSession())) {%>
                                editButton = "<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='Edit' onclick='editComponentPopUp(\"<%=category%>\", " + data + ")'><i class='fas fa-pen'></i></button>";
                                <%}%>

                                var viewButton = "<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='View' onclick='view(\"<%=Page.COMPONENT%>/<%=category%>\",  "+data+");'><i class='fas fa-eye'></i></button>";
                                return "<div style='display: inline-flex'>" + editButton + viewButton +
                                 "</div>";
                            }
                        }
                    ],
                    select: {
                        style: 'multi',
                        selector: 'td:not(:last-child)'
                    },
                    <% if(Security.isAuthorized(Action.EDIT_COMPONENT, request.getSession())){ %>
                    dom: "Bftipr", //l",
                    buttons: [

                        {
                            // extend: 'selected',
                            text: "Add",
                            action: function (e, dt, node, config) {
                                <%action="add";%>
                                loadCategoryFields("<%=category%>");
                            }
                        },
                        {
                            extend: 'selected',
                            text: "Delete",
                            action: function (e, dt, node, config) {
                                Swal.fire({
                                    title: 'Are you sure?',
                                    text: "You won't be able to revert this operation!",
                                    type: 'warning',
                                    showCancelButton: true,
                                    confirmButtonColor: '#3085d6',
                                    confirmButtonColor: '#3085d6',
                                    cancelButtonColor: '#d33',
                                    confirmButtonText: 'Yes, delete it!',
                                    cancelButtonText: "Undo"
                                }).then((result) => {
                                    if (result.isConfirmed) {
                                        var data = table.rows({selected: true}).data();
                                        var newarray = [];
                                        for (var i = 0; i < data.length; i++)
                                            newarray.push(data[i].id);
                                        
                                        $.ajax({
                                            type: "GET",
                                            url: "<%=Action.DELETE_COMPONENT.value%><%=category%>",
                                            data: {newarray},
                                            contentType: "application/json",
                                            dataType: "json",
                                            success: function (data) {
                                                if(JSON.parse(data)!==false){
                                                table.ajax.reload();
                                                var title = "Removed";
                                                var description = "Component removed successfully";
                                                if (newarray.length > 1) 
                                                    description = "Components removed successfully";
                                                
                                                Swal.fire(
                                                    title,
                                                    description,
                                                    'success'
                                                )
                                                }
                                            }
                                        });

                                    }
                                })

                            }
                        }
                    ],
                    <%} else if(Security.isAuthorized(Action.SUGGEST_COMPONENT, request.getSession())){ %>
                  dom: 'Bfrtip',
                    buttons: [
                        {
                            // extend: 'selected',
                            text: "Suggest",
                            action: function (e, dt, node, config) {
                                <%action="add";%>
                                loadCategoryFields("<%=category%>");
                                setGeniusRole();
                            }
                        }
                    ],
                    <% }else {%>

                    dom: "ftipr",

                    <%}%>

                    "order":
                        [[1, "asc"]],

                    "language":
                        {
                            "oPaginate":
                                {                                 
                                    "sPrevious":
                                        "<i class='fas fa-angle-double-left'></i>",
                                    "sNext":
                                        "<i class='fas fa-angle-double-right'></i>",
                                }
                        },
                    "lengthMenu":
                        [[25, 50], [25, 50]]
                    ,
                    "thousands":
                        ".",
                    "decimal":
                        ",",
                    "columnDefs":
                        [
                            {"className": "dt-center", "targets": "_all"}
                        ]

                });
            });
        </script>
        <!-- Footer-->
        <%@ include file="/WEB-INF/jsp/footer.jsp" %>
<script>
</script>
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>
