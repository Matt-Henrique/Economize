package br.com.economize.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Produto extends GenericDomain {

	@Column(length = 100, nullable = false)
	private String descricao;

	@Column(length = 50, nullable = false)
	private String descricaoReduzida;

	@Column(length = 10, nullable = false)
	private String unidadeMedida;

	@Column(length = 15, nullable = false)
	private String embalagem;
	
	@Column(length = 15, nullable = false)
	private Integer ean;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Categoria categoria;
	
	@Column(length = 20, nullable = false)
	private String subCategoria;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;
	
	@Transient
	private String caminho;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoReduzida() {
		return descricaoReduzida;
	}

	public void setDescricaoReduzida(String descricaoReduzida) {
		this.descricaoReduzida = descricaoReduzida;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}

	public Integer getEan() {
		return ean;
	}

	public void setEan(Integer ean) {
		this.ean = ean;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getSubCategoria() {
		return subCategoria;
	}
	
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}