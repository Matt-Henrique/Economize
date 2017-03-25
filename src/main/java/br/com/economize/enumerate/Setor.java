package br.com.economize.enumerate;

public enum Setor {

	ADMINISTRATIVO, CADASTRO;

	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRATIVO:
			return "Administrativo";
		case CADASTRO:
			return "Cadastro";
		default:
			return null;
		}
	}
}