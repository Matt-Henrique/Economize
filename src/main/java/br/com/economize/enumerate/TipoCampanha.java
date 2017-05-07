package br.com.economize.enumerate;

public enum TipoCampanha {

	COMUM, PREMIUM;

	@Override
	public String toString() {
		switch (this) {
		case COMUM:
			return "Comum";
		case PREMIUM:
			return "Premium";
		default:
			return null;
		}
	}
}