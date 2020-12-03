package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.PessoaController;
import seletor.PessoaSeletor;
import vo.Pessoa;

import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JTable;

public class ConsultaPessoaSeletor extends JPanel {
	private JTextField textNome;
	private JTextField textIdade;
	private JTextField textCpf;
	private JTable tblPessoas;
	private JLabel lblPaginaAtual;
	private JRadioButton rdbtnmasculino;
	private JRadioButton rdbtnFeminino;
	private List<Pessoa> pessoasConsultadas;
	private int paginaAtual = 1;


	/**
	 * Create the panel.
	 */
	public ConsultaPessoaSeletor() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(30, 35, 50, 20);
		add(lblNome);
		
		textNome = new JTextField();
		textNome.setBounds(90, 35, 205, 19);
		add(textNome);
		textNome.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(30, 75, 45, 20);
		add(lblCpf);
		
		try {
			
			
			MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
			textCpf = new JFormattedTextField(maskFormatter);
			textCpf.setBounds(95, 75, 180, 20);
			add(textCpf);
			textCpf.setColumns(10);
		} catch(Exception e) {
			System.out.println("Erro na mascara de formatacao de CPF no painel de cadastro do usuario.");
			e.printStackTrace();
		}
		
		
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdade.setBounds(30, 110, 45, 20);
		add(lblIdade);
		
		textIdade = new JTextField();
		textIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textIdade.setBounds(90, 110, 25, 20);
		add(textIdade);
		textIdade.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(30, 140, 45, 20);
		add(lblSexo);
		
		rdbtnmasculino = new JRadioButton("Masculino");
		rdbtnmasculino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnmasculino.setBounds(75, 145, 95, 20);
		add(rdbtnmasculino);
		
		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFeminino.setBounds(185, 145, 95, 20);
		add(rdbtnFeminino);
						
		ButtonGroup sexo = new ButtonGroup();
		sexo.add(rdbtnmasculino);
		sexo.add(rdbtnFeminino);
		
		
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarPessoas();
			}

			private void consultarPessoas() {
				lblPaginaAtual.setText(paginaAtual + "");
				
				PessoaController controller = new PessoaController();
				PessoaSeletor seletor = new PessoaSeletor();
				
				seletor.setNome(textNome.getText());
				seletor.setCpf(textCpf.getText());
				
				if((textIdade.getText()!= null && !textIdade.getText().isEmpty())){
					seletor.setIdade(Integer.parseInt(textIdade.getText()));
				}else {
					seletor.setIdade(0);
					
				}
				List<Pessoa> pessoas = controller.listarComSeletor(seletor);
				atualizarTabelaPessoa(pessoas);
				
				
			}
			

			private void atualizarTabelaPessoa(List<Pessoa> pessoas) {
				pessoasConsultadas = pessoas;
				
				this.limparTabela();

				DefaultTableModel modelo = (DefaultTableModel) tblPessoas.getModel();
				
				for(Pessoa pessoa : pessoas) {
					
					String[]novaLinha = new String[] {pessoa.getId()+"",pessoa.getNome(), pessoa.getCpf(), pessoa.getIdade()+"", pessoa.getSexo()+""};
					modelo.addRow(novaLinha);
				}
				
				tblPessoas = new JTable();
				this.limparTabela();
				tblPessoas.setBounds(200, 200, 50, 20);
				add(tblPessoas);
			
			}

			private void limparTabela() {
				tblPessoas.setModel(new DefaultTableModel(new String[][] { { "#", "Nome", "CPF", "idade", "sexo" }, },
						new String[] { "#", "Nome", "CPF", "Idade", "Sexo" }));;
				
			}
		});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultar.setBounds(140, 235, 85, 25);
		add(btnConsultar);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				consultarPessoas();
			}

			private void consultarPessoas() {
				
				
			}
			
				
					
			
		});
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.setBounds(30, 185, 85, 25);
		add(btnAnterior);
		
		JButton btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
			
		
		btnProximo.setBounds(290, 185, 85, 25);
		add(btnProximo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(355, 35, 50, 15);
		add(separator);
		
		JLabel lblFiltros = new JLabel("Filtros de Consulta");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFiltros.setBounds(355, 80, 100, 20);
		add(lblFiltros);
		
		JButton btnGerarPlanilha = new JButton("Gerar Planilha");
		btnGerarPlanilha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerarPlanilha.setBounds(165, 325, 110, 25);
		add(btnGerarPlanilha);
		
		
	}

		
}

	
	

