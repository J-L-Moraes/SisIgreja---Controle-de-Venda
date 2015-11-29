package br.com.dioceseOsasco.Paroquia.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.dioceseOsasco.Paroquia.Model.TbPermissao;
import br.com.dioceseOsasco.Paroquia.Model.TbUsuario;

public class UsuarioDAO {

	EntityManager em;

	public UsuarioDAO() {
		em = ConnectionFactory.getEntityManager();
	}

	/**
	 * Pesquisa pelo usuario atraves de seu ID.
	 * @param idUsuario
	 * @return
	 */
	public TbUsuario find(Integer idUsuario) {

		em.getTransaction().begin();
		TbUsuario tbUsuario = em.find(TbUsuario.class, idUsuario);
		em.getTransaction().commit();

		return tbUsuario;
	}

	/***
	 * Procura no Banco de dados se usuario e senha confere.
	 * 
	 * @param usuario
	 * @param senha
	 * @return ResultList
	 */
	public TbUsuario VerificarLoginSenha(String usuario, String senha) {
		
		TbUsuario tb_usuario = new TbUsuario() ;
		
		try{
		tb_usuario = (TbUsuario) em.createNamedQuery("TbUsuario.findUsuarioSenha")
									.setParameter("usuario", usuario)
									.setParameter("senha", senha).getSingleResult();
		}catch(Exception e){
			System.out.println("Ocorreu um erro ao realizar consulta ou Consulta retornou nula :\n"+e.getMessage());
		}
		return tb_usuario;
	}

	/***
	 * Procura no Banco de dados se existe o usuario no banco de dados.
	 * 
	 * @param usuario
	 * @param senha
	 * @return ResultList
	 */
	public TbUsuario VerificarLogin(String usuario) {
		TbUsuario tb_usuario = new TbUsuario();
		try{
			tb_usuario = (TbUsuario) em.createNamedQuery("TbUsuario.findUsuario").setParameter("usuario", usuario)
					.getSingleResult();
		}catch(Exception e){ e.getMessage();}
		return tb_usuario;
	}

	/**
	 * Cadastra o usuario no Banco de dados
	 * 
	 * @param tb_usuario
	 * @param tb_permissao
	 * @return 
	 */
	public boolean salvarUsuario(TbUsuario tbUsuario, TbPermissao tbPermissao) {

		try {
			em.getTransaction().begin(); 

			em.persist(tbPermissao);
			tbUsuario.setTbPermissao(tbPermissao);
			em.persist(tbUsuario);
			
			em.getTransaction().commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao Cadastrar Usuario: "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * Atualiza o usuario no Banco de dados
	 * 
	 * @param tb_usuario
	 * @param tb_permissao
	 * @return 
	 */
	public boolean atualizarUsuario(TbUsuario tbUsuario, TbPermissao tbPermissao) {

		try {
			em.getTransaction().begin(); 
			em.merge(tbPermissao);
			em.merge(tbUsuario);
			em.getTransaction().commit();

			return true;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Erro ao Atualizar o usuario: "+e.getMessage());
			return false;
		}
	}
	
	/**
	 * Remover Usuário Cadastrado
	 * @param tbUsuario
	 * @return boolean
	 */
	public boolean removerUsuario(TbUsuario tbUsuario){
		
		try{
            em.getTransaction().begin();
            em.remove(tbUsuario);
            em.remove(tbUsuario.getTbPermissao());
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
        	return false;}

		
	}
	
	public boolean alterarSenha(TbUsuario tbUsuario){
		
		try{
            em.getTransaction().begin();
            em.merge(tbUsuario);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
        	System.out.println("Ocorreu um erro ao alterar à senha.");
        	return false;}
		
	}
	

	/**
	 * Reseta a senha do usuário, Sendo que a nova senha será sua data de
	 * Nascimento.
	 * 
	 * @param tb_usuario
	 * @return boolean
	 */
	public boolean resetarSenha(TbUsuario tbUsuario) {

		Date DataNascimento = tbUsuario.getDataNascimento();
		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		String DataNascimentoConvertida;
		try {

			DataNascimentoConvertida = out.format(in.parse(DataNascimento.toString()));

			em.getTransaction().begin();

			Query query = em.createNamedQuery("TbUsuario.resetarSenha")
											.setParameter("dataNascimento", DataNascimentoConvertida)
											.setParameter("usuario", tbUsuario.getUsuario());

			int linhasAfetadas = query.executeUpdate();

			if (linhasAfetadas > 1 || linhasAfetadas == 0) {
				em.getTransaction().rollback();
				ConnectionFactory.closeEntityManager(em);
				return false;
			} else {
				em.getTransaction().commit();
				ConnectionFactory.closeEntityManager(em);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Procura no Banco usuarios cadastrados no sistema atraves de seu nome
	 * @return List<TbUsuario>
	 */
	@SuppressWarnings("unchecked")
	public List<TbUsuario> findNome(String nome){
			
		List<TbUsuario> tbUsuario = null;
		
		try {
			tbUsuario = em.createNamedQuery("TbUsuario.findNome")
											.setParameter("nome", "%"+ nome +"%")
											.getResultList();
		} catch (Exception e) { }
		return tbUsuario;
	}

}
