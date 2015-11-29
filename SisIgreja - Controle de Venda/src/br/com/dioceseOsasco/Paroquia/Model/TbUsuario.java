package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tb_usuario database table.
 * 
 */
@Entity
@Table(name="tb_usuario")
@NamedQuery(name="TbUsuario.resetarSenha", query="UPDATE TbUsuario t SET t.senha = :dataNascimento WHERE t.usuario = :usuario")
@NamedQueries({
	@NamedQuery(name="TbUsuario.findAll", query="SELECT t FROM TbUsuario t"),
	@NamedQuery(name="TbUsuario.findNome", query="SELECT t FROM TbUsuario t WHERE t.nome LIKE :nome ORDER BY t.nome ASC"),
	@NamedQuery( name="TbUsuario.findUsuarioSenha", query="SELECT t FROM TbUsuario t WHERE t.usuario = :usuario AND t.senha = :senha"),
	@NamedQuery( name="TbUsuario.findUsuario", query="SELECT t FROM TbUsuario t WHERE t.usuario = :usuario")
	})
public class TbUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idUsuario;
	private byte ativo;
	private Date dataNascimento;
	private String nivelTipo;
	private String nome;
	private String senha;
	private String usuario;
	private TbPermissao tbPermissao;
	private List<TbVenda> tbVendas;

	public TbUsuario() {
		
	}

	//Usuario Atual
	public static TbUsuario instance;

	public static TbUsuario getInstance(boolean instanciado) {
		if(instance == null){
	          instance = new TbUsuario();
	    }
	    return instance;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	@Column(nullable=false)
	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}


	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	@Column(nullable=false, length=30)
	public String getNivelTipo() {
		return this.nivelTipo;
	}

	public void setNivelTipo(String nivelTipo) {
		this.nivelTipo = nivelTipo;
	}


	@Column(nullable=false, length=60)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(nullable=false, length=30)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Column(nullable=false, length=30)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	//bi-directional many-to-one association to TbPermissao
	@ManyToOne
	@JoinColumn(name="FK_idPermissao", nullable=false)
	public TbPermissao getTbPermissao() {
		return this.tbPermissao;
	}

	public void setTbPermissao(TbPermissao tbPermissao) {
		this.tbPermissao = tbPermissao;
	}


	//bi-directional many-to-one association to TbVenda
	@OneToMany(mappedBy="tbUsuario")
	public List<TbVenda> getTbVendas() {
		return this.tbVendas;
	}

	public void setTbVendas(List<TbVenda> tbVendas) {
		this.tbVendas = tbVendas;
	}

	public TbVenda addTbVenda(TbVenda tbVenda) {
		getTbVendas().add(tbVenda);
		tbVenda.setTbUsuario(this);

		return tbVenda;
	}

	public TbVenda removeTbVenda(TbVenda tbVenda) {
		getTbVendas().remove(tbVenda);
		tbVenda.setTbUsuario(null);

		return tbVenda;
	}

}