package model.bo;

import java.util.List;
import model.vo.Pessoa;
import model.dao.PessoaDAO;

public class PessoaBO {
	
	private PessoaDAO dao = new PessoaDAO();
	private Pessoa vo = new Pessoa();
	public Pessoa cadastrar(Pessoa novaPessoa) {
		
		Pessoa pessoa = new Pessoa();
		if(pessoa.getCpf() == null) {
			System.out.println("A pessoa deve possuir CPF");
		}
		if(dao.cpfJaCadastrado(novaPessoa)) {
			System.out.println("O CPF "+pessoa.getCpf()+" j� foi cadastrado");
		}
		return dao.cadastrar(novaPessoa);
	}	
		
	public boolean alterar(Pessoa pessoaAtualizada) {
		
		Pessoa pessoa = new Pessoa();
		if(pessoa.getCpf() == null) {
			System.out.println("A pessoa deve possuir CPF");
		}
		if(dao.cpfJaCadastrado(pessoa)) {
			System.out.println("O CPF "+pessoa.getCpf()+" j� foi cadastrado");
		}
		
		return dao.alterar(pessoa);
	}
	
	public boolean excluir(int id) {
		
		return dao.excluir(id);
	}
	
	public Pessoa pesquisarPorId(int id) {
		
		return dao.pesquisarPorId(id);
	}
	
	public List<Pessoa> buscarTodos(){
		
		return dao.pesquisarTodos();
	}
	public boolean cpfCadastrado(String cpf) {
		
		boolean jaCadastrado = dao.cpfJaCadastrado(vo);

		return jaCadastrado;
		
	}
}
