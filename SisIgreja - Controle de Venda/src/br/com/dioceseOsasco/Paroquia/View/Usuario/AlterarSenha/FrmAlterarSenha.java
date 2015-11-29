package br.com.dioceseOsasco.Paroquia.View.Usuario.AlterarSenha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmAlterarSenha extends JDialog {
	
	private JPanel panel;
	private JLabel lblUsuario;
	private JLabel lblSenhaAntiga;
	private JLabel lblSenhaNova;
	private JLabel lblRepetaASenha;

	JPasswordField txtSenhaAntiga;
	JPasswordField txtSenhaNova;
	JPasswordField txtRepitirSenha;
	
	JButton btnAlterarSenha;
	JButton btnCancelar;

	
	public FrmAlterarSenha(){
		Interface();
		new AlterarSenhaListener(this);
	}

	private void Interface() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAlterarSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Alterar Senha ");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(626, 287);
		this.setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 586, 187);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		@SuppressWarnings("static-access")
		TbUsuario tbUsuario = new TbUsuario().getInstance(true);
		lblUsuario = new JLabel("Usuário: "+ tbUsuario.getUsuario() );
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setBounds(37, 14, 477, 30);
		panel.add(lblUsuario);
		
		lblSenhaAntiga = new JLabel("Senha Antiga:");
		lblSenhaAntiga.setBounds(37, 66, 84, 14);
		panel.add(lblSenhaAntiga);
		
		txtSenhaAntiga = new JPasswordField();
		txtSenhaAntiga.setBounds(132, 56, 382, 30);
		txtSenhaAntiga.setDocument(new ControlarDigitosTeclado(30));
		panel.add(txtSenhaAntiga);
		
		lblSenhaNova = new JLabel("Senha Nova:");
		lblSenhaNova.setBounds(37, 105, 85, 14);
		panel.add(lblSenhaNova);
		
		txtSenhaNova = new JPasswordField();
		txtSenhaNova.setBounds(132, 97, 382, 30);
		txtSenhaNova.setDocument(new ControlarDigitosTeclado(30));
		panel.add(txtSenhaNova);
		
		lblRepetaASenha = new JLabel("Repita a senha:");
		lblRepetaASenha.setBounds(37, 146, 96, 14);
		panel.add(lblRepetaASenha);
		
		txtRepitirSenha = new JPasswordField();
		txtRepitirSenha.setBounds(132, 138, 382, 30);
		txtRepitirSenha.setDocument(new ControlarDigitosTeclado(30));
		panel.add(txtRepitirSenha);
		
		btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.setIcon(new ImageIcon(FrmAlterarSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Senha.png")));
		btnAlterarSenha.setBounds(276, 209, 156, 38);
		getContentPane().add(btnAlterarSenha);
		getRootPane().setDefaultButton(btnAlterarSenha); //Botão Padrão
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAlterarSenha.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(442, 209, 128, 38);
		getContentPane().add(btnCancelar);
		
	}
}
