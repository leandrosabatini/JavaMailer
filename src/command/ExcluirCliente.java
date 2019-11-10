

package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ClienteService;

public class ExcluirCliente implements Command {
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {}

		ClienteService cs = new ClienteService();
		cs.excluir(id);
		
	    response.sendRedirect("/Mailer/controller.do?command=ListarClientes");
		
	}

}
