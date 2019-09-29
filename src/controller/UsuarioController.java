package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empresa;
import model.Usuario;
import service.EmpresaService;
import service.UsuarioService;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/Usuario.do")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pEmail = request.getParameter("email");
		String pSenha = request.getParameter("senha");
		String pEmpresa = request.getParameter("empresa");

		EmpresaService es = new EmpresaService();
		Empresa empresa = new Empresa();
		empresa.setNome(pEmpresa);
		empresa.setId(es.criar(empresa));

		UsuarioService us = new UsuarioService();
		Usuario usuario = new Usuario();
		usuario.setNome(pNome);
		usuario.setEmail(pEmail);
		usuario.setSenha(pSenha);
		usuario.setEmpresaId(empresa.getId());
		usuario.setId(us.criar(usuario));
		
		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Usuario.jsp");
		
//		switch(acao){
//		case "Incluir":
//			cs.criar(usuario);
//			usuario = cs.carregar(usuario.getId());
//			
//			//manda parametro para o JSP via request
//			request.setAttribute("usuario", usuario);
//			dispatcher = request.getRequestDispatcher("Usuario.jsp");
//			break;
//		case "Listar":
//			ArrayList<Cliente> clientes = cs.listarTodos();
//			request.setAttribute("clientes", clientes);
//			dispatcher = request.getRequestDispatcher("ListaDeClientes.jsp");
//		}
		
		
		dispatcher.forward(request, response);
		
	}

}
