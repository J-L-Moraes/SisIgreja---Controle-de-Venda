package br.com.dioceseOsasco.Paroquia.View.Produto.Consultar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

@SuppressWarnings("serial")
public class FrmConsultaProduto extends JDialog {

	
	JTable table;
	private JPanel panel;
	private JLabel lblProduto;
	JTextField txtProduto;
	JLabel lblPreco;
	JFormattedTextField txtPreco;
	JButton btnPesquisar;
	
	ProdutoDAO produtoDAO;
	List<TbProduto> tb_produto;
	JButton btnLimparCampos;


	public FrmConsultaProduto() {
		Interface();
		new ConsultaListener(this);
	}

	/**
	 * Interface
	 */
	void Interface(){
		
		setSize(783, 488);
		this.setLocationRelativeTo(null);

		
		setModal(true);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConsultaProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setTitle("SisIgreja - Consulta de produtos");
		
		
		produtoDAO = new ProdutoDAO();
		tb_produto = produtoDAO.findAllOrdeByDescricaoASC();
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setModel(new ProdutosTableModel(tb_produto));
		table.setBounds(375, 227, -351, -195);
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 148, 731, 291);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(60, 11, 638, 113);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(31, 22, 46, 14);
		panel.add(lblProduto);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(87, 15, 414, 28);
		panel.add(txtProduto);
		txtProduto.setColumns(10);
		
		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setBounds(31, 61, 46, 14);
		panel.add(lblPreco);
		
		txtPreco = new JFormattedTextField();
		txtPreco.setBounds(87, 54, 147, 28);
		txtPreco.setFormatterFactory(new DefaultFormatterFactory( new NumberFormatter(new DecimalFormat("###.00"))));
		panel.add(txtPreco);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(FrmConsultaProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Pesquisar/Lupa(24x24).png")));
		btnPesquisar.setBounds(303, 54, 143, 40);
		getRootPane().setDefaultButton(btnPesquisar);
		panel.add(btnPesquisar);
		
		btnLimparCampos = new JButton("Limpar Campos");
		btnLimparCampos.setIcon(new ImageIcon(FrmConsultaProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Corrigir/Borracha(24x24).png")));
		btnLimparCampos.setBounds(456, 54, 143, 40);
		panel.add(btnLimparCampos);

		
	}
}
