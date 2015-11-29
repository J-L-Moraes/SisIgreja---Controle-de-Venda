package br.com.dioceseOsasco.Paroquia.View.Componente;

public class UtilitarioVerificarCampos {

	/**
	 * Verifica-se o texto informado � nulo ou n�o.
	 * Retornando um Boolean True caso n�o seja nulo.
	 * 
	 * \nCom Verifica��o de Tamanho de texto.
	 * 
	 * @param texto
	 * @return
	 */
	public Boolean VerificarCamposNulos(String texto){
		
		if (texto.isEmpty()) {
			return false;
		}
		if (texto.equals("")) {
			return false;
		}
		if (texto.length() <= 2){
			return false;
		}
		return true;
	}

	/**
	 * Verifica-se o texto informado � nulo ou n�o 
	 * Retornando um Boolean True caso n�o seja nulo. 
	 * 
	 * @param texto
	 * @return
	 */
	public Boolean VerificarCampos(String string){
		
		if (string.isEmpty()) {
			return false;
		}
		if (string.equals("")) {
			return false;
		}
		return true;
	}
}
