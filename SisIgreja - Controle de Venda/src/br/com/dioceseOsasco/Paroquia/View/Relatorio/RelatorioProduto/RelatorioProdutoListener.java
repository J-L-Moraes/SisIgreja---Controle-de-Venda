package br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioProduto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Date;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.dioceseOsasco.Paroquia.Controller.RelatorioDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbItem;
import br.com.dioceseOsasco.Paroquia.View.Componente.ConversorData;
import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;
import br.com.dioceseOsasco.Paroquia.View.Relatorio.UtilitarioRelatorio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioProdutoListener implements ActionListener {

	FrmRelatorioProduto frm;
	
	public RelatorioProdutoListener(FrmRelatorioProduto frm){
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		frm.btnCancelar.addActionListener(this);
		frm.btnSalvarRelatorio.addActionListener(this);
		frm.btnVisualizar.addActionListener(this);
		frm.chckbxTodosOsEventos.addChangeListener(new ChckbxTodosOsEventosChangeListener());
	}

	//Evento comboBox 
		private class ChckbxTodosOsEventosChangeListener implements ChangeListener {
			public void stateChanged(ChangeEvent arg0) {
				if (frm.chckbxTodosOsEventos.isSelected()) {
					frm.cmBxDataEventoInicial.setEnabled(false);
					frm.cmBxDataEventoFinal.setEnabled(false);
				}else{
					frm.cmBxDataEventoInicial.setEnabled(true);
					frm.cmBxDataEventoFinal.setEnabled(true);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			String evento;
			evento = event.getActionCommand();
			
			switch (evento) {
			case "Cancelar":
				 	frm.dispose();
				break;
				
			case "Visualizar Relat\u00F3rio":
				
				if (frm.chckbxTodosOsEventos.isSelected()) {
					JasperPrint jasperPrint = gerarRelatorio("RelatorioProduto.jasper");
					if (jasperPrint != null) {
						frm.dispose();
						
						JasperViewer viewer = new JasperViewer(jasperPrint, false);
						
						//Abrindo o Relatório
						viewer.setVisible(true);
						//abrindo maximizado  
						viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
						 //setando um titulo p/ o relatório  
						viewer.setTitle("Relatório de Produto");
					}
				}else{
					
					if ( (frm.cmBxDataEventoInicial.getSelectedItem() == null) && (frm.cmBxDataEventoFinal.getSelectedItem() == null)  ) {
						JOptionPane.showMessageDialog(null, "É necessario digitar a data do evento para gerar o relatório.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						frm.cmBxDataEventoInicial.grabFocus();
						break;
					}
					
					
					
					
					Date dataInicial = null;
					if (frm.cmBxDataEventoInicial.getSelectedItem() != null){
						try{
							String DataString = (((JCalendar) frm.cmBxDataEventoInicial).getText());
							ConversorData conversorData = new ConversorData();
							dataInicial = conversorData.ConverterStringParaDate(DataString);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.\n" + e1.getMessage(), frm.getTitle(), JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					
					Date dataFinal = null;
					if (frm.cmBxDataEventoFinal.getSelectedItem() != null){
						try{
							String DataString = (((JCalendar) frm.cmBxDataEventoFinal).getText());
							ConversorData conversorData = new ConversorData();
							dataFinal = conversorData.ConverterStringParaDate(DataString);
						} catch (ParseException e1) {
							JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.\n" + e1.getMessage(), frm.getTitle(), JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					
					if (dataInicial == null) {
						dataInicial = dataFinal;
					}
					
					
					if (dataInicial != null && dataFinal != null) {
						if (dataInicial.after(dataFinal)) {
							Date dInicial = dataFinal;
							Date dFinal = dataInicial;
							dataInicial = dInicial;
							dataFinal = dFinal;
						}
					}
					
					
					
					
					JasperPrint jasperPrint;
					if  ( (frm.cmBxDataEventoInicial.getSelectedItem() != null) && (frm.cmBxDataEventoFinal.getSelectedItem() != null)  ) {
						jasperPrint = gerarRelatorio("RelatorioProduto.jasper", dataInicial, dataFinal );
					}else{
						jasperPrint = gerarRelatorio("RelatorioProduto.jasper", dataInicial);
					}
					
					if (jasperPrint != null) {
						frm.dispose();
						
						JasperViewer viewer = new JasperViewer(jasperPrint, false);
						
						//Abrindo o Relatório
						viewer.setVisible(true);
						//abrindo maximizado  
						viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
						 //setando um titulo p/ o relatório  
						viewer.setTitle("Relatório de Produto");
					}
				}
				
				break;
			case "Salvar Relat\u00F3rio":
				
				if (frm.chckbxTodosOsEventos.isSelected()) {

					//Abrir o SelectionFile
					JFileChooser JFileChooser = new JFileChooser();
					JFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Portable Document Format(*.PDF)", "PDF"));  
					JFileChooser.setAcceptAllFileFilterUsed(false);  
					// Impede seleções múltiplas.  
					JFileChooser.setMultiSelectionEnabled(false);  
					JFileChooser.setDialogTitle("Selecione o local para salvar o seu relatório...");
				
					int returnVal = JFileChooser.showSaveDialog(null);
					if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
					
						File file = JFileChooser.getSelectedFile();
						
						JasperPrint jasperPrint = gerarRelatorio("RelatorioProduto.jasper");
						
						if (jasperPrint != null) {
							try {
								JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath()+".pdf");
								JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso.",frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
								frm.dispose();
							} catch ( JRException e) {
								JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o relatório.",frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}else{
						break;
					}
					
					break;
				}
		
				
				if ( (frm.cmBxDataEventoInicial.getSelectedItem() == null) && (frm.cmBxDataEventoFinal.getSelectedItem() == null)  ) {
					JOptionPane.showMessageDialog(null, "É necessario digitar a data do evento para gerar o relatório.", frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
					frm.cmBxDataEventoInicial.grabFocus();
					break;
				}
				
				
				
				
				Date dataInicial = null;
				if (frm.cmBxDataEventoInicial.getSelectedItem() != null){
					try{
						String DataString = (((JCalendar) frm.cmBxDataEventoInicial).getText());
						ConversorData conversorData = new ConversorData();
						dataInicial = conversorData.ConverterStringParaDate(DataString);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.\n" + e1.getMessage(), frm.getTitle(), JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				
				Date dataFinal = null;
				if (frm.cmBxDataEventoFinal.getSelectedItem() != null){
					try{
						String DataString = (((JCalendar) frm.cmBxDataEventoInicial).getText());
						ConversorData conversorData = new ConversorData();
						dataFinal = conversorData.ConverterStringParaDate(DataString);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null,"Não foi possivel converter a data para o sistema.\n" + e1.getMessage(), frm.getTitle(), JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				
				if (dataInicial == null) {
					dataInicial = dataFinal;
				}
				
				
				
				//Abrir o SelectionFile
				JFileChooser JFileChooser = new JFileChooser();
				JFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Portable Document Format(*.PDF)", "PDF"));  
				JFileChooser.setAcceptAllFileFilterUsed(false);  
				// Impede seleções múltiplas.  
				JFileChooser.setMultiSelectionEnabled(false);  
				JFileChooser.setDialogTitle("Selecione o local para salvar o seu relatório...");
			
				int returnVal = JFileChooser.showSaveDialog(null);
				if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
				
					File file = JFileChooser.getSelectedFile();
					

					
					JasperPrint jasperPrint;
					if  ( (frm.cmBxDataEventoInicial.getSelectedItem() != null) && (frm.cmBxDataEventoFinal.getSelectedItem() != null)  ) {
						jasperPrint = gerarRelatorio("RelatorioProduto.jasper", dataInicial, dataFinal );
					}else{
						jasperPrint = gerarRelatorio("RelatorioProduto.jasper", dataInicial);
					}
					
					if (jasperPrint != null) {
						
						
						try {
							JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath()+".pdf");
							JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso.",frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
							frm.dispose();
						} catch ( JRException e) {
							JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar o relatório.",frm.getTitle(), JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				break;
			}
			
		}
		
		
		
		/**
		 * Método para gerar o relatório 
		 * @param nomeRelatorio
		 * @return
		 */
		private JasperPrint gerarRelatorio(String nomeRelatorio){
			
			Collection<TbItem> CollectionTbItem = new LinkedList<TbItem>();
			RelatorioDAO relatorioDAO = new RelatorioDAO();
			CollectionTbItem = relatorioDAO.RelatorioProduto();
			
			JasperPrint jasperPrint = new UtilitarioRelatorio().gerarRelatorio(nomeRelatorio, CollectionTbItem);
			
			return jasperPrint;
			
		}
		
		/**
		 * Método para gerar o relatório
		 * @param nomeRelatorio
		 * @param data
		 * @return
		 */
		private JasperPrint gerarRelatorio(String nomeRelatorio, Date data){
			
			Collection<TbItem> CollectionTbItem = new LinkedList<TbItem>();
			RelatorioDAO relatorioDAO = new RelatorioDAO();
			CollectionTbItem = relatorioDAO.RelatorioProduto(data);
			
			JasperPrint jasperPrint = new UtilitarioRelatorio().gerarRelatorio(nomeRelatorio, CollectionTbItem);
			
			return jasperPrint;
			
		}
		
		/**
		 * Método para gerar o relatório
		 * @param nomeRelatorio
		 * @param data
		 * @return
		 */
		private JasperPrint gerarRelatorio(String nomeRelatorio, Date dataInicial, Date dataFinal){
			
			Collection<TbItem> CollectionTbItem = new LinkedList<TbItem>();
			RelatorioDAO relatorioDAO = new RelatorioDAO();
			CollectionTbItem = relatorioDAO.RelatorioProduto(dataInicial, dataFinal);
			
			JasperPrint jasperPrint = new UtilitarioRelatorio().gerarRelatorio(nomeRelatorio, CollectionTbItem);
			
			return jasperPrint;
			
		}

}
