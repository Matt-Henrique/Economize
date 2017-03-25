package br.com.economize.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class ItemCampanha extends GenericDomain{
	
	@Column(nullable = true, precision = 7, scale = 2)
	private BigDecimal precoOferta;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Campanha campanha;

	public BigDecimal getPrecoOferta() {
		return precoOferta;
	}

	public void setPrecoOferta(BigDecimal precoOferta) {
		this.precoOferta = precoOferta;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public ItemCampanha() {
		campanha = new Campanha();
	}
}