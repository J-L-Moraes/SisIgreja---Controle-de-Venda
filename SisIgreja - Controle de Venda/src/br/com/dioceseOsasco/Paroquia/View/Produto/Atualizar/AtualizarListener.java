package br.com.dioceseOsasco.Paroquia.View.Produto.Atualizar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class AtualizarListener implements MouseListener, ActionListener {

	private static int idProdutoComboBox; //Chave Primaria resgatada do ComboBox
	private static int indexComboBox;
	TbProduto tbproduto;
	
	//Quando se removia os dados do comboBox ocorria um erro, este erro era ocasioando porque ele possui o eventoTableModel comboBoxChanged
	private boolean AtualizarComboBox;   
	
	
	private FrmAtualizarProduto frm;
	
	AtualizarListener( FrmAtualizarProduto frm){
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener() {
		
		frm.btnCancelar.addActionListener(this);
		frm.cmBxProduto.addActionListener(this);
		frm.btnAtualizarProduto.addActionListener(this);
		
		indexComboBox = -10; //Incializando a variavel para que não incie com 0 e o primiro item do comboBox não seja igual e não armazene o ID
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		String evento;
		evento = event.getActionCommand();

		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;

		case "comboBoxChanged":
			
			if (!AtualizarComboBox) {

				if (frm.cmBxProduto.getSelectedIndex() != indexComboBox ){

					if (!evento.equals("comboBoxEdited")) {
						indexComboBox = frm.cmBxProduto.getSelectedIndex();
						frm.cmBxProdutoBACKUP.setSelectedIndex(indexComboBox);  

						try {

							tbproduto = ( (TbProduto) frm.cmBxProdutoBACKUP.getSelectedItem());
							idProdutoComboBox = tbproduto.getIdProduto();
							
							frm.txtPreco.setText(tbproduto.getPreco().toString());
							if (tbproduto.getAtivo() == 1) {
								frm.cmBxAtivo.setSelectedItem("Sim");
							}else{
								frm.cmBxAtivo.setSelectedItem("N\u00E3o");
							}
							
						} catch (Exception e) {				}
					}
					
				}
			}
			frm.EstadoComponente(true);
			
			
			break;
			
		case "Atualizar Produto":
					
			frm.EstadoComponente(false);
			
			TbProduto produto = new TbProduto();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			
			produto.setIdProduto(idProdutoComboBox);
			
			UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
			
			String descricao = "";
			try{
				descricao = frm.cmBxProduto.getSelectedItem().toString();
			}catch (Exception e){
				JOptionPane.showMessageDialog(null, "Por favor, selecione um dos itens a ser atualizado.", frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
				frm.EstadoComponenteInicial(false);
				break;
			}
			if (!utilitario.VerificarCamposNulos(descricao)) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro: \nVocê só pode atualizar um produto já cadastrado no sistema."
													, frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
				frm.EstadoComponenteInicial(false);
				break;
			}
			
			//Tamanho da Descrição no Banco de Dados
			if (descricao.length() >  50) {
				JOptionPane.showMessageDialog(null, "A um limite de 50 carateres neste campo.\nPor Favor, reduza o tamanho do nome do produto."
						, frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
				frm.EstadoComponenteInicial(true);
				break;
			}
			
			produto.setDescricao(descricao);
			
			if (("Sim").equals(frm.cmBxAtivo.getSelectedItem())) {
				produto.setAtivo((byte) 1);
			}else{
				produto.setAtivo((byte) 0);
			}
			
			BigDecimal preco = null;
			
			try {
				preco = BigDecimal.valueOf(Double.parseDouble(frm.txtPreco.getText().replace(",", ".")));
				
				if(Double.parseDouble(frm.txtPreco.getText().replace(",", ".")) == 0) {
					JOptionPane.showMessageDialog(null, "Por favor insira um valor ao produto.", frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
					frm.txtPreco.grabFocus();
					frm.EstadoComponente(true);
					break;
				}
				
				if (!utilitario.VerificarCamposNulos(preco.toString())) {
					JOptionPane.showMessageDialog(null, "O Valor do produto não pode estar vazio."
							+ "", frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
					frm.txtPreco.grabFocus();
					frm.EstadoComponente(true);
					break;
				}
				if (Double.parseDouble(frm.txtPreco.getText().replace(",", ".")) > 10) {
					int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Tem certeza que o valor do produto: "+ frm.cmBxProduto.getSelectedItem().toString()+" é este "+frm.txtPreco.getText()+" ?"
																			+ "\nCaso não seja reponda a este questionamento não e altere o valor.", frm.getTitle(), JOptionPane.YES_NO_OPTION);  
			        if(OpcaoSelecionada == JOptionPane.NO_OPTION){
			        	frm.txtPreco.grabFocus();
			        	frm.EstadoComponente(true);
			        	break;
			        }
				}
			} catch (NumberFormatException  e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao converter o campo preço.", frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
				frm.EstadoComponente(true);
				break;
			}
			produto.setPreco(preco);
			
			if (produtoDAO.atualizarProduto(produto)) {
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null, "Produto atualizado com sucesso.\nDeseja atualizar outro produto ?", frm.getTitle() , JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.NO_OPTION){
		        	frm.dispose();
		        	break;
		        }else{
		        	
		        	AtualizarComboBox = true;
			    	frm.popularComboBox();
					frm.txtPreco.setText("");
					
					frm.cmBxAtivo.setSelectedItem(null);
					AtualizarComboBox = false;	

					frm.EstadoComponenteInicial(false);
		        }
			}else{
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o produto.", frm.getTitle() , JOptionPane.INFORMATION_MESSAGE);
				frm.EstadoComponente(true);
			}
			
			break;
		default:
			
			break;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) { }

	@Override
	public void mouseEntered(MouseEvent arg0) { }

	@Override
	public void mouseExited(MouseEvent arg0) { 	}

	@Override
	public void mousePressed(MouseEvent arg0) { }

	@Override
	public void mouseReleased(MouseEvent arg0){ }

}

