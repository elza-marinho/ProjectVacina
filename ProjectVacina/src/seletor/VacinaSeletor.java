package seletor;

import vo.Pesquisador;

public class VacinaSeletor {
	
	private int id;
	private String nome;
	private String pais;
	private int estagioPesquisa;
	private Pesquisador pesquisador;

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
		
		if(this.estagioPesquisa>0) {
			return true;
		}
		
		if(this.pesquisador!=null) {
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

	public int getEstagioPesquisa() {
		return estagioPesquisa;
	}

	public void setEstagioPesquisa(int estagioPesquisa) {
		this.estagioPesquisa = estagioPesquisa;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

}


