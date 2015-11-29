package br.com.dioceseOsasco.Paroquia.View.Usuario.Excluir;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class FrmExcluirUsuario extends JDialog {
	
	private JPanel panel;
	private JLabel lblUsuario;
	JTextField txtUsuario;
	JLabel lblNome;
	JLabel lblDataDeNascimento;
	
	JButton btnProcurar;
	JButton btnPesquisarUsurio;
	JButton btnExcluirUsurio;
	JButton btnCancelar;
	
	public FrmExcluirUsuario(){
		Interface();
		new ExcluirUsuarioListener(this);
	}

	/**
	 * Interface
	 */
	private void Interface() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmExcluirUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Excluir usu\u00E1rio");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setSize(707, 229);
		this.setLocationRelativeTo(null);
		
		setModal(true);
		setResizable(false);
		
		getContentPane().setLayout(null);
		
		btnExcluirUsurio = new JButton("Excluir usu\u00E1rio");
		btnExcluirUsurio.setIcon(new ImageIcon(FrmExcluirUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Excluir.png")));
		btnExcluirUsurio.setBounds(346, 150, 154, 34);
		getContentPane().add(btnExcluirUsurio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmExcluirUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(510, 150, 125, 34);
		getContentPane().add(btnCancelar);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 675, 121);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblDataDeNascimento = new JLabel("");
		lblDataDeNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(10, 76, 491, 28);
		panel.add(lblDataDeNascimento);
		
		lblNome = new JLabel("");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 50, 491, 28);
		panel.add(lblNome);
		
		lblUsuario = new JLabel("Usu\u00E1rio :");
		lblUsuario.setBounds(10, 23, 46, 14);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(65, 16, 282, 28);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnProcurar = new JButton("Procurar usu\u00E1rio");
		btnProcurar.setBounds(357, 11, 144, 33);
		panel.add(btnProcurar);
		btnProcurar.setIcon(new ImageIcon(FrmExcluirUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		
		btnPesquisarUsurio = new JButton("Localizar usu\u00E1rio");
		btnPesquisarUsurio.setBounds(511, 11, 154, 33);
		panel.add(btnPesquisarUsurio);
		btnPesquisarUsurio.setIcon(new ImageIcon(FrmExcluirUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Pesquisar.png")));
		setBackground(Color.WHITE);
	}

	public void setEvento(String usuario, String nome, String dataNascimento) {
		
		txtUsuario.setText(usuario);
		lblNome.setText("Nome: "+nome);
		lblDataDeNascimento.setText("Data de Nascimento: "+dataNascimento);	
	}
}
