<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 12/25/16
  Time: 5:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Title</title>
    <!--<script type="text/javascript" src="jquery-1.3.2.min.js"></script>-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <%--<script src="js/2.5.3-crypto-md5.js"></script>--%>

    <script>

    function checkPars() {
        var login = $("#login").val();
        var firstname = $("#firstname").val();
        var lastname = $("#lastname").val();
        var password = $("#password").val();
        var cpassword = $("#password_again").val();

        if (login == ''  || password    == '' || cpassword == '') {
            alert("Please fill all fields...!!!!!!");
        } else if (!jQuery.isEmptyObject(password) && (password.length) < 6)  {
            alert("Password should atleast 8 character in length...!!!!!!");
        } else if (password.localeCompare(cpassword) != 0) {
            alert("Your passwords don't match. Try again?");
        } else {
            document.getElementById('registration').submit();
        }

    }
    /*
        $(document).ready(function() {
            $("#register").click(function() {
                var login = $("#login").val();
//                var email = $("#email").val();
                var firstname = $("#firstname").val();
                var lastname = $("#lastname").val();
                var password = $("#password").val();
                var cpassword = $("#password_again").val();
                console.log(password);
                console.log(cpassword);
                console.log(login);
                if (login == ''  || password    == '' || cpassword == '') {
                    alert("Please fill all fields...!!!!!!");
                } else if (!jQuery.isEmptyObject(password) && (password.length) < 6)  {
                    alert("Password should atleast 8 character in length...!!!!!!");
                } else if (password.localeCompare(cpassword) != 0) {
                    alert("Your passwords don't match. Try again?");
                } else {
                    $.post("/register", {
                        login: login,
                        firstname:firstname,
                        lastname:lastname,
                        password: password
                    }, function(data) {
                        if (data == 'You have Successfully Registered.....') {
//                            $("form")[0].reset();


                        }
//                        alert(data);
                        window.location.replace("/");
                    });
                }
            });
        });
*/
    </script>

</head>
<body>
<form method="post" action="/register" id="registration">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <fieldset>
        <legend>Регистрация</legend>
        <table>
            <tr>
                <td>
                    <label>Логин пользователя</label>
                </td>
                <td>
                    <input type="text" name="login" id="login" placeholder="Введите свой логин"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Пароль</label>
                </td>
                <td>
                    <input type="password" name="password" id="password" placeholder="Введите пароль"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Подтвердите пароль</label>
                </td>
                <td>
                    <input type="password" name="password_again" id="password_again" placeholder="Введите повторно пароль"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Имя пользователя</label>
                </td>
                <td>
                    <input type="text" name="firstname" id="firstname" placeholder="Введите имя пользователя"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Фамилия пользователя</label>
                </td>
                <td>
                    <input type="text" name="lastname" id="lastname" placeholder="Введите фамилию пользователя"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="button" name="register" id="register" value="Register" onclick="checkPars()">

                    <!--<button type="submit" id="register" name="submit" value="register">Подтвердить</button>-->
                </td>
            </tr>
        </table>
    </fieldset>

    <!--<table>-->
    <!--<tr>-->
    <!--<td>-->
    <!--<input type="text" placeholder="Введите логин" name="login">-->
    <!--</td>-->
    <!--</tr>-->
    <!--<tr>-->
    <!--<td>-->
    <!--<input type="password" placeholder="Введите пароль" name="password">-->
    <!--</td>-->
    <!--</tr>-->
    <!--<tr>-->
    <!--<td>-->
    <!--<input type="text" placeholder="Введите Имя" name="firstname">-->
    <!--</td>-->
    <!--</tr>-->
    <!--<tr>-->
    <!--<td>-->
    <!--<input type="text" placeholder="Введите Фамилию" name="lastname">-->
    <!--</td>-->
    <!--</tr>-->
    <!--<tr>-->
    <!--<td>-->
    <!--<button type="submit" value="suces" name="registration">Зарегистрироваться</button>-->
    <!--</td>-->
    <!--</tr>-->
    <!--</table>-->

</form>
</body>
</html>
