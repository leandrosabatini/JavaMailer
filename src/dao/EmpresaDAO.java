package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Empresa;

public class EmpresaDAO {
	public int criar(Empresa empresa) {
		String sqlInsert = "INSERT INTO empresa(nome, cnpj) VALUES (?, ?)";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, empresa.getNome());
			stm.setString(2, empresa.getCnpj());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					empresa.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empresa.getId();
	}

	public void atualizar(Empresa empresa) {
		String sqlUpdate = "UPDATE empresa SET nome=?, cnpj=? WHERE id=?";
	
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, empresa.getNome());
			stm.setString(2, empresa.getCnpj());
			stm.setInt(3, empresa.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM empresa WHERE id = ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Empresa carregar(int id) {
		Empresa empresa = new Empresa();
		empresa.setId(id);
		String sqlSelect = "SELECT nome, cnpj FROM empresa WHERE empresa.id = ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, empresa.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					empresa.setNome(rs.getString("nome"));
					empresa.setCnpj(rs.getString("cnpj"));
				} else {
					empresa.setId(-1);
					empresa.setNome(null);
					empresa.setCnpj(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return empresa;
	}

	public ArrayList<Empresa> listarTodos() {
		ArrayList<Empresa> empresas = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, cnpj FROM empresa";
		Empresa empresa;

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				empresa = new Empresa();
				empresa.setId(rs.getInt("id"));
				empresa.setNome(rs.getString("nome"));
				empresa.setCnpj(rs.getString("cnpj"));
				empresas.add(empresa);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return empresas;
	}

}
