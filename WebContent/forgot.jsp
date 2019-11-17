<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Esqueci a senha</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/register.css" rel="stylesheet">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
</head>

<body>
	<div class="wrapper fadeInDown">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	
	    <!-- Icon -->
	    <div class="fadeIn first">
	      <h1 class="pt-4 pb-4">Esqueci a senha</h1>
	      <h3>${message}</h3>
	    </div>
	
	    <!-- Login Form -->
	    <form action="/Mailer/controller.do" method="POST">
	   		<input type="text" name="command" value="Forgot" class="hidden">
	      	<input type="text" id="email" class="fadeIn second" name="email" placeholder="Email">
	      	<input type="submit" class="fadeIn fourth" value="Recuperar">
	    </form>
	
		<div id="formFooter" style="font-size: 15px;"	>
	      <a class="underlineHover" href="register.jsp">Criar conta</a><br><br>
	      <a class="underlineHover" href="index.jsp">Fazer login</a>
	    </div>
	  </div>
	</div>
</body>

</html>