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
    if (validateUsername() && validatePassword() && validateCofPassword() && validateEmail()) {
        $("#regUser").submit();
    } else {
        alert("请完善信息")
    }
}

function validateUsername() {
    $('#user').removeAttr('style')
    if ($('#user').val() == "") {
        $('#user').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font id='validateUser' color='red'><b>×用户名不能为空</b></font>");
        return false;
    } else if ($('#user').val() != "") {
        $("#validateUser").remove();
        $('#userCue').html("快速注册请注意格式")
        var username = $('#user').val();
        $.ajax({
            type: "POST",
            url: "/register/validateusername.html",
            data: {"username": username},
            success: function (msg) {
                var b = $.trim(msg) == "success";
                if (b == false) {
                    $('#userCue').html("<font id='validateUser' color='red'><b>×用户已存在</b></font>");
                    return false;
                }
            }
        });
    }
    ;
    if ($('#user').val().length < 4 || $('#user').val().length > 16) {
        $('#user').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
        return false;
    }
    ;
}

function validatePassword() {
    $("#validatePass").remove();
    $('#userCue').html("快速注册请注意格式")

    if ($('#passwd').val().length < pwdmin) {
        $('#passwd').focus();
        $('#userCue').html("<font id='validatePass' color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
        return false;
    } else if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font id='validatecofPass' color='red'><b>×两次密码不一致！</b></font>");
        return false;
    }
}

function validateCofPassword() {
    $("#validatePass").remove();
    $('#userCue').html("快速注册请注意格式")
    if ($('#passwd2').val() != $('#passwd').val()) {
        $('#passwd2').focus();
        $('#userCue').html("<font id='validatecofPass' color='red'><b>×两次密码不一致！</b></font>");
        return false;
    } else if ($('#passwd2').val() == $('#passwd').val()) {
        $("#validatecofPass").remove();
        $('#userCue').html("快速注册请注意格式")
        return true;
    }
}

function validateEmail() {
    $("#validateEmail").remove();
    $('#userCue').html("快速注册请注意格式");
    $('#email').removeAttr('style');
    if ($("#email").val() == "") {
        $('#email').focus().css({
            border: "1px solid red",
            boxShadow: "0 0 2px red"
        });
        $('#userCue').html("<font id='validateEmail' color='red'><b>×邮箱不能为空</b></font>");
        return false;
    } else if ($("#email").val() != '') {
        var email = $("#email").val();
        var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(email)) {
            $('#userCue').html("<font id='validateEmail' color='red'><b>×邮箱格式不正确！</b></font>");
            return false;
        }
    }
}