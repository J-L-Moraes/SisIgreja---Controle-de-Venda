package br.com.dioceseOsasco.Paroquia.View.Evento.Listar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.View.Caixa.Principal.FrmCaixa;
import br.com.dioceseOsasco.Paroquia.View.Evento.Cadastrar.FrmCadastrarEvento;

public class EventoDoDiaListener implements ActionListener {

	TbEvento tbEvento;
	FrmCaixa frmCaixa;

	FrmEventosDoDia frm;

	EventoDoDiaListener(FrmEventosDoDia frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.btnCadastrarEvento.addActionListener(this);
		frm.btnConfirmar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento = null;
		evento = event.getActionCommand();

		switch (evento) {
		case "Cancelar":
				frm.setIdEvento(-1); 
				frm.setVisible(false);
			break;
		case "Cadastrar Evento":
				FrmCadastrarEvento frmCadastrarEvento = new FrmCadastrarEvento();
				frmCadastrarEvento.setVisible(true);
				frm.dispose();
			break;
		case "Confirmar":

				if (frm.cmbEventoDoDia.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione o evento do Dia / Caixa .", frm.getTitle(),
							JOptionPane.INFORMATION_MESSAGE);
					frm.cmbEventoDoDia.grabFocus();
					break;
				}
	
				try {
					tbEvento = (TbEvento) frm.cmbEventoDoDia.getSelectedItem();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao converter dados no sistema", frm.getTitle(),
							JOptionPane.ERROR_MESSAGE);
					break;
				}
	
				int idEvento = tbEvento.getIdEvento();
	
				frm.setIdEvento(idEvento);
				frm.setVisible(false);
			break;
		default:
			break;
		}
	}

}
