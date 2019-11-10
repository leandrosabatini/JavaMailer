package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Funcionario;
import model.Usuario;
import service.ClienteService;
import service.FuncionarioService;

public class NovoEmail implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("logado");

		ClienteService cs = new ClienteService();
		ArrayList<Cliente> clientes = cs.listarPorEmpresa(user.getEmpresaId());
		request.setAttribute("clientes", clientes);

		FuncionarioService fs = new FuncionarioService();
		ArrayList<Funcionario> funcionarios = fs.listarPorEmpresa(user.getEmpresaId());
		request.setAttribute("funcionarios", funcionarios);

		RequestDispatcher dispatcher = request.getRequestDispatcher("emails/new.jsp");

		dispatcher.forward(request, response);
	}

}
