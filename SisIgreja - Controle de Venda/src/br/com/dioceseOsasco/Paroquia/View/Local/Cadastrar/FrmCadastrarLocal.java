package br.com.dioceseOsasco.Paroquia.View.Local.Cadastrar;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.com.dioceseOsasco.Paroquia.Controller.LocalizacaoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocalizacao;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;
import br.com.dioceseOsasco.Paroquia.View.Localizacao.FrmLocalizacao;

@SuppressWarnings("serial")
public class FrmCadastrarLocal extends JDialog {
	
	private JLabel lblNomeDaDiocese;
	private JLabel lblNomeDeComunidade;
	private JLabel lblNomeDaParquia;
	JTextField txtDiocese;
	JTextField txtParoquia;
	JTextField txtComunidade;
	JButton btnCadastrarComunidade;
	JButton btnCancelar;
	
	public FrmCadastrarLocal(){
		Interface();
		new CadastrarLocalListener(this);
		ConsultarDioceseParoquia();
	}

	private void Interface() {
		
		setTitle("SisIgreja - Cadastrar a Comunidade");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastrarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));

		setSize(486, 242);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		lblNomeDaDiocese = new JLabel("Nome da Diocese :");
		lblNomeDaDiocese.setBounds(10, 30, 121, 21);
		getContentPane().add(lblNomeDaDiocese);
		
		lblNomeDeComunidade = new JLabel("Nome de Comunidade :");
		lblNomeDeComunidade.setBounds(10, 115, 129, 14);
		getContentPane().add(lblNomeDeComunidade);
		
		lblNomeDaParquia = new JLabel("Nome da Par\u00F3quia :");
		lblNomeDaParquia.setBounds(10, 74, 121, 14);
		getContentPane().add(lblNomeDaParquia);
		
		txtDiocese = new JTextField();
		txtDiocese.setEditable(false);
		txtDiocese.setBounds(141, 25, 309, 30);
		txtDiocese.setToolTipText("Para Editar este campo, por favor, abra o modulo \"Cadastrar ou Alterar Diocese | Par\u00F3quia\" da aba \"Localiza\u00E7\u00E3o\".");
		getContentPane().add(txtDiocese);
		txtDiocese.setColumns(10);
		
		txtParoquia = new JTextField();
		txtParoquia.setEditable(false);
		txtParoquia.setBounds(141, 66, 309, 30);
		txtParoquia.setToolTipText("Para Editar este campo, por favor, abra o modulo \"Cadastrar ou Alterar Diocese | Par\u00F3quia\" da aba \"Localiza\u00E7\u00E3o\".");
		getContentPane().add(txtParoquia);
		txtParoquia.setColumns(10);
		
		txtComunidade = new JTextField();
		txtComunidade.setBounds(140, 107, 310, 30);
		txtComunidade.setDocument(new ControlarDigitosTeclado(60));
		getContentPane().add(txtComunidade);
		txtComunidade.setColumns(10);
		
		btnCadastrarComunidade = new JButton("Cadastrar");
		btnCadastrarComunidade.setIcon(new ImageIcon(FrmCadastrarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/AdicionarComunidade(24x24).png")));
		btnCadastrarComunidade.setBounds(204, 158, 118, 30);
		getContentPane().add(btnCadastrarComunidade);
		getRootPane().setDefaultButton(btnCadastrarComunidade);// Botão Padrão da Tecla Enter
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCadastrarLocal.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(332, 158, 118, 30);
		getContentPane().add(btnCancelar);
		
		
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
