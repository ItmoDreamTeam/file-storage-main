function OpenEnterForm() {
    document.getElementById('enterform').style.display = 'block';
    document.getElementById('registrationform').style.display = 'none';
}

function OpenRegistrationForm() {
    document.getElementById('enterform').style.display = 'none';
    document.getElementById('registrationform').style.display = 'block';
}

function CodeForAuthorization(user, password) {
    try {
        var tok = user + ':' + password;
        var hash = btoa(tok);
        return "Basic " + hash;
    }
    catch (err) {
        return "error";
    }
}

function AuthorizationToSystem() {
    var user = document.getElementById('last_name').value;
    var password = document.getElementById('password').value;
    var line = CodeForAuthorization(user, password);
    if (line === "error") {
        alert("Use only latin letters");
        document.getElementById("animation").innerHTML = "sentiment_very_dissatisfied";
    }
    else {
        $.ajax
        ({
            type: "GET",
            url: API_ROOT + "/profile",
            dataType: 'json',
            async: true,
            data: '',

            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', line);
            },
            success: function () {
                window.open('feed.html', '_self', false);
                sessionStorage.setItem("login", user);
                sessionStorage.setItem("line", line);
            },
            error: function (xhr) {
                alert("No such login or password");
                document.getElementById('animation').innerHTML = "sentiment_very_dissatisfied";
            }
        });
    }
}

function Registration() {
    $.ajax
    ({
        type: 'POST',
        url: API_ROOT + '/signup',
        async: true,
        crossDomain: true,
        data: {
            'username': document.getElementById('new_login').value,
            'password': document.getElementById('newpassword').value
        },

        beforeSend: function (xhr) {
            xhr.setRequestHeader('content-type', 'application/x-www-form-urlencoded');
        },
        success: function () {
            alert("created")
        },
        error: function () {
            alert("Not created");
        }
    });
}
