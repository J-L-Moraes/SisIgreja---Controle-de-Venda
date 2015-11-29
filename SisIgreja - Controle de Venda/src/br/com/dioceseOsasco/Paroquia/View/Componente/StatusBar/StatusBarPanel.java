package br.com.dioceseOsasco.Paroquia.View.Componente.StatusBar;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class StatusBarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private int panelWidth = -1;
	private boolean widthFixed = false;
	public StatusBarPanel() {
		super();
		this.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	}
	public int getPanelWidth() {
		return panelWidth;
	}
	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}
	public boolean isWidthFixed() {
		return widthFixed;
	}
	public void setWidthFixed(boolean widthFixed) {
		this.widthFixed = widthFixed;
	}

}
