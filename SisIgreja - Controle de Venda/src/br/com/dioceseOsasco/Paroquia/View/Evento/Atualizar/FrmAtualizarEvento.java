package br.com.dioceseOsasco.Paroquia.View.Evento.Atualizar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmAtualizarEvento extends JDialog{
	
	private JLabel lblEvento;
	JTextField txtEvento;
	private JLabel lblData;
	JLabel lblDataDoEvento;
	
	private JPanel panel;
	private JLabel lblIdEvento;
	JTextField txtIdEvento;

	JButton btnAtualizarEvento;
	JButton btnCancelar;
	JButton btnLocalizarEvento;
	JButton btnPesquisarCancelar;
	
	AtualizarEventoListener atualizarEventoListener;
	private JLabel lblComunidade;
	JComboBox<TbLocal> cmBxComunidade;
	
	public FrmAtualizarEvento() {
		Interface();
		atualizarEventoListener = new AtualizarEventoListener(this);
	
	}

	private void Interface() {

		setTitle("SisIgreja - Atualizar Evento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(658, 282);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnAtualizarEvento = new JButton("Atualizar Evento");
		btnAtualizarEvento.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioAtualizar(16x16).png")));
		btnAtualizarEvento.setBounds(362, 212, 143, 30);
		btnAtualizarEvento.setEnabled(false);
		getContentPane().add(btnAtualizarEvento);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(515, 212, 102, 30);
		getContentPane().add(btnCancelar);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Atualizar Evento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 628, 190);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("Data:");
		lblData.setBounds(85, 152, 46, 14);
		panel.add(lblData);
		
		lblEvento = new JLabel("Evento :");
		lblEvento.setBounds(74, 76, 57, 14);
		panel.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setBounds(132, 67, 471, 30);
		txtEvento.setEnabled(false);
		txtEvento.setDocument(new ControlarDigitosTeclado(80));
		txtEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEvento.setColumns(10);
		panel.add(txtEvento);
		
		lblIdEvento = new JLabel("C\u00F3digo do Evento:");
		lblIdEvento.setBounds(25, 34, 106, 21);
		panel.add(lblIdEvento);
		
		txtIdEvento = new JTextField();
		txtIdEvento.setBounds(132, 28, 198, 30);
		txtIdEvento.setDocument(new ControlarDigitosNumeros(9));
		txtIdEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdEvento.setColumns(10);
		panel.add(txtIdEvento);
		
		btnLocalizarEvento = new JButton("Localizar Evento");
		btnLocalizarEvento.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioConsultar(16x16).png")));
		btnLocalizarEvento.setBounds(437, 28, 139, 30);
		panel.add(btnLocalizarEvento);
		
		lblDataDoEvento = new JLabel("");
		lblDataDoEvento.setToolTipText("A Data n\u00E3o pode ser modificada, pois, ocasionaria inconsist\u00EAncia na base de dados. \r\nCaso necessite consulte o Administrador do Sistema.");
		lblDataDoEvento.setBounds(132, 149, 130, 26);
		panel.add(lblDataDoEvento);
		
		btnPesquisarCancelar = new JButton("Pesquisar | Cancelar");
		btnPesquisarCancelar.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnPesquisarCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		btnPesquisarCancelar.setBounds(347, 27, 72, 30);
		getRootPane().setDefaultButton(btnPesquisarCancelar);// Botão Padrão da Tecla Enter
		
		panel.add(btnPesquisarCancelar);
		
		lblComunidade = new JLabel("Comunidade :");
		lblComunidade.setBounds(51, 116, 80, 14);
		panel.add(lblComunidade);
		
		
		LocalDAO localDAO = new LocalDAO();
		List<TbLocal> tbLocal = localDAO.listarTodasComunidades();
		cmBxComunidade = new JComboBox<TbLocal>();
		cmBxComunidade.setBounds(132, 108, 471, 30);
		for (TbLocal tb_local : tbLocal) {
			cmBxComunidade.addItem(tb_local);
		}
		cmBxComunidade.setEnabled(false);
		cmBxComunidade.setSelectedItem(null);
		panel.add(cmBxComunidade);
		
	}
	
	
	
	public void setEvento(int idEvento, String nomeEvento, String dataEvento, String comunidade){
	
		txtIdEvento.setText(String.valueOf(idEvento));
		
		//Pesquiso no Banco atraves do ID
		TbEvento tbEvento = new TbEvento();
		tbEvento = null;
		EventoDAO eventoDAO = new EventoDAO();
		tbEvento = eventoDAO.find(Integer.parseInt(txtIdEvento.getText()));
		
		
		txtEvento.setText(tbEvento.getNomeEvento());
		lblDataDoEvento.setText(new ConversorData().ConverterDateSQLEmString(tbEvento.getDataEvento()));
		cmBxComunidade.getModel().setSelectedItem(tbEvento.getTbLocal());
		controleDeEdicao(true);
		atualizarEventoListener.PesquisarCancelar = false;
		btnPesquisarCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		getRootPane().setDefaultButton(btnAtualizarEvento);// Botão Padrão da Tecla Enter
	}
	
	void controleDeEdicao(boolean controle){
		
		txtIdEvento.setEnabled(!controle);
		btnLocalizarEvento.setEnabled(!controle);
		
		txtEvento.setEnabled(controle);
		cmBxComunidade.setEnabled(controle);
		btnAtualizarEvento.setEnabled(controle);
		
	}
}
