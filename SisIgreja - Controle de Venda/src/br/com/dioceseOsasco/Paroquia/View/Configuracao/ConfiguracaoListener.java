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
			
			//Verificação de Campos
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtCaminhoServidor.getText())){
				JOptionPane.showMessageDialog(null, "É necessario digitar um IP válido ou um hostname aonde se encontra o serviço de banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtCaminhoServidor.grabFocus();
				break;
			}
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "É necessario digitar o usuário do banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
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
					//Altero o caminho do Banco de Dados já testado
					ConnectionFactory.alterarDados(caminhoServidor, usuario, senha); 
					
					
					//Verifico se existe o usuário ADIM no sistema
					tbUsuario = new TbUsuario();
					UsuarioDAO usuarioDAO = new UsuarioDAO();
					tbUsuario = usuarioDAO.VerificarLogin("admin");
					
					//Não existe o usuário ADMIN
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
								JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso."
																  + "\nComo é seu primeiro acesso foi configurado a conta de usuário: 'admin' com senha: 'admin'."
																  + "\nPor favor, altere a senha em seu primeiro login", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
								frm.dispose();
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "Paramêtros de configuração alterado com sucesso."
																	+ "\nNão foi possível adicionar o usuário ADMIN"
																	+ "\nVerifique se houve interrupção na conexão com o servidor.", frm.getTitle(),JOptionPane.ERROR_MESSAGE);
								frm.dispose();
							} 
						}
					
					} else {
						JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						frm.dispose();
		
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,	"Erro ao ler o Arquivo.\nNão foi possivel alterar os dados.", frm.getTitle(),
							JOptionPane.ERROR_MESSAGE);
				}
		
			}else{
				JOptionPane.showMessageDialog(null, "Confguração Incorreta.\nPor favor, verifique-se o serviço do banco de dados está ativo\nou se as configurações estão corretas.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			break;
		
		case "Testar":
			
			//Verificação de Campos
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtCaminhoServidor.getText())){
				JOptionPane.showMessageDialog(null, "É necessario digitar um IP válido ou um hostname aonde se encontra o serviço de banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtCaminhoServidor.grabFocus();
				break;
			}
			if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "É necessario digitar o usuário do banco de dados.", frm.getTitle(),JOptionPane.OK_OPTION);
				frm.txtUsuario.grabFocus();
				break;
			}
			
			
			if (testarConfiguracao()) {
				JOptionPane.showMessageDialog(null, "Teste realizado com sucesso.\nPor favor, salve esta configuração", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "Confguração incorreta.\nPor favor, verifique-se o serviço do banco de dados está ativo.\nou se as configurações estão corretas.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
			}
			
			
			
		break;
		
		case "Cancelar":
			frm.dispose();
		break;
		}
		
	}
	
	/**
	 * Testa a Configuração afim de verificar possíveis erros
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
