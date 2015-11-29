package br.com.dioceseOsasco.Paroquia.Controller;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import br.com.dioceseOsasco.Paroquia.Model.TbVenda;

public class VendaDAO {
	
	EntityManager em;
	
	public VendaDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	/**
	 * Retorna uma venda através do código passado por parametro
	 * @param codProduto
	 * @return 
	 */
	public TbVenda find(BigInteger codVenda){
		em.getTransaction().begin();
		TbVenda tbVenda = em.find(TbVenda.class, codVenda);
		em.getTransaction().commit();
		
		return tbVenda;	
	}
	
	/***
	 * Cadastra a venda no sistema.
	 * 
	 * @param produto
	 * @return
	 */
	public BigInteger cadastrarVenda(TbVenda tbVenda){
		
		BigInteger idVenda = null;
		
		try {
			em.getTransaction().begin();
			em.persist(tbVenda);
			em.getTransaction().commit();
			idVenda = tbVenda.getIdVenda();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return idVenda;
		
	}	
	
	/**
	 * Atualiza a Venda no sistema
	 * @param tbVenda
	 * @return
	 */
	public boolean atualizarVenda(TbVenda tbVenda){
		try{
			em.getTransaction().begin();
			em.merge(tbVenda); 
			em.getTransaction().commit();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Remove a Venda no sistema
	 * @param idvenda
	 * @return
	 */
	public boolean removerVenda(int idvenda){
		
		TbVenda tbVenda = em.find(TbVenda.class, idvenda);
        if (tbVenda != null) {
            em.getTransaction().begin();
            em.remove(tbVenda);
            em.getTransaction().commit();
            return true;
        }
        return false;

		
	}
	
}
