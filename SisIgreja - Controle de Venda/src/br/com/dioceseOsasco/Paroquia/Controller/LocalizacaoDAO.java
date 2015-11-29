package br.com.dioceseOsasco.Paroquia.Controller;

import javax.persistence.EntityManager;

import br.com.dioceseOsasco.Paroquia.Model.TbLocalizacao;

public class LocalizacaoDAO {
	
	EntityManager em;
	
	public LocalizacaoDAO() {
		em = ConnectionFactory.getEntityManager();
	}
	
	
	/**
	 * Pesquisa em todos os registro (Por Causa do Padrão Sigleton) e retorna o registro da TbLocalização
	 * @param idLocalizacao
	 * @return
	 */
	public TbLocalizacao find(Integer idLocalizacao) {

		TbLocalizacao tbLocalizacao;
		tbLocalizacao = null;
		try {
			em.getTransaction().begin();
			tbLocalizacao = em.find(TbLocalizacao.class, idLocalizacao);
			em.getTransaction().commit();
		} catch (Exception e) { System.out.println("Ocorreu um Erro: "+e.getMessage());	}

		
		return tbLocalizacao;	
	}
	
	/**
	 * Cadastra ou alterar a Localização da Diocese e Paroquia no Banco de Dados
	 * @param tbLocalizacao
	 * @return
	 */
	
	public boolean cadastarAlterarLocalizacao(TbLocalizacao tbLocalizacao){
		
		try {
			em.getTransaction().begin();
			em.merge(tbLocalizacao);
			em.getTransaction().commit();
		
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}

}
