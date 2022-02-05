function calculatePostId(post) {
  return post.replace("post", "");
}

function askToServer(path, data) {
  return $.ajax({
    type: "POST",
    url: path,
    data: data,
    processData: false,
    contentType: false,
    cache: false,
    timeout: 800000,
    success: function (data) {},
    error: function (e) {
      showPopUp(
        "C'&egrave stato un errore durante la trasmissione dei dati",
        "error"
      );
      $("#publish-post").prop("disabled", false);
    },
  });
}

/* vv standard post creation vv */
function createPostStructure(
  postId,
  userName,
  userPicture,
  postTitle,
  postDescription
) {
  var post =
    '<div id="' +
    postId +
    '" class=" post-style container p-0 position-relative">' +
    '<div id="interactions" class="interactions position-absolute top-0 end-0">' +
    '<div id="like" class="interact-icon position-relative">' +
    '<span id="like-count" class="position-absolute top-0 start-0 translate-middle badge rounded-pill bg-secondary" style="font-size:11px">+99</span>' +
    '<i id="like-icon" class="far fa-heart"></i>' +
    "</div>" +
    '<div class="interact-profile">' +
    '<image class="profile-picture" src = "' +
    userPicture +
    '"></image>' +
    userName +
    "</div>" +
    "</div>" +
    '<div class="row" style="min-height:400px">' +
    '<div id="picture-section" class="col-12 col-lg-5 position-relative">' +
    '<div id="blurr">' +
    "</div>" +
    "</div>" +
    '<div id="content" class="col-12 col-lg ">' +
    '<div id="description-section" class="section">' +
    '<h2 id="title"> ' +
    postTitle +
    " </h2>" +
    '<p id="description" > ' +
    postDescription +
    " </p>" +
    "</div>" +
    "</div>" +
    "</div>" +
    '<div id="all-comments" class="row undisplay-comment">' +
    "</div>";

  return post;
}

function addLikeListener(post) {
  const likeBtn = $("#" + post).find("#like");
  const likeIcon = $("#" + post).find("#like-icon");
  const path = "/toggleLike/" + calculatePostId(post);

  var likeCounter = $("#" + post).find("#like-count");

  likeBtn.click({ param1: likeIcon }, function () {
    var counter = parseInt(likeCounter.html());

    if (likeIcon.hasClass("fas")) {
      likeIcon.removeClass("fas");
      likeIcon.addClass("far");

      likeCounter.html(counter - 1);
      haveLike(post);

      askToServer(path, "[]");
    } else {
      likeIcon.removeClass("far");
      likeIcon.addClass("fas");

      likeCounter.html(counter + 1);
      haveLike(post);

      askToServer(path, "[]");
    }
  });
}

function addCommentListener(post) {
  const commentBtn = $("#" + post).find("#comments");
  const allComments = $("#" + post).find("#all-comments");

  commentBtn.click({ param1: allComments }, function () {
    allComments.fadeToggle();
    allComments.toggleClass("undisplay-comment");
  });
}
/* ^^ standard post creation ^^ */

/* vv add parts to post vv */
function addPostPicture(post, img) {
  const imagePlace = $("#" + post).find("#blurr");
  const imageBackground = $("#" + post).find("#picture-section");

  removePostPicture(post);

  imageBackground.css({ "background-image": "URL('" + img + "')" });

  imagePlace.append('<img id="picture" src="' + img + '">');
}

function addConfiguration(post, conf, confName, confLink) {
  const cont = $("#" + post).find("#content");

  removePostConfiguration(post);

  var componentSection =
    '<div id="configuration-section" class="section">' +
    "<h5>" +
    confName +
    "</h5>" +
    '<ul class = "component-list" >';

  for (var i = 0; i < 5; ++i)
    componentSection = componentSection.concat(
      '<li style="position: relative; z-index:' +
        i +
        '!important;">' +
        conf[i][0] +
        ': <a href="' +
        conf[i][1] +
        '">' +
        conf[i][1] +
        "</a></li>"
    );

  componentSection = componentSection.concat(
    '<li style="position: relative; z-index: 6!important;"><a href="' +
      confLink +
      '"> Vedi la configurazione >></a></li>' +
      "</ul>" +
      "</div>"
  );

  cont.append(componentSection);
}

function addPoll(post, opz1, opz1votes, opz2, opz2votes, currentChoice) {
  const cont = $("#" + post).find("#content");

  removePool(post);

  cont.append(
    '<div id="poll-section" class="section">' +
      '<div id="poll">' +
      '<div id="option-left" class="poll-option h5 position-relative" onclick="choiceLeft(\'' +
      post +
      "')\">" +
      '<span class="centered-text">' +
      opz1 +
      "</span>" +
      '<span id="option-left-votes" class="position-absolute bottom-0 end-0 badge rounded-pill bg-secondary" style="transform: translate(-20px);">' +
      opz1votes +
      "</span>" +
      "</div>" +
      '<div id="option-right" class="poll-option h5 position-relative" onclick="choiceRight(\'' +
      post +
      "')\">" +
      '<span class="centered-text">' +
      opz2 +
      "</span>" +
      '<span id="option-right-votes" class="position-absolute bottom-0 start-0 badge rounded-pill bg-secondary" style="transform: translate(20px);">' +
      opz2votes +
      "</span>" +
      "</div>" +
      "</div>" +
      "</div>"
  );

  if (currentChoice == -1) {
    cont.find("#option-left-votes").css("display", "none");
    cont.find("#option-right-votes").css("display", "none");
  }

  setChooseToPoll(post, currentChoice);
}

function setChooseToPoll(post, choice) {
  const cont = $("#" + post).find("#content");

  if (choice == 1)
    cont
      .find("#option-left")
      .css("background-color", "var(--color-optionHover)");
  if (choice == 2)
    cont
      .find("#option-right")
      .css("background-color", "var(--color-optionHover)");

  if (choice != -1) {
    cont.find("#option-left-votes").css("display", "inline");
    cont.find("#option-right-votes").css("display", "inline");

    cont.find("#option-left").css("pointer-events", "none");
    cont.find("#option-right").css("pointer-events", "none");
  }
}

/* ^^ add parts to post ^^ */

/* vv remove parts to post vv */

function removePool(post) {
  const cont = $("#" + post).find("#content");
  const poll = cont.find("#poll-section");

  if (poll.length) {
    poll.remove();
  }
}

function removePostPicture(post) {
  const imagePlace = $("#" + post).find("#blurr");
  const imageBackground = $("#" + post).find("#picture-section");

  imageBackground.css({ "background-image": "" });

  if (imagePlace.children().length > 0) {
    imagePlace.empty();
  }
}

function removePostConfiguration(post) {
  const cont = $("#" + post).find("#content");
  const conf = cont.find("#configuration-section");

  if (conf.length) {
    conf.remove();
  }
}

/* ^^ remove parts to post ^^ */

// vv load posts vv
function showPostInPage(response, postSection) {
  for (var i = 0; i < response.length; ++i) {
    var post = response[i];
    var postName = "post" + post.idPost;

    postSection.append(
      createPostStructure(
        postName,
        post.postUser.username,
        post.postUser.avatar,
        post.postTitle,
        post.postDescription
      )
    );

    addLikeListener(postName);
    initialLikeSet(postName, post.like, post.likeCount);

    //addCommentListener(postName);

    if (post.postPicture != "" && post.postPicture != null)
      addPostPicture(postName, post.postPicture);

    if (post.pollOptionOne != "" && post.pollOptionTwo != "")
      addPoll(
        postName,
        post.pollOptionOne,
        post.pollOptionOneChoices,
        post.pollOptionTwo,
        post.pollOptionTwoChoices,
        post.choice
      );
  }
}

function putPlaceHolder(postSection) {
  for (var i = 0; i < 2; ++i)
    postSection.append(
      '<div class="load-wraper">' + '<div class="activity"></div>' + "</div>"
    );
}
// ^^ load posts ^^

// vv post interactions vv

// vv like functions vv
function initialLikeSet(post, isLiked, likeCount) {
  if (isLiked) {
    var likeIcon = $("#" + post).find("#like-icon");

    likeIcon.removeClass("far");
    likeIcon.addClass("fas");
  }

  $("#" + post)
    .find("#like-count")
    .html(likeCount);
  haveLike(post);
}

function haveLike(post) {
  var likeCounter = $("#" + post).find("#like-count");

  if (likeCounter.html() > 0 || likeCounter.html() > 100) {
    likeCounter.css("display", "inline");

    if (likeCounter.html() > 99) likeCounter.html("+99");

    return;
  }

  if (likeCounter.html() <= 0) likeCounter.css("display", "none");
}
// ^^ like functions ^^

// vv poll functions vv
function choiceLeft(post) {
  var chose = new FormData();
  chose.append("choice", 1);
  pollListener(chose, post);
}

function choiceRight(post) {
  var chose = new FormData();
  chose.append("choice", 2);
  pollListener(chose, post);
}

function pollListener(choose, post) {
  const path = "/chooseToPoll/" + calculatePostId(post);

  setChooseToPoll(post, choose.get("choice"));
  increaseChosenPoll(post, choose.get("choice"));

  askToServer(path, choose).then(function (response) {
    if (!response) showPopUp("Hai già votato per questo sondaggio", "error");
  });
}

function increaseChosenPoll(post, choice) {
  const cont = $("#" + post).find("#content");

  if (choice == 1)
    cont
      .find("#option-left-votes")
      .html(parseInt(cont.find("#option-left-votes").html()) + 1);
  if (choice == 2)
    cont
      .find("#option-right-votes")
      .html(parseInt(cont.find("#option-right-votes").html()) + 1);
}

// ^^ poll functions ^^

// ^^ post interactions ^^
