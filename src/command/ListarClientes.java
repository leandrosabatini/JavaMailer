package command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Usuario;
import service.ClienteService;

public class ListarClientes implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

	    ClienteService cs = new ClienteService();
	    ArrayList<Cliente> clientes = cs.listarPorEmpresa(user.getEmpresaId());
		request.setAttribute("clientes", clientes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("clientes/list.jsp");

		dispatcher.forward(request, response);
	}

}
