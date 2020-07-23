<%-- 
    Document   : verify
    Created on : Jul 19, 2020, 10:03:51 PM
    Author     : Yiorgos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <span>We already send the verification code to your email!</span>
        
        <form action="VerifyCode" method="post"> 
            <input type="text" name="authcode" > 
            <input type="submit" value="Verify" >
        </form>
    </body>
</html>
