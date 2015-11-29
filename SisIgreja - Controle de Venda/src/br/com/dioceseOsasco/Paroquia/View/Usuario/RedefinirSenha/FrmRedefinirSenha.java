package br.com.dioceseOsasco.Paroquia.View.Usuario.RedefinirSenha;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmRedefinirSenha extends JDialog {
	
	private JPanel panel;
	private JLabel lblUsurio;
	JTextField txtUsuario;
	private JLabel lblSenha;
	JPasswordField txtSenha;
	
	JButton btnAlterarSenha;
	JButton btnCancelar;
	JButton btnLocalizarUsuario;

	
	public FrmRedefinirSenha(){
		Interface();
		new RedefinirSenhaListener(this);
	}

	private void Interface() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRedefinirSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Redefinir Senha ");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(626, 221);
		this.setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 593, 117);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setBounds(29, 28, 46, 14);
		panel.add(lblUsurio);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(85, 20, 314, 30);
		txtUsuario.setDocument(new ControlarDigitosTeclado(30));
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnLocalizarUsuario = new JButton("Localizar Usu\u00E1rio");
		btnLocalizarUsuario.setIcon(new ImageIcon(FrmRedefinirSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Pesquisar.png")));
		btnLocalizarUsuario.setBounds(415, 34, 161, 38);
		panel.add(btnLocalizarUsuario);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(29, 69, 46, 14);
		panel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(85, 61, 314, 30);
		txtSenha.setDocument(new ControlarDigitosTeclado(30));
		panel.add(txtSenha);
		
		btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.setIcon(new ImageIcon(FrmRedefinirSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Senha.png")));
		btnAlterarSenha.setBounds(291, 139, 156, 38);
		getContentPane().add(btnAlterarSenha);
		getRootPane().setDefaultButton(btnAlterarSenha); //Botão Padrão
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmRedefinirSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(457, 139, 128, 38);
		getContentPane().add(btnCancelar);
		
	}

	public void setEvento(String usuario) {
		txtUsuario.setText(usuario);
	}
}
