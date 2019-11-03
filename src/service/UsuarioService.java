package service;

import java.util.ArrayList;

import model.Usuario;
import dao.UsuarioDAO;


public class UsuarioService {
	UsuarioDAO dao = new UsuarioDAO();
	
	public boolean validar(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		return dao.validar(usuario);
	}
	
	public int criar(Usuario usuario) {
		return dao.criar(usuario);
	}
	
	public void atualizar(Usuario usuario){
		dao.atualizar(usuario);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Usuario carregar(int id){
		return dao.carregar(id);
	}

	public ArrayList<Usuario> listarTodos() {
		return dao.listarTodos();
		
	}

	public Usuario carregarByEmail(String email) {
		return dao.carregarByEmail(email);
	}

}
