function CheckForAuthorization() {
    if (sessionStorage.getItem("line") === null) {
        window.open('index.html', '_self', false);
    }
    else {
        document.getElementById("login").display = "block";
        document.getElementById("login").innerHTML = "login: " + sessionStorage.getItem("login");
    }
}

function CreateList(name, id) {
    var input = document.createElement('li');
    input.setAttribute("id", id);
    input.setAttribute('class', "collection-item");
    var parent = document.getElementById("collection");
    var download = document.createElement('a');
    download.setAttribute("class", "waves-effect waves-light btn");
    download.setAttribute('id', 'download');
    input.innerHTML = name;
    download.innerHTML = "download";
    download.setAttribute('onclick', 'DownloadImage(' + id + ')');
    parent.appendChild(input);
    input.appendChild(download);
}

function GetImageListFromServer() {
    $.ajax({
        type: "GET",
        url: API_ROOT + "/profile",
        dataType: 'json',
        async: true,
        data: '',
        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', sessionStorage.getItem('line'));
        },
        complete: function (xhr) {
            var pics = JSON.parse(xhr.responseText);
            console.log(pics);
            for (var i = 0; i < pics.files.length; i++) {
                CreateList(pics.files[i].name, pics.files[i].id);
            }
            document.getElementById('number').innerHTML = "Number of pics: " + pics.files.length.toString();
        }
    });
}

function OpenAndClosePasswordChangeForm() {
    if (document.getElementById('changefield').style.display === 'block') {
        document.getElementById('upload').style.display = 'block';
        document.getElementById('changefield').style.display = 'none';
    }
    else {
        document.getElementById('changefield').style.display = 'block';
        document.getElementById('upload').style.display = 'none';
    }
}

function SaveNewPassword() {
    var line = sessionStorage.getItem('line');
    $.ajax
    ({
        async: true,
        crossDomain: true,
        method: "PUT",
        url: API_ROOT + "/profile",
        data: {
            'password': document.getElementById('password').value
        },

        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', line);
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("Your password didn't change");
        }
    });
}

function UploadImageFile() {
    var line = sessionStorage.getItem('line');
    var fileSelect = document.getElementById('myfile');
    var files = fileSelect.files;
    var formData = new FormData();
    formData.append('file', files[0]);
    $.ajax
    ({
        async: true,
        crossDomain: true,
        method: "POST",
        processData: false,
        contentType: false,
        url: API_ROOT + "/files",
        mimeType: 'multipart/form-data',
        data: formData,

        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', line);
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("Cant Upload File");
        }
    });
}

function OpenAndCloseUploadImageForm() {
    if (document.getElementById('upform').style.display === 'block') {
        document.getElementById('upload').style.display = 'block';
        document.getElementById('upform').style.display = 'none';
        document.getElementById('edit').classList.remove('disabled');
    }
    else {
        document.getElementById('upform').style.display = 'block';
        document.getElementById('upload').style.display = 'none';
        document.getElementById('edit').classList.add('disabled');
    }
}

function ExitFromAccount() {
    sessionStorage.clear();
    location.reload();
}

CheckForAuthorization();
GetImageListFromServer();
