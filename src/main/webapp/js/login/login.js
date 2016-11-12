$(function () {

    $('#switch_qlogin').click(function () {
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left: '0px', width: '70px'});
        $('#qlogin').css('display', 'none');
        $('#web_qr_login').css('display', 'block');

    });
    $('#switch_login').click(function () {

        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left: '154px', width: '70px'});

        $('#qlogin').css('display', 'block');
        $('#web_qr_login').css('display', 'none');
    });
    if (getParam("a") == '0') {
        $('#switch_login').trigger('click');
    }

});

function logintab() {
    scrollTo(0);
    $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
    $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
    $('#switch_bottom').animate({left: '154px', width: '96px'});
    $('#qlogin').css('display', 'none');
    $('#web_qr_login').css('display', 'block');

}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) {
    var params = location.search.substr(1); // 获取参数 平且去掉？
    var ArrParam = params.split('&');
    if (ArrParam.length == 1) {
        //只有一个参数的情况
        return params.split('=')[1];
    }
    else {
        //多个参数参数的情况
        for (var i = 0; i < ArrParam.length; i++) {
            if (ArrParam[i].split('=')[0] == pname) {
                return ArrParam[i].split('=')[1];
            }
        }
    }
}


var reMethod = "GET",
    pwdmin = 6;

function submitForm() {
    //alert(passwordstauts && cofpasswordstatus)
    if (usernamestatus && passwordstauts && cofpasswordstatus && emailstatus) {
        $("#regUser").submit();
    }else {
        alert("信息填写有误")
    }

    //alert("zzzz")
    /*if (true){
     validateUsername() ;
     validatePassword() ;
     validateCofPassword() ;
     validateEmail();
     alert("aaaa")
     }*/
    //$("#regUser").submit();
    //alert("请完善信息")
}
var usernamestatus = false;
function validateUsername() {
    $('#user').removeAttr('style')
    usernamestatus = true;
    if ($('#user').val() == "") {
        $('#user').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font id='validateUser' color='red'><b>×用户名不能为空</b></font>");
        usernamestatus = false;
    } else if ($('#user').val() != "") {
        $("#validateUser").remove();
        $('#userCue').html("快速注册请注意格式")
        usernamestatus = true;
        var username = $('#user').val();
        $.ajax({
            type: "POST",
            url: "/register/validateusername.html",
            data: {"username": username},
            success: function (msg) {
                var b = $.trim(msg) == "success";
                if (b == false) {
                    $('#userCue').html("<font id='validateUser' color='red'><b>×用户已存在</b></font>");
                    usernamestatus = false;
                }
            }
        });
    }
    if ($('#user').val().length < 4 || $('#user').val().length > 16) {
        $('#user').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
        usernamestatus = false;
    }
}

var passwordstauts = false;
function validatePassword() {
    $("#validatePass").remove();
    $('#userCue').html("快速注册请注意格式")
    passwordstauts = true;
    if ($('#passwd').val().length < pwdmin) {
        $('#passwd').focus();
        $('#userCue').html("<font id='validatePass' color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
        passwordstauts = false;
    } else if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font id='validatecofPass' color='red'><b>×两次密码不一致！</b></font>");
        passwordstauts = false;
    }
}

var cofpasswordstatus = false;
function validateCofPassword() {
    $("#validatePass").remove();
    $('#userCue').html("快速注册请注意格式")
    cofpasswordstatus = true;
    passwordstauts = true;
    if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font id='validatecofPass' color='red'><b>×两次密码不一致！</b></font>");
        cofpasswordstatus = false;
    } else if ($('#passwd2').val() == $('#passwd').val()) {
        $("#validatecofPass").remove();
        $('#userCue').html("快速注册请注意格式")
        cofpasswordstatus = true;
    }
}

var emailstatus = false;
function validateEmail() {
    $("#validateEmail").remove();
    $('#userCue').html("快速注册请注意格式");
    $('#email').removeAttr('style');
    emailstatus = true;
    if ($("#email").val() == "") {
        $('#email').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font id='validateEmail' color='red'><b>×邮箱不能为空</b></font>");
        emailstatus = false;
    } else if ($("#email").val() != '') {
        var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(email)) {
            $('#userCue').html("<font id='validateEmail' color='red'><b>×邮箱格式不正确！</b></font>");
            emailstatus = false;
        }
        $.ajax({
            type: "POST",
            url: "/register/validateemail.html",
            data: {"email": email},
            success: function (msg) {
                var b = $.trim(msg) == "success";
                if (b == false) {
                    $('#userCue').html("<font id='validateEmail' color='red'><b>×邮箱已注册</b></font>");
                    emailstatus = false;
                }
            }
        });
    }
}

$(function () {
    $("#dl").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "/register/login.html",
            data: {"username": username, "password": password},
            success: function (msg) {
                if (msg) {
                    window.location.href = "/admin/index.html";
                } else {
                    alert("信息填写有误")
                }
            }
        });
    });
})