package br.com.dioceseOsasco.Paroquia.View.Produto.Excluir;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

@SuppressWarnings("serial")
public class FrmExcluirProduto extends JDialog {
	
	private JPanel contentPane;
	private JLabel lblProduto;
	JComboBox<TbProduto> cmBxProduto;
	JLabel lblPreco;
	JLabel lblStatusProduto;
	JButton btnExcluirProduto;
	JButton btnCancelar;
	
	List<TbProduto> tb_produto;
	ProdutoDAO produtoDAO;
	
	public FrmExcluirProduto() {
		Interface();
		new ExcluirListener(this);
	}
	
	

	/**
	 * Interface do Excluir produto
	 */
	private void Interface(){
		
		setTitle("SisIgreja - Excluir produto cadastrado");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmExcluirProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setSize(437, 194);
		this.setLocationRelativeTo(null);

		setModal(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(24, 28, 46, 14);
		contentPane.add(lblProduto);
		
		lblPreco = new JLabel();
		lblPreco.setBounds(24, 62, 381, 20);
		contentPane.add(lblPreco);
		
		btnExcluirProduto = new JButton("Excluir produto");
		btnExcluirProduto.setIcon(new ImageIcon(FrmExcluirProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/deletarTrash.png")));
		btnExcluirProduto.setBounds(122, 116, 141, 32);
		getRootPane().setDefaultButton(btnExcluirProduto); //Botão Padrão
		contentPane.add(btnExcluirProduto);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmExcluirProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(273, 116, 132, 32);
		contentPane.add(btnCancelar);
		
		cmBxProduto = new JComboBox<TbProduto>();
		cmBxProduto.setMaximumRowCount(20);
		cmBxProduto.setBounds(80, 21, 325, 29);
		contentPane.add(cmBxProduto);
		
		produtoDAO = new ProdutoDAO();	
		tb_produto = produtoDAO.listarTodosProduto();
		
		for (TbProduto tbProduto : tb_produto) {
			cmBxProduto.addItem(tbProduto);
		}
		cmBxProduto.setSelectedItem(null);
				
		lblStatusProduto = new JLabel();
		lblStatusProduto.setBounds(24, 85, 381, 20);
		contentPane.add(lblStatusProduto);

	}
}
