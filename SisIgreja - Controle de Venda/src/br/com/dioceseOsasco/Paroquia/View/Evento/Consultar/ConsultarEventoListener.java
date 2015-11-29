package br.com.dioceseOsasco.Paroquia.View.Evento.Consultar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.text.ParseException;

import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;

public class ConsultarEventoListener implements ActionListener, KeyListener {

	private FrmConsultarEvento frm;
	
	public ConsultarEventoListener(FrmConsultarEvento frm){
		this.frm = frm;
		adicionaListener();
	}
	
	
	private void adicionaListener() {
		
		frm.btnPesquisar.addActionListener(this);
		frm.btnLimparCampos.addActionListener(this);
		frm.txtEvento.addKeyListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Pesquisar":
			
			PesquisarInformacoes();
			
			break;
		case "Limpar Campos":
				
				frm.txtEvento.setText(null);
				frm.cmBxDataInicial.setSelectedItem(null);
				frm.cmBxDataFinal.setSelectedItem(null);
				
				frm.tbEvento = frm.eventoDAO.findEventoEntreDatasNome(null, null, null);
				frm.table.setModel(new EventoTableModel(frm.tbEvento));
			break;

		default:
			break;
		}
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		PesquisarInformacoes();
		
	}


	@Override
	public void keyTyped(KeyEvent e) { 	}
	
	private void PesquisarInformacoes(){

		Date dataInicial = null, dataFinal = null;
		String nomeEvento = frm.txtEvento.getText();
		
		
		try {
			dataInicial = new ConversorData().ConverterStringParaDate( (((JCalendar) frm.cmBxDataInicial).getText()) );
			dataFinal = new ConversorData().ConverterStringParaDate( (((JCalendar) frm.cmBxDataFinal).getText()) );
		} catch (ParseException e1) { 	}
		
		
		if (dataFinal != null) {
			try {
				if (dataInicial.after(dataFinal)) {
					Date dataInicialInvertida, dataFinalInvertida;
					dataInicialInvertida = dataFinal;
					dataFinalInvertida = dataInicial;
					dataFinal = dataFinalInvertida;
					dataInicial = dataInicialInvertida;
				}
			} catch (Exception e) { }
		}
		
		
		if ( (dataInicial == null) && (dataFinal == null) ) {
			frm.tbEvento = frm.eventoDAO.findEventoEntreDatasNome(nomeEvento, null, null);
			frm.table.setModel(new EventoTableModel(frm.tbEvento));
		}else if((dataInicial == null) && (dataFinal != null)){
			frm.tbEvento = frm.eventoDAO.findEventoEntreDatasNome(nomeEvento, dataFinal, dataFinal);
			frm.table.setModel(new EventoTableModel(frm.tbEvento));
		}else if(  (dataInicial != null) && (dataFinal == null)  ){
			frm.tbEvento = frm.eventoDAO.findEventoEntreDatasNome(nomeEvento, dataInicial, dataInicial);
			frm.table.setModel(new EventoTableModel(frm.tbEvento));
		}else if((dataInicial != null) && (dataFinal != null)){
			frm.tbEvento = frm.eventoDAO.findEventoEntreDatasNome(nomeEvento, dataInicial, dataFinal);
			frm.table.setModel(new EventoTableModel(frm.tbEvento));
		}
		
	}

}
