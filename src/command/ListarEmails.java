package command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Email;
import model.Usuario;
import service.EmailService;

public class ListarEmails implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

	    EmailService es = new EmailService();
	    ArrayList<Email> emails = es.listarPorEmpresa(user.getEmpresaId());
		request.setAttribute("emails", emails);

		RequestDispatcher dispatcher = request.getRequestDispatcher("emails/list.jsp");

		dispatcher.forward(request, response);
	}

}
