package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vo.Pessoa;

public class GerarPlanilha {

	public String gerarPlanilhaPessoas(String caminhoArquivo, List<Pessoa> pessoas) {
		XSSFWorkbook planilha = new XSSFWorkbook();

		
		XSSFSheet aba = planilha.createSheet("Pessoas");

		int linhaAtual = 0;

		
		String[] nomesColunas = { "#", "Nome", "CPF", "idade", "Sexo" };
		criarCabecalho(nomesColunas, aba, linhaAtual);

		criarLinhasPessoas(pessoas, aba, linhaAtual);

		
		return salvarNoDisco(planilha, caminhoArquivo, ".xlsx");

	}

	private void criarLinhasPessoas(List<Pessoa> pessoas, XSSFSheet aba, int linhaAtual) {
		for (Pessoa p : pessoas) {

			XSSFRow linha = aba.createRow(linhaAtual);

			linha.createCell(0).setCellValue(p.getNome());
			linha.createCell(1).setCellValue(p.getCpf());
			linha.createCell(3).setCellValue(p.getIdade());
			linha.createCell(4).setCellValue(p.getSexo());

			linhaAtual++;

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

	private void criarCabecalho(String[] nomesColunas, XSSFSheet aba, int posicaoLinhaAtual) {
		Row linhaAtual = aba.createRow(posicaoLinhaAtual);

		posicaoLinhaAtual++;
		
		for (int i = 0; i < nomesColunas.length; i++) {
			Cell novaCelula = linhaAtual.createCell(i);
			novaCelula.setCellValue(nomesColunas[i]);
		}

	}

}
