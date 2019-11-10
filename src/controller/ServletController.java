package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import dao.ConnectionFactory;
import model.Usuario;

@WebServlet("/controller.do")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Usuario user = (Usuario) request.getSession().getAttribute("logado");
		 request.setCharacterEncoding("UTF-8");
		 String command = request.getParameter("command");
		 try {
			if (!command.equals("FazerLogin") && user.getEmail() == null) {
				response.sendRedirect("index.jsp");
			} else {

				try {
					request.setCharacterEncoding("UTF-8");
					Command comando = (Command)Class.forName("command."+request.getParameter("command")).newInstance();
					comando.executar(request, response);
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request,response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request,response);
	}
	
	@Override
	public void init(){
		try {
			ConnectionFactory.obterConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy(){
		try {
			ConnectionFactory.fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
