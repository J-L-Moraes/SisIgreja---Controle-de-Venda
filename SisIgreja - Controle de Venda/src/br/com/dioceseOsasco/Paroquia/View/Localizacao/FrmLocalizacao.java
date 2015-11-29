package br.com.dioceseOsasco.Paroquia.View.Localizacao;

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

@SuppressWarnings("serial")
public class FrmLocalizacao extends JDialog {
	
	private JLabel lblNomeDaDiocese;
	private JLabel lblNomeDaParoquia;
	JTextField txtDiocese;
	JTextField txtParoquia;
	JButton btnCadastrarAlterar;
	JButton btnCancelar;
	
	public FrmLocalizacao(){
		Interface();
		new LocalizacaoListener(this);
		ConsultarDioceseParoquia();
	}

	private void Interface() {
		
		setTitle("Cadastrar a Diocese e a Par\u00F3quia");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLocalizacao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setSize( 486, 185);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		lblNomeDaDiocese = new JLabel("Nome da Diocese :");
		lblNomeDaDiocese.setBounds(21, 30, 110, 21);
		getContentPane().add(lblNomeDaDiocese);
		
		lblNomeDaParoquia = new JLabel("Nome da Par\u00F3quia :");
		lblNomeDaParoquia.setBounds(21, 74, 110, 14);
		getContentPane().add(lblNomeDaParoquia);
		
		txtDiocese = new JTextField();
		txtDiocese.setBounds(141, 25, 309, 30);
		txtDiocese.setDocument(new ControlarDigitosTeclado(60));
		txtDiocese.setColumns(10);
		getContentPane().add(txtDiocese);
		
		txtParoquia = new JTextField();
		txtParoquia.setBounds(141, 66, 309, 30);
		txtParoquia.setDocument(new ControlarDigitosTeclado(60));
		txtParoquia.setColumns(10);
		getContentPane().add(txtParoquia);
		
		btnCadastrarAlterar = new JButton("Cadastrar");
		btnCadastrarAlterar.setIcon(new ImageIcon(FrmLocalizacao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Local/Localizacao(24x24).png")));
		btnCadastrarAlterar.setBounds(193, 107, 129, 35);
		getContentPane().add(btnCadastrarAlterar);
		getRootPane().setDefaultButton(btnCadastrarAlterar);// Botão Padrão da Tecla Enter
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmLocalizacao.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(332, 107, 118, 35);
		getContentPane().add(btnCancelar);
		
		
	}
	
	void ConsultarDioceseParoquia(){
		
		TbLocalizacao tbLocalizacao = new TbLocalizacao();
		LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
		
		tbLocalizacao = localizacaoDAO.find(1);	
		if (!(tbLocalizacao == null)) {
			txtDiocese.setText(tbLocalizacao.getNomeDiocese());
			txtParoquia.setText(tbLocalizacao.getNomeParoquia());
			setTitle("SisIgreja - Atualizar Diocese e Par\u00F3quia");
		}
	}

}
