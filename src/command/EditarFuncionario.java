package command;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Funcionario;
import model.Usuario;
import service.FuncionarioService;

public class EditarFuncionario implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Usuario user = (Usuario) request.getSession().getAttribute("logado");

		request.setAttribute("title", "Cadastrar novo funcionário");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {}

		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		FuncionarioService fs = new FuncionarioService();

		if (id > 0) {
			request.setAttribute("title", "Editar funcionário");
			funcionario = fs.carregar(id);
		} else {
			funcionario = new Funcionario();
			
			funcionario.setEmpresaId(user.getEmpresaId());
		}
		try {
			if (request.getParameter("nome").length() > 0) {
				funcionario.setNome(request.getParameter("nome"));
				funcionario.setEmail(request.getParameter("email"));

				if (funcionario.getId() > 0) {
					fs.atualizar(funcionario);
				} else {
					funcionario.setId(fs.criar(funcionario));
				}
				
				response.sendRedirect("/Mailer/controller.do?command=ListarFuncionarios");
			} else {
				request.setAttribute("funcionario", funcionario);

				RequestDispatcher dispatcher = request.getRequestDispatcher("funcionarios/new.jsp");

				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("funcionario", funcionario);

			RequestDispatcher dispatcher = request.getRequestDispatcher("funcionarios/new.jsp");

			dispatcher.forward(request, response);
		}
		
		
	}

}
