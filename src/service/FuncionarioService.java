package service;

import java.util.ArrayList;

import model.Funcionario;
import dao.FuncionarioDAO;


public class FuncionarioService {
	FuncionarioDAO dao = new FuncionarioDAO();
	
	public int criar(Funcionario funcionario) {
		return dao.criar(funcionario);
	}
	
	public void atualizar(Funcionario funcionario){
		dao.atualizar(funcionario);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Funcionario carregar(int id){
		return dao.carregar(id);
	}

	public ArrayList<Funcionario> listarTodos() {
		return dao.listarTodos();
		
	}

	public ArrayList<Funcionario> listarPorEmpresa(int idEmpresa) {
		return dao.listarTodos(idEmpresa);
	}

}
