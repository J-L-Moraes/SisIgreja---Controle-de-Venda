package br.com.dioceseOsasco.Paroquia.View.Produto.Cadastrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class CadastrarListener  implements ActionListener {
	
	private FrmCadastrarProduto frm;
	
	CadastrarListener( FrmCadastrarProduto frm){
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener() {
		
		frm.btnCancelar.addActionListener(this);	
		frm.btnCadastrarProduto.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;
		case "Cadastrar Produto":
			
				UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();
				
				if (!utilidade.VerificarCamposNulos(frm.txtProduto.getText())) {
					frm.txtProduto.grabFocus();
					JOptionPane.showMessageDialog(null, "É necessario digitar um nome para o produto.", frm.getTitle(), JOptionPane.OK_OPTION);
					return;
				}
				if (!utilidade.VerificarCamposNulos(frm.txtPreco.getText())) {
					frm.txtPreco.grabFocus();
					JOptionPane.showMessageDialog(null, "É necessario digitar um preço ao produto a ser cadastrado.", frm.getTitle(), JOptionPane.OK_OPTION);
					return;
				}
				
				if (Double.parseDouble(frm.txtPreco.getText().replace(",", ".")) > 10) {
					int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Tem certeza que o valor do produto: "+frm.txtProduto.getText()+" é este "+frm.txtPreco.getText()+" ?"
																			+ "\nCaso não seja reponda a este questionamento não e altere o valor.", frm.getTitle(), JOptionPane.YES_NO_OPTION);  
			        if(OpcaoSelecionada == JOptionPane.NO_OPTION){
			        	return;
			        }
				}
				
				TbProduto produto = new TbProduto();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				
				produto.setAtivo((byte) 1);
				produto.setDescricao(frm.txtProduto.getText());
				
				BigDecimal preco;
				preco = BigDecimal.valueOf(Double.parseDouble(frm.txtPreco.getText().replace(",", ".")));
				produto.setPreco(preco);
				
				if (produtoDAO.cadastrarProduto(produto)) {
					
					int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Produto cadastrado com sucesso.\nDeseja cadastrar outro produto no sistema?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
					if(OpcaoSelecionada == JOptionPane.NO_OPTION){
						frm.dispose();
					}else{
						frm.txtProduto.setText(null);
						frm.txtPreco.setText(null);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao cadastrar o produto.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				}
				
			break;

		}
				
		
	}




}
