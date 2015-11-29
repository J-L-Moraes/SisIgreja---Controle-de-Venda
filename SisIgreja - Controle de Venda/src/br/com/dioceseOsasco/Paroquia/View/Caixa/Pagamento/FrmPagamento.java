package br.com.dioceseOsasco.Paroquia.View.Caixa.Pagamento;

import java.awt.Color;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import br.com.dioceseOsasco.Paroquia.View.Caixa.Principal.FrmCaixa;

@SuppressWarnings("serial")
public class FrmPagamento extends JDialog  {
	
	FrmCaixa frmCx;
	
	private JLabel lblValorPago;
	JFormattedTextField txtValorPago;
	JRadioButton rdbtnPagamentoEmDinheiro;
	JRadioButton rdbtnPagamentoEmCartao;
	JButton btnFinalizarCompra;
	JButton btnCancelar;
	
	
	public FrmPagamento(BigInteger codVenda, FrmCaixa frm) {
		Interface();
		this.frmCx = frm;
		new PagamentoListener(this, codVenda);
	}

	private void Interface() {
		
		setTitle("Escolhendo o tipo de pagamento");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPagamento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);

		setSize(394, 170);
		this.setLocationRelativeTo(null);


		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.setIcon(new ImageIcon(FrmPagamento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Caixa/AlterarItem(24x24).png")));
		btnFinalizarCompra.setBounds(107, 95, 139, 36);
		getContentPane().add(btnFinalizarCompra);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmPagamento.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(256, 95, 122, 36);
		getContentPane().add(btnCancelar);
		
		rdbtnPagamentoEmDinheiro = new JRadioButton("Pagamento em Dinheiro");
		rdbtnPagamentoEmDinheiro.setSelected(true);
		rdbtnPagamentoEmDinheiro.setBackground(Color.WHITE);
		rdbtnPagamentoEmDinheiro.setBounds(17, 17, 166, 23);
		getContentPane().add(rdbtnPagamentoEmDinheiro);
		
		rdbtnPagamentoEmCartao = new JRadioButton("Pagamento em Cart\u00E3o");
		rdbtnPagamentoEmCartao.setBackground(Color.WHITE);
		rdbtnPagamentoEmCartao.setBounds(185, 17, 178, 23);
		getContentPane().add(rdbtnPagamentoEmCartao);
		
		final ButtonGroup opcoesPagamento = new ButtonGroup();
		opcoesPagamento.add(rdbtnPagamentoEmDinheiro);
		opcoesPagamento.add(rdbtnPagamentoEmCartao);

		txtValorPago = new JFormattedTextField();
		txtValorPago.setBounds(121, 51, 148, 27);
		txtValorPago.setFormatterFactory(new DefaultFormatterFactory( new NumberFormatter(new DecimalFormat("###.00"))));
		txtValorPago.setColumns(10);
		getContentPane().add(txtValorPago);
		
		lblValorPago = new JLabel("Valor Pago:");
		lblValorPago.setBounds(47, 57, 64, 14);
		getContentPane().add(lblValorPago);
	}

}
