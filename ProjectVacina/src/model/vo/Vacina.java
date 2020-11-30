package model.vo;

import java.time.LocalDate;

public class Vacina {
	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_NO_PUBLICO = 3;
	public static final String TXT_ESTAGIO_INICIAL = "Estágio Inicial";
	public static final String TXT_ESTAGIO_TESTES = "Estágio de Testes";
	public static final String TXT_ESTAGIO_APLICACAO_NO_PUBLICO = "Estágio de Aplicação no Público";

	private int id;

	private String paisOrigem;
	private LocalDate dataInicio;
	private int estagioVacina;
	private Pesquisador pesquisador;
	private String nome;

	public Vacina() {
		super();

	}

	public Vacina(int id, String paisOrigem, LocalDate dataInicio, int estagioVacina, Pesquisador pesquisador,
			String nome) {
		super();
		this.id = id;
		this.paisOrigem = paisOrigem;
		this.dataInicio = dataInicio;
		this.estagioVacina = estagioVacina;
		this.pesquisador = pesquisador;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaisOrigem() {
		return paisOrigem;
	}

	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public int getEstagioVacina() {
		return estagioVacina;
	}

	public void setEstagioVacina(int estagioVacina) {
		this.estagioVacina = estagioVacina;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private String getStringEstagioDaVacina() {
		String estagioVacina = null;
		switch (this.estagioVacina) {
		case Vacina.ESTAGIO_INICIAL:
			estagioVacina = TXT_ESTAGIO_INICIAL;
			break;
		case Vacina.ESTAGIO_TESTES:
			estagioVacina = TXT_ESTAGIO_TESTES;
			break;
		case Vacina.ESTAGIO_APLICACAO_NO_PUBLICO:
			estagioVacina = TXT_ESTAGIO_APLICACAO_NO_PUBLICO;
			break;
		default:
		}

		return estagioVacina;
	}

	public static int getIntEstagioDePesquisa(String nomeEstagio) {
		int estagioVacina = -1;

		if (nomeEstagio != null) {
			if (nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_INICIAL)) {
				estagioVacina = ESTAGIO_INICIAL;
			} else if (nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_TESTES)) {
				estagioVacina = ESTAGIO_TESTES;
			} else if (nomeEstagio.equalsIgnoreCase(TXT_ESTAGIO_APLICACAO_NO_PUBLICO)) {
				estagioVacina = ESTAGIO_APLICACAO_NO_PUBLICO;
			}
		}

		return estagioVacina;
	}

	public static String[] getEstagiosDeVacina() {
		return new String[] { TXT_ESTAGIO_INICIAL, TXT_ESTAGIO_TESTES, TXT_ESTAGIO_APLICACAO_NO_PUBLICO };
	}

	@Override
	public String toString() {
		return "Vacina [id=" + id + ", paisOrigem=" + paisOrigem + ", dataInicio=" + dataInicio + ", estagioVacina="
				+ estagioVacina + ", pesquisador=" + pesquisador + ", nome=" + nome + "]";
	}

}
