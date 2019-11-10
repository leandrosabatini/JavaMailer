package command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Empresa;
import service.EmpresaService;

public class EditarEmpresa implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {}

		Empresa empresa = new Empresa();
		empresa.setId(id);
		EmpresaService es = new EmpresaService();

		empresa = es.carregar(id);

		try {
			if (request.getParameter("nome").length() > 0) {
				empresa.setNome(request.getParameter("nome"));

				es.atualizar(empresa);
				
				response.sendRedirect("/Mailer/controller.do?command=ViewEmpresa");
			} else {
				request.setAttribute("empresa", empresa);

				RequestDispatcher dispatcher = request.getRequestDispatcher("empresa/edit.jsp");

				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("empresa", empresa);

			RequestDispatcher dispatcher = request.getRequestDispatcher("empresa/edit.jsp");

			dispatcher.forward(request, response);
		}
		
		
	}

}
