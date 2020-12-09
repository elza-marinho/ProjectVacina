package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import atividade_vacina.model.dao.Banco;
import atividade_vacina.model.vo.PessoaVO;
import model.vo.Pessoa;

public class PessoaDAO {
	public Pessoa salvar(Pessoa novaPessoa) {
		Connection conn = Banco.getConnection();
		String sql = "INSERT INTO PESSOA ( NOME, CPF, SEXO, IDADE)" + " VALUES (?, ?, ?, ?, ?)";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;
		try {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setString(2, novaPessoa.getCpf());
			stmt.setString(3, novaPessoa.getSexo() + "")
			Stmt.setInt(4, pessoa.getIdade());
						
			stmt.execute();

			int refIdGerado = 0;

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				refIdGerado = idGerado;
				novaPessoa.setId(idGerado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar  Pessoa");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}
		return novaPessoa;
	}

	public boolean alterar(Pessoa pessoa) {

		String sql = "UPDATE PESSOA " + "SET NOME=?, CPF=?, IDADE=?, SEXO=?, VOLUNTARIO=?";
		boolean alterado = false;
		try (Connection conexao = Banco.getConnection();
				PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);) {
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getCpf());
			stmt.setInt(3, pessoa.getIdade());
			stmt.setString(4, pessoa.getSexo() + "");
			
			int codigoRetorno = stmt.executeUpdate();

			alterado = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);

		} catch (SQLException e) {
			System.out.println("Erro ao alterar pessoa.\nCausa: " + e.getMessage());

		}
		return alterado;
	}

	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM PESSOA WHERE ID=? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		boolean excluido = false;
		try {
			stmt.setInt(1, id);

			int codigoRetorno = stmt.executeUpdate();
			excluido = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);

		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa (id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return excluido;

	}

	public Pessoa pesquisarPorId(int id) {

		String sql = "SELECT * FROM PESSOA WHERE ID=?";
		Pessoa pessoaPesquisada = null;
		try (Connection conn = Banco.getConnection();
				PreparedStatement consulta = Banco.getPreparedStatement(conn, sql);) {
			consulta.setInt(1, id);
			ResultSet rs = consulta.executeQuery();
			if (rs.next()) {
				pessoaPesquisada = construirPessoaDoResultSet(rs);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoa por Id(id: " + id + ").\nCausa: " + e.getMessage());
		}
		return pessoaPesquisada;
	}

	private Pessoa construirPessoaDoResultSet(ResultSet rs) throws SQLException {
		Pessoa pessoa = new Pessoa();

		pessoa.setId(rs.getInt("id"));
		pessoa.setNome(rs.getString("Nome"));
		pessoa.setCpf(rs.getString("CPF"));
		pessoa.setIdade(rs.getInt("Idade"));
		pessoa.setSexo(rs.getString("Sexo").charAt(0));
		

		return pessoa;
	}

	public List<Pessoa> pesquisarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = construirPessoaDoResultSet(rs);
				pessoas.add(pessoa);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar pessoas.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;

	}
	
	public boolean cpfJaCadastrado(Pessoa pessoa) {
		Connection conn = Banco.getConnection();
		String sql = "select from pessoa where cpf = ?";
		
		if(pessoa.getId() > 0) {
			sql += "and id <> ?";
		}
		PreparedStatement ps = Banco.getPreparedStatement(conn, sql);
		boolean jaCadastrado = false;
		
		try {
			ps.setString(1, pessoa.getCpf());
			
			if(pessoa.getId() > 0) {
				ps.setInt(2, pessoa.getId());
			}
			
			ResultSet rs = ps.executeQuery();
			jaCadastrado = rs.next();
		}catch(SQLException e) {
			System.out.println("Erro ao verificar se o CPF "+pessoa.getCpf()+" já foi utilizado. \nErro: "+e.getMessage());
		}finally {
			Banco.closeConnection(conn);
			Banco.closePreparedStatement(ps);
		}
		
		return jaCadastrado;
	}
	
	public List<Pessoa> listarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				Pessoa pessoa = construirPessoaDoResultSet(rs);
				pessoas.add(pessoa);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar pessoas.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;

	}

	public List<Pessoa> listarComSeletor(PessoaSeletor seletor) {

		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA p";
		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Pessoa p = construirPessoaDoResultSet(result);
				pessoas.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pessoas com filtros. " + e.getMessage());
		}
		return pessoas;
	}

	private String criarFiltros(PessoaSeletor seletor, String sql) {

		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getId() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			sql += "p.id = " + seletor.getId();
			primeiro = false;
		}

		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += "AND";
			}
			sql += "v.nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}
		if (seletor.getCpf() != null && seletor.getCpf().trim().length() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			sql += " CPF LIKE " + "'%" + seletor.getCpf() + "%' ";
		}

		if (seletor.getSexo() != null) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "p.sexo = '" + seletor.getSexo() + "'";
			primeiro = false;
		}

		return sql;
	}

	public boolean excluirPorCpf(String cpf) {

		boolean excluido = false;
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM PESSOA WHERE CPF = '" + cpf + "'";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;
		try {
			stmt.setString(3, cpf);

			int codigoRetorno = stmt.executeUpdate();
			excluido = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);

		} catch (SQLException e) {
			System.out.println("Erro ao excluir pessoa pelo cpf (cpf " + cpf + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluido;

}
