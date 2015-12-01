package br.com.dioceseOsasco.Paroquia.View.Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private JLabel lblCadeado;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	JButton btnEntrar;
	JButton btnCancelar;
	JLabel lblEsquecerSenha;
	JLabel lblConfig;


	/**
	 * Método Construtor
	 */
	public FrmLogin() {
		Interface();
		new LoginListener(this);
	}

	
	/**
	 * Create the frame da Interface.
	 */
	private void Interface() {

		setResizable(false);
		setAutoRequestFocus(true);

		setTitle("SisIgreja - Sistema de autentica\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));

		setSize(500, 225);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(116, 58, 67, 14);
		contentPane.add(lblUsuario);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setForeground(Color.BLACK);
		lblSenha.setBounds(122, 94, 61, 14);
		contentPane.add(lblSenha);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(182, 55, 258, 25);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(182, 89, 258, 25);
		contentPane.add(txtSenha);

		btnEntrar = new JButton("Acessar");
		btnEntrar.setIcon(
				new ImageIcon(FrmLogin.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/checar.png")));
		btnEntrar.setBounds(240, 125, 95, 34);
		contentPane.add(btnEntrar);
		getRootPane().setDefaultButton(btnEntrar); // Definir Botão padrão ao
													// pressionar enter

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(
				FrmLogin.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(345, 125, 107, 34);
		contentPane.add(btnCancelar);

		lblEsquecerSenha = new JLabel("Esqueceu a senha, clique aqui.");
		lblEsquecerSenha.setForeground(SystemColor.activeCaption);
		lblEsquecerSenha.setBounds(314, 172, 170, 14);
		contentPane.add(lblEsquecerSenha);

		lblConfig = new JLabel("");
		lblConfig.setToolTipText("Configurar o Banco de Dados");
		lblConfig.setLabelFor(lblConfig);
		lblConfig.setIcon(new ImageIcon(
				FrmLogin.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Configuracao/Configuracao.png")));
		lblConfig.setBounds(445, 7, 42, 47);
		contentPane.add(lblConfig);

		lblCadeado = new JLabel("");
		lblCadeado.setIcon(
				new ImageIcon(FrmLogin.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Login/Cadeado.png")));
		lblCadeado.setBounds(-23, -4, 187, 181);
		contentPane.add(lblCadeado);

		txtUsuario.grabFocus();
	}
}
