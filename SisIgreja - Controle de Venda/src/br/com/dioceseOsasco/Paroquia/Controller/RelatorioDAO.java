package br.com.dioceseOsasco.Paroquia.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.dioceseOsasco.Paroquia.Model.TbItem;

public class RelatorioDAO {
	

	EntityManager em;
	
	public RelatorioDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	/**
	 * Faz uma consulta no banco retornando um List para utilizar no relatório
	 * 
	 * A Primeira pesquisa retorna a consulta no banco de dados
	 * A Segunda retorna somente os valores somados, posteriomente é adicionado através de um foreach os valores na Lista Inicial substituindo todos os valores do Atribyto ValorItem.
	 * 
	 * @return List<TbItem>
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioVendaSimples(){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1 "
								+" GROUP BY t.tbVenda.tbEvento.idEvento"
								+" ORDER BY t.tbVenda.tbEvento.dataEvento DESC").getResultList();
		
		
		List<Object> somaValorItem = new ArrayList<Object>();
		//Soma todos os valores e joga no atriburo ValorItem pois o mesmo não é utilizado
		Query query = em.createQuery("SELECT SUM(t.valorItem) FROM TbItem t"
				 +" WHERE t.tbVenda.vendaFinalizada = 1 "
				 +" GROUP BY t.tbVenda.tbEvento.idEvento"
				 +" ORDER BY t.tbVenda.tbEvento.dataEvento DESC");
		somaValorItem = query.getResultList();
		
		int posicao = 0;
		List<TbItem> TbCopia = new ArrayList<TbItem>(TbItem.size());
		TbCopia.addAll(TbItem);
		for (TbItem tbCopia : TbCopia) {
			tbCopia.setValorItem(new BigDecimal(somaValorItem.get(posicao).toString()));			
			TbItem.remove(posicao);
			TbItem.add(posicao, (br.com.dioceseOsasco.Paroquia.Model.TbItem) tbCopia);

			posicao = posicao +1;
		}
		
		return TbItem;
	}

	/**
	 * Faz uma consulta no banco retornando um List pela data informada para utilizar no relatório
	 * 
	 * 
	 * A Primeira pesquisa retorna a consulta no banco de dados
	 * A Segunda retorna somente os valores somados, posteriomente é adicionado através de um foreach os valores na Lista Inicial substituindo todos os valores do Atribyto ValorItem.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioVendaSimples(Date dataEvento){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento = :dataEvento"
								+" GROUP BY t.tbVenda.tbEvento.idEvento"
								+" ORDER BY t.tbVenda.tbEvento.dataEvento DESC")
								.setParameter("dataEvento", dataEvento).getResultList();
		
		
		List<Object> somaValorItem = new ArrayList<Object>();
		//Soma todos os valores e joga no atriburo ValorItem pois o mesmo não é utilizado
		Query query = em.createQuery("SELECT SUM(t.valorItem) FROM TbItem t"
				 +" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento = :dataEvento"
				 +" GROUP BY t.tbVenda.tbEvento.idEvento"
				 +" ORDER BY t.tbVenda.tbEvento.dataEvento DESC").setParameter("dataEvento", dataEvento);
		somaValorItem = query.getResultList();
		
		int posicao = 0;
		List<TbItem> TbCopia = new ArrayList<TbItem>(TbItem.size());
		TbCopia.addAll(TbItem);
		for (TbItem tbCopia : TbCopia) {
			tbCopia.setValorItem(new BigDecimal(somaValorItem.get(posicao).toString()));			
			TbItem.remove(posicao);
			TbItem.add(posicao, (br.com.dioceseOsasco.Paroquia.Model.TbItem) tbCopia);

			posicao = posicao +1;
		}
		
		return TbItem;
	}
	
	/**
	 * Faz uma busca no Banco de dados afim de popular o Relatório Detalhado
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioVendaDetalhado(){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1 "
								+" ORDER BY t.tbVenda.tbEvento.dataEvento DESC").getResultList();

		return TbItem;
	}
	
	/**
	 * Faz uma busca no Banco de dados afim de popular o Relatório Detalhado, informando a data
	 * @param dataEvento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioVendaDetalhado(Date dataEvento){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();
		
		TbItem = em.createQuery("SELECT t FROM TbItem t "
				+" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento = :dataEvento "
				+" ORDER BY t.tbVenda.tbEvento.dataEvento DESC")
				.setParameter("dataEvento", dataEvento)
				.getResultList();
		
		return TbItem;
	}
	
	
	
	/**
	 * Faz uma busca no Banco de dados afim de popular o Relatório Produto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioProduto(){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1"
								+" GROUP BY t.tbProduto.descricao").getResultList();
		
		List<?> somaQtde = new ArrayList<Object>();
		
		//Soma todos os valores e joga no List somaQtde pois o atributo não existe no Model
		Query query = em.createQuery("SELECT SUM(t.quantidade) FROM TbItem t"
				 					+" WHERE t.tbVenda.vendaFinalizada = 1"
				 					+" GROUP BY t.tbProduto.descricao");
		somaQtde =  query.getResultList();
		
		
		int posicao = 0;
		List<TbItem> TbCopia = new ArrayList<TbItem>();
		TbCopia.addAll(TbItem);
		
		//Transferio os valor da soma nos atributos: NomeEvento e Quantidades (que não são Utilizadas no relatório)
		for (TbItem tbCopia : TbCopia) {
			tbCopia.setQuantidade( Integer.parseInt(somaQtde.get(posicao).toString()));			
			tbCopia.getTbVenda().getTbEvento().setNomeEvento(somaQtde.get(posicao).toString()); //O jasperReport não aceita converter um dado já passado como parametro para sua utilização...
			
			TbItem.remove(posicao);
			TbItem.add(posicao, (br.com.dioceseOsasco.Paroquia.Model.TbItem) tbCopia);
			posicao = posicao +1;
		}
		
		return TbItem;
	}
	
	/**
	 * Faz uma busca no Banco de dados afim de popular o Relatório Produto
	 * @param dataEvento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioProduto(Date dataEvento){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento = :dataEvento"
								+" GROUP BY t.tbProduto.descricao")
								.setParameter("dataEvento", dataEvento)
								.getResultList();
		
		List<?> somaQtde = new ArrayList<Object>();
		
		//Soma todos os valores e joga no List somaQtde pois o atributo não existe no Model
		Query query = em.createQuery("SELECT SUM(t.quantidade) FROM TbItem t"
				 +" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento = :dataEvento"
				 +" GROUP BY t.tbProduto.descricao")
				.setParameter("dataEvento", dataEvento);
		somaQtde =  query.getResultList();
		
		
		int posicao = 0;
		List<TbItem> TbCopia = new ArrayList<TbItem>();
		TbCopia.addAll(TbItem);
		
		//Transferio os valor da soma nos atributos: NomeEvento e Quantidades (que não são Utilizadas no relatório)
		for (TbItem tbCopia : TbCopia) {
			tbCopia.setQuantidade( Integer.parseInt(somaQtde.get(posicao).toString()));			
			tbCopia.getTbVenda().getTbEvento().setNomeEvento(somaQtde.get(posicao).toString()); //O jasperReport não aceita converter um dado já passado como parametro para sua utilização...
			TbItem.remove(posicao);
			TbItem.add(posicao, (br.com.dioceseOsasco.Paroquia.Model.TbItem) tbCopia);
			posicao = posicao +1;
		}
		
		return TbItem;
	}
	
	/**
	 * Faz uma busca no Banco de dados afim de popular o Relatório Produto
	 * @param dataEventoInicial
	 * @param dataEventoFinal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem>  RelatorioProduto(Date dataEventoInicial, Date dataEventoFinal){
		
		List<TbItem> TbItem = new ArrayList<TbItem>();

		TbItem = em.createQuery("SELECT t FROM TbItem t "
								+" WHERE t.tbVenda.vendaFinalizada = 1  AND t.tbVenda.tbEvento.dataEvento BETWEEN :dataEventoInicial AND :dataEventoFinal"
								+" GROUP BY t.tbProduto.descricao")
								.setParameter("dataEventoInicial", dataEventoInicial)
								.setParameter("dataEventoFinal", dataEventoFinal)
								.getResultList();
		
		List<?> somaQtde = new ArrayList<Object>();
		
		//Soma todos os valores e joga no List somaQtde pois o atributo não existe no Model
		Query query = em.createQuery("SELECT SUM(t.quantidade) FROM TbItem t"
				 +" WHERE t.tbVenda.vendaFinalizada = 1 AND t.tbVenda.tbEvento.dataEvento BETWEEN :dataEventoInicial AND :dataEventoFinal"
				 +" GROUP BY t.tbProduto.descricao")
				.setParameter("dataEventoInicial", dataEventoInicial)
				.setParameter("dataEventoFinal", dataEventoFinal);
		somaQtde =  query.getResultList();
		
		
		int posicao = 0;
		List<TbItem> TbCopia = new ArrayList<TbItem>();
		TbCopia.addAll(TbItem);
		
		//Transferio os valor da soma nos atributos: NomeEvento e Quantidades (que não são Utilizadas no relatório)
		for (TbItem tbCopia : TbCopia) {
			tbCopia.setQuantidade( Integer.parseInt(somaQtde.get(posicao).toString()));			
			tbCopia.getTbVenda().getTbEvento().setNomeEvento(somaQtde.get(posicao).toString()); //O jasperReport não aceita converter um dado já passado como parametro para sua utilização...
			TbItem.remove(posicao);
			TbItem.add(posicao, (br.com.dioceseOsasco.Paroquia.Model.TbItem) tbCopia);
			posicao = posicao +1;
		}
		
		return TbItem;
	}
	
	
	
	
	
}
