package bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dao.VacinaDao;
import seletor.VacinaSeletor;
import util.GeradorPlanilha;
import vo.Vacina;

public class VacinaBo {
	VacinaDao vacinaDao = new VacinaDao();

	public Vacina cadastrarVacina(Vacina vacina) {
		VacinaDao vacinaDao = new VacinaDao();
		return vacinaDao.cadastrar(vacina);
	}
	
	public void salvar(Vacina vacina) {
		
		
		if(vacina.getDataInicio().isAfter(LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "A data de início da pesquisa não pode ser anterior a data atual");
		}
		vacinaDao.cadastrar(vacina);
		
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


