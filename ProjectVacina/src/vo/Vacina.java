package vo;

import java.time.LocalDate;
import java.util.Date;

public class Vacina {
	public static final int ESTAGIO_INICIAL = 1;
	public static final int ESTAGIO_TESTES = 2;
	public static final int ESTAGIO_APLICACAO_NO_PUBLICO = 3;

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

	@Override
	public String toString() {
		return "Vacina [id=" + id + ", paisOrigem=" + paisOrigem + ", dataInicio=" + dataInicio + ", estagioVacina="
				+ estagioVacina + ", pesquisador=" + pesquisador + ", nome=" + nome + "]";
	}

	
		


}
