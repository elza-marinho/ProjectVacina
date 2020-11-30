package model.vo;

public class Pessoa {
	private int id;
	private String nome;
	private String cpf;
	private int idade;
	private char sexo;
	private boolean voluntario;

	public Pessoa() {
		super();

	}

	public Pessoa(int id, String nome, String cpf, int idade, char sexo, boolean voluntario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
		this.sexo = sexo;
		this.voluntario = voluntario;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isVoluntario() {
		return voluntario;
	}

	public void setVoluntario(boolean voluntario) {
		this.voluntario = voluntario;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + ", sexo=" + sexo
				+ ", voluntario=" + voluntario + "]";
	}

	

}
