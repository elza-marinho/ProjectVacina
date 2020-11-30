package bo;

import java.util.List;

import dao.PesquisadorDao;
import model.vo.Pesquisador;

public class PesquisadorBo {
	public String excluirPorId(int idSelecionado) {

		String mensagem = "";
		PesquisadorDao pDao = new PesquisadorDao();
		if (pDao.excluir(idSelecionado)) {
			mensagem = "Excluído com sucesso";
		} else {
			mensagem = "Erro ao excluir";
		}
		return mensagem;

	}

	public Pesquisador cadastrarPesquisador(Pesquisador pesquisador) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existeCpf(String txtCpf) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Pesquisador> listarTodos() {
		PesquisadorDao pDao = new PesquisadorDao();
		List<Pesquisador> pesquisadores = pDao.listarTodos();
		return pesquisadores;
	}

}


