package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class FazerLogin implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		UsuarioService us = new UsuarioService();

		if (us.validar(usuario)) {
			usuario = us.carregarByEmail(email);
			HttpSession session = request.getSession();
			session.setAttribute("logado", usuario);
		} else {
			request.setAttribute("message", "Usuário inválido");

			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

			dispatcher.forward(request, response);
		}
		response.sendRedirect("index.jsp");
	}

}
