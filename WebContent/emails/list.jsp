<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Emails</title>

    <link href="../css/style.css" rel="stylesheet">
    
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
   	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container-fluid display-table" style="padding: 0;">
        <div class="row display-table-row" style="height: 100vh;">
            <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" id="navigation">
                <div class="navi">
                    <ul>
                        <li><a href="/Mailer/dashboard.jsp"><i class="fa fa-home" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Dashboard</span></a></li>
                        <li><a href="/Mailer/empresa/view.jsp"><i class="fa fa-tasks" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Empresa</span></a></li>
                        <li><a href="/Mailer/funcionarios/list.jsp"><i class="fa fa-bar-chart" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Funcionários</span></a></li>
                        <li><a href="/Mailer/clientes/list.jsp"><i class="fa fa-user" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Clientes</span></a></li>
                        <li class="active"><a href="/Mailer/emails/list.jsp"><i class="fa fa-calendar" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Emails</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align">
                <!--<button type="button" class="slide-toggle">Slide Toggle</button> -->
                <div class="row">
                    <header>
                        <div class="col-md-7">
                            <nav class="navbar-default pull-left">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                            </nav>
                        </div>
                        <div class="col-md-5">
                            <div class="header-rightside">
                                <ul class="list-inline header-top pull-right">                                    
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Perfil
                                            <b class="caret"></b>
                                        </a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <div class="navbar-content">
                                                    <span>Leandro</span>
                                                    <p class="text-muted small">
                                                        leandro.sabatini31@gmail.com
                                                    </p>
                                                    <div class="divider">
                                                    </div>
                                                    <a href="index.jsp" class="view btn-sm active">Sair</a>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </header>
                </div>
                <div class="cliente-dashboard">
                	<div class="col-12" style="margin: 20px 0px;">
                    	<span class="h1">Emails</span> <a class="btn btn-sm btn-success" style="margin-top: -15px; margin-left: 20px;" href="new.jsp"><i class="fa fa-plus"></i></a>
                    </div>
                    <div class="col-12">
						<div class="">
							<div class="table-responsive table-bordered movie-table">
					            <table class="table">
				                  	<thead>
					                  	<tr class= "movie-table-head">
					                      	<th>ID</th>
					                      	<th>Assunto</th>
					                      	<th>Clientes</th>
					                      	<th>Funcionários</th>
					                  	</tr>
					              	</thead>   
					              	<tbody>
				                		<tr class= "dark-row">
						                    <td>#1</td>
						                    <td>Lançamento novo livro</td>
						                    <td>lucas@gmail.com, leandro@gmail.com, joao@gmail.com</td>
						                    <td>adriana@gmail.com, marcos@gmail.com, eduardo@gmail.com</td>
					                	</tr>  
					              	</tbody>
					            </table>
				            </div>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>