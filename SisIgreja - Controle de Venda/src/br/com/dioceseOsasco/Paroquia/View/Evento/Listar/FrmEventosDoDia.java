package br.com.dioceseOsasco.Paroquia.View.Evento.Listar;

import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.com.dioceseOsasco.Paroquia.Controller.EventoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbEvento;

@SuppressWarnings("serial")
public class FrmEventosDoDia extends JDialog {
	
	private JLabel lblEvento;
	JButton btnCadastrarEvento;
	JButton btnCancelar;
	private JPanel panel;
	JComboBox<TbEvento> cmbEventoDoDia;
	JButton btnConfirmar;
	
	
	int idEvento;
	
	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

	
	public FrmEventosDoDia() {
		Interface();
		new EventoDoDiaListener(this);
	
	}

	private void Interface() {

		setTitle("SisIgreja - Eventos do dia");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmEventosDoDia.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(464, 207);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);
		

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		btnCadastrarEvento = new JButton("Cadastrar Evento");
		btnCadastrarEvento.setIcon(new ImageIcon(FrmEventosDoDia.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Evento/CalendarioAdicionar(16x16).fw.png")));
		btnCadastrarEvento.setBounds(171, 133, 153, 30);
		getContentPane().add(btnCadastrarEvento);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(FrmEventosDoDia.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/CancelarRemover.png")));
		btnCancelar.setBounds(334, 133, 102, 30);
		getContentPane().add(btnCancelar);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selecione o evento ou cadastre um evento para o dia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 437, 111);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblEvento = new JLabel("Evento :");
		lblEvento.setBounds(43, 46, 53, 28);
		panel.add(lblEvento);
		
		cmbEventoDoDia = new JComboBox<TbEvento>();
		cmbEventoDoDia.setBounds(106, 46, 285, 28);
		popularComboBox();
		panel.add(cmbEventoDoDia);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(FrmEventosDoDia.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/checar.png")));
		btnConfirmar.setBounds(31, 133, 130, 30);
		getContentPane().add(btnConfirmar);
		getRootPane().setDefaultButton(btnConfirmar); //Botão Padrão
	}
	
	/**
	 * Popula-se o comboBox com os eventos do dia
	 * 
	 * Através da pesquisa por data do sistema com o Banco de dados.
	 */
    public void popularComboBox()  
    {     
    	cmbEventoDoDia.removeAllItems();

    	EventoDAO eventoDAO = new EventoDAO();
    	
    	// Data do Sistema...
    	Date data = new java.sql.Date(new java.util.Date().getTime());
    	List<TbEvento> tbEvento = eventoDAO.EventoPorData(data);

        for (TbEvento tb_Evento : tbEvento) {
        	cmbEventoDoDia.addItem(tb_Evento);
        }
        
        cmbEventoDoDia.setSelectedItem(null);		
    } 
}
