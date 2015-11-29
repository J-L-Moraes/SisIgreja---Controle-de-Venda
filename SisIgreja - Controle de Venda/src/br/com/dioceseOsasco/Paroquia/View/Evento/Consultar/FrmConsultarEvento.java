package br.com.dioceseOsasco.Paroquia.View.Evento.Consultar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Evento.Atualizar.FrmAtualizarEvento;
import br.com.dioceseOsasco.Paroquia.View.Evento.Excluir.FrmExcluirEvento;
import br.com.dioceseOsasco.Paroquia.View.Produto.Consultar.FrmConsultaProduto;

@SuppressWarnings("serial")
public class FrmConsultarEvento extends JDialog {
	
	
	JTable table;
	private JPanel panel;
	
	private JLabel lblEvento;
	JTextField txtEvento;
	
	private JLabel lblDataIncial;
	private JLabel lblDataFinal;
	
	JComboBox<?> cmBxDataInicial;
	JComboBox<?> cmBxDataFinal;
	
	JButton btnPesquisar;	
	JButton btnLimparCampos;
	
	EventoTableModel eventoTableModel;
	
	EventoDAO eventoDAO;
	List<TbEvento> tbEvento;
	
	
	
	public FrmConsultarEvento(){
		Interface();
		new ConsultarEventoListener(this);
	}
	//Método Construtor Atualizar Evento
	FrmAtualizarEvento frmAtualizarEvento;
	public FrmConsultarEvento(FrmAtualizarEvento frm){
		this.frmAtualizarEvento = frm;
		Interface();
		new ConsultarEventoListener(this);
	}
	//Método Construtor Excluir Evento
	FrmExcluirEvento frmExcluirEvento;
	public FrmConsultarEvento(FrmExcluirEvento frm){
		this.frmExcluirEvento = frm;
		Interface();
		new ConsultarEventoListener(this);
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
		setTitle("SisIgreja - Consulta de eventos");
		
		
		getContentPane().setLayout(null);
		
		eventoDAO = new EventoDAO();
		tbEvento = eventoDAO.findEventoEntreDatasNome(null, null, null);
		eventoTableModel = new EventoTableModel(tbEvento);
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		table.setModel(eventoTableModel);
		table.setBounds(375, 227, -351, -195);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 148, 731, 291);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(58, 11, 630, 92);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblEvento = new JLabel("Evento:");
		lblEvento.setBounds(46, 56, 46, 14);
		panel.add(lblEvento);
		
		txtEvento = new JTextField();
		txtEvento.setBounds(102, 49, 442, 28);
		panel.add(txtEvento);
		txtEvento.setColumns(10);
		
		lblDataIncial = new JLabel("Data Incial:");
		lblDataIncial.setBounds(116, 18, 62, 14);
		panel.add(lblDataIncial);
		
		cmBxDataInicial = new JCalendar(false);
		cmBxDataInicial.setBounds(182, 11, 100, 28);
		panel.add(cmBxDataInicial);
		
		lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setBounds(347, 15, 62, 22);
		panel.add(lblDataFinal);
		
		cmBxDataFinal = new JCalendar(false);
		cmBxDataFinal.setBounds(408, 12, 100, 28);
		panel.add(cmBxDataFinal);
		
		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setBounds(581, 109, 132, 28);
		getContentPane().add(btnLimparCampos);
		btnLimparCampos.setIcon(new ImageIcon(FrmConsultarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Corrigir/Borracha(24x24).png")));
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(442, 108, 120, 28);
		getContentPane().add(btnPesquisar);
		btnPesquisar.setIcon(new ImageIcon(FrmConsultarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		getRootPane().setDefaultButton(btnPesquisar);//Botão Padrão

		
	}
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			
			// Se houve dois cliques com o mouse...
	        if (event.getClickCount() == 2) {

	            // verifico se foi selecionado um item da tabela.
	            if (table.getSelectedRow() != -1) {
	            	
	            	if (frmAtualizarEvento != null) {
	            		
	            		Integer idEvento =  (Integer) table.getModel().getValueAt(table.getSelectedRow(),0);
	            		String NomeEvento = (String) table.getModel().getValueAt(table.getSelectedRow(),1);
	            		String Data = (String) table.getModel().getValueAt(table.getSelectedRow(),2);
	            		String comunidade = (String) table.getModel().getValueAt(table.getSelectedRow(),3);
	            		frmAtualizarEvento.setEvento(idEvento, NomeEvento, Data, comunidade);
	            		
	            		dispose();
	            	}
	            	if (frmExcluirEvento != null) {
	            	
	            		Integer idEvento =  (Integer) table.getModel().getValueAt(table.getSelectedRow(),0);
	            		String NomeEvento = (String) table.getModel().getValueAt(table.getSelectedRow(),1);
	            		String Data = (String) table.getModel().getValueAt(table.getSelectedRow(),2);
	            		String comunidade = (String) table.getModel().getValueAt(table.getSelectedRow(),3);
	            		
	            		frmExcluirEvento.setEvento(idEvento, NomeEvento, Data, comunidade);
	            		
	            		dispose();
					}
	            }
	        }
		}
	}
}
