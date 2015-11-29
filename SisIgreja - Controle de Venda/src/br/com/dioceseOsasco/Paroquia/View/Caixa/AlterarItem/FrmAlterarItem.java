package br.com.dioceseOsasco.Paroquia.View.Caixa.AlterarItem;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.AdicionarItem.FrmAdicionarItem;
import br.com.dioceseOsasco.Paroquia.View.Componente.Teclado.ControlarDigitosNumeros;

@SuppressWarnings("serial")
public class FrmAlterarItem extends JDialog {

	private JPanel contentPane;
	JTextField txtQuantidade;
	JComboBox<TbProduto> cmBxProdutoVenda;
	JButton btnAlterarProduto;
	JButton btnCancelar;
	private JLabel lblPreco;
	private JLabel lblProdutoASer;
	JComboBox<TbItem> cmBxProdutoVendido;
	JLabel lblPrecoPagar;

	ItemDAO itemDAO;

	public FrmAlterarItem(BigInteger codigoVenda) {
		Interface(codigoVenda);
		new AlterarItemListener(this, codigoVenda);
	}
	
	void Interface(BigInteger codigoVenda){
		
		setTitle("Altere os itens vendidos.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		
		setSize(617,257);
		this.setLocationRelativeTo(null);


		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlCaixaAddItem = new JPanel();
		pnlCaixaAddItem.setBackground(Color.WHITE);
		pnlCaixaAddItem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alterar Produto Vendido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCaixaAddItem.setBounds(10, 11, 585, 162);
		contentPane.add(pnlCaixaAddItem);
		pnlCaixaAddItem.setLayout(null);
		
		JLabel lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(82, 77, 51, 14);
		pnlCaixaAddItem.add(lblProduto);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(66, 121, 67, 14);
		pnlCaixaAddItem.add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQuantidade.setBounds(143, 110, 115, 33);
		txtQuantidade.setColumns(10);
		txtQuantidade.setDocument(new ControlarDigitosNumeros(3));
		pnlCaixaAddItem.add(txtQuantidade);

		
		cmBxProdutoVenda = new JComboBox<TbProduto>();
		cmBxProdutoVenda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmBxProdutoVenda.setBackground(Color.WHITE);
		cmBxProdutoVenda.setBounds(143, 66, 414, 33);
		pnlCaixaAddItem.add(cmBxProdutoVenda);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
        List<TbProduto> tb_produto = produtoDAO.listarTodosProdutoAtivos();
        for (TbProduto tbProduto : tb_produto) {
        	cmBxProdutoVenda.addItem(tbProduto);
        }
        cmBxProdutoVenda.setSelectedItem(null);
        
        lblPreco = new JLabel("Pre\u00E7o:");
        lblPreco.setBounds(308, 121, 46, 14);
        pnlCaixaAddItem.add(lblPreco);
        
        lblProdutoASer = new JLabel("Produto a ser alterado:");
        lblProdutoASer.setBounds(23, 29, 115, 19);
        pnlCaixaAddItem.add(lblProdutoASer);
        
        cmBxProdutoVendido = new JComboBox<TbItem>();
        cmBxProdutoVendido.setBounds(143, 22, 414, 33);
        cmBxProdutoVendido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        VendaDAO vendaDAO = new VendaDAO();
		TbVenda tbVenda = new TbVenda();
		tbVenda = vendaDAO.find(codigoVenda);
		itemDAO = new ItemDAO();
	
		List<TbItem> ListItem = itemDAO.consultarProdutos(tbVenda);
		for (TbItem tbItem : ListItem) {
			cmBxProdutoVendido.addItem(tbItem);
		}
		cmBxProdutoVendido.setSelectedItem(null);

        
        pnlCaixaAddItem.add(cmBxProdutoVendido);
        
        lblPrecoPagar = new JLabel("");
        lblPrecoPagar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrecoPagar.setBounds(349, 114, 104, 25);
        pnlCaixaAddItem.add(lblPrecoPagar);
        
        btnAlterarProduto = new JButton("Alterar Produto");
        btnAlterarProduto.setBounds(292, 184, 135, 33);
        contentPane.add(btnAlterarProduto);
        btnAlterarProduto.setIcon(new ImageIcon(FrmAlterarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AlterarItem(24x24).png")));
        getRootPane().setDefaultButton(btnAlterarProduto);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(437, 184, 120, 33);
        contentPane.add(btnCancelar);
        btnCancelar.setIcon(new ImageIcon(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		
		
	}

}
