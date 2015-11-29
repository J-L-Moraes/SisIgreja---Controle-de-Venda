package br.com.dioceseOsasco.Paroquia.View.Componente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
	
	/**
	 * Converter Data no formato 1991-10-11 em 11/10/1991
	 */
	public String ConverterDateSQLEmString(Date date){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
    	df.setLenient(false);  

    	String DataConvertida = df.format(date);
    	
    	return DataConvertida;

	}
	
	/**
	 * Converter Data no formato 11/10/1991 em java.sql.date 1991-10-11
	 * @throws ParseException 
	 */
	public java.sql.Date ConverterStringParaDate(String DataString) throws ParseException{
		String dataString = DataString;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		java.sql.Date dataEvento = null;
		dataEvento = new java.sql.Date(format.parse(dataString).getTime());
		return dataEvento;
	}	
	
}
