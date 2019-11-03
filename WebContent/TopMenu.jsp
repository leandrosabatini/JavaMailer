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
                                    <span><%=user.getNome()%></span>
                                    <p class="text-muted small">
                                       <%=user.getEmail()%>
                                    </p>
                                    <div class="divider">
                                    </div>
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