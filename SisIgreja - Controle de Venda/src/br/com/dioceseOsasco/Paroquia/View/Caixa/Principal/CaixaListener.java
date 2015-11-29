package br.com.dioceseOsasco.Paroquia.View.Caixa.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.View.Caixa.AdicionarItem.FrmAdicionarItem;
import br.com.dioceseOsasco.Paroquia.View.Caixa.AlterarItem.FrmAlterarItem;
import br.com.dioceseOsasco.Paroquia.View.Caixa.ExcluirItem.FrmExcluirItem;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Caixa.Pagamento.FrmPagamento;

public class CaixaListener implements ActionListener {
	
	FrmCaixa frm;
	int selectedOption;
	
	CaixaListener(FrmCaixa frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnNovo.addActionListener(this);
		frm.btnAdicionarItem.addActionListener(this);
		frm.btnAlterarItem.addActionListener(this);
		frm.btnExcluirItem.addActionListener(this);
		frm.btnFinalizarPedido.addActionListener(this);
		frm.btnCancelarPedido.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String evento = null;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Novo":
				selectedOption = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja iniciar uma nova venda?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					frm.CadastrarVenda();
					

					//Desabilitando os botões, pois a venda está finalizada.
					frm.btnCancelarPedido.setEnabled(true);
					frm.btnAdicionarItem.setEnabled(true);
					frm.btnAlterarItem.setEnabled(true);
					frm.btnExcluirItem.setEnabled(true);
					frm.btnFinalizarPedido.setEnabled(true);
					
					new UtilitarioNotaFiscal().setInciarNotaFiscal(frm.getCodVenda());
					
				}
			break;
		case "Adicionar Item":
			  new FrmAdicionarItem(frm.getCodVenda()).setVisible(true);
			  
			break;
		case "Alterar Item":
				new FrmAlterarItem(frm.getCodVenda()).setVisible(true);
			break;
		case "Excluir Item":
				new FrmExcluirItem(frm.getCodVenda()).setVisible(true);
			break;
		case "Finalizar Pedido":
				new FrmPagamento(frm.getCodVenda(), frm).setVisible(true);
				
			break;
		case "Cancelar Pedido":
			
        	if (frm.btnCancelarPedido.isEnabled()) {
        		 selectedOption = JOptionPane.showConfirmDialog(null,"Deseja cancelar a Venda?", frm.getTitle(), JOptionPane.YES_NO_OPTION);  
			}else{
				selectedOption = JOptionPane.YES_OPTION;  
			}
        	
            if(selectedOption == JOptionPane.YES_OPTION){  
            	frm.dispose();                          
            } 
			
			break;
		}
	}

	
	

}
