package vo;

import java.time.LocalDate;


public class Vacina {
	public static final String ESTAGIO_INICIAL = "Estágio Inicial";
	public static final String ESTAGIO_TESTES= "Estágio  Testes";
	public static final String ESTAGIO_APLICACAO_NO_PUBLICO = "Estágio de Aplicação no Público";

	private int id;
	
	private String paisOrigem;
	private LocalDate dataInicio;
	private String estagioVacina;
	private Pesquisador pesquisador;
	private String nome;

	public Vacina() {
		super();

	}

	
	
	public Vacina(int id, String paisOrigem, LocalDate dataInicio, String estagioVacina, Pesquisador pesquisador,
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



	public String getEstagioVacina() {
		return estagioVacina;
	}



	public void setEstagioVacina(String estagioVacina) {
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



	


	
		
	
	
	
	
	public static String[] getEstagiosDeVacina() {
		return new String[] {"ESTAGIO_INICIAL", "ESTAGIO_TESTES", "ESTAGIO_APLICACAO_NO_PUBLICO"};
	}

	@Override
	public String toString() {
		return "Vacina [id=" + id + ", paisOrigem=" + paisOrigem + ", dataInicio=" + dataInicio + ", estagioVacina="
				+ estagioVacina + ", pesquisador=" + pesquisador + ", nome=" + nome + "]";
	}

	
		


}
