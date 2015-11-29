package br.com.dioceseOsasco.Paroquia.View.Evento.Consultar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dioceseOsasco.Paroquia.Model.TbEvento;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;

@SuppressWarnings("serial")
public class EventoTableModel extends AbstractTableModel {

    private static final int COL_ID_EVENTO = 0;
	private static final int COL_EVENTO = 1;  
    private static final int COL_DIA_DO_EVENTO = 2;
    private static final int COL_COMUNIDADE = 3;

  
    private List<TbEvento> tb_evento; ///////////////Valores
	
    //Esse é um construtor, que recebe a nossa lista de livros  
    public EventoTableModel(List<TbEvento> tb_evento) {  
          this.tb_evento = new ArrayList<TbEvento>(tb_evento);  
    }  
	
    
	@Override
	public int getColumnCount() {
		//Quantas colunas tem a tabela?  
        return 4;  
	}

	@Override
	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.  
        return tb_evento.size();
	}
	
	public String getColumnName(int column) {  
        //Qual é o nome das nossas colunas?  
        if (column == COL_ID_EVENTO) return "Código do Evento";  
        if (column == COL_EVENTO) return "Nome do Evento";  
        if (column == COL_DIA_DO_EVENTO) return "Data do Evento"; 
        if (column == COL_COMUNIDADE) return "Comunidade";
        return ""; //Nunca deve ocorrer  
    }  

	
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
	        
		//Precisamos retornar o valor da coluna column e da linha row.  
	        TbEvento evento = tb_evento.get(row);  
	        if (column == COL_ID_EVENTO) return evento.getIdEvento();  
	        else if (column == COL_EVENTO) return evento.getNomeEvento();
	        else if (column == COL_DIA_DO_EVENTO) return new ConversorData().ConverterDateSQLEmString(evento.getDataEvento());
	        else if (column == COL_COMUNIDADE) return evento.getTbLocal().getNomeComunidade();	
	        return ""; //Nunca deve ocorrer  
	        	    
	}
	
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	        //TbProduto produto = tb_evento.get(row);  
		   TbEvento evento = tb_evento.get(rowIndex); 
		   //Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parâmetro.  
	        //Note que vc poderia alterar 2 campos ao invés de um só.  
	        if (columnIndex == COL_ID_EVENTO) evento.setIdEvento(Integer.parseInt(aValue.toString()));
	        if (columnIndex == COL_EVENTO) evento.setNomeEvento(aValue.toString());
	        else if (columnIndex == COL_DIA_DO_EVENTO){
	        	try {
	        		Date DataConvertida = new ConversorData().ConverterStringParaDate(aValue.toString());
		        	evento.setDataEvento(DataConvertida);
				} catch (Exception e) {
					System.out.println("Não foi possivel Converter");
				}	
	        }
	        else if (columnIndex == COL_COMUNIDADE) evento.getTbLocal().setNomeComunidade(aValue.toString());
	        	
	        	
	        
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
	    public TbEvento get(int row) {  
	        return tb_evento.get(row);  
	    }  
}
