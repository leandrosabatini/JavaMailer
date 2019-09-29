package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Funcionario;

public class FuncionarioDAO {
	public int criar(Funcionario funcionario) {
		String sqlInsert = "INSERT INTO funcionario(nome, email, empresa_id) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
			PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getEmail());
			stm.setInt(3, funcionario.getEmpresaId());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					funcionario.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario.getId();
	}

	public void atualizar(Funcionario funcionario) {
		String sqlUpdate = "UPDATE funcionario SET nome=?, email=? empresa_id=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, funcionario.getNome());
			stm.setString(2, funcionario.getEmail());
			stm.setInt(3, funcionario.getEmpresaId());
			stm.setInt(4, funcionario.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM funcionario WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Funcionario carregar(int id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		String sqlSelect = "SELECT nome, email, empresa_id FROM funcionario WHERE funcionario.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, funcionario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					funcionario.setNome(rs.getString("nome"));
					funcionario.setEmail(rs.getString("email"));
					funcionario.setEmpresaId(rs.getInt("empresa_id"));
				} else {
					funcionario.setId(-1);
					funcionario.setNome(null);
					funcionario.setEmail(null);
					funcionario.setEmpresaId(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return funcionario;
	}

	public ArrayList<Funcionario> listarTodos() {
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, email, empresa_id FROM funcionario";
		Funcionario funcionario;

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setEmpresaId(rs.getInt("empresa_id"));
				funcionarios.add(funcionario);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return funcionarios;
	}

}
