package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;

import seletor.VacinaSeletor;
import vo.Pesquisador;
import vo.Vacina;

public class VacinaDao {
	public Vacina cadastrar(Vacina novaVacina) {
		Connection conn = Banco.getConnection();

		String sql = "INSERT INTO VACINA (ID, PAISORIGEM, DATAINICIO, ESTAGIOPESQUISA, PESQUISADOR, NOME )"
				+ " VALUES (?, ?, ?, ?, ?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		ResultSet rs = null;
		try {

			stmt.setString(1, novaVacina.getPaisOrigem());
			Date dataConvertidaSQL = java.sql.Date.valueOf(novaVacina.getDataInicio());
			stmt.setInt(3, novaVacina.getEstagioVacina());
			stmt.setInt(4, novaVacina.getId());
			stmt.setString(5, novaVacina.getNome());
			stmt.setInt(6, novaVacina.getPesquisador().getId());
			rs = stmt.getGeneratedKeys();
			int refIdGerado = 0;

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				refIdGerado = idGerado;
				novaVacina.setId(idGerado);

			}

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar nova Vacina");
			System.out.println("Erro: " + e.getMessage());

		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}

		return novaVacina;
	}

	public boolean alterar(Vacina vacina) {
		Connection conn = Banco.getConnection();

		String sql = "UPDATE VACINA SET PAISORIGEM=?, DATAINICIO=?, ESTAGIOPESQUISA=?, PESQUISADOR=?, NOME=? "
				+ "WHERE ID=?";
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		int registrosAlterados = 0;
		try {

			stmt.setString(1, vacina.getPaisOrigem());
			Date dataConvertidaSQL = java.sql.Date.valueOf(vacina.getDataInicio());
			stmt.setInt(3, vacina.getEstagioVacina());
			stmt.setInt(4, vacina.getPesquisador().getId());
			stmt.setString(5, vacina.getNome());

			registrosAlterados = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar a vacina.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return registrosAlterados > 0;

	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM VACINA WHERE ID= " + id;
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		boolean excluiu = false;
		try {
			stmt.setInt(1, id);

			int codigoRetorno = stmt.executeUpdate();
			excluiu = (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO);

		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina (id: " + id + ").\nCausa: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;

	}

	public Vacina consultarPorId(int id) {
		String sql = "SELECT * FROM VACINA WHERE ID=?";
		Vacina vacinaConsultada = null;

		try (Connection conexao = Banco.getConnection();
				PreparedStatement consulta = Banco.getPreparedStatement(conexao, sql);) {
			consulta.setInt(1, id);
			ResultSet conjuntoResultante = consulta.executeQuery();
			if (conjuntoResultante.next()) {
				vacinaConsultada = construirVacinaDoResultSet(conjuntoResultante);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar a vacina por id (id: " + id + "). \nCausa: " + e.getMessage());
		}
		return vacinaConsultada;

	}

	private Vacina construirVacinaDoResultSet(ResultSet conjuntoResultante) throws SQLException {
		Vacina vac = new Vacina();

		vac.setId(conjuntoResultante.getInt("Id"));
		vac.setPaisOrigem(conjuntoResultante.getString("País de Origem"));
		
		vac.setNome(conjuntoResultante.getString("Nome"));
		vac.setEstagioVacina(conjuntoResultante.getInt("Estágio da Vacina"));
		vac.setDataInicio(conjuntoResultante.getDate("Data de Início").toLocalDate());

		PesquisadorDao pesquisadorDao = new PesquisadorDao();

		int idPesquisador = conjuntoResultante.getInt("idPesquisador");
		Pesquisador pesquisador = pesquisadorDao.consultarPorId(idPesquisador);
		vac.setPesquisador(pesquisador);

		return vac;
	}

	public ArrayList<Vacina> listarComSeletor(VacinaSeletor seletor) {
		String sql = "SELECT FROM VACINA v";
		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);

		}

		if (seletor.temPaginacao()) {
			sql += "LIMIT" + seletor.getLimite() + "OFFSET" + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Vacina v = construirVacinaDoResultSet(result);
				vacinas.add(v);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao consultar as vacinas com filtros\nCausa:  " + e.getMessage());
		}
		return vacinas;
	}

	private String criarFiltros(VacinaSeletor seletor, String sql) {

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

		if ((seletor.getPais() != null) && (seletor.getPais().trim().length() > 0)) {
			if (!primeiro) {
				sql += "AND";
			}
			sql += "v.pais LIKE '%" + seletor.getPais() + "%'";
			primeiro = false;

		}
		if (seletor.getEstagioPesquisa() > 0) {
			if (!primeiro) {
				sql += " AND ";
			}

			if ((seletor.getPesquisador() != null) && (seletor.getPesquisador().getId() > 0)) {
				if (!primeiro) {
					sql += "AND";
				}
				
			}
		}
		return sql;
	}

	public boolean procurarVacinaPorNome(String nome) {
		String sql = "SELECT * FROM VACINA WHERE NOME = '";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		ResultSet rs = null;

		int contador = 0;

		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				contador++;
			}
		} catch (Exception e) {
			System.out.println("Erro ao procurar vacina por nome" + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);

		}
		if (contador == 0)
			return false;
		return true;

	}

	public ArrayList<Vacina> listarTodos() {
		Connection conn = Banco.getConnection();
		String sql = " SELECT * FROM VACINA ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		ResultSet rs = null;

		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();
		try {
			rs = stmt.executeQuery();
			while (rs.next()) {
				Vacina vac = construirVacinaDoResultSet(rs);
				vacinas.add(vac);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao listar Vacinas.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vacinas;
	}

}
