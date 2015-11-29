package br.com.dioceseOsasco.Paroquia.View.Local.Cadastrar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.LocalDAO;
import br.com.dioceseOsasco.Paroquia.Controller.LocalizacaoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocal;
import br.com.dioceseOsasco.Paroquia.Model.TbLocalizacao;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class CadastrarLocalListener implements ActionListener {

	FrmCadastrarLocal frm;
	
	public CadastrarLocalListener(FrmCadastrarLocal frm){
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		frm.btnCadastrarComunidade.addActionListener(this);
		frm.btnCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String evento;
		evento = event.getActionCommand();
		
		switch (evento) {
		case "Cancelar":
				frm.dispose();
			break;
		case "Cadastrar":
				
			UtilitarioVerificarCampos utilidade = new UtilitarioVerificarCampos();
	
			if (!utilidade.VerificarCamposNulos(frm.txtComunidade.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar o nome da Comunidade (ex. Matriz).", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtDiocese.grabFocus();
				break;
			}
			
			String comunidade = frm.txtComunidade.getText();
			
			TbLocal tbLocal = new TbLocal();
			
			TbLocalizacao tbLocalizacao = new TbLocalizacao();
			LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
			tbLocalizacao = localizacaoDAO.find(1);
			
			tbLocal.setTbLocalizacao(tbLocalizacao);
			tbLocal.setNomeComunidade(comunidade);
			
			LocalDAO localDAO = new LocalDAO();
			
			if (localDAO.CadastrarLocal(tbLocal)) {
				int OpcaoSelecionada = JOptionPane.showConfirmDialog(null,"Comunidade cadastrada com sucesso.\nDeseja cadastrar outra Comunidade no sistema?", frm.getTitle(),JOptionPane.YES_NO_OPTION);
				if (OpcaoSelecionada == JOptionPane.NO_OPTION) 
					frm.dispose();
				else{
					frm.txtComunidade.setText(null);
				}
			}
			
			break;
		}
	}
}
