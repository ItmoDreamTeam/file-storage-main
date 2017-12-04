function auth(user, password) {
    var tok = user + ':' + password;
    var hash = btoa(tok);
    return "Basic " + hash;
}

function go() {
    var user = document.getElementById('last_name').value;
    var password = document.getElementById('password').value;
    var line = auth(user, password);
    $.ajax({
        type: "GET",
        url: "/api/profile",
        dataType: 'json',
        async: true,
        data: '',

        beforeSend: function (xhr) {
            xhr.setRequestHeader('Authorization', line);
        },
        success: function () {
            window.open('feed.html', '_self', false);
            sessionStorage.setItem("login", user);
            sessionStorage.setItem("password", password);
            sessionStorage.setItem("line", line);
        },
        error: function () {
            alert("No such login or password");
        }
    });
}
