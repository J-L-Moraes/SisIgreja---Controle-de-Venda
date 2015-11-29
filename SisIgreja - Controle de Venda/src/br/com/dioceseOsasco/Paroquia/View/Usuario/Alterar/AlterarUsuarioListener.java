package br.com.dioceseOsasco.Paroquia.View.Usuario.Alterar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.UsuarioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbPermissao;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;
import br.com.dioceseOsasco.Paroquia.View.Usuario.PermissaoUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar.FrmConsultarUsuario;

public class AlterarUsuarioListener implements ActionListener {

	FrmAlterarUsuario frm;
	private TbUsuario tbUsuario;
	public AlterarUsuarioListener(FrmAlterarUsuario frm) {
		this.frm = frm;
		adicionaListener();
	}
	private void adicionaListener() {
		frm.btnAlterar.addActionListener(this);
		frm.btnPesquisarUsuario.addActionListener(this);
		frm.btnCancelar.addActionListener(this);
		frm.txtUsuario.addFocusListener(new TxtUsuarioFocusListener());
		frm.cmBxTipoUsuario.addItemListener(new CmBxTipoUsuarioItemListener());
		frm.btnLocalizarUsuario.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		String evento = event.getActionCommand();
		
		switch (evento) {
		case "Pesquisar Usu\u00E1rio":
				if (!new UtilitarioVerificarCampos().VerificarCampos(frm.txtPesquisarUsuario.getText())) {
					JOptionPane.showMessageDialog(null, "É necessario digitar um usuário.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
					frm.txtPesquisarUsuario.grabFocus();
					break;
				}
				String usuario = frm.txtPesquisarUsuario.getText();
			    PesquisarUsuario(usuario);
			break;
		case "Cancelar Pesquisa":
			 frm.desabilitarHabilitarComponentes(false);
			 frm.LimparCampos();
			 frm.PersonalizacaoNormal();
			 break;

		case "Cancelar":
				frm.dispose();
			break;
		case "Localizar Usu\u00E1rio":
				new FrmConsultarUsuario(frm).setVisible(true);
			break;
		case "Alterar":
			
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
			if(tbUsuario != null){
				if (!tbUsuario.getUsuario().equals(frm.txtPesquisarUsuario.getText())) {
					JOptionPane.showMessageDialog(null,"Usuário já está cadastrado no Banco de Dados pelo usuário: "+tbUsuario.getNome()+"\nPor favor insira outro usuário.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					frm.txtUsuario.grabFocus();
					break;
				}
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
			
			
			//Populando a TbUsuario
			tbUsuario.setNome( frm.txtNome.getText() );
			tbUsuario.setUsuario(frm.txtUsuario.getText());
			tbUsuario.setSenha( new String(frm.txtSenha.getPassword()));
			
			if (frm.cmBxAtivo.getSelectedItem().toString().equals("Ativo")) {
				tbUsuario.setAtivo((byte) 1);//Usuario é ativo	
			}else{
				tbUsuario.setAtivo((byte) 0);
			}
			
			
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
				
			
			tbPermissao.setIdPermissao(tbUsuario.getTbPermissao().getIdPermissao());
			
			
			tbUsuario.setTbPermissao(tbPermissao);
				
			
			if( usuarioDAO.atualizarUsuario(tbUsuario, tbPermissao) ){
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Colaborador atualizado com sucesso.\nDeseja cadastrar outro colaborador no sistema?", frm.getTitle(), JOptionPane.YES_NO_OPTION);
				if(OpcaoSelecionada == JOptionPane.NO_OPTION){
					frm.dispose();
				}else{
					frm.desabilitarHabilitarComponentes(false);
					frm.LimparCampos();
					frm.PersonalizacaoNormal();
				}
			}
			
			break;
		}
		
	}
	
	//Evento quando o campo txtUsuario Peder o foco, verifica se o mesmo existe no banco de dados
	private class TxtUsuarioFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent event) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			TbUsuario tb_usuario = usuarioDAO.VerificarLogin(frm.txtUsuario.getText());
			if (tb_usuario != null) {
				
				if (!tb_usuario.getUsuario().equals(frm.txtPesquisarUsuario.getText())) {
					JOptionPane.showMessageDialog(null,"Usuário já está cadastrado no Banco de Dados pelo usuário: "+tb_usuario.getNome()+"\nPor favor insira outro usuário.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					frm.txtUsuario.grabFocus();
				}
				
				
			}
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
	
	
	
	/**
	 * Pesquisa e Preenche na tela o usuario cadastrado.
	 * @param usuario
	 */
	void PesquisarUsuario(String usuario) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		TbUsuario tbUsuario = new TbUsuario();
		tbUsuario = usuarioDAO.VerificarLogin(usuario);
		
		if (tbUsuario == null) {
			JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum registro no sistema com este usuário.", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
			frm.txtPesquisarUsuario.grabFocus();
			frm.desabilitarHabilitarComponentes(false);
			return;
		}else{
			
			frm.txtNome.setText(tbUsuario.getNome());
			frm.txtSenha.setText(tbUsuario.getSenha());
			frm.txtUsuario.setText(tbUsuario.getUsuario());
			
			
			//Data de Nascimento - Separo a Data e posteriormente crio um GregorianCalendar
			SimpleDateFormat ano1 = new SimpleDateFormat("yyyy");  
			SimpleDateFormat mes1 = new SimpleDateFormat("MM");  
			SimpleDateFormat dia1 = new SimpleDateFormat("dd");  

			int ano = Integer.valueOf(ano1.format(tbUsuario.getDataNascimento()));
			int mes = Integer.valueOf(mes1.format(tbUsuario.getDataNascimento()));
			int dia = Integer.valueOf(dia1.format(tbUsuario.getDataNascimento()));
			
			Calendar Date = new GregorianCalendar(ano, mes , dia);
			frm.cmBxDataNascimento.setSelectedItem(Date);
			
			
			frm.cmBxTipoUsuario.setSelectedItem(tbUsuario.getNivelTipo());
			
			if (tbUsuario.getAtivo() == (byte) 1) {
				frm.cmBxAtivo.setSelectedItem("Ativo");
			}else{
				frm.cmBxAtivo.setSelectedItem("Inativo");
			}
			
			if (frm.cmBxTipoUsuario.getSelectedItem() == "Personalizado") {
				frm.PersonalizacaoPeronalizada();
			}else {
				frm.PersonalizacaoNormal();
			}
			
			if (tbUsuario.getTbPermissao().getAbrirCaixa() == ( (byte) 1)) 
				frm.chckbxAbrirCaixa.setSelected(true);
			else
				frm.chckbxAbrirCaixa.setSelected(false);
			
			
			if (tbUsuario.getTbPermissao().getAnalisarPedido() == ( (byte) 1)) 
				frm.chckbxAnalisarPedido.setSelected(true);
			else
				frm.chckbxAnalisarPedido.setSelected(false);
			

			if (tbUsuario.getTbPermissao().getCadastrarProduto() == ( (byte) 1)) 
				frm.chckbxCadastrarProduto.setSelected(true);
			else
				frm.chckbxCadastrarProduto.setSelected(false);
			

			if (tbUsuario.getTbPermissao().getAlterarProduto() == ( (byte) 1)) 
				frm.chckbxAlterarProduto.setSelected(true);
			else
				frm.chckbxAlterarProduto.setSelected(false);
		
			if (tbUsuario.getTbPermissao().getProcurarProduto() == ( (byte) 1)) 
				frm.chckbxProcurarProduto.setSelected(true);
			else
				frm.chckbxProcurarProduto.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getExcluirProduto() == ( (byte) 1)) 
				frm.chckbxExcluirProduto.setSelected(true);
			else
				frm.chckbxExcluirProduto.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getCadastrarEvento() == ( (byte) 1)) 
				frm.chckbxCadastrarEvento.setSelected(true);
			else
				frm.chckbxCadastrarEvento.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getAlterarEvento() == ( (byte) 1)) 
				frm.chckbxAlterarEvento.setSelected(true);
			else
				frm.chckbxAlterarEvento.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getPesquisarEvento() == ( (byte) 1)) 
				frm.chckbxPesquisarEvento.setSelected(true);
			else
				frm.chckbxPesquisarEvento.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getExcluirEvento() == ( (byte) 1)) 
				frm.chckbxExcluirEvento.setSelected(true);
			else
				frm.chckbxExcluirEvento.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getCadastrarUsuario() == ( (byte) 1)) 
				frm.chckbxCadastrarUsuario.setSelected(true);
			else
				frm.chckbxCadastrarUsuario.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getAlterarUsuario() == ( (byte) 1)) 
				frm.chckbxAlterarUsuario.setSelected(true);
			else
				frm.chckbxAlterarUsuario.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getProcurarUsuario() == ( (byte) 1)) 
				frm.chckbxProcurarUsuario.setSelected(true);
			else
				frm.chckbxProcurarUsuario.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getExcluirUsuario() == ( (byte) 1)) 
				frm.chckbxExcluirUsurio.setSelected(true);
			else
				frm.chckbxExcluirUsurio.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getRedefinirSenhaOutros() == ( (byte) 1)) 
				frm.chckbxRedefinirSenhaoutros.setSelected(true);
			else
				frm.chckbxRedefinirSenhaoutros.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getDioceseParoquia() == ( (byte) 1)) 
				frm.chckbxDioceseParoquia.setSelected(true);
			else
				frm.chckbxDioceseParoquia.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getCadastrarComunidade() == ( (byte) 1)) 
				frm.chckbxCadastrarComunidade.setSelected(true);
			else
				frm.chckbxCadastrarComunidade.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getAtualizarComunidade() == ( (byte) 1)) 
				frm.chckbxAtualizarComunidade.setSelected(true);
			else
				frm.chckbxAtualizarComunidade.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getProcurarComunidade() == ( (byte) 1)) 
				frm.chckbxProcurarComunidade.setSelected(true);
			else
				frm.chckbxProcurarComunidade.setSelected(false);
		
			if (tbUsuario.getTbPermissao().getAtualizarComunidade() == ( (byte) 1)) 
				frm.chckbxAtualizarComunidade.setSelected(true);
			else
				frm.chckbxAtualizarComunidade.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getExcluirComunidade() == ( (byte) 1)) 
				frm.chckbxExcluirComunidade.setSelected(true);
			else
				frm.chckbxExcluirComunidade.setSelected(false);
		
			if (tbUsuario.getTbPermissao().getRelatorioDeVenda() == ( (byte) 1)) 
				frm.chckbxRelatorioDeVenda.setSelected(true);
			else
				frm.chckbxRelatorioDeVenda.setSelected(false);
		
			if (tbUsuario.getTbPermissao().getRelatorioDeVendaDetalhado() == ( (byte) 1)) 
				frm.chckbxRelatorioDeVendaDetalhado.setSelected(true);
			else
				frm.chckbxRelatorioDeVendaDetalhado.setSelected(false);
			
			if (tbUsuario.getTbPermissao().getRelatorioDeVendaDetalhado() == ( (byte) 1)) 
				frm.chckbxRelatorioDeProduto.setSelected(true);
			else
				frm.chckbxRelatorioDeProduto.setSelected(false);
			
			frm.desabilitarHabilitarComponentes(true);
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
