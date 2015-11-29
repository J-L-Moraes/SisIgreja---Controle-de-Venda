package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_evento database table.
 * 
 */
@Entity
@Table(name="tb_evento")
@NamedQueries({
	@NamedQuery(name="TbEvento.findAll", query="SELECT t FROM TbEvento t"),
	@NamedQuery(name="TbEvento.findEventToday", query="SELECT t FROM TbEvento t WHERE t.dataEvento = :dataEvento"),
	@NamedQuery(name="TbEvento.findEventoEntreDatasNome", query="SELECT t FROM TbEvento t WHERE t.nomeEvento LIKE :nomeEvento AND t.dataEvento BETWEEN :dataInicial AND :dataFinal")
})
public class TbEvento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEvento;
	private Date dataEvento;
	private String nomeEvento;
	private TbLocal tbLocal;
	private List<TbVenda> tbVendas;

	public TbEvento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdEvento() {
		return this.idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}


	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	public Date getDataEvento() {
		return this.dataEvento;
	}

	public void setDataEvento(Date dataEvento) {
		this.dataEvento = dataEvento;
	}


	@Column(nullable=false, length=80)
	public String getNomeEvento() {
		return this.nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}


	//bi-directional many-to-one association to TbLocal
	@ManyToOne
	@JoinColumn(name="FK_idLlocal", nullable=false)
	public TbLocal getTbLocal() {
		return this.tbLocal;
	}

	public void setTbLocal(TbLocal tbLocal) {
		this.tbLocal = tbLocal;
	}


	//bi-directional many-to-one association to TbVenda
	@OneToMany(mappedBy="tbEvento")
	public List<TbVenda> getTbVendas() {
		return this.tbVendas;
	}

	public void setTbVendas(List<TbVenda> tbVendas) {
		this.tbVendas = tbVendas;
	}

	public TbVenda addTbVenda(TbVenda tbVenda) {
		getTbVendas().add(tbVenda);
		tbVenda.setTbEvento(this);

		return tbVenda;
	}

	public TbVenda removeTbVenda(TbVenda tbVenda) {
		getTbVendas().remove(tbVenda);
		tbVenda.setTbEvento(null);

		return tbVenda;
	}
	
	@Override
	  public String toString() {
	      return getNomeEvento() +" - "+getTbLocal().getNomeComunidade();
	  }

}