package br.com.dioceseOsasco.Paroquia.View.Evento.Cadastrar;

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

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmCadastrarEvento extends JDialog {
	private JLabel lblEvento;
	JTextField txtEvento;
	private JLabel lblData;

	JComboBox<?> cmbData;
	JButton btnCadastrarEvento;
	JButton btnCancelar;
	private JPanel panel;
	private JLabel lblComunidade;
	JComboBox<TbLocal> cmBxComunidade;
	
	public FrmCadastrarEvento() {
		Interface();
		new CadastrarEventoListener(this);
	
	}

	private void Interface() {

		setTitle("SisIgreja - Cadastrar Evento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastrarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(503, 246);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnCadastrarEvento = new JButton("Cadastrar Evento");
		btnCadastrarEvento.setIcon(new ImageIcon(FrmCadastrarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioAdicionar(16x16).fw.png")));
		btnCadastrarEvento.setBounds(200, 175, 137, 30);
		getRootPane().setDefaultButton(btnCadastrarEvento);//Botão Padrão
		getContentPane().add(btnCadastrarEvento);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCadastrarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(347, 175, 102, 30);
		getContentPane().add(btnCancelar);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Cadastrar Evento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 477, 153);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		cmbData = new JCalendar(true);
		cmbData.setBounds(110, 106, 113, 30);
		panel.add(cmbData);
		cmbData.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblData = new JLabel("Data:");
		lblData.setBounds(25, 115, 46, 14);
		panel.add(lblData);
		
		lblEvento = new JLabel("Evento :");
		lblEvento.setBounds(25, 32, 46, 14);
		panel.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setBounds(110, 24, 342, 30);
		txtEvento.setDocument(new ControlarDigitosTeclado(80));
		txtEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEvento.setColumns(10);
		panel.add(txtEvento);
		
		lblComunidade = new JLabel("Comunidade:");
		lblComunidade.setBounds(25, 73, 75, 14);
		panel.add(lblComunidade);
		
		LocalDAO localDAO = new LocalDAO();
		List<TbLocal> tbLocal = localDAO.listarTodasComunidades();
		cmBxComunidade = new JComboBox<TbLocal>();
		cmBxComunidade.setBounds(110, 65, 342, 30);
		for (TbLocal tb_local : tbLocal) {
			cmBxComunidade.addItem(tb_local);
		}
		cmBxComunidade.setSelectedItem(null);
		panel.add(cmBxComunidade);
	
	}
}
