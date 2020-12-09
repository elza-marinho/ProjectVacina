package bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

import javax.swing.JOptionPane;


import dao.VacinaDao;
import exception.DataInicioInvalidaException;

import seletor.VacinaSeletor;
import util.GeradorPlanilha;
import vo.Vacina;

public class VacinaBo {
	VacinaDao vacinaDao = new VacinaDao();

	public Vacina cadastrarVacina(Vacina vacina) {
		VacinaDao vacinaDao = new VacinaDao();
		return vacinaDao.salvar(vacina);
	}
	
	public Vacina salvar(Vacina vacina) throws DataInicioInvalidaException {
		if(vacina.getDataInicio().isAfter(LocalDate.now())) {
			throw new DataInicioInvalidaException("Data de inicio da pesquisa deve ser anterior a data atual.");
		}
		
		vacinaDao.salvar(vacina);
		
		return vacina;
	}
	public ArrayList<Vacina> listarComSeletor(VacinaSeletor seletor) {
		
		 return vacinaDao.listarComSeletor(seletor);

	}

	public ArrayList<Vacina> listarTodos() {
		
		return vacinaDao.listarTodos();
	}

	public String excluirPorId(int idSelecionado) {
		String mensagem = "";
		VacinaDao vacinaDao = new VacinaDao();
		if (vacinaDao.excluir(idSelecionado)) {
			mensagem = "Excluído com sucesso";
		} else {
			mensagem = "Erro ao excluir";
		}
		return mensagem;

	}
	

	public void gerarPlanilha(List<Vacina> vacinas, String caminhoEscolhido) {
		GeradorPlanilha gerador = new GeradorPlanilha();
	}
		
	}


