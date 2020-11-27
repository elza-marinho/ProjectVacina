package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import bo.VacinaBo;
import dao.PesquisadorDao;
import dao.VacinaDao;
import seletor.VacinaSeletor;
import vo.Pesquisador;
import vo.Vacina;

public class VacinaController {
	VacinaDao vdao = new VacinaDao();
	VacinaBo vbo = new VacinaBo();

	public String svalidar(String textPais,  String textNome) {
		String mensagem = "";
		if (textPais == null)  {
			mensagem +="O país de origem precisa ter no mínimo 2 letras";

		}
		
		if (textNome == null) {
			mensagem += "Nome precisa ter no mínimo 3 caracteres";
		}
		return mensagem;

	}

	public ArrayList<Vacina> listarComSeletor(VacinaSeletor seletor) {

		return vbo.listarComSeletor(seletor);
	}

	public ArrayList<Vacina> listarTodos() {
		return vbo.listarTodos();

	}

	public String excluir(String textoIdSelecionado) {
		String mensagem = "";
		try {
			int idSelecionado = Integer.parseInt(textoIdSelecionado);
			mensagem = vbo.excluirPorId(idSelecionado);
		} catch (NumberFormatException ex) {
			mensagem = "Informe um número inteiro";
		}
		return mensagem;
	}

	
		
		
	
	
	
	public void obterPesquisador() {
		PesquisadorDao dao = new PesquisadorDao();
		List<Pesquisador>pesquisadores = dao.listarTodos();
		
		
		
	}
	
	public void gerarRelatorio(List<Vacina> vacinas, String caminhoEscolhido) {
	
		
	}
		

}
