package br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Alterar.FrmAlterarUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Excluir.FrmExcluirUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.RedefinirSenha.FrmRedefinirSenha;

@SuppressWarnings("serial")
public class FrmConsultarUsuario extends JDialog {
	
	private JLabel lblNome;
	private JPanel panel;
	
	JTable table;
	JTextField txtNome;
	
	UsuarioDAO usuarioDAO;
	List<TbUsuario> tbusuario;
	
	/**
	 * Create the frame.
	 */
	public FrmConsultarUsuario() {
		Interface();
		new ConsultaListener(this);
	}
	
	//Método Construtor Excluir Usuario
	FrmExcluirUsuario frmExcluirUsuario;
	public FrmConsultarUsuario(FrmExcluirUsuario frm){
		this.frmExcluirUsuario = frm;
		Interface();
		new ConsultaListener(this);
	}
	//Método Construtor Alterar Usuario
	FrmAlterarUsuario frmAlterarUsuario;
	public FrmConsultarUsuario(FrmAlterarUsuario frm){
		this.frmAlterarUsuario = frm;
		Interface();
		new ConsultaListener(this);
	}
	//Método Construtor Alterar Senha (Outros)
	FrmRedefinirSenha frmRedefinirSenha;
	public FrmConsultarUsuario(FrmRedefinirSenha frm){
		this.frmRedefinirSenha = frm;
		Interface();
		new ConsultaListener(this);
	}
	
	/**
	 * Interface
	 */
	void Interface(){
		
		setSize(783, 488);
		this.setLocationRelativeTo(null);
				
		setModal(true);
		setResizable(false);

		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConsultarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("Consulta de colaboradores");
		
		usuarioDAO = new UsuarioDAO();
		tbusuario = usuarioDAO.findNome("");
		
		table = new JTable();
		table.addMouseListener(new TableMouseListener());
		table.setModel(new UsuarioTableModel(tbusuario));
		table.setBounds(375, 227, -351, -195);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 137, 731, 302);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Digite o nome do Colaborador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(60, 29, 638, 82);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(31, 36, 46, 14);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(87, 29, 498, 28);
		panel.add(txtNome);
		txtNome.setColumns(10);

		
	}
	
	
	private class TableMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent event) {
			
			// Se houve dois cliques com o mouse...
	        if (event.getClickCount() == 2) {

	            // verifico se foi selecionado um item da tabela.
	            if (table.getSelectedRow() != -1) {
	            	
	            	if (frmExcluirUsuario != null) {

	            		String nome = (String) table.getModel().getValueAt(table.getSelectedRow(),0);
	            		String usuario = (String) table.getModel().getValueAt(table.getSelectedRow(),2);
	            		String DataNascimento = (String) table.getModel().getValueAt(table.getSelectedRow(),3);
	            		
	            		frmExcluirUsuario.setEvento(usuario, nome, DataNascimento);
	            		
	            		dispose();
	            	}
	            	if (frmAlterarUsuario != null) {

	            		String usuario = (String) table.getModel().getValueAt(table.getSelectedRow(),2);
	            		
	            		frmAlterarUsuario.setEvento(usuario);
	            		
	            		dispose();
	            	}
	            	if (frmRedefinirSenha != null) {
	            		
	            		String usuario = (String) table.getModel().getValueAt(table.getSelectedRow(),2);
	            		
	            		frmRedefinirSenha.setEvento(usuario);
	            		
	            		dispose();
	            	}
	            	
	            	
	            	
	            }
	        }
		}
	}
	

}
