package br.com.economize.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Campanha extends GenericDomain {

	@Column(length = 80, nullable = false)
	private String titulo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;

	@Column(length = 3, nullable = false)
	private Integer vigencia;

	@Column(length = 15, nullable = true)
	private String tipo;

	@OneToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Campanha() {
		empresa = new Empresa();
	}
}