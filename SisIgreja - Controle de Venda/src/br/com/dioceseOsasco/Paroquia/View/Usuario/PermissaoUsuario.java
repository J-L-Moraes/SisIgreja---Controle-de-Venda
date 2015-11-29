package br.com.dioceseOsasco.Paroquia.View.Usuario;

import br.com.dioceseOsasco.Paroquia.Model.TbPermissao;

public class PermissaoUsuario {


	
	
	/**
	 * Permissão de Gerente
	 * @return TbPermissao
	 */
	public TbPermissao permissaoGerente(){
		
		TbPermissao tbPermissao = new TbPermissao();
		
		tbPermissao.setAbrirCaixa( (byte) 1 );
		tbPermissao.setAnalisarPedido((byte) 1 );
		
		tbPermissao.setCadastrarProduto((byte) 1 );
		tbPermissao.setAlterarProduto((byte) 1 );
		tbPermissao.setProcurarProduto((byte) 1 );
		tbPermissao.setExcluirProduto((byte) 0 );
		
		tbPermissao.setCadastrarEvento((byte) 1 );
		tbPermissao.setAlterarEvento((byte) 1 );
		tbPermissao.setPesquisarEvento((byte) 1 );
		tbPermissao.setExcluirEvento((byte) 0 );
		
		tbPermissao.setCadastrarUsuario((byte) 0 );
		tbPermissao.setAlterarUsuario((byte) 0 );
		tbPermissao.setProcurarUsuario((byte) 0 );
		tbPermissao.setExcluirUsuario((byte) 0 );
		tbPermissao.setRedefinirSenhaOutros((byte) 0 );
		
		tbPermissao.setDioceseParoquia((byte) 1 );
		tbPermissao.setCadastrarComunidade((byte) 1 );
		tbPermissao.setAtualizarComunidade((byte) 1 );
		tbPermissao.setProcurarComunidade((byte) 1 );
		tbPermissao.setExcluirComunidade((byte) 0 );
		
		tbPermissao.setRelatorioDeVenda((byte) 1 );
		tbPermissao.setRelatorioDeVendaDetalhado((byte) 1 );
		tbPermissao.setRelatorioDeProduto((byte) 1 );
		
		return tbPermissao;
		
	}
	
	/**
	 * Permissão de Vendedor
	 * @return TbPermissao
	 */
	public TbPermissao permissaoVendedor(){
		TbPermissao tbPermissao = new TbPermissao();
		
		tbPermissao.setAbrirCaixa( (byte) 1 );
		tbPermissao.setAnalisarPedido((byte) 0 );
		
		tbPermissao.setCadastrarProduto((byte) 1 );
		tbPermissao.setAlterarProduto((byte) 1 );
		tbPermissao.setProcurarProduto((byte) 1 );
		tbPermissao.setExcluirProduto((byte) 0 );
		
		tbPermissao.setCadastrarEvento((byte) 1 );
		tbPermissao.setAlterarEvento((byte) 1 );
		tbPermissao.setPesquisarEvento((byte) 1 );
		tbPermissao.setExcluirEvento((byte) 0 );
		
		tbPermissao.setCadastrarUsuario((byte) 0 );
		tbPermissao.setAlterarUsuario((byte) 0 );
		tbPermissao.setProcurarUsuario((byte) 0 );
		tbPermissao.setExcluirUsuario((byte) 0 );
		tbPermissao.setRedefinirSenhaOutros((byte) 0 );
		
		tbPermissao.setDioceseParoquia((byte) 0 );
		tbPermissao.setCadastrarComunidade((byte) 1 );
		tbPermissao.setAtualizarComunidade((byte) 1 );
		tbPermissao.setProcurarComunidade((byte) 0 );
		tbPermissao.setExcluirComunidade((byte) 0 );
		
		tbPermissao.setRelatorioDeVenda((byte) 0 );
		tbPermissao.setRelatorioDeVendaDetalhado((byte) 0 );
		tbPermissao.setRelatorioDeProduto((byte) 0 );
		
		return tbPermissao;
		
	}
	
	/**
	 * Permissão de Administrador
	 * @return TbPermissao
	 */
	public TbPermissao permissaoAdministrador(){
		
		TbPermissao tbPermissao = new TbPermissao();
		
		tbPermissao.setAbrirCaixa( (byte) 1 );
		tbPermissao.setAnalisarPedido((byte) 1 );
		
		tbPermissao.setCadastrarProduto((byte) 1 );
		tbPermissao.setAlterarProduto((byte) 1 );
		tbPermissao.setProcurarProduto((byte) 1 );
		tbPermissao.setExcluirProduto((byte) 1 );
		
		tbPermissao.setCadastrarEvento((byte) 1 );
		tbPermissao.setAlterarEvento((byte) 1 );
		tbPermissao.setPesquisarEvento((byte) 1 );
		tbPermissao.setExcluirEvento((byte) 1 );
		
		tbPermissao.setCadastrarUsuario((byte) 1 );
		tbPermissao.setAlterarUsuario((byte) 1 );
		tbPermissao.setProcurarUsuario((byte) 1 );
		tbPermissao.setExcluirUsuario((byte) 1 );
		tbPermissao.setRedefinirSenhaOutros((byte) 1 );
		
		tbPermissao.setDioceseParoquia((byte) 1 );
		tbPermissao.setCadastrarComunidade((byte) 1 );
		tbPermissao.setAtualizarComunidade((byte) 1 );
		tbPermissao.setProcurarComunidade((byte) 1 );
		tbPermissao.setExcluirComunidade((byte) 1 );
		
		tbPermissao.setRelatorioDeVenda((byte) 1 );
		tbPermissao.setRelatorioDeVendaDetalhado((byte) 1 );
		tbPermissao.setRelatorioDeProduto((byte) 1 );
		
		
		
		return tbPermissao;
	}
}
