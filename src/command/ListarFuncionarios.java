package command;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Usuario;
import service.FuncionarioService;

public class ListarFuncionarios implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

	    FuncionarioService fs = new FuncionarioService();
	    ArrayList<Funcionario> funcionarios = fs.listarPorEmpresa(user.getEmpresaId());
	    
		request.setAttribute("funcionarios", funcionarios);

		RequestDispatcher dispatcher = request.getRequestDispatcher("funcionarios/list.jsp");

		dispatcher.forward(request, response);
	}

}
