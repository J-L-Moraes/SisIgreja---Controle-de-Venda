package br.com.dioceseOsasco.Paroquia.View.Local.Consultar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dioceseOsasco.Paroquia.Model.TbLocal;

@SuppressWarnings("serial")
public class LocalTableModel  extends AbstractTableModel {
	
	private static final int COL_COMUNIDADE = 0;  
    private static final int COL_PAROQUIA = 1;
    private static final int COL_DIOCESE = 2;
  
    private List<TbLocal> tb_Local; ///////////////Valores
	
    //Esse é um construtor, que recebe a nossa lista de livros  
    public LocalTableModel(List<TbLocal> tb_Local) {  
          this.tb_Local = new ArrayList<TbLocal>(tb_Local);  
    }  
	
    
	@Override
	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 2.  
        return 3;  
	}

	@Override
	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.  
        return tb_Local.size();
	}
	
	public String getColumnName(int column) {  
        //Qual é o nome das nossas colunas?  
        if (column == COL_COMUNIDADE) return "Comunidade";  
        if (column == COL_PAROQUIA) return "Paróquia";  
        if (column == COL_DIOCESE) return "Diocese";  
        return ""; //Nunca deve ocorrer  
    }  

	
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
	        //Precisamos retornar o valor da coluna column e da linha row.  
	        TbLocal local =  tb_Local.get(row);  
	        if (column == COL_COMUNIDADE) return local.getNomeComunidade();  
	        else if (column == COL_PAROQUIA) return local.getTbLocalizacao().getNomeParoquia();
	        else if (column == COL_DIOCESE) return local.getTbLocalizacao().getNomeDiocese();
	        return ""; //Nunca deve ocorrer  
	    
	}
	
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	        //TbProduto produto = tb_produto.get(row);  
		   TbLocal local = tb_Local.get(rowIndex); 
		   //Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parâmetro.  
	        //Note que vc poderia alterar 2 campos ao invés de um só.  
	        if (columnIndex== COL_COMUNIDADE) local.setNomeComunidade(aValue.toString());
	        else if (columnIndex== COL_PAROQUIA) local.getTbLocalizacao().setNomeParoquia(aValue.toString());
	        else if (columnIndex == COL_DIOCESE) local.getTbLocalizacao().setNomeDiocese(aValue.toString());
	    }  
	  
	    public Class<?> getColumnClass(int columnIndex) {  
	        //Qual a classe das nossas colunas? Como estamos exibindo texto, é string.  
	        return String.class;  
	    }  
	      
	    public boolean isCellEditable(int rowIndex, int columnIndex) {  
	        //Indicamos se a célula da rowIndex e da columnIndex não é editável.  
	    	return true;  
	    }  
	    //Já que esse tableModel é de livros, vamos fazer um get que retorne um livro inteiro.  
	    //Isso elimina a necessidade de chamar o getValueAt() nas telas.   
	    public TbLocal get(int row) {  
	        return  tb_Local.get(row);  
	    }  
	


}
