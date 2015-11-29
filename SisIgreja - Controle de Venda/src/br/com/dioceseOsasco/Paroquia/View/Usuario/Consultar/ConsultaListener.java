package br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsultaListener implements KeyListener {
	
	FrmConsultarUsuario frm;
	public ConsultaListener(FrmConsultarUsuario frm){
		this.frm = frm;
		adicionaListener();
	}
	private void adicionaListener() {
		frm.txtNome.addKeyListener(this);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {

		frm.tbusuario = frm.usuarioDAO.findNome(frm.txtNome.getText());
		frm.table.setModel(new UsuarioTableModel(frm.tbusuario));
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {  }
	@Override
	public void keyTyped(KeyEvent arg0) { 	}

}
