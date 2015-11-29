package br.com.dioceseOsasco.Paroquia.View.Local.Atualizar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class AtualizarLocalListener implements ActionListener {
	
	FrmAtualizarLocal frm;
	
	AtualizarLocalListener(FrmAtualizarLocal frm){
		this.frm = frm;
		adicionaListener();
	}

	

	private void adicionaListener() {
		frm.btnCancelar.addActionListener(this);
		frm.btnAtualizarComunidade.addActionListener(this);
		frm.cmBxComunidadeCadastrada.addItemListener(new CmBxComunidadeCadastradaItemListener());
	}



	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;

		case "Atualizar":
			
			UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();
			
			if (!utilidade.VerificarCamposNulos(frm.txtComunidade.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar o nome da Comunidade (ex. Matriz).", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtComunidade.grabFocus();
				break;
			}
			if (frm.cmBxComunidadeCadastrada.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario escolher a comunidade que deseja alterar.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxComunidadeCadastrada.grabFocus();
				break;
			}
			
			String comunidade = frm.txtComunidade.getText();
			
			TbLocal tbLocal = new TbLocal();
			LocalDAO localDAO = new LocalDAO();
			
			tbLocal = (TbLocal) frm.cmBxComunidadeCadastrada.getSelectedItem();
			tbLocal.setNomeComunidade(comunidade);
			
			
			if (localDAO.AtualizarLocal(tbLocal)) {
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Comunidade atualizada com sucesso.\nDeseja cadastrar outra comunidade no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
				if (OpcaoSelecionada == JOptionPane.NO_OPTION) {
					frm.dispose();
					break;
				}else{

					frm.cmBxComunidadeCadastrada.removeAllItems();
					List<TbLocal> Local = localDAO.listarTodasComunidades();
					for (TbLocal tb_Local : Local) {
						frm.cmBxComunidadeCadastrada.addItem(tb_Local);
					}
					
					frm.cmBxComunidadeCadastrada.setSelectedItem(null);
					frm.txtComunidade.setText(null);
				}
				
			}

			break;
		}
		
	}
	//ComboBox Alterado
	private class CmBxComunidadeCadastradaItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			
			if (frm.cmBxComunidadeCadastrada.getSelectedObjects() != null) {
				try {
					String comunidade = frm.cmBxComunidadeCadastrada.getSelectedItem().toString();
					frm.txtComunidade.setText(comunidade);	
				} catch (Exception e) {	}
				
			}
		}
	}
	
	

}
