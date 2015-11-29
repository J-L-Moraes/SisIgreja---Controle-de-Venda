package br.com.dioceseOsasco.Paroquia.View.Configuracao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ConnectionFactory;
import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbPermissao;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Usuario.PermissaoUsuario;

public class ConfiguracaoListener implements ActionListener {

	FrmConfiguracao frm;
	private TbUsuario tbUsuario;
	
	ConfiguracaoListener(FrmConfiguracao frm){
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener() {
		frm.btnAlterarConfiguracao.addActionListener(this);
		frm.btnCancelarConfiguracao.addActionListener(this);
		frm.btnTestarConfiguracao.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String evento = event.getActionCommand();
		
		switch (evento) {
		case "Alterar Configura\u00E7\u00E3o":
			
			//Verifica��o de Campos
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtCaminhoServidor.getText())){
				JOptionPane.showMessageDialog(null, "� necessario digitar um IP v�lido ou um hostname aonde se encontra o servi�o de banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtCaminhoServidor.grabFocus();
				break;
			}
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "� necessario digitar o usu�rio do banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			if (testarConfiguracao()) {
				
				String portaServidor = frm.txtPortaServidor.getText();
				String caminhoServidor = null;
				
				if (new UtilitarioVerificarCampos().VerificarCampos(portaServidor)) {
					caminhoServidor = "jdbc:mysql://"+ frm.txtCaminhoServidor.getText() +":"+ portaServidor + "/caixa"; 
				}else{
					caminhoServidor = "jdbc:mysql://"+ frm.txtCaminhoServidor.getText() + portaServidor + "/caixa";
				}
				
				String usuario = frm.txtUsuario.getText();
				
				String senha = " ";
				if (new UtilitarioVerificarCampos().VerificarCampos(senha))
						senha =	String.valueOf(frm.txtSenha.getPassword());
				
				
				try {
					//Altero o caminho do Banco de Dados j� testado
					ConnectionFactory.alterarDados(caminhoServidor, usuario, senha); 
					
					
					//Verifico se existe o usu�rio ADIM no sistema
					tbUsuario = new TbUsuario();
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					tbUsuario = usuarioDAO.VerificarLogin("admin");
					
					//N�o existe o usu�rio ADMIN
					if (tbUsuario.getNome() == null) {
						
						tbUsuario = new TbUsuario();

						tbUsuario.setNome("Administrador");
						tbUsuario.setAtivo( (byte) 1);
						try {
							tbUsuario.setDataNascimento( new ConversorData().ConverterStringParaDate("11/10/1991"));
						} catch (ParseException e) { }
						//tbUsuario.setIdUsuario(1);
						tbUsuario.setNivelTipo("Administrador");
						tbUsuario.setSenha("admin");
						tbUsuario.setUsuario("admin");
						
						TbPermissao tbPermissao = new TbPermissao();
						tbPermissao = new PermissaoUsuario().permissaoAdministrador();
						tbUsuario.setTbPermissao(tbPermissao);
						
						
						if (usuarioDAO.salvarUsuario(tbUsuario, tbPermissao)){
							try {
								ConnectionFactory.alterarDados(caminhoServidor, usuario, senha);
								JOptionPane.showMessageDialog(null, "Informa��es alteradas com sucesso."
																  + "\nComo � seu primeiro acesso foi configurado a conta de usu�rio: 'admin' com senha: 'admin'."
																  + "\nPor favor, altere a senha em seu primeiro login", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
								frm.dispose();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "Param�tros de configura��o alterado com sucesso."
																	+ "\nN�o foi poss�vel adicionar o usu�rio ADMIN"
																	+ "\nVerifique se houve interrup��o na conex�o com o servidor.", frm.getTitle(),JOptionPane.ERROR_MESSAGE);
								frm.dispose();
							} 
						}
					
					} else {
						JOptionPane.showMessageDialog(null, "Informa��es alteradas com sucesso.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						frm.dispose();
		
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,	"Erro ao ler o Arquivo.\nN�o foi possivel alterar os dados.", frm.getTitle(),
							JOptionPane.ERROR_MESSAGE);
				}
		
			}else{
				JOptionPane.showMessageDialog(null, "Confgura��o Incorreta.\nPor favor, verifique-se o servi�o do banco de dados est� ativo\nou se as configura��es est�o corretas.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			break;
		
		case "Testar":
			
			//Verifica��o de Campos
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtCaminhoServidor.getText())){
				JOptionPane.showMessageDialog(null, "� necessario digitar um IP v�lido ou um hostname aonde se encontra o servi�o de banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtCaminhoServidor.grabFocus();
				break;
			}
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "� necessario digitar o usu�rio do banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			
			if (testarConfiguracao()) {
				JOptionPane.showMessageDialog(null, "Teste realizado com sucesso.\nPor favor, salve esta configura��o", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Confgura��o incorreta.\nPor favor, verifique-se o servi�o do banco de dados est� ativo.\nou se as configura��es est�o corretas.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		break;
		
		case "Cancelar":
			frm.dispose();
		break;
		}
		
	}
	
	/**
	 * Testa a Configura��o afim de verificar poss�veis erros
	 * @return
	 */
	private boolean testarConfiguracao(){
		
		UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();

		try {
			String portaServidor = frm.txtPortaServidor.getText();
			
			String caminhoServidor = null;
			if (utilidade.VerificarCampos(portaServidor)) {
				caminhoServidor = "jdbc:mysql://"+ frm.txtCaminhoServidor.getText() +":"+ portaServidor + "/caixa";
			}else{
				caminhoServidor = "jdbc:mysql://"+ frm.txtCaminhoServidor.getText() + portaServidor + "/caixa";
			}
			
			String usuario = frm.txtUsuario.getText();
			String senha = String.valueOf(frm.txtSenha.getPassword());
			
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.url", caminhoServidor);
			properties.put("javax.persistence.jdbc.user", usuario);
			properties.put("javax.persistence.jdbc.password", senha);

			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SisIgreja", properties);

			entityManagerFactory.close();
			
			return true;
		} catch (Exception e) {
			
			return false;
		}
		
		
	}
	

}
