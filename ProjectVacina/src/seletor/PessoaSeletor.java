package seletor;

public class PessoaSeletor {

	private int id;
	private String nome;
	private String cpf;
	private int idade;
	private Character sexo;

	private int limite;
	private int pagina;

	public PessoaSeletor() {
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
		if ((this.cpf != null) && (this.cpf.trim().length() > 0)) {
			return true;
		}
		if (this.idade > 0) {
			return true;
		}
		if (this.sexo != null) {
			return true;
		}
		return false;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
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
