package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.PesquisadorController;

import vo.Pesquisador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroPesquisador extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textInstituicao;
	private JTextField textCpf;
	private JRadioButton rdbtnmasculino;
	private JRadioButton rdbtnFeminino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPesquisador frame = new CadastroPesquisador();
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
	public CadastroPesquisador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(20, 30, 50, 20);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNome.setBounds(70, 30, 170, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(20, 70, 35, 20);
		contentPane.add(lblCpf);

		try {
			MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
			textCpf = new JFormattedTextField(maskFormatter);
			textCpf.setBounds(70, 70, 170, 20);
			getContentPane().add(textCpf);
			textCpf.setColumns(10);
			;
		} catch (Exception e) {
			System.out.println("Erro na mascara de formatacao de CPF no painel de cadastro do pesquisador.");
			e.printStackTrace();
		}

		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o");
		lblInstituicao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInstituicao.setBounds(20, 105, 60, 20);
		contentPane.add(lblInstituicao);

		textInstituicao = new JTextField();
		textInstituicao.setBounds(85, 105, 180, 20);
		contentPane.add(textInstituicao);
		textInstituicao.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(20, 140, 45, 20);
		contentPane.add(lblSexo);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.setBounds(50, 205, 85, 25);
		contentPane.add(btnLimpar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char sexoSelecionado = ' ';

				if (rdbtnmasculino.isSelected()) {
					sexoSelecionado = 'M';
				} else if (rdbtnFeminino.isSelected()) {
					sexoSelecionado = 'F';
				}

				PesquisadorController controller = new PesquisadorController();
				String mensagem = controller.salvar(textNome.getText(), textCpf.getText(), textInstituicao.getText());
				JOptionPane.showMessageDialog(null, mensagem);

			}
		});
		btnSalvar.setBounds(195, 205, 85, 25);
		contentPane.add(btnSalvar);

		rdbtnmasculino = new JRadioButton("Masculino");
		rdbtnmasculino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnmasculino.setBounds(70, 140, 85, 20);
		contentPane.add(rdbtnmasculino);

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFeminino.setBounds(180, 140, 95, 20);
		contentPane.add(rdbtnFeminino);

		ButtonGroup sexo = new ButtonGroup();
		sexo.add(rdbtnmasculino);
		sexo.add(rdbtnFeminino);
	}

	private void limparCampos() {
		this.textNome.setText("");
		this.textCpf.setText("");
		this.textInstituicao.setText("");
	}

}
