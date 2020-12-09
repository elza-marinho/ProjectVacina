package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.PessoaController;
import vo.Pessoa;

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

public class CadastroPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textIdade;
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
					CadastroPessoa frame = new CadastroPessoa();
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
	public CadastroPessoa() {
		setTitle("Cadastro de Pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(20, 25, 45, 20);
		contentPane.add(lblNome);

		textNome = new JTextField();
		textNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNome.setBounds(75, 25, 180, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(20, 70, 45, 20);
		contentPane.add(lblCpf);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIdade.setBounds(20, 110, 45, 20);
		contentPane.add(lblIdade);

		textIdade = new JTextField();
		textIdade.setBounds(75, 110, 25, 20);
		contentPane.add(textIdade);
		textIdade.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(20, 145, 45, 20);
		contentPane.add(lblSexo);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLimpar.setBounds(100, 245, 85, 25);
		contentPane.add(btnLimpar);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char sexoSelecionado = ' ';

				if (rdbtnmasculino.isSelected()) {
					sexoSelecionado = 'M';
				} else if (rdbtnFeminino.isSelected()) {
					sexoSelecionado = 'F';
				}

				String mensagem = "";

				if (!mensagem.equals("")) {
					JOptionPane.showMessageDialog(null, "Preencha os campos");
				} else {
					PessoaController controller = new PessoaController();

				
					String salvar = controller.salvar( textNome.getText(), textCpf.getText(),  Integer.parseInt(textIdade.getText()));
					JOptionPane.showMessageDialog(null, "Preencha os campos");

					
				}
			}

			
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalvar.setBounds(245, 245, 85, 25);
		contentPane.add(btnSalvar);

		rdbtnmasculino = new JRadioButton("Masculino");
		rdbtnmasculino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnmasculino.setBounds(75, 145, 95, 20);
		contentPane.add(rdbtnmasculino);

		rdbtnFeminino = new JRadioButton("Feminino");
		rdbtnFeminino.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFeminino.setBounds(185, 145, 95, 20);
		contentPane.add(rdbtnFeminino);

		ButtonGroup sexo = new ButtonGroup();
		sexo.add(rdbtnmasculino);
		sexo.add(rdbtnFeminino);

		try {

			MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
			textCpf = new JFormattedTextField(maskFormatter);
			textCpf.setBounds(75, 72, 180, 20);
			getContentPane().add(textCpf);
			textCpf.setColumns(10);
		} catch (Exception e) {
			System.out.println("Erro na mascara de formatacao de CPF no painel de cadastro do usuario.");
			e.printStackTrace();
		}

	}

	protected void limparCampos() {
		this.textNome.setText("");
		this.textCpf.setText("");
		this.textIdade.setText("");

	}
}
