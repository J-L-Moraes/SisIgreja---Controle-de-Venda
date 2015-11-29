package br.com.dioceseOsasco.Paroquia.View.Localizacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.LocalizacaoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbLocalizacao;
import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class LocalizacaoListener implements ActionListener {

	FrmLocalizacao frm;
	
	public LocalizacaoListener(FrmLocalizacao frm){
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		frm.btnCadastrarAlterar.addActionListener(this);
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
			
			if (!utilidade.VerificarCamposNulos(frm.txtDiocese.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar o nome da Diocese (ex. Osasco).", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtDiocese.grabFocus();
				break;
			}
			if (!utilidade.VerificarCamposNulos(frm.txtParoquia.getText())) {
				JOptionPane.showMessageDialog(null, "É necessario digitar o nome da Paróquia (ex. Bom Jesus e Santa Cruz).", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.txtDiocese.grabFocus();
				break;
			}
			
			
			String diocese, paroquia;
			
			diocese = frm.txtDiocese.getText();
			paroquia = frm.txtParoquia.getText();
			
			TbLocalizacao tbLocalizacao = new TbLocalizacao();
			
			tbLocalizacao.setIdLocalizacao(1); //Cada Paroquia deve ter um banco, raramente, alguma Paróquia conversa com a outra.
			tbLocalizacao.setNomeDiocese(diocese);
			tbLocalizacao.setNomeParoquia(paroquia);
			
			
			LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO();
			
			if (localizacaoDAO.cadastarAlterarLocalizacao(tbLocalizacao)) {
				JOptionPane.showMessageDialog(null, "Diocese e Paróquia atualizado ou cadastrado com sucesso no sistema. \nSeu formulário será encerrado!", frm.getTitle(),JOptionPane.INFORMATION_MESSAGE);
				frm.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "Ocorreu um erro interno ao cadastrar ou atualizar a Diocese e Paróquia no sistema.", frm.getTitle(),JOptionPane.ERROR_MESSAGE);
			}
			
			break;
		}
	}

}
