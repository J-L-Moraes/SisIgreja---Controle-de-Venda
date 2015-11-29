package br.com.dioceseOsasco.Paroquia.View.Evento.Excluir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Evento.Consultar.FrmConsultarEvento;

public class ExcluirEventoListener implements ActionListener {

	FrmExcluirEvento frm;
	private UtilitarioVerificarCampos utilidade;

	ExcluirEventoListener(FrmExcluirEvento frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.btnExcluirEvento.addActionListener(this);
		frm.btnLocalizarEvento.addActionListener(this);
		frm.btnPesquisar.addActionListener(this);
		
		utilidade = new UtilitarioVerificarCampos();
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;
		case "Localizar Evento":	

			new FrmConsultarEvento(frm).setVisible(true);

			break;
		case "Pesquisar":
			
			
			
			if (utilidade.VerificarCampos(frm.txtIdEvento.getText())){
			
				//Pesquiso no Banco atraves do ID
				TbEvento tbEvento = new TbEvento();
				tbEvento = null;
				EventoDAO eventoDAO = new EventoDAO();
		
				
				tbEvento = eventoDAO.find(Integer.parseInt(frm.txtIdEvento.getText()));
				
				if (tbEvento == null) {
					JOptionPane.showMessageDialog(null, "Não foi encontrado registro para este evento pelo código: "+frm.txtIdEvento.getText()+" .", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
					frm.txtIdEvento.grabFocus();
					frm.getRootPane().setDefaultButton(frm.btnPesquisar);// Botão Padrão da Tecla Enter
					frm.btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
					break;
				}
				frm.lblNomeEvento.setText("Evento: "+tbEvento.getNomeEvento());
				frm.lblDataDoEvento.setText("Data: "+new ConversorData().ConverterDateSQLEmString(tbEvento.getDataEvento()));
				frm.lblComunidade.setText("Comunidade: "+tbEvento.getTbLocal().getNomeComunidade());
				frm.btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
				frm.getRootPane().setDefaultButton(frm.btnExcluirEvento);// Botão Padrão da Tecla Enter
			}else{
				JOptionPane.showMessageDialog(null, "É necessario digitar o código do evento para realizar a consulta.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtIdEvento.grabFocus();
			}
				
			break;
		case "Excluir Evento":
			
			if (utilidade.VerificarCampos(frm.txtIdEvento.getText())){
				
				//Pesquiso no Banco atraves do ID
				TbEvento tbEvento = new TbEvento();
				tbEvento = null;
				EventoDAO eventoDAO = new EventoDAO();
		
				
				tbEvento = eventoDAO.find(Integer.parseInt(frm.txtIdEvento.getText()));
				
				if (tbEvento == null) {
					JOptionPane.showMessageDialog(null, "Não foi encontrado registro para este evento pelo código: "+frm.txtIdEvento.getText()+"\nPortanto não será possivel exclui-lo.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
					frm.txtIdEvento.grabFocus();
					frm.getRootPane().setDefaultButton(frm.btnPesquisar);// Botão Padrão da Tecla Enter
					frm.btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
					break;
				}
				
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja excluir o evento: "+ tbEvento.getNomeEvento() +" ?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
				if (OpcaoSelecionada == JOptionPane.YES_OPTION) {
					if (eventoDAO.removerEvento(tbEvento.getIdEvento())) {
						
						OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Evento excluido com sucesso.\nDeseja excluir outro evento no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
						if (OpcaoSelecionada == JOptionPane.NO_OPTION) 
							frm.dispose();
						else{
							frm.txtIdEvento.setText(null);
							frm.lblDataDoEvento.setText(null);
							frm.lblNomeEvento.setText(null);
							frm.lblComunidade.setText(null);
							frm.getRootPane().setDefaultButton(frm.btnPesquisar);// Botão Padrão da Tecla Enter
							frm.btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
						}
					}else{
						JOptionPane.showMessageDialog(null, "Não foi possível excluir o evento.\nNão se pode excluir um evento que esteja relacionado com alguma venda.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
						frm.txtIdEvento.grabFocus();
						frm.getRootPane().setDefaultButton(frm.btnPesquisar);// Botão Padrão da Tecla Enter
						frm.btnPesquisar.setIcon(new ImageIcon(FrmExcluirEvento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
						break;
					}
				}
			}
			break;

		}
		
	}
	
}
