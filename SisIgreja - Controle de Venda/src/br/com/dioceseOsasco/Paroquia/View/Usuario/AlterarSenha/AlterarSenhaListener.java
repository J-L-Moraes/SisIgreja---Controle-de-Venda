package br.com.dioceseOsasco.Paroquia.View.Usuario.AlterarSenha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class AlterarSenhaListener implements ActionListener{

	FrmAlterarSenha frm;
	public AlterarSenhaListener(FrmAlterarSenha frm) {
		this.frm = frm;
		adicionaListener();
	}
	private void adicionaListener() {
		frm.btnCancelar.addActionListener(this);
		frm.btnAlterarSenha.addActionListener(this);		
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		String evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
			   frm.dispose();
			break;

		case "Alterar Senha":
			
			@SuppressWarnings("static-access")
			TbUsuario tbUsuario = new TbUsuario().getInstance(true);
			
			//Senha Antiga
			if (!new UtilitarioVerificarCampos().VerificarCamposNulos(String.valueOf(frm.txtSenhaAntiga.getPassword()))) {
				JOptionPane.showMessageDialog(null, "É necessario digitar sua senha antiga.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtSenhaAntiga.grabFocus();
				break;
			}
			
			//Senha Nova
			if (!new UtilitarioVerificarCampos().VerificarCamposNulos(String.valueOf(frm.txtSenhaNova.getPassword()))) {
				JOptionPane.showMessageDialog(null, "É necessario digitar uma senha nova.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtSenhaNova.grabFocus();
				break;
			}
			
			
			String senhaAntiga = String.valueOf(frm.txtSenhaAntiga.getPassword());
			String senhaNova = String.valueOf(frm.txtSenhaNova.getPassword());
			
			//Senha Antiga
			if ( ! senhaAntiga.equals(tbUsuario.getSenha())  ) {
				JOptionPane.showMessageDialog(null, "Senha antiga não confere.\nPor favor, redigite sua senha antiga.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtSenhaAntiga.setText(null);
				frm.txtSenhaAntiga.grabFocus();
				break;
			}
			
			//Conferindo a senha nova
			if (!senhaNova.equals(String.valueOf(frm.txtRepitirSenha.getPassword()) )) {
				JOptionPane.showMessageDialog(null, "Senha nova não confere.\nPor favor, redigite sua senha nova.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtRepitirSenha.setText(null);
				frm.txtRepitirSenha.grabFocus();
				break;
			}
			
			
			tbUsuario.setSenha(senhaNova);
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			if (usuarioDAO.alterarSenha(tbUsuario)){
				JOptionPane.showMessageDialog(null,"Senha alterada com sucesso.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar a senha do usuário.", frm.getTitle(),JOptionPane.ERROR_MESSAGE);
				break;
			}	
			
			
			break;
		}
		
	}

}
