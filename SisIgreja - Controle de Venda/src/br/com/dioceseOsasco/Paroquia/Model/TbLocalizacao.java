package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_localizacao database table.
 * 
 */
@Entity
@Table(name="tb_localizacao")
@NamedQuery(name="TbLocalizacao.findAll", query="SELECT t FROM TbLocalizacao t")
public class TbLocalizacao implements Serializable {
	

	
	private static final long serialVersionUID = 1L;
	private int idLocalizacao;
	private String nomeDiocese;
	private String nomeParoquia;
	private List<TbLocal> tbLocals;

	public TbLocalizacao() {
	}


	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdLocalizacao() {
		return this.idLocalizacao;
	}

	public void setIdLocalizacao(int idLocalizacao) {
		this.idLocalizacao = idLocalizacao;
	}


	@Column(nullable=false, length=60)
	public String getNomeDiocese() {
		return this.nomeDiocese;
	}

	public void setNomeDiocese(String nomeDiocese) {
		this.nomeDiocese = nomeDiocese;
	}


	@Column(nullable=false, length=60)
	public String getNomeParoquia() {
		return this.nomeParoquia;
	}

	public void setNomeParoquia(String nomeParoquia) {
		this.nomeParoquia = nomeParoquia;
	}


	//bi-directional many-to-one association to TbLocal
	@OneToMany(mappedBy="tbLocalizacao")
	public List<TbLocal> getTbLocals() {
		return this.tbLocals;
	}

	public void setTbLocals(List<TbLocal> tbLocals) {
		this.tbLocals = tbLocals;
	}

	public TbLocal addTbLocal(TbLocal tbLocal) {
		getTbLocals().add(tbLocal);
		tbLocal.setTbLocalizacao(this);

		return tbLocal;
	}

	public TbLocal removeTbLocal(TbLocal tbLocal) {
		getTbLocals().remove(tbLocal);
		tbLocal.setTbLocalizacao(null);

		return tbLocal;
	}

}