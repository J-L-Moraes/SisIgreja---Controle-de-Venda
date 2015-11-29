package br.com.dioceseOsasco.Paroquia.View.Componente;

public class UtilitarioVerificarCampos {

	/**
	 * Verifica-se o texto informado é nulo ou não.
	 * Retornando um Boolean True caso não seja nulo.
	 * 
	 * \nCom Verificação de Tamanho de texto.
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
	 * Verifica-se o texto informado é nulo ou não 
	 * Retornando um Boolean True caso não seja nulo. 
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
