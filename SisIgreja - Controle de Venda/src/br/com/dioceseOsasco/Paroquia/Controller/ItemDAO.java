package br.com.dioceseOsasco.Paroquia.Controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.Model.TbVenda;

public class ItemDAO {
	
	EntityManager em;
	
	public ItemDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	/**
	 * Cadastra o item da Venda no sistema.
	 * @param tbItem
	 * @return
	 */
	public boolean cadastarItemVenda(TbItem tbItem){
		
		try {
			em.getTransaction().begin();
			em.persist(tbItem);
			em.getTransaction().commit();
		
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * Consulta os Item da Venda Passando como parametro a Tabela Venda e retornando a Lista de Item Cadastrado com aquele produto na Venda. 
	 * @param venda
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem> consultarProdutos(TbVenda venda){
		
		List<TbItem> ListItem = null;
		
		try {
			em = ConnectionFactory.getEntityManager();
			ListItem = em.createNamedQuery("TbItem.findTbProdutoTbVenda")
											.setParameter("tbVenda", venda)
											.getResultList();
		} catch (Exception e) { }
		return ListItem; 
		
		
	}
	
	/**
	 * Consulta no Banco o Valor a ser pago pela venda
	 * @param venda
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object consultarPrecoPagar(BigInteger idVenda){
		
		Object ValorPagar = new ArrayList();
		
		try {
			em = ConnectionFactory.getEntityManager();
			ValorPagar = em.createNamedQuery("TbItem.findSUMValorItem")
											.setParameter("idVenda", idVenda)
											.getSingleResult();
		} catch (Exception e) { }
		
		return ValorPagar; 
		
		
	}
	
	/**
	 * Altera o Item da Venda
	 * @param tbItem
	 * @return
	 */
	public boolean alterarItemVenda (TbItem tbItem){
		
		try{
			em.getTransaction().begin();
			em.merge(tbItem); 
			em.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Remover Item da Venda cadastrado
	 * @param idItem
	 * @return boolean
	 */
	public boolean removerItem(BigInteger idItem){
		
		TbItem tbItem = em.find(TbItem.class, idItem);
        if (tbItem != null) {
            em.getTransaction().begin();
            em.remove(tbItem);
            em.getTransaction().commit();
            return true;
        }
        return false;	
	}
	
	/**
	 * Consulta os itens do Pedido Através do IdVenda
	 * @param BigInteger idVenda
	 * @return List<TbItem>
	 */
	@SuppressWarnings("unchecked")
	public List<TbItem> consultarPedidoItem(BigInteger idVenda){
		
		List<TbItem> ListItem = null;
		
		try {
			em = ConnectionFactory.getEntityManager();
			ListItem = em.createNamedQuery("TbItem.consultarPedidoItem")
											.setParameter("idVenda", idVenda)
											.getResultList();
		} catch (Exception e) { }
		return ListItem; 
	}
	
	

	

}
