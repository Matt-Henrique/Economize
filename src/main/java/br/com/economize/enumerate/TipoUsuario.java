package br.com.economize.enumerate;

public enum TipoUsuario {

	ADMINISTRADOR, EMPRESA;

	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRADOR:
			return "Administtrador";
		case EMPRESA:
			return "Empresa";
		default:
			return null;
		}
	}
}