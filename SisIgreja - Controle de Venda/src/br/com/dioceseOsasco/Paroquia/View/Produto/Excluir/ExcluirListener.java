package br.com.dioceseOsasco.Paroquia.View.Produto.Excluir;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import br.com.dioceseOsasco.Paroquia.Controller.ProdutoDAO;
import br.com.dioceseOsasco.Paroquia.Model.TbProduto;

public class ExcluirListener implements MouseListener, ActionListener {

	private FrmExcluirProduto frm;
	private TbProduto tbproduto;
	private boolean AtualizarComboBox;

	ExcluirListener(FrmExcluirProduto frm) {
		this.frm = frm;
		adicionaListener();
	}

	private void adicionaListener() {

		frm.btnCancelar.addActionListener(this);
		frm.cmBxProduto.addActionListener(this);
		frm.btnExcluirProduto.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		String evento;
		evento = event.getActionCommand();

		switch (evento) {
		case "Cancelar":
			frm.dispose();
			break;
		case "comboBoxChanged":

			if (!AtualizarComboBox) {

				tbproduto = ((TbProduto) frm.cmBxProduto.getSelectedItem());
				frm.lblPreco.setText("Preço: R$" + tbproduto.getPreco());

				if (((byte) 1) == tbproduto.getAtivo()) {
					frm.lblStatusProduto.setText("O produto possui no estoque.");
				} else {
					frm.lblStatusProduto.setText("O produto não possui no estoque.");
				}
			}

			break;
		case "Excluir produto":

			if (frm.cmBxProduto.getSelectedItem() != null) {

				int selectedOption = JOptionPane.showConfirmDialog(null,
						"Tem certeza que deseja excluir o produto " + frm.cmBxProduto.getSelectedItem() + "?",
						frm.getTitle(), JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {

					tbproduto = ((TbProduto) frm.cmBxProduto.getSelectedItem());
					ProdutoDAO produtoDAO = new ProdutoDAO();

					if (produtoDAO.removerProduto(tbproduto.getIdProduto())) {
						selectedOption = JOptionPane.showConfirmDialog(null,
								"Produto excluido com sucesso.\nVocê deseja ainda excluir mais produto?",
								frm.getTitle(), JOptionPane.YES_NO_OPTION);
						if (selectedOption == JOptionPane.YES_OPTION) {

							AtualizarComboBox = true;
							frm.cmBxProduto.removeAllItems();
							
							frm.tb_produto = produtoDAO.listarTodosProduto();
							for (TbProduto produtos : frm.tb_produto) {
								frm.cmBxProduto.addItem(produtos);
							}
							frm.cmBxProduto.setSelectedItem(null);
							frm.lblPreco.setText(null);
							frm.lblStatusProduto.setText(null);

							AtualizarComboBox = false;
						} else {
							frm.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Ocorreu um erro ao tentar excluir o produto no banco de dados\n Talvez não seja possivel... até excluir todas as referências do mesmo no banco de dados.",
								"Erro ao excluir produto", JOptionPane.ERROR_MESSAGE);
					}

				}

			} else {
				JOptionPane.showMessageDialog(null,
						"Por favor, para excluir o produto selecione-o nos itens cadastrados", frm.getTitle(),
						JOptionPane.INFORMATION_MESSAGE);
				frm.cmBxProduto.grabFocus();
			}
			break;
		// default:

		// break;
		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
