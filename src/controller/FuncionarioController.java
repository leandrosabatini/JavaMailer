package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Funcionario;
import service.FuncionarioService;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/funcionarios/Funcionario.do")
public class FuncionarioController extends HttpServlet {
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

		FuncionarioService cs = new FuncionarioService();
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(pNome);
		funcionario.setEmail(pEmail);
		funcionario.setEmpresaId(1);
		funcionario.setId(cs.criar(funcionario));
		
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
