package controller;

import javax.swing.JOptionPane;

import model.bo.PessoaBO;
import model.dao.PessoaDAO;
import model.vo.Pessoa;

public class PessoaController {
	PessoaDAO dao = new PessoaDAO();
	PessoaBO bo = new PessoaBO();
	
	
	public String salvar(String textNome, String textCpf, String textIdade) {
		String mensagem = "";

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(textNome);
		pessoa.setCpf(textCpf);
		pessoa.setIdade(Integer.parseInt(textIdade));

		mensagem += validar(pessoa);

		if (mensagem.trim().isEmpty()) {
			mensagem += "Salvo com sucesso";

		}

		return mensagem;
	}

	private String validar(Pessoa pessoa) {
		String mensagem = "";

		if ((pessoa.getNome() == null) || (pessoa.getNome().trim().length() < 3)
				|| (pessoa.getNome().trim().length() > 100)) {
			mensagem += "- O Nome precisa deve ter entre 3 e 100 caracteres; " + "\n";
		}
		if ((pessoa.getCpf() == null) || (pessoa.getCpf().length() != 11)) {
			mensagem += "- O CPF deve conter 11 números; " + "\n";
		}
		if(pessoa.getIdade()<50) {
			mensagem += "Pessoa fora da idade para se qualificar para a vacina";
			
		}
		return mensagem;
	}
		
		
	}

	
	
	public String cpfExistente(String txtCpf) {
		String mensagem = "";

		if (bo.cpfCadastrado(txtCpf)) {
			mensagem += "Este cpf já está sendo utilizado.\n";
		}
		return mensagem;
	}
	
	public String validarCpf(String txtCpf) {
		String mensagem = "";

		if (txtCpf.length() != 11) {
			mensagem += "O cpf deve possuir 11 dígitos.\n";
		}
		if (txtCpf.isEmpty()) {
			mensagem += "O campo do cpf não foi preenchido.\n";
		}

		return mensagem;
	}
	
	public String excluir(String textoIdSelecionado) {
		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = bo.excluir(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um número inteiro";
		}
		return mensagem;
	}
	public List<Pessoa> listarComSeletor(PessoaSeletor seletor) {

		return dao.listarComSeletor(seletor);
	}
	
}
