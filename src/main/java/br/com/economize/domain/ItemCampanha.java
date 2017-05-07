package br.com.economize.domain;

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
	private BigDecimal valorOferta = BigDecimal.ZERO;

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

	public BigDecimal getValorOferta() {
		return valorOferta;
	}

	public void setValorOferta(BigDecimal valorOferta) {
		this.valorOferta = valorOferta;
	}
	
	public ItemCampanha() {
		campanha = new Campanha();
	}
}