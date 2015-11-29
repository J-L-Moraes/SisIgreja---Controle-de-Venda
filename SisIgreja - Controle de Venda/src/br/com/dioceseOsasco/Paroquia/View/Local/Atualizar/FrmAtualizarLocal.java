package br.com.dioceseOsasco.Paroquia.View.Local.Atualizar;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Controller.LocalizacaoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.Model.TbLocalizacao;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;
import br.com.dioceseOsasco.Paroquia.View.Localizacao.FrmLocalizacao;

@SuppressWarnings("serial")
public class FrmAtualizarLocal extends JDialog {
	
	private JLabel lblNomeDaDiocese;
	private JLabel lblNomeDeComunidade;
	private JLabel lblNomeDaParquia;
	JTextField txtDiocese;
	JTextField txtParoquia;
	JTextField txtComunidade;
	JButton btnAtualizarComunidade;
	JButton btnCancelar;
	private JLabel lblComunidadeASer;
	JComboBox<TbLocal> cmBxComunidadeCadastrada;
	
	public FrmAtualizarLocal(){
		Interface();
		new AtualizarLocalListener(this);
		ConsultarDioceseParoquia();
	}

	private void Interface() {
		
		setTitle("SisIgreja - Atualizar Comunidade cadastrada");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAtualizarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));

		setSize(530, 260);
		this.setLocationRelativeTo(null);
		
		setResizable(false);
		setModal(true);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		lblNomeDaDiocese = new JLabel("Nome da Diocese :");
		lblNomeDaDiocese.setBounds(21, 30, 110, 21);
		getContentPane().add(lblNomeDaDiocese);
		
		lblNomeDeComunidade = new JLabel("Nome de Comunidade :");
		lblNomeDeComunidade.setBounds(21, 156, 139, 14);
		getContentPane().add(lblNomeDeComunidade);
		
		lblNomeDaParquia = new JLabel("Nome da Par\u00F3quia :");
		lblNomeDaParquia.setBounds(21, 74, 110, 14);
		getContentPane().add(lblNomeDaParquia);
		
		txtDiocese = new JTextField();
		txtDiocese.setToolTipText("Para Editar este campo, por favor, abra o modulo \"Cadastrar ou Alterar Diocese | Par\u00F3quia\" da aba \"Localiza\u00E7\u00E3o\".");
		txtDiocese.setEditable(false);
		txtDiocese.setBounds(141, 25, 339, 30);
		getContentPane().add(txtDiocese);
		txtDiocese.setColumns(10);
		
		txtParoquia = new JTextField();
		txtParoquia.setToolTipText("Para Editar este campo, por favor, abra o modulo \"Cadastrar ou Alterar Diocese | Par\u00F3quia\" da aba \"Localiza\u00E7\u00E3o\".");
		txtParoquia.setEditable(false);
		txtParoquia.setBounds(141, 66, 339, 30);
		getContentPane().add(txtParoquia);
		txtParoquia.setColumns(10);
		
		txtComunidade = new JTextField();
		txtComunidade.setBounds(170, 148, 310, 30);
		txtComunidade.setDocument(new ControlarDigitosTeclado(60));
		txtComunidade.setColumns(10);
		getContentPane().add(txtComunidade);
		
		btnAtualizarComunidade = new JButton("Atualizar");
		btnAtualizarComunidade.setIcon(new ImageIcon(FrmAtualizarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/AtualizarComunidade(24x24).png")));
		btnAtualizarComunidade.setBounds(255, 189, 118, 30);
		getContentPane().add(btnAtualizarComunidade);
		getRootPane().setDefaultButton(btnAtualizarComunidade);// Botão Padrão da Tecla Enter
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAtualizarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(383, 189, 118, 30);
		getContentPane().add(btnCancelar);
		
		lblComunidadeASer = new JLabel("Comunidade a ser alterada:");
		lblComunidadeASer.setBounds(20, 118, 150, 14);
		getContentPane().add(lblComunidadeASer);
		
		cmBxComunidadeCadastrada = new JComboBox<TbLocal>();
		cmBxComunidadeCadastrada.setBounds(170, 110, 310, 30);
		getContentPane().add(cmBxComunidadeCadastrada);
		
		LocalDAO localDAO = new LocalDAO();
		List<TbLocal> tbLocal = localDAO.listarTodasComunidades();
		for (TbLocal tb_Local : tbLocal) {
			cmBxComunidadeCadastrada.addItem(tb_Local);
		}
		
		cmBxComunidadeCadastrada.setSelectedItem(null);
		
	}
	
	/**
	 * Realiza a Consulta da Diocese e Paróquia, caso não encontrada é direcionado para cadastrar a Diocese e Paróquia 
	 */
	void ConsultarDioceseParoquia(){
		
		TbLocalizacao tbLocalizacao = new TbLocalizacao();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		
		
		tbLocalizacao = localizacaoDAO.find(1);
		
		if (!(tbLocalizacao == null)) {
			txtDiocese.setText(tbLocalizacao.getNomeDiocese());
			txtParoquia.setText(tbLocalizacao.getNomeParoquia());
		}else{
			FrmLocalizacao frmLocalizacao = new FrmLocalizacao();
			frmLocalizacao.setVisible(true);
			while (frmLocalizacao.isVisible()) { }
			ConsultarDioceseParoquia();
		}
		
	}

}
