package br.com.dioceseOsasco.Paroquia.View.Caixa.AnalisarPedido;

import java.awt.Color;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;

@SuppressWarnings("serial")
public class FrmAnalisarPedido extends JDialog {


	private JPanel contentPane;
	private JLabel lblNumeroPedido;
	public static JTextPane txtPaneNotaFiscal;
	JTextField txtNumeroPedido;
	JButton btnCancelarAtivarVenda;
	JButton btnPesquisar;
	JButton btnImprimirCupom;
	JButton btnImprimirNotaFiscal;
	
	String CodVenda = null; 
	BigInteger CodigoVenda;
	public Boolean cancelarAbertura;

	public BigInteger getCodVenda() {
		return CodigoVenda;
	}

	public void setCodVenda(BigInteger CodigoVenda) {
		this.CodigoVenda = CodigoVenda;
	}


	protected void VerificarPedidoVenda(){

		cancelarAbertura = false;
		
		if (CodVenda == null) {
			CodVenda = JOptionPane.showInputDialog(null, "Digite o Código da Venda do Sistema.", "SisIgreja - Analisar Pedido", JOptionPane.PLAIN_MESSAGE);
			//Clicou em Cancelar
			if (CodVenda == null) {
				cancelarAbertura = true;
				return;
			}
			try{
				setCodVenda(BigInteger.valueOf(Long.valueOf(CodVenda)));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Código Inváido.\nPor favor, digite um código válido no sistema.", "Código Inválido", JOptionPane.ERROR_MESSAGE);
				CodVenda = null;
			}
			VerificarPedidoVenda();
		}else{
			ItemDAO itemDAO = new ItemDAO();
			List<TbItem> ListItem = null;
			ListItem = itemDAO.consultarPedidoItem(getCodVenda());
			
			if (ListItem.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhuma Nota Fiscal com este código.", "Nenhum registro encontrado.", JOptionPane.ERROR_MESSAGE);
				CodVenda = null;
				VerificarPedidoVenda();
			}else{
				new UtilitarioNotaFiscal().setAnalisarVenda(getCodVenda());
				for (TbItem tbItem : ListItem) {
					if (tbItem.getTbVenda().getVendaFinalizada() == (byte) 0) {
						
						btnImprimirNotaFiscal.setEnabled(false);
						btnImprimirCupom.setEnabled(false);
						btnCancelarAtivarVenda.setText("Finalizar Venda");
						btnCancelarAtivarVenda.setToolTipText("Finalize a venda Efetuada.");
						
					}else{
						btnImprimirNotaFiscal.setEnabled(true);
						btnImprimirCupom.setEnabled(true);
						btnCancelarAtivarVenda.setText("Cancelar Venda");
						btnCancelarAtivarVenda.setToolTipText("Cancele a venda Efetuada.");
					}
					txtNumeroPedido.setText(tbItem.getTbVenda().getIdVenda().toString());
				}
			}
			
		}
	}
	
	
	public FrmAnalisarPedido() {	
		Interface();
		VerificarPedidoVenda();
		new AnalisarPedidoListener(this);
	}

	void Interface() {

		setTitle("SisIgreja - Analisar Pedido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAnalisarPedido.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		setSize(534, 500);
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
		scroolPane.setBounds(72, 63, 375, 345);
		scroolPane.setViewportView(txtPaneNotaFiscal);

		contentPane.add(scroolPane);

		btnCancelarAtivarVenda = new JButton("Cancelar venda");
		btnCancelarAtivarVenda.setIcon(new ImageIcon(
				FrmAnalisarPedido.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AlterarItem(24x24).png")));
		btnCancelarAtivarVenda.setBounds(362, 419, 156, 36);
		contentPane.add(btnCancelarAtivarVenda);
		getRootPane().setDefaultButton(btnCancelarAtivarVenda);

		btnPesquisar = new JButton("Consultar Pedido");
		btnPesquisar.setToolTipText("Consulte uma Nota Fiscal atr\u00E1ves do C\u00F3digo do sistema");
		btnPesquisar.setIcon(new ImageIcon(FrmAnalisarPedido.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Search.png")));
		btnPesquisar.setBounds(362, 14, 156, 36);
		contentPane.add(btnPesquisar);
		
		btnImprimirCupom = new JButton("Imprimir Cupom");
		btnImprimirCupom.setIcon(new ImageIcon(FrmAnalisarPedido.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/Impressora.png")));
		btnImprimirCupom.setBounds(20, 419, 158, 36);
		contentPane.add(btnImprimirCupom);
		
		btnImprimirNotaFiscal = new JButton("Imprimir Nota Fiscal");
		btnImprimirNotaFiscal.setIcon(new ImageIcon(FrmAnalisarPedido.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/Impressora.png")));
		btnImprimirNotaFiscal.setBounds(188, 419, 164, 36);
		contentPane.add(btnImprimirNotaFiscal);
		
		txtNumeroPedido = new JTextField();
		txtNumeroPedido.setBounds(150, 19, 202, 27);
		txtNumeroPedido.setDocument(new ControlarDigitosNumeros(40));
		contentPane.add(txtNumeroPedido);
		txtNumeroPedido.setColumns(10);
		
		lblNumeroPedido = new JLabel("N\u00FAmero do Pedido:");
		lblNumeroPedido.setBounds(20, 25, 107, 14);
		contentPane.add(lblNumeroPedido);

	}
}
