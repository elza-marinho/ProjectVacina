package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import bo.VacinaBo;
import dao.PesquisadorDao;
import dao.VacinaDao;
import seletor.VacinaSeletor;
import model.vo.Pesquisador;
import model.vo.Vacina;

public class VacinaController {
	VacinaDao vdao = new VacinaDao();
	VacinaBo vbo = new VacinaBo();
	private JComboBox<Pesquisador> cbPesquisador;

	public String validar(String textPais,  String textNome,Pesquisador pesquisadorSelecionado,  LocalDate dataInicio) {
		String mensagem = "";
		if (textPais == null)  {
			mensagem +="O país de origem precisa ter no mínimo 2 letras";

		}
		
		if (textNome == null) {
			mensagem += "Nome precisa ter no mínimo 3 caracteres";
		}
		if((pesquisadorSelecionado ==null)) {
			mensagem += "Selecione um pesquisador";
		}
		if((dataInicio == null)) {
			mensagem += "Selecione a data da pesquisa";
		}
		return mensagem;

	}
	
	
		public Vacina salvar(Vacina vacina) {
			VacinaBo vbo = new VacinaBo();
			vbo.salvar(vacina);
			return vacina;
			
		 
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
	
	public String gerarPlanilha(List<Vacina> vacinas, String caminhoEscolhido) {
		return caminhoEscolhido;
	
		
	}
		

}
