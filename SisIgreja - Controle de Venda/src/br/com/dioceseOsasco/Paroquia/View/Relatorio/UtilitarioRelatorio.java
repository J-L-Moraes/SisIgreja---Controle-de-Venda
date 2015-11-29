package br.com.dioceseOsasco.Paroquia.View.Relatorio;

import java.util.Collection;
import java.util.HashMap;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UtilitarioRelatorio {
	
	private String path; //Caminho base
	private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jasper
	
	public UtilitarioRelatorio() {
		//Recupera os caminhos para que a classe possa encontrar os relatórios
		this.path = this.getClass().getClassLoader().getResource("").getPath();
		this.pathToReportPackage = this.path + "br/com/dioceseOsasco/Paroquia/Relatorio/";
	}

	
	public JasperPrint gerarRelatorio(String nomeRelatorio, Collection<TbItem> CollectionTbItem) {

		JasperPrint jasperPrint = null;

		try{			
			jasperPrint = JasperFillManager.fillReport(pathToReportPackage.replace("%20", " ") + nomeRelatorio, new HashMap<String, Object>(), new JRBeanCollectionDataSource(CollectionTbItem));

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o relatório.\n" + e.getMessage(),
					"Erro ao gerar o relatório", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro geral ao gerar o relatório.\n" + e.getMessage(),
					"Erro ao gerar o relatório", JOptionPane.ERROR_MESSAGE);
		}

		return jasperPrint;
	}
}
