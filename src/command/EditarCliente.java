package command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Usuario;
import service.ClienteService;

public class EditarCliente implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

		request.setAttribute("title", "Cadastrar novo cliente");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {}

		Cliente cliente = new Cliente();
		cliente.setId(id);
		ClienteService cs = new ClienteService();

		if (id > 0) {
			request.setAttribute("title", "Editar cliente");
			cliente = cs.carregar(id);
		} else {
			cliente = new Cliente();
			
			cliente.setEmpresaId(user.getEmpresaId());
		}
		try {
			if (request.getParameter("nome").length() > 0) {
				cliente.setNome(request.getParameter("nome"));
				cliente.setEmail(request.getParameter("email"));

				if (cliente.getId() > 0) {
					cs.atualizar(cliente);
				} else {
					cliente.setId(cs.criar(cliente));
				}
				
				response.sendRedirect("/Mailer/controller.do?command=ListarClientes");
			} else {
				request.setAttribute("cliente", cliente);

				RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/new.jsp");

				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("cliente", cliente);

			RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/new.jsp");

			dispatcher.forward(request, response);
		}
		
		
	}

}
