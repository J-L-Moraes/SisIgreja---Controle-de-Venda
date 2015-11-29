package br.com.dioceseOsasco.Paroquia.View.Usuario.Alterar;

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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmAlterarUsuario extends JDialog {
	
	private JLabel lblNome;
	private JLabel lblTipoDeUsurio;
	private JLabel lblUsurio;
	private JLabel lblSenha;
	private JLabel lblDataDeNascimento;

	private JLabel lblUsurio_1;
	JTextField txtUsuario;
	private JPanel panel;
	private JLabel lblUsurio_2;
	
	JButton btnAlterar;
	JButton btnCancelar;
	JButton btnPesquisarUsuario;
	JButton btnLocalizarUsuario;
	
	JTextField txtNome;
	JTextField txtPesquisarUsuario;
	JComboBox<?> cmBxTipoUsuario;
	JComboBox<?> cmBxAtivo;
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
	
	
	AlterarUsuarioListener alterarUsuarioListener;
	/**
	 * Método Contrutor
	 */
	public FrmAlterarUsuario(){
		Interface();
		PersonalizacaoNormal();
		alterarUsuarioListener = new AlterarUsuarioListener(this);
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
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Atualizar usu\u00E1rio");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		lblNome = new JLabel("Nome :");
		lblNome.setBounds(22, 90, 50, 14);
		getContentPane().add(lblNome);
		
		lblTipoDeUsurio = new JLabel("Tipo de sistema :");
		lblTipoDeUsurio.setBounds(271, 174, 94, 14);
		getContentPane().add(lblTipoDeUsurio);
		
		lblSenha = new JLabel("Senha :");
		lblSenha.setBounds(388, 131, 50, 14);
		getContentPane().add(lblSenha);
		
		lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(22, 175, 116, 14);
		getContentPane().add(lblDataDeNascimento);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Checar.png")));
		btnAlterar.setBounds(430, 225, 135, 38);
		getContentPane().add(btnAlterar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(600, 423, 135, 38);
		getContentPane().add(btnCancelar);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(85, 81, 400, 30);
		txtNome.setDocument(new ControlarDigitosTeclado(60));
		txtNome.setColumns(10);
		getContentPane().add(txtNome);
		
		cmBxTipoUsuario = new JComboBox();
		cmBxTipoUsuario.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Gerente", "Vendedor", "Personalizado"}));
		cmBxTipoUsuario.setBounds(367, 168, 140, 30);
		cmBxTipoUsuario.setSelectedItem(null);
		getContentPane().add(cmBxTipoUsuario);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setBounds(448, 122, 203, 30);
		txtSenha.setDocument(new ControlarDigitosTeclado(30));
		getContentPane().add(txtSenha);
		
		cmBxDataNascimento = new JCalendar(false);
		cmBxDataNascimento.setBounds(160, 167, 94, 30);
		getContentPane().add(cmBxDataNascimento);
		
		separator = new JSeparator();
		separator.setBounds(22, 212, 713, 14);
		getContentPane().add(separator);
		
		separatorPersonalizado = new JSeparator();
		separatorPersonalizado.setBounds(22, 410, 713, 2);
		getContentPane().add(separatorPersonalizado);
		
		chckbxAbrirCaixa = new JCheckBox("Abrir Caixa");
		chckbxAbrirCaixa.setBackground(Color.WHITE);
		chckbxAbrirCaixa.setBounds(1, 233, 105, 23);
		getContentPane().add(chckbxAbrirCaixa);
		
		chckbxAnalisarPedido = new JCheckBox("Analisar Pedido");
		chckbxAnalisarPedido.setBackground(Color.WHITE);
		chckbxAnalisarPedido.setBounds(1, 259, 116, 23);
		getContentPane().add(chckbxAnalisarPedido);
		
		chckbxCadastrarProduto = new JCheckBox("Cadastrar Produto");
		chckbxCadastrarProduto.setBackground(Color.WHITE);
		chckbxCadastrarProduto.setBounds(108, 233, 128, 23);
		getContentPane().add(chckbxCadastrarProduto);
		
		chckbxAlterarProduto = new JCheckBox("Alterar Produto");
		chckbxAlterarProduto.setBackground(Color.WHITE);
		chckbxAlterarProduto.setBounds(108, 259, 128, 23);
		getContentPane().add(chckbxAlterarProduto);
		
		chckbxProcurarProduto = new JCheckBox("Procurar Produto");
		chckbxProcurarProduto.setBackground(Color.WHITE);
		chckbxProcurarProduto.setBounds(108, 285, 128, 23);
		getContentPane().add(chckbxProcurarProduto);
		
		chckbxExcluirProduto = new JCheckBox("Excluir Produto");
		chckbxExcluirProduto.setBackground(Color.WHITE);
		chckbxExcluirProduto.setBounds(108, 310, 128, 23);
		getContentPane().add(chckbxExcluirProduto);
		
		chckbxCadastrarEvento = new JCheckBox("Cadastrar Evento");
		chckbxCadastrarEvento.setBackground(Color.WHITE);
		chckbxCadastrarEvento.setBounds(238, 233, 127, 23);
		getContentPane().add(chckbxCadastrarEvento);
		
		chckbxAlterarEvento = new JCheckBox("Alterar Evento");
		chckbxAlterarEvento.setBackground(Color.WHITE);
		chckbxAlterarEvento.setBounds(237, 259, 128, 23);
		getContentPane().add(chckbxAlterarEvento);
		
		chckbxPesquisarEvento = new JCheckBox("Pesquisar Evento");
		chckbxPesquisarEvento.setBackground(Color.WHITE);
		chckbxPesquisarEvento.setBounds(238, 285, 127, 23);
		getContentPane().add(chckbxPesquisarEvento);
		
		chckbxExcluirEvento = new JCheckBox("Excluir Evento");
		chckbxExcluirEvento.setBackground(Color.WHITE);
		chckbxExcluirEvento.setBounds(238, 310, 127, 23);
		getContentPane().add(chckbxExcluirEvento);
		
		chckbxCadastrarUsuario = new JCheckBox("Cadastrar Usu\u00E1rio");
		chckbxCadastrarUsuario.setBackground(Color.WHITE);
		chckbxCadastrarUsuario.setBounds(369, 233, 157, 23);
		getContentPane().add(chckbxCadastrarUsuario);
		
		chckbxAlterarUsuario = new JCheckBox("Alterar Usu\u00E1rio");
		chckbxAlterarUsuario.setBackground(Color.WHITE);
		chckbxAlterarUsuario.setBounds(369, 259, 138, 23);
		getContentPane().add(chckbxAlterarUsuario);
		
		chckbxProcurarUsuario = new JCheckBox("Procurar Usu\u00E1rio");
		chckbxProcurarUsuario.setBackground(Color.WHITE);
		chckbxProcurarUsuario.setBounds(369, 285, 138, 23);
		getContentPane().add(chckbxProcurarUsuario);
		
		chckbxExcluirUsurio = new JCheckBox("Excluir Usu\u00E1rio");
		chckbxExcluirUsurio.setBackground(Color.WHITE);
		chckbxExcluirUsurio.setBounds(369, 310, 138, 23);
		getContentPane().add(chckbxExcluirUsurio);
		
		chckbxRedefinirSenhaoutros = new JCheckBox("Redefinir Senha (Outros usu\u00E1rios)");
		chckbxRedefinirSenhaoutros.setBackground(Color.WHITE);
		chckbxRedefinirSenhaoutros.setBounds(369, 336, 211, 23);
		getContentPane().add(chckbxRedefinirSenhaoutros);
		
		chckbxDioceseParoquia = new JCheckBox("Alterar ou Incluir Diocese | Par\u00F3quia");
		chckbxDioceseParoquia.setBackground(Color.WHITE);
		chckbxDioceseParoquia.setBounds(573, 233, 226, 23);
		getContentPane().add(chckbxDioceseParoquia);
		
		chckbxCadastrarComunidade = new JCheckBox("Cadastrar Comunidade");
		chckbxCadastrarComunidade.setBackground(Color.WHITE);
		chckbxCadastrarComunidade.setBounds(575, 259, 157, 23);
		getContentPane().add(chckbxCadastrarComunidade);
		
		chckbxAtualizarComunidade = new JCheckBox("Atualizar Comunidade");
		chckbxAtualizarComunidade.setBackground(Color.WHITE);
		chckbxAtualizarComunidade.setBounds(575, 285, 157, 23);
		getContentPane().add(chckbxAtualizarComunidade);
		
		chckbxProcurarComunidade = new JCheckBox("Procurar Comunidade");
		chckbxProcurarComunidade.setBackground(Color.WHITE);
		chckbxProcurarComunidade.setBounds(575, 310, 145, 23);
		getContentPane().add(chckbxProcurarComunidade);
		
		chckbxExcluirComunidade = new JCheckBox("Excluir Comunidade");
		chckbxExcluirComunidade.setBackground(Color.WHITE);
		chckbxExcluirComunidade.setBounds(575, 336, 157, 23);
		getContentPane().add(chckbxExcluirComunidade);
		
		chckbxRelatorioDeVenda = new JCheckBox("Relat\u00F3rio de Venda Simples");
		chckbxRelatorioDeVenda.setBackground(Color.WHITE);
		chckbxRelatorioDeVenda.setBounds(130, 380, 176, 23);
		getContentPane().add(chckbxRelatorioDeVenda);
		
		chckbxRelatorioDeVendaDetalhado = new JCheckBox("Relat\u00F3rio de Venda Detalhado");
		chckbxRelatorioDeVendaDetalhado.setBackground(Color.WHITE);
		chckbxRelatorioDeVendaDetalhado.setBounds(310, 380, 197, 23);
		getContentPane().add(chckbxRelatorioDeVendaDetalhado);
		
		chckbxRelatorioDeProduto = new JCheckBox("Relat\u00F3rio de Produto");
		chckbxRelatorioDeProduto.setBackground(Color.WHITE);
		chckbxRelatorioDeProduto.setBounds(511, 380, 140, 23);
		getContentPane().add(chckbxRelatorioDeProduto);
		
		lblUsurio_1 = new JLabel("Usu\u00E1rio:");
		lblUsurio_1.setBounds(22, 131, 62, 14);
		getContentPane().add(lblUsurio_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(85, 122, 260, 30);
		txtUsuario.setDocument(new ControlarDigitosTeclado(30));
		txtUsuario.setColumns(10);
		getContentPane().add(txtUsuario);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 15, 700, 55);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblUsurio = new JLabel("Pesquise pelo usu\u00E1rio :");
		lblUsurio.setBounds(24, 20, 126, 14);
		panel.add(lblUsurio);
		
		txtPesquisarUsuario = new JTextField();
		txtPesquisarUsuario.setBounds(150, 11, 289, 30);
		panel.add(txtPesquisarUsuario);
		txtPesquisarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPesquisarUsuario.setColumns(10);
		
		btnPesquisarUsuario = new JButton("Pesquisar Usu\u00E1rio");
		btnPesquisarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 0));
		btnPesquisarUsuario.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		btnPesquisarUsuario.setBounds(470, 11, 41, 33);
		panel.add(btnPesquisarUsuario);

		btnLocalizarUsuario = new JButton("Localizar Usu\u00E1rio");
		btnLocalizarUsuario.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Usuario/Pesquisar.png")));
		btnLocalizarUsuario.setBounds(521, 11, 156, 30);
		panel.add(btnLocalizarUsuario);
		
		lblUsurio_2 = new JLabel("Usu\u00E1rio ?");
		lblUsurio_2.setBounds(517, 174, 64, 14);
		getContentPane().add(lblUsurio_2);
		
		cmBxAtivo = new JComboBox();
		cmBxAtivo.setMaximumRowCount(2);
		cmBxAtivo.setModel(new DefaultComboBoxModel(new String[] {"Ativo", "Inativo"}));
		cmBxAtivo.setBounds(591, 168, 100, 30);
		cmBxAtivo.setSelectedItem(null);
		getContentPane().add(cmBxAtivo);
		
		desabilitarHabilitarComponentes(false);
		getRootPane().setDefaultButton(btnPesquisarUsuario);// Botão Padrão da Tecla Enter
		btnPesquisarUsuario.setText("Pesquisar Usu\u00E1rio");
		btnPesquisarUsuario.setToolTipText("Pesquise o usuário no sistema para poder edita-lo.");
		getRootPane().setDefaultButton(btnPesquisarUsuario); //Botão Padrão
		

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
		btnCancelar.setBounds(575, 225, 135, 38);
		btnAlterar.setBounds(430,225,135,38);
		separator.setBounds(22, 212, 713, 14);
		CentralizarTela(759,305);
	}
	
	/**
	 * Form com os checkbox da Personalização
	 */
	void PersonalizacaoPeronalizada(){
		ocultarExibirJCheckBox(true);
		separator.setBounds(22, 212, 713, 14);
		btnAlterar.setBounds(445, 423, 135, 38);
		btnCancelar.setBounds(600, 423, 135, 38);
		CentralizarTela(794,500);
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
	
	/**
	 * Desabilita ou desabilita os componentes em tela de acordo com o estado passado
	 * @param estado
	 */
	void desabilitarHabilitarComponentes(boolean estado){
		Component[] componentesJPanel = getContentPane().getComponents();
		for (int a = 0; a < componentesJPanel.length; a++) {
			if (componentesJPanel[a] instanceof JCheckBox) { 
				JCheckBox checkbox = (JCheckBox) componentesJPanel[a]; 
				checkbox.setEnabled(estado);
			}
			else if (componentesJPanel[a] instanceof JTextField) { 
				JTextField jTextField = (JTextField) componentesJPanel[a]; 
				jTextField.setEnabled(estado);
			}
			else if (componentesJPanel[a] instanceof JComboBox) { 
				JComboBox<?> jComboBox = (JComboBox<?>) componentesJPanel[a]; 
				jComboBox.setEnabled(estado);
			}
			else if (componentesJPanel[a] instanceof JPasswordField) { 
				JPasswordField jPasswordField = (JPasswordField) componentesJPanel[a]; 
				jPasswordField.setEnabled(estado);
			}
			else if (componentesJPanel[a] instanceof JCalendar) { 
				JCalendar jCalendar = (JCalendar) componentesJPanel[a]; 
				jCalendar.setEnabled(estado);
			}
			else if (componentesJPanel[a] instanceof JButton) { 
				JButton jButton = (JButton) componentesJPanel[a]; 
				jButton.setEnabled(estado);
			}
		}
		txtPesquisarUsuario.setEnabled(!estado);

		btnPesquisarUsuario.setEnabled(true);
		if (estado) {
			btnPesquisarUsuario.setText("Cancelar Pesquisa");
			getRootPane().setDefaultButton(btnAlterar); //Botão Padrão
			btnPesquisarUsuario.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
			btnPesquisarUsuario.setToolTipText("Cancele a alteração do usuário atual e habilite o campo para realizar uma nova Pesquisa.");
		}else{
			txtPesquisarUsuario.grabFocus();
			btnPesquisarUsuario.setText("Pesquisar Usu\u00E1rio");
			getRootPane().setDefaultButton(btnPesquisarUsuario); //Botão Padrão
			btnPesquisarUsuario.setIcon(new ImageIcon(FrmAlterarUsuario.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
			btnPesquisarUsuario.setToolTipText("Pesquise o usuário no sistema para poder edita-lo.");
		}
	}

	/**
	 * Limpa os campos preenchidos após pesquisa
	 */
	void LimparCampos(){
		Component[] componentesJPanel = getContentPane().getComponents();
		for (int a = 0; a < componentesJPanel.length; a++) {
			if (componentesJPanel[a] instanceof JCheckBox) { 
				JCheckBox checkbox = (JCheckBox) componentesJPanel[a]; 
				checkbox.setSelected(false);
			}
			else if (componentesJPanel[a] instanceof JTextField) { 
				JTextField jTextField = (JTextField) componentesJPanel[a]; 
				jTextField.setText(null);
			}
			else if (componentesJPanel[a] instanceof JComboBox) { 
				JComboBox<?> jComboBox = (JComboBox<?>) componentesJPanel[a]; 
				jComboBox.setSelectedItem(null);
			}
			else if (componentesJPanel[a] instanceof JPasswordField) { 
				JPasswordField jPasswordField = (JPasswordField) componentesJPanel[a]; 
				jPasswordField.setText(null);
			}
			else if (componentesJPanel[a] instanceof JCalendar) { 
				JCalendar jCalendar = (JCalendar) componentesJPanel[a]; 
				jCalendar.setSelectedItem(null);
			}
		}
	}

	public void setEvento(String usuario) {
		txtPesquisarUsuario.setText(usuario);
		alterarUsuarioListener.PesquisarUsuario(usuario);
	}
}
