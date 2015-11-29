package br.com.dioceseOsasco.Paroquia.View.Local.Excluir;

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
import br.com.dioceseOsasco.Paroquia.View.Localizacao.FrmLocalizacao;

@SuppressWarnings("serial")
public class FrmExcluirLocal extends JDialog {
	
	private JLabel lblNomeDaDiocese;
	private JLabel lblNomeDaParquia;
	JTextField txtDiocese;
	JTextField txtParoquia;
	JButton btnExcluirComunidade;
	JButton btnCancelar;
	private JLabel lblComunidade;
	JComboBox<TbLocal> cmBxComunidadeCadastrada;
	
	public FrmExcluirLocal(){
		Interface();
		new ExcluirLocalListener(this);
		ConsultarDioceseParoquia();
	}

	private void Interface() {
		
		setTitle("Excluir Comunidade cadastrada");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmExcluirLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setSize(520, 234);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		lblNomeDaDiocese = new JLabel("Nome da Diocese :");
		lblNomeDaDiocese.setBounds(10, 30, 121, 21);
		getContentPane().add(lblNomeDaDiocese);
		
		lblNomeDaParquia = new JLabel("Nome da Par\u00F3quia :");
		lblNomeDaParquia.setBounds(10, 74, 121, 14);
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
		
		btnExcluirComunidade = new JButton("Excluir");
		btnExcluirComunidade.setIcon(new ImageIcon(FrmExcluirLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/ExcluirComunidade(24x24).png")));
		btnExcluirComunidade.setBounds(215, 163, 118, 30);
		getContentPane().add(btnExcluirComunidade);
		getRootPane().setDefaultButton(btnExcluirComunidade);// Botão Padrão da Tecla Enter
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(343, 163, 118, 30);
		getContentPane().add(btnCancelar);
		
		lblComunidade = new JLabel("Comunidade a ser excluida:");
		lblComunidade.setBounds(10, 117, 148, 14);
		getContentPane().add(lblComunidade);
		
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
	 * Consultar a Diocese e Paróquia
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
