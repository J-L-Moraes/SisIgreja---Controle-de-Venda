package br.com.dioceseOsasco.Paroquia.View.Produto.Cadastrar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosTeclado;

@SuppressWarnings("serial")
public class FrmCadastrarProduto extends JDialog {
	
	private JPanel contentPane;
	private JLabel lblProduto;
	private JLabel lblPreco;
	JTextField txtProduto;
	JFormattedTextField txtPreco;
	JButton btnCadastrarProduto;
	JButton btnCancelar;
	
	
	public FrmCadastrarProduto() {
		Interface();
		new CadastrarListener(this);
	}
	
	

	/**
	 * Interface do Cadastrar produto
	 */
	private void Interface(){
		
		setTitle("SisIgreja - Cadastrar produtos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmCadastrarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setSize(464, 186);
		this.setLocationRelativeTo(null);

		setModal(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(24, 31, 46, 14);
		contentPane.add(lblProduto);
		
		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setBounds(24, 73, 36, 14);
		contentPane.add(lblPreco);
		
		txtProduto = new JTextField();
		txtProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtProduto.setBounds(80, 22, 336, 32);
		contentPane.add(txtProduto);
		txtProduto.setColumns(10);
		txtProduto.setDocument(new ControlarDigitosTeclado(50));
		
		txtPreco = new JFormattedTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPreco.setBounds(80, 63, 171, 32);
		txtPreco.setFormatterFactory(new DefaultFormatterFactory( new NumberFormatter(new DecimalFormat("###.00"))));
		contentPane.add(txtPreco);
		
		
		btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.setIcon(new ImageIcon(FrmCadastrarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/checar.png")));
		btnCadastrarProduto.setBounds(150, 111, 141, 32);
		getRootPane().setDefaultButton(btnCadastrarProduto);//Botão Padrão
		contentPane.add(btnCadastrarProduto);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmCadastrarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(301, 111, 132, 32);
		contentPane.add(btnCancelar);
	}
}
