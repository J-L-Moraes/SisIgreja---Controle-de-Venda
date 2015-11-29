package br.com.dioceseOsasco.Paroquia.View.Local.Excluir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;

public class ExcluirLocalListener implements ActionListener {
	
	FrmExcluirLocal frm;
	
	ExcluirLocalListener(FrmExcluirLocal frm){
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener() {
		frm.btnCancelar.addActionListener(this);
		frm.btnExcluirComunidade.addActionListener(this);
	}



	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;

		case "Excluir":
			
			if (frm.cmBxComunidadeCadastrada.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario escolher a comunidade que deseja excluir.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxComunidadeCadastrada.grabFocus();
				break;
			}
						
			TbLocal tbLocal = new TbLocal();
			LocalDAO localDAO = new LocalDAO();

			tbLocal = (TbLocal) frm.cmBxComunidadeCadastrada.getSelectedItem();
			Integer idLocal = tbLocal.getIdLlocal();
			
			if (localDAO.removerComunidade(idLocal)) {
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Comunidade excluida com sucesso.\nDeseja excluir outra comunidade no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
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
				}
				
			}

			break;
		}
		
	}

}
