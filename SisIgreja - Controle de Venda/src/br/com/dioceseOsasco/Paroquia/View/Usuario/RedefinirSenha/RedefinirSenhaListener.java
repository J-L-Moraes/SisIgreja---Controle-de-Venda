package br.com.dioceseOsasco.Paroquia.View.Usuario.RedefinirSenha;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar.FrmConsultarUsuario;

public class RedefinirSenhaListener implements ActionListener{

	FrmRedefinirSenha frm;
	
	public RedefinirSenhaListener(FrmRedefinirSenha frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnAlterarSenha.addActionListener(this);
		frm.btnCancelar.addActionListener(this);
		frm.btnLocalizarUsuario.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
			 frm.dispose();
			break;

		case "Alterar Senha":
			
			UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
			//Usu�rio
			if (!utilitario.VerificarCamposNulos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "� necessario digitar um usu�rio.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}
			//Senha
			if (!utilitario.VerificarCamposNulos( String.valueOf(frm.txtSenha.getPassword()))){
				JOptionPane.showMessageDialog(null, "� necessario digitar uma senha.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			TbUsuario tbUsuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			if(tbUsuario == null){
				JOptionPane.showMessageDialog(null, "Usu�rio n�o est� cadastrado no sistema. \nPor favor, digite um usu�rio v�lido.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}
			tbUsuario.setSenha(String.valueOf(frm.txtSenha.getPassword()));
			
			if (usuarioDAO.alterarSenha(tbUsuario)) {
				
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Senha alterada com sucesso.\nDeseja alterar outra senha?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.NO_OPTION){
					frm.dispose();
				}else{
					frm.txtUsuario.setText(null);
					frm.txtSenha.setText(null);
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao alterar a senha do usu�rio.", frm.getTitle(),JOptionPane.ERROR_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			
			break;
		case "Localizar Usu\u00E1rio":
				new FrmConsultarUsuario(frm).setVisible(true);
			break;
		}
		
	}

}
