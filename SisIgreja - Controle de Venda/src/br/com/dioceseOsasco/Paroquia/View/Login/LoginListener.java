package br.com.dioceseOsasco.Paroquia.View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Configuracao.FrmConfiguracao;
import br.com.dioceseOsasco.Paroquia.View.Principal.FrmPrincipal;

public class LoginListener implements ActionListener {

	private FrmLogin frm;
	private UtilitarioVerificarCampos utilidade;
	LoginListener(FrmLogin frm) {
		this.frm = frm;
		adicionaListener();

		utilidade = new UtilitarioVerificarCampos();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.btnEntrar.addActionListener(this);
		frm.lblEsquecerSenha.addMouseListener(new ResetarSenhaMouseEvent());
		frm.lblConfig.addMouseListener(new ConfiguracaoMouseEvent());

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand();

		switch (evento) {
		case "Cancelar":
			System.exit(0);
			break;

		case "Acessar":
			
			if (!utilidade.VerificarCampos(frm.txtUsuario.getText())) {
				JOptionPane.showMessageDialog(null, "� necessario digitar um nome de usu�rio v�lido.", "Campo usu�rio ",
						JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				return;
			}
			if (!utilidade.VerificarCampos(frm.txtSenha.getPassword().toString())) {
				JOptionPane.showMessageDialog(null, "� necessario digitar uma senha v�lida.", "Campo senha",
						JOptionPane.OK_OPTION);
				frm.txtSenha.grabFocus();
				return;
			}

			UsuarioDAO usuario = new UsuarioDAO();
			
			TbUsuario tb_usuario = new TbUsuario(); 
			
			tb_usuario = usuario.VerificarLoginSenha(frm.txtUsuario.getText(), String.valueOf(frm.txtSenha.getPassword()));
			
			if (tb_usuario.getNome() != null) {

				
				if (tb_usuario.getAtivo() == (byte) 0 ) {
					JOptionPane.showMessageDialog(null,"Usu�rio bloqueado ou inativo no sistema\nPor Favor contate o administrador do sistema.", frm.getTitle(), JOptionPane.OK_OPTION);
					break;
				}

				new TbUsuario().instance =  tb_usuario; // Inst�ncia TbUsuario Unico
				
				FrmPrincipal frmPrincipal = new FrmPrincipal(); // Formulario Principal
				frmPrincipal.verificacaoPermissao(tb_usuario); //Permiss�es do Usu�rio no Sistema

				
				
				frmPrincipal.setVisible(true);
				frm.dispose();

				
				
				
			} else {

				TbUsuario tbUsuario = usuario.VerificarLogin(frm.txtUsuario.getText());

				if (tbUsuario.getNome() != null) {
					JOptionPane.showMessageDialog(null, "Senha inv�lida.\nPor Favor digite uma senha v�lida.",
							frm.getTitle(), JOptionPane.OK_OPTION);
					frm.txtSenha.setText(null);
					frm.txtSenha.grabFocus();
				} else {
					JOptionPane.showMessageDialog(null,
							"Usu�rio inv�lido.\nPor Favor, digite um usu�rio v�lido.", frm.getTitle(),
							JOptionPane.OK_OPTION);
					frm.txtSenha.setText(null);
					frm.txtUsuario.setText(null);
					frm.txtUsuario.grabFocus();
				}
			}

			break;

		default:
			break;
		}

	}

	// Resetar Senha
	class ResetarSenhaMouseEvent extends MouseAdapter {
		public void mousePressed(MouseEvent event) {

			int selectedOption = JOptionPane.showConfirmDialog(null, "Deseja redefinir sua senha?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
				
				
				if (!utilidade.VerificarCampos(frm.txtUsuario.getText())) {
					JOptionPane.showMessageDialog(null, "� necessario digitar um nome de usuario v�lido no sistema.", frm.getTitle(), JOptionPane.OK_OPTION);
					frm.txtUsuario.grabFocus();
					return;
				}

				UsuarioDAO usuario = new UsuarioDAO();
				TbUsuario tbUsuario = usuario.VerificarLogin(frm.txtUsuario.getText());

				if (tbUsuario.equals(null)) {
					JOptionPane.showMessageDialog(null, "Usu�rio n�o cadastrado no sitema\nDigite um usu�rio cadastrado no sistema.", frm.getTitle(), JOptionPane.OK_OPTION);
					frm.txtUsuario.grabFocus();
					return;
				}

				if (usuario.resetarSenha(tbUsuario)) {
					JOptionPane.showMessageDialog(null, "Sua senha foi redefinida. \nSua senha agora � sua data de nascimento no seguinte formato: dia/m�s/ano (4 digitos)", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Ocorreu um erro ao redefinir sua senha.", frm.getTitle(), 	JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	// Configura��o
	class ConfiguracaoMouseEvent extends MouseAdapter {
		public void mousePressed(MouseEvent event) {
			FrmConfiguracao frmConfiguracao = new FrmConfiguracao();
			frmConfiguracao.setVisible(true);
		}
	}
}
