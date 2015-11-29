package br.com.dioceseOsasco.Paroquia.Controller;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.dioceseOsasco.Paroquia.Model.TbEvento;

public class EventoDAO {

	EntityManager em;
	
	public EventoDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	/***
	 * Cadastra o eventoTableModel no sistema.
	 * 
	 * @param produto
	 * @return
	 */
	public boolean cadastrarEvento(TbEvento tbEvento){
		
		try {
			em.getTransaction().begin();
			em.persist(tbEvento);
			em.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * Atualiza o evento cadastrado
	 * 
	 * @param produto
	 * @return boolean
	 */
	public boolean atualizarEvento(TbEvento tbEvento){
		try{
			em.getTransaction().begin();
			em.merge(tbEvento); 
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Retorna um Evento através do código passado por parametro
	 * @param idEvento
	 * @return TbEvento
	 */
	public TbEvento find(Integer idEvento){
		
		em.getTransaction().begin();
		TbEvento tbEvento = em.find(TbEvento.class, idEvento);
		em.getTransaction().commit();
		
		return tbEvento;	
	}
	
	/**
	 * Remover Evento cadastrado
	 * @param produto
	 * @return boolean
	 */
	public boolean removerEvento(int idEvento){
		
		TbEvento tbEvento = em.find(TbEvento.class, idEvento);
        if (tbEvento != null) {
            try {
				em.getTransaction().begin();
				em.remove(tbEvento);
				em.getTransaction().commit();
				return true;
			} catch (Exception e) {
				System.out.println("Erro ao Excluir Evento: "+e.getMessage());
				return false;
			}
        }
        return false;

		
	}
	
	
	/***
	 * Procura um Evento por Data.
	 * 
	 * @param Date data
	 * @return List<TbEvento>
	 */
	@SuppressWarnings("unchecked")
	public List<TbEvento> EventoPorData(Date data) {
		
		List<TbEvento> tbEvento = null;
		em.getTransaction().begin();
		try{
			tbEvento = em.createNamedQuery("TbEvento.findEventToday").setParameter("dataEvento", data).getResultList();
			em.getTransaction().commit();
		}catch(Exception e){
			em.getTransaction().rollback();
		}
		return tbEvento;	
	}
	
	/**
	 * Realiza uma pesquisa no banco pesquisando por nome do Evento e eventos entre as datas passadas por parametros
	 * @param nomeEvento
	 * @param dataIncial
	 * @param dataFinal
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<TbEvento> findEventoEntreDatasNome(String nomeEvento, Date dataIncial, Date dataFinal){
		
		List<TbEvento> TbEvento = null;
		
		try {
			
			TbEvento = em.createNamedQuery("TbEvento.findEventoEntreDatasNome")
											.setParameter("nomeEvento", "%"+ nomeEvento +"%")
											.setParameter("dataInicial", dataIncial)
											.setParameter("dataFinal", dataFinal)
											.getResultList();
			
		} catch (Exception e) { e.getMessage(); }
		return TbEvento;
	}
	
}
