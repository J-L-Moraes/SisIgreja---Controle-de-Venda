package br.com.dioceseOsasco.Paroquia.View.Caixa.Pagamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Impressora.criarArquivo;

public class PagamentoListener {
	
	BigInteger codigoVenda;
	FrmPagamento frm;
	
	public PagamentoListener (FrmPagamento frm, BigInteger codigoVenda){
		this.codigoVenda = codigoVenda;
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		
		frm.rdbtnPagamentoEmCartao.addChangeListener(new RdbtnPagamentoChangeListener());
		frm.rdbtnPagamentoEmDinheiro.addChangeListener(new RdbtnPagamentoChangeListener());
		frm.btnCancelar.addActionListener(new BtnCancelarActionListener());
		frm.btnFinalizarCompra.addActionListener(new BtnFinalizarCompraActionListener());
		
	}
	//Botão Cancelar
	private class BtnCancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			frm.dispose();
		}
	}
	
	//Botão Finalizar Compra
	private class BtnFinalizarCompraActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (frm.rdbtnPagamentoEmDinheiro.isSelected()) {
			
				if (new UtilitarioVerificarCampos().VerificarCampos(frm.txtValorPago.getText())) {
					
					ItemDAO itemDAO = new ItemDAO();
					Object ValorPagar = itemDAO.consultarPrecoPagar(codigoVenda);
					
					if ( ValorPagar == null) {
						JOptionPane.showMessageDialog(null, "Não foi vendido nenhum produto.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					Double valorPagar = Double.valueOf(ValorPagar.toString());
					Double valorPago = Double.valueOf(frm.txtValorPago.getText().replace(",", "."));

					if (valorPagar > valorPago) {
						JOptionPane.showMessageDialog(null, "O Valor Pago é menor que o valor da Venda.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					
					Double troco = valorPago - valorPagar;
					TbVenda tbVenda = new TbVenda();
					VendaDAO vendaDAO = new VendaDAO();
					
					tbVenda = vendaDAO.find(codigoVenda);
					tbVenda.setVendaFinalizada((byte) 1); 
					
					if (vendaDAO.atualizarVenda(tbVenda) ){
						new UtilitarioNotaFiscal().setFinalizarVenda(codigoVenda, valorPago, troco);
						
						//Imprimindo
						new criarArquivo("NotaFiscal.txt").criaTxtNotaFiscal();
						new criarArquivo("CupomFiscal.txt").criaTxtCupom(codigoVenda);
						
						JOptionPane.showMessageDialog(null, "Venda concluida com sucesso.\nTroco do Cliente R$ "+troco, frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						
						//Desabilitando os botões, pois a venda está finalizada.
						frm.frmCx.btnCancelarPedido.setEnabled(false);
						frm.frmCx.btnAdicionarItem.setEnabled(false);
						frm.frmCx.btnAlterarItem.setEnabled(false);
						frm.frmCx.btnExcluirItem.setEnabled(false);
						frm.frmCx.btnFinalizarPedido.setEnabled(false);
						
						frm.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Ocorreu um erro ao finalizar a venda.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					}
					
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Deve ser inserido um valor para à ser pago.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				
			}else if (frm.rdbtnPagamentoEmCartao.isSelected()) {
				TbVenda tbVenda = new TbVenda();
				VendaDAO vendaDAO = new VendaDAO();
				
				tbVenda = vendaDAO.find(codigoVenda);
				tbVenda.setVendaFinalizada((byte) 1); 
				
				if (vendaDAO.atualizarVenda(tbVenda) ){
					new UtilitarioNotaFiscal().setFinalizarVenda(codigoVenda, "Cartão");
					
					//Imprimindo
					new criarArquivo("NotaFiscal.txt").criaTxtNotaFiscal();
					new criarArquivo("CupomFiscal.txt").criaTxtCupom(codigoVenda);
					

					//Desabilitando os botões, pois a venda está finalizada.
					frm.frmCx.btnCancelarPedido.setEnabled(false);
					frm.frmCx.btnAdicionarItem.setEnabled(false);
					frm.frmCx.btnAlterarItem.setEnabled(false);
					frm.frmCx.btnExcluirItem.setEnabled(false);
					frm.frmCx.btnFinalizarPedido.setEnabled(false);
					
					JOptionPane.showMessageDialog(null, "Venda concluida com sucesso.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					
					frm.dispose();
				}else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao finalizar a venda.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	//Controle dos RadionButtons
	private class RdbtnPagamentoChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			
			if (frm.rdbtnPagamentoEmCartao.isSelected()) {
				frm.txtValorPago.setEnabled(false);
			}else if (frm.rdbtnPagamentoEmDinheiro.isSelected()){
				frm.txtValorPago.setEnabled(true);
			}
			
		}
	}
	

}
