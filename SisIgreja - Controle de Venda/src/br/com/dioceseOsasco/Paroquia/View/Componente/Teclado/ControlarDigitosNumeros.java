package br.com.dioceseOsasco.Paroquia.View.Componente.Teclado;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class ControlarDigitosNumeros extends PlainDocument {

	private int quantidadeMaxima;
	
	public ControlarDigitosNumeros(int maxLength){
		super();
		if(maxLength<=0)
			throw new IllegalArgumentException("Especifique a quantidade");
		quantidadeMaxima = maxLength;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
	
		if(str==null || getLength() == quantidadeMaxima)
			return;
		int totalquantia = (getLength() + str.length());
		if(totalquantia <= quantidadeMaxima){
			super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
			return;
		}
		String nova = str.substring(0, getLength() - quantidadeMaxima);
		super.insertString(offset, nova, attr);
		
	}
	
	public void replace (int offset, String str, AttributeSet attr) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
	}
	

	
	
}
