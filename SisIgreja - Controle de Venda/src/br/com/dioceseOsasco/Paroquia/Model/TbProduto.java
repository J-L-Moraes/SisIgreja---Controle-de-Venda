package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tb_produto database table.
 * 
 */
@Entity
@Table(name="tb_produto")
@NamedQueries({
	@NamedQuery(name="TbProduto.findAll", query="SELECT t FROM TbProduto t"),
	@NamedQuery(name="TbProduto.findAllOrdeByDescricaoASC", query="SELECT t FROM TbProduto t ORDER BY t.descricao ASC"),
	@NamedQuery(name="TbProduto.findAllativoASC", query="SELECT t FROM TbProduto t WHERE t.ativo = 1 ORDER BY t.descricao ASC"),
	@NamedQuery(name="TbProduto.findAllLikeDescricao", query="SELECT t FROM TbProduto t WHERE t.descricao LIKE :descricao ORDER BY t.descricao ASC"),
	@NamedQuery(name="TbProduto.findAllPreco", query="SELECT t FROM TbProduto t WHERE t.preco = :preco ORDER BY t.descricao ASC"),
	@NamedQuery(name="TbProduto.findAllLikeDescricaoPreco", query="SELECT t FROM TbProduto t WHERE t.preco LIKE :preco AND t.descricao LIKE :descricao ORDER BY t.descricao ASC")
})
public class TbProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idProduto;
	private byte ativo;
	private String descricao;
	private BigDecimal preco;
	private List<TbItem> tbItems;
	private List<TbVenda> tbVendas;

	public TbProduto() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdProduto() {
		return this.idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}


	@Column(nullable=false)
	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}


	@Column(nullable=false, length=50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Column(nullable=false, precision=10, scale=2)
	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	//bi-directional many-to-one association to TbItem
	@OneToMany(mappedBy="tbProduto")
	public List<TbItem> getTbItems() {
		return this.tbItems;
	}

	public void setTbItems(List<TbItem> tbItems) {
		this.tbItems = tbItems;
	}

	public TbItem addTbItem(TbItem tbItem) {
		getTbItems().add(tbItem);
		tbItem.setTbProduto(this);

		return tbItem;
	}

	public TbItem removeTbItem(TbItem tbItem) {
		getTbItems().remove(tbItem);
		tbItem.setTbProduto(null);

		return tbItem;
	}


	//bi-directional many-to-many association to TbVenda
	@ManyToMany(mappedBy="tbProdutos")
	public List<TbVenda> getTbVendas() {
		return this.tbVendas;
	}

	public void setTbVendas(List<TbVenda> tbVendas) {
		this.tbVendas = tbVendas;
	}
	
	
	@Override
	  public String toString() {
	      return getDescricao();
	  }

}