package br.com.economize.domain;

import java.text.SimpleDateFormat;

/**
* @author Mateus Henrique Tofanello
* 
*/

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.economize.enumerate.TipoCampanha;

@SuppressWarnings("serial")
@Entity
public class Campanha extends GenericDomain {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;

	@Column(length = 50, nullable = false)
	private String titulo;

	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCampanha tipo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataInicial;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFinal;

	@Column(length = 3, nullable = false)
	private Short vigencia;

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

	public TipoCampanha getTipo() {
		return tipo;
	}

	public void setTipo(TipoCampanha tipo) {
		this.tipo = tipo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}
	
	public String getDataInicialFormatada() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    return sdf.format(this.getDataInicial());
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}
	
	public String getDataFinalFormatada() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    return sdf.format(this.getDataFinal());
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Short getVigencia() {
		return vigencia;
	}

	public void setVigencia(Short vigencia) {
		this.vigencia = vigencia;
	}

	public Campanha() {
		empresa = new Empresa();
	}
}