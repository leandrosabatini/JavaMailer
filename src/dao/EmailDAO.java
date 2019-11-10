package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;
import model.Email;
import model.Funcionario;
import service.ClienteService;
import service.EmailService;
import service.FuncionarioService;

public class EmailDAO {
	public int criar(Email email) {
		String sqlInsert = "INSERT INTO email(assunto, conteudo, empresa_id) VALUES (?, ?, ?)";

		try (Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement stm = conn.prepareStatement(sqlInsert);) {

			stm.setString(1, email.getAssunto());
			stm.setString(2, email.getConteudo());
			stm.setInt(3, email.getEmpresaId());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					email.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			for (int i = 0; i < email.getClientes().size(); i++) {
				Cliente cliente = email.getClientes().get(i);
			
				sqlInsert = "INSERT INTO email_cliente(email_id, cliente_id) VALUES (?, ?)";
				try (Connection conn = ConnectionFactory.obterConexao();
					PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
					stm.setInt(1, email.getId());
					stm.setInt(2, cliente.getId());
					stm.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (NullPointerException e) {}
		
		try {
			for (int i = 0; i < email.getFuncionarios().size(); i++) {
				Funcionario funcionario = email.getFuncionarios().get(i);
			
				sqlInsert = "INSERT INTO email_funcionario(email_id, funcionario_id) VALUES (?, ?)";
				try (Connection conn = ConnectionFactory.obterConexao();
					PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
					stm.setInt(1, email.getId());
					stm.setInt(2, funcionario.getId());
					stm.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (NullPointerException e) {}
		
		return email.getId();
	}

	public void atualizar(Email email) {
		String sqlUpdate = "UPDATE email SET assunto=?, conteudo=? empresa_id=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, email.getAssunto());
			stm.setString(2, email.getConteudo());
			stm.setInt(3, email.getEmpresaId());
			stm.setInt(4, email.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM email WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Email carregar(int id) {
		Email email = new Email();
		email.setId(id);
		String sqlSelect = "SELECT assunto, conteudo, empresa_id FROM email WHERE email.id = ?";

		try (Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, email.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					email.setAssunto(rs.getString("assunto"));
					email.setConteudo(rs.getString("conteudo"));
					email.setEmpresaId(rs.getInt("empresa_id"));
				} else {
					email.setId(-1);
					email.setAssunto(null);
					email.setConteudo(null);
					email.setEmpresaId(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}

		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ClienteService cs = new ClienteService();
		sqlSelect = "SELECT cliente_id FROM email_cliente WHERE email_cliente.email_id = ?";

		try (Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, email.getId());
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					clientes.add(cs.carregar(rs.getInt("cliente_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		email.setClientes(clientes);
		
		
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		FuncionarioService fs = new FuncionarioService();
		sqlSelect = "SELECT funcionario_id FROM email_funcionario WHERE email_id = ?";

		try (Connection conn = ConnectionFactory.obterConexao();
			PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, email.getId());
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					funcionarios.add(fs.carregar(rs.getInt("funcionario_id")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		email.setFuncionarios(funcionarios);
		return email;
	}

	public ArrayList<Email> listarTodos() {
		ArrayList<Email> emails = new ArrayList<>();
		String sqlSelect = "SELECT id FROM email";
		Email email;

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
			EmailService es = new EmailService();
			while (rs.next()) {
				email = es.carregar(rs.getInt("id"));
				emails.add(email);
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return emails;
	}

	public ArrayList<Email> listarPorEmpresa(int empresaId) {
		ArrayList<Email> emails = new ArrayList<>();
		String sqlSelect = "SELECT id FROM email WHERE empresa_id=?";
		Email email;
		EmailService es = new EmailService();

		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				) {
			stm.setInt(1, empresaId);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					email = new Email();
					email.setId(rs.getInt("id"));
					email = es.carregar(rs.getInt("id"));
					emails.add(email);
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return emails;
	}

}
