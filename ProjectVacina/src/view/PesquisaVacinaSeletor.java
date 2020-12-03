package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
<<<<<<< HEAD
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.VacinaController;
import vo.Pesquisador;
import vo.Vacina;
import seletor.VacinaSeletor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class PesquisaVacinaSeletor extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textPais;
	private JComboBox cbEstagio;
	private JComboBox cbPesquisador;
	private DatePicker dataInicioPesquisa;
	private List<Vacina> vacinasConsultadas;
	private JLabel lblPaginaAtual;
	private int paginaAtual = 1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaVacinaSeletor frame = new PesquisaVacinaSeletor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PesquisaVacinaSeletor() {
		setTitle("Pesquisa Vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(20, 20, 45, 20);
		contentPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNome.setBounds(75, 20, 195, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(20, 60, 45, 20);
		contentPane.add(lblPais);
		
		textPais = new JTextField();
		textPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPais.setBounds(75, 60, 195, 20);
		contentPane.add(textPais);
		textPais.setColumns(10);
		
		JLabel lblEstagio = new JLabel("Est\u00E1gio Pesquisa:");
		lblEstagio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstagio.setBounds(20, 90, 100, 20);
		contentPane.add(lblEstagio);
		
		cbEstagio = new JComboBox();
		cbEstagio.setModel(new DefaultComboBoxModel(new String[] {"Selecione:", "1- Testes", "2- Aplica\u00E7\u00E3o no P\u00FAblico"}));
		cbEstagio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbEstagio.setBounds(130, 90, 140, 20);
		contentPane.add(cbEstagio);
		
		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisador.setBounds(20, 130, 70, 20);
		contentPane.add(lblPesquisador);
		
		ArrayList<Pesquisador> pesquisadores = obterPesquisadoresMock();
		cbPesquisador = new JComboBox(pesquisadores.toArray());
		cbPesquisador.setBounds(100, 130, 225, 20);
		contentPane.add(cbPesquisador);
		
		JLabel lblDataInicio = new JLabel("Data Inicio Pesquisa:");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataInicio.setBounds(20, 170, 115, 20);
		contentPane.add(lblDataInicio);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		DatePicker dataInicioPesquisa = new DatePicker(dateSettings);
		dataInicioPesquisa.setBounds(145, 170, 145, 20);
		contentPane.add(dataInicioPesquisa);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				
			}
		});
		btnAnterior.setBounds(100, 285, 85, 25);
		contentPane.add(btnAnterior);
		
		JButton btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBounds(270, 285, 85, 25);
		contentPane.add(btnProximo);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VacinaController controller = new VacinaController();
				VacinaSeletor seletor = new VacinaSeletor();
				
				seletor.setNome(textNome.getText());
				seletor.setDataInicioPesquisa(dataInicioPesquisa.getDate());
				seletor.setPais(textPais.getText());
				seletor.setEstagioPesquisa(""+ cbEstagio.getSelectedItem().toString());
				seletor.setPesquisador((Pesquisador)cbPesquisador.getSelectedItem());
					
				}
				
		});
	
		
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultar.setBounds(185, 225, 85, 25);
		contentPane.add(btnConsultar);
		
		JButton btnGerar = new JButton("Gerar Planilha");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaArquivos = new JFileChooser();
				int opcaoSelecionada = janelaArquivos.showSaveDialog(null);

				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminho = janelaArquivos.getSelectedFile().getAbsolutePath();
					
					VacinaController controller = new VacinaController();
					String mensagem = controller.gerarPlanilha(vacinasConsultadas, caminho);

			}
			}
		});
		btnGerar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerar.setBounds(185, 340, 140, 25);
		contentPane.add(btnGerar);
		
		JLabel lblPaginaAtual = new JLabel("P\u00E1gina Atual");
		lblPaginaAtual.setBounds(430, 295, 90, 20);
		contentPane.add(lblPaginaAtual);
	}

	private ArrayList<Pesquisador> obterPesquisadoresMock() {
		ArrayList<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
		pesquisadores.add(new Pesquisador(1, "Albert Sabin", "23690399068", "UNIVERSIDADE DE NOVA YORK", 'm'));
		pesquisadores.add(new Pesquisador(2, "Osvaldo Cruz", " 59924002067", "UNIVERSIDADE FEDERAL DO RIO DE JANEIRO", 'm'));
		pesquisadores.add(new Pesquisador(3, "Carlos Chagas", " 63522680057", "UNIVERSIDADE FEDERAL DO RIO DE JANEIRO", 'm'));
		return pesquisadores;
		
=======
import javax.swing.DefaultComboBoxModel;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PesquisaVacinaSeletor extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textPais;
	private JComboBox cbEstagioPesquisa;
	private JComboBox cbPesquisador;
	private DatePicker dataInicioPesquisa;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaVacinaSeletor frame = new PesquisaVacinaSeletor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PesquisaVacinaSeletor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(20, 20, 45, 20);
		contentPane.add(lblNome);
		
		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNome.setBounds(75, 20, 195, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		JLabel lblPais = new JLabel("Pa\u00EDs:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(20, 60, 45, 20);
		contentPane.add(lblPais);
		
		textPais = new JTextField();
		textPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPais.setBounds(75, 60, 195, 20);
		contentPane.add(textPais);
		textPais.setColumns(10);
		
		JLabel lblEstagio = new JLabel("Est\u00E1gio Pesquisa:");
		lblEstagio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstagio.setBounds(20, 90, 100, 20);
		contentPane.add(lblEstagio);
		
		JComboBox cbEstagioPesquisa = new JComboBox();
		cbEstagioPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Selecione:", "1- Testes", "2- Aplica\u00E7\u00E3o no P\u00FAblico"}));
		cbEstagioPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbEstagioPesquisa.setBounds(130, 90, 140, 20);
		contentPane.add(cbEstagioPesquisa);
		
		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisador.setBounds(20, 130, 70, 20);
		contentPane.add(lblPesquisador);
		
		JComboBox cbPesquisador = new JComboBox();
		cbPesquisador.setBounds(100, 130, 170, 20);
		contentPane.add(cbPesquisador);
		
		JLabel lblDataInicio = new JLabel("Data Inicio Pesquisa:");
		lblDataInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataInicio.setBounds(20, 170, 115, 20);
		contentPane.add(lblDataInicio);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(145, 170, 145, 20);
		contentPane.add(datePicker);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAnterior.setBounds(100, 285, 85, 25);
		contentPane.add(btnAnterior);
		
		JButton btnProximo = new JButton("Pr\u00F3ximo");
		btnProximo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnProximo.setBounds(270, 285, 85, 25);
		contentPane.add(btnProximo);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConsultar.setBounds(185, 225, 85, 25);
		contentPane.add(btnConsultar);
		
		JButton btnGerar = new JButton("Gerar Planilha");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGerar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGerar.setBounds(185, 340, 140, 25);
		contentPane.add(btnGerar);
		
		JLabel lblPaginaAtual = new JLabel("P\u00E1gina Atual");
		lblPaginaAtual.setBounds(430, 295, 60, 20);
		contentPane.add(lblPaginaAtual);
>>>>>>> branch 'master' of https://github.com/elza-marinho/ProjectVacina
	}
}
