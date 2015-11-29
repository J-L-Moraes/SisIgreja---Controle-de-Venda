package br.com.dioceseOsasco.Paroquia.View.Relatorio.RelatorioDetalhado;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.View.Componente.JCalendar;

@SuppressWarnings("serial")
public class FrmRelatorioDetalhado extends JDialog{
	
	JCheckBox chckbxTodosOsEventos;
	private JLabel lblEspecifiqueAData;
	JComboBox<?> cmBxDataEvento;
	JButton btnSalvarRelatorio;
	JButton btnCancelar;
	private JPanel pnlGerarRelatorio;
	JButton btnVisualizar;
	
	
	public FrmRelatorioDetalhado(){
		Interface();
		new RelatorioDetalhadoListener(this);
	}

	private void Interface() {
		setTitle("SisIgreja - Gerador de Relat\u00F3rio Detalhado");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRelatorioDetalhado.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(479, 218);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnSalvarRelatorio = new JButton("Salvar Relat\u00F3rio");
		btnSalvarRelatorio.setIcon(new ImageIcon(FrmRelatorioDetalhado.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/Relatorio2(24x24).png")));
		btnSalvarRelatorio.setBounds(171, 139, 137, 29);
		getContentPane().add(btnSalvarRelatorio);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmRelatorioDetalhado.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(318, 139, 137, 28);
		getContentPane().add(btnCancelar);
		
		pnlGerarRelatorio = new JPanel();
		pnlGerarRelatorio.setBorder(new TitledBorder(null, "Especifique as informa\u00E7\u00F5es para gerar o relat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlGerarRelatorio.setBackground(Color.WHITE);
		pnlGerarRelatorio.setBounds(42, 13, 400, 115);
		getContentPane().add(pnlGerarRelatorio);
		pnlGerarRelatorio.setLayout(null);
		
		chckbxTodosOsEventos = new JCheckBox("Todos os eventos no sistema");
		chckbxTodosOsEventos.setBounds(25, 33, 198, 23);
		pnlGerarRelatorio.add(chckbxTodosOsEventos);
		chckbxTodosOsEventos.setSelected(true);
		chckbxTodosOsEventos.setBackground(Color.WHITE);
		
		lblEspecifiqueAData = new JLabel("Especifique a data:");
		lblEspecifiqueAData.setBounds(64, 66, 98, 23);
		pnlGerarRelatorio.add(lblEspecifiqueAData);
		
		cmBxDataEvento = new JCalendar(false);
		cmBxDataEvento.setBounds(172, 63, 166, 28);
		pnlGerarRelatorio.add(cmBxDataEvento);
		cmBxDataEvento.setEnabled(false);
		
		btnVisualizar = new JButton("Visualizar Relat\u00F3rio");
		btnVisualizar.setIcon(new ImageIcon(FrmRelatorioDetalhado.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Relatorio/VisualizarRelatorio(24x24).png")));
		btnVisualizar.setBounds(10, 138, 151, 31);
		getContentPane().add(btnVisualizar);
		
	}

}
