package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tb_venda database table.
 * 
 */
@Entity
@Table(name="tb_venda")
@NamedQuery(name="TbVenda.findAll", query="SELECT t FROM TbVenda t")
public class TbVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger idVenda;
	private Time horaVenda;
	private byte vendaFinalizada;
	private List<TbItem> tbItems;
	private TbEvento tbEvento;
	private List<TbProduto> tbProdutos;
	private TbUsuario tbUsuario;

	public TbVenda() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public BigInteger getIdVenda() {
		return this.idVenda;
	}

	public void setIdVenda(BigInteger idVenda) {
		this.idVenda = idVenda;
	}


	@Column(nullable=false)
	public Time getHoraVenda() {
		return this.horaVenda;
	}

	public void setHoraVenda(Time horaVenda) {
		this.horaVenda = horaVenda;
	}


	@Column(nullable=false)
	public byte getVendaFinalizada() {
		return this.vendaFinalizada;
	}

	public void setVendaFinalizada(byte vendaFinalizada) {
		this.vendaFinalizada = vendaFinalizada;
	}


	//bi-directional many-to-one association to TbItem
	@OneToMany(mappedBy="tbVenda")
	public List<TbItem> getTbItems() {
		return this.tbItems;
	}

	public void setTbItems(List<TbItem> tbItems) {
		this.tbItems = tbItems;
	}

	public TbItem addTbItem(TbItem tbItem) {
		getTbItems().add(tbItem);
		tbItem.setTbVenda(this);

		return tbItem;
	}

	public TbItem removeTbItem(TbItem tbItem) {
		getTbItems().remove(tbItem);
		tbItem.setTbVenda(null);

		return tbItem;
	}


	//bi-directional many-to-one association to TbEvento
	@ManyToOne
	@JoinColumn(name="FK_idEvento", nullable=false)
	public TbEvento getTbEvento() {
		return this.tbEvento;
	}

	public void setTbEvento(TbEvento tbEvento) {
		this.tbEvento = tbEvento;
	}


	//bi-directional many-to-many association to TbProduto
	@ManyToMany
	@JoinTable(
		name="tb_item"
		, joinColumns={
			@JoinColumn(name="FK_idVenda", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="FK_idProduto", nullable=false)
			}
		)
	public List<TbProduto> getTbProdutos() {
		return this.tbProdutos;
	}

	public void setTbProdutos(List<TbProduto> tbProdutos) {
		this.tbProdutos = tbProdutos;
	}


	//bi-directional many-to-one association to TbUsuario
	@ManyToOne
	@JoinColumn(name="FK_idUsuario", nullable=false)
	public TbUsuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

}