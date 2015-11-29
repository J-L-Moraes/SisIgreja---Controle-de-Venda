package br.com.dioceseOsasco.Paroquia.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;


public abstract class ConnectionFactory {

	private static String CaminhoBanco;
	private static String Usuario;
	private static String senha;
	
	/**
	 * Getters and Setters
	 */
	public static String getCaminhoBanco() {
		return CaminhoBanco;
	}

	public static void setCaminhoBanco(String caminhoBanco) {
		CaminhoBanco = caminhoBanco;
	}

	public static String getUsuario() {
		return Usuario;
	}

	public static void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		ConnectionFactory.senha = senha;
	}

	private ConnectionFactory() {
	}
	
	

	private static EntityManagerFactory entityManagerFactory;

	public static EntityManager getEntityManager() {
		
		if (entityManagerFactory == null) {
			
			Map<String, String> properties = new HashMap<String, String>();
			
			try {
				abrirDados();
				properties.put("javax.persistence.jdbc.url", getCaminhoBanco());
				properties.put("javax.persistence.jdbc.user", getUsuario());
				properties.put("javax.persistence.jdbc.password", getSenha());
				
			} catch (IOException e) {
				System.out.println("Não foi possivel ler o Arquivo de configuração da conexão");
			}
			
			try {

				entityManagerFactory = Persistence.createEntityManagerFactory("SisIgreja", properties);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer à conexão com o banco de dados.\n"
						+ "Por favor verifique se o serviço do banco de dados está ativo ou se os parametros da configuração estão corretos.", "Erro ao estabelcer a conexão ",
						JOptionPane.ERROR_MESSAGE);
			}	
		}
		
		return entityManagerFactory.createEntityManager();
	}
	
	public static void closeEntityManager(EntityManager em) {  
		if (entityManagerFactory != null) {
			em.close();
		}
	} 
	
	/**
	 * Abre o arquivo de informações de configurações.
	 * @return 
	 */
	public static void abrirDados() throws IOException {
		Properties properties = new Properties();
		FileInputStream file = new FileInputStream("./propriedade/dados.xml");
		properties.loadFromXML(file);
		
		setCaminhoBanco(properties.getProperty("javax.persistence.jdbc.url")); 
		setUsuario(properties.getProperty("javax.persistence.jdbc.user")); 
		setSenha(properties.getProperty("javax.persistence.jdbc.password")); 
	}

	/**
	 * Configura o Arquivo de informações para conexão.
	 */
	public static void alterarDados(String CaminhoServidor, String Usuario, String Senha) throws IOException{
	
		Properties properties = new Properties();
		String caminho = "./propriedade/dados.xml";
		
		properties.put("javax.persistence.jdbc.url", CaminhoServidor);
		properties.put("javax.persistence.jdbc.user", Usuario);
		properties.put("javax.persistence.jdbc.password", Senha);
		
		FileOutputStream fos = new FileOutputStream(caminho);
		properties.storeToXML(fos, "Arquivo de Configuração Gerado Por José Lucas Moraes", "ISO-8859-1");
		fos.close();

	}
	
}
