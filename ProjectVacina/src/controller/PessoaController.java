package controller;


	
	import java.util.List;

import javax.swing.JOptionPane;

	import bo.PessoaBo;
	import dao.PessoaDao;
import seletor.PessoaSeletor;
import vo.Pessoa;

	public class PessoaController {
		PessoaDao peDao = new PessoaDao();
		PessoaBo pessoaBo = new PessoaBo();
		
		
	
	
	
	
	
	public String cpfExistente(String textCpf) {
		String mensagem = "";
		PessoaBo pessoa = new PessoaBo();
		if (pessoa.existeCpf(textCpf)) {
			mensagem += "Este cpf j� est� sendo utilizado.\n";
		}
		return mensagem;
	}
	
	public String validarCpf(String txtCpf) {
		String mensagem = "";

		if (txtCpf.length() != 11) {
			mensagem += "O cpf deve possuir 11 d�gitos.\n";
		}
		if (txtCpf.isEmpty()) {
			mensagem += "O campo do cpf n�o foi preenchido.\n";
		}

		return mensagem;
	}
	
	public String excluir(String textoIdSelecionado) {
		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = pessoaBo.excluirPorId(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um n�mero inteiro";
		}
		return mensagem;
	}

	
		
	
	
	public List <Pessoa> listarTodos() {
		return pessoaBo.listarTodos();
		
	}
		public List<Pessoa> listarComSeletor(PessoaSeletor seletor){
			return peDao.listarComSeletor(seletor);
			
		}



		public String salvar(Pessoa pessoa) {
		
			return null;
		}

		public String salvar(String textNome, String textCpf, int idade) {
			String mensagem ="";
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(textNome);
		pessoa.setCpf(textCpf);
		pessoa.setIdade(idade);
		
		mensagem += validar(pessoa);
		
		if (mensagem.trim().isEmpty()) {
			pessoa = pessoaBo.cadastrarPessoa(pessoa);
		}
		
		if (mensagem.trim().isEmpty()) {
			mensagem += "O Cliente foi cadastrado com sucesso!";
		}
		return mensagem;
			
		}
		
		public String validar(Pessoa pessoa) {
			String mensagem ="";
			if ((pessoa.getNome() == null) || (pessoa.getNome().trim().length() < 3) || (pessoa.getNome().trim().length() < 101)) {
				mensagem+= "Nome precisa ter no m�nimo 3 caracteres";

			}
			if ((pessoa.getCpf() == null) || (pessoa.getCpf().trim().length() != 11)) {
				mensagem+=  "CPF cont�m somente 11 n�meros";

			}

			if(pessoa.getIdade()<0) {
				mensagem+= "Digite uma idade v�lida";
				
			}
			return mensagem;
		}
		
		public String excluirPorCpf(String cpf) {
			String mensagem = "";
			boolean retorno = false;
			if (peDao.cpfJaUtilizado(cpf)) {
				retorno = peDao.excluirPorCpf(cpf);
				if (retorno == true) {
					mensagem = "Pessoa excluida com sucesso.";
				}
			} else {
				mensagem = "Este CPF n�o consta no cadastro.";
			}
			return mensagem;
		}
	}