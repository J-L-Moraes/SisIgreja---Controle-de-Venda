package br.com.dioceseOsasco.Paroquia.View.Principal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.StatusBar.StatusBar;
import br.com.dioceseOsasco.Paroquia.View.Componente.StatusBar.StatusBarPanel;
import br.com.dioceseOsasco.Paroquia.View.Login.FrmLogin;

@SuppressWarnings("serial")
public class FrmPrincipal extends JRibbonFrame {

	//Caixa
	JRibbonBand caixaBand;
	JCommandButton abrirCaixa;
	JCommandButton analisarPedido;
	RibbonTask caixaTask;
	
	//Produto
	JRibbonBand produtoBand;
	JCommandButton cadastarProduto;
	JCommandButton alterarProduto;
	JCommandButton procurarProduto;
	JCommandButton excluirProduto;
	RibbonTask produtoTask;
	
	//Evento
	JRibbonBand eventoBand;
	JCommandButton cadastarEvento;
	JCommandButton alterarEvento;
	JCommandButton procurarEvento;
	JCommandButton excluirEvento;
	RibbonTask eventoTask;
	
	//Usuário
	JRibbonBand UsuarioBand ;
	JCommandButton cadastrarUsuario ;
	JCommandButton alterarUsuario ;
	JCommandButton procurarUsuario ;
	JCommandButton excluirUsuario ;
	JCommandButton redefinirSenhaUsuario ;
	RibbonTask UsuarioTask;
	

	//Local
	JRibbonBand localBand;
	JCommandButton alterarLocalizacao;
	JCommandButton cadastrarComunidade;
	JCommandButton alterarComunidade;
	JCommandButton procurarComunidade; 
	JCommandButton excluirComunidade; 
	RibbonTask localTask;

	
	//Relatório
	JRibbonBand relatorioBand;
	JCommandButton relatorioSimples;
	JCommandButton RelatorioDetalhado;
	JCommandButton RelatorioProduto;
	RibbonTask relatorioTask;

	//Configuração
	JRibbonBand configuracaoBand;
	JCommandButton alterarCaminhoConexaoConfiguracao;
	JCommandButton redefinirSenha;
	RibbonTask configuracaoTask;
	
	//HELP
	JCommandButton ajuda;
	JCommandButton about;//Sobre
	
	public FrmPrincipal() {
		Interface();
		new PrincipalListener(this);
	}
	
	
	
	/**
	 * Interface
	 */
	@SuppressWarnings({ "static-access" })
	void Interface() {
		
		setTitle("SisIgreja - Controle de Venda");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
	
		setSize(800, 600);
		this.setLocationRelativeTo(null);
		
		//Maximilizar à Tela
		setExtendedState(FrmPrincipal.MAXIMIZED_BOTH);
		
		setModalExclusionType(getModalExclusionType());
		setMaximizedBounds(getMaximizedBounds());
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Evento Fechar do Formulario...
		addWindowListener(new java.awt.event.WindowAdapter() {  
	        public void windowClosing(java.awt.event.WindowEvent e) {  
	            if (e.getID() == WindowEvent.WINDOW_CLOSING){  
	                int selectedOption = JOptionPane.showConfirmDialog(null,"Deseja realizar logoff do sistema?", getTitle(), JOptionPane.YES_NO_OPTION);  
	                if(selectedOption == JOptionPane.YES_OPTION){  
	                	FrmLogin frmLogin = new FrmLogin();
	                	frmLogin.setVisible(true);
	                	dispose();                          
	                }     
	            }     
	        }  
	    });
		

	

		
		/*
		 *  Create JRibbonBand - Caixa
		 */
		caixaBand = new JRibbonBand("", null);
		
		// JCommandButton
		abrirCaixa = new JCommandButton("Abrir Caixa",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AbrirCaixa.png")));
		analisarPedido = new JCommandButton("Analisar Pedido",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/Analisar.png")));
		
		// JRibbonBand
		caixaBand.addCommandButton(abrirCaixa, RibbonElementPriority.TOP);
		caixaBand.addCommandButton(analisarPedido, RibbonElementPriority.TOP);
	
		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesCaixa = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesCaixa.add(new CoreRibbonResizePolicies.None(caixaBand.getControlPanel()));
		resizePoliciesCaixa.add(new CoreRibbonResizePolicies.Mirror(caixaBand.getControlPanel()));
		resizePoliciesCaixa.add(new CoreRibbonResizePolicies.Low2Mid(caixaBand.getControlPanel()));
		resizePoliciesCaixa.add(new CoreRibbonResizePolicies.Mid2Low(caixaBand.getControlPanel()));
		resizePoliciesCaixa.add(new CoreRibbonResizePolicies.High2Low(caixaBand.getControlPanel()));
		
		caixaBand.setResizePolicies(resizePoliciesCaixa);
		
		// Create new RibbonTask and add JRibbonBand into it
		caixaTask = new RibbonTask("Caixa", caixaBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(caixaTask);

		
		
		
		
		/*
		 *  Create JRibbonBand - Produto
		 */
		produtoBand = new JRibbonBand("", null);
		
		// JCommandButton
		cadastarProduto = new JCommandButton("Cadastrar Produto",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Add.png")));
		alterarProduto = new JCommandButton("Alterar Produto",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Modify.png")));
		procurarProduto = new JCommandButton("Procurar Produto",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Search.png")));
		excluirProduto = new JCommandButton("Excluir Produto",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Menu/Delete.png")));

		// JRibbonBand
		produtoBand.addCommandButton(cadastarProduto, RibbonElementPriority.TOP);
		produtoBand.addCommandButton(alterarProduto, RibbonElementPriority.TOP);
		produtoBand.addCommandButton(procurarProduto, RibbonElementPriority.MEDIUM);
		produtoBand.addCommandButton(excluirProduto, RibbonElementPriority.MEDIUM);

		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesProduto = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesProduto.add(new CoreRibbonResizePolicies.None(produtoBand.getControlPanel()));
		resizePoliciesProduto.add(new CoreRibbonResizePolicies.Mirror(produtoBand.getControlPanel()));
		resizePoliciesProduto.add(new CoreRibbonResizePolicies.Mid2Low(produtoBand.getControlPanel()));
		resizePoliciesProduto.add(new CoreRibbonResizePolicies.High2Low(produtoBand.getControlPanel()));
		
		produtoBand.setResizePolicies(resizePoliciesProduto);
				
		// Create new RibbonTask and add JRibbonBand into it
		produtoTask = new RibbonTask("Produto", produtoBand);
		
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(produtoTask);

		
		/*
		 *  Create JRibbonBand - Evento
		 */
		eventoBand = new JRibbonBand("", null);
		
		// JCommandButton
		cadastarEvento = new JCommandButton("Cadastrar Evento",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioAdicionar.png")));
		alterarEvento = new JCommandButton("Alterar Evento",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioAtualizar.png")));
		procurarEvento = new JCommandButton("Pesquisar Evento",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioConsultar.png")));
		excluirEvento = new JCommandButton("Excluir Evento",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioExcluir.png")));

		// JRibbonBand
		eventoBand.addCommandButton(cadastarEvento, RibbonElementPriority.TOP);
		eventoBand.addCommandButton(alterarEvento, RibbonElementPriority.TOP);
		eventoBand.addCommandButton(procurarEvento, RibbonElementPriority.MEDIUM);
		eventoBand.addCommandButton(excluirEvento, RibbonElementPriority.LOW);

		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesEvento = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesEvento.add(new CoreRibbonResizePolicies.None(eventoBand.getControlPanel()));
		resizePoliciesEvento.add(new CoreRibbonResizePolicies.Mirror(eventoBand.getControlPanel()));
		resizePoliciesEvento.add(new CoreRibbonResizePolicies.Mid2Low(eventoBand.getControlPanel()));
		resizePoliciesEvento.add(new CoreRibbonResizePolicies.High2Low(eventoBand.getControlPanel()));
		
		eventoBand.setResizePolicies(resizePoliciesEvento);
		
		// Create new RibbonTask and add JRibbonBand into it
		eventoTask = new RibbonTask("Evento", eventoBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(eventoTask);
		
		
		
		/*
		 *  Create JRibbonBand - Usuário
		 */
		
		UsuarioBand = new JRibbonBand("", null);
		
		// JCommandButton
		cadastrarUsuario = new JCommandButton("Cadastrar usuário",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Adicionar.png")));
		alterarUsuario = new JCommandButton("Alterar usuário",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Checar.png")));
		procurarUsuario = new JCommandButton("Procurar usuário",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Pesquisar.png")));
		excluirUsuario = new JCommandButton("Excluir usuário",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Excluir.png")));
		redefinirSenhaUsuario = new JCommandButton("Redefinir senha",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Senha.png")));
		
		// JRibbonBand
		UsuarioBand.addCommandButton(cadastrarUsuario, RibbonElementPriority.TOP);
		UsuarioBand.addCommandButton(alterarUsuario, RibbonElementPriority.TOP);
		UsuarioBand.addCommandButton(procurarUsuario, RibbonElementPriority.MEDIUM);
		UsuarioBand.addCommandButton(excluirUsuario, RibbonElementPriority.MEDIUM);
		UsuarioBand.addCommandButton(redefinirSenhaUsuario, RibbonElementPriority.MEDIUM);
		
		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesUsuario = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesUsuario.add(new CoreRibbonResizePolicies.None(UsuarioBand.getControlPanel()));
		resizePoliciesUsuario.add(new CoreRibbonResizePolicies.Mirror(UsuarioBand.getControlPanel()));
		resizePoliciesUsuario.add(new CoreRibbonResizePolicies.Mid2Low(UsuarioBand.getControlPanel()));
		resizePoliciesUsuario.add(new CoreRibbonResizePolicies.High2Low(UsuarioBand.getControlPanel()));
		
		UsuarioBand.setResizePolicies(resizePoliciesUsuario);
		
		// Create new RibbonTask and add JRibbonBand into it
		UsuarioTask = new RibbonTask("Usuário", UsuarioBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(UsuarioTask);
		
				
		
		/*
		 *  Create JRibbonBand - Local
		 */
		
		localBand = new JRibbonBand("", null);
		
		// JCommandButton
		alterarLocalizacao = new JCommandButton("Alterar ou Incluir Diocese | Paróquia",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/Localizacao.png")));//FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		cadastrarComunidade = new JCommandButton("Cadastrar Comunidade",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/AdicionarComunidade.png")));
		alterarComunidade = new JCommandButton("Atualizar Comunidade",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/AtualizarComunidade.png")));
		procurarComunidade = new JCommandButton("Procurar Comunidade",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/PesquisarComunidade.png")));
		excluirComunidade = new JCommandButton("Excluir Comunidade",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/ExcluirComunidade.png")));
		
		
		
		
		// JRibbonBand
		localBand.addCommandButton(alterarLocalizacao, RibbonElementPriority.TOP);
		localBand.addCommandButton(cadastrarComunidade, RibbonElementPriority.TOP);
		localBand.addCommandButton(alterarComunidade, RibbonElementPriority.MEDIUM);
		localBand.addCommandButton(procurarComunidade, RibbonElementPriority.MEDIUM);
		localBand.addCommandButton(excluirComunidade, RibbonElementPriority.MEDIUM);
		
		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesLocal = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesLocal.add(new CoreRibbonResizePolicies.None(localBand.getControlPanel()));
		resizePoliciesLocal.add(new CoreRibbonResizePolicies.Mirror(localBand.getControlPanel()));
		resizePoliciesLocal.add(new CoreRibbonResizePolicies.Mid2Low(localBand.getControlPanel()));
		resizePoliciesLocal.add(new CoreRibbonResizePolicies.High2Low(localBand.getControlPanel()));
		
		localBand.setResizePolicies(resizePoliciesLocal);
		
		// Create new RibbonTask and add JRibbonBand into it
		localTask = new RibbonTask("Localização", localBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(localTask);
		
		
		
		/*
		 *  Create JRibbonBand - Relatório
		 */
		
		relatorioBand = new JRibbonBand("", null);
		
		// JCommandButton
		relatorioSimples = new JCommandButton("Relatório Simples de Venda",
				new ResizableIconFromResource().getResizableIconFromResource(
						FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/Relatorio1.png")));
		RelatorioDetalhado = new JCommandButton("Relatório Detalhado de Venda",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/Relatorio2.png")));		
		RelatorioProduto = new JCommandButton("Relatório de Produto",
				new ResizableIconFromResource().getResizableIconFromResource(
						FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/Relatorio3.png")));
		
		// JRibbonBand
		relatorioBand.addCommandButton(relatorioSimples, RibbonElementPriority.MEDIUM);
		relatorioBand.addCommandButton(RelatorioDetalhado, RibbonElementPriority.MEDIUM);
		relatorioBand.addCommandButton(RelatorioProduto, RibbonElementPriority.MEDIUM);
		
		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesRelatorio = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesRelatorio.add(new CoreRibbonResizePolicies.None(relatorioBand.getControlPanel()));
		resizePoliciesRelatorio.add(new CoreRibbonResizePolicies.Mirror(relatorioBand.getControlPanel()));
		resizePoliciesRelatorio.add(new CoreRibbonResizePolicies.Mid2Low(relatorioBand.getControlPanel()));
		resizePoliciesRelatorio.add(new CoreRibbonResizePolicies.High2Low(relatorioBand.getControlPanel()));
		
		relatorioBand.setResizePolicies(resizePoliciesRelatorio);

		// Create new RibbonTask and add JRibbonBand into it
		relatorioTask = new RibbonTask("Relatório", relatorioBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(relatorioTask);
	
		
		
		

		/*
		 *  Create JRibbonBand - Configuração
		 */
		configuracaoBand = new JRibbonBand("", null);
		
		// JCommandButton
		alterarCaminhoConexaoConfiguracao = new JCommandButton("Configuração do Banco de Dados",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Configuracao/Configuracao.png")));
		redefinirSenha = new JCommandButton("Alterar sua senha",
				new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Senha.png")));
		
		// JRibbonBand
		configuracaoBand.addCommandButton(alterarCaminhoConexaoConfiguracao, RibbonElementPriority.TOP);
		configuracaoBand.addCommandButton(redefinirSenha, RibbonElementPriority.TOP);
		
		// JRibbonBand - Politicas
		List<RibbonBandResizePolicy> resizePoliciesConfiguracao = new ArrayList<RibbonBandResizePolicy>();
		resizePoliciesConfiguracao.add(new CoreRibbonResizePolicies.None(configuracaoBand.getControlPanel()));
		resizePoliciesConfiguracao.add(new CoreRibbonResizePolicies.Mirror(configuracaoBand.getControlPanel()));
		resizePoliciesConfiguracao.add(new CoreRibbonResizePolicies.Mid2Low(configuracaoBand.getControlPanel()));
		resizePoliciesConfiguracao.add(new CoreRibbonResizePolicies.High2Low(configuracaoBand.getControlPanel()));
		
		configuracaoBand.setResizePolicies(resizePoliciesConfiguracao);

		// Create new RibbonTask and add JRibbonBand into it
		configuracaoTask = new RibbonTask("Configurações", configuracaoBand);
		// Add Ribbon Task into JRibbonFrame
		this.getRibbon().addTask(configuracaoTask);
				
		//Botão HELP
		ajuda = new JCommandButton("Ajuda", new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/help/HELP.png")));
		this.getRibbon().addHelpPanelComponent(ajuda);
		//Sobre o Software
		about = new JCommandButton("Sobre", new ResizableIconFromResource().getResizableIconFromResource(FrmPrincipal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/help/about.png")));
		this.getRibbon().addHelpPanelComponent(about);
		
		//Status Bar
		getContentPane().add(getStatusBar(), BorderLayout.SOUTH);
		
	}


	/**
	 * Verifica as permisões que o usuário possui e habilita para o usuário.
	 * @param tbUsuario
	 */
	public void verificacaoPermissao(TbUsuario tbUsuario) {

		if (tbUsuario.getTbPermissao().getAbrirCaixa() == ((byte) 1))
			abrirCaixa.setEnabled(true);
		else
			abrirCaixa.setEnabled(false);

		if (tbUsuario.getTbPermissao().getAnalisarPedido() == ((byte) 1))
			analisarPedido.setEnabled(true);
		else
			analisarPedido.setEnabled(false);

		if (tbUsuario.getTbPermissao().getCadastrarProduto() == ((byte) 1))
			cadastarProduto.setEnabled(true);
		else
			cadastarProduto.setEnabled(false);

		if (tbUsuario.getTbPermissao().getAlterarProduto() == ((byte) 1))
			alterarProduto.setEnabled(true);
		else
			alterarProduto.setEnabled(false);

		if (tbUsuario.getTbPermissao().getProcurarProduto() == ((byte) 1))
			procurarProduto.setEnabled(true);
		else
			procurarProduto.setEnabled(false);

		if (tbUsuario.getTbPermissao().getExcluirProduto() == ((byte) 1))
			excluirProduto.setEnabled(true);
		else
			excluirProduto.setEnabled(false);

		if (tbUsuario.getTbPermissao().getCadastrarEvento() == ((byte) 1))
			cadastarEvento.setEnabled(true);
		else
			cadastarEvento.setEnabled(false);

		if (tbUsuario.getTbPermissao().getAlterarEvento() == ((byte) 1))
			alterarEvento.setEnabled(true);
		else
			alterarEvento.setEnabled(false);

		if (tbUsuario.getTbPermissao().getPesquisarEvento() == ((byte) 1))
			procurarEvento.setEnabled(true);
		else
			procurarEvento.setEnabled(false);

		if (tbUsuario.getTbPermissao().getExcluirEvento() == ((byte) 1))
			excluirEvento.setEnabled(true);
		else
			excluirEvento.setEnabled(false);

		if (tbUsuario.getTbPermissao().getCadastrarUsuario() == ((byte) 1))
			cadastrarUsuario.setEnabled(true);
		else
			cadastrarUsuario.setEnabled(false);

		if (tbUsuario.getTbPermissao().getAlterarUsuario() == ((byte) 1))
			alterarUsuario.setEnabled(true);
		else
			alterarUsuario.setEnabled(false);

		if (tbUsuario.getTbPermissao().getProcurarUsuario() == ((byte) 1))
			procurarUsuario.setEnabled(true);
		else
			procurarUsuario.setEnabled(false);

		if (tbUsuario.getTbPermissao().getExcluirUsuario() == ((byte) 1))
			excluirUsuario.setEnabled(true);
		else
			excluirUsuario.setEnabled(false);

		if (tbUsuario.getTbPermissao().getRedefinirSenhaOutros() == ((byte) 1))
			redefinirSenha.setEnabled(true);
		else
			redefinirSenha.setEnabled(false);

		if (tbUsuario.getTbPermissao().getDioceseParoquia() == ((byte) 1))
			alterarLocalizacao.setEnabled(true);
		else
			alterarLocalizacao.setEnabled(false);

		if (tbUsuario.getTbPermissao().getCadastrarComunidade() == ((byte) 1))
			cadastrarComunidade.setEnabled(true);
		else
			cadastrarComunidade.setEnabled(false);

		if (tbUsuario.getTbPermissao().getAtualizarComunidade() == ((byte) 1))
			alterarComunidade.setEnabled(true);
		else
			alterarComunidade.setEnabled(false);

		if (tbUsuario.getTbPermissao().getProcurarComunidade() == ((byte) 1))
			procurarComunidade.setEnabled(true);
		else
			procurarComunidade.setEnabled(false);

		if (tbUsuario.getTbPermissao().getExcluirComunidade() == ((byte) 1))
			excluirComunidade.setEnabled(true);
		else
			excluirComunidade.setEnabled(false);

		if (tbUsuario.getTbPermissao().getRelatorioDeVenda() == ((byte) 1))
			relatorioSimples.setEnabled(true);
		else
			relatorioSimples.setEnabled(false);

		if (tbUsuario.getTbPermissao().getRelatorioDeVendaDetalhado() == ((byte) 1))
			RelatorioDetalhado.setEnabled(true);
		else
			RelatorioDetalhado.setEnabled(false);

		if (tbUsuario.getTbPermissao().getRelatorioDeProduto() == ((byte) 1))
			RelatorioProduto.setEnabled(true);
		else
			RelatorioProduto.setEnabled(false);

	}

	

	
	private StatusBar statusBar = null;
	private StatusBarPanel statusBarPanel1 = null;
	private StatusBarPanel statusBarPanel2 = null;
	private StatusBarPanel statusBarPanel3 = null;
	private StatusBarPanel statusBarPanel4 = null;
	
	private TbUsuario tbUsuario;
	
	private StatusBar getStatusBar() {
		if (statusBar==null) {		
			statusBar = new StatusBar(new StatusBarPanel[] { 
					getStatusBarPanel1(), getStatusBarPanel2(), getStatusBarPanel3(), getStatusBarPanel4() 
				} );
		}
		return statusBar;
	}

	@SuppressWarnings("static-access")
	private StatusBarPanel getStatusBarPanel1() {
		if (statusBarPanel1==null) {
			statusBarPanel1=new StatusBarPanel();
			statusBarPanel1.setPanelWidth(90);
			statusBarPanel1.setWidthFixed(true);
			statusBarPanel1.setLayout(new BorderLayout());
			
			tbUsuario = new TbUsuario().getInstance(true);
			JLabel BemVindoaoSistema = new JLabel("Bem-vindo ao sistema: "+ tbUsuario.getNome());
			
			BemVindoaoSistema.setFont(new Font("Dialog", Font.PLAIN, 12));
			statusBarPanel1.add(BemVindoaoSistema);
		}
		return statusBarPanel1;
	}

	
	@SuppressWarnings("static-access")
	private StatusBarPanel getStatusBarPanel2() {
		if (statusBarPanel2==null) {
			statusBarPanel2=new StatusBarPanel();
			statusBarPanel2.setLayout(new BorderLayout());
			
			tbUsuario = new TbUsuario().getInstance(true);
			JLabel lblTipoSistema = new JLabel("Sistema: "+tbUsuario.getNivelTipo());
			lblTipoSistema.setFont(new Font("Dialog", Font.PLAIN, 12));
			
			
			statusBarPanel2.add(lblTipoSistema);
		}
		return statusBarPanel2;
	}
	
	
	JLabel lblHora;
	private StatusBarPanel getStatusBarPanel3() {
		
		if (statusBarPanel3==null) {
			statusBarPanel3=new StatusBarPanel();
			statusBarPanel3.setPanelWidth(10);
			statusBarPanel3.setWidthFixed(true);
	
			statusBarPanel3.setLayout(new BorderLayout());
			
			Date hoje =  new Date();
			
			lblData = new JLabel("Data: "+ new ConversorData().ConverterDateSQLEmString(hoje));
			lblData.setFont(new Font("Dialog", Font.PLAIN, 12));
				
			statusBarPanel3.add(lblData);
		}
		
		return statusBarPanel3;
		
	}
	
	JLabel lblData;

	private StatusBarPanel getStatusBarPanel4() {

		if (statusBarPanel4 == null) {
			statusBarPanel4 = new StatusBarPanel();
			statusBarPanel4.setPanelWidth(10);
			statusBarPanel4.setWidthFixed(true);

			statusBarPanel4.setLayout(new BorderLayout());
			lblHora = new JLabel("Hora: ");
			lblHora.setFont(new Font("Dialog", Font.PLAIN, 12));

			lblHora = new JLabel(); // Seu Label
			Timer timer = new Timer(); // O timer para temporizar
			// O timer task contém o que o timer irá fazer
			TimerTask task = new TimerTask() {
				public void run() {
					// Setando o Texto o seu Label na tarefa da timer task
					lblHora.setText("Hora: " + new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
				}
				
			};
			// Setando a chamada da task para o timer
			// ainda enviamos qual a timertask e o tempo para cada chamada
			// em milissegundos
			
			timer.schedule(task, 1000, 1000);

			statusBarPanel4.add(lblHora);
		}

		return statusBarPanel4;
	}
	

}
