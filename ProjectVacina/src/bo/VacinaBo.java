package bo;

import java.util.ArrayList;

import dao.VacinaDao;
import seletor.VacinaSeletor;
import vo.Vacina;

public class VacinaBo {
	VacinaDao vacinaDao = new VacinaDao();

	public Vacina cadastrarVacina(Vacina vacina) {
		VacinaDao vacinaDao = new VacinaDao();
		return vacinaDao.cadastrar(vacina);
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
		
	}


