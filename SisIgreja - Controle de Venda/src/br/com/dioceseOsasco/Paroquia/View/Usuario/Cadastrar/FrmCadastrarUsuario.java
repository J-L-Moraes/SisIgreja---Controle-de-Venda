package br.com.dioceseOsasco.Paroquia.View.Usuario.Cadastrar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmCadastrarUsuario extends JDialog {
	
	private JLabel lblNome;
	private JLabel lblTipoDeUsurio;
	private JLabel lblUsurio;
	private JLabel lblSenha;
	private JLabel lblDataDeNascimento;
	JButton btnCadastrar;
	JButton btnLimparCampos;
	JButton btnCancelar;
	JTextField txtNome;
	JTextField txtUsuario;
	JComboBox<?> cmBxTipoUsuario;
	JPasswordField txtSenha;
	JCalendar cmBxDataNascimento;
	private JSeparator separator;
	private JSeparator separatorPersonalizado;
	
	JCheckBox chckbxAbrirCaixa;
	JCheckBox chckbxAnalisarPedido;
	JCheckBox chckbxCadastrarProduto;
	JCheckBox chckbxAlterarProduto;
	JCheckBox chckbxProcurarProduto;
	JCheckBox chckbxExcluirProduto;
	JCheckBox chckbxCadastrarEvento;
	JCheckBox chckbxAlterarEvento;
	JCheckBox chckbxPesquisarEvento;
	JCheckBox chckbxExcluirEvento;
	JCheckBox chckbxCadastrarUsuario;
	JCheckBox chckbxAlterarUsuario;
	JCheckBox chckbxProcurarUsuario;
	JCheckBox chckbxExcluirUsurio;
	JCheckBox chckbxRedefinirSenhaoutros;
	JCheckBox chckbxDioceseParoquia;
	JCheckBox chckbxCadastrarComunidade;
	JCheckBox chckbxAtualizarComunidade;
	JCheckBox chckbxProcurarComunidade;
	JCheckBox chckbxExcluirComunidade;
	JCheckBox chckbxRelatorioDeVenda;
	JCheckBox chckbxRelatorioDeVendaDetalhado;
	JCheckBox chckbxRelatorioDeProduto;
	
	/**
	 * Método Contrutor
	 */
	public FrmCadastrarUsuario(){
		Interface();
		PersonalizacaoNormal();
		new CadastrarUsuarioListener(this);
	}

	/**
	 * Interface
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void Interface() {
		
		setModal(true);
		setResizable(false);
		
		getContentPane().setLayout(null);
		setBackground(Color.WHITE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastrarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisiIgreja - Cadastrar usu\u00E1rio");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		lblNome = new JLabel("Nome :");
		lblNome.setBounds(95, 20, 46, 14);
		getContentPane().add(lblNome);
		
		lblTipoDeUsurio = new JLabel("Tipo de sistema :");
		lblTipoDeUsurio.setBounds(320, 150, 100, 14);
		getContentPane().add(lblTipoDeUsurio);
		
		lblUsurio = new JLabel("Usu\u00E1rio :");
		lblUsurio.setBounds(95, 61, 46, 14);
		getContentPane().add(lblUsurio);
		
		lblSenha = new JLabel("Senha :");
		lblSenha.setBounds(95, 102, 46, 14);
		getContentPane().add(lblSenha);
		
		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(22, 150, 119, 14);
		getContentPane().add(lblDataDeNascimento);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setIcon(new ImageIcon(FrmCadastrarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Adicionar.png")));
		getContentPane().add(btnCadastrar);
		getRootPane().setDefaultButton(btnCadastrar); //Botão Padrão
		
		btnLimparCampos = new JButton(" Limpar Campos");
		btnLimparCampos.setIcon(new ImageIcon(FrmCadastrarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Corrigir/Borracha(24x24).png")));
		getContentPane().add(btnLimparCampos);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCadastrarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		getContentPane().add(btnCancelar);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(160, 11, 400, 30);
		txtNome.setDocument(new ControlarDigitosTeclado(60));
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(160, 52, 400, 30);
		txtUsuario.setDocument(new ControlarDigitosTeclado(30));
		getContentPane().add(txtUsuario);
		
		cmBxTipoUsuario = new JComboBox();
		cmBxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Gerente", "Vendedor", "Personalizado"}));
		cmBxTipoUsuario.setBounds(420, 142, 140, 30);
		cmBxTipoUsuario.setSelectedItem(null);
		getContentPane().add(cmBxTipoUsuario);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setBounds(160, 93, 400, 30);
		txtSenha.setDocument(new ControlarDigitosTeclado(30));
		getContentPane().add(txtSenha);
		
		cmBxDataNascimento = new JCalendar(false);
		cmBxDataNascimento.setBounds(160, 142, 140, 30);
		getContentPane().add(cmBxDataNascimento);
		
		separator = new JSeparator();
		separator.setBounds(22, 183, 597, 14);
		getContentPane().add(separator);
		
		separatorPersonalizado = new JSeparator();
		separatorPersonalizado.setBounds(22, 380, 713, 2);
		getContentPane().add(separatorPersonalizado);
		
		chckbxAbrirCaixa = new JCheckBox("Abrir Caixa");
		chckbxAbrirCaixa.setBackground(Color.WHITE);
		chckbxAbrirCaixa.setBounds(2, 193, 100, 23);
		getContentPane().add(chckbxAbrirCaixa);
		
		chckbxAnalisarPedido = new JCheckBox("Analisar Pedido");
		chckbxAnalisarPedido.setBackground(Color.WHITE);
		chckbxAnalisarPedido.setBounds(2, 219, 115, 23);
		getContentPane().add(chckbxAnalisarPedido);
		
		chckbxCadastrarProduto = new JCheckBox("Cadastrar Produto");
		chckbxCadastrarProduto.setBackground(Color.WHITE);
		chckbxCadastrarProduto.setBounds(110, 193, 124, 23);
		getContentPane().add(chckbxCadastrarProduto);
		
		chckbxAlterarProduto = new JCheckBox("Alterar Produto");
		chckbxAlterarProduto.setBackground(Color.WHITE);
		chckbxAlterarProduto.setBounds(110, 219, 115, 23);
		getContentPane().add(chckbxAlterarProduto);
		
		chckbxProcurarProduto = new JCheckBox("Procurar Produto");
		chckbxProcurarProduto.setBackground(Color.WHITE);
		chckbxProcurarProduto.setBounds(110, 245, 124, 23);
		getContentPane().add(chckbxProcurarProduto);
		
		chckbxExcluirProduto = new JCheckBox("Excluir Produto");
		chckbxExcluirProduto.setBackground(Color.WHITE);
		chckbxExcluirProduto.setBounds(110, 270, 115, 23);
		getContentPane().add(chckbxExcluirProduto);
		
		chckbxCadastrarEvento = new JCheckBox("Cadastrar Evento");
		chckbxCadastrarEvento.setBackground(Color.WHITE);
		chckbxCadastrarEvento.setBounds(234, 193, 119, 23);
		getContentPane().add(chckbxCadastrarEvento);
		
		chckbxAlterarEvento = new JCheckBox("Alterar Evento");
		chckbxAlterarEvento.setBackground(Color.WHITE);
		chckbxAlterarEvento.setBounds(233, 219, 116, 23);
		getContentPane().add(chckbxAlterarEvento);
		
		chckbxPesquisarEvento = new JCheckBox("Pesquisar Evento");
		chckbxPesquisarEvento.setBackground(Color.WHITE);
		chckbxPesquisarEvento.setBounds(234, 245, 129, 23);
		getContentPane().add(chckbxPesquisarEvento);
		
		chckbxExcluirEvento = new JCheckBox("Excluir Evento");
		chckbxExcluirEvento.setBackground(Color.WHITE);
		chckbxExcluirEvento.setBounds(234, 270, 115, 23);
		getContentPane().add(chckbxExcluirEvento);
		
		chckbxCadastrarUsuario = new JCheckBox("Cadastrar Usu\u00E1rio");
		chckbxCadastrarUsuario.setBackground(Color.WHITE);
		chckbxCadastrarUsuario.setBounds(359, 193, 129, 23);
		getContentPane().add(chckbxCadastrarUsuario);
		
		chckbxAlterarUsuario = new JCheckBox("Alterar Usu\u00E1rio");
		chckbxAlterarUsuario.setBackground(Color.WHITE);
		chckbxAlterarUsuario.setBounds(359, 219, 129, 23);
		getContentPane().add(chckbxAlterarUsuario);
		
		chckbxProcurarUsuario = new JCheckBox("Procurar Usu\u00E1rio");
		chckbxProcurarUsuario.setBackground(Color.WHITE);
		chckbxProcurarUsuario.setBounds(359, 245, 129, 23);
		getContentPane().add(chckbxProcurarUsuario);
		
		chckbxExcluirUsurio = new JCheckBox("Excluir Usu\u00E1rio");
		chckbxExcluirUsurio.setBackground(Color.WHITE);
		chckbxExcluirUsurio.setBounds(359, 270, 129, 23);
		getContentPane().add(chckbxExcluirUsurio);
		
		chckbxRedefinirSenhaoutros = new JCheckBox("Redefinir Senha (Outros usu\u00E1rios)");
		chckbxRedefinirSenhaoutros.setBackground(Color.WHITE);
		chckbxRedefinirSenhaoutros.setBounds(359, 296, 210, 23);
		getContentPane().add(chckbxRedefinirSenhaoutros);
		
		chckbxDioceseParoquia = new JCheckBox("Alterar ou Incluir Diocese | Par\u00F3quia");
		chckbxDioceseParoquia.setBackground(Color.WHITE);
		chckbxDioceseParoquia.setBounds(565, 193, 217, 23);
		getContentPane().add(chckbxDioceseParoquia);
		
		chckbxCadastrarComunidade = new JCheckBox("Cadastrar Comunidade");
		chckbxCadastrarComunidade.setBackground(Color.WHITE);
		chckbxCadastrarComunidade.setBounds(565, 219, 170, 23);
		getContentPane().add(chckbxCadastrarComunidade);
		
		chckbxAtualizarComunidade = new JCheckBox("Atualizar Comunidade");
		chckbxAtualizarComunidade.setBackground(Color.WHITE);
		chckbxAtualizarComunidade.setBounds(565, 245, 170, 23);
		getContentPane().add(chckbxAtualizarComunidade);
		
		chckbxProcurarComunidade = new JCheckBox("Procurar Comunidade");
		chckbxProcurarComunidade.setBackground(Color.WHITE);
		chckbxProcurarComunidade.setBounds(565, 270, 170, 23);
		getContentPane().add(chckbxProcurarComunidade);
		
		chckbxExcluirComunidade = new JCheckBox("Excluir Comunidade");
		chckbxExcluirComunidade.setBackground(Color.WHITE);
		chckbxExcluirComunidade.setBounds(565, 296, 170, 23);
		getContentPane().add(chckbxExcluirComunidade);
		
		chckbxRelatorioDeVenda = new JCheckBox("Relat\u00F3rio de Venda Simples");
		chckbxRelatorioDeVenda.setBackground(Color.WHITE);
		chckbxRelatorioDeVenda.setBounds(130, 340, 176, 23);
		getContentPane().add(chckbxRelatorioDeVenda);
		
		chckbxRelatorioDeVendaDetalhado = new JCheckBox("Relat\u00F3rio de Venda Detalhado");
		chckbxRelatorioDeVendaDetalhado.setBackground(Color.WHITE);
		chckbxRelatorioDeVendaDetalhado.setBounds(310, 340, 195, 23);
		getContentPane().add(chckbxRelatorioDeVendaDetalhado);
		
		chckbxRelatorioDeProduto = new JCheckBox("Relat\u00F3rio de Produto");
		chckbxRelatorioDeProduto.setBackground(Color.WHITE);
		chckbxRelatorioDeProduto.setBounds(507, 340, 153, 23);
		getContentPane().add(chckbxRelatorioDeProduto);
	}
	
	/**
	 * Centraliza o Form
	 * @param width
	 * @param height
	 */
	void CentralizarTela(int width, int height){
		setSize(width, height);
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Form Normal, sem os checkBox da Personalização
	 */
	void PersonalizacaoNormal(){
		ocultarExibirJCheckBox(false);
		btnCancelar.setBounds(460, 193, 135, 38);
		btnCadastrar.setBounds(170,193,135,38);
		btnLimparCampos.setBounds(315, 193, 135, 38);
		separator.setBounds(22, 183, 597, 14);
		CentralizarTela(635,276);
	}
	
	/**
	 * Form com os checkbox da Personalização
	 */
	void PersonalizacaoPeronalizada(){
		ocultarExibirJCheckBox(true);
		separator.setBounds(22, 183, 713, 14);
		btnCadastrar.setBounds(312, 393, 135, 38);
		btnLimparCampos.setBounds(455, 393, 135, 38);
		btnCancelar.setBounds(600, 393, 135, 38);
		CentralizarTela(794,479);
	}
	
	/**
	 * Oculta ou exibe os checkBox em Tela
	 * @param estado
	 */
	void ocultarExibirJCheckBox(boolean estado){
		Component[] componentesJPanel = getContentPane().getComponents();
		for (int a = 0; a < componentesJPanel.length; a++) {
			if (componentesJPanel[a] instanceof JCheckBox) { 
				JCheckBox checkbox = (JCheckBox) componentesJPanel[a]; 
				checkbox.setVisible(estado);
			}
		}
	}

}
