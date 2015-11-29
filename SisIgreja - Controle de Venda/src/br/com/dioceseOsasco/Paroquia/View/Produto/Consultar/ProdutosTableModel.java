package br.com.dioceseOsasco.Paroquia.View.Produto.Consultar;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

@SuppressWarnings("serial")
public class ProdutosTableModel extends AbstractTableModel {

	private static final int COL_PRODUTO = 0;  
    private static final int COL_PRECO = 1;
    private static final int COL_ATIVO = 2;
  
    private List<TbProduto> tb_produto; ///////////////Valores
	
    //Esse é um construtor, que recebe a nossa lista de livros  
    public ProdutosTableModel(List<TbProduto> tb_produto) {  
          this.tb_produto = new ArrayList<TbProduto>(tb_produto);  
    }  
	
    
	@Override
	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 2.  
        return 3;  
	}

	@Override
	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.  
        return tb_produto.size();
	}
	
	public String getColumnName(int column) {  
        //Qual é o nome das nossas colunas?  
        if (column == COL_PRODUTO) return "Produto";  
        if (column == COL_PRECO) return "Preço";  
        if (column == COL_ATIVO) return "Produto em estoque";  
        return ""; //Nunca deve ocorrer  
    }  

	
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
	        //Precisamos retornar o valor da coluna column e da linha row.  
	        TbProduto produto = tb_produto.get(row);  
	        if (column == COL_PRODUTO) return produto.getDescricao();  
	        else if (column == COL_PRECO) return produto.getPreco().toString();
	        else if (column == COL_ATIVO){ 
	        	if (produto.getAtivo() == 1) {
					return "SIM";
				}else{
					return "N\u00E3o";
				}};
	        return ""; //Nunca deve ocorrer  
	    
	}
	
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	        //TbProduto produto = tb_produto.get(row);  
		   TbProduto produto = tb_produto.get(rowIndex); 
		   //Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parâmetro.  
	        //Note que vc poderia alterar 2 campos ao invés de um só.  
	        if (columnIndex== COL_PRODUTO) produto.setDescricao(aValue.toString());
	        else if (columnIndex== COL_PRECO){
	        	BigDecimal preco = null;
	        	try {
	    			MathContext mc = new MathContext(aValue.toString().length()); // Precisão
	    			preco = new BigDecimal(aValue.toString().replace(",", "."), mc);
	    			preco.toPlainString();
	    			produto.setPreco(preco);
	    		} catch (NumberFormatException e) { 		}
	        }
	        else if (columnIndex == COL_ATIVO){
	        	
	        	if (aValue.toString().equals("SIM")){
	        			produto.setAtivo((byte) 1);
				}else{
					produto.setAtivo((byte) 0);
				}
	        
	        }
	    }  
	  
	    public Class<?> getColumnClass(int columnIndex) {  
	        //Qual a classe das nossas colunas? Como estamos exibindo texto, é string.  
	        return String.class;  
	    }  
	      
	    public boolean isCellEditable(int rowIndex, int columnIndex) {  
	        //Indicamos se a célula da rowIndex e da columnIndex não é editável.  
	    	return false;  
	    }  
	    //Já que esse tableModel é de livros, vamos fazer um get que retorne um livro inteiro.  
	    //Isso elimina a necessidade de chamar o getValueAt() nas telas.   
	    public TbProduto get(int row) {  
	        return tb_produto.get(row);  
	    }  
	

}
