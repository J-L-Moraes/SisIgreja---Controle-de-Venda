package br.com.dioceseOsasco.Paroquia.View.Splash;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import br.com.dioceseOsasco.Paroquia.View.Login.FrmLogin;


@SuppressWarnings("serial")
public class Splash extends JWindow{
	

	public static void main(String[] args) {
		
		new Splash();
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				//Temas para a aplicação
				// SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.NebulaSkin"); //Tema Gelo
				// SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.BusinessBlackSteelSkin");Tema Brancos
				// SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.MarinerSkin");
				//SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.OfficeBlack2007Skin");
				
				SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.OfficeBlack2007Skin");
				//JFrame.setDefaultLookAndFeelDecorated(true);   /* Permite alterar o LookAndFeel do Frame */
				//JDialog.setDefaultLookAndFeelDecorated(true);    /* Permite alterar o tema da borda */  
			}
		});
				
		FrmLogin FrmLogin = new FrmLogin();
		FrmLogin.setVisible(true);

	}

	
	private JProgressBar barraDeProgresso;

	public Splash()  {
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {	} 
		
		getContentPane().setBackground(new Color(255, 255, 255));
		
		this.setSize(477, 189);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
		getContentPane().setLayout(null);

		barraDeProgresso = new JProgressBar();
		barraDeProgresso.setForeground(Color.DARK_GRAY);
		barraDeProgresso.setBounds(41, 158, 396, 20);
		barraDeProgresso.setStringPainted(true); //Porcentagem
		getContentPane().add(barraDeProgresso);
		
		
		final JLabel label = new JLabel("");
		label.setBounds(63, 0, 361, 189);
		getContentPane().add(label);
		label.setIcon(new ImageIcon(Splash.class.getResource("/br/com/dioceseOsasco/Paroquia/View/img/Login/Splash.fw.png")));

		Cursor cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
		setCursor(cursor);

		new Thread() {
			public void run() {
				try {
					for (int progress = 0; progress <= 100; progress++) {
						barraDeProgresso.setValue(progress);
						barraDeProgresso.setString("Carregando o sistema: "+progress+"%");
						sleep(20);
					}
				} catch (InterruptedException  Ie) {
					JOptionPane.showMessageDialog(null,	"Não Foi Possivel Carregar :" + Ie.getMessage());
				}
			}
		}.start();

		try {
			Thread.sleep(2005);
		} catch (InterruptedException e) { 	}

		this.dispose();
	}
	
}
