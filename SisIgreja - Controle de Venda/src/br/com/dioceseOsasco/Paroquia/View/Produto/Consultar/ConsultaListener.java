package br.com.dioceseOsasco.Paroquia.View.Produto.Consultar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.MathContext;

import br.com.dioceseOsasco.Paroquia.View.Componente.UtilitarioVerificarCampos;

public class ConsultaListener implements ActionListener, KeyListener {

	FrmConsultaProduto frm;

	String txtProduto, txtPreco;

	ConsultaListener(FrmConsultaProduto frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {
		frm.btnPesquisar.addActionListener(this);
		frm.btnLimparCampos.addActionListener(this);
		frm.txtProduto.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand().toString();
		
		switch (evento) {
		case "Pesquisar":
				Pesquisar();
			break;
		case "Limpar Campos":
			frm.txtProduto.setText(null);
			frm.txtPreco.setText(null);
			break;
		}

	}

	@Override
	public void keyPressed(KeyEvent key) { 	}

	@Override
	public void keyReleased(KeyEvent key) { 
		Pesquisar();
	}

	@Override
	public void keyTyped(KeyEvent arg0) { 	}

	/**
	 * Pesquisa no Banco de dados a partir dos dados inseridos em Tela.
	 */
	private void Pesquisar() {
		
		txtProduto = frm.txtProduto.getText();
		txtPreco = frm.txtPreco.getText();

		UtilitarioVerificarCampos utilitario = new UtilitarioVerificarCampos();

		BigDecimal preco = null;
		try {
			MathContext mc = new MathContext(frm.txtPreco.getText().length()); // Precisão
			preco = new BigDecimal(frm.txtPreco.getText().replace(",", "."), mc);
			preco.toPlainString();

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		if ((utilitario.VerificarCampos(txtProduto)) && (utilitario.VerificarCampos(txtPreco))) {
			frm.tb_produto = frm.produtoDAO.findAllLikeDescricaoPreco(txtProduto, preco);
		} else if (utilitario.VerificarCampos(txtProduto) && !utilitario.VerificarCampos(txtPreco)) {
			frm.tb_produto = frm.produtoDAO.findAllLikeDescricao(txtProduto);
		} else if (!utilitario.VerificarCampos(txtProduto) && utilitario.VerificarCampos(txtPreco)) {
			frm.tb_produto = frm.produtoDAO.findAllPreco(preco);
		} else {
			frm.tb_produto = frm.produtoDAO.findAllOrdeByDescricaoASC();
		}

		frm.table.setModel(new ProdutosTableModel(frm.tb_produto));
	}

}
