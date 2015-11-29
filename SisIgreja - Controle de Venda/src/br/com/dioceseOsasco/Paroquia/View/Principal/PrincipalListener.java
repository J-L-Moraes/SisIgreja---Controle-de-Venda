package br.com.dioceseOsasco.Paroquia.View.Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.View.Caixa.AnalisarPedido.FrmAnalisarPedido;
import br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal.UtilitarioNotaFiscal;
import br.com.dioceseOsasco.Paroquia.View.Caixa.Principal.FrmCaixa;
import br.com.dioceseOsasco.Paroquia.View.Configuracao.FrmConfiguracao;
import br.com.dioceseOsasco.Paroquia.View.Evento.Atualizar.FrmAtualizarEvento;
import br.com.dioceseOsasco.Paroquia.View.Evento.Cadastrar.FrmCadastrarEvento;
import br.com.dioceseOsasco.Paroquia.View.Evento.Consultar.FrmConsultarEvento;
import br.com.dioceseOsasco.Paroquia.View.Evento.Excluir.FrmExcluirEvento;
import br.com.dioceseOsasco.Paroquia.View.Local.Atualizar.FrmAtualizarLocal;
import br.com.dioceseOsasco.Paroquia.View.Local.Cadastrar.FrmCadastrarLocal;
import br.com.dioceseOsasco.Paroquia.View.Local.Consultar.FrmConsultarLocal;
import br.com.dioceseOsasco.Paroquia.View.Local.Excluir.FrmExcluirLocal;
import br.com.dioceseOsasco.Paroquia.View.Localizacao.FrmLocalizacao;
import br.com.dioceseOsasco.Paroquia.View.Produto.Atualizar.FrmAtualizarProduto;
import br.com.dioceseOsasco.Paroquia.View.Produto.Cadastrar.FrmCadastrarProduto;
import br.com.dioceseOsasco.Paroquia.View.Produto.Consultar.FrmConsultaProduto;
import br.com.dioceseOsasco.Paroquia.View.Produto.Excluir.FrmExcluirProduto;
import br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioDetalhado.FrmRelatorioDetalhado;
import br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioProduto.FrmRelatorioProduto;
import br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioSimples.FrmRelatorioSimples;
import br.com.dioceseOsasco.Paroquia.View.Sobre.FrmSobre;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Alterar.FrmAlterarUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.AlterarSenha.FrmAlterarSenha;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Cadastrar.FrmCadastrarUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar.FrmConsultarUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.Excluir.FrmExcluirUsuario;
import br.com.dioceseOsasco.Paroquia.View.Usuario.RedefinirSenha.FrmRedefinirSenha;

public class PrincipalListener{
	
	private FrmPrincipal frm;
	
	PrincipalListener(FrmPrincipal frm){
		this.frm = frm;
		adicionaListener();
	}
	
	private void adicionaListener(){
		
		//FrmCaixa
		frm.abrirCaixa.addActionListener(new AbrirCaixaActionListener());
		frm.analisarPedido.addActionListener(new VerificarPedidoActionListener());
		
		//Produto
		frm.cadastarProduto.addActionListener(new CadastrarProdutoActionListener());
		frm.alterarProduto.addActionListener(new AlterarProdutoActionListener());
		frm.excluirProduto.addActionListener(new ExcluirProdutoActionListener());
		frm.procurarProduto.addActionListener(new ProcurarProdutoActionListener());
		
		//Evento
		frm.cadastarEvento.addActionListener(new CadastrarEventoActionListener());
		frm.alterarEvento.addActionListener(new AlterarEventoActionListener());
		frm.excluirEvento.addActionListener(new ExcluirEventoActionListener());
		frm.procurarEvento.addActionListener(new ProcurarEventoActionListener());
		
		//Usuário
		frm.cadastrarUsuario.addActionListener(new CadastrarUsuarioActionListener());
		frm.alterarUsuario.addActionListener(new AlterarUsuarioActionListener());
		frm.procurarUsuario.addActionListener(new ProcurarUsuarioActionListener());
		frm.excluirUsuario.addActionListener(new ExcluirUsuarioActionListener());
		frm.redefinirSenhaUsuario.addActionListener(new RedefinirSenhaActionListener());
		
		//Local e Localização
		frm.alterarLocalizacao.addActionListener(new AlterarLocalizacaoActionListener());
		frm.cadastrarComunidade.addActionListener(new CadastrarComunidadeActionListener());
		frm.alterarComunidade.addActionListener(new AlterarComunidadeActionListener());
		frm.procurarComunidade.addActionListener(new ProcurarComunidadeActionListener());
		frm.excluirComunidade.addActionListener(new ExcluirComunidadeActionListener());
		
		//Relatório
		frm.relatorioSimples.addActionListener(new RelatorioSimplesActionListener());
		frm.RelatorioDetalhado.addActionListener(new RelatorioDetalhadoActionListener());
		frm.RelatorioProduto.addActionListener(new RelatorioProdutoActionListener());
		
		//Configuração
		frm.alterarCaminhoConexaoConfiguracao.addActionListener(new AlterarCaminhoConexaoConfiguracaoActionListener());
		frm.redefinirSenha.addActionListener(new AlterarSenhaActionListener());
		
		//Ajuda e Sobre
		frm.about.addActionListener(new SobreActionListener());
		frm.ajuda.addActionListener(new AjudaActionListener());
	
	}
	
	//Caixa - Abrir Caixa
	private class AbrirCaixaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			FrmCaixa frmCaixa = new FrmCaixa();
			frmCaixa.CadastrarVenda();
			
			if (frmCaixa.getCodVenda() == null) {
				frmCaixa.dispose();
			}else{
				new UtilitarioNotaFiscal().setInciarNotaFiscal(frmCaixa.getCodVenda());
				frmCaixa.setVisible(true);
			}
		}
	}
	//Caixa - Verificar Pedido
	private class VerificarPedidoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			FrmAnalisarPedido frmAnalisarPedido = new FrmAnalisarPedido();
			if (frmAnalisarPedido.cancelarAbertura){
				frmAnalisarPedido.dispose();
			}else{
				frmAnalisarPedido.setVisible(true);
			}
			
		}
	}
	

	//Evento CadastrarProduto
	private class CadastrarProdutoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FrmCadastrarProduto frmCadastrarProduto = new FrmCadastrarProduto();
			frmCadastrarProduto.setVisible(true);
		}
	}
	//Evento AtualizarProduto
	private class AlterarProdutoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FrmAtualizarProduto frmAlterarProduto = new FrmAtualizarProduto();
			frmAlterarProduto.setVisible(true);
		}
	}
	//Evento FrmProcurarProduto
	private class ProcurarProdutoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FrmConsultaProduto frmProcurarProduto = new FrmConsultaProduto();
			frmProcurarProduto.setVisible(true);
		}
	}
	//Evento FrmExcluirProduto
	private class ExcluirProdutoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			FrmExcluirProduto frmExcluirProduto = new FrmExcluirProduto();
			frmExcluirProduto.setVisible(true);
		}
	}
	
	//Evento - CadastrarEvento
	private class CadastrarEventoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmCadastrarEvento frmCadastrarEvento = new FrmCadastrarEvento();
			frmCadastrarEvento.setVisible(true);
		}
	}
	//Evento - AlterarEvento
	private class AlterarEventoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmAtualizarEvento frmAtualizarEvento = new FrmAtualizarEvento();
			frmAtualizarEvento.setVisible(true);
		}
	}
	//Evento - ConsultarEvento
	private class ProcurarEventoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmConsultarEvento frmConsultarEvento = new FrmConsultarEvento();
			frmConsultarEvento.setVisible(true);
		}
	}
	//Evento - ExcluirEvento
	private class ExcluirEventoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmExcluirEvento frmExcluirEvento = new FrmExcluirEvento();
			frmExcluirEvento.setVisible(true);
		}
	}
	
	//Usuario
	//Cadastrar Usuario
	private class CadastrarUsuarioActionListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			FrmCadastrarUsuario frmCadastrarUsuario = new FrmCadastrarUsuario();
			frmCadastrarUsuario.setVisible(true);
		}
	}
	//Alterar Usuario
	private class AlterarUsuarioActionListener implements ActionListener{
		public void actionPerformed (ActionEvent event){
			FrmAlterarUsuario frmAlterarUsuario = new FrmAlterarUsuario();
			frmAlterarUsuario.setVisible(true);
		}
	}
	
	//Procurar Usuario
	private class ProcurarUsuarioActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			FrmConsultarUsuario frmConsultarUsuario = new FrmConsultarUsuario();
			frmConsultarUsuario.setVisible(true);
		}
		
	}
	
	//Excluir Usuario
	private class ExcluirUsuarioActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			FrmExcluirUsuario frmExcluirUsuario = new FrmExcluirUsuario();
			frmExcluirUsuario.setVisible(true);
		}
		
	}

	//Redefinir Senha de Outros Usuarios
	private class RedefinirSenhaActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			FrmRedefinirSenha frmRedefinirSenha = new FrmRedefinirSenha();
			frmRedefinirSenha.setVisible(true);
		}
		
	}
	
	
	
	//Local e Localização
	//Localização - Cadastrar ou alterar Localização (Paroquia e Diocese)
	private class AlterarLocalizacaoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmLocalizacao frmLocalizacao = new FrmLocalizacao();
			frmLocalizacao.setVisible(true);
		}
	}
	//Local - Cadastrar Comunidade
	private class CadastrarComunidadeActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmCadastrarLocal frmCadastrarLocal = new FrmCadastrarLocal();
			frmCadastrarLocal.setVisible(true);
		}
	}
	
	//Local - Alterar Comunidade
	private class AlterarComunidadeActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmAtualizarLocal frmAtualizarLocal = new FrmAtualizarLocal();
			frmAtualizarLocal.setVisible(true);
		}
	}
	//Local - Procurar Comunidade
	private class ProcurarComunidadeActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			
			FrmConsultarLocal frmConsultarLocal = new FrmConsultarLocal();
			frmConsultarLocal.setVisible(true); 
			
			
		}
	}
	//Local - Excluir Comunidade
	private class ExcluirComunidadeActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmExcluirLocal frmExcluirLocal = new FrmExcluirLocal();
			frmExcluirLocal.setVisible(true);
		}
	}
	
	
	//Relatório
	//Relatório Simples
	private class RelatorioSimplesActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmRelatorioSimples frmRelatorioSimples = new FrmRelatorioSimples();
			frmRelatorioSimples.setVisible(true);
		}
	}	
	
	//Relatório Detalhado
	private class RelatorioDetalhadoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmRelatorioDetalhado frmRelatorioDetalhado = new FrmRelatorioDetalhado();
			frmRelatorioDetalhado.setVisible(true);
		}
	}	
	
	//Relatório Produto
	private class RelatorioProdutoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmRelatorioProduto frmRelatorioProduto = new FrmRelatorioProduto();
			frmRelatorioProduto.setVisible(true);
		}
	}	

	
	//Configuração
	//Alterar Banco de Dados
	private class AlterarCaminhoConexaoConfiguracaoActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmConfiguracao frmConfiguracao = new FrmConfiguracao();
			frmConfiguracao.setVisible(true);
		}
	}	
	//Alterar Senha
	private class AlterarSenhaActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmAlterarSenha frmAlterarSenha = new FrmAlterarSenha();
			frmAlterarSenha.setVisible(true);
		}
	}
	
	//Ajuda e Sobre
	
	
	//Sobre
	private class SobreActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			FrmSobre frmSobre = new FrmSobre();
			frmSobre.setVisible(true);
		}
	}
	
	//Ajuda
	private class AjudaActionListener implements ActionListener {
		public void actionPerformed (ActionEvent event ) {
			
			String ManualUsuario = this.getClass().getClassLoader().getResource("").getPath() + "br/com/dioceseOsasco/Paroquia/View/Manual/Manual do Usuario.pdf";
			
			try {
				//Abre o PDF no Editor Padrão
				java.awt.Desktop.getDesktop().open( new File( ManualUsuario.replaceAll("%20", " ") ) );
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Não foi possível Localizar o Arquivo: Manual do Usuario.\nDetalhes técnicos: "+e.getMessage()+".", frm.getTitle(),JOptionPane.OK_OPTION);
				
			}

		}
	}
	
	
	

}
