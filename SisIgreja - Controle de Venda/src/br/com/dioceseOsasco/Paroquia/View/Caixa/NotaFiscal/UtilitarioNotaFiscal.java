package br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal;

import java.math.BigInteger;

import br.com.dioceseOsasco.Paroquia.View.Caixa.AnalisarPedido.FrmAnalisarPedido;
import br.com.dioceseOsasco.Paroquia.View.Caixa.Principal.FrmCaixa;

public class UtilitarioNotaFiscal {
	
	public void setInciarNotaFiscal(BigInteger codigoVenda){
		String cabecalho = ModeloNotaFiscal.getInstance(codigoVenda).Cabecalho();
		FrmCaixa.txtPaneNotaFiscal.setText( cabecalho);		
	}
	
	public void setAlteracaoNotaFiscal(BigInteger codigoVenda){
		String cabecalho = ModeloNotaFiscal.getInstance(codigoVenda).Cabecalho();
		String ProdutosVendido = ModeloNotaFiscal.getInstance(codigoVenda).ProdutosVendido();
		String TotalPagar = ModeloNotaFiscal.getInstance(codigoVenda).valorCompra();
		FrmCaixa.txtPaneNotaFiscal.setText( cabecalho + ProdutosVendido +TotalPagar );
		
	}
	
	public void setFinalizarVenda(BigInteger codigoVenda, Double ValorPago, Double Troco){
		String cabecalho = ModeloNotaFiscal.getInstance(codigoVenda).Cabecalho();
		String ProdutosVendido = ModeloNotaFiscal.getInstance(codigoVenda).ProdutosVendido();
		String TotalPagar = ModeloNotaFiscal.getInstance(codigoVenda).valorCompra();
		String Finalizarvenda = ModeloNotaFiscal.getInstance(codigoVenda).finalCompra(ValorPago, Troco);
		FrmCaixa.txtPaneNotaFiscal.setText( cabecalho + ProdutosVendido + TotalPagar + Finalizarvenda);		
	}	

	public void setFinalizarVenda(BigInteger codigoVenda, String FormaDePagamento){
		String cabecalho = ModeloNotaFiscal.getInstance(codigoVenda).Cabecalho();
		String ProdutosVendido = ModeloNotaFiscal.getInstance(codigoVenda).ProdutosVendido();
		String TotalPagar = ModeloNotaFiscal.getInstance(codigoVenda).valorCompra();
		String Finalizarvenda = ModeloNotaFiscal.getInstance(codigoVenda).finalCompra(FormaDePagamento);
		FrmCaixa.txtPaneNotaFiscal.setText( cabecalho + ProdutosVendido + TotalPagar + Finalizarvenda);		
	}	
	
	public void setAnalisarVenda(BigInteger codigoVenda){
		String cabecalho = ModeloNotaFiscal.getInstance(codigoVenda).Cabecalho();
		String ProdutosVendido = ModeloNotaFiscal.getInstance(codigoVenda).ProdutosVendido();
		String TotalPagar = ModeloNotaFiscal.getInstance(codigoVenda).valorCompra();
		String Finalizarvenda = ModeloNotaFiscal.getInstance(codigoVenda).finalAnalisarCompra();
		FrmAnalisarPedido.txtPaneNotaFiscal.setText( cabecalho + ProdutosVendido + TotalPagar + Finalizarvenda);		
	}	 

	
}
