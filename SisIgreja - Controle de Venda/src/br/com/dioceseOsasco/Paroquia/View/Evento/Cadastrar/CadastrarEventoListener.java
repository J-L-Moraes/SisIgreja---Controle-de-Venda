package br.com.dioceseOsasco.Paroquia.View.Evento.Cadastrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Evento.Listar.FrmEventosDoDia;

public class CadastrarEventoListener implements ActionListener {

	FrmEventosDoDia frmEventosDoDia;

	FrmCadastrarEvento frm;

	CadastrarEventoListener(FrmCadastrarEvento frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.btnCadastrarEvento.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento = null;
		evento = event.getActionCommand();

		switch (evento) {
		case "Cancelar":
			frm.dispose();
			break;
		case "Cadastrar Evento":

			if (frm.cmbData.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario digitar a data ao evento.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmbData.grabFocus();
				break;
			}
			
			if (frm.cmBxComunidade.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario selecionar uma comunidade relacionado ao evento.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxComunidade.grabFocus();
				break;
			}

			UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();
			
			if (!utilidade.VerificarCamposNulos(frm.txtEvento.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar um nome ao evento e que possua mais de 3 caracteres.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtEvento.grabFocus();
				break;
			}

			String nomeEvento = frm.txtEvento.getText();

			String dataString = (((JCalendar) frm.cmbData).getText());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dataEvento = null;
			try {
				dataEvento = new Date(format.parse(dataString).getTime());
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.\n" + e.getMessage(), frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				break;
			}

			TbEvento tbEvento = new TbEvento();
			tbEvento.setNomeEvento(nomeEvento);
			tbEvento.setDataEvento(dataEvento);
			
			
			TbLocal tbLocal = new TbLocal();
			tbLocal = (TbLocal) frm.cmBxComunidade.getSelectedItem();
			tbEvento.setTbLocal(tbLocal);

			EventoDAO eventoDAO = new EventoDAO();
			if (eventoDAO.cadastrarEvento(tbEvento)) {

				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Evento cadastrado com sucesso.\nDeseja cadastrar outro evento no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
				if (OpcaoSelecionada == JOptionPane.NO_OPTION) {
					if (frmEventosDoDia == null) {
						frm.dispose();
					} else {
						frmEventosDoDia.dispose();
						frm.dispose();
					}
				} else {
					frm.txtEvento.setText(null);
					frm.cmBxComunidade.setSelectedItem(null);
					frm.cmbData = new JCalendar(true);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar o eventoTableModel no sistema.", frm.getTitle(),
						JOptionPane.ERROR_MESSAGE);
				break;
			}
			break;
		default:
			break;
		}

	}

}
