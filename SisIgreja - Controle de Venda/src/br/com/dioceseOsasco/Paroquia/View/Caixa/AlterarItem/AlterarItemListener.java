package br.com.dioceseOsasco.Paroquia.View.Caixa.AlterarItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class AlterarItemListener implements ActionListener, KeyListener {

	private FrmAlterarItem frm;
	BigInteger codigoVenda;
	
	TbItem tbItem;
	TbProduto tbProduto;

	AlterarItemListener(FrmAlterarItem frm, BigInteger codigoVenda) {
		this.frm = frm;
		this.codigoVenda = codigoVenda;
		adicionaListener();
		
		

		tbItem = new TbItem();
		tbProduto = new TbProduto();
	}
	
	private void adicionaListener() {
	
		frm.btnCancelar.addActionListener(this);
		frm.btnAlterarProduto.addActionListener(this);
		
		frm.txtQuantidade.addKeyListener(this);
		
		
		
		frm.cmBxProdutoVendido.addItemListener(new CmBxProdutoVendidoItemListener());
		frm.cmBxProdutoVenda.addItemListener(new CmBxProdutoVendaItemListener());
	}





	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento  = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
			frm.dispose();
			
			break;
		case "Alterar Produto":
			
			if (frm.cmBxProdutoVenda.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Informe o produto a ser trocado.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxProdutoVenda.grabFocus();
				break;
			}
			if (frm.cmBxProdutoVendido.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Informe o produto a vendido que deve ser trocado.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxProdutoVendido.grabFocus();
				break;
			}
			
			UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
			if (!utilitario.VerificarCampos(frm.txtQuantidade.getText())) {
				JOptionPane.showMessageDialog(null, "Informe uma quantidade de saída do produto.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtQuantidade.grabFocus();
				break;
			}
			
			
			tbItem = (TbItem) frm.cmBxProdutoVendido.getSelectedItem();
			
			tbItem.setQuantidade(Integer.parseInt(frm.txtQuantidade.getText()));
			tbProduto = (TbProduto) frm.cmBxProdutoVenda.getSelectedItem();
			tbItem.setTbProduto(tbProduto);
			
			//Preço Total
			BigDecimal precoTotal = calcularPrecoTotal();
			if (precoTotal == null) {
				JOptionPane.showMessageDialog(null, "Não foi possivel converter o Preço Total, para inseri-lo no banco de dados.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				break;
			}	
			tbItem.setValorItem(precoTotal);

		
			//Alterar os Dados
			if (frm.itemDAO.alterarItemVenda(tbItem)) {

				new UtilitarioNotaFiscal().setAlteracaoNotaFiscal(codigoVenda);
				
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null, "Item alterado com sucesso.\nDeseja alterar mais itens?", frm.getTitle() , JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.YES_OPTION){
			
					VendaDAO vendaDAO = new VendaDAO();
					TbVenda tbVenda = new TbVenda();
					tbVenda = vendaDAO.find(codigoVenda);

					List<TbItem> ListItem = frm.itemDAO.consultarProdutos(tbVenda);
					frm.cmBxProdutoVendido.removeAllItems();
					for (TbItem tbItem : ListItem) {
						frm.cmBxProdutoVendido.addItem(tbItem);
					}
					frm.cmBxProdutoVendido.setSelectedItem(null);
					
					frm.cmBxProdutoVenda.setSelectedItem(null);
					frm.lblPrecoPagar.setText(null);					
					frm.txtQuantidade.setText(null);
					frm.txtQuantidade.grabFocus();
				}else{
					frm.dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Não foi possivel alterar o item.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				break;
			}
			
			break;

		default:
			break;
		}
		
	}
	
	private class CmBxProdutoVendaItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			calcularPrecoTotalFormatado();
		}
	}
	
	private class CmBxProdutoVendidoItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {

			if (frm.cmBxProdutoVendido.getSelectedObjects() != null) {

				tbItem = (TbItem) frm.cmBxProdutoVendido.getSelectedItem();

				if (tbItem != null) {
					BigDecimal precoTotal = null;
					try {
						int qtde = tbItem.getQuantidade();
						BigDecimal qtdeConvertida = new java.math.BigDecimal(String.valueOf(qtde));
						BigDecimal preco = tbItem.getTbProduto().getPreco();

						precoTotal = qtdeConvertida.multiply(preco);
					} catch (Exception e) {
						System.out.println("Não foi possicel Convereter \n\n" + e.getMessage());
					}

					if (precoTotal != null) {
						frm.lblPrecoPagar.setText("R$ " + precoTotal);
						frm.txtQuantidade.setText(String.valueOf(tbItem.getQuantidade()));
						frm.cmBxProdutoVenda.getModel().setSelectedItem(tbItem.getTbProduto());
					}
				}

			}

		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		calcularPrecoTotalFormatado();
	}

	@Override
	public void keyPressed(KeyEvent arg0) { }


	@Override
	public void keyTyped(KeyEvent arg0) { }
	


	
	
	/**
	 * Calcular Preço e formata para o LabelPrecoAPagar
	 * 
	 */
	void calcularPrecoTotalFormatado(){

		BigDecimal precoTotal = calcularPrecoTotal();

		if (precoTotal == null) 
			return;
		
		frm.lblPrecoPagar.setText("R$ " + precoTotal.toPlainString());

	}
	
	/**
	 * Calcular o Preço Total do Item.
	 * @return
	 */
	BigDecimal calcularPrecoTotal(){
		
		BigDecimal precoTotal = null;
		
		UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
		
		if ( utilitario.VerificarCampos(frm.txtQuantidade.getText()) && (frm.cmBxProdutoVenda.getSelectedItem() != null) ){

			TbProduto produto = ((TbProduto) frm.cmBxProdutoVenda.getSelectedItem());

			int qtde = Integer.parseInt(frm.txtQuantidade.getText());
			BigDecimal qtdeConvertida = new java.math.BigDecimal(String.valueOf(qtde));
			BigDecimal preco = produto.getPreco();
			
			precoTotal = qtdeConvertida.multiply(preco);	
		}		
		return precoTotal;
	}
	
}
