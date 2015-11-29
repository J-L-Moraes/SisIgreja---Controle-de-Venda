package br.com.dioceseOsasco.Paroquia.View.Sobre;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FrmSobre extends JDialog {
	private JButton btnOk;
	private JLabel lblParte1;
	private JLabel lblEclipseMars;
	private JLabel lblJava;
	private JLabel lblJpaCom;
	private JLabel lblFlamingo;
	private JLabel lblJasperreport;
	private JLabel lblDesenvolvidoPorJos;
	private JLabel lblParquiaBom;

	public FrmSobre() {
		Interface();

	}

	private void Interface() {
		setTitle("SisIgreja - Sobre o sistema");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FrmSobre.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/IconeSistema.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setSize(469, 255);
		this.setLocationRelativeTo(null);

		setResizable(false);
		setModal(true);

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new BtnOkActionListener());
		btnOk.setBounds(344, 185, 107, 29);
		getContentPane().add(btnOk);

		lblParte1 = new JLabel("SisIgreja - Desenvolvido utilizando das seguintes tecnologias:");
		lblParte1.setBounds(61, 11, 321, 29);
		getContentPane().add(lblParte1);

		lblEclipseMars = new JLabel("- Eclipse Mars 4.1");
		lblEclipseMars.setBounds(61, 61, 138, 22);
		getContentPane().add(lblEclipseMars);

		lblJava = new JLabel("- Java 8.65");
		lblJava.setBounds(61, 84, 138, 22);
		getContentPane().add(lblJava);

		lblJpaCom = new JLabel("- JPA com mySQL");
		lblJpaCom.setBounds(209, 51, 138, 22);
		getContentPane().add(lblJpaCom);

		lblFlamingo = new JLabel("- Flamingo");
		lblFlamingo.setBounds(209, 73, 138, 22);
		getContentPane().add(lblFlamingo);

		lblJasperreport = new JLabel("- JasperReport");
		lblJasperreport.setBounds(209, 97, 138, 22);
		getContentPane().add(lblJasperreport);

		lblDesenvolvidoPorJos = new JLabel("Desenvolvido por Jos\u00E9 Lucas Moraes ");
		lblDesenvolvidoPorJos.setBounds(188, 130, 210, 22);
		getContentPane().add(lblDesenvolvidoPorJos);

		lblParquiaBom = new JLabel("\u00E0 Par\u00F3quia Bom Jesus e Santa Cruz - Canguera (S\u00E3o Roque/SP)");
		lblParquiaBom.setBounds(61, 152, 337, 22);
		getContentPane().add(lblParquiaBom);

	}

	private class BtnOkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}
}
