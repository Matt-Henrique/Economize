package br.com.economize.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Campanha extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;

	@Column(length = 80, nullable = false)
	private String titulo;

	@Column(length = 15, nullable = true)
	private String tipo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;

	@Column(length = 3, nullable = false)
	private Integer vigencia;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getVigencia() {
		return vigencia;
	}

	public void setVigencia(Integer vigencia) {
		this.vigencia = vigencia;
	}

	public Campanha() {
		empresa = new Empresa();
	}
}