package service;

import java.util.ArrayList;

import model.Cliente;
import dao.ClienteDAO;


public class ClienteService {
	ClienteDAO dao = new ClienteDAO();
	
	public int criar(Cliente cliente) {
		return dao.criar(cliente);
	}
	
	public void atualizar(Cliente cliente){
		dao.atualizar(cliente);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Cliente carregar(int id){
		return dao.carregar(id);
	}

	public ArrayList<Cliente> listarTodos() {
		return dao.listarTodos();
		
	}

	public ArrayList<Cliente> listarPorEmpresa(int empresaId) {
		return dao.listarPorEmpresa(empresaId);
	}

}
