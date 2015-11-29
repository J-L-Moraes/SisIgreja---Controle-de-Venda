package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_local database table.
 * 
 */
@Entity
@Table(name="tb_local")
@NamedQueries({
	@NamedQuery(name="TbLocal.findAll", query="SELECT t FROM TbLocal t"),
	@NamedQuery(name="TbLocal.findComunidade", query="SELECT t FROM TbLocal t WHERE t.nomeComunidade LIKE :nomeComunidade")
})
public class TbLocal implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idLlocal;
	private String nomeComunidade;
	private List<TbEvento> tbEventos;
	private TbLocalizacao tbLocalizacao;

	public TbLocal() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdLlocal() {
		return this.idLlocal;
	}

	public void setIdLlocal(int idLlocal) {
		this.idLlocal = idLlocal;
	}


	@Column(nullable=false, length=60)
	public String getNomeComunidade() {
		return this.nomeComunidade;
	}

	public void setNomeComunidade(String nomeComunidade) {
		this.nomeComunidade = nomeComunidade;
	}


	//bi-directional many-to-one association to TbEvento
	@OneToMany(mappedBy="tbLocal")
	public List<TbEvento> getTbEventos() {
		return this.tbEventos;
	}

	public void setTbEventos(List<TbEvento> tbEventos) {
		this.tbEventos = tbEventos;
	}

	public TbEvento addTbEvento(TbEvento tbEvento) {
		getTbEventos().add(tbEvento);
		tbEvento.setTbLocal(this);

		return tbEvento;
	}

	public TbEvento removeTbEvento(TbEvento tbEvento) {
		getTbEventos().remove(tbEvento);
		tbEvento.setTbLocal(null);

		return tbEvento;
	}


	//bi-directional many-to-one association to TbLocalizacao
	@ManyToOne
	@JoinColumn(name="FK_idLocalizacao", nullable=false)
	public TbLocalizacao getTbLocalizacao() {
		return this.tbLocalizacao;
	}

	public void setTbLocalizacao(TbLocalizacao tbLocalizacao) {
		this.tbLocalizacao = tbLocalizacao;
	}
	
	@Override
	  public String toString() {
	      return getNomeComunidade();
	  }

}