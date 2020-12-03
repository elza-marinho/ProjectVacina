package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bo.VacinaBo;
import dao.PesquisadorDao;
import dao.VacinaDao;
import exception.DataInicioInvalidaException;
import seletor.VacinaSeletor;
import vo.Pesquisador;
import vo.Vacina;

public class VacinaController {
	VacinaDao vdao = new VacinaDao();
	VacinaBo vbo = new VacinaBo();
	private JComboBox<Pesquisador> cbPesquisador;

	public void validar(String textPais,  String textNome,Pesquisador pesquisadorSelecionado,  LocalDate dataInicio) {
		String mensagem = "";
		if (textPais == null)  {
			JOptionPane.showMessageDialog(null, "O país de origem precisa ter no mínimo 2 letras");

		}
		
		if (textNome == null) {
			JOptionPane.showMessageDialog(null, "Nome precisa ter no mínimo 3 caracteres");
		}
		if((pesquisadorSelecionado ==null)) {
			JOptionPane.showMessageDialog(null, "Selecione um pesquisador"); 
		}
		if((dataInicio == null)) {
			JOptionPane.showMessageDialog(null, "Selecione a data da pesquisa"); 
		}
		

	}
	public Vacina salvar(Vacina vacina) {
		return vdao.salvar(vacina);
		
	}
	
	
		
	public static String validarData(LocalDate dataEscolhida) {
		String mensagem ="";
		if ((dataEscolhida == null)) {
			mensagem = "Escolha uma data para  ser consultada";
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
	
	public String gerarPlanilha(List<Vacina> vacinas, String caminhoEscolhido) {
		return caminhoEscolhido;
	
		
	}
		

}
