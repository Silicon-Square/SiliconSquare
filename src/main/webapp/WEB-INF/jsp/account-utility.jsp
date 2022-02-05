<script>

    function deleteConfig(id, url) {
        $.ajax({
            url: url+"/delete/"+id,
            type: 'DELETE',
            beforeSend: function () {
                Swal.fire({
                    title: 'Please Wait!',
                    text: 'We\'re trying to delete your configuration',
                    allowOutsideClick: false,
                    showConfirmButton: false,
                    showSpinner: true,
                    didOpen: () => {
                        Swal.showLoading()
                    },

                });
            },
            success: function(result) {
                if(JSON.parse(result) == "false"){ error(event); return;}
                $("#configuration_" + id).empty();
                showPopUp("Your Configuration has been deleted.", "success");
                getConfigurationCount(url, id);
            },
            error: function(result) {
                        showPopUp("Your Configuration has not been deleted.", "error");
                
            }

        });
    }

    function getConfigurationCount(url, idConfig) {
        var count = document.getElementById("configuration_count").innerHTML;
        count = parseInt(count);
        count--;
        document.getElementById("configuration_count").innerHTML = count;
    }

    const titleField = document.getElementById("new-post-title");
    const descriptionField = document.getElementById("new-post-description");
    const bakeca = $('#allUserPost');

    // vv load posts from server vv
    function requestPost(path){
        return $.ajax({
            type: "get",
            url: path,
            processData: false,
            contentType: "application/json",
            dataType: "json",
            cache: false,
            timeout: 800000,
            success: function (datas) {
                if(datas == null){
                    this.error();
                }
            },
            error: function (e) {
                showPopUp("errore nel caricamento post");
                console.log(e);
            }
        })
    }
    // ^^ load posts from server ^^

    // vv send post to server vv
    function sendFormData(action){

        if(titleField.value === "" || descriptionField.value === ""){
            showPopUp("Non puoi pubblicare un post vuoto", "error")
            return;
        }
            
        var data = new FormData($("#form-datas")[0]);
        $("#publish-post").prop("disabled", true);

        data.append("user", <%=((User)request.getSession().getAttribute("user")).getIdUser()%>);

        $.ajax({
            type: "POST",
            url: action,
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 800000,
            success: function (data) {
                if(JSON.parse(data) ==="false") this.error(event);
                else{
                    fetchPosts();
                    showPopUp("Il post &egrave stato pubblicato", "success");
                    $("#publish-post").prop("disabled", false);
                    resetForm();
                }
            },
            error: function (e) {
                showPopUp("C'&egrave stato un errore durante la pubblicazione", "error")
                $("#publish-post").prop("disabled", false);
            }
        });

    }

    // ^^ send post to server ^^

    // vv publish post vv
    $("#publish-post").click(function(){
        sendFormData("<%=Action.PUBLISH_POST.value%>");
    });

    function resetForm(){
        $('#form-datas')[0].reset();

        deleteAttached('picture-attached', ['picture']);
        //$('#form-datas')[0].deleteAttached(); servirà per la configurazione
        deleteAttached('poll-attached', ['poll-opt1', 'poll-opt2']); 
    }

    // vv add external content to post vv
    $("#btn-add-poll").click(function(){
        const pollOpt1Field = $('#poll-opt1');
        const pollOpt2Field = $('#poll-opt2');

        Swal.fire({
        title: 'Aggiunta di un sondaggio',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Aggiungi',
        cancelButtonText: 'Annulla',
        html:
            '<p class="col-12"> Indica le opzioni del sondaggio </p>' +
            '<div style="display:flex">' + 
                '<form class="form-floating col-6 p-1">' +
                    '<input type="email" class="form-control" id="option-one" placeholder=" " value=" ">' +
                    '<label for="floatingInputValue">Opzione uno</label>' +
                '</form>'+
                '<form class="form-floating col-6 p-1">' +
                    '<input type="email" class="form-control" id="option-two" placeholder=" " value=" ">' +
                    '<label for="floatingInputValue">Opzione due</label>' +
                '</form>' +
            '</div>',
        }).then((result) => {
            if (result.isConfirmed) {
                if($("#option-one").val().length == 0 || $("#option-two").val().length == 0){
                    showPopUp("Non hai indicato le opzioni", "error");
                    return;
                }
            
                if(pollOpt1Field.val() !== '' || pollOpt2Field.val() !== ''){
                    showPopUp("Hai gi&agrave caricato un sondaggio, eliminalo prima", "error");
                    return;
                }

                attachPoll( $("#option-one").val(), $("#option-two").val())
            }
        });
    });

    function attachPoll( opz1, opz2){
        const pollOpt1Field = $('#poll-opt1');
        const pollOpt2Field = $('#poll-opt2');

        pollOpt1Field.val(opz1);
        pollOpt2Field.val(opz2);

        $("#attachments").append(' <div class="attached" id="poll-attached">' + 
                                        'Sondaggio' + 
                                        createCloseButton('poll-attached', [pollOpt1Field.attr('id'), pollOpt2Field.attr('id')]) +
                                    '</div>');

    }

    /*$("#btn-add-configuration").click(function(){
    });*/

    $("#btn-add-picture").click(function(){
        const pictureField = $('#picture');
        const pictureToEditField = $('#picture-to-edit');
        var oldPicture = pictureField.val();

        Swal.fire({
        title: 'Aggiunta di una foto',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Add',
        cancelButtonText: 'Cancel',
        html:'<p class="col-12"> Choose the photo you wnt to upload </p>',

        }).then((result) => {
            if (result.isConfirmed) {

                if(pictureField.val() == null){
                    showPopUp("You haven't uploaded any photo", "error");
                    return;
                }
                
                if(oldPicture !== '' || pictureToEditField.val() !== ''){
                    showPopUp("You have already uploaded a photo, delete it first", "error");
                    pictureField.val('');
                    return;
                }

                $("#attachments").append(' <div class="attached" id="picture-attached"> foto' + createCloseButton('picture-attached', ['picture, picture-to-edit']) + '</div>');
                
            }else{
                
                if(oldPicture === '')
                    pictureField.val('');

            }
        });
    });


    function createCloseButton(attached, id_fields){
        var button = '<button type="button" class="btn-close" onclick="deleteAttached(\'' + attached + '\',';

        button = button + '[';
        
        for(var i = 0; i < id_fields.length; ++i){
            button = button + '\'' + id_fields[i] + '\'';

            if(i !== id_fields.length-1)
                button = button + ',';
        }

        button = button + '])" aria-label="Close"></button>';

        return button;
    }


    function deleteAttached(attached, idss){
        idss.forEach(element => {
            $('#' + element).val('');
        });

        $("#" + attached).remove();
    }
    // ^^ add external content to post ^^
    // ^^ publish post ^^ 

    // vv post loading vv
    $( document ).ready(function() {
        fetchPosts();
    });

    function fetchPosts(){
        var user = '<%=((User)request.getSession().getAttribute("user")).getIdUser()%>';
        var functionPath = '/loadUserPosts/' + user;
        putPlaceHolder(bakeca);
        bakeca.empty();

        requestPost(functionPath).then( function(response){
            if(response.length == 0){
                showPopUp("Nothing to show", "info");
                return;
            }

            showPostInPage(response, bakeca);

            for(var i = 0; i < response.length; ++i)
                addModificationListener('post'+response[i].idPost);

            continueLoadingPost(response, user);
        });
    }

    function continueLoadingPost(response, user){
        $("div.load-wraper").remove();
        if(response.length <= 0)
            return;
        
        putPlaceHolder(bakeca);
        requestPost("/nextUserPosts/" + user + "/" + (response[response.length - 1].idPost)).then(function(newResponse){
            showPostInPage(newResponse, bakeca);

            for(var i = 0; i < newResponse.length; ++i)
                addModificationListener('post'+ newResponse[i].idPost);

            continueLoadingPost(newResponse, user);
        });
    }

    // ^^ post loading ^^

    // vv post actions vv
    function addModificationListener(post){
        const interactions = $('#' + post).find('#interactions');

        interactions.append('<div class="interact-icon position-absolute end-0">' +
                                '<div class="dropdown">' +
                                    '<button class="btn dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="transform: translate(-12px);">' +
                                        '<i class="fas fa-ellipsis-v"></i>' +
                                    '</button>' +
                                    '<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">' +
                                        '<li><a id="' + post + 'modify" class="dropdown-item" onclick="modifyP(\'' + post + '\')">Modifica </a></li>' +
                                        '<li><a id="' + post + 'delete" class="dropdown-item" onclick="deleteP(\'' + post + '\')">Elimina </a></li>' +
                                    '</ul>' +
                                '</div>' +
                            '</div>');
    }

    function modifyP(post){
        var id_post = calculatePostId(post);
        var functionPath = "/loadUserPost/" + id_post;

        $.when(
            requestPost(functionPath)

        ).then(function(datas) {
            resetForm();

            titleField.value = datas.postTitle;
            descriptionField.value = datas.postDescription;

            if(datas.pollOptionOne != '' && datas.pollOptionTwo != '')
                attachPoll(datas.pollOptionOne, datas.pollOptionTwo);

            if(datas.postPicture != null){
                $("#attachments").append(' <div class="attached" id="picture-attached"> foto' + createCloseButton('picture-attached', ['picture-to-edit']) + '</div>');
                $("#picture-to-edit").val(datas.postPicture);
            }

            $('#post-in-edit').val(datas.idPost);

            if(!$('#modify-button-section').length){
                $('#publish-post').addClass("hidden");
                $('#buttons').append("<div id=\"modify-button-section\"><button id=\"modify-post\" type=\"submit\" class=\"btn btn-success w-100\" onclick=\"modifyPost()\">Modifica<button type=\"button\" class=\"btn-close position-absolute end-0\" onclick=\"returnToPublish()\" aria-label=\"Close\"></button></button></div>");
            }

            document.getElementById('new-post').scrollIntoView();
        });
    }

    function modifyPost(){
        sendFormData("/modifyPost/" + $('#post-in-edit').val());
        returnToPublish();
    }

    function returnToPublish(){
        resetForm();
        
        $("#publish-post").removeClass("hidden");
        $("#modify-button-section").remove();
    }

    function deleteP(post){

        Swal.fire({
            title: 'sicuro?',
            text: "non sarai in grado di annullare questa operazione",
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Continua'
        }).then((result) => {
            if (result.isConfirmed) {
                var id_post = calculatePostId(post);
                var functionPath = "/deletePost/" + id_post;


                $.ajax({
                    type: "get",
                    url: functionPath,
                    processData: false,
                    contentType: "application/json",
                    dataType: "json",
                    cache: false,
                    timeout: 800000,
                    success: function (data) {
                        if(JSON.parse(data) === false){
                            this.error();
                        }else{
                            $("#" + post).empty();
                            showPopUp("Il post &egrave stato cancellato", "success");
                        }
                    },
                    error: function (e) {
                        showPopUp("errore durante la cancellazione", "error");
                    }
                
                });      
            }
        })
    }

    // ^^ post actions ^^

    // vv pop up vv
    function showPopUp(text, type){
        Swal.fire({
            position: 'top-end',
            icon: type,
            title: text,
            showConfirmButton: false,
            timer: 2000
        });
    }
    // ^^ pop up ^^
</script>