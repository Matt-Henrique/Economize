package br.com.economize.domain;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.economize.geocode.CepWebService;

@Entity
public class Endereco extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(length = 15, nullable = false)
	private String cep = null;

	@Column(length = 100, nullable = false)
	private String logradouro;

	@Column(length = 50, nullable = false)
	private String tipoLogradouro;

	@Column(length = 50, nullable = false)
	private String estado;

	@Column(length = 100, nullable = false)
	private String cidade;

	@Column(length = 100, nullable = false)
	private String bairro;

	public void encontraCEP() {
		CepWebService cepWebService = new CepWebService(getCep());

		if (cepWebService.getResultado() == 1) {
			setTipoLogradouro(cepWebService.getTipoLogradouro());
			setLogradouro(cepWebService.getLogradouro());
			setEstado(cepWebService.getEstado());
			setCidade(cepWebService.getCidade());
			setBairro(cepWebService.getBairro());
		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"O servidor não está respondendo", "O servidor não está respondendo"));
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}