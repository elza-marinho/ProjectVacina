package controller;

import java.util.List;

import javax.swing.JOptionPane;

import bo.PesquisadorBo;
import dao.PesquisadorDao;
import vo.Pesquisador;

public class PesquisadorController {
	
	PesquisadorDao pdao = new PesquisadorDao();
	PesquisadorBo bo = new PesquisadorBo();

	public String excluir(String textoIdSelecionado) {
		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = bo.excluirPorId(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um número inteiro";
		}
		return mensagem;
	}

	public String validar(String Nome, String Cpf, String Instituicao) {
		Pesquisador pesquisador = new Pesquisador();
		String mensagem = "";
		if ((Nome == null) || (Nome.trim().length() < 3) || (Nome.trim().length() < 101)) {
			JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 3 caracteres");

		}
		if ((Cpf == null) || (Cpf.trim().length() != 11)) {
			JOptionPane.showMessageDialog(null, "CPF contém somente 11 números");

		}
		if((Instituicao==null) || (Instituicao.trim().length()<3) ||(Instituicao.trim().length()<201))
		{
			JOptionPane.showMessageDialog(null, "Nome da Instituição precisa ter no mínimo 3 caracteres ");
		}
		

		return mensagem;
	}

	public String cpfExistente(String txtCpf) {
		String mensagem = "";
		PesquisadorBo pesquisador = new PesquisadorBo();
		if (pesquisador.existeCpf(txtCpf)) {
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
	
	

	public List<Pesquisador> listarTodosOsPesquisadores() {
		return bo.listarTodos();
	}

	
	
	}




