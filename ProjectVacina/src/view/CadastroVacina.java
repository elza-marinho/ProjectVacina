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
import javax.swing.DefaultComboBoxModel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import controller.VacinaController;
import vo.Pesquisador;
import vo.Vacina;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CadastroVacina extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textPais;
	private JComboBox cbPesquisador;
	private JComboBox estagioVacina;
	private DatePicker dataInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroVacina frame = new CadastroVacina();
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
	public CadastroVacina() {
		setTitle("Cadastro Vacina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(20, 20, 50, 20);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNome.setBounds(80, 20, 185, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblPais = new JLabel("Pa\u00EDs de Origem:");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(20, 65, 95, 20);
		contentPane.add(lblPais);

		textPais = new JTextField();
		textPais.setBounds(125, 65, 140, 20);
		contentPane.add(textPais);
		textPais.setColumns(10);

		JLabel lblEstagio = new JLabel("Est\u00E1gio Vacina:");
		lblEstagio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstagio.setBounds(20, 105, 90, 20);
		contentPane.add(lblEstagio);

		JComboBox cbEstagio = new JComboBox();
		cbEstagio.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione", "1- Inicial", "2- Testes", "3 - Aplica\u00E7\u00E3o no p\u00FAblico" }));
		cbEstagio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbEstagio.setBounds(125, 105, 140, 20);
		contentPane.add(cbEstagio);

		JLabel lblPesquisador = new JLabel("Pesquisador:");
		lblPesquisador.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPesquisador.setBounds(20, 150, 80, 20);
		contentPane.add(lblPesquisador);

		ArrayList<Pesquisador> pesquisadores = obterPesquisadoresMock();

		cbPesquisador = new JComboBox(pesquisadores.toArray());
		cbPesquisador.setBounds(125, 150, 220, 20);
		contentPane.add(cbPesquisador);

		JLabel lblData = new JLabel("Data In\u00EDcio da Pesquisa:");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblData.setBounds(20, 200, 140, 20);
		contentPane.add(lblData);
		
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
		
		
		dataInicio = new DatePicker(dateSettings);
		contentPane.add(dataInicio,"cell 1 6 2 1,growx");
		final JFrame janelaAtual = this;
		dataInicio.setBounds(170, 203, 147, 19);
		contentPane.add(dataInicio);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(80, 265, 85, 25);
		contentPane.add(btnLimpar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Vacina vacina = new Vacina();

				vacina.setNome(textNome.getText());
				vacina.setPaisOrigem(textPais.getText());
				vacina.setEstagioVacina(""+ cbEstagio.getSelectedItem().toString());
				vacina.setPesquisador((Pesquisador) cbPesquisador.getSelectedItem());
				vacina.setDataInicio(dataInicio.getDate());
				
				
				VacinaController controller = new VacinaController();
				controller.salvar(vacina);

			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(220, 265, 85, 25);
		contentPane.add(btnSalvar);
	}

	private ArrayList<Pesquisador> obterPesquisadoresMock() {
		ArrayList<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();
		pesquisadores.add(new Pesquisador(1, "Albert Sabin", "23690399068", "UNIVERSIDADE DE NOVA YORK", 'm'));
		pesquisadores.add(new Pesquisador(2, "Osvaldo Cruz", " 59924002067", "UNIVERSIDADE FEDERAL DO RIO DE JANEIRO", 'm'));
		pesquisadores.add(new Pesquisador(3, "Carlos Chagas", " 63522680057", "UNIVERSIDADE FEDERAL DO RIO DE JANEIRO", 'm'));
		return pesquisadores;

	}
	
	protected void limparTela() {
		Vacina vacina = new Vacina();
		vacina.setNome("");
		vacina.setPaisOrigem("");
		
		cbPesquisador.setSelectedIndex(0);
		
	}

}
