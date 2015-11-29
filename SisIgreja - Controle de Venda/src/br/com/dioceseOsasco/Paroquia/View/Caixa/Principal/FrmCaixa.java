package br.com.dioceseOsasco.Paroquia.View.Caixa.Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.sql.Time;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Evento.Listar.FrmEventosDoDia;

@SuppressWarnings("serial")
public class FrmCaixa extends JDialog {

	
	TbEvento tbEvento;
	public TbVenda tbVenda;
	
	
	private JPanel contentPane;
	public static JTextPane txtPaneNotaFiscal;
	public JButton btnFinalizarPedido;
	public JButton btnCancelarPedido;
	public JButton btnAdicionarItem;
	public JButton btnAlterarItem;
	public JButton btnExcluirItem;
	JButton btnNovo;

	BigInteger CodVenda;
	public BigInteger getCodVenda() {
		return CodVenda;
	}

	public void setCodVenda(BigInteger codVenda) {
		CodVenda = codVenda;
	}



	public FrmCaixa() {
		Interface();
		new CaixaListener(this);
	}

	/**
	 * Interface
	 */
	void Interface() {

		setTitle("SisIgreja - Caixa Aberto ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Evento Fechar do Formulario...
		addWindowListener(new java.awt.event.WindowAdapter() {  
	        public void windowClosing(java.awt.event.WindowEvent e) {  
	            if (e.getID() == WindowEvent.WINDOW_CLOSING){  
	            	int selectedOption;
	            	if (btnCancelarPedido.isEnabled()) {
	            		 selectedOption = JOptionPane.showConfirmDialog(null,"Deseja cancelar a Venda?", getTitle(), JOptionPane.YES_NO_OPTION);  
					}else{
						selectedOption = JOptionPane.YES_OPTION;  
					}
	            	
	                if(selectedOption == JOptionPane.YES_OPTION){  
	                	dispose();                          
	                }     
	            }     
	        }  
	    });
		
		setModal(true);
		setResizable(false);
		
		setSize(417, 500);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPaneNotaFiscal = new JTextPane();
		txtPaneNotaFiscal.setEditable(false);
		
		// Alinhar Texto no Centro
		StyledDocument docNotaFiscal = txtPaneNotaFiscal.getStyledDocument();
		SimpleAttributeSet SimpleAttributeSet = new SimpleAttributeSet();
		StyleConstants.setAlignment(SimpleAttributeSet, StyleConstants.ALIGN_JUSTIFIED);
		docNotaFiscal.setParagraphAttributes(0, docNotaFiscal.getLength(), SimpleAttributeSet, false);

		JScrollPane scroolPane = new JScrollPane(txtPaneNotaFiscal);
		scroolPane.setBounds(20, 72, 375, 345);
		scroolPane.setViewportView(txtPaneNotaFiscal);

		contentPane.add(scroolPane);

		btnFinalizarPedido = new JButton("Finalizar Pedido");
		btnFinalizarPedido.setToolTipText("Finalize sua venda.");
		btnFinalizarPedido.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AlterarItem(24x24).png")));
		btnFinalizarPedido.setBounds(55, 425, 156, 36);
		contentPane.add(btnFinalizarPedido);
		getRootPane().setDefaultButton(btnFinalizarPedido);

		btnCancelarPedido = new JButton("Cancelar Pedido");
		btnCancelarPedido.setToolTipText("Cancele seu pedido");
		btnCancelarPedido.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelarPedido.setBounds(221, 425, 156, 36);
		contentPane.add(btnCancelarPedido);

		btnAdicionarItem = new JButton("Adicionar Item");
		btnAdicionarItem.setToolTipText("Adicionar item a compra");
		btnAdicionarItem.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnAdicionarItem.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AdicionarItem.png")));
		btnAdicionarItem.setBounds(121, 11, 56, 50);
		contentPane.add(btnAdicionarItem);

		btnAlterarItem = new JButton("Alterar Item");
		btnAlterarItem.setToolTipText("Altere o item da venda.");
		btnAlterarItem.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnAlterarItem.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AlterarItem.png")));
		btnAlterarItem.setBounds(187, 11, 56, 50);
		contentPane.add(btnAlterarItem);

		btnExcluirItem = new JButton("Excluir Item");
		btnExcluirItem.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/RemoverItem.png")));
		btnExcluirItem.setToolTipText("Remova o item da venda");
		btnExcluirItem.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnExcluirItem.setBounds(253, 11, 56, 50);
		contentPane.add(btnExcluirItem);
		
		btnNovo = new JButton("Novo");
		btnNovo.setToolTipText("Iniciar uma nova venda no sistema");
		btnNovo.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnNovo.setIcon(new ImageIcon(FrmCaixa.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Add.png")));
		btnNovo.setBounds(10, 11, 56, 50);
		contentPane.add(btnNovo);

	}


	
	/**
	 * Cadastra e seta o Codigo de Venda no sistema.
	 */
	public void CadastrarVenda() {

		//TbVenda
		tbVenda = new TbVenda();
		
		// Hora do Sistema...
		Time time = new java.sql.Time(new java.util.Date().getTime());
		tbVenda.setHoraVenda(time);
			
		// Data do Sistema...
		//Date data = new java.sql.Date(new java.util.Date().getTime());

		// TbEvento		
		EventoDAO eventoDAO = new EventoDAO();
		//tbEvento = null;		

		if(tbEvento == null){
			//Abre a Listagem de Eventos do dia...
			FrmEventosDoDia frmEventoDoDia = new FrmEventosDoDia();
			
			while (frmEventoDoDia.getIdEvento() == 0) {
				frmEventoDoDia.dispose();
				frmEventoDoDia = new FrmEventosDoDia();
				frmEventoDoDia.setVisible(true);
			}
				
			tbEvento = eventoDAO.find(frmEventoDoDia.getIdEvento()) ;
			frmEventoDoDia.dispose();	
		}
		tbVenda.setTbEvento(tbEvento);

		// TbUsuario
		@SuppressWarnings("static-access")
		TbUsuario tbUsuario = new TbUsuario().getInstance(true);//TbUsuario já instanciada no Login
		
		tbVenda.setTbUsuario(tbUsuario);

		tbVenda.setVendaFinalizada((byte) 0); //Venda Não Finalizada
		
		VendaDAO vendaDAO = new VendaDAO();
		setCodVenda(vendaDAO.cadastrarVenda(tbVenda));

	}
}
