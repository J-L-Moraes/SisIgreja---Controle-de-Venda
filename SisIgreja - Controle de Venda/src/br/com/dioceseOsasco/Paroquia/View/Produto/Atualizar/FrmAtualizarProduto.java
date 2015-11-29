package br.com.dioceseOsasco.Paroquia.View.Produto.Atualizar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

@SuppressWarnings("serial")
public class FrmAtualizarProduto extends JDialog {
	
	private JPanel contentPane;
	private JLabel lblProduto;
	private JLabel lblPreco;
	JFormattedTextField txtPreco;
	JButton btnAtualizarProduto;
	JButton btnCancelar;
	JComboBox<TbProduto> cmBxProduto;
	JComboBox<TbProduto> cmBxProdutoBACKUP; //ComboBox Identico ao cmbxProduto para retornar o indice
	
	JComboBox<?> cmBxAtivo;
	private JLabel lblPossuiProduto;
	
	public static List<TbProduto> tb_produto;
	public static List<TbProduto> tb_produtoBACKUP;
	
	 
	
	public FrmAtualizarProduto(){
		Interface();
		new AtualizarListener(this);
		
	}
	
	/**
	 * Interface do Atualizar produto
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void Interface(){
		
		setTitle("SisIgreja - Atualizar produto cadastrado");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAtualizarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		
		setSize(441, 247);
		this.setLocationRelativeTo(null);

		setModal(true);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblProduto = new JLabel("Produto:");
		lblProduto.setBounds(24, 41, 56, 14);
		contentPane.add(lblProduto);
		
		lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setBounds(24, 81, 56, 14);
		contentPane.add(lblPreco);
		
		btnAtualizarProduto = new JButton("Atualizar Produto");
		btnAtualizarProduto.setIcon(new ImageIcon(FrmAtualizarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/alterar.png")));
		btnAtualizarProduto.setBounds(121, 164, 141, 32);
		getRootPane().setDefaultButton(btnAtualizarProduto); //Botão Padrão
		contentPane.add(btnAtualizarProduto);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmAtualizarProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(272, 164, 132, 32);
		contentPane.add(btnCancelar);
		
		cmBxProduto = new JComboBox<TbProduto>();
		cmBxProduto.setEditable(true);
		cmBxProduto.setMaximumRowCount(20);
		cmBxProduto.setBounds(80, 34, 296, 29);
		contentPane.add(cmBxProduto);
		
		cmBxProdutoBACKUP = new JComboBox<TbProduto>();
		popularComboBox();
				
		lblPossuiProduto = new JLabel("Possui o produto?");
		lblPossuiProduto.setBounds(24, 118, 108, 21);
		contentPane.add(lblPossuiProduto);
		
		cmBxAtivo = new JComboBox();
		cmBxAtivo.setModel(new DefaultComboBoxModel(new String[] {"Sim", "N\u00E3o"}));
		cmBxAtivo.setBounds(143, 112, 141, 29);
		cmBxAtivo.setSelectedItem(null);
		cmBxAtivo.setEnabled(false);
		contentPane.add(cmBxAtivo);
		
		txtPreco = new JFormattedTextField();
		txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPreco.setBounds(80, 74, 296, 29);
		txtPreco.setFormatterFactory(new DefaultFormatterFactory( new NumberFormatter(new DecimalFormat("###.00"))));
		txtPreco.setEnabled(false);
		contentPane.add(txtPreco);

	}
	
	/**
	 * Popula-se os comboBox:
	 * 
	 * cmBxProduto
	 * cmBxProdutoBACKUP
	 * 
	 * Através de pesquisa com o Banco de dados.
	 */
    void popularComboBox()  
    {     
    	cmBxProduto.removeAllItems();
    	cmBxProdutoBACKUP.removeAllItems();

    	ProdutoDAO produtoDAO = new ProdutoDAO();
        tb_produto = produtoDAO.listarTodosProduto();
        
        for (TbProduto tbProduto : tb_produto) {
        	cmBxProduto.addItem(tbProduto);
        	cmBxProdutoBACKUP.addItem(tbProduto);
        }
        
        cmBxProduto.setSelectedItem(null);		
    }  
    
    /**
     * Controla o estado de habilitado ou não do componente
     * @param modo
     */
    void EstadoComponente(boolean modo){
    	cmBxAtivo.setEnabled(modo);
    	cmBxProduto.setEnabled(modo);
    	txtPreco.setEnabled(modo);
    	btnAtualizarProduto.setEnabled(modo);
    	btnCancelar.setEnabled(modo);
    }
    
    /**
     * Controla o estado de habilitado ou não do componente na forma inicial da aplicação.
     * @param modo
     */
    void EstadoComponenteInicial(boolean modo){
    	cmBxProduto.setEnabled(true);
    	cmBxAtivo.setEnabled(modo);
    	txtPreco.setEnabled(modo);
    	btnAtualizarProduto.setEnabled(true);
    	btnCancelar.setEnabled(true);
    }
    
    
    
}