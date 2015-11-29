package br.com.dioceseOsasco.Paroquia.View.Caixa.ExcluirItem;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.AdicionarItem.FrmAdicionarItem;

@SuppressWarnings("serial")
public class FrmExcluirItem extends JDialog {

	private JPanel contentPane;
	JButton btnAlterarProduto;
	JButton btnCancelar;
	private JLabel lblPreco;
	private JLabel lblProduto;
	JComboBox<TbItem> cmBxProduto;
	JLabel lblPrecoPagar;

	ItemDAO itemDAO;
	JLabel lblQtde;

	public FrmExcluirItem(BigInteger codigoVenda) {
		Interface(codigoVenda);
		new ExcluirItemListener(this, codigoVenda);
	}
	
	void Interface(BigInteger codigoVenda){
		
		setTitle("Excluir  Itens da Venda.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		
		setSize(563, 209);
		this.setLocationRelativeTo(null);


		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlCaixaAddItem = new JPanel();
		pnlCaixaAddItem.setBackground(Color.WHITE);
		pnlCaixaAddItem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Excluir Produto Vendido", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCaixaAddItem.setBounds(10, 11, 529, 113);
		contentPane.add(pnlCaixaAddItem);
		pnlCaixaAddItem.setLayout(null);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(22, 73, 67, 14);
		pnlCaixaAddItem.add(lblQuantidade);
		
        lblPreco = new JLabel("Pre\u00E7o:");
        lblPreco.setBounds(197, 73, 46, 14);
        pnlCaixaAddItem.add(lblPreco);

        lblPrecoPagar = new JLabel("");
        lblPrecoPagar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPrecoPagar.setBounds(253, 66, 104, 30);
        pnlCaixaAddItem.add(lblPrecoPagar);
        
        lblProduto = new JLabel("Produto:");
        lblProduto.setBounds(22, 31, 55, 19);
        pnlCaixaAddItem.add(lblProduto);
        
        cmBxProduto = new JComboBox<TbItem>();
        cmBxProduto.setBounds(87, 22, 414, 33);
        cmBxProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        VendaDAO vendaDAO = new VendaDAO();
		TbVenda tbVenda = new TbVenda();
		tbVenda = vendaDAO.find(codigoVenda);
		itemDAO = new ItemDAO();
	
		List<TbItem> ListItem = itemDAO.consultarProdutos(tbVenda);
		for (TbItem tbItem : ListItem) {
			cmBxProduto.addItem(tbItem);
		}
		cmBxProduto.setSelectedItem(null);

        pnlCaixaAddItem.add(cmBxProduto);
        
        
        lblQtde = new JLabel("");
        lblQtde.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblQtde.setBounds(99, 66, 88, 30);
        pnlCaixaAddItem.add(lblQtde);
        
        btnAlterarProduto = new JButton("Excluir Produto");
        btnAlterarProduto.setBounds(224, 133, 140, 33);
        contentPane.add(btnAlterarProduto);
        btnAlterarProduto.setIcon(new ImageIcon(FrmExcluirItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/RemoverItem(24x24).png")));
        getRootPane().setDefaultButton(btnAlterarProduto);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(377, 134, 115, 33);
        contentPane.add(btnCancelar);
        btnCancelar.setIcon(new ImageIcon(FrmAdicionarItem.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		
		
	}
}
