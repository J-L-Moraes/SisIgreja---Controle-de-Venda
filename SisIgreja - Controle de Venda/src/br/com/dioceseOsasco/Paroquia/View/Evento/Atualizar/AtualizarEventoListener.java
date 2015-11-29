package br.com.dioceseOsasco.Paroquia.View.Evento.Atualizar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Evento.Consultar.FrmConsultarEvento;

public class AtualizarEventoListener implements ActionListener {

	private int OpcaoSelecionada;
	boolean PesquisarCancelar;
	FrmAtualizarEvento frm;

	AtualizarEventoListener(FrmAtualizarEvento frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.btnAtualizarEvento.addActionListener(this);
		frm.btnLocalizarEvento.addActionListener(this);
		frm.btnPesquisarCancelar.addActionListener(this);
		
		PesquisarCancelar = true;
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				
				if (!PesquisarCancelar) {
					OpcaoSelecionada =JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair e não realizar a alteração no evento?", frm.getTitle(),JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (OpcaoSelecionada == JOptionPane.NO_OPTION) {
						break;
					}
				}
				
				frm.dispose();
			break;
		case "Localizar Evento":	

			new FrmConsultarEvento(frm).setVisible(true);

			break;
		case "Pesquisar | Cancelar":
			// if PesquisarCancelar quer dizer que irá Pesquisar no Banco de Dados
			if (PesquisarCancelar) {
				if (!frm.txtIdEvento.getText().isEmpty()) {
					
					//Pesquiso no Banco atraves do ID
					TbEvento tbEvento = new TbEvento();
					tbEvento = null;
					EventoDAO eventoDAO = new EventoDAO();
					tbEvento = eventoDAO.find(Integer.parseInt(frm.txtIdEvento.getText()));
					
					if (tbEvento == null) {
						JOptionPane.showMessageDialog(null, "Não foi encontrado registro para este evento de código: "+frm.txtIdEvento.getText()+" .", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
						frm.txtEvento.grabFocus();
						break;
					}
					
					frm.txtEvento.setText(tbEvento.getNomeEvento());
					frm.lblDataDoEvento.setText(new ConversorData().ConverterDateSQLEmString(tbEvento.getDataEvento()));
					frm.cmBxComunidade.getModel().setSelectedItem(tbEvento.getTbLocal());
					frm.controleDeEdicao(true);
					PesquisarCancelar = false;
					frm.btnPesquisarCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
					frm.getRootPane().setDefaultButton(frm.btnAtualizarEvento);// Botão Padrão da Tecla Enter
				}
			}else{
				frm.btnPesquisarCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
				frm.getRootPane().setDefaultButton(frm.btnPesquisarCancelar);// Botão Padrão da Tecla Enter
				frm.controleDeEdicao(false);
				PesquisarCancelar = true;
			}
			
			break;
		case "Atualizar Evento":
				
				UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();
			
				if (!utilidade.VerificarCamposNulos(frm.txtEvento.getText())) {
					JOptionPane.showMessageDialog(null, "É necessario digitar um nome ao evento e que possua mais de 3 caracteres.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
					frm.txtEvento.grabFocus();
					break;
				
				}
				
				if (frm.cmBxComunidade.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "É necessario selecionar uma comunidade relacionado ao evento.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					frm.cmBxComunidade.grabFocus();
					break;
				}
				
				TbEvento tbEvento = new TbEvento();
				EventoDAO eventoDAO = new EventoDAO();
				tbEvento = eventoDAO.find(Integer.parseInt(frm.txtIdEvento.getText()));
				
				TbLocal tbLocal = new TbLocal();
				tbLocal = (TbLocal) frm.cmBxComunidade.getModel().getSelectedItem();
				
				
				tbEvento.setTbLocal(tbLocal);
				
				tbEvento.setNomeEvento(frm.txtEvento.getText());
				tbEvento.setTbLocal(tbLocal);
				
				if(eventoDAO.atualizarEvento(tbEvento)){
					OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Evento atualizado com sucesso.\nDeseja atualizar outro evento no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
					if (OpcaoSelecionada == JOptionPane.NO_OPTION) 
						frm.dispose();
					else{
						PesquisarCancelar = true;
						frm.txtEvento.setText(null);
						frm.lblDataDoEvento.setText(null);
						frm.txtIdEvento.setText(null);
						frm.cmBxComunidade.setSelectedItem(null);
						frm.controleDeEdicao(false);
						frm.btnPesquisarCancelar.setIcon(new ImageIcon(FrmAtualizarEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
						frm.getRootPane().setDefaultButton(frm.btnPesquisarCancelar);// Botão Padrão da Tecla Enter
					}
				}
			break;

		}
		
	}
	
}
