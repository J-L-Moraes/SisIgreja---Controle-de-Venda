package br.com.dioceseOsasco.Paroquia.Controller;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.dioceseOsasco.Paroquia.Model.TbLocal;

public class LocalDAO {
	
	EntityManager em;

	public LocalDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	

	/**
	 * Retorna um Local através do código passado por parametro
	 * @param idLocal
	 * @return 
	 */

	public TbLocal find(Integer idLocal) {

		em.getTransaction().begin();
		TbLocal tbLocal = em.find(TbLocal.class, idLocal);
		em.getTransaction().commit();
		
		return tbLocal;	
	}

	/**
	 * Cadastra a Comunidade no sistema
	 * 
	 */
	public boolean CadastrarLocal(TbLocal tbLocal){
		
		try {
			em.getTransaction().begin();
			em.persist(tbLocal);
			em.getTransaction().commit();
		
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Atualiza a Comunidade Cadastrada no sistema
	 * @param tbLocal
	 * @return
	 */
	public boolean AtualizarLocal(TbLocal tbLocal){
		
		try {
			em.getTransaction().begin();
			em.merge(tbLocal);
			em.getTransaction().commit();
		
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Lista todos as comunidades cadastrada no Banco de Dados* 
	 * @return List<TbProdutos>
	 */
	@SuppressWarnings("unchecked")
	public List<TbLocal> listarTodasComunidades(){
		
		List<TbLocal> tbLocal = null;
		try {
			tbLocal = em.createNamedQuery("TbLocal.findAll").getResultList();
		} catch (Exception e) { }
		return tbLocal;
	}
	
	/**
	 * Exclui a comunidade passada por parametro.
	 * @param idLocal
	 * @return
	 */
	public boolean removerComunidade(int idLocal){
		
		TbLocal tbLocal = em.find(TbLocal.class, idLocal);
        if (tbLocal != null) {
            em.getTransaction().begin();
            em.remove(tbLocal);
            em.getTransaction().commit();
            return true;
        }
        return false;
	}
	
	/**
	 * Procura pela comunidade informada.
	 * @param comunidade
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TbLocal> findComunidade(String comunidade){
		
		List<TbLocal> local = null;
		
		try {
			local = em.createNamedQuery("TbLocal.findComunidade")
											.setParameter("nomeComunidade", "%"+ comunidade +"%")
											.getResultList();
		} catch (Exception e) { }
		return local;
	}
	
	
}
