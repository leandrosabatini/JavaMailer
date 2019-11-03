package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empresa;
import service.EmpresaService;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/empresa/Empresa.do")
public class EmpresaController extends HttpServlet {
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
		String action = request.getParameter("action");
		
		switch (action) {
			case "edit":
				EmpresaService es = new EmpresaService();
				Empresa empresa = es.carregar(1);//TODO: Pegar empresa do usuário da sessão
				request.setAttribute("empresa", empresa);
				RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
				dispatcher.forward(request, response);
				break;
			case "save":
				
				break;
			default:
				RequestDispatcher dispatcher2 = request.getRequestDispatcher("view.jsp");
				dispatcher2.forward(request, response);
				break;
		}
	}
}
