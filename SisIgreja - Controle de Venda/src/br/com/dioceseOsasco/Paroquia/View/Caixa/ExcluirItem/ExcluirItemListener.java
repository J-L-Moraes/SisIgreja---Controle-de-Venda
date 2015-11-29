package br.com.dioceseOsasco.Paroquia.View.Caixa.ExcluirItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigInteger;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;

public class ExcluirItemListener implements ActionListener {

	private FrmExcluirItem frm;
	BigInteger codigoVenda;
	
	TbItem tbItem;
	TbProduto tbProduto;

	ExcluirItemListener(FrmExcluirItem frm, BigInteger codigoVenda) {
		this.frm = frm;
		this.codigoVenda = codigoVenda;
		adicionaListener();
		
		

		tbItem = new TbItem();
		tbProduto = new TbProduto();
	}
	
	private void adicionaListener() {
	
		frm.btnCancelar.addActionListener(this);
		frm.btnAlterarProduto.addActionListener(this);
		
		frm.cmBxProduto.addItemListener(new CmBxProdutoItemListener());
	
	}





	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento  = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;
		case "Excluir Produto":
			
			if (frm.cmBxProduto.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "Selecione o produto a ser excluido.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxProduto.grabFocus();
				break;
			}
			
			int selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o item da venda? ",frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {

				tbItem = (TbItem) frm.cmBxProduto.getSelectedItem();
				
				ItemDAO itemDAO = new ItemDAO();
				
				if (itemDAO.removerItem(tbItem.getIdItem())) {
					
					new UtilitarioNotaFiscal().setAlteracaoNotaFiscal(codigoVenda);
					
					selectedOption = JOptionPane.showConfirmDialog(null, "Item excluido com sucesso.\nVocê deseja ainda excluir mais itens da Venda?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
					if (selectedOption == JOptionPane.YES_OPTION) {
						
						VendaDAO vendaDAO = new VendaDAO();
						TbVenda tbVenda = new TbVenda();
						tbVenda = vendaDAO.find(codigoVenda);
						itemDAO = new ItemDAO();
					
						frm.cmBxProduto.removeAllItems();
						List<TbItem> ListItem = itemDAO.consultarProdutos(tbVenda);
						for (TbItem tbItem : ListItem) {
							frm.cmBxProduto.addItem(tbItem);
						}
						frm.cmBxProduto.setSelectedItem(null);
						frm.lblQtde.setText(null);
						frm.lblPrecoPagar.setText(null);
						
						
					}else{
						frm.dispose();
					}

						
				} else {
					JOptionPane.showMessageDialog(null,"Ocorreu um erro ao tentar excluir o item no banco de dados.","Erro ao excluir item da compra", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
			
			
			break;

		default:
			break;
		}
		
	}
	
	
	private class CmBxProdutoItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {

			if (frm.cmBxProduto.getSelectedObjects() != null) {

				tbItem = (TbItem) frm.cmBxProduto.getSelectedItem();

				if (tbItem != null) {
						frm.lblQtde.setText(String.valueOf(tbItem.getQuantidade()));
						frm.lblPrecoPagar.setText(String.valueOf(tbItem.getValorItem()));
				}

			}

		}
		
	}



	
	


}
