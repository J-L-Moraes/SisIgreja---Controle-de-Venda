package br.com.dioceseOsasco.Paroquia.View.Configuracao;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;

@SuppressWarnings("serial")
public class FrmConfiguracao extends JDialog {

	private JPanel contentPane;
	private JLabel lblCaminhoServidor;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	JTextField txtCaminhoServidor;
	JTextField txtPortaServidor;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	private JPanel panel;
	private JLabel lblPorta;

	JButton btnAlterarConfiguracao;
	JButton btnCancelarConfiguracao;
	JButton btnTestarConfiguracao;

	public FrmConfiguracao() {
		Interface();
		popularConfiguracao();
		new ConfiguracaoListener(this);
	}

	private void Interface() {

		setTitle("SisIgreja - Alterar parâmetros da conex\u00E3o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConfiguracao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setSize(541, 230);
		this.setLocationRelativeTo(null);


		setResizable(false);
		setModal(true);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Alterar Configura\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(10, 11, 514, 141);
		contentPane.add(panel);
		panel.setLayout(null);

		lblCaminhoServidor = new JLabel("IP ou Hostname: *");
		lblCaminhoServidor.setBounds(28, 24, 100, 14);
		panel.add(lblCaminhoServidor);

		txtCaminhoServidor = new JTextField();
		txtCaminhoServidor.setBounds(138, 17, 223, 28);
		panel.add(txtCaminhoServidor);
		txtCaminhoServidor.setColumns(10);

		lblUsuario = new JLabel("Usu\u00E1rio: *");
		lblUsuario.setBounds(68, 61, 60, 14);
		panel.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(138, 54, 223, 28);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);

		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(68, 99, 60, 14);
		panel.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(138, 91, 223, 28);
		panel.add(txtSenha);

		lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(379, 24, 37, 14);
		panel.add(lblPorta);

		txtPortaServidor = new JTextField();
		txtPortaServidor.setBounds(418, 17, 73, 28);
		txtPortaServidor.setDocument(new ControlarDigitosNumeros(4));
		txtPortaServidor.setColumns(10);
		panel.add(txtPortaServidor);

		btnAlterarConfiguracao = new JButton("Alterar Configura\u00E7\u00E3o");
		btnAlterarConfiguracao.setIcon(new ImageIcon(FrmConfiguracao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Configuracao/substituir.png")));
		btnAlterarConfiguracao.setBounds(118, 157, 153, 32);
		contentPane.add(btnAlterarConfiguracao);
		getRootPane().setDefaultButton(btnAlterarConfiguracao);// Botão Padrão da Tecla Enter

		btnCancelarConfiguracao = new JButton("Cancelar");
		btnCancelarConfiguracao.setIcon(new ImageIcon(FrmConfiguracao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelarConfiguracao.setBounds(398, 159, 107, 28);
		contentPane.add(btnCancelarConfiguracao);

		btnTestarConfiguracao = new JButton("Testar");
		btnTestarConfiguracao.setIcon(new ImageIcon(FrmConfiguracao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/checar.png")));
		btnTestarConfiguracao.setBounds(281, 159, 107, 28);
		contentPane.add(btnTestarConfiguracao);

	}

	/**
	 * Popula as configurações de conexão cajo haja o arquivo. ./propriedade/dados.xml
	 */
	private void popularConfiguracao() {

		try {
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream("./propriedade/dados.xml");

			properties.loadFromXML(file);

			String pro = properties.getProperty("javax.persistence.jdbc.url");

			String caminho = pro.substring(13);
			caminho = caminho.replaceAll("/caixa", "");

			String porta = caminho;

			if (porta.contains(":")) {

				int um;
				int dois;
				um = porta.indexOf(":");
				dois = porta.lastIndexOf("");

				porta = porta.substring(um + 1, dois);
				caminho = caminho.replace(":"+porta, "");
			}else
				porta = "";

			txtCaminhoServidor.setText(caminho);
			txtPortaServidor.setText(porta);

			txtUsuario.setText(properties.getProperty("javax.persistence.jdbc.user"));
			txtSenha.setText(properties.getProperty("javax.persistence.jdbc.password"));
		
		} catch (IOException e) {
		}

	}

}
