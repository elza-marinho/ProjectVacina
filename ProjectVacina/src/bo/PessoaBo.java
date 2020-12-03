package bo;

import java.util.ArrayList;
import java.util.List;

import dao.PessoaDao;
import vo.Pessoa;

public class PessoaBo {
	PessoaDao peDao = new PessoaDao();

	public String excluirPorId(int idSelecionado) {
		String mensagem ="";
		peDao.excluir(idSelecionado);
				return mensagem;
	}

	public boolean existeCpf(String txtCpf) {
		PessoaDao peDao = new PessoaDao();
		if(peDao.cpfJaUtilizado(txtCpf)) {
			return  true;
		}else {
			
		}
		return false;
	}

	
	public List<Pessoa> listarTodos(){
		List<Pessoa> pessoas = peDao.listarTodos();
		return pessoas;
	}
	
	public String excluir(String cpf) {
		String mensagem = "";
		boolean retorno = false;
		if (peDao.cpfJaUtilizado(cpf)) {
			retorno = peDao.excluirPorCpf(cpf);
			if (retorno == true) {
				mensagem = "Pessoa excluida com sucesso.";
			}
		} else {
			mensagem = "Este CPF não consta no cadastro.";
		}
		return mensagem;
	}

	

	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		peDao.cadastrar(pessoa);
		String mensagem ="";
		if(pessoa.getId()>0) {
			mensagem +="Pessoa cadastrada com Sucesso";
		}else {
			mensagem +="Erro ao cadastrar pessoa";
		}
		return pessoa;
	}

}
