package br.com.dioceseOsasco.Paroquia.View.Local.Consultar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Produto.Consultar.FrmConsultaProduto;

@SuppressWarnings("serial")
public class FrmConsultarLocal extends JDialog {

	JTable table;
	private JPanel panel;
	JTextField txtComunidade;
	
	LocalDAO localDAO;
	List<TbLocal> tb_local;
	private JLabel lblComunidade;


	/**
	 * Create the frame.
	 */
	public FrmConsultarLocal() {
		Interface();
		new ConsultaListener(this);
	}
	
	/**
	 * Interface
	 */
	void Interface(){
		
		setSize(783, 488);
		this.setLocationRelativeTo(null);
		
		setResizable(false);
		setModal(true);
		
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConsultaProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Consulta de Comunidades");
		
		
		localDAO = new LocalDAO();
		tb_local = localDAO.listarTodasComunidades();
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new LocalTableModel(tb_local));
		table.setBounds(375, 227, -351, -195);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 148, 731, 291);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(70, 32, 566, 88);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		txtComunidade = new JTextField();
		txtComunidade.setBounds(115, 29, 414, 28);
		panel.add(txtComunidade);
		txtComunidade.setColumns(10);
		
		lblComunidade = new JLabel("Comunidade :");
		lblComunidade.setBounds(20, 36, 85, 14);
		panel.add(lblComunidade);
	}
}
