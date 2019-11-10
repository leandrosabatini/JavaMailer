package service;

import java.util.ArrayList;

import model.Email;
import dao.EmailDAO;


public class EmailService {
	EmailDAO dao = new EmailDAO();
	
	public int criar(Email email) {
		return dao.criar(email);
	}
	
	public void atualizar(Email email){
		dao.atualizar(email);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Email carregar(int id){
		return dao.carregar(id);
	}

	public ArrayList<Email> listarTodos() {
		return dao.listarTodos();
		
	}

	public ArrayList<Email> listarPorEmpresa(int empresaId) {
		return dao.listarPorEmpresa(empresaId);
	}

}
