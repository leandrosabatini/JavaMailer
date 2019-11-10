package command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Empresa;
import model.Usuario;
import service.EmpresaService;

public class ViewEmpresa implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

		EmpresaService es = new EmpresaService();
		Empresa empresa = es.carregar(user.getEmpresaId());

		request.setAttribute("empresa", empresa);
		
		try {
			if (user.getEmail() == null) {
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {}

		RequestDispatcher dispatcher = request.getRequestDispatcher("empresa/view.jsp");

		dispatcher.forward(request, response);
	}

}
