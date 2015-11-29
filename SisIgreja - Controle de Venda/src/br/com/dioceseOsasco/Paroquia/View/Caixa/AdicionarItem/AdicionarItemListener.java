package br.com.dioceseOsasco.Paroquia.View.Caixa.AdicionarItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class AdicionarItemListener implements ActionListener, KeyListener {

	FrmAdicionarItem frm;
	BigInteger codigoVenda;
	

	AdicionarItemListener(FrmAdicionarItem frm, BigInteger codigoVenda) {
		this.frm = frm;
		this.codigoVenda = codigoVenda;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnAdicionarCompra.addActionListener(this);
		frm.btnCancelar.addActionListener(this);
		frm.cmBxProduto.addActionListener(this);
		frm.txtQuantidade.addKeyListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Adicionar \u00E0 Compra":

			
			if (!verificarCampos(frm.txtQuantidade.getText())) {
				JOptionPane.showMessageDialog(null, "Informe a quantidade de saída do produto.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtQuantidade.grabFocus();
				break;
			}
			
			if (frm.cmBxProduto.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "Informe o produto de saída.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxProduto.grabFocus();
				break;
			}
			
			Integer quantidade = Integer.parseInt(frm.txtQuantidade.getText());
			if (  quantidade.equals(0)  ) {
				JOptionPane.showMessageDialog(null, "Informe uma quantidade de saída do produto.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtQuantidade.grabFocus();
				break;
			}
			
			TbProduto tbproduto = ((TbProduto) frm.cmBxProduto.getSelectedItem());
			Integer codProduto = tbproduto.getIdProduto();
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			VendaDAO vendaDAO = new VendaDAO();
			ItemDAO itemDAO = new ItemDAO();
			
			TbItem tbItem = new TbItem();
			tbItem.setQuantidade(quantidade);
			tbItem.setTbProduto(produtoDAO.find(codProduto));
			tbItem.setTbVenda(vendaDAO.find(codigoVenda));

			tbItem.setValorItem( calcularPrecoTotal() );

			if(itemDAO.cadastarItemVenda(tbItem)){

				new UtilitarioNotaFiscal().setAlteracaoNotaFiscal(codigoVenda);
				
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null, "Item adicionado com sucesso.\nDeseja adicionar mais itens?", frm.getTitle() , JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.YES_OPTION){
					frm.txtQuantidade.setText(null);
					frm.cmBxProduto.setSelectedItem(null);
					frm.lblPrecoAPagar.setText(null);
					frm.txtQuantidade.grabFocus();
				}else{
					frm.dispose();
				}
			}else{
				JOptionPane.showMessageDialog(null, "Não foi possivel adiconar o item à compra.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			
			
			break;

		case "comboBoxChanged":
				calcularPrecoTotalFormatado();
			break;
		case "Cancelar":
				frm.dispose();
			break;
		default:
			break;
		}

	}

	private Boolean verificarCampos(String texto) {

		if (texto.isEmpty()) {
			return false;
		}
		if (texto.equals("")) {
			return false;
		}
		return true;

	}

	@Override
	public void keyPressed(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) {
		calcularPrecoTotalFormatado();
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	
	
	
	/**
	 * Calcular Preço e formata para o LabelPrecoAPagar
	 * 
	 */
	void calcularPrecoTotalFormatado(){

		BigDecimal precoTotal = calcularPrecoTotal();

		if (precoTotal == null) 
			return;
		
		frm.lblPrecoAPagar.setText("R$ " + precoTotal.toPlainString());

	}
	
	/**
	 * Calcular o Preço Total do Item.
	 * @return
	 */
	BigDecimal calcularPrecoTotal(){
		
		BigDecimal precoTotal = null;
		
		UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
		
		if ( utilitario.VerificarCampos(frm.txtQuantidade.getText()) && (frm.cmBxProduto.getSelectedItem() != null) ){

			TbProduto produto = ((TbProduto) frm.cmBxProduto.getSelectedItem());

			int qtde = Integer.parseInt(frm.txtQuantidade.getText());
			BigDecimal qtdeConvertida = new java.math.BigDecimal(String.valueOf(qtde));
			BigDecimal preco = produto.getPreco();
			
			precoTotal = qtdeConvertida.multiply(preco);
			
		}
		
		return precoTotal;
	}
	
	

}
