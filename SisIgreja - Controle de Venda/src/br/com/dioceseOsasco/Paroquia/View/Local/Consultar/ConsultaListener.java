package br.com.dioceseOsasco.Paroquia.View.Local.Consultar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;

public class ConsultaListener implements KeyListener {
	
	FrmConsultarLocal frm;
	
	LocalDAO localDAO;
	List<TbLocal> tb_local;
	
	ConsultaListener(FrmConsultarLocal frm){
		this.frm = frm;
		frm.txtComunidade.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent arg0) { }

	@Override
	public void keyReleased(KeyEvent arg0) {
		
			String comunidade = frm.txtComunidade.getText();
			localDAO = new LocalDAO();
			tb_local = localDAO.findComunidade(comunidade);
			frm.table.setModel(new LocalTableModel(tb_local));
	}

	@Override
	public void keyTyped(KeyEvent arg0) { }

}
