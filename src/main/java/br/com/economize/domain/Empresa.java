package br.com.economize.domain;

/**
* @author Mateus Henrique Tofanello
* 
*/

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity
public class Empresa extends GenericDomain {

	@Column(length = 50, nullable = false)
	private String nomeFantasia;

	@Column(length = 50, nullable = false)
	private String razaoSocial;

	@Column(length = 15, nullable = false)
	private String ie;

	@Column(length = 20, nullable = false)
	private String cnpj;

	@Column(length = 15, nullable = false)
	private String fone1;

	@Column(length = 15, nullable = true)
	private String fone2;

	@Column(length = 15, nullable = true)
	private String fone3;

	@Column(length = 100, nullable = false)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = false)
	private Endereco endereco;

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1;
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2;
	}

	public String getFone3() {
		return fone3;
	}

	public void setFone3(String fone3) {
		this.fone3 = fone3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Empresa() {
		endereco = new Endereco();
	}
}