package br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioProduto;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;

@SuppressWarnings("serial")
public class FrmRelatorioProduto extends JDialog{
	

	private JPanel pnlGerarRelatorio;
	JCheckBox chckbxTodosOsEventos;
	private JLabel lblEspecifiqueAData;
	private JLabel lblDataFinal;
	JCalendar cmBxDataEventoInicial;
	JButton btnSalvarRelatorio;
	JButton btnCancelar;
	JButton btnVisualizar;
	JCalendar cmBxDataEventoFinal;
	
	
	public FrmRelatorioProduto(){
		Interface();
		new RelatorioProdutoListener(this);
	}

	private void Interface() {
		setTitle("SisIgreja - Gerador de Relat\u00F3rio de Produto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRelatorioProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(559, 203);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnSalvarRelatorio = new JButton("Salvar Relat\u00F3rio");
		btnSalvarRelatorio.setIcon(new ImageIcon(FrmRelatorioProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/Relatorio3(24x24).png")));
		btnSalvarRelatorio.setBounds(256, 126, 137, 29);
		getContentPane().add(btnSalvarRelatorio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmRelatorioProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(403, 126, 137, 28);
		getContentPane().add(btnCancelar);
		
		pnlGerarRelatorio = new JPanel();
		pnlGerarRelatorio.setBorder(new TitledBorder(null, "Especifique as informa\u00E7\u00F5es para gerar o relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlGerarRelatorio.setBackground(Color.WHITE);
		pnlGerarRelatorio.setBounds(10, 11, 530, 103);
		getContentPane().add(pnlGerarRelatorio);
		pnlGerarRelatorio.setLayout(null);
		
		chckbxTodosOsEventos = new JCheckBox("Todos os eventos no sistema");
		chckbxTodosOsEventos.setBounds(18, 22, 198, 23);
		pnlGerarRelatorio.add(chckbxTodosOsEventos);
		chckbxTodosOsEventos.setSelected(true);
		chckbxTodosOsEventos.setBackground(Color.WHITE);
		
		lblEspecifiqueAData = new JLabel("Data Inicial:");
		lblEspecifiqueAData.setBounds(28, 55, 66, 23);
		pnlGerarRelatorio.add(lblEspecifiqueAData);
		
		cmBxDataEventoInicial = new JCalendar(false);
		cmBxDataEventoInicial.setBounds(92, 52, 166, 28);
		pnlGerarRelatorio.add(cmBxDataEventoInicial);
		cmBxDataEventoInicial.setEnabled(false);
		
		cmBxDataEventoFinal = new JCalendar(false);
		cmBxDataEventoFinal.setEnabled(false);
		cmBxDataEventoFinal.setBounds(332, 50, 166, 28);
		pnlGerarRelatorio.add(cmBxDataEventoFinal);
		
		lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setBounds(275, 59, 54, 14);
		pnlGerarRelatorio.add(lblDataFinal);
		
		btnVisualizar = new JButton("Visualizar Relat\u00F3rio");
		btnVisualizar.setIcon(new ImageIcon(FrmRelatorioProduto.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/VisualizarRelatorio(24x24).png")));
		btnVisualizar.setBounds(95, 125, 151, 31);
		getContentPane().add(btnVisualizar);
		
	}
}
