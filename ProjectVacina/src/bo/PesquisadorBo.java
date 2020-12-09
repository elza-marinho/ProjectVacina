package bo;

import java.util.List;

import dao.PesquisadorDao;
import vo.Pesquisador;

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

	public Pesquisador salvar(Pesquisador pesquisador) {
		PesquisadorDao pDao = new PesquisadorDao();
		
		return pDao.salvar(pesquisador);
	}

	public boolean existeCpf(String txtCpf) {
		PesquisadorDao pDao = new PesquisadorDao();
		pDao.cpfJaUtilizado(txtCpf);
		return false;
		
	}

	public List<Pesquisador> listarTodos() {
		PesquisadorDao pDao = new PesquisadorDao();
		List<Pesquisador> pesquisadores = pDao.listarTodos();
		return pesquisadores;
	}

}


