<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Criar uma conta</title>

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
	      <h1 class="pt-4 pb-4">Criar uma conta</h1>
	    </div>
	
	    <!-- Login Form -->
	    <form action="Usuario.do" method="POST">
	      <input type="text" id="nome" class="fadeIn second" name="nome" placeholder="Nome">
	      <input type="text" id="email" class="fadeIn second" name="email" placeholder="Email">
	      <input type="text" id="empresa" class="fadeIn second" name="empresa" placeholder="Nome da empresa">
	      <input type="password" id="password" class="fadeIn second" name="senha" placeholder="Senha">
	      <input type="submit" class="fadeIn fourth" value="Criar conta">
	    </form>
	
		<div id="formFooter" style="font-size: 15px;"	>
	      <a class="underlineHover" class="mb-3" href="forgot.jsp">Esqueci a senha</a><br><br>
	      <a class="underlineHover" href="index.jsp">Fazer login</a>
	    </div>
	
	    <!-- Remind Passowrd -->
	    <!--
	    	    
			<c:import url="Menu.jsp"/>
		    <div id="main" class="container">
		        <h3 class="page-header">Realizar login</h3>
		        <form action="Usuario.do" method="post">
		            <div class="row">
		                <div class="form-group col-md-12">
		                    <label for="nome">Nome</label>
		                    <input type="text" class="form-control" name="nome" id="nome" required maxlength="100">
		                </div>
		            </div>
		            <div class="row">
		                <div class="form-group col-md-6">
		                    <label for="fone">E-mail</label>
		                    <input type="email" class="form-control" name="email" id="email" maxlength="100">
		                </div>
		
		                <div class="form-group col-md-6">
		                    <label for="email">Nome da empresa</label>
		                    <input type="text" class="form-control" name="empresa" id="empresa" required maxlength="100">
		                </div>
		                
		                <div class="form-group col-md-6">
		                    <label for="email">Senha</label>
		                    <input type="password" class="form-control" name="senha" id="senha" required maxlength="100">
		                </div>
		            </div>
		            <hr />
		            <div id="actions" class="row">
		                <div class="col-md-12">
		                    <button type="submit" class="btn btn-primary" name="acao" value="Criar">Salvar</button>
		                    <a href="index.html" class="btn btn-default">Cancelar</a>
		                </div>
		            </div>
		        </form>
		    </div>
		    <script src="js/jquery.min.js"></script>
		    <script src="js/bootstrap.min.js"></script>
	    -->
	
	  </div>
	</div>
</body>

</html>