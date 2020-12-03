package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vo.Pesquisador;
import vo.Vacina;

public class GeradorPlanilha {

	public String gerarPlanilhaProdutos(String caminhoArquivo, List<Vacina> vacinas) {
		XSSFWorkbook planilha = new XSSFWorkbook();

		XSSFSheet aba = planilha.createSheet("Vacinas");

		int linhaAtual = 0;

		String[] nomesColunas = { "#", "Nome", "País", "Estágio Pesquisa", "Data de Início" };
		criarCabecalho(nomesColunas, aba, linhaAtual++);

		criarLinhasVacinas(vacinas, aba, linhaAtual);

		ajustarLarguraColunas(aba, nomesColunas.length);

		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");
	}

	private void ajustarLarguraColunas(XSSFSheet aba, int quantidadeColunas) {
		for (int i = 0; i < quantidadeColunas; i++) {
			aba.autoSizeColumn(i);
		}
	}

	private void criarLinhasVacinas(List<Vacina> vacinas, XSSFSheet aba, int posicaoLinhaAtual) {
		DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for (Vacina v : vacinas) {

			XSSFRow linhaAtual = aba.createRow(posicaoLinhaAtual);
			linhaAtual.createCell(0).setCellValue(v.getId());
			linhaAtual.createCell(1).setCellValue(v.getNome());
			linhaAtual.createCell(2).setCellValue(v.getPaisOrigem());
			linhaAtual.createCell(3).setCellValue(v.getEstagioVacina());
			linhaAtual.createCell(4).setCellValue(v.getDataInicio().format(formatadorData));
			linhaAtual.createCell(5).setCellValue(v.getPesquisador().getNome());

			posicaoLinhaAtual++;
		}

	}

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;

		for (int i = 0; i < nomesColunas.length; i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas[i]);
		}

	}

	private String salvarNoDisco(XSSFWorkbook planilha, String caminhoArquivo, String extensao) {
		String mensagem = "";
		FileOutputStream saida = null;

		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso!";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} catch (IOException e) {
			mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}
}
