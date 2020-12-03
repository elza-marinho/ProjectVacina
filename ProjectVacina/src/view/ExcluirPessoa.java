package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controller.PessoaController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExcluirPessoa extends JFrame {

	private JPanel contentPane;
	private JTextField textCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcluirPessoa frame = new ExcluirPessoa();
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
	public ExcluirPessoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExcluir = new JLabel("Digite o CPF  que deseja excluir");
		lblExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExcluir.setBounds(35, 35, 200, 20);
		contentPane.add(lblExcluir);
		

		try {

			MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
			textCpf = new JFormattedTextField(maskFormatter);
			textCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textCpf.setBounds(75, 70, 150, 20);
			getContentPane().add(textCpf);
			textCpf.setColumns(10);
		} catch (Exception e) {
			System.out.println("Erro na mascara de formatacao de CPF no painel de cadastro do usuario.");
			e.printStackTrace();
		}

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaController controller = new PessoaController();
				controller.excluirPorCpf(textCpf.getText());
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExcluir.setBounds(135, 180, 85, 25);
		contentPane.add(btnExcluir);
	}

}
