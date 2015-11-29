package br.com.dioceseOsasco.Paroquia.Model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_permissao database table.
 * 
 */
@Entity
@Table(name="tb_permissao")
@NamedQuery(name="TbPermissao.findAll", query="SELECT t FROM TbPermissao t")
public class TbPermissao implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idPermissao;
	private byte abrirCaixa;
	private byte alterarEvento;
	private byte alterarProduto;
	private byte alterarUsuario;
	private byte analisarPedido;
	private byte atualizarComunidade;
	private byte cadastrarComunidade;
	private byte cadastrarEvento;
	private byte cadastrarProduto;
	private byte cadastrarUsuario;
	private byte dioceseParoquia;
	private byte excluirComunidade;
	private byte excluirEvento;
	private byte excluirProduto;
	private byte excluirUsuario;
	private byte pesquisarEvento;
	private byte procurarComunidade;
	private byte procurarProduto;
	private byte procurarUsuario;
	private byte redefinirSenhaOutros;
	private byte relatorioDeProduto;
	private byte relatorioDeVenda;
	private byte relatorioDeVendaDetalhado;
	private List<TbUsuario> tbUsuarios;

	public TbPermissao() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getIdPermissao() {
		return this.idPermissao;
	}

	public void setIdPermissao(int idPermissao) {
		this.idPermissao = idPermissao;
	}


	@Column(nullable=false)
	public byte getAbrirCaixa() {
		return this.abrirCaixa;
	}

	public void setAbrirCaixa(byte abrirCaixa) {
		this.abrirCaixa = abrirCaixa;
	}


	@Column(nullable=false)
	public byte getAlterarEvento() {
		return this.alterarEvento;
	}

	public void setAlterarEvento(byte alterarEvento) {
		this.alterarEvento = alterarEvento;
	}


	@Column(nullable=false)
	public byte getAlterarProduto() {
		return this.alterarProduto;
	}

	public void setAlterarProduto(byte alterarProduto) {
		this.alterarProduto = alterarProduto;
	}


	@Column(nullable=false)
	public byte getAlterarUsuario() {
		return this.alterarUsuario;
	}

	public void setAlterarUsuario(byte alterarUsuario) {
		this.alterarUsuario = alterarUsuario;
	}


	@Column(nullable=false)
	public byte getAnalisarPedido() {
		return this.analisarPedido;
	}

	public void setAnalisarPedido(byte analisarPedido) {
		this.analisarPedido = analisarPedido;
	}


	@Column(nullable=false)
	public byte getAtualizarComunidade() {
		return this.atualizarComunidade;
	}

	public void setAtualizarComunidade(byte atualizarComunidade) {
		this.atualizarComunidade = atualizarComunidade;
	}


	@Column(nullable=false)
	public byte getCadastrarComunidade() {
		return this.cadastrarComunidade;
	}

	public void setCadastrarComunidade(byte cadastrarComunidade) {
		this.cadastrarComunidade = cadastrarComunidade;
	}


	@Column(nullable=false)
	public byte getCadastrarEvento() {
		return this.cadastrarEvento;
	}

	public void setCadastrarEvento(byte cadastrarEvento) {
		this.cadastrarEvento = cadastrarEvento;
	}


	@Column(nullable=false)
	public byte getCadastrarProduto() {
		return this.cadastrarProduto;
	}

	public void setCadastrarProduto(byte cadastrarProduto) {
		this.cadastrarProduto = cadastrarProduto;
	}


	@Column(nullable=false)
	public byte getCadastrarUsuario() {
		return this.cadastrarUsuario;
	}

	public void setCadastrarUsuario(byte cadastrarUsuario) {
		this.cadastrarUsuario = cadastrarUsuario;
	}

	@Column(nullable=false)
	public byte getDioceseParoquia() {
		return this.dioceseParoquia;
	}

	public void setDioceseParoquia(byte dioceseParoquia) {
		this.dioceseParoquia = dioceseParoquia;
	}


	@Column(nullable=false)
	public byte getExcluirComunidade() {
		return this.excluirComunidade;
	}

	public void setExcluirComunidade(byte excluirComunidade) {
		this.excluirComunidade = excluirComunidade;
	}


	@Column(nullable=false)
	public byte getExcluirEvento() {
		return this.excluirEvento;
	}

	public void setExcluirEvento(byte excluirEvento) {
		this.excluirEvento = excluirEvento;
	}


	@Column(nullable=false)
	public byte getExcluirProduto() {
		return this.excluirProduto;
	}

	public void setExcluirProduto(byte excluirProduto) {
		this.excluirProduto = excluirProduto;
	}


	@Column(nullable=false)
	public byte getExcluirUsuario() {
		return this.excluirUsuario;
	}

	public void setExcluirUsuario(byte excluirUsuario) {
		this.excluirUsuario = excluirUsuario;
	}


	@Column(nullable=false)
	public byte getPesquisarEvento() {
		return this.pesquisarEvento;
	}

	public void setPesquisarEvento(byte pesquisarEvento) {
		this.pesquisarEvento = pesquisarEvento;
	}


	@Column(nullable=false)
	public byte getProcurarComunidade() {
		return this.procurarComunidade;
	}

	public void setProcurarComunidade(byte procurarComunidade) {
		this.procurarComunidade = procurarComunidade;
	}


	@Column(nullable=false)
	public byte getProcurarProduto() {
		return this.procurarProduto;
	}

	public void setProcurarProduto(byte procurarProduto) {
		this.procurarProduto = procurarProduto;
	}


	@Column(nullable=false)
	public byte getProcurarUsuario() {
		return this.procurarUsuario;
	}

	public void setProcurarUsuario(byte procurarUsuario) {
		this.procurarUsuario = procurarUsuario;
	}


	@Column(nullable=false)
	public byte getRedefinirSenhaOutros() {
		return this.redefinirSenhaOutros;
	}

	public void setRedefinirSenhaOutros(byte redefinirSenhaOutros) {
		this.redefinirSenhaOutros = redefinirSenhaOutros;
	}


	@Column(nullable=false)
	public byte getRelatorioDeProduto() {
		return this.relatorioDeProduto;
	}

	public void setRelatorioDeProduto(byte relatorioDeProduto) {
		this.relatorioDeProduto = relatorioDeProduto;
	}


	@Column(nullable=false)
	public byte getRelatorioDeVenda() {
		return this.relatorioDeVenda;
	}

	public void setRelatorioDeVenda(byte relatorioDeVenda) {
		this.relatorioDeVenda = relatorioDeVenda;
	}


	@Column(nullable=false)
	public byte getRelatorioDeVendaDetalhado() {
		return this.relatorioDeVendaDetalhado;
	}

	public void setRelatorioDeVendaDetalhado(byte relatorioDeVendaDetalhado) {
		this.relatorioDeVendaDetalhado = relatorioDeVendaDetalhado;
	}


	//bi-directional many-to-one association to TbUsuario
	@OneToMany(mappedBy="tbPermissao")
	public List<TbUsuario> getTbUsuarios() {
		return this.tbUsuarios;
	}

	public void setTbUsuarios(List<TbUsuario> tbUsuarios) {
		this.tbUsuarios = tbUsuarios;
	}

	public TbUsuario addTbUsuario(TbUsuario tbUsuario) {
		getTbUsuarios().add(tbUsuario);
		tbUsuario.setTbPermissao(this);

		return tbUsuario;
	}

	public TbUsuario removeTbUsuario(TbUsuario tbUsuario) {
		getTbUsuarios().remove(tbUsuario);
		tbUsuario.setTbPermissao(null);

		return tbUsuario;
	}

}