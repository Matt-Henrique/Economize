package br.com.economize.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

import br.com.economize.enumerate.Setor;

@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name = "codigo")
public class Administrador extends Usuario {

	@Column(length = 15, nullable = false)
	@Enumerated(EnumType.STRING)
	private Setor setor;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}