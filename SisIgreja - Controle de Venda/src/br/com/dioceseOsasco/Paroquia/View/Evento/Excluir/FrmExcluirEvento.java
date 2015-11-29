package br.com.dioceseOsasco.Paroquia.View.Evento.Excluir;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;
import br.com.dioceseOsasco.Paroquia.View.Configuracao.FrmConfiguracao;

@SuppressWarnings("serial")
public class FrmExcluirEvento extends JDialog{
	
	JLabel lblNomeEvento;
	JLabel lblDataDoEvento;
	
	private JPanel panel;
	private JLabel lblIdEvento;
	JTextField txtIdEvento;

	JButton btnExcluirEvento;
	JButton btnCancelar;
	JButton btnLocalizarEvento;
	JButton btnPesquisar;
	JLabel lblComunidade;
	
	public FrmExcluirEvento() {
		Interface();
		new ExcluirEventoListener(this);
	}

	private void Interface() {

		setTitle("SisIgreja - Excluir Evento");
		setIconImage(Toolkit.getDefaultToolkit().getImage( FrmConfiguracao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(609, 254);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnExcluirEvento = new JButton("Excluir Evento");
		btnExcluirEvento.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioExcluir(16x16).png")));
		btnExcluirEvento.setBounds(268, 180, 130, 30);
		getContentPane().add(btnExcluirEvento);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(408, 180, 102, 30);
		getContentPane().add(btnCancelar);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Excluir Evento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 583, 158);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNomeEvento = new JLabel();
		lblNomeEvento.setBounds(36, 75, 379, 30);
		panel.add(lblNomeEvento);
		lblNomeEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblIdEvento = new JLabel("C\u00F3digo do Evento:");
		lblIdEvento.setBounds(26, 33, 102, 21);
		panel.add(lblIdEvento);
		
		txtIdEvento = new JTextField();
		txtIdEvento.setBounds(132, 28, 195, 30);
		txtIdEvento.setDocument(new ControlarDigitosNumeros(9));
		txtIdEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdEvento.setColumns(10);
		panel.add(txtIdEvento);
		
		btnLocalizarEvento = new JButton("Localizar Evento");
		btnLocalizarEvento.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioConsultar(16x16).png")));
		btnLocalizarEvento.setBounds(408, 28, 139, 30);
		panel.add(btnLocalizarEvento);
		
		lblDataDoEvento = new JLabel("");
		lblDataDoEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDoEvento.setToolTipText("A Data n\u00E3o pode ser modificada, pois, ocasionaria inconsist\u00EAncia na base de dados. \r\nCaso necessite consulte o Administrador do Sistema.");
		lblDataDoEvento.setBounds(430, 75, 139, 30);
		panel.add(lblDataDoEvento);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		btnPesquisar.setBounds(337, 28, 61, 30);
		panel.add(btnPesquisar);
		
		lblComunidade = new JLabel();
		lblComunidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblComunidade.setBounds(36, 105, 379, 30);
		panel.add(lblComunidade);
		
		getRootPane().setDefaultButton(btnPesquisar);// Botão Padrão da Tecla Enter
		
	}
	
	/**
	 * Seta os campos pela chamada do FrmConsultarEvento
	 * @param idEvento
	 * @param nomeEvento
	 * @param dataEvento
	 * @param comunidade
	 */
	public void setEvento(int idEvento, String nomeEvento, String dataEvento, String comunidade){
		txtIdEvento.setText(String.valueOf(idEvento));
		lblNomeEvento.setText("Evento: "+nomeEvento);
		lblDataDoEvento.setText("Data: "+dataEvento);
		lblComunidade.setText("Comunidade: "+comunidade);
		btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		getRootPane().setDefaultButton(btnExcluirEvento);// Botão Padrão da Tecla Enter
	}
	

}
