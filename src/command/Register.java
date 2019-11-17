package command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Empresa;
import model.Usuario;
import service.EmpresaService;
import service.UsuarioService;

public class Register implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pEmail = request.getParameter("email");
		String pSenha = request.getParameter("senha");
		String pEmpresa = request.getParameter("empresa");

		EmpresaService es = new EmpresaService();
		Empresa empresa = new Empresa();
		empresa.setNome(pEmpresa);
		empresa.setId(es.criar(empresa));

		UsuarioService us = new UsuarioService();
		Usuario usuario = new Usuario();
		usuario.setNome(pNome);
		usuario.setEmail(pEmail);
		usuario.setSenha(pSenha);
		usuario.setEmpresaId(empresa.getId());
		usuario.setId(us.criar(usuario));
		
		HttpSession session = request.getSession();
		session.setAttribute("logado", usuario);
		
		response.sendRedirect("dashboard.jsp");
	}

}
