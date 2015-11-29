package br.com.dioceseOsasco.Paroquia.View.Impressora;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;
import br.com.dioceseOsasco.Paroquia.View.Caixa.AnalisarPedido.FrmAnalisarPedido;
import br.com.dioceseOsasco.Paroquia.View.Caixa.Principal.FrmCaixa;

public class criarArquivo {
	
	File arquivo;
	/**
	 * Método Contrutor, este recebe o nome do Arquivo a ser gerado.
	 * @param nomeDoArquivo
	 */
	public criarArquivo(String nomeDoArquivo){
		localizacaoArquivo(nomeDoArquivo);
	}
	
	
	
	/**
	 * Cria um arquivo txt para a Nota Fiscal este arquivo txt será impresso com a chamada de outro método.
	 */
	public void criaTxtNotaFiscal(){
		
		String NotaFiscal = null;
		if (FrmCaixa.txtPaneNotaFiscal != null) {
			NotaFiscal = FrmCaixa.txtPaneNotaFiscal.getText();
		}else if (FrmAnalisarPedido.txtPaneNotaFiscal != null){
			NotaFiscal = FrmAnalisarPedido.txtPaneNotaFiscal.getText();
		}
		
		
		try {
			if(arquivo.exists()){
	        	arquivo.delete();
			}
			arquivo.createNewFile();

            FileWriter arquivoTxt = new FileWriter(arquivo, true);
            PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
            
            linhasTxt.println(NotaFiscal);
            arquivoTxt.close();
            
            new Imprimir().imprimirLPT1(arquivo);
			
			
		} catch (IOException e) {
			 JOptionPane.showMessageDialog(null, "Não foi possivel gerar/criar o Arquivo.\nVerifique a permissão da pasta, aonde se encontra a aplicação\n"+e.getMessage());
		}
	}
	
	
	/**
	 * Cria um arquivo txt para cada Cupom, este arquivo txt será impresso com a chamada de outro método.
	 */
	public void criaTxtCupom(BigInteger idVenda){
		
		VendaDAO vendaDAO = new VendaDAO();
		TbVenda tbVenda = new TbVenda();
		tbVenda = vendaDAO.find(idVenda);
		ItemDAO itemDAO = new ItemDAO(); 
		List<TbItem> ListItem = itemDAO.consultarProdutos(tbVenda);

		for (TbItem tbItem : ListItem) {
			
			try {
				
				for (int i = 1; i <= tbItem.getQuantidade(); i++) {
					
					if(arquivo.exists()){
			        	arquivo.delete();
					}
					arquivo.createNewFile();
					
					FileWriter arquivoTxt = new FileWriter(arquivo, true);
		            PrintWriter linhasTxt = new PrintWriter(arquivoTxt);
		            
		            linhasTxt.println("COMUNIDADE: "+tbItem.getTbVenda().getTbEvento().getTbLocal().getNomeComunidade().toUpperCase());
		            linhasTxt.println("EVENTO: "+tbItem.getTbVenda().getTbEvento().getNomeEvento().toUpperCase());
		            linhasTxt.println("");
		            linhasTxt.println("Quantidade: 1 ");
		            linhasTxt.println(tbItem.getTbProduto().getDescricao());
		            linhasTxt.println("");
		            linhasTxt.println("");
		            arquivoTxt.close();
		            
		            new Imprimir().imprimirLPT1(arquivo);
				}
				
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Não foi possivel gerar/criar o Arquivo.\nVerifique a permissão da pasta, aonde se encontra a aplicação\n"+e.getMessage());
			}		
		}	
	}
	
	
	
	
	
	
	/**
	 * Retorna o local onde a aplicação está sendo executada. 
	 * @param nome
	 * @return
	 */
	private  File localizacaoArquivo(String nome){
		return arquivo = new File( getApplicationPath() + nome);
	}
	/** 
     * Retorna o caminho onde a aplicação está sendo executada 
     * @return caminho da aplicação 
     */  
    private String getApplicationPath() {  
        
    	String path = System.getProperty("user.dir");
        try {  
            return URLDecoder.decode(path, "UTF-8");  
        }  
        catch (UnsupportedEncodingException e) {                  
            return path.replace("%20", " ");  
        }  
    } 

}
