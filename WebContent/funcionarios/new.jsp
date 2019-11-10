<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>

    <link href="/Mailer/css/style.css" rel="stylesheet">
    
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
   	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container-fluid display-table" style="padding: 0;height: 100vh;">
        <div class="row display-table-row" style="height: 100vh;">
            <div class="col-md-2 col-sm-1 toogle-menu hidden-xs  display-table-cell v-align box" id="navigation">
                <div class="navi">
                    <ul>
                        <li><a href="/Mailer/dashboard.jsp"><i class="fa fa-home" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Dashboard</span></a></li>
                        <li><a href="/Mailer/controller.do?command=ViewEmpresa"><i class="fa fa-tasks" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Empresa</span></a></li>
                        <li class="active"><a href="/Mailer/controller.do?command=ListarFuncionarios"><i class="fa fa-bar-chart" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Funcionários</span></a></li>
                        <li><a href="/Mailer/controller.do?command=ListarClientes"><i class="fa fa-user" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Clientes</span></a></li>
                        <li><a href="/Mailer/controller.do?command=ListarEmails"><i class="fa fa-calendar" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Emails</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align">
                <c:import url="../TopMenu.jsp"/>
                
                <div class="cliente-dashboard">
                	<div class="col-12" style="margin: 20px 0px;">
                    	<span class="h1">${title}</span>
                    </div>
                    <div class="col-12">
						<div class="">
							<form action="/Mailer/controller.do" method="POST">
								<input class="hidden" type="text" name="id" value='${funcionario.id }'>
								<input class="hidden" type="text" name="command" value="EditarFuncionario">
								<div class="form-group">
							    	<label for="nome">Nome</label>
							    	<input type="text" value='${funcionario.nome }' class="form-control" name="nome" id="nome" placeholder="Leandro Sabatini">
							  	</div>
							  	<div class="form-group">
							    	<label for="nome">Email</label>
							    	<input type="text" value='${funcionario.email }' class="form-control" name="email" id="email" placeholder="exemplo@dominio.com.br">
							  	</div>
							  	<div class="form-group">
							  		<a class="btn btn-danger" type="submit" href="/Mailer/controller.do?command=ListarFuncionarios">Voltar</a>
							  		<button class="btn btn-success" type="submit">Salvar</button>
							  	</div>
							</form>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>