package br.com.dioceseOsasco.Paroquia.View.Caixa.AnalisarPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Impressora.criarArquivo;

public class AnalisarPedidoListener implements ActionListener {
	
	FrmAnalisarPedido frm;
	
	public AnalisarPedidoListener(FrmAnalisarPedido frm){
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		frm.btnPesquisar.addActionListener(this);
		frm.btnCancelarAtivarVenda.addActionListener(this);
		frm.btnImprimirCupom.addActionListener(this);
		frm.btnImprimirNotaFiscal.addActionListener(this);
		frm.btnPesquisar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar Venda":
			int selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a venda efetuada?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				
				TbVenda tbVenda = new TbVenda();
				VendaDAO vendaDAO = new VendaDAO();
				
				tbVenda = vendaDAO.find(frm.getCodVenda());
				tbVenda.setVendaFinalizada((byte) 0); 
				
				if (vendaDAO.atualizarVenda(tbVenda) ){
					JOptionPane.showMessageDialog(null, "Venda cancelada com sucesso no sistema!", frm.getTitle(), JOptionPane.OK_OPTION);
					frm.btnImprimirNotaFiscal.setEnabled(false);
					frm.btnImprimirCupom.setEnabled(false);
					frm.btnCancelarAtivarVenda.setText("Finalizar Venda");
					frm.btnCancelarAtivarVenda.setToolTipText("Finalize a venda Efetuada.");
					
				}
			}
			
			
			break;
		case "Consultar Pedido":
			
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtNumeroPedido.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario Informar o Número do Pedido.", frm.getTitle(), JOptionPane.OK_OPTION);
				frm.txtNumeroPedido.grabFocus();
				break;
			}
			
			try{
				frm.setCodVenda(BigInteger.valueOf(Long.valueOf(frm.txtNumeroPedido.getText())));
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Código Inváido.\nPor favor, digite um código válido no sistema.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				frm.txtNumeroPedido.grabFocus();
				break;
			}
			
			
			ItemDAO itemDAO = new ItemDAO();
			List<TbItem> ListItem = null;
			ListItem = itemDAO.consultarPedidoItem(frm.getCodVenda());
			
			if (ListItem.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhuma Nota Fiscal com este código passado.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				frm.txtNumeroPedido.grabFocus();
				frm.setCodVenda(null);
				
				FrmAnalisarPedido.txtPaneNotaFiscal.setText(null);
				frm.btnImprimirNotaFiscal.setEnabled(false);
				frm.btnImprimirCupom.setEnabled(false);
				frm.btnCancelarAtivarVenda.setEnabled(false);
				
				break;
			}else {
				
				new UtilitarioNotaFiscal().setAnalisarVenda(frm.getCodVenda());
				for (TbItem tbItem : ListItem) {
					if (tbItem.getTbVenda().getVendaFinalizada() == (byte) 0) {
						
						frm.btnImprimirNotaFiscal.setEnabled(false);
						frm.btnImprimirCupom.setEnabled(false);
						frm.btnCancelarAtivarVenda.setText("Finalizar Venda");
						frm.btnCancelarAtivarVenda.setToolTipText("Finalize a venda Efetuada.");
						break;
						
					}else{
						frm.btnImprimirNotaFiscal.setEnabled(true);
						frm.btnImprimirCupom.setEnabled(true);
						frm.btnCancelarAtivarVenda.setText("Cancelar Venda");
						frm.btnCancelarAtivarVenda.setToolTipText("Cancele a venda Efetuada.");
						break;
					}
				}
			}
			break;
		case "Finalizar Venda":
			
			selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja finalizar está venda?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				
				TbVenda tbVenda = new TbVenda();
				VendaDAO vendaDAO = new VendaDAO();
				
				tbVenda = vendaDAO.find(frm.getCodVenda());
				tbVenda.setVendaFinalizada((byte) 1); 
				
				if (vendaDAO.atualizarVenda(tbVenda) ){
					JOptionPane.showMessageDialog(null, "Venda finalizada com sucesso no sistema!\nPor favor, caso deseje imprimir cupom ou nota fiscal clique nos botões correpondente.", frm.getTitle(), JOptionPane.OK_OPTION);
					frm.btnImprimirNotaFiscal.setEnabled(true);
					frm.btnImprimirCupom.setEnabled(true);
					frm.btnCancelarAtivarVenda.setText("Cancelar Venda");
					frm.btnCancelarAtivarVenda.setToolTipText("Cancele a venda Efetuada.");
					
				}
			}
			break;
		case "Imprimir Nota Fiscal":
			selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja imprimir a Nota Fiscal?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				//Imprimindo
				new criarArquivo("NotaFiscal.txt").criaTxtNotaFiscal();
			}
			
			break;
		case "Imprimir Cupom":
			selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja imprimir os cupons de pedido?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				new criarArquivo("CupomFiscal.txt").criaTxtCupom(frm.getCodVenda());
			}
			break;
		
		}
	}

}
