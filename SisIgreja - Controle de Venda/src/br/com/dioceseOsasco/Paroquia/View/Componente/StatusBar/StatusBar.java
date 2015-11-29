package br.com.dioceseOsasco.Paroquia.View.Componente.StatusBar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private StatusBarPanel[] panels = null;

	/**
	 * This is the default constructor. The default panel array 
	 * is an array of two unspecified, unfixed panels;
	 */
	public StatusBar() {
		super();
		this.panels = new StatusBarPanel[] { new StatusBarPanel(), new StatusBarPanel() };
		initialize();
	}

	/**
	 * Allows you to set the StatusBarPanel array;
	 * @param panels 
	 */
	public StatusBar(StatusBarPanel[] panels) {
		super();
		this.panels = panels;
		initialize();
	}
	
	/**
	 * shared by two constructors.
	 */
	private void initialize() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 0;
		constraints.anchor=GridBagConstraints.WEST;
		constraints.insets = new Insets(0, 0, 0, 2);
		this.setLayout(new GridBagLayout());
		for (int i=0; i<panels.length; i++) {
			if (i==panels.length-1) constraints.insets=new Insets(0, 0, 0, 0);
			if (i==1) constraints.anchor=GridBagConstraints.CENTER;
			constraints.ipadx = (panels[i].getPanelWidth()==-1) ? 0 : panels[i].getPanelWidth();
			if (panels[i].isWidthFixed()) {
				constraints.fill=GridBagConstraints.NONE;
				constraints.weightx = 0;
			} else {
				constraints.fill=GridBagConstraints.HORIZONTAL;
				constraints.weightx = 100;
			}
			constraints.gridx=i;
			this.add(panels[i], constraints);
		}
	}

	public StatusBarPanel[] getPanels() {
		return panels;
	}

	public void setPanels(StatusBarPanel[] panels) {
		this.panels = panels;
		initialize();
	}

}
