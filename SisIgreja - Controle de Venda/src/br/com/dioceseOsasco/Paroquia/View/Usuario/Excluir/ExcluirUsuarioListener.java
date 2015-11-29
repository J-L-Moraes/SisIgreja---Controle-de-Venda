package br.com.dioceseOsasco.Paroquia.View.Usuario.Excluir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar.FrmConsultarUsuario;

public class ExcluirUsuarioListener implements ActionListener {
	
	UsuarioDAO usuarioDAO;
	TbUsuario tb_usuario;
	FrmExcluirUsuario frm;
	public ExcluirUsuarioListener(FrmExcluirUsuario frm){
		this.frm = frm;
		adicionaListener();
	}
	
	
	private void adicionaListener() {
		
		frm.btnCancelar.addActionListener(this);
		frm.btnExcluirUsurio.addActionListener(this);
		frm.btnPesquisarUsurio.addActionListener(this);
		frm.btnProcurar.addActionListener(this);
		
		usuarioDAO = new UsuarioDAO();
		tb_usuario = new TbUsuario();
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		String evento = event.getActionCommand();
		switch (evento) {
		case "Cancelar":
			 	frm.dispose();
			break;
		case "Procurar usu\u00E1rio":
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar um nome de usuário válido.", "Campo nome de usuário ", JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			tb_usuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			
			if (tb_usuario == null) {
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum usuário no sistema.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}else{
				frm.lblNome.setText("Nome: "+ tb_usuario.getNome());
				frm.lblDataDeNascimento.setText("Data de Nascimento: " + new ConversorData().ConverterDateSQLEmString(tb_usuario.getDataNascimento()));
			}
			
			
			break;
		case "Localizar usu\u00E1rio":
				new FrmConsultarUsuario(frm).setVisible(true);
			break;
		case "Excluir usu\u00E1rio":
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar um nome de usuário válido.", "Campo nome de usuário ", JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			tb_usuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			
			if (tb_usuario == null) {
				JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum usuário no sistema.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}else{
				if (usuarioDAO.removerUsuario(tb_usuario)) {
					
					int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Usuário excluido com sucesso.\nDeseja excluir outro usuário no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
					if (OpcaoSelecionada == JOptionPane.NO_OPTION) 
						frm.dispose();
					else{
						frm.lblDataDeNascimento.setText(null);
						frm.lblNome.setText(null);
						frm.txtUsuario.setText(null);
					}
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o usuário.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
					break;	
				}
				
			}
			
			break;
		}
		
	}

}
