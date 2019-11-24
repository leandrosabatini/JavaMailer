<%@page import="model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) request.getSession().getAttribute("logado");
%>
<div class="row">
    <header>
        <div class="col-md-7">
            <nav class="navbar-default pull-left">
                <div class="navbar-header" style="background: white;">
                    <button type="button" class="navbar-toggle collapsed toogle-menu-click" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
            </nav>
        </div>
        <script type="text/javascript">
        	$('.toogle-menu-click').click(function(e) {
        		$('.toogle-menu').toggleClass('hidden-xs');
        	})
        </script>
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
                                    <span><%=user.getNome()%></span>
                                    <p class="text-muted small">
                                       <%=user.getEmail()%>
                                    </p>
                                    <div class="divider">
                                    </div>
                                    <a href="/Mailer/controller.do?command=EditarPerfil" class="view btn-sm active">Editar perfil</a>
                                    <a href="/Mailer/controller.do?command=FazerLogout" class="view btn-sm active">Sair</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </header>
</div>