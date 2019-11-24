package command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import service.UsuarioService;

public class EditarPerfil implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

		UsuarioService us = new UsuarioService();
		
		try {
			if (request.getParameter("nome").length() > 0) {
				user.setNome(request.getParameter("nome"));
				user.setEmail(request.getParameter("email"));

				try {
					if (request.getParameter("senha").length() > 0) {
						user.setSenha(request.getParameter("senha"));
					}
				} catch (Exception e) {}

				us.atualizar(user);
				
				response.sendRedirect("/Mailer/controller.do?command=ViewEmpresa");
			} else {
				request.setAttribute("user", user);

				RequestDispatcher dispatcher = request.getRequestDispatcher("editarperfil.jsp");

				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getRequestDispatcher("editarperfil.jsp");

			dispatcher.forward(request, response);
		}
		
		
	}

}
