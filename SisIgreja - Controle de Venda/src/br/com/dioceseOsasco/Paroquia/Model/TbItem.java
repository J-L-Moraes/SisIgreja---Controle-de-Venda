package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * The persistent class for the tb_item database table.
 * 
 */
@Entity
@Table(name="tb_item")
@NamedQueries({
	@NamedQuery(name="TbItem.findAll", query="SELECT t FROM TbItem t"),
	@NamedQuery(name="TbItem.findTbProdutoTbVenda", query="SELECT t FROM TbItem t WHERE t.tbVenda = :tbVenda"),
	@NamedQuery(name="TbItem.consultarPedidoItem", query="SELECT t FROM TbItem t WHERE t.tbVenda.idVenda = :idVenda"),
	@NamedQuery(name="TbItem.findSUMValorItem", query="SELECT SUM(t.valorItem) FROM TbItem t WHERE t.tbVenda.idVenda = :idVenda")
})
public class TbItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger idItem;
	private int quantidade;
	private BigDecimal valorItem;
	private TbProduto tbProduto;
	private TbVenda tbVenda;

	public TbItem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public BigInteger getIdItem() {
		return this.idItem;
	}

	public void setIdItem(BigInteger idItem) {
		this.idItem = idItem;
	}


	@Column(nullable=false)
	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getValorItem() {
		return this.valorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		this.valorItem = valorItem;
	}


	//bi-directional many-to-one association to TbProduto
	@ManyToOne
	@JoinColumn(name="FK_idProduto", nullable=false)
	public TbProduto getTbProduto() {
		return this.tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}


	//bi-directional many-to-one association to TbVenda
	@ManyToOne
	@JoinColumn(name="FK_idVenda", nullable=false)
	public TbVenda getTbVenda() {
		return this.tbVenda;
	}

	public void setTbVenda(TbVenda tbVenda) {
		this.tbVenda = tbVenda;
	}
	
	@Override
	  public String toString() {
	      return tbProduto.getDescricao();
	  }

}