package br.com.economize.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class ItensCampanha extends GenericDomain {

	@OneToOne
	@JoinColumn(nullable = false)
	private Campanha campanha;

	@OneToMany
	private List<Produto> produtos;

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ItensCampanha() {
		campanha = new Campanha();
	}
}