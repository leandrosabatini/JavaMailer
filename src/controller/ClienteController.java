package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import service.ClienteService;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/clientes/Cliente.do")
public class ClienteController extends HttpServlet {
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
//		String pEmpresa = request.getParameter("empresa");

		ClienteService cs = new ClienteService();
		Cliente cliente = new Cliente();
		cliente.setNome(pNome);
		cliente.setEmail(pEmail);
		cliente.setEmpresaId(1);
		cliente.setId(cs.criar(cliente));
		
//		request.setAttribute("usuario", usuario);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		
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
