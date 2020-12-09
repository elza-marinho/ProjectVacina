package seletor;

import java.time.LocalDate;



import vo.Pesquisador;

public class VacinaSeletor {
	
	private int id;
	private String nome;
	private String pais;
	private String estagioPesquisa;
	private Pesquisador pesquisador;
	private  LocalDate dataInicioPesquisa;

	private int limite;
	private int pagina;

	public VacinaSeletor() {
		super();
		this.limite = 0;
		this.pagina = -1;

	}

	public boolean temFiltro() {
		if (this.id > 0) {
			return true;
		}

		if ((this.nome != null) && (this.nome.trim().length() > 0)) {
			return true;
		}

		if ((this.pais != null) && (this.pais.trim().length() > 0)) {
			return true;
		}
		
		if((this.estagioPesquisa!=null) &&(this.estagioPesquisa.trim().length()>0)) {
			return true;
		}
		
		if(this.pesquisador!=null) {
			return true;
		}
		if(this.dataInicioPesquisa!=null) {
			return true;
		}

		return false;
	}

	public boolean temPaginacao() {
		return ((this.limite > 0) && (this.pagina > -1));
	}

	public int getOffset() {
		return (this.limite * (this.pagina - 1));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	

	public String getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(String estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}
	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
}


