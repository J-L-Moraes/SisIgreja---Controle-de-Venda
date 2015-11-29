package br.com.dioceseOsasco.Paroquia.View.Caixa.AdicionarItem;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;

@SuppressWarnings("serial")
public class FrmAdicionarItem extends JDialog {

	private JPanel contentPane;
	JTextField txtQuantidade;
	JComboBox<TbProduto> cmBxProduto;
	JLabel lblPrecoAPagar;
	JButton btnAdicionarCompra;
	JButton btnCancelar;


	public FrmAdicionarItem(BigInteger codigoVenda) {
		Interface();
		new AdicionarItemListener(this, codigoVenda);
	}
	
	void Interface(){
		
		setTitle("Adicione Itens a Venda.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		
		setSize(550, 226);
		this.setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlCaixaAddItem = new JPanel();
		pnlCaixaAddItem.setBackground(Color.WHITE);
		pnlCaixaAddItem.setBorder(new TitledBorder(null, "Adicionar itens ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCaixaAddItem.setBounds(10, 11, 520, 127);
		contentPane.add(pnlCaixaAddItem);
		pnlCaixaAddItem.setLayout(null);
		
		JLabel lblCodigoProduto = new JLabel("Produto:");
		lblCodigoProduto.setBounds(20, 37, 51, 14);
		pnlCaixaAddItem.add(lblCodigoProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(20, 83, 70, 14);
		pnlCaixaAddItem.add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuantidade.setBounds(98, 72, 117, 33);
		txtQuantidade.setColumns(10);
		txtQuantidade.setDocument(new ControlarDigitosNumeros(3));
		pnlCaixaAddItem.add(txtQuantidade);

		
		cmBxProduto = new JComboBox<TbProduto>();
		cmBxProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmBxProduto.setBackground(Color.WHITE);
		cmBxProduto.setBounds(100, 26, 392, 33);
		pnlCaixaAddItem.add(cmBxProduto);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
        List<TbProduto> tb_produto = produtoDAO.listarTodosProdutoAtivos();
        for (TbProduto tbProduto : tb_produto) {
        	cmBxProduto.addItem(tbProduto);
        }
        cmBxProduto.setSelectedItem(null);  
		
		lblPrecoAPagar = new JLabel("");
		lblPrecoAPagar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPrecoAPagar.setBounds(271, 72, 221, 33);
		pnlCaixaAddItem.add(lblPrecoAPagar);
		
		btnAdicionarCompra = new JButton("Adicionar \u00E0 Compra");
		btnAdicionarCompra.setBounds(250, 149, 153, 33);
		contentPane.add(btnAdicionarCompra);
		btnAdicionarCompra.setIcon(new ImageIcon(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AdicionarItem(24x24).png")));
		getRootPane().setDefaultButton(btnAdicionarCompra);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(413, 149, 115, 33);
		contentPane.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		
		
	}
}
