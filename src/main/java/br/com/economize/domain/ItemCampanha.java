package br.com.economize.domain;

/**
* @author Mateus Henrique Tofanello
* 
*/

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemCampanha extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Campanha campanha;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal precoOferta = BigDecimal.ZERO;

	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal precoNormal = BigDecimal.ZERO;

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getPrecoOferta() {
		return precoOferta;
	}

	public void setPrecoOferta(BigDecimal precoOferta) {
		this.precoOferta = precoOferta;
	}

	public BigDecimal getPrecoNormal() {
		return precoNormal;
	}

	public void setPrecoNormal(BigDecimal precoNormal) {
		this.precoNormal = precoNormal;
	}

	public ItemCampanha() {
		campanha = new Campanha();
	}
}