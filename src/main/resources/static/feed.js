function show() {
    if (sessionStorage.getItem("login") == null) {
        window.open('index.html', '_self', false);
    } else {
        document.getElementById("login").display = "block";
        document.getElementById("login").innerHTML = sessionStorage.getItem("login");
    }
}

function exit() {
    sessionStorage.clear();
    window.open('index.html', '_self', false);
}

function createimage(name) {
    var input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("id", name);
    var parent = document.getElementById("right");
    parent.appendChild(input);
    input.setAttribute("value", name);
}

function getimage() {
    $.ajax({
        type: "GET",
        url: "/api/profile",
        dataType: 'json',
        async: true,
        data: '',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', sessionStorage.getItem('line'));
        },
        complete: function (xhr) {
            var pics = JSON.parse(xhr.responseText);
            for (var i = 0; i < pics.files.length; i++) {
                createimage(pics.files[i].name);
            }
            document.getElementById('number').innerHTML = pics.files.length.toString();
        }
    });
}

function openp() {
    if (document.getElementById('changefield').style.display == 'block') {
        document.getElementById('upload').style.display = 'block';
        document.getElementById('changefield').style.display = 'none';
    } else {
        document.getElementById('changefield').style.display = 'block';
        document.getElementById('upload').style.display = 'none';
    }
}

function auth(user, password) {
    var tok = user + ':' + password;
    var hash = btoa(tok);
    return "Basic " + hash;
}

function savepassword() {
    var line = sessionStorage.getItem('line');
    $.ajax({
        async: true,
        crossDomain: true,
        method: "PUT",
        url: "/api/profile",
        data: {
            'password': document.getElementById('password').value
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', line);
        },
        success: function () {
            exit();
        },
        error: function () {
            alert("Your password didn't change");
        }
    });
}

function closeupload() {
    if (document.getElementById('upform').style.display == 'block') {
        document.getElementById('upload').style.display = 'block';
        document.getElementById('upform').style.display = 'none';
        document.getElementById('edit').classList.remove('disabled');
    } else {
        document.getElementById('upform').style.display = 'block';
        document.getElementById('upload').style.display = 'none';
        document.getElementById('edit').classList.add('disabled');
    }
}

function sendfile() {
    var line = sessionStorage.getItem('line');
    var fileSelect = document.getElementById('myfile');
    var files = fileSelect.files;
    var formData = new FormData();
    var file = files[0];
    formData.append('file', files[0]);
    $.ajax({
        async: true,
        crossDomain: true,
        method: "POST",
        processData: false,
        contentType: false,
        url: "/api/files",
        mimeType: 'multipart/form-data',
        data: formData,
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', line);
        },
        success: function () {
            exit();
        },
        error: function () {
            alert("Cant Upload File");
        }
    });
}

getimage();
show();
