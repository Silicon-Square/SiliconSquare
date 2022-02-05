<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.siliconsquare.common.redirect.ComponentCategory" %>
<%@ page import="it.siliconsquare.model.component.Component" %>
<%@ page import="it.siliconsquare.provider.Database" %>
<%@ page import="it.siliconsquare.common.Security" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    if(!Security.isAuthorized(Page.SUGGESTCOMPONENT, request.getSession()))
        response.sendRedirect(Page.ERROR);
%>

<!DOCTYPE html>
<html lang="it">

<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
  <title>Suggest component</title>
  
    <link rel="shortcut icon" href="https://resources.siliconsquare.it/logo/favicon.ico?v=1.1.2" type="image/x-icon">
  <link rel="stylesheet" href="<%=Page.HOME%>css/suggest-component.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body id="body">


   <div class="container">
      <div id="components" class="row justify-content-center">
  
      </div>
  </div>
                <c:import url='<%=Page.HOME+"components/single-dialog-component.html"%>'/>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
  <script src="<%=Page.HOME%>js/component-utility.js" type="text/javascript"></script>

  <script>
  //create an ajax call that will get the unpublished components from the database and fill the component div
  $(document).ready(function() {
                Swal.fire({
                    title: 'Please Wait!',
                    // text: 'Your link is being generated',
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    didOpen: () => {
                        Swal.showLoading()
                    },

                });
      renderComponentInPage();

      //parse Json and create the component in the page
      
  });

  function loadComponents(){
    return $.ajax({
      url: '<%=Page.COMPONENT%>/unpublishedComponents',
      type: 'GET',
      dataType: 'json',
      contentType: 'application/json',
      onBeforeSend: function(request) {
      },   
      success: function(data) {
        console.log(data);
      },
      error: function(data) {
        console.log(data);
      },
      complete: function(data){
        Swal.close();
      }
    });
  }

  function renderComponentInPage(){
    loadComponents().then( response =>{
      $.each(response, function(index, value){      
      $('#components').append('<div id="component_' + value.id + '_' + value.category + '" class="component col-lg-3 col-md-2 mt-5">'+
                                    '<div id="image-container">'+
                                      '<img class="m-auto" src="' + value.image + '">'+
                                    '</div>'+
                                    '<div class="description text-center">'+
                                      '<div id="title-component">' + value.name + '</div>'+
                                      '<div class="btn btn-primary" onclick="publishComponent('+value.id+',\''+value.category+'\')">Publish</div>'+
                                    '</div>' +
                                '</div>' +
                                '<div id="separator" class="col-1"></div>');
      
      
      });
    })
  }
  
var categoryDisplayName ="";
  function publishComponent(id, category){
    categoryDisplayName = category.replace("-", " ");
    editComponentPopUp(category,id);
    //enable submit button jquery
    $('#submit-button').prop('disabled', false);
    $('#submit-buttom').on('click', function(e){
     $('#component_' + id + '_' + category).remove();
    });
  }
   
  </script>
</body>
</html>
