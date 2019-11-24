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
    
    <script src="/Mailer/js/jquery-1.11.1.min.js"></script>
   	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
   	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/select2@4.0.12/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.0.12/dist/js/select2.min.js"></script>
	
	<!-- Include Editor style. -->
	<link href='https://cdn.jsdelivr.net/npm/froala-editor@3.0.6/css/froala_editor.pkgd.min.css' rel='stylesheet' type='text/css' />
	
	<!-- Include JS file. -->
	<script type='text/javascript' src='https://cdn.jsdelivr.net/npm/froala-editor@3.0.6/js/froala_editor.pkgd.min.js'></script>
</head>

<body>
	<style>
		#insertFile-1, .fr-quick-insert, .second-toolbar #logo {
			display: none;
		}
	</style>
    <div class="container-fluid display-table" style="padding: 0;height: 100vh;">
        <div class="row display-table-row" style="height: 100vh;">
            <div class="col-md-2 col-sm-1 toogle-menu hidden-xs  display-table-cell v-align box" id="navigation">
                <div class="navi">
                    <ul>
                        
                        <li><a href="/Mailer/controller.do?command=ViewEmpresa"><i class="fa fa-tasks" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Dashboard</span></a></li>
                        <li><a href="/Mailer/controller.do?command=ListarFuncionarios"><i class="fa fa-bar-chart" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Funcionários</span></a></li>
                        <li><a href="/Mailer/controller.do?command=ListarClientes"><i class="fa fa-user" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Clientes</span></a></li>
                        <li class="active"><a href="/Mailer/controller.do?command=ListarEmails"><i class="fa fa-calendar" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Emails</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align" style="max-height: 99vh;overflow-y: scroll;">
            	<c:import url="../TopMenu.jsp"/>

                <div class="cliente-dashboard">
                	<div class="col-12" style="margin: 20px 0px;">
                    	<span class="h1">Enviar novo email</span>
                    </div>
                    <div class="col-12">
						<div class="">
							<form action="/Mailer/controller.do" method="POST">
								<input class="hidden" type="text" name="command" value="EnviarEmail">
							
								<div class="form-group">
							    	<label for="nome">Assunto</label>
							    	<input type="text" class="form-control" name="assunto" id="assunto" placeholder="Newsletter">
							  	</div>
							  	<div class="form-group">
							    	<label for="nome">Clientes</label>
							    	<select class="form-control select2" name="clientes" multiple>
                                       	<option value='all'>Selecionar todos</option> 
							    		<c:forEach var="cliente" items="${clientes}">
                                        	<option value='${cliente.id}'>${cliente.nome} (${cliente.email})</option> 
                                        </c:forEach>
							    	</select>
							  	</div>
							  	
							  	<div class="form-group">
							    	<label for="nome">Funcionários</label>
							    	<select class="form-control select2" name="funcionarios" multiple>
                                       	<option value='all'>Selecionar todos</option> 
							    		<c:forEach var="funcionario" items="${funcionarios}">
                                        	<option value='${funcionario.id}'>${funcionario.nome} (${funcionario.email})</option> 
                                        </c:forEach>
							    	</select>
							  	</div>
							  	<div class="form-group">
							    	<label for="nome">Conteúdo</label>
							    	<textarea rows="10" cols="30" class="form-control" name="conteudo" id="conteudo"></textarea>
							  	</div>
							  	<div class="form-group">
							  		<a class="btn btn-danger" type="submit" href="/Mailer/controller.do?command=ListarEmails">Voltar</a>
							  		<button class="btn btn-success" type="submit">Enviar</button>
							  	</div>
							</form>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
    <script>
    	$('.select2').select2()
	    $('.select2').change(function(e) {
	    	var $this = $(this);
		    var prevValue = $(this).val();
			prevValue.forEach(function(element) {
		    	if (element == "all") {
		    		var allOptions = [];
		    		$this.children().each(function(i) {
		    			var val = $(this).val();
		    			if (val != "all") {
		    				allOptions.push(val);
		    			}
		    		})
		    		prevValue = allOptions;
		    		$this.val(prevValue).trigger('change');
				}
			})
		 	
		})
	    
	    
				
		

    	//$('select.your-select option').attr('selected', true).parent().trigger('change')
	    new FroalaEditor('textarea', {
	    	fileUpload: false,
	    	imageUpload: false,
	    	videoUpload: false
	    });
  </script>
</body>

</html>