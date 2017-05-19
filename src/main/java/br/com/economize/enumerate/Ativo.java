package br.com.economize.enumerate;

/**
 * @author Mateus Henrique Tofanello
 * 
 */

public enum Ativo {

	SIM, NAO;

	@Override
	public String toString() {
		switch (this) {
		case SIM:
			return "Sim";
		case NAO:
			return "Não";
		default:
			return null;
		}
	}
}