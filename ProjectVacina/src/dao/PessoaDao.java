package dao;


	
	import java.sql.Connection;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;

	import model.vo.Pessoa;

	public class PessoaDao {
		public Pessoa cadastrar(Pessoa novaPessoa) {
			Connection conn = Banco.getConnection();
			String sql = "INSERT INTO PESSOA ( NOME, CPF, SEXO, IDADE, VOLUNTARIO )" + " VALUES (?, ?, ?, ?, ?,?)";

			PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ResultSet rs = null;
			try {
				stmt.setString(1, novaPessoa.getNome());
				stmt.setString(2, novaPessoa.getCpf());
				stmt.setString(3, novaPessoa.getSexo() + "");
				stmt.setInt(4, novaPessoa.getIdade());
				stmt.setBoolean(5, novaPessoa.isVoluntario());
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
				stmt.setBoolean(5, pessoa.isVoluntario());

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
			pessoa.setVoluntario(rs.getBoolean("Voluntário"));

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
	}


