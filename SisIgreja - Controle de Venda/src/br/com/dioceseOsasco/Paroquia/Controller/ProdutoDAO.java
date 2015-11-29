package br.com.dioceseOsasco.Paroquia.Controller;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;


import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

public class ProdutoDAO {

	EntityManager em;
	
	public ProdutoDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	
	/***
	 * Cadastra o produto no sistema.
	 * 
	 * @param produto
	 * @return
	 */
	public boolean cadastrarProduto(TbProduto produto){
		
		try {
			em.getTransaction().begin();
			em.persist(produto);
			em.getTransaction().commit();
			ConnectionFactory.closeEntityManager(em);
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ConnectionFactory.closeEntityManager(em);
			return false;
		}
		
	}
	
	/**
	 * Remover Produto cadastrado
	 * @param produto
	 * @return boolean
	 */
	public boolean removerProduto(int idProduto){
		
		TbProduto produto = em.find(TbProduto.class, idProduto);
        if (produto != null) {
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
            return true;
        }
        return false;

		
	}
	
	/**
	 * Atualiza o produto cadastrado
	 * 
	 * @param produto
	 * @return boolean
	 */
	public boolean atualizarProduto(TbProduto produto){
		try{
			em.getTransaction().begin();
			em.merge(produto); 
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e) {
			ConnectionFactory.closeEntityManager(em);
			return false;
		}
	}
	
	
	/**
	 * Lista todos os produtos encontrados no Banco de Dados* 
	 * @return List<TbProdutos>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> listarTodosProduto(){
		
		List<TbProduto> produto = null;
		try {
			produto = em.createNamedQuery("TbProduto.findAll").getResultList();
		} catch (Exception e) { }
		return produto;
	}
	
	/**
	 * Lista todos os produtos ATIVOS encontrados no Banco de Dados* 
	 * @return List<TbProdutos>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> listarTodosProdutoAtivos(){
		
		List<TbProduto> produto = null;
		try {
			produto = em.createNamedQuery("TbProduto.findAllativoASC").getResultList();
		} catch (Exception e) { }
		return produto;
	}
	
	/**
	 * Lista todos os produtos Ordenando pela Descrição do Nome em ordem crescente atraves do NOME e do PREÇO
	 * @return List<TbProduto>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> findAllLikeDescricaoPreco(String descricao, BigDecimal preco){
			
		List<TbProduto> produto = null;
		
		try {
			produto = em.createNamedQuery("TbProduto.findAllLikeDescricaoPreco")
											.setParameter("descricao", "%"+ descricao +"%")
											.setParameter("preco", preco)
											.getResultList();
		} catch (Exception e) { }
		return produto;
	}
	
	/**
	 * Lista todos os produtos Ordenando pela Descrição do Nome em ordem crescente
	 * @return List<TbProduto>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> findAllOrdeByDescricaoASC(){
			
		List<TbProduto> produto = null;
		
		try {
			produto = em.createNamedQuery("TbProduto.findAllOrdeByDescricaoASC").getResultList();
		} catch (Exception e) { }
		return produto;
	}
	
	
	/**
	 * Lista todos os produtos Ordenando pela Descrição do Nome em ordem crescente pesquisando pelo Preço
	 * @param preco
	 * @return List<TbProduto>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> findAllPreco(BigDecimal preco){
		
		List<TbProduto> produto = null;
		
		try {
			produto = em.createNamedQuery("TbProduto.findAllPreco")
											.setParameter("preco", preco).getResultList();
		} catch (Exception e) { }
		return produto;
	}
	
	/**
	 * Lista todos os produtos Ordenando pela Descrição do Nome em ordem crescente pesquisando pelo Descrição do Produto
	 * @param descricao
	 * @return List<TbProduto>
	 */
	@SuppressWarnings("unchecked")
	public List<TbProduto> findAllLikeDescricao(String descricao){
		
		List<TbProduto> produto = null;
		
		try {
			produto = em.createNamedQuery("TbProduto.findAllLikeDescricao")
											.setParameter("descricao", "%"+descricao+"%").getResultList();
		} catch (Exception e) { }
		return produto;
	}

	/**
	 * Retorna um produto através do código passado por parametro
	 * @param codProduto
	 * @return 
	 */

	public TbProduto find(Integer codProduto) {

		em.getTransaction().begin();
		TbProduto tbProduto = em.find(TbProduto.class, codProduto);
		em.getTransaction().commit();
		
		return tbProduto;	
	}
}
