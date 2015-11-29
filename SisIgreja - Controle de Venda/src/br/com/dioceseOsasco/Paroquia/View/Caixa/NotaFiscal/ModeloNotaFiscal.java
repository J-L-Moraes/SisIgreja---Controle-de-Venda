package br.com.dioceseOsasco.Paroquia.View.Caixa.NotaFiscal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import br.com.dioceseOsasco.Paroquia.Controller.ItemDAO;
import br.com.dioceseOsasco.Paroquia.Controller.VendaDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;

public class ModeloNotaFiscal {

	BigInteger codVenda;
	TbVenda tbVenda;
	VendaDAO vendaDAO;
	ItemDAO itemDAO;
	
	/**
	 * Padrão Sigleton
	 */
	// cria um atributo estático da classe
	private static ModeloNotaFiscal instance;

	// cria um construtor privado pra evitar novas instancias
	private ModeloNotaFiscal(BigInteger codVenda) {
		this.codVenda = codVenda;

		itemDAO = new ItemDAO();
		vendaDAO = new VendaDAO();

		this.tbVenda = vendaDAO.find(codVenda);

	}

	// cria o método que vai chamar a única instancia da classe
	public static ModeloNotaFiscal getInstance(BigInteger codVenda) {

				
		if (instance == null) {
			instance = new ModeloNotaFiscal(codVenda);
		}
		
		if (instance.codVenda != codVenda) {
			instance = new ModeloNotaFiscal(codVenda);
		}
		
		
		return instance;
	}


	
	
	/**
	 * Cabeçalho da Nota Fiscal
	 * @return
	 */
	public String Cabecalho(){
		String cabecalho = null;
		cabecalho = "\n------------------------------------------------------------\r"
					+ "\n"+ tbVenda.getTbEvento().getTbLocal().getTbLocalizacao().getNomeParoquia().toUpperCase() +"\r"
					+ "\nCOMUNIDADE : "+ tbVenda.getTbEvento().getTbLocal().getNomeComunidade().toUpperCase()+" \r" 
					+ "\n------------------------------------------------------------\r"
					+ "\nCÓDIGO DA VENDA NO SISTEMA : " + codVenda  +"\r"
					+ "\nVENDEDOR: " +tbVenda.getTbUsuario().getNome() +"\r"
					+ "\n------------------------------------------------------------\r";
		
		return cabecalho.toUpperCase();
	}
	
	/**
	 * Produtos Vendidos no sistema
	 * @return
	 */
	public String ProdutosVendido(){

		List<TbItem> ListItem = itemDAO.consultarProdutos(tbVenda);
		
		String Inicial = "\nPRODUTO                                    QTDE   VALOR UN. VALOR\r" 
						 + "\n\r";
		
		String ProdutosVendido = Inicial;
		
		for (TbItem tbItem : ListItem) {
			ProdutosVendido = ProdutosVendido 
							+ verificarDigitosFaltantesDescricao(tbItem.getTbProduto().getDescricao()) //Descrição
							+ verificarDigitosFaltantesQuantidade(tbItem.getQuantidade()) //Quantidade
							+ verificarDigitosFaltantesPreco(tbItem.getTbProduto().getPreco()) //Preço unitário
							+ verificarDigitosFaltantesPreco(tbItem.getValorItem()) +"\n"; //Preço do Item
		}
		
		return ProdutosVendido.toUpperCase();
	}
	
	/**
	 * Valor da Compra
	 * @return
	 */
	public String valorCompra(){
		
		Object ValorPagar = itemDAO.consultarPrecoPagar(codVenda);
		String ValorCompra = "";
		if (ValorPagar != null) {

			ValorCompra = 	" \n\t          \r"							
									+ "\n------------------------------------------------------------\r"
									+ "\n"+ verificarDigitosFaltantesOutros("VALOR DA COMPRA: R$ "+ ValorPagar.toString()) +"\r";
				
		}
		return ValorCompra.toUpperCase();
	}

	/**
	 * Faz o Final da Nota Fiscal, quando é dinheiro
	 * @param ValorPago
	 * @param Troco
	 * @return
	 */
	public String finalCompra(Double ValorPago, Double Troco){
				
		String FinalCompra =
				  "\n"+verificarDigitosFaltantesOutros("VALOR PAGO: R$ "+ValorPago)+"\r"
				+ "\n"+verificarDigitosFaltantesOutros("TROCO: R$ "+Troco)+"\r"
				+ "\n \r"
				+ "\n-----------------------------------------------------------\r"
				+ "\n       ******* N\u00C3O \u00C9 DOCUMENTO FISCAL ******     \r"
				+ "\n-----------------------------------------------------------\r"
				+ "\n\t               ";
		
		return FinalCompra.toUpperCase();
	}

	/**
	 * Faz o Final da Nota Fiscal, quando não é dinheiro, como não esta informação não é salvo no Banco de Dados passa-se o paraemtro Forma de pagamennto
	 * @param FormaPagamento
	 * @return
	 */
	public String finalCompra(String FormaPagamento){
		
		String FinalCompra =
				  "\n      FORMA DE PAGAMENTO: "+FormaPagamento+"\r"
				+ "\n------------------------------------------------------------\r"
				+ "\n       ******* N\u00C3O \u00C9 DOCUMENTO FISCAL ******      \r"
				+ "\n------------------------------------------------------------\r"
				+ "\n\t               ";
		
		return FinalCompra.toUpperCase();
	}

	/**
	 * Faz o Final da Nota para o Formulario AnalisarCompra
	 * @return
	 */
	public String finalAnalisarCompra(){
		
		String FinalCompra =
				  "\n------------------------------------------------------------\r"
				+ "\n       ******* N\u00C3O \u00C9 DOCUMENTO FISCAL ******      \r"
				+ "\n------------------------------------------------------------\r"
				+ "\n\t               ";
		
		return FinalCompra.toUpperCase();
	}
	

	/**
	 * Verificar quantos digitos faltantes para a descrição do produto incrementando os espaços faltantes em Branco ao CONTRARIO.
	 * 
	 * @param outros
	 * @return
	 */
	String verificarDigitosFaltantesOutros(String outros){
		for (int i = outros.length(); i < 50; i++) {
			outros = " " + outros;
		}
		return outros;
	}
	
	/**
	 * Verificar quantos digitos faltantes para a descrição do produto incrementando os espaços faltantes em Branco.
	 * 
	 * @param descricao
	 * @return
	 */
	String verificarDigitosFaltantesDescricao(String descricao){
		int tamanho = descricao.length();

		for (int i = tamanho; i < 45; i++) {
			descricao = descricao +" ";
		}
		
		if(tamanho > 40){
			descricao = descricao +"\n";
			for (int i = 0; i < 63; i++) {
				descricao = descricao +" ";
			}
		}
		
		return descricao;
	}
	
	/**
	 * Verificar quantos digitos faltantes para a qunatidade afim de incrementar os espaços faltantes.
	 * 
	 * @param quantidade
	 * @return
	 */
	String verificarDigitosFaltantesQuantidade(int qtde){
		String quantidade = String.valueOf(qtde);
		for (int i = quantidade.length(); i < 10; i++) {
			quantidade = quantidade +" ";
		}
		return quantidade;
	}
	
	/**
	 * Verificar quantos digitos faltantes para a preço afim de incrementar os espaços faltantes.
	 * 
	 * @param quantidade
	 * @return
	 */
	String verificarDigitosFaltantesPreco(BigDecimal valorPreco){
		String preco = "R$ "+ String.valueOf(valorPreco);
		for (int i = preco.length(); i < 10; i++) {
			preco = preco +" ";
		}
		return preco;
	}
	
}
