<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.siliconsquare.model.configuration.Configuration" %>
<%@ page import="it.siliconsquare.common.redirect.Page" %>
<%@ page import="it.siliconsquare.logger.Logger" %>
<%@ page import="it.siliconsquare.model.component.Component" %>


<html>
<head>
    <title>Silicon Square</title>
    <link
            rel="shortcut icon"
            href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2"
            type="image/x-icon"
    />

    <%@ include file="/WEB-INF/jsp/header.jsp" %>
    <!-- datatable installation  -->
    <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css"
    />

    <!-- datatable installation  -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <%-- jquery  --%>

    <style>
        .components-section {
            padding: 10px;
            margin-top: 50px;
            margin-bottom: 50px;
            border-radius: 25px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),
            0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }
        
        .price-field {
            margin: 2px;
        }

        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
            display: none;
            vertical-align: middle;
            height: 100%;
            margin: 120px;
        }

        #turn {
            position: fixed;
            top: 0;
            left: 0;
            background: black;
            height: 100%;
            width: 100%;

            z-index: 100;

            display: none;
        }

        .center {
            width: 50%;

            position: absolute;
            top: 50%;
            left: 25%;
            transform: translate(0, -50%);
        }

        .phone {
            height: 50px;
            width: 100px;
            border: 3px solid white;
            border-radius: 10px;
            animation: rotate 1.5s ease-in-out infinite alternate;
            margin: auto;

            margin-bottom: 30px;
        }

        #turn {
            color: white;
            font-size: 1em;
            display: none;
        }

        @keyframes rotate {
            0% {
                transform: rotate(-90deg);
            }
            50% {
                transform: rotate(5deg);
            }

            75%,
            100% {
                transform: rotate(0deg);
            }
        }

        @media only screen and (max-device-width: 812px) and (orientation: portrait) {
            #turn {
                display: inline;
            }
        }

        .configurator_left-bar {
            margin-top: 7% !important;
            margin-bottom: 4% !important;
            padding: 8px 0;

            position: -webkit-sticky;
            position: sticky;
            top: 100px;

            max-height: 300px;

            display: flex;
            justify-content: space-between;
            flex-direction: column;

            border-radius: 25px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),
            0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }

        .configurator_left-bar a {
            margin: auto;

            width: 100%;
            height: 100%;
            max-width: 60px;
            max-height: 45px;
        }

        @media only screen and (max-width: 767.5px) {
            .configurator_left-bar {
                flex-direction: row;
            }
        }

        .configurator_left-bar a div {
            padding: 10px;

            width: 100%;
            height: 100%;

            text-align: center !important;

            border-radius: 25px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),
            0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }

        .configurator_left-bar i {
            margin: auto;
        }

        .configurator_left-bar a div:active {
            color: #ffffff;
            background-color: #0d6efd;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0), 0 6px 20px 0 rgba(0, 0, 0, 0);
        }
    </style>
</head>
<body>
<%if(request.getSession().getAttribute("configurator")==null)
request.getSession().setAttribute("configurator",new Configuration());%>

<div id="turn">
    <div class="center">
        <div class="phone"></div>
        <div class="message text-center">Please rotate your device!</div>
    </div>
</div>

<div class="container">
    <div class="row justify-content-md-center components-section">
        <div class="col-md-11">
            <table
                    id="component-list"
                    class="table component-list"
                    style="width: 100%; overflow: hidden"
            ></table>
        </div>

        <div class="col-md-1 configurator_left-bar">
            <a href="#">
                <div id="saveBtn" onclick="saveConfig();">
                    <i class="far fa-save"></i>
                </div>
            </a>
          
            <a href="#">
                <div class="text-center" onclick="shareConfig();"> 
                    <i class="fas fa-share-alt"></i>
                </div>
            </a>
            <a href="#">
                <div id="clearConfigBtn" onclick="clearConfig();">
                    <i class="fas fa-sync-alt"></i>
                </div>
            </a>
            <a href="#" onclick="openAmazonCart();">
                <div>
                    <i class="fas fa-shopping-cart"></i>
                </div>
            </a>

            <div class="overflow-auto" style="margin: auto; max-width: 100%">
                <span id="total-price"> 0 </span><span> € </span>
            </div>
      
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
<div class="modal fade" id="componentsPopUp" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Choose Your Component</h4>
                <span type="button" class="close" data-bs-dismiss="modal" aria-hidden="true">&times;
                    </span>

            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div>
                        <div class="candidates-listing-item">
                            <!-- Begin Item -->
                            <table id="componentsTablePopUp" class="table table-bordered table-hover">
                            </table>
                            <!-- END ITEM -->
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="my-button" id="cancelBtn" data-bs-dismiss="modal"><span
                            class="fas fa-trash-alt"></span> Cancel
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var numberOfComponents = 0;

    function openAmazonCart(){
         if (numberOfComponents <= 0) {
            Swal.fire({
                title: "You need to add at least one component",
                text: "Please add a component!",
                icon: "warning",
            })
                .then((confirm) => {
                    return;
                });
            this.event.preventDefault();
            return;
        }

    $.ajax({
        url: "/configurator/get-amazon-cart/all/items.asp",
        type: "POST",
        success: function (data) {
            if(data != ""){
        var link = "https://www.amazon.it/gp/aws/cart/add.html?"+data;
                Swal.fire({
            title: "Components added to cart!",
            icon: "success",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            footer: "<a href="+link+" target='_blank'>Click here to procede with your purchase!</a>"
        });
            }else{
 Swal.fire({
            title: "The selected components are not available in Amazon!",
            icon: "warning",
            showCancelButton: false,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
        });
            }
        }
    });

    }

    function shareConfig(){
        if (numberOfComponents <= 0) {
            Swal.fire({
                title: "You need to add at least one component",
                text: "Please add a component!",
                icon: "warning",
            })
                .then((confirm) => {
                    return;
                });
            this.event.preventDefault();
            return;
        }

        if (<%=request.getSession().getAttribute("user")==null%>) {
            Swal.fire({
                title: "You need to be logged in to share your configuration",
                text: "Please login or register!",
                icon: "warning",
            })
                .then((confirm) => {
                    return;
                });
            event.preventDefault();
            return;
        }

        var apiUrl = "<%=Page.CONFIGURATOR%>/save";
        $.ajax({
            type: "POST",
            url: apiUrl,
            beforeSend: function () {
                Swal.fire({
                    title: 'Please Wait!',
                    text: 'Your link is being generated',
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    showSpinner: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },

                });
            },
            success: function (data, error) {

                if (data == -1) {
                    this.error(event);
                }
                    share(data);
            }, error: function (e) {
                Swal.fire({
                    title: "Error",
                    text: "Something went wrong",
                    icon: "error"
                })
            }
        });

    }

    function share(configId){
        var linkMyUrl = "<%=Page.CONFIGURATOR%>/"+configId;
        Swal.fire({
            icon: 'info',
            title: 'Your configuration link',
            text: linkMyUrl,
            footer: '<a href="'+linkMyUrl+'">Custom Configuration Link</a>'
        })
    }

    function saveConfig() {
        if (numberOfComponents <= 0) {
            Swal.fire({
                title: "You need to add at least one component",
                text: "Please add a component!",
                icon: "warning",
            })
                .then((confirm) => {
                    return;
                });
            this.event.preventDefault();
            return;
        }

        if (<%=request.getSession().getAttribute("user")==null%>) {
            Swal.fire({
                title: "You need to be logged in to save your configuration",
                text: "Please login or register!",
                icon: "warning",
            })
                .then((confirm) => {
                    return;
                });
            event.preventDefault();
            return;
        }

        var apiUrl = "<%=Page.CONFIGURATOR%>/save";
        $.ajax({
            type: "POST",
            url: apiUrl,
            beforeSend: function () {
                Swal.fire({
                    title: 'Please Wait!',
                    text: 'Your configuration is being saved',
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    showSpinner: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },

                });
            },
            success: function (data, error) {

                if (JSON.parse(data) !== true) {
                    this.error(event);
                }
                Swal.fire({

                    title: "Configuration saved!",
                    text: "Your configuration has been saved!",
                    icon: "success",
                    timer: 2000,
                }).then((result) => {
                    Swal.close();
                    return;
                });
            }, error: function (e) {
                Swal.fire({
                    title: "Error",
                    text: "Something went wrong",
                    icon: "error"
                })
            }
        });
    }

    var path = "<%=Action.ALL_COMPONENT_CATEGORY.value%>";
    var table;
    var tablePopUp = "false";
    var categoryRows = new Map();
    $(document).ready(function () {
        
        table = $.fn.dataTable.ext.errMode = "none";
        table = $("#component-list").DataTable({
            bPaginate: false,
            bLengthChange: false,
            bFilter: false,
            bInfo: false,
            bAutoWidth: true,
            scrollX: true,
            ordering: true,
            ajax: {
                url: path,
                dataSrc: "",
            },
            columns: [

                 {
              title: "Category",
              data: "name",
              orderable: false,
              width: "25%"
            },
                 {title: "Component", orderable: false, width: "50%"},
                //  {  data: {name: "name"},
                //     title: "",
                //     orderable: false,
                //     width: "50%",
                // render: function(data, type, row, meta){
                
                // }

                
                // },
                {title: "Price", orderable: false, width: "10%"},
                {

                    data: {name: "name"},
                    title: "",
                    width: "5%",
                    orderable: false,
                    render: function (data, type, row, meta) {


                        var componentPath = data.path;
                        var idRow = meta.row;
                        categoryRows.set(componentPath, idRow);

                        var buttons = "";
                       buttons +=
                            '<div class=" row justify-content-md-center px-4">' +
                            '<button id="addBtn' +
                            meta.row +
                            '" class="my-button m-auto align-middle" type="submit" onclick ="chooseComponent(\'' +
                            data.single +
                            "','" +
                            meta.row +
                            "','" +
                            data.name +
                            "','" +
                            data.path +
                            "')\">" +
                            '<i class="far fa-plus-square"></i>' +
                            "</button>" +
                            "</div>";

                          //#1  BEGIN TEST load components from session
                   <% if(((Configuration)request.getSession().getAttribute("configurator")) != null){
                   for(Component c : ((Configuration)request.getSession().getAttribute("configurator")).getAllComponents()){
                  %>
                var componentPath = "/<%=c.getCategory()%>";        
                if (data.path == componentPath && tablePopUp == "false") {
                    var single = "<%=!c.canBeAddedMultipleTimes()%>";
                    var componentName = "<%=c.getTitle()%>";
                    var componentId = "<%=c.getId()%>";
                    var componentImage = "<%=c.getImageLink()%>";
                    var myRow = meta.row;
                
                    var componentLink = "<%=Page.COMPONENT%>" + componentPath + "/" + componentId;
                   loadComponentInTable("\"" + single + "\"", myRow, componentId, "\"" + componentImage + "\"",  componentName , componentPath, "\"" + componentLink + "\"");


                }

                <%}
                    }%>
                
                //#1 END TEST load components from session

                        return buttons;
                    }
                }
            ],"initComplete": function() {
                tablePopUp = "true";
  }
        })
        });
        
    function clearConfig() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, clear!'
        }).then((result) => {
            if(!result.isConfirmed)
                return;
            
            $.ajax({
                type: "POST",
                url: "<%=Page.CONFIGURATOR%>/clear",
                beforeSend: function () {
                    Swal.fire({
                        title: 'Please Wait!',
                        allowOutsideClick: false,
                        showConfirmButton: false,
                        // showSpinner: true,
                        didOpen: () => {
                            Swal.showLoading()
                        },

                    });
                },
                success: function (data, error) {
                    Swal.close();
                    table.ajax.reload();
                    return;
                }, error: function (e) {
                    Swal.fire({
                        title: "Error",
                        text: "Something went wrong",
                        icon: "error"
                    })
                }
            });


        });
    }

    // find the component "section DIV"
    function getComponentSection(idCategory) {
        var content = table.cell(idCategory, 1);
        if (content.data() == null)
            content.data("<div id=components" + idCategory + ">" + "</div>");

        return $("#components" + idCategory);
    }

    //test
    function loadComponentInTable(single, idCat, idComponent, imageC, nameC, categoryPath, pathC) {
        // console.log("single: " + single + "idCat: " + idCat + " idComponent: " + idComponent + " imageC: " + imageC + " nameC: " + nameC + " categoryPath: " + categoryPath + " pathC: " + pathC);
        if (JSON.parse(single) == "true")
            $("#addBtn" + idCat).attr("disabled", true);
        var childrenDiv = getComponentSection(idCat).children().length;

        var newComponent = buildConfigurationElement(nameC, pathC, idCat, idComponent, categoryPath);
        var newPrice = buildPriceField(idCat + "" + idComponent);

        getComponentSection(idCat).append(newComponent);
        getPriceSection(idCat).append(newPrice);

            
        <%request.getSession().setAttribute("configurator",request.getSession().getAttribute("configurator"));%>
        numberOfComponents = <%=((Configuration)request.getSession().getAttribute("configurator")).getAllComponents().size()%>;
    }

    //test

    // find the price section
    function getPriceSection(idCategory) {
        var content = table.cell(idCategory, 2);

        if (content.data() == null)
            content.data(
                "<div id=prices" + idCategory + ' class="pt-1">' + "</div>"
            );

        return $("#prices" + idCategory);
    }


    /**
     * opent table view for the selected component category
     * */
    function chooseComponent(single, idCat, catName, catPath) {
        populateTable(catName, catPath, single, idCat);
        myModalLabel.innerHTML = "Choose your " + catName;
        $("#componentsPopUp").modal("show");
    }

    // vv add new component for a category to the table vv
    /**
     * @pathC: the path of the component e.g. link
     * @imageC: the image of the component
     * @idC: the id of the component
     * @categoryPath: the category path of the component e.g. "/cpu-cooler"
     * @idCat: index of the row where the category is
     *
     * */
    function addComponent(single, idCat, idComponent, imageC, nameC, categoryPath, pathC) {
       // console.log("single: " + single + "idCat: " + idCat + " idComponent: " + idComponent + " imageC: " + imageC + " nameC: " + nameC + " categoryPath: " + categoryPath + " pathC: " + pathC);
        if (single == "true") $("#addBtn" + idCat).attr("disabled", true);
        var childrenDiv = getComponentSection(idCat).children().length;

        var newComponent = buildConfigurationElement(nameC, pathC, idCat, idComponent, categoryPath);
        var newPrice = buildPriceField(idCat + "" + idComponent);

        getComponentSection(idCat).append(newComponent);
        getPriceSection(idCat).append(newPrice);
        $("#cancelBtn").click();

        saveComponentToSession(idComponent, categoryPath);
        if (numberOfComponents > 0)
            $("#saveBtn").attr("disabled", false);

    }

    function saveComponentToSession(idComponent, categoryPath) {
        var apiUrl = "<%=Page.CONFIGURATOR%>/add" + categoryPath + "/" + idComponent;
        $.ajax({
            type: "POST",
            url: apiUrl,
            success: function (data) {
                numberOfComponents++;
            }
        });
    }

    function buildConfigurationElement(componentName, link, catName, idComponent, categoryPath) {
        var component =
            "<div id='" + catName + "_" +
            idComponent + "'" +
            ' class="configuration-element position-relative">' +
            "<a href=" +
            link +
            "> " +
            componentName +
            " </a>";
        component +=
            '<button class="btn btn-danger position-absolute top-50 end-0 translate-middle" type="submit" onclick="deleteListElement(\'' +
            catName +
            "','" +
            idComponent +
            "','" +
            categoryPath +
            "')\">" +
            '<i class="far fa-trash-alt"></i>' +
            "</button>";
            component+=
            "</div>";

        return component;
    }

    function buildPriceField(name) {
        var priceField =
            "<input id=price" +
            name +
            ' class="price-field" type="number" min="0" max="9999" onkeyup="calculatePrice()"></input>';

        return priceField;
    }

    // ^^ add new component for a category to the table ^^

    // vv delete a component form a category vv
    function deleteListElement(idCategory, idElement, categoryPath) {
        $("#" + idCategory + "_" + idElement).remove();
        // $("#component" + idElement).remove();
        $("#price" + idCategory + idElement).remove();

        calculatePrice();

        if (getComponentSection(idCategory).children().length <= 0)
            $("#addBtn" + idCategory).attr("disabled", false);

        removeComponentFromSession(idElement, categoryPath);
        if (numberOfComponents == 0)
            $("#saveBtn").attr("disabled", true);
    }

    // ^^ delete a component form a category ^^

    function removeComponentFromSession(idComponent, categoryPath) {
        var apiUrl = "<%=Page.CONFIGURATOR%>/remove" + categoryPath + "/" + idComponent;
        $.ajax({
            type: "DELETE",
            url: apiUrl,
            data: {
                idComponent: idComponent,
                categoryPath: categoryPath
            },
            success: function (data) {
                numberOfComponents--;
            }
        });
    }

    // calculate total price
    function calculatePrice() {
        var totalPriceField = $("#total-price");

        var totalPrice = 0;

        $(".price-field").each(function (index) {
            var componentPrice = parseInt($(this).val());

            if (!isNaN(componentPrice)) totalPrice += componentPrice;
        });

        totalPriceField.html(totalPrice);
    }
</script>

<script
        type="text/javascript"
        charset="utf8"
        src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"
></script>


<%-- Datatable Component Category Popup --%>
<script>
    // path = location.href + '.asp';
    //  var categorySlug = "case";
    function populateTable(catName, catPath, single, idCat) {
        var cPath = "/configurator" + catPath + ".asp";
        var tables = $('#componentsTablePopUp').DataTable({
            bDestroy: true,
            ajax: {
                url: cPath,
                dataSrc: ''
            },
            deferRender: true,
            columns: [
                {
                    data: "image",
                    title: "Image",
                    searchable: false,
                    orderable: false,
                    render: function (data, type, row) {
                        return '<img src="' + data + '">';
                    }
                },
                {

                    data: {name: "Nome", id: "id"},
                    title: "Nome",
                    render: function (data, type, row) {
                        return '<a href="<%=Page.COMPONENT%>' + catPath + '/' + data.id + '">' + data.Name + '</a>';
                    }
                },

                {
                    data: {name: "id"},
                    title: "Actions",
                    searchable: false,
                    orderable: false,
                    render: function (data, type, row) {
                        // single or multiple component (if it can be added more times) (single)
                        //id category (idCat)
                        var idC = data.id; //id of the component
                        var imageC = data.image; //image of the component
                        var nameC = data.Name; //name of the component
                        //category path of the component (catPath)
                        var linkComponent = "<%=Page.COMPONENT%>" + catPath + "/" + idC;

                        var addToConfigurationButton = "<button class='item' data-bs-toggle='tooltip' data-bs-placement='top' title='Add to the configuration' onclick='addComponent(\"" + single + "\"," + idCat + "," + idC + ", \"" + imageC + "\", \"" + nameC + "\", \"" + catPath + "\", \"" + linkComponent + "\");'><i class='fas fa-plus'></i></button>";
                        return "<div style='display: inline-flex'>" + addToConfigurationButton + "</div>";
                    }
                }
            ],

            dom: "ftipr",

            "order":
                [[1, "asc"]],

            "language":
                {
                    "oPaginate":
                        {
                            "sPrevious":
                                "<i class='fas fa-angle-double-left'></i>",
                            "sNext":
                                "<i class='fas fa-angle-double-right'></i>"
                        }
                }
            ,
            "lengthMenu":
                [[6, 25, 50], [6, 25, 50]]
            , "columnDefs":
                [
                    {"className": "dt-center", "targets": "_all"}
                ]

        });
    }
</script>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>