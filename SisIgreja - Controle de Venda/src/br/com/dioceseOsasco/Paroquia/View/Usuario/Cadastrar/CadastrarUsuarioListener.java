package br.com.dioceseOsasco.Paroquia.View.Usuario.Cadastrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbPermissao;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Usuario.PermissaoUsuario;

public class CadastrarUsuarioListener implements ActionListener {

	FrmCadastrarUsuario frm;
	private TbUsuario tbUsuario;
	
	public CadastrarUsuarioListener(FrmCadastrarUsuario frm) {
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener() {
		
		frm.btnCadastrar.addActionListener(this);
		frm.btnCancelar.addActionListener(this);
		frm.btnLimparCampos.addActionListener(this);
		frm.cmBxTipoUsuario.addItemListener(new CmBxTipoUsuarioItemListener());
		frm.txtUsuario.addFocusListener(new TxtUsuarioFocusListener());
		
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Limpar Campos":
				
			break;
		case "Cadastrar":
			
			UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			//Nome
			if (!utilitario.VerificarCamposNulos(frm.txtNome.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar o nome do usuário.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtNome.grabFocus();
				break;
			}
			System.out.println(frm.txtNome.getText());
			//Tipo de Sistema
			if (frm.cmBxTipoUsuario.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario selecionar o Tipo de sistema que o colaborador irá utilizar.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxTipoUsuario.grabFocus();
				break;
			}
			//Senha
			if (!utilitario.VerificarCamposNulos(frm.txtSenha.getPassword().toString())){
				JOptionPane.showMessageDialog(null, "É necessario digitar uma senha para acesso ao sistema.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtSenha.grabFocus();
				break;
			}

			//Usuário
			if (!utilitario.VerificarCamposNulos(frm.txtUsuario.getText())){
				JOptionPane.showMessageDialog(null, "É necessario digitar um usuário.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}
			tbUsuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			if(tbUsuario.getUsuario() != null){
				JOptionPane.showMessageDialog(null, "Usuário já cadastrado no sistema, Por favor cadastre outro usuario.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
				break;
			}

			//Data de Nascimento
			if (frm.cmBxDataNascimento.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "É necessario digitar a Data de Nascimento \nA data de Nascimento será utilizado em caso de perda de senha por parte do colaborador.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxTipoUsuario.grabFocus();
				break;
			}
			
			
			
			
			ConversorData conversorData = new ConversorData(); 
			Date DataNascimento = null;
			
			try {
				DataNascimento = conversorData.ConverterStringParaDate( (((JCalendar) frm.cmBxDataNascimento).getText()) );
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.", frm.getTitle(), JOptionPane.ERROR_MESSAGE);
				frm.cmBxDataNascimento.grabFocus();
				break;
			}
			
			tbUsuario = new TbUsuario();
			//Populando a TbUsuario
			tbUsuario.setNome( frm.txtNome.getText() );
			tbUsuario.setUsuario(frm.txtUsuario.getText());
			tbUsuario.setSenha( new String(frm.txtSenha.getPassword()));
			tbUsuario.setAtivo((byte) 1);//Usuario padrão cadastrado é ativo
			tbUsuario.setNivelTipo(frm.cmBxTipoUsuario.getSelectedItem().toString());
			tbUsuario.setDataNascimento(DataNascimento);
			
			

			TbPermissao tbPermissao = null;
			if (frm.cmBxTipoUsuario.getSelectedItem() == "Personalizado") 
				tbPermissao = permissaoPersonalizada();
			else if (frm.cmBxTipoUsuario.getSelectedItem() == "Administrador")
				tbPermissao = new PermissaoUsuario().permissaoAdministrador();
			else if (frm.cmBxTipoUsuario.getSelectedItem() == "Vendedor")
				tbPermissao = new PermissaoUsuario().permissaoVendedor();
			else if (frm.cmBxTipoUsuario.getSelectedItem() == "Gerente")
				tbPermissao = new PermissaoUsuario().permissaoGerente();
				
				
			
			if( usuarioDAO.salvarUsuario(tbUsuario, tbPermissao) ){
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Colaborador cadastrado com sucesso.\nDeseja cadastrar outro colaborador no sistema?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.NO_OPTION){
					frm.dispose();
				}else{
					frm.txtNome.setText(null);
					frm.txtUsuario.setText(null);
					frm.txtSenha.setText(null);
					frm.cmBxDataNascimento.setSelectedItem(null);
					frm.cmBxTipoUsuario.setSelectedItem(null);
					frm.PersonalizacaoNormal();
				}
			}
			
			break;
		case "Cancelar":
				frm.dispose();
			break;
		}
		
	}
	//ComboBox Tipo de Usuario responsavel pelo evento para exibir os campos personalizados
	private class CmBxTipoUsuarioItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (frm.cmBxTipoUsuario.getSelectedItem() == "Personalizado") {
				frm.PersonalizacaoPeronalizada();
			}else{
				frm.PersonalizacaoNormal();
			}
		}
	}
	

	//Evento quando o campo txtUsuario Peder o foco, verifica se o mesmo existe no banco de dados
	private class TxtUsuarioFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent event) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			TbUsuario tb_usuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			if (tb_usuario.getUsuario() != null) {
				JOptionPane.showMessageDialog(null,"Usuário já está cadastrado no Banco de Dados pelo usuário: "+tb_usuario.getNome()+"\nPor favor insira outro usuário.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
				frm.txtUsuario.grabFocus();
			}
		}
	}
	


	
	/**
	 * Verifica qual item está checado para que deja a permissão correta ao usuario
	 * @return TbPermissao
	 */
	TbPermissao permissaoPersonalizada(){

		TbPermissao tbPermissao = new TbPermissao();
		
		if (frm.chckbxAbrirCaixa.isSelected())
			tbPermissao.setAbrirCaixa( (byte) 1 );
		else
			tbPermissao.setAbrirCaixa( (byte) 0 );
		
		if (frm.chckbxAnalisarPedido.isSelected())
			tbPermissao.setAnalisarPedido((byte) 1 );
		else
			tbPermissao.setAnalisarPedido((byte) 0 );
		
		if (frm.chckbxCadastrarProduto.isSelected())
			tbPermissao.setCadastrarProduto((byte) 1 );
		else
			tbPermissao.setCadastrarProduto((byte) 0 );
		
		if (frm.chckbxAlterarProduto.isSelected())
			tbPermissao.setAlterarProduto((byte) 1 );
		else
			tbPermissao.setAlterarProduto((byte) 0 );
		
		if (frm.chckbxProcurarProduto.isSelected())
			tbPermissao.setProcurarProduto((byte) 1 );
		else
			tbPermissao.setProcurarProduto((byte) 0 );
		
		if (frm.chckbxExcluirProduto.isSelected())
			tbPermissao.setExcluirProduto((byte) 1 );
		else
			tbPermissao.setExcluirProduto((byte) 0 );
		
		if (frm.chckbxCadastrarEvento.isSelected())
			tbPermissao.setCadastrarEvento((byte) 1 );
		else
			tbPermissao.setCadastrarEvento((byte) 0 );
		
		if (frm.chckbxAlterarEvento.isSelected())
			tbPermissao.setAlterarEvento((byte) 1 );
		else
			tbPermissao.setAlterarEvento((byte) 0 );
		
		if (frm.chckbxPesquisarEvento.isSelected())
			tbPermissao.setPesquisarEvento((byte) 1 );
		else
			tbPermissao.setPesquisarEvento((byte) 0 );
		
		if (frm.chckbxExcluirEvento.isSelected())
			tbPermissao.setExcluirEvento((byte) 1 );
		else
			tbPermissao.setExcluirEvento((byte) 0 );
		
		if (frm.chckbxCadastrarUsuario.isSelected()) 
			tbPermissao.setCadastrarUsuario((byte) 1 );
		else
			tbPermissao.setCadastrarUsuario((byte) 0 );
		
		if (frm.chckbxAlterarUsuario.isSelected())
			tbPermissao.setAlterarUsuario((byte) 1 );
		else
			tbPermissao.setAlterarUsuario((byte) 0 );
		
		if (frm.chckbxProcurarUsuario.isSelected())
			tbPermissao.setProcurarUsuario((byte) 1 );
		else
			tbPermissao.setProcurarUsuario((byte) 0 );
		
		if (frm.chckbxExcluirUsurio.isSelected())
			tbPermissao.setExcluirUsuario((byte) 1 );
		else
			tbPermissao.setExcluirUsuario((byte) 0 );
		
		if (frm.chckbxRedefinirSenhaoutros.isSelected())
			tbPermissao.setRedefinirSenhaOutros((byte) 1 );
		else
			tbPermissao.setRedefinirSenhaOutros((byte) 0 );
		
		if (frm.chckbxDioceseParoquia.isSelected())
			tbPermissao.setDioceseParoquia((byte) 1 );
		else
			tbPermissao.setDioceseParoquia((byte) 0 );
		
		if (frm.chckbxCadastrarComunidade.isSelected())
			tbPermissao.setCadastrarComunidade((byte) 1 );
		else
			tbPermissao.setCadastrarComunidade((byte) 0 );
		
		if (frm.chckbxAtualizarComunidade.isSelected())
			tbPermissao.setAtualizarComunidade((byte) 1 );
		else
			tbPermissao.setAtualizarComunidade((byte) 0 );
		
		if (frm.chckbxProcurarComunidade.isSelected())
			tbPermissao.setProcurarComunidade((byte) 1 );
		else
			tbPermissao.setProcurarComunidade((byte) 0 );
		
		if (frm.chckbxExcluirComunidade.isSelected())
			tbPermissao.setExcluirComunidade((byte) 1 );
		else
			tbPermissao.setExcluirComunidade((byte) 0 );
		
		if (frm.chckbxRelatorioDeVenda.isSelected())
			tbPermissao.setRelatorioDeVenda((byte) 1 );
		else
			tbPermissao.setRelatorioDeVenda((byte) 0 );
		
		if (frm.chckbxRelatorioDeVendaDetalhado.isSelected())
			tbPermissao.setRelatorioDeVendaDetalhado((byte) 1 );
		else 
			tbPermissao.setRelatorioDeVendaDetalhado((byte) 0 );
		
		if (frm.chckbxRelatorioDeProduto.isSelected())
			tbPermissao.setRelatorioDeProduto((byte) 1 );
		else
			tbPermissao.setRelatorioDeProduto((byte) 0 );
		
		return tbPermissao;
	}

}
