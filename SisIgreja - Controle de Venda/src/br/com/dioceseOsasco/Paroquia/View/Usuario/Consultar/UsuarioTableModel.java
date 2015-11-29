package br.com.dioceseOsasco.Paroquia.View.Usuario.Consultar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;

public class UsuarioTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	
	
	private static final int COL_NOME_USUARIO = 0;  
    private static final int COL_TIPO_SISTEMA = 1;
    private static final int COL_USUARIO = 2;
    private static final int COL_DATA_NASCIMENTO = 3;
  
    private List<TbUsuario> tb_usuario; ///////////////Valores
	
    //Esse é um construtor, que recebe a nossa lista de livros  
    public UsuarioTableModel(List<TbUsuario> tb_usuario) {  
          this.tb_usuario = new ArrayList<TbUsuario>(tb_usuario);  
    }  
	
    
	@Override
	public int getColumnCount() {
		//Quantas colunas tem a tabela? Nesse exemplo, só 2.  
        return 4;  
	}

	@Override
	public int getRowCount() {
		//Quantas linhas tem sua tabela? Uma para cada item da lista.  
        return tb_usuario.size();
	}
	
	public String getColumnName(int column) {  
        //Qual é o nome das nossas colunas?  
        if (column == COL_NOME_USUARIO) return "Colaborador";  
        if (column == COL_TIPO_SISTEMA) return "Tipo de Sistema";  
        if (column == COL_USUARIO) return "Usuário no sistema"; 
        if (column == COL_DATA_NASCIMENTO) return "Data de Nascimento";
        return ""; //Nunca deve ocorrer  
    }  

	
	
	
	
	@Override
	public Object getValueAt(int row, int column) {
	        //Precisamos retornar o valor da coluna column e da linha row.  
	        TbUsuario usuario = tb_usuario.get(row);  
	        if (column == COL_NOME_USUARIO) return usuario.getNome();  
	        else if (column == COL_TIPO_SISTEMA) return usuario.getNivelTipo();
	        else if (column == COL_USUARIO) return usuario.getUsuario();
	        else if (column == COL_DATA_NASCIMENTO) {
	        	return new ConversorData().ConverterDateSQLEmString(usuario.getDataNascimento());
	        }
	        return ""; //Nunca deve ocorrer  
	    
	}
	
	   public void setValueAt(Object aValue, int rowIndex, int columnIndex) {  
	       
		   TbUsuario usuario = tb_usuario.get(rowIndex); 
		   //Vamos alterar o valor da coluna columnIndex na linha rowIndex com o valor aValue passado no parâmetro.  
	        //Note que vc poderia alterar 2 campos ao invés de um só.  
	        if (columnIndex== COL_NOME_USUARIO) usuario.setNome(aValue.toString());
	        else if (columnIndex== COL_TIPO_SISTEMA) usuario.setNivelTipo(aValue.toString());
	        else if (columnIndex == COL_USUARIO) usuario.setUsuario(aValue.toString());
	        else if (columnIndex == COL_DATA_NASCIMENTO)
				try {
					new ConversorData().ConverterStringParaDate(aValue.toString());
				} catch (ParseException e) { e.getMessage(); }
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
	    public TbUsuario get(int row) {  
	        return tb_usuario.get(row);  
	    }  

}
